package com.example.devicemanage.common;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtResponse {
    private String token;
    private String role;


    public JwtResponse(String token, String roles) {
        this.token = token;
        this.role = roles;
    }
}
