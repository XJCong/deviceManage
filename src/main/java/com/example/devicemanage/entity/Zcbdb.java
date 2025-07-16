package com.example.devicemanage.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/*
设备变动表数据项（zcbdb）
范围：设备变动数据信息。

 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Zcbdb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 新增自增主键

    private String lydwh; // 使用单位号
    private String lydwm; // 使用单位名
    private String zcbh;  // 设备编号
    private String zcflh; // 分类号
    private String zcmc;  // 设备名称
    private String ppxh;  // 品牌型号
    private String gg;    // 规格
    private Double je;    // 金额
    private String jldw;  // 计量单位
    private String cj;    // 厂家
    private String ggrq;  // 购置日期
    private String xz;    // 现状
    private String jfkm;  // 经费科目
    private String cfdbh; // 存放地编号
    private String cfdmc; // 存放地名称
    private String syrbh; // 使用人编号
    private String syr;   // 使用人
    private String jsr;   // 经手人
    private String shzt;  // 审核状态
    private String ywdh;  // 业务单号
    private String jzr;   // 记帐人
    private String rzrq;  // 入账时间
    private String bz;    // 备注
    private String zfby1; // 字符备用1
    private String zfby2; // 字符备用2
    private Double szby1; // 数字备用1
    private Double szby2; // 数字备用2
    private String srr;   // 输入人
    private String srrq;  // 输入日期
    private String bdrq;  // 变动日期
    private String bdyy;  // 变动历史
    private String zrdwh; // 转入单位号
    private String sqrbh; // 申请人编号
    private String sqr;   // 申请人

    // 定义格式器（在类中声明为常量）
private static final Map<String, String> FIELD_NAME_MAPPING = Map.ofEntries(
        Map.entry("lydwh", "使用单位号"),
        Map.entry("lydwm", "使用单位名"),
        Map.entry("zcbh", "设备编号"),
        Map.entry("zcflh", "分类号"),
        Map.entry("zcmc", "设备名称"),
        Map.entry("ppxh", "品牌型号"),
        Map.entry("gg", "规格"),
        Map.entry("je", "金额"),
        Map.entry("jldw", "计量单位"),
        Map.entry("cj", "厂家"),
        Map.entry("ggrq", "购置日期"),
        Map.entry("xz", "现状"),
        Map.entry("jfkm", "经费科目"),
        Map.entry("cfdbh", "存放地编号"),
        Map.entry("cfdmc", "存放地名称"),
        Map.entry("syrbh", "使用人编号"),
        Map.entry("syr", "使用人"),
        Map.entry("jsr", "经手人"),
        Map.entry("shzt", "审核状态"),
        Map.entry("ywdh", "业务单号"),
        Map.entry("jzr", "记帐人"),
        Map.entry("rzrq", "入账时间"),
        Map.entry("bz", "备注"),
        Map.entry("zfby1", "字符备用1"),
        Map.entry("zfby2", "字符备用2"),
        Map.entry("szby1", "数字备用1"),
        Map.entry("szby2", "数字备用2"),
        Map.entry("srr", "输入人"),
        Map.entry("srrq", "输入日期")
        ,Map.entry("zrdwh", "转入单位号"),
        Map.entry("sqrbh", "申请人编号"),
        Map.entry("sqr", "申请人")

);


    public Zcbdb(Zczzb originalDevice, String fieldName, String oldValue, String newValue,String name,long id) {
        this.lydwh = originalDevice.getLydwh();
        this.lydwm = originalDevice.getLydwm();
        this.zcbh = originalDevice.getZcbh();
        this.zcflh = originalDevice.getZcflh();
        this.zcmc = originalDevice.getZcmc();
        this.ppxh = originalDevice.getPpxh();
        this.gg = originalDevice.getGg();
        this.je = originalDevice.getJe();
        this.jldw = originalDevice.getJldw();
        this.cj = originalDevice.getCj();
        this.ggrq = originalDevice.getGgrq();
        this.xz = originalDevice.getXz();
        this.jfkm = originalDevice.getJfkm();
        this.cfdbh = originalDevice.getCfdbh();
        this.cfdmc = originalDevice.getCfdmc();
        this.syrbh = originalDevice.getSyrbh();
        this.syr = originalDevice.getSyr();
        this.jsr = originalDevice.getJsr();
        this.shzt = originalDevice.getShzt();
        this.ywdh = originalDevice.getYwdh();
        this.jzr = originalDevice.getJzr();
        this.rzrq = originalDevice.getRzrq();
        this.bz = originalDevice.getBz();
        this.zfby1 = originalDevice.getZfby1();
        this.zfby2 = originalDevice.getZfby2();
        this.szby1 = originalDevice.getSzby1();
        this.szby2 = originalDevice.getSzby2();
        this.srr = originalDevice.getSrr();
        this.srrq = originalDevice.getSrrq();
        this.bdrq = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.bdyy = FIELD_NAME_MAPPING.getOrDefault(fieldName, fieldName) +
                ": " + oldValue + " -> " + newValue;
        //this.zrdwh = originalDevice.getLydwh();
        this.sqrbh = String.valueOf(id);
        this.sqr = name;
    }


}
