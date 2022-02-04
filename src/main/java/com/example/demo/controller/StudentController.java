package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.response.StudentResponse;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController  // combination of response body and controller
@RequestMapping("/api/student") // mapping controller base uri
public class StudentController {


    // accessing properties of application property
    @Value("${app.name:Default value if property not exist}")
    private String appName;

    @Autowired
    private StudentService studentService;

    @GetMapping("/get")
    //@RequestMapping(value = "/get",method = RequestMethod.GET) getMapping similar to this line also
    public StudentResponse getStudent() {

        StudentResponse response = new StudentResponse(1, "fName", "lName");
        return response;
    }

    /**
     * return all the students db have
     * */
    @GetMapping("/getAll")
    public List<Student> getAll()
    {
        return studentService.getStudents();
    }
}
