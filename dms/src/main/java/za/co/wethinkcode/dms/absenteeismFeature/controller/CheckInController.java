package za.co.wethinkcode.dms.absenteeismFeature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.co.wethinkcode.dms.absenteeismFeature.entities.CheckInEntity;
import za.co.wethinkcode.dms.absenteeismFeature.model.CheckIn;
import za.co.wethinkcode.dms.absenteeismFeature.repository.CheckInRepository;
import za.co.wethinkcode.dms.absenteeismFeature.services.CheckInService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/avail")
public class CheckInController {

    private final CheckInService checkInService;

    public CheckInController(@Autowired CheckInService checkInService) {
        this.checkInService = checkInService;
    }

    @PostMapping("/checkin")
    void createCheckin(@RequestBody CheckIn checkIn) {
        checkInService.addACheckIn(checkIn.getUsername(), checkIn.getTime(), checkIn.getDate());
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
