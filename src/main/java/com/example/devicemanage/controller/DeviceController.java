package com.example.devicemanage.controller;

import com.example.devicemanage.common.Result;
import com.example.devicemanage.entity.Dwb;
import com.example.devicemanage.entity.Sbfl;
import com.example.devicemanage.entity.Zcbdb;
import com.example.devicemanage.entity.Zczzb;
import com.example.devicemanage.mapper.HistoryRespository;
import com.example.devicemanage.repository.DwbRepository;
import com.example.devicemanage.repository.SbflRepository;
import com.example.devicemanage.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

// DeviceController.java
@RestController
@RequestMapping("/device")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;
    @Autowired
    private HistoryRespository zcbdbRepository;

    private final DwbRepository dwbRepository;

    private final SbflRepository sbflRepository;


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
    @GetMapping("/{zcbh}")
    public ResponseEntity<Zczzb> getDeviceById(@PathVariable String zcbh) {
        Optional<Zczzb> device = deviceService.findDeviceById(zcbh);
        return device.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{zcbh}")
    public ResponseEntity<?> updateDevice(
            @PathVariable String zcbh,
            @RequestBody Map<String, String> updates) {
        boolean success = deviceService.updateDevice(zcbh, updates);
        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("更新失败");
        }
    }
    @GetMapping("/{zcbh}/history")
    public ResponseEntity<Page<Zcbdb>> getDeviceHistory(
        @PathVariable String zcbh,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {

        // 计算偏移量
        long offset = (long) page * size;

        // 查询数据
        List<Zcbdb> content = zcbdbRepository.findByZcbh(zcbh, offset, size);

        // 查询总数
        long total = zcbdbRepository.countByZcbh(zcbh);

        // 构造分页结果
        Page<Zcbdb> result = new PageImpl<>(
            content,
            PageRequest.of(page, size),
            total
        );

        return ResponseEntity.ok(result);
    }

 @GetMapping("/dwb/list")
public ResponseEntity<List<Map<String, String>>> getDepartmentList() {
    try {
        List<Dwb> departments = dwbRepository.findAll();

        // 转换为前端需要的格式: [{value: "单位编号", label: "单位名称"}]
        List<Map<String, String>> result = departments.stream()
            .map(dept -> Map.of(
                "value", dept.getDwbh(),  // 单位编号作为value
                "label", dept.getDwmc()   // 单位名称作为显示文本
            ))
            .collect(Collectors.toList());

        return ResponseEntity.ok(result);
    } catch (Exception e) {
        return ResponseEntity.status(500).body(null);
        }
    }
        @GetMapping("/getFilteredDevices")
        public ResponseEntity<List<Map<String, String>>> getFilteredDevicesList() {
            try {
                List<Sbfl> departments = sbflRepository.findAll();

                // 显式转换为String类型
                List<Map<String, String>> result = departments.stream()
                    .map(dept -> {
                        Map<String, String> map = new HashMap<>();
                        map.put("value", dept.getFldm());  // 确保返回String
                        map.put("label", dept.getFlmc());  // 确保返回String
                        return map;
                    })
                    .collect(Collectors.toList());

                return ResponseEntity.ok(result);
            } catch (Exception e) {
                return ResponseEntity.status(500).body(null);
            }
        }

}


