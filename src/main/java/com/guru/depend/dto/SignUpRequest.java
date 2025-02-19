package com.guru.depend.dto;

import com.guru.depend.enums.Role;

public class SignUpRequest {

    private String email;
    private String password;

    public SignUpRequest(String email, String password, Role student) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
