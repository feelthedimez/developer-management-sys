package za.co.wethinkcode.dms.checkInAndOutSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.wethinkcode.dms.checkInAndOutSystem.entities.CheckInEntity;
import za.co.wethinkcode.dms.checkInAndOutSystem.exceptions.CustomErrorResponseException;
import za.co.wethinkcode.dms.checkInAndOutSystem.exceptions.ApiSuccessResponse;
import za.co.wethinkcode.dms.checkInAndOutSystem.model.CheckIn;
import za.co.wethinkcode.dms.checkInAndOutSystem.services.CheckInService;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/avail")
public class CheckInController {

    private final CheckInService checkInService;

    public CheckInController(@Autowired CheckInService checkInService) {
        this.checkInService = checkInService;
    }

    @PostMapping("/checkin")
    ResponseEntity<?> createCheckin(@RequestBody CheckIn checkIn) {

        if(checkInService.doesDateAndUsernameExist(checkIn.getDate(), checkIn.getUsername()))
            throw new CustomErrorResponseException("Already checked in", HttpStatus.BAD_REQUEST);

        checkInService.addACheckIn(checkIn.getUsername(), checkIn.getTime(), checkIn.getDate());

        return new ResponseEntity<>(
                new ApiSuccessResponse(201, "Check In Successful"),
                HttpStatus.CREATED
        );
    }

    @GetMapping("checkin/{username}/{date}")
    ResponseEntity<?> getCheckInByDate(
            @PathVariable String date,
            @PathVariable String username
    ) {

        Optional<CheckInEntity> checkInEntityData = checkInService
                .getCheckInDataByDateAndUserName(actualDate(date), userName(username));

        if(checkInEntityData.isEmpty())
            throw new CustomErrorResponseException("Check in data not found", HttpStatus.NOT_FOUND);

        return  new ResponseEntity<>(CheckIn.createCheckIn(
                checkInEntityData.get().getUsername(),
                checkInEntityData.get().getTime(),
                checkInEntityData.get().getDate()
        ), HttpStatus.OK);

    }

    @GetMapping("checkin/{username}")
    ResponseEntity<List<CheckInEntity>> getAllCheckInsByUsername(@PathVariable String username) {
        return new ResponseEntity<>(checkInService.getAllCheckInDataByUserName(username), HttpStatus.OK);
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

}
