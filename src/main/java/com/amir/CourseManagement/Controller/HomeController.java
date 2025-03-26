package com.amir.CourseManagement.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/")
public class HomeController {
    @GetMapping("/welcome")
    public String sayHello() {
        return "Hello World";
    }
}
