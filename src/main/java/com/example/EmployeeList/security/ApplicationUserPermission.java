package com.example.EmployeeList.security;

public enum ApplicationUserPermission {
    EMPLOYEE_WRITE("employee:write"),
    EMPLOYEE_READ("employee:read"),
    CATEGORY_WRITE("category:write"),
    CATEGORY_READ("category:read");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }
}
