package za.co.wethinkcode.dms.absenteeismFeature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.wethinkcode.dms.absenteeismFeature.entities.CheckInEntity;
import za.co.wethinkcode.dms.absenteeismFeature.exceptionResponses.ApiErrorResponse;
import za.co.wethinkcode.dms.absenteeismFeature.exceptionResponses.ApiSuccessResponse;
import za.co.wethinkcode.dms.absenteeismFeature.model.CheckIn;
import za.co.wethinkcode.dms.absenteeismFeature.services.CheckInService;
import java.time.LocalDate;
import java.util.List;

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
            return new ResponseEntity<ApiSuccessResponse>(
                    new ApiSuccessResponse(201, "Check In Successful"), HttpStatus.CREATED
            );
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<ApiErrorResponse>(
                    new ApiErrorResponse(500, "Failed to Check in"), HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("checkin/{date}")
    CheckInEntity getCheckInByDate(@PathVariable String date) {
        LocalDate actualDate = LocalDate.parse(date);
        return checkInService.getCheckInDataByDate(actualDate).get();
    }

    @GetMapping("checkin/{username}")
    List<CheckInEntity> getAllCheckInsByUsername(@PathVariable String username) {
        return checkInService.getAllCheckInDataByUserName(username);
    }

}
