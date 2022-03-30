package za.co.wethinkcode.dms.checkInAndOutSystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.wethinkcode.dms.checkInAndOutSystem.model.SmsRequest;

@Service
public class SendSmsService {

    private final CreateSms createSms;


    public SendSmsService(@Autowired CreateSms createSms) {
        this.createSms = createSms;
    }

    public void sendSms(SmsRequest smsRequest) {
        createSms.sendSms(smsRequest);
    }
}
