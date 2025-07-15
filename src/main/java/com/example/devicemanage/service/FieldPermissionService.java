package com.example.devicemanage.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.devicemanage.entity.FieldPermission;
import com.example.devicemanage.mapper.FieldPermissionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FieldPermissionService {

    private final FieldPermissionMapper mapper;

    // 根据角色获取字段权限
    public Map<String, String> getPermissionsByRole(String role) {
        List<FieldPermission> list = mapper.selectList(
                new QueryWrapper<FieldPermission>().eq("role", role)
        );

        Map<String, String> map = new HashMap<>();
        for (FieldPermission fp : list) {
            map.put(fp.getFieldName(), fp.getPermission());
        }
        return map;
    }

    // 获取所有角色
    public List<String> getAllRoles() {
        return mapper.selectObjs(
                        new QueryWrapper<FieldPermission>()
                                .select("DISTINCT role")
                ).stream()
                .map(Object::toString)
                .collect(Collectors.toList());
    }

    @Transactional
    public void savePermissions(String role, Map<String, String> permissions) {
        mapper.delete(new QueryWrapper<FieldPermission>().eq("role", role));
        permissions.forEach((field, perm) -> {
            FieldPermission fp = new FieldPermission();
            fp.setRole(role);
            fp.setFieldName(field);
            fp.setPermission(perm);
            mapper.insert(fp);
        });
    }
}