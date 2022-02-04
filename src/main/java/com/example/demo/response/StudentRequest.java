package com.example.demo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequest {
    @NotBlank(message = "first name is required.")
    private String firstName;
    private String lastName;
}
