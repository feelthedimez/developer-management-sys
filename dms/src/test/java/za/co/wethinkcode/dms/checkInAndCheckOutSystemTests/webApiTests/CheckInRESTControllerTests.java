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
import java.util.Map;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
                        .content(asJsonString(Map.of("username", "tetema", "date", "2022-03-13", "time", "09:30")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("Check In Successful")))
                .andExpect(status().isCreated());
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
    @DisplayName("POST /avail/checkin - Posting twice to receive an error")
    void postTwiceTest() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/avail/checkin")
                                .content(asJsonString(Map.of("username", "tetema", "date", "2022-03-13", "time", "09:30")))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("Check In Successful")))
                .andExpect(status().isCreated())
                .andDo(result ->  this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/avail/checkout")
                                .content(asJsonString(Map.of("username", "tetema", "date", "2022-03-13", "time", "09:30")))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(content().json(asJsonString(Map.of("message", "Never checked in..."))))
                        .andExpect(status().isBadRequest())
                );
    }

    @Test
    @DisplayName("GET /avail/checkin/{username}/{date} - Normal GET request")
    void retrieveDataByDateGETTest() throws Exception {
        CheckInEntity checkInEntity = new CheckInEntity("tetema", LocalTime.parse("08:30"), LocalDate.parse("2022-03-13"));
        when(service.getCheckInDataByDateAndUserName(LocalDate.parse("2022-03-13"), "tetema")).thenReturn(java.util.Optional.of(checkInEntity));

        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/avail/checkin/tetema/2022-03-13")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(asJsonString(Map.of(
                        "username", "tetema",
                        "date", "2022-03-13",
                        "time", "08:30:00"
                ))));
    }

    @Test
    @DisplayName("GET /avail/checkin/{username}/{date} - With invalid date format")
    void getInvalidDateFormatWhenGETByDateTest() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/avail/checkin/tetema/2022-03-32")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(asJsonString(Map.of(
                        "message", "Incorrect date format"
                ))));

    }


    public static String asJsonString(final Map<String, String> obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
