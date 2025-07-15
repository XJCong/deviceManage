package com.example.devicemanage.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.devicemanage.entity.DevicePhoto;
import com.example.devicemanage.mapper.DevicePhotoMapper;
import com.example.devicemanage.model.DevicePhotoDTO;
import com.example.devicemanage.utils.DiskStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DevicePhotoService {

    private final DevicePhotoMapper mapper;
    private final DiskStorageService storage;

    @Transactional
    public DevicePhotoDTO savePhoto(String zcbh, MultipartFile file) {
        try {
            String fileUrl = storage.save(zcbh, file);
            String thumbUrl = storage.generateThumb(zcbh,
                    Paths.get(fileUrl).getFileName().toString());
            DevicePhoto entity = new DevicePhoto();
            entity.setZcbh(zcbh);
            entity.setFileName(file.getOriginalFilename());
            entity.setFileUrl(fileUrl);
            entity.setThumbUrl(thumbUrl);
            entity.setCreatedAt(LocalDateTime.now());
            mapper.insert(entity);

            return new DevicePhotoDTO(
                    entity.getId(), zcbh, entity.getFileName(), fileUrl, thumbUrl);

        } catch (IOException e) {
            throw new RuntimeException("上传失败", e);
        }
    }

    public List<DevicePhotoDTO> listPhotos(String zcbh) {
        return mapper.selectList(new QueryWrapper<DevicePhoto>()
                        .eq("zcbh", zcbh))
                .stream()
                .map(e -> new DevicePhotoDTO(
                        e.getId(), e.getZcbh(), e.getFileName(),
                        e.getFileUrl(), e.getThumbUrl()))
                .collect(Collectors.toList());
    }

    public void deletePhoto(String zcbh, Long id) {
        DevicePhoto po = mapper.selectById(id);
        System.out.println("前端传入 id = " + id);
        System.out.println("数据库记录 id = " + (po == null ? "null" : po.getId()));
        if (po == null || !po.getZcbh().equals(zcbh)) {
            throw new RuntimeException("记录不存在");
        }
        storage.delete(zcbh,
                Paths.get(po.getFileUrl()).getFileName().toString(),
                po.getThumbUrl() == null ?
                        null : Paths.get(po.getThumbUrl()).getFileName().toString());
        mapper.deleteById(id);
    }
}