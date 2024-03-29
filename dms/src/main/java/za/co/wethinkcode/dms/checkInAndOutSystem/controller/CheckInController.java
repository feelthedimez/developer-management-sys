package za.co.wethinkcode.dms.checkInAndOutSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.wethinkcode.dms.checkInAndOutSystem.entities.CheckInEntity;
import za.co.wethinkcode.dms.checkInAndOutSystem.exceptions.CustomErrorResponseException;
import za.co.wethinkcode.dms.checkInAndOutSystem.exceptions.ApiSuccessResponse;
import za.co.wethinkcode.dms.checkInAndOutSystem.model.CheckIn;
import za.co.wethinkcode.dms.checkInAndOutSystem.model.Checks;
import za.co.wethinkcode.dms.checkInAndOutSystem.services.CheckInService;
import za.co.wethinkcode.dms.checkInAndOutSystem.services.CheckOutService;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@RestController
@RequestMapping("/avail/checkin")
public class CheckInController {

    private final CheckInService checkInService;
    private final CheckOutService checkOutService;

    public CheckInController(@Autowired CheckInService checkInService,
                             @Autowired CheckOutService checkOutService) {
        this.checkInService = checkInService;
        this.checkOutService = checkOutService;
    }

    @PostMapping
    ResponseEntity<?> createCheckin(@RequestBody CheckIn checkIn) {

        LocalDate date = checkIn.getDate();
        LocalTime time = checkIn.getTime();
        String username = checkIn.getUsername();
        String phoneNumber = checkIn.getPhoneNumber();

        if(checkInService.doesDateAndUsernameExist(date, username))
            throw new CustomErrorResponseException("Already checked in", HttpStatus.BAD_REQUEST);

        checkInService.addACheckIn(username, time, date, phoneNumber);
        checkOutService.addCheckOut(username, date, phoneNumber);

        return new ResponseEntity<>(
                new ApiSuccessResponse(201, "Check In Successful"),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{username}/{date}")
    ResponseEntity<Checks> getCheckInByDate(@PathVariable String date, @PathVariable String username) {

        Optional<CheckInEntity> checkInEntityData = checkInService
                .getCheckInDataByDateAndUserName(actualDate(date), userName(username));

        if(checkInEntityData.isEmpty())
            throw new CustomErrorResponseException("Check in data not found", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(CheckIn.createCheckIn(checkInEntityData.get()), HttpStatus.OK);
    }

    @GetMapping("/all")
    ResponseEntity<List<CheckIn>> getEveryCheckInData(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String date) {

        if (!Objects.isNull(username)) return new ResponseEntity<>(
                modelToCheckIn(checkInService.getAllCheckInDataByUserName(username)),
                HttpStatus.OK);
        else if (!Objects.isNull(date)) return new ResponseEntity<>(
                modelToCheckIn(checkInService.getAllCheckInDataByDate(actualDate(date))),
                HttpStatus.OK);

        return new ResponseEntity<>(modelToCheckIn(checkInService.getAllCheckIn()), HttpStatus.OK);
    }

    private static String userName(String username) {
        try {
            return username;
        } catch (NullPointerException e) {
            throw new CustomErrorResponseException(
                    "Provide a username",
                    HttpStatus.BAD_REQUEST
            );
        }
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

    private static List<CheckIn> modelToCheckIn(List<CheckInEntity> checkInEntities) {
        List<CheckIn> finalCheckInData = new ArrayList<>();

        for (CheckInEntity checkInEntity : checkInEntities) {
            finalCheckInData.add(CheckIn.createCheckIn(checkInEntity));
        }
        return finalCheckInData;
    }

}
