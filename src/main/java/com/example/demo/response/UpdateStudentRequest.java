package com.example.demo.response;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateStudentRequest {
    @NotNull // because @notBland not applicable for numeric fields
    private int id;
    private String firstName;
    private String lastName;
}
