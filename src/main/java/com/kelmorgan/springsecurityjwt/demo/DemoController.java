package com.kelmorgan.springsecurityjwt.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {


    @GetMapping("/test")
    public String sayHello (){
        return "Hello from secure endpoint";
    }
}
