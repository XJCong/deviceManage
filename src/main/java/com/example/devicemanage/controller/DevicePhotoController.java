package com.example.devicemanage.controller;

import com.example.devicemanage.model.DevicePhotoDTO;
import com.example.devicemanage.service.DevicePhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/devices/{zcbh}/photos")
@RequiredArgsConstructor
public class DevicePhotoController {

    private final DevicePhotoService photoService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public DevicePhotoDTO upload(@PathVariable String zcbh,
                                 @RequestPart("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new RuntimeException("文件为空");
        }
        return photoService.savePhoto(zcbh, file);
    }


    @GetMapping
    public List<DevicePhotoDTO> list(@PathVariable String zcbh) {

        return photoService.listPhotos(zcbh);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String zcbh,
                       @PathVariable Long id) {
        System.out.println("zcbh:"+zcbh);
        System.out.println("id:"+id);
        photoService.deletePhoto(zcbh, id);
    }
}