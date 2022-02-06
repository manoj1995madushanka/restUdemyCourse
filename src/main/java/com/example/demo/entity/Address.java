package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "student")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "street")
    private String street;
    @Column(name = "city")
    private String city;

    @OneToOne(mappedBy = "address")
    private Student student;

    public Address(String city, String street) {
        this.city = city;
        this.street = street;
    }
}
