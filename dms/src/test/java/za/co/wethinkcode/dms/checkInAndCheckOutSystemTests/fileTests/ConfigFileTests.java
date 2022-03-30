package za.co.wethinkcode.dms.checkInAndCheckOutSystemTests.fileTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import za.co.wethinkcode.dms.checkInAndOutSystem.config.TwilioConfig;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
public class ConfigFileTests {
    @Autowired
    private TwilioConfig config;

    @Test
    void whenFactoryProvidedThenYamlPropertiesInjected() {
        assertThat(config.getPhoneNumber()).isNotNull();
        assertThat(config.getAccountSid()).isNotNull();
        assertThat(config.getAuthToken()).isNotNull();
    }
}
