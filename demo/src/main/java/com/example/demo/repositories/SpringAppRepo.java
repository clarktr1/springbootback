package com.example.demo.repositories;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SpringAppRepo {

    @GetMapping(path = "/hello")
    public String helloWorld(){
        return "Hello World";
    }

}
