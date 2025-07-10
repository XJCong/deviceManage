package com.example.devicemanage.entity;

import lombok.Getter;
import lombok.Setter;

// 建议移出 controller，放入 dto 包中
@Setter
@Getter
public class LoginRequest {
    private String username;
    private String password;
}
