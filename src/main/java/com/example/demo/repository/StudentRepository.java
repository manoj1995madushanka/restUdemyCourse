package com.example.demo.repository;

import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    // JpaRepository is a combination of CrudRepository<T,ID> and PagingAndSortingRepository<T,ID>

    List<Student> findByFirstName(String firstName);

    List<Student> findByFirstNameAndLastName(String firstName, String lastname);

    List<Student> findByFirstNameOrLastName(String firstName, String lastname);

    // select * from student where firstName in ('abs','def')
    List<Student> findByFirstNameIn(List<String> firstNames);

    // select * from student where first_name like '%abc%'
    List<Student> findByFirstNameContains(String firstName);

    // start with query
    List<Student> findByFirstNameStartsWith(String firstName);

    // ends with query
    List<Student> findByFirstNameEndsWith (String firstName);

}
