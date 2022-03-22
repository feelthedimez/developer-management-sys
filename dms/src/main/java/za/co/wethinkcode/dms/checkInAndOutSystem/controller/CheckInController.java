package za.co.wethinkcode.dms.checkInAndOutSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.wethinkcode.dms.checkInAndOutSystem.entities.CheckInEntity;
import za.co.wethinkcode.dms.checkInAndOutSystem.exceptions.CustomErrorResponse;
import za.co.wethinkcode.dms.checkInAndOutSystem.exceptions.CustomErrorWithDataException;
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

        try {
            checkInService.addACheckIn(checkIn.getUsername(), checkIn.getTime(), checkIn.getDate());
            return new ResponseEntity<>(
                    new ApiSuccessResponse(201, "Check In Successful"), HttpStatus.CREATED
            );
        } catch (NullPointerException e) {
            throw new CustomErrorWithDataException(e.getMessage());
        }
    }

    @GetMapping("checkin/{username}/{date}")
    ResponseEntity<CheckInEntity> getCheckInByDate(
            @PathVariable String date,
            @PathVariable String username
    ) {

        LocalDate actualDate;
        try {
            actualDate = LocalDate.parse(date);
        } catch (DateTimeException e) {
            throw new CustomErrorResponse(
                    "Incorrect date format",
                    HttpStatus.BAD_REQUEST
            );
        }

        String finalUsername;
        try {
            finalUsername = username;
        } catch (NullPointerException e) {
            throw new CustomErrorResponse(
                    "Provide a username",
                    HttpStatus.BAD_REQUEST
            );
        }

        Optional<CheckInEntity> checkInEntityData = checkInService
                .getCheckInDataByDateAndUserName(actualDate, finalUsername);

        if(checkInEntityData.isEmpty()) {
            throw new CustomErrorResponse(
                    "Check in data not found",
                    HttpStatus.NOT_FOUND
            );
        }

        return new ResponseEntity<>(checkInEntityData.get(), HttpStatus.OK);


    }

    @GetMapping("checkin/{username}")
    List<CheckInEntity> getAllCheckInsByUsername(@PathVariable String username) {
        return checkInService.getAllCheckInDataByUserName(username);
    }

}
