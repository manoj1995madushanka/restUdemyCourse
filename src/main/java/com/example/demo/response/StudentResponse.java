package com.example.demo.response;

import com.example.demo.entity.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/*
* return this as a json object from API
* JACKSON library is used to convert this to json object
 */
@Data // getters, setters, equals, hashcode, toString
@AllArgsConstructor
public class StudentResponse {
    @JsonIgnore // this will exclude id from endpoint response
    private int id;
    @JsonProperty("first_name") // endpoint response return first_name as key of json
    private String firstName;
    private String lastName;

    public StudentResponse(Student student)
    {
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.id = student.getId();
    }

}
