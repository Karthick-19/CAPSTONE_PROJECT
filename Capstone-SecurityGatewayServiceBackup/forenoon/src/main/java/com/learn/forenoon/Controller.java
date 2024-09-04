package com.learn.forenoon;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Controller {

    @GetMapping("/morning")
    public String morning(){
        return "Good Morning, from forenoon service.";
    }

    @GetMapping("/afternoon")
    public String afternoon(){
        return "Good Afternoon, from forenoon service.";
    }


}
