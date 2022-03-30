package za.co.wethinkcode.dms.checkInAndOutSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.wethinkcode.dms.checkInAndOutSystem.entities.CheckOutEntity;
import za.co.wethinkcode.dms.checkInAndOutSystem.exceptions.ApiSuccessResponse;
import za.co.wethinkcode.dms.checkInAndOutSystem.exceptions.CustomErrorResponseException;
import za.co.wethinkcode.dms.checkInAndOutSystem.model.CheckOut;
import za.co.wethinkcode.dms.checkInAndOutSystem.model.Checks;
import za.co.wethinkcode.dms.checkInAndOutSystem.services.CheckInService;
import za.co.wethinkcode.dms.checkInAndOutSystem.services.CheckOutService;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/avail/")
public class CheckOutController {

    private final CheckOutService checkOutService;
    private final CheckInService checkInService;

    public CheckOutController(@Autowired CheckOutService checkOutService,
                              @Autowired CheckInService checkInService) {
        this.checkOutService = checkOutService;
        this.checkInService = checkInService;
    }

    @PutMapping("checkout")
    ResponseEntity<?> createCheckOut(@RequestBody CheckOut checkOut) {

        LocalDate date = checkOut.getDate();
        LocalTime time = checkOut.getTime();
        String username = checkOut.getUsername();
        String phoneNumber = checkOut.getPhoneNumber();

        if (!checkInService.doesDateAndUsernameExist(date, username))
            throw new CustomErrorResponseException("Never checked in...", HttpStatus.BAD_REQUEST);

        checkOutService.updateCheckOut(modifyId(username, date), username, phoneNumber, time, date);

        return new ResponseEntity<>(
                new ApiSuccessResponse(201, "Check Out Successful"),
                HttpStatus.CREATED
        );
    }

    @GetMapping("checkout/{username}/{date}")
    ResponseEntity<Checks> getCheckOutByDate(@PathVariable String date, @PathVariable String username) {

        Optional<CheckOutEntity> checkOutEntity = checkOutService
                .getCheckOutDataByDateAndUserName(actualDate(date), userName(username));

        if(checkOutEntity.isEmpty())
            throw new CustomErrorResponseException("Check out data not found", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(CheckOut.createCheckOut(checkOutEntity.get()), HttpStatus.OK);

    }

    @GetMapping("checkout/all")
    ResponseEntity<List<CheckOut>> getEveryCheckInData() {
        return new ResponseEntity<>(modelToCheckIn(checkOutService.getAllCheckOut()), HttpStatus.OK);
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

    private static List<CheckOut> modelToCheckIn(List<CheckOutEntity> checkoutEntities) {
        List<CheckOut> finalCheckInData = new ArrayList<>();

        for (CheckOutEntity checkOutEntity : checkoutEntities) {
            finalCheckInData.add(CheckOut.createCheckOut(checkOutEntity));
        }
        return finalCheckInData;
    }

    private static String modifyId(String username, LocalDate date) {
        return username + date.toString().replace("-", "");
    }

}
