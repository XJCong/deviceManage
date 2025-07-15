package com.example.devicemanage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName; // 添加该行

import java.time.LocalDateTime;

@Data
@TableName("t_device_photo")
public class DevicePhoto {
    @TableId(value = "id", type = IdType.ASSIGN_ID) // 雪花算法
    private String id;
    private String zcbh;
    private String fileName;
    private String fileUrl;
    private String thumbUrl;
    private LocalDateTime createdAt;
}
