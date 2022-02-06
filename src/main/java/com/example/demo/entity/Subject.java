package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "subject_name")
    private String subjectName;
    @Column(name = "marks_obtain")
    private Double marksObtain;

    @ManyToOne
    @JoinColumn(name = "id")
    private Student student;

}
