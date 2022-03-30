package za.co.wethinkcode.dms.checkInAndOutSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.wethinkcode.dms.checkInAndOutSystem.entities.CheckInEntity;
import za.co.wethinkcode.dms.checkInAndOutSystem.entities.CheckOutEntity;
import za.co.wethinkcode.dms.checkInAndOutSystem.exceptions.CustomErrorResponseException;
import za.co.wethinkcode.dms.checkInAndOutSystem.model.SmsRequest;
import za.co.wethinkcode.dms.checkInAndOutSystem.services.CheckInService;
import za.co.wethinkcode.dms.checkInAndOutSystem.services.CheckOutService;
import za.co.wethinkcode.dms.checkInAndOutSystem.services.SendSmsService;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/avail/checks")
public class ChecksController {

    private final CheckInService checkInService;
    private final CheckOutService checkOutService;
    private final SendSmsService sendSmsService;

    public ChecksController(
            @Autowired CheckInService checkInService,
            @Autowired CheckOutService checkOutService,
            @Autowired SendSmsService sendSmsService) {
        this.checkInService = checkInService;
        this.checkOutService = checkOutService;
        this.sendSmsService = sendSmsService;
    }

    @GetMapping("/summary/monthly/{username}")
    ResponseEntity<?> getSummaryDataForTheMonth(@PathVariable String username,
                                                @RequestParam String startDate,
                                                @RequestParam String endDate) {

        List<CheckInEntity> checkIns = checkInService.getAllCheckInBetweenDates(username, actualDate(startDate), actualDate(endDate));
        List<CheckOutEntity> checkOuts = checkOutService.getAllCheckOutBetweenDates(username, actualDate(startDate), actualDate(endDate));

        return new ResponseEntity<>("stuff", HttpStatus.OK);
    }

    @PostMapping
    public void sendUserSms(@RequestBody SmsRequest smsRequest) {
        sendSmsService.sendSms(smsRequest);
    }


    private static LocalDate actualDate(String date) {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeException e) {
            throw new CustomErrorResponseException(
                    "Incorrect date format",
                    HttpStatus.BAD_REQUEST
            );
        }
    }

}
