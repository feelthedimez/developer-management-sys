package za.co.wethinkcode.dms.absenteeismFeature.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/avail")
public class AbsenteeismController {

    @GetMapping("/{email}/checkin/{date}")
    public String getCheckInByDate(
            @PathVariable("email") String email,
            @PathVariable("date") String date
            ) {
        return "check_in data by date";
    }

    @GetMapping("/{email}/checkout/{date}")
    public String getCheckOutByDate(
            @PathVariable("email") String email,
            @PathVariable("date") String date
            ) {
        return "check_out data by date";
    }

    @GetMapping("/{email}/day/{date}")
    public String getCheckInAndOutDayByDate(
            @PathVariable("email") String email,
            @PathVariable("date") String date
    ) {
        return "check_in, check_out, hours, and late data by date";
    }

    @GetMapping("/{email}/{date}/weekly/{weekdayNum}")
    public List<String> getCheckInAndCheckoutWeeklyByDate(
            @PathVariable("email") String email,
            @PathVariable("date") String date,
            @PathVariable("weekdayNum") int weekdayNum
    ) {
        List<String> testingEnd = new ArrayList<>();
        testingEnd.add("check_in, check_out, hours, and late data by date - but in a list");
        return testingEnd;
    }

    @GetMapping("/{email}/monthly/{date}")
    public List<String> getCheckInAndCheckOutMonthlyByDate(
            @PathVariable("email") String email,
            @PathVariable("date") String date
    ) {
        List<String> testingEnd = new ArrayList<>();
        testingEnd.add("check_in, check_out, hours, and late data by date - but in a list");

        return testingEnd;
    }
}
