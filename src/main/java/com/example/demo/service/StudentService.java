package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.response.StudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<StudentResponse> getStudentsByFirstname(String firstName) {
        List<Student> students = studentRepository.findByFirstName(firstName);

        return mapStudentDaoToDtoList(students);
    }

    public List<StudentResponse> getStudentsByFullName(String firstName, String lastName) {
        List<Student> students = studentRepository.findByFirstNameAndLastName(firstName, lastName);

        return mapStudentDaoToDtoList(students);
    }

    public List<StudentResponse> getStudentsByFirstOrLastName(String firstName, String lastName) {
        List<Student> students = studentRepository.findByFirstNameOrLastName(firstName, lastName);

        return mapStudentDaoToDtoList(students);
    }

    private List<StudentResponse> mapStudentDaoToDtoList(List<Student> students) {
        List<StudentResponse> studentResponses = new ArrayList<>();

        students.stream().forEach(std -> {
            studentResponses.add(new StudentResponse(std));
        });

        return studentResponses;
    }
}
