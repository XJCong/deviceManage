package com.example.devicemanage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("field_permission")
public class FieldPermission {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String fieldName;
    private String role;
    private String permission; // read / write
}