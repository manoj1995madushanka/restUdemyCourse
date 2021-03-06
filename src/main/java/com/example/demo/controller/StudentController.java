package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.response.InQueryRequest;
import com.example.demo.response.StudentRequest;
import com.example.demo.response.StudentResponse;
import com.example.demo.response.UpdateStudentRequest;
import com.example.demo.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController  // combination of response body and controller
@RequestMapping("/api/student") // mapping controller base uri
public class StudentController {


    // accessing properties of application property
    @Value("${app.name:Default value if property not exist}")
    private String appName;

    @Autowired
    private StudentService studentService;

    /*
    * configure spring logs
    * there are 5 log levels
    * ERROR < WARN < INFO < DEBUG < TRACE
    * */
    Logger logger = LoggerFactory.getLogger(getClass());



    @GetMapping("/get")
    //@RequestMapping(value = "/get",method = RequestMethod.GET) getMapping similar to this line also
    public StudentResponse getStudent() {

        StudentResponse response = new StudentResponse(1, "fName", "lName","street","city");
        return response;
    }

    /**
     * return all the students db have
     */
    @GetMapping("/getAll")
    public List<StudentResponse> getAll() {

        // logs with different log levels
        logger.error("Inside the error");
        logger.warn("Inside the warn");
        logger.info("Inside the info");
        // below logs are not showing in cmd because spring
        // configured default log level as Info
        logger.debug("Inside the debug");
        logger.trace("Inside the trace");

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
     */
    @DeleteMapping("/delete")
    public String deleteStudent(@RequestParam("id") Integer id) {
        studentService.deleteStudent(id);
        return "Student deleted.";
    }

    /**
     * delete student using path variable
     * /delete/1
     */
    @DeleteMapping("/delete/{id}")
    public String deleteStudentByPathVariable(@PathVariable("id") Integer id) {
        studentService.deleteStudent(id);
        return "Student deleted.";
    }

    /**
     * find students by first name path variable
     */
    // comment this out because controller layer can not have same parameter list with two method
    /*@GetMapping("/getStudentsByFirstname/{firstname}")
    public List<StudentResponse> getStudentsByFirstName(@PathVariable("firstName") String firstName) {
        return studentService.getStudentsByFirstname(firstName);
    }*/

    /**
     * find students by first name and last name path variable
     */
    @GetMapping("/getStudentsByFirstname/{firstname}/{lastName}")
    public List<StudentResponse> getStudentsByFullName(@PathVariable("firstName") String firstName,
                                                       @PathVariable("lastName") String lastName) {

        return studentService.getStudentsByFullName(firstName, lastName);
    }

    /**
     * find students by first name or last name path variable
     */
    @GetMapping("/getStudentsByFirstname/{firstname}/{lastName}")
    public List<StudentResponse> getStudentsByFirstOrLastName(@PathVariable("firstName") String firstName,
                                                       @PathVariable("lastName") String lastName) {

        return studentService.getStudentsByFirstOrLastName(firstName, lastName);
    }

    /**
     * find students by first name in
     */
    @GetMapping("/getStudentsByFirstnameIn")
    public List<StudentResponse> getStudentsByFirstNameIn(@RequestBody InQueryRequest inQueryRequest) {

        // payload example

        /*{
            "firstNames":["abc","def"]
        }*/

        return studentService.getStudentsByFirstIn(inQueryRequest.getFirstNames());
    }


    /*
    * pagination example
    * */
    @GetMapping("/getAllWithPagination")
    public List<StudentResponse> getAllWithPagination(@RequestParam int pageNo, @RequestParam int pageSize)
    {
        return studentService.getAllWithPagination(pageNo,pageSize);
    }

    /*
     * sorting example
     * */
    @GetMapping("/getAllWithSorting")
    public List<StudentResponse> getAllWithSorting()
    {
        return studentService.getAllWithSorting();
    }

    /*
     * like query example
     * */
    @GetMapping("/likeFirstName/{firstName}")
    public List<StudentResponse> getAllFirstNameLike(String firstName)
    {
        return studentService.getStudentsByFirstNameLike(firstName);
    }
}
