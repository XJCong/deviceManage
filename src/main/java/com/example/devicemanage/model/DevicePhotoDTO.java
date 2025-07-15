package com.example.devicemanage.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DevicePhotoDTO {
    private String id;
    private String zcbh;
    private String fileName;
    private String url;
    private String thumbUrl;
}