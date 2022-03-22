package za.co.wethinkcode.dms.checkInAndOutSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.wethinkcode.dms.checkInAndOutSystem.entities.CheckOutEntity;
import za.co.wethinkcode.dms.checkInAndOutSystem.exceptions.ApiSuccessResponse;
import za.co.wethinkcode.dms.checkInAndOutSystem.exceptions.CustomErrorResponseException;
import za.co.wethinkcode.dms.checkInAndOutSystem.model.CheckOut;
import za.co.wethinkcode.dms.checkInAndOutSystem.services.CheckInService;
import za.co.wethinkcode.dms.checkInAndOutSystem.services.CheckOutService;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
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

    @PostMapping("checkout")
    ResponseEntity<?> createCheckOut(@RequestBody CheckOut checkOut) {

        LocalDate date = checkOut.getDate();
        LocalTime time = checkOut.getTime();
        String username = checkOut.getUsername();

        if (!checkInService.doesDateAndUsernameExist(date, username)) {
            throw new CustomErrorResponseException(
                    "Never checked in...",
                    HttpStatus.BAD_REQUEST
            );
        } else if (checkOutService.doesDateAndUsernameExist(date, username)) {
            throw new CustomErrorResponseException(
                    "Already checked out",
                    HttpStatus.BAD_REQUEST
            );
        }

        checkOutService.addCheckOut(username, time, date);

        return new ResponseEntity<>(
                new ApiSuccessResponse(201, "Check Out Successful"),
                HttpStatus.CREATED
        );
    }

    @GetMapping("checkout/{username}/{date}")
    ResponseEntity<CheckOutEntity> getCheckOutByDate(
                @PathVariable String date,
                @PathVariable String username
        ) {

            String finalUsername;
            LocalDate actualDate;
            try {
                finalUsername = username;
                actualDate = LocalDate.parse(date);
            } catch (NullPointerException e) {
                throw new CustomErrorResponseException(
                        "Provide a username",
                        HttpStatus.BAD_REQUEST
                );
            } catch (DateTimeException e) {
                throw new CustomErrorResponseException(
                        "Incorrect date format",
                        HttpStatus.BAD_REQUEST
                );
            }

        Optional<CheckOutEntity> checkOutEntity = checkOutService
                .getCheckOutDataByDateAndUserName(actualDate, finalUsername);

        if(checkOutEntity.isEmpty()) {
            throw new CustomErrorResponseException(
                    "Check out data not found",
                    HttpStatus.NOT_FOUND
            );
        }

        return  new ResponseEntity<>(checkOutEntity.get(), HttpStatus.OK);

    }

}
