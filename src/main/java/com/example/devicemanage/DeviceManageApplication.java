package com.example.devicemanage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class DeviceManageApplication {

    public static void main(String[] args) {
        // 启动Spring Boot应用程序
        SpringApplication.run(DeviceManageApplication.class, args);
    }
    // 添加这个 Bean 来启用全局 CORS 支持
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/open/**")
                        .allowedOrigins("http://localhost:5173") // 允许来自 Vue 开发环境的请求
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true)
                        .maxAge(3600); // 缓存预检请求结果时间
            }
        };
    }
}
