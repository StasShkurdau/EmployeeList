package com.example.EmployeeList.security;



import com.example.EmployeeList.model.User;
import com.example.EmployeeList.security.jwt.JwtUser;
import com.example.EmployeeList.security.jwt.JwtUserFactory;
import com.example.EmployeeList.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private  final UserService userService;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userService.findByUsername(name);
        if(isNull(user)){
            throw new UsernameNotFoundException("User: " + name + " not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(user);
        LOGGER.info("In loadUserByUsername loaded user with username: " + name);
        return jwtUser;
    }
}
