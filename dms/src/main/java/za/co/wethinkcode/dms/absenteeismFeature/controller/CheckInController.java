package za.co.wethinkcode.dms.absenteeismFeature.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.wethinkcode.dms.absenteeismFeature.model.CheckIn;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/avail")
public class CheckInController {

    @GetMapping("/all/checkin/{username}")
    public List<CheckIn> getAllCheckIns(@PathVariable String username) {
        return Arrays.asList(CheckIn.createCheckIn(username, LocalTime.parse("10:20"), LocalDate.now()));
    }
}
