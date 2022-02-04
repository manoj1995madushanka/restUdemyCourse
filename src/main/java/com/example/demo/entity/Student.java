package com.example.demo.entity;

import com.example.demo.response.StudentRequest;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    public Student(StudentRequest studentRequest) {
        this.firstName = studentRequest.getFirstName();
        this.lastName = studentRequest.getLastName();
    }
}
