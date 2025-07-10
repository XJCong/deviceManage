package com.example.devicemanage.entity;


/*
单位表数据项（DWB）
范围：全校所有单位，包括教学、科研、后勤、行政、生产等单位。
规则：设备必须落实到最低一级上。字段要求：

 */
public class Dwb {
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
