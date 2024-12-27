package com.guru.depend.enums;

public enum Role {


    ADMIN("admin"),
    STUDENT("student"),
    TUTOR("tutor");


    private String role;


    Role(String role){
        this.role=role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
