package com.example.devicemanage.service;

import com.example.devicemanage.entity.Zczzb;
import com.example.devicemanage.model.Device;
import com.example.devicemanage.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    public Page<Zczzb> getDevicesPaged(Pageable pageable) {
        // 调用 Repository 层方法实现分页查询
        return deviceRepository.findAll(pageable);
    }
}