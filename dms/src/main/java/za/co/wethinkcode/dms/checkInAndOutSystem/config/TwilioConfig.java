package za.co.wethinkcode.dms.checkInAndOutSystem.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("twilio")
@NoArgsConstructor @Getter @Setter
public class TwilioConfig {
    private String accountSid;
    private String authToken;
    private String phoneNumber;
}
