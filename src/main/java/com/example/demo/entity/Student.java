package com.example.demo.entity;

import com.example.demo.response.StudentRequest;
import com.example.demo.response.UpdateStudentRequest;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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

    // one to one relationship
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    // one to many relationship
    @OneToMany(mappedBy = "student")
    private List<Subject> subjects;

    // using this annotation we can add parameter that not in db table,
    // If not added this annotation run time exception accrues
    @Transient
    private String fullName;

    public Student(StudentRequest studentRequest) {
        this.firstName = studentRequest.getFirstName();
        this.lastName = studentRequest.getLastName();
    }

    public Student(UpdateStudentRequest updateStudentRequest) {
        this.id = updateStudentRequest.getId();
        this.firstName = updateStudentRequest.getFirstName();
        this.lastName = updateStudentRequest.getLastName();
    }
}
