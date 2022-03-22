package za.co.wethinkcode.dms.checkInAndOutSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.wethinkcode.dms.checkInAndOutSystem.services.CheckInService;
import za.co.wethinkcode.dms.checkInAndOutSystem.services.CheckOutService;

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


}
