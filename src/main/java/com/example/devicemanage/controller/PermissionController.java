package com.example.devicemanage.controller;

import com.example.devicemanage.service.FieldPermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PermissionController {

    private final FieldPermissionService permissionService;

    @GetMapping("/getPermissions")
    public Map<String, String> getPermissions(@RequestParam String role) {
        return permissionService.getPermissionsByRole(role);
    }

    @GetMapping("/roles")
    public List<String> getRoles() {
        return permissionService.getAllRoles();
    }

    @PostMapping("/savePermissions")
    public Map<String, Object> savePermissions(@RequestBody Map<String, Object> body) {
        String role        = (String) body.get("role");
        Map<String,String> permissions = (Map<String,String>) body.get("permissions");

        permissionService.savePermissions(role, permissions);

        return Map.of("code", 200, "message", "保存成功");
    }
}