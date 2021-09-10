package com.example.EmployeeList.repository;

import com.example.EmployeeList.model.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {

    Employee findByName(String oldName);

    void deleteByName(String name);

    String findCategoryNameByName(String name);
}
