package com.example.EmployeeList.security;


import com.google.common.collect.Sets;

import java.util.Set;

import static com.example.EmployeeList.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    USER(Sets.newHashSet(EMPLOYEE_READ, CATEGORY_READ)),
    ADMIN(Sets.newHashSet(EMPLOYEE_READ, EMPLOYEE_WRITE, CATEGORY_READ, CATEGORY_WRITE));

    Set<ApplicationUserPermission> permission;

    ApplicationUserRole(Set<ApplicationUserPermission> permission) {
        this.permission = permission;
    }
}
