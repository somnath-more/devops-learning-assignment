package com.example.dockerdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/hello-world")
@RestController
public class DockerDemoController {

    @GetMapping
    public String HelloWorld(){
        return "Hello World !! I am creating docker file for this app";
    }
}
