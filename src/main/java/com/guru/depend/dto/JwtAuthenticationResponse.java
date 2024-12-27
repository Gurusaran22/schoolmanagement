package com.guru.depend.dto;

public class JwtAuthenticationResponse {

    private  String status;
    private String token;
    private String refreshToken;

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getRefreshToken() {
        return refreshToken;
    }
    public void setRefreshToken(String refreshToken) {this.refreshToken = refreshToken;}
    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}
}