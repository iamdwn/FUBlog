package tech.fublog.FuBlog.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")

public class GreetController {

    @GetMapping("/greet")
    public String greetMessage(){
        return "Good day, did well";
    }

}
