package com.example.devicemanage.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dwb")
public class Dwb {
    @Id
    private String dwbh; // 单位编号
    private String dwmc; // 单位名称
    private String fjdbh; // 父节点编号
    private String dwxz; // 单位性质
    private String dwbz; // 单位标志
    private String zfby1; // 字符备用1
    private String zfby2; // 字符备用2
    private Double szby1; // 数字备用1
    private Double szby2; // 数字备用2
}
