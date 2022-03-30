package za.co.wethinkcode.dms.checkInAndOutSystem.config;

import za.co.wethinkcode.dms.checkInAndOutSystem.model.SmsRequest;

public interface Sender {
    void sendSms(SmsRequest smsRequest);
}
