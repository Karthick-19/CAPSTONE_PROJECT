package com.learn.afternoon;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/evening")
    public String evening(){
        return "Good Evening, from afternoon service.";
    }

    @GetMapping("/night")
    public String night(){
        return "Good Night, from afternoon service";
    }

}
