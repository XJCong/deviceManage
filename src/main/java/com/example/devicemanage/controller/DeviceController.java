package com.example.devicemanage.controller;

import com.example.devicemanage.common.Result;
import com.example.devicemanage.entity.Zczzb;
import com.example.devicemanage.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// DeviceController.java
@RestController
@RequestMapping("/device")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @GetMapping("/getDevices")
    public Result<Page<Zczzb>> getDevices(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit) {
        Pageable pageable = PageRequest.of(page, limit);
        return deviceService.getDevicesPaged(pageable);
    }

    @PutMapping("/update")
    public ResponseEntity<Boolean> updateDeviceField(
            @RequestParam String id,
            @RequestParam String field,
            @RequestParam String value) {
        return ResponseEntity.ok(deviceService.updateDeviceField(id, field, value));
    }
    @PostMapping("/changeInfo")
    public ResponseEntity<String> changeDeviceInfo(@RequestBody List<Map<String, Object>> changes) {
        try {
            // 调用服务层处理设备信息变更
            deviceService.updateDeviceInformation(changes);
            return ResponseEntity.ok("设备信息更新成功");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("设备信息更新失败: " + e.getMessage());
        }
    }
}

