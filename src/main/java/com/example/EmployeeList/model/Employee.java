package com.example.EmployeeList.model;

import org.springframework.data.annotation.Id;

@Entity
public class Employee {

    @Id
    private Long Id;

    private String name;


    private Long categoryId;
}
