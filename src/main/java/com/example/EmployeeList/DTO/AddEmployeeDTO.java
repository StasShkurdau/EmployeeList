package com.example.EmployeeList.DTO;

public class AddEmployeeDTO {

    private String name;

    private  String category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryName() {
        return category;
    }

    public void setCategoryName(String categoryName) {
        this.category = categoryName;
    }
}
