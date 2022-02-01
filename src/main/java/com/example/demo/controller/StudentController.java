package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  // combination of response body and controller
@RequestMapping("/api/student/") // mapping controller base uri
public class StudentController {


    @GetMapping("/get")
    //@RequestMapping(value = "/get",method = RequestMethod.GET) getMapping similar to this line also
    public String getStudent()
    {
        return "student";
    }
}
