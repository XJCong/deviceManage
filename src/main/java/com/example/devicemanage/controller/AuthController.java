package com.example.devicemanage.controller;

import com.example.devicemanage.common.Result;
import com.example.devicemanage.common.JwtResponse;
import com.example.devicemanage.entity.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import com.example.devicemanage.utils.JwtUtils;

import java.util.Collection;

@RestController
@RequestMapping("/open")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    // 登录接口
    @PostMapping("/login")
    public Result<JwtResponse> createAuthenticationToken(@RequestBody LoginRequest loginRequest) {
    if (loginRequest.getUsername() == null || loginRequest.getPassword() == null) {
        return Result.fail("用户名或密码不能为空");
    }

        try {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
    } catch (BadCredentialsException e) {
        System.out.println("Authentication failed: Invalid credentials for user - " + loginRequest.getUsername());
        return Result.fail("凭据无效");
    }

    UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
    if (userDetails == null) {
        //日志
        System.out.println("Authentication failed: User not found - " + loginRequest.getUsername());
        return Result.fail("用户不存在");
    }
    String token = jwtUtils.generateJwtToken(userDetails.getUsername());

        // 只取第一个权限作为字符串
        String role = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("ROLE_USER"); // 默认值可根据实际情况设置
        return Result.success(new JwtResponse(token, role));
}



}

