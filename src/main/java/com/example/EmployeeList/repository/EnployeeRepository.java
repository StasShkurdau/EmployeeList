package com.example.EmployeeList.repository;

import com.example.EmployeeList.model.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EnployeeRepository extends PagingAndSortingRepository<Employee, Long> {
}
