package com.example.EmployeeList.repository;

import com.example.EmployeeList.model.EmployeeCategory;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EnployeeCategoryRepository extends PagingAndSortingRepository<EmployeeCategory, Long> {
}
