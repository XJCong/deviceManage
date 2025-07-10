package com.example.devicemanage.repository;

import com.example.devicemanage.entity.Zczzb;
import com.example.devicemanage.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Zczzb, Long> {

}