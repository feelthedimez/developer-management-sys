package za.co.wethinkcode.dms.absenteeismFeature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.co.wethinkcode.dms.absenteeismFeature.model.CheckIn;
import za.co.wethinkcode.dms.absenteeismFeature.repository.CheckInRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/avail")
public class CheckInController {

    @Autowired
    private CheckInRepository checkInRepository;

    @PostMapping("/checkin")
    CheckIn createCheckin(@RequestBody CheckIn checkIn) {
        return checkInRepository.save(checkIn);
    }


}
