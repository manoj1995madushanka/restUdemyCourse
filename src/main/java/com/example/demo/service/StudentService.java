package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.response.StudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public StudentResponse createStudent(Student student) {
        return new StudentResponse(studentRepository.save(student));
    }

    public StudentResponse updateStudent(Student student) {
        //ToDo
        // check student exist in db first
        return new StudentResponse(studentRepository.save(student));
    }

    public void deleteStudent(Integer id) {
        //ToDo
        // check student availability before delete
        studentRepository.deleteById(id);
    }
}
