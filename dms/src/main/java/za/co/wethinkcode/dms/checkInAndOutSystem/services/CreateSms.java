package za.co.wethinkcode.dms.checkInAndOutSystem.services;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.wethinkcode.dms.checkInAndOutSystem.config.Sender;
import za.co.wethinkcode.dms.checkInAndOutSystem.config.TwilioKeys;
import za.co.wethinkcode.dms.checkInAndOutSystem.model.SmsRequest;

@Service
public class CreateSms implements Sender {

    private final TwilioKeys twilioKeys;

    public CreateSms(@Autowired TwilioKeys twilioKeys) {
        this.twilioKeys = twilioKeys;
    }

    @Override
    public void sendSms(SmsRequest smsRequest) {
        PhoneNumber toUser = new PhoneNumber(smsRequest.getPhoneNumber());
        PhoneNumber from = new PhoneNumber(twilioKeys.getPhoneNumber());
        MessageCreator messageCreator =  Message.creator(toUser, from, smsRequest.getMessage());
        messageCreator.create();
    }
}
