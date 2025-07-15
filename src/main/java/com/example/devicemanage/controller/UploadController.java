package com.example.devicemanage.controller;

import com.example.devicemanage.common.Result;
import com.example.devicemanage.model.DevicePhotoDTO;
import com.example.devicemanage.service.DevicePhotoService;
import com.example.devicemanage.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

// UploadController.java
@RestController
@RequestMapping("/api")
public class UploadController {

    @Autowired
    private DevicePhotoService photoService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping(value = "/upload/{zcbh}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result<DevicePhotoDTO> uploadPhoto(
            @PathVariable String zcbh,
            @RequestPart("file") MultipartFile file,
            @RequestHeader("Authorization") String authHeader) {

        // 1. JWT验证
        String token = authHeader.substring(7);
        if (!jwtUtils.validateJwtToken(token)) {
            return Result.fail("无效的认证令牌");
        }

        // 2. 设备编号格式校验
        if (!zcbh.matches("^[A-Z0-9]{8}$")) {
            return Result.fail("设备编号格式错误");
        }

        // 3. 文件验证
        if (file.isEmpty()) {
            return Result.fail("文件不能为空");
        }
        if (file.getSize() > 5 * 1024 * 1024) {
            return Result.fail("文件大小不能超过5MB");
        }

        // 4. 调用服务层处理
        DevicePhotoDTO dto = photoService.savePhoto(zcbh, file);
        return Result.success(dto);
    }
}

