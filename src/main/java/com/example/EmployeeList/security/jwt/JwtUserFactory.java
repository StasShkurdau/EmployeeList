package com.example.EmployeeList.security.jwt;

import com.example.EmployeeList.model.Role;
import com.example.EmployeeList.model.Status;
import com.example.EmployeeList.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

     public static JwtUser create(User user){
         return new JwtUser(
                 user.getId(),
                 user.getUsername(),
                 user.getPassword(),
                 mapToGrantedAuthority(new ArrayList<>(user.getRoles())),
                 user.getStatus().equals(Status.ACTIVE)
         );
     }

     private static List<GrantedAuthority> mapToGrantedAuthority(List<Role> userRoles){
         return userRoles.stream()
                    .map(role -> new SimpleGrantedAuthority(role.getName()))
                    .collect(Collectors.toList());
     }
}
