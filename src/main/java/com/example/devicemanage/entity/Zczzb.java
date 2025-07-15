package com.example.devicemanage.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//设备在账表数据项
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Zczzb {
    private String lydwh; // 使用单位号
    private String lydwm; // 使用单位名
    @Id
    private String zcbh;  // 设备编号
    private String zcflh; // 分类号
    private String zcmc;  // 设备名称
    private String ppxh;  // 品牌型号
    private String gg;    // 规格
    private String jldw;  // 计量单位
    private Double je;    // 金额
    private String cj;    // 厂家
    private String ggrq;  // 购置日期
    private String xz;    // 现状
    private String jfkm;  // 经费科目
    private String cfdbh; // 存放地编号
    private String cfdmc; // 存放地名称
    private String syrbh; // 使用人编号
    private String syr;   // 使用人
    private String jsr;   // 经手人
    private String ywdh;  // 业务单号
    private String shzt;  // 审核状态
    private String jzr;   // 记帐人
    private String rzrq;  // 入账时间
    private String bz;    // 备注
    private String zfby1; // 字符备用1
    private String zfby2; // 字符备用2
    private Double szby1; // 数字备用1
    private Double szby2; // 数字备用2
    private String rqby1; // 日期备用1
    private String srr;   // 输入人
    private String srrq;  // 输入日期

}
