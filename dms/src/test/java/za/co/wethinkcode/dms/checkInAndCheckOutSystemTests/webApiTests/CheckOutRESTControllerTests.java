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
import za.co.wethinkcode.dms.checkInAndOutSystem.services.CheckInService;
import za.co.wethinkcode.dms.checkInAndOutSystem.services.CheckOutService;

import java.util.Map;

import static org.hamcrest.Matchers.containsString;
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
    void normalCheckOutPostWithCheckIn() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/avail/checkout")
                        .content(asJsonString(Map.of("username", "tetema", "date", "2022-03-13", "time", "14:30")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().json(asJsonString(Map.of("message", "Never checked in..."))))
                .andExpect(status().isBadRequest());
    }

    public static String asJsonString(final Map<String, String> obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
