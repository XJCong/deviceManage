package com.example.devicemanage.repository;

import com.example.devicemanage.entity.Dwb;
import com.example.devicemanage.entity.Sbfl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SbflRepository extends JpaRepository<Sbfl, String> {
    // 继承JpaRepository默认就有findAll方法
    // 如需特殊查询可以添加自定义方法
}
