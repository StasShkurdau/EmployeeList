package com.example.EmployeeList.service;

import com.example.EmployeeList.model.User;

import java.util.List;

public interface UserService {

    User register(User user);

    List<User> getAll();

    User findByUsername(String username);

}
