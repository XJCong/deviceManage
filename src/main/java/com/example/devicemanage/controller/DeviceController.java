package com.example.devicemanage.controller;

import com.example.devicemanage.common.Result;
import com.example.devicemanage.entity.Zczzb;
import com.example.devicemanage.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;
    @GetMapping("/getDevices")
    public Result<Page<Zczzb>> getDevices(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "1") int limit) {
        Pageable pageable = PageRequest.of(page, limit);
        // 调用 Service 层方法实现分页查询
        Page<Zczzb> devices = deviceService.getDevicesPaged(pageable);
        System.out.println(devices);
        return Result.success(devices);
    }
}
