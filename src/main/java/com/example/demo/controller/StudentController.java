package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  // combination of response body and controller
@RequestMapping("/api/student/") // mapping controller base uri
public class StudentController {


    // accessing properties of application property
    @Value("${app.name}")
    private String appName;

    @GetMapping("/get")
    //@RequestMapping(value = "/get",method = RequestMethod.GET) getMapping similar to this line also
    public String getStudent()
    {
        return appName;
    }
}
