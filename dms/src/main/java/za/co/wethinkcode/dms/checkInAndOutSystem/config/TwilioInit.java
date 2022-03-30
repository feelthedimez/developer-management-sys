package za.co.wethinkcode.dms.checkInAndOutSystem.config;

import com.twilio.Twilio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioInit {

    public TwilioInit(@Autowired TwilioKeys twilioKeys) {
        Twilio.init(twilioKeys.getAccountSid(), twilioKeys.getAuthToken());
    }

}
