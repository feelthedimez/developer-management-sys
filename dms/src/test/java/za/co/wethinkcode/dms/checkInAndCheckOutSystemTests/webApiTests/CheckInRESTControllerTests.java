package za.co.wethinkcode.dms.checkInAndCheckOutSystemTests.webApiTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import za.co.wethinkcode.dms.checkInAndOutSystem.entities.CheckInEntity;
import za.co.wethinkcode.dms.checkInAndOutSystem.services.CheckInService;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class CheckInRESTControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CheckInService service;

    @Test
    @DisplayName("POST /avail/checkin - Normal post endpoint")
    void normalCheckInApiPOSTTest() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/avail/checkin")
                        .content(
                                "{" +
                                    "\"username\":\"tetema\"," +
                                    "\"date\":\"2022-03-13\"," +
                                    "\"time\":\"09:30:00\"," +
                                    "\"userLate\":false," +
                                    "\"userCheckedIn\":true" +
                                "}"
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("Check In Successful")))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("POST /avail/checkin - Check in twice")
    void checkIfUserChecksInTwice() {
        // I have to find out how to post twice using MockMVC to test for the logic
    }

    @Test
    @DisplayName("POST /avail/checkin - No body with the post-request")
    void emptyBodyCheckInApiPOSTTest() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/avail/checkin")
                                .content("")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("GET /avail/checkin/{username}/{date} - Normal GET request")
    void retrieveCheckInDataByDateGETTest() throws Exception {
        CheckInEntity checkInEntity = new CheckInEntity("tetema", LocalTime.parse("08:30"), LocalDate.parse("2022-03-13"));
        when(service.getCheckInDataByDateAndUserName(LocalDate.parse("2022-03-13"), "tetema")).thenReturn(Optional.of(checkInEntity));

        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/avail/checkin/tetema/2022-03-13")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                    "{" +
                                    "\"username\":\"tetema\"," +
                                    "\"date\":\"2022-03-13\"," +
                                    "\"time\":\"08:30:00\"," +
                                    "\"userLate\":false," +
                                    "\"userCheckedIn\":true" +
                                "}"
                ));
    }

    @Test
    @DisplayName("GET avail/checkin/{username}/{date} - Normal GET request")
    void retrieveCheckInDataByDataNoDataGetTest() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/avail/checkin/tetema/2022-03-13")
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(asJsonString(Map.of("message", "Check in data not found"))));
    }

    @Test
    @DisplayName("GET /avail/checkin/{username}/{date} - With invalid date format")
    void getInvalidDateFormatWhenGETByDateCheckInTest() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/avail/checkin/tetema/2022-03-32")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(asJsonString(Map.of("message", "Incorrect date format"))));

    }


    @Test
    @DisplayName("GET /checkin/all/ - Get every checkin data in the database")
    void getAllCheckInDataTest() throws Exception {
        CheckInEntity checkInEntity1 = new CheckInEntity("tetema", LocalTime.parse("08:30"), LocalDate.parse("2022-03-13"));
        CheckInEntity checkInEntity2 = new CheckInEntity("justin", LocalTime.parse("08:32"), LocalDate.parse("2022-03-13"));
        when(service.getAllCheckIn()).thenReturn(new ArrayList<>(Arrays.asList(checkInEntity1, checkInEntity2)));

        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/avail/checkin/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                        "[" +
                                "{\"username\":\"tetema\"," +
                                "\"date\":\"2022-03-13\"," +
                                "\"time\":\"08:30:00\"," +
                                "\"userLate\":false," +
                                "\"userCheckedIn\":true}" +
                                "," +
                                "{\"username\":\"justin\"," +
                                "\"date\":\"2022-03-13\"," +
                                "\"time\":\"08:32:00\"," +
                                "\"userLate\":false," +
                                "\"userCheckedIn\":true" +
                                "}"+
                        "]"))
        ;
    }

    @Test
    @DisplayName("GET /checkin/all/ - Get every checkin data in the database, it should be empty")
    void getAnEmptyListAllCheckInDataTest() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/avail/checkin/all")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("[]")));
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
