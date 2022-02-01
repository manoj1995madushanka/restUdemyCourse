package com.example.demo.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

/*
* return this as a json object from API
* JACKSON library is used to convert this to json object
 */
@Data
@AllArgsConstructor
public class StudentResponse {
    @JsonIgnore // this will exclude id from endpoint response
    private int id;
    private String firstName;
    private String lastName;

}
