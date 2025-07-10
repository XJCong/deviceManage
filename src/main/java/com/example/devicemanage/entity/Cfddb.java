package com.example.devicemanage.entity;

import lombok.Data;

/*
4、	设备存放地点表数据项（CFDDB）
范围：存放设备地点。
 */
@Data
public class Cfddb {
    private Integer xh; // 序号
    private String cfdbh; // 存放地编号
    private String cfcmc; // 存放地名称
    private String dwbh; // 单位编号
    private String dwmc; // 单位名称
    private String zfby1; // 字符备用1
    private String zfby2; // 字符备用2
    private Double szby1; // 数字备用1
    private Double szby2; // 数字备用2
    private Double szby3; // 数字备用3
}
