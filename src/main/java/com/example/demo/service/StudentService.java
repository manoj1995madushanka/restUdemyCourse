package com.example.demo.service;

import com.example.demo.entity.Address;
import com.example.demo.entity.Student;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.response.StudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private AddressRepository addressRepository;

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public StudentResponse createStudent(Student student) {

        /*
        when we have parent child relationship we save parent object first
         and get primary key to assign child object
        */
        Address address = new Address(student.getAddress().getCity(),student.getAddress().getStreet());
        student.setAddress(addressRepository.save(address));

        return new StudentResponse(studentRepository.save(student));

        // Todo
        // before return statement check subject available and map them to subject dao
        //and save subject list
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

    public List<StudentResponse> getStudentsByFirstIn(List<String> firstNames) {
        List<Student> students = studentRepository.findByFirstNameIn(firstNames);

        return mapStudentDaoToDtoList(students);
    }

    public List<StudentResponse> getAllWithPagination(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize); //  this page number is zero based
        List<Student> students = studentRepository.findAll(pageable).getContent();
        return mapStudentDaoToDtoList(students);
    }

    public List<StudentResponse> getAllWithSorting() {

        Sort sort = Sort.by(Sort.Direction.ASC,"firstName","lastName");
        List<Student> students = studentRepository.findAll(sort);
        return mapStudentDaoToDtoList(students);
    }

    public List<StudentResponse> getStudentsByFirstNameLike(String firstName) {
        List<Student> students = studentRepository.findByFirstNameContains(firstName);

        return mapStudentDaoToDtoList(students);
    }
}
