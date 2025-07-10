package com.example.devicemanage.utils;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtils {

    // 建议使用 Base64 编码的 512-bit 密钥（64 字节）
    private static final String SECRET_STRING = "THIS_IS_A_STRONG_SECRET_KEY_FOR_HS512_SIGNATURE_1234567890";
    private static final long EXPIRATION = 86400000; // 24小时

    // 构建安全密钥
    private final SecretKey secretKey;

    public JwtUtils() {
        byte[] decodedKey = Base64.getEncoder().encode(SECRET_STRING.getBytes());
        this.secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "HmacSHA512");
    }

    /**
     * 生成 JWT Token
     */
    public String generateJwtToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }

    /**
     * 从 Token 中提取用户名
     */
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * 验证 Token 是否有效
     */
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(authToken);
            return true;
        } catch (JwtException e) {
            // 可以记录日志用于调试
            System.err.println("JWT validation error: " + e.getMessage());
            return false;
        }
    }
}
