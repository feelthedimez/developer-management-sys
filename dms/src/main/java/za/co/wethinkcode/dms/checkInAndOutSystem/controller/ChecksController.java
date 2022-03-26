package za.co.wethinkcode.dms.checkInAndOutSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import za.co.wethinkcode.dms.checkInAndOutSystem.entities.CheckInEntity;
import za.co.wethinkcode.dms.checkInAndOutSystem.entities.CheckOutEntity;
import za.co.wethinkcode.dms.checkInAndOutSystem.exceptions.CustomErrorResponseException;
import za.co.wethinkcode.dms.checkInAndOutSystem.model.CheckIn;
import za.co.wethinkcode.dms.checkInAndOutSystem.services.CheckInService;
import za.co.wethinkcode.dms.checkInAndOutSystem.services.CheckOutService;

import java.text.DecimalFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/avail/checks")
public class ChecksController {

    private final CheckInService checkInService;
    private final CheckOutService checkOutService;

    public ChecksController(
            @Autowired CheckInService checkInService,
            @Autowired CheckOutService checkOutService) {
        this.checkInService = checkInService;
        this.checkOutService = checkOutService;
    }

    @GetMapping("/summary/monthly/{username}")
    ResponseEntity<?> getSummaryDataForTheMonth(@PathVariable String username,
                                                @RequestParam String startDate,
                                                @RequestParam String endDate) {

        List<CheckInEntity> checkIns = checkInService.getAllCheckInBetweenDates(username, actualDate(startDate), actualDate(endDate));
        List<CheckOutEntity> checkOuts = checkOutService.getAllCheckOutBetweenDates(username, actualDate(startDate), actualDate(endDate));

        return new ResponseEntity<>("stuff", HttpStatus.OK);
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
