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
import za.co.wethinkcode.dms.checkInAndOutSystem.entities.CheckOutEntity;
import za.co.wethinkcode.dms.checkInAndOutSystem.services.CheckInService;
import za.co.wethinkcode.dms.checkInAndOutSystem.services.CheckOutService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CheckOutRESTControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CheckInService checkInService;

    @MockBean
    private CheckOutService checkOutService;

    @Test
    @DisplayName("POST /avail/checkout - Never checked in exception")
    void normalCheckOutPostWithCheckInButNeverCheckInTest() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/avail/checkout")
                        .content(asJsonString(Map.of("username", "tetema", "date", "2022-03-13", "time", "14:30")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().json(asJsonString(Map.of("message", "Never checked in..."))))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /avail/checkout - Normal checkout when user checked in")
    void normalCheckOutPostTest() throws Exception {
        // I have to find out how to post twice using MockMVC
    }

    @Test
    @DisplayName("GET avail/checkout/{username}/{date} - Normal GET request")
    void retrieveCheckOutDataByDateGETTEst() throws Exception {
        CheckOutEntity checkOutEntity = new CheckOutEntity("tetema", LocalTime.parse("17:30"), LocalDate.parse("2022-03-13"));
        when(checkOutService.getCheckOutDataByDateAndUserName(LocalDate.parse("2022-03-13"), "tetema")).thenReturn(Optional.of(checkOutEntity));

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/avail/checkout/tetema/2022-03-13")
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                        "{" +
                                "\"username\":\"tetema\"," +
                                "\"date\":\"2022-03-13\"," +
                                "\"time\":\"17:30:00\"," +
                                "\"didUserCheckOutLate\":false," +
                                "\"userCheckedOut\":true" +
                                "}"
                ));
    }

    @Test
    @DisplayName("GET avail/checkout/{username}/{date} - Normal GET request")
    void retrieveCheckOutDataByDataNoDataGetTest() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/avail/checkout/tetema/2022-03-13")
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(asJsonString(Map.of("message", "Check out data not found"))));
    }

    @Test
    @DisplayName("GET /avail/checkout/{username}/{date} - With invalid date format")
    void getInvalidDateFormatWhenGETByDateCheckOutTest() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/avail/checkout/tetema/2022-03-32")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(asJsonString(Map.of("message", "Incorrect date format"))));

    }

    @Test
    @DisplayName("GET avail/checkout/all - Get every checkout data in the database")
    void getAllCheckOutDataTest() throws Exception {
        CheckOutEntity checkOutEntity1 = new CheckOutEntity("tetema", LocalTime.parse("15:30"), LocalDate.parse("2022-03-13"));
        CheckOutEntity checkOutEntity2 = new CheckOutEntity("justin", LocalTime.parse("18:30"), LocalDate.parse("2022-03-13"));
        when(checkOutService.getAllCheckOut()).thenReturn(new ArrayList<>(Arrays.asList(checkOutEntity1, checkOutEntity2)));

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/avail/checkout/all")
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                        "[" +
                                "{\"username\":\"tetema\"," +
                                "\"date\":\"2022-03-13\"," +
                                "\"time\":\"15:30:00\"," +
                                "\"didUserCheckOutLate\":false," +
                                "\"userCheckedOut\":true}" +
                                "," +
                                "{\"username\":\"justin\"," +
                                "\"date\":\"2022-03-13\"," +
                                "\"time\":\"18:30:00\"," +
                                "\"didUserCheckOutLate\":true," +
                                "\"userCheckedOut\":true}"
                        +"]"
                ));
    }

    @Test
    @DisplayName("GET avail/checkout/all - Get every checkout data in the database")
    void getEmptyListAllCheckOutDataTest() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/avail/checkout/all")
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));
    }



    public static String asJsonString(final Map<String, String> obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
