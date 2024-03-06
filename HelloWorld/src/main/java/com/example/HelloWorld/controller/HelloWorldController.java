package com.example.HelloWorld.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gradle")
public class HelloWorldController {

    @GetMapping
    public String helloWorld() {
        return "Hello World! Using Gradle";
    }
}
