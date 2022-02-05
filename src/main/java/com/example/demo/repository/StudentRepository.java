package com.example.demo.repository;

import com.example.demo.entity.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
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


    // query data using jpql
    @Query("from student where firstName = :firstName and lastName =:lastName")
    List<Student> getResultsByFullName(@Param("firstName") String firstName, @Param("lastName") String lastname);

    @Modifying
    @Transactional
    @Query("update student set firstname =?1 where id =?2")
    Integer updateFirstName(@Param("firstName") String firstName,Integer id);
    // above return type Integer will return number of rows affected by query
    // return type can be void also for above query

    @Modifying
    @Transactional
    @Query("delete from student where firstName =:firstName")
    Integer deleteByFirstName(@Param("firstName") String firstName);
}
