package com.example.EmployeeList.repository;

import com.example.EmployeeList.model.EmployeeCategory;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeCategoryRepository extends PagingAndSortingRepository<EmployeeCategory, Long> {

    EmployeeCategory getByName(String category);

}
