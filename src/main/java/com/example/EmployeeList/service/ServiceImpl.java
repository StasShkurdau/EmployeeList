package com.example.EmployeeList.service;

import com.example.EmployeeList.DTO.AddEmployeeDTO;
import com.example.EmployeeList.DTO.GetEmployeeDTO;
import com.example.EmployeeList.model.Employee;
import com.example.EmployeeList.model.EmployeeCategory;
import com.example.EmployeeList.repository.EmployeeCategoryRepository;
import com.example.EmployeeList.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Objects.isNull;

@org.springframework.stereotype.Service
@Transactional
public class ServiceImpl implements Service {

    @Autowired
    private final EmployeeCategoryRepository employeeCategoryRepository;
    @Autowired
    private final EmployeeRepository employeeRepository;


    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public ServiceImpl(EmployeeCategoryRepository employeeCategoryRepository, EmployeeRepository employeeRepository) {
        this.employeeCategoryRepository = employeeCategoryRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void addEmployee(AddEmployeeDTO addEmployeeDTO) {
        Employee newEmployee = new Employee();
        newEmployee.setName(addEmployeeDTO.getName());
        EmployeeCategory category = addCategory(addEmployeeDTO.getCategoryName());
        newEmployee.setEmployeeCategory(category);
        employeeRepository.save(newEmployee);
        LOGGER.info("Added employee");

    }

    @Override
    public EmployeeCategory addCategory(String category) {
        EmployeeCategory employeeCategory = employeeCategoryRepository.getByName(category);
        if(isNull(employeeCategory)) {
            EmployeeCategory newCategory = new EmployeeCategory();
            newCategory.setName(category);
            employeeCategoryRepository.save(newCategory);
            LOGGER.info("Add category " + category);
            return newCategory;
        }
        return employeeCategory;
    }

    @Override
    public void deleteEmployee(String name) {
        employeeRepository.deleteByName(name);
        LOGGER.info("Deleted employee with name " + name);
    }

    @Override
    public void changeName(String oldName, String newName) {
        Employee employee = employeeRepository.findByName(oldName);
        employee.setName(newName);
        employeeRepository.save(employee);
        LOGGER.info("Changed name from" + oldName + "to" + newName);
    }

    @Override
    public GetEmployeeDTO getEmployee(String name) {
        GetEmployeeDTO getEmployeeDTO = new GetEmployeeDTO();
        Employee employee = employeeRepository.findByName(name);
        getEmployeeDTO.setName(employee.getName());
        getEmployeeDTO.setCategoryName(employeeRepository.findCategoryNameByName(name));
        return getEmployeeDTO;
    }

    @Cacheable("EmployeeList")
    public Page<Employee> getAllEmployee(Pageable pageable) {
        Page<Employee> page = employeeRepository.findAll(pageable);
        return page;
    }
}
