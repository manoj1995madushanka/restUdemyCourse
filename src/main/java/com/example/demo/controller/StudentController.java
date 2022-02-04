package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.response.StudentRequest;
import com.example.demo.response.StudentResponse;
import com.example.demo.response.UpdateStudentRequest;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
     */
    @GetMapping("/getAll")
    public List<StudentResponse> getAll() {
        List<Student> students = studentService.getStudents();
        List<StudentResponse> studentResponses = new ArrayList<>();

        students.stream().forEach(std -> {
            studentResponses.add(new StudentResponse(std));
        });

        return studentResponses;
    }

    /**
     * endpoint for create student
     */
    @PostMapping("/create")
    public StudentResponse createStudent(@Valid @RequestBody StudentRequest studentRequest) {
        // first convert json object to java object using @RequestBody annotation
        // after check validations those are available in java class from @Valid annotation

        Student student = new Student(studentRequest);
        return studentService.createStudent(student);
    }

    /**
     * update endpoint
     */
    @PutMapping("/update")
    public StudentResponse updateStudent(@Valid @RequestBody UpdateStudentRequest updateStudentRequest) {
        Student student = new Student(updateStudentRequest);
        return studentService.updateStudent(student);
    }

    /**
     * delete student using query param
     * /delete?id=1
     * */
    @DeleteMapping("/delete")
    public String deleteStudent(@RequestParam("id") Integer id)
    {
        studentService.deleteStudent(id);
        return "Student deleted.";
    }

    /**
     * delete student using path variable
     * /delete/1
     * */
    @DeleteMapping("/delete/{id}")
    public String deleteStudentByPathVariable(@PathVariable("id") Integer id)
    {
        studentService.deleteStudent(id);
        return "Student deleted.";
    }
}
