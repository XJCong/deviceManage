package com.example.devicemanage.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
设备分类表数据项（sbfl）
范围：存放设备分类信息。

 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sbfl")
public class Sbfl {
    @Id
    private String fldm; // 分类代码
    private String flmc; // 分类名称
}
