package com.example.EmployeeList.service;

import com.example.EmployeeList.DTO.AddEmployeeDTO;
import com.example.EmployeeList.DTO.GetEmployeeDTO;
import com.example.EmployeeList.model.Employee;
import com.example.EmployeeList.model.EmployeeCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface Service {

    void addEmployee(AddEmployeeDTO addEmployeeDTO);

    EmployeeCategory addCategory(String category);

    void deleteEmployee(String name);

    void changeName(String oldName, String newName);

    GetEmployeeDTO getEmployee(String name);

    Page<Employee> getAllEmployee(Pageable pageable);
}
