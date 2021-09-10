package com.example.EmployeeList.controllers;

import com.example.EmployeeList.DTO.AddEmployeeDTO;
import com.example.EmployeeList.DTO.GetEmployeeDTO;
import com.example.EmployeeList.model.Employee;
import com.example.EmployeeList.service.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/api/v1/employee")
public class MainController {

    private final Service service;

    public MainController(Service service) {
        this.service = service;
    }

    @PostMapping(path = "/addEmployee")
    public ResponseEntity<Object> addEmployee(@RequestBody AddEmployeeDTO employeeDTO) {
        if (isNull(employeeDTO)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        service.addEmployee(employeeDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(path = "/addCategory")
    public ResponseEntity<Object> addCategory(@RequestParam String newCategory){
        if(isNull(newCategory)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        service.addCategory(newCategory);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(path = "/changeName")
    public ResponseEntity<Object> changeName(@RequestParam String oldName,@RequestParam String newName){
        if(isNull(oldName)||isNull(newName)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        service.changeName(oldName, newName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/deleteEmployee")
    public ResponseEntity<Object> deletePerson(@RequestParam String name){
        if(isNull(name)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        service.deleteEmployee(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/getEmployee")
    public ResponseEntity<Object> getEmployee(@RequestParam String name){
        if(isNull(name)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        GetEmployeeDTO employeeDTO = service.getEmployee(name);
        return new ResponseEntity<>(employeeDTO , HttpStatus.OK);
    }

    @GetMapping(path = "/getAll")
    public ResponseEntity<Object> getAll(@PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable){
        Page<Employee> page = service.getAllEmployee(pageable);
        return new ResponseEntity<>(page , HttpStatus.OK);
    }

}
