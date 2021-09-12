package com.example.EmployeeList.service;

import com.example.EmployeeList.model.Role;
import com.example.EmployeeList.model.User;
import com.example.EmployeeList.repository.RoleRepository;
import com.example.EmployeeList.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final RoleRepository roleRepository;

    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());



    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        User registeredUser = userRepository.save(user);

        LOGGER.info("In register method registered user : " + registeredUser);

        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        LOGGER.info("IN getAll found " + result.size() + " users");
        return result;
    }

    @Override
    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username);
        LOGGER.info("IN findByUsername - user: {} found by username: {}", result, username);
        return result;
    }
}
