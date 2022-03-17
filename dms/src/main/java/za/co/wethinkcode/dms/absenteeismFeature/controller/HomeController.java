package za.co.wethinkcode.dms.absenteeismFeature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.wethinkcode.dms.absenteeismFeature.services.GreetingService;

@Controller
@RestController
public class HomeController {

    private final GreetingService greetingService;

    public HomeController(@Autowired GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/greeting")
    public String greeting() {
        return greetingService.greet();
    }
}
