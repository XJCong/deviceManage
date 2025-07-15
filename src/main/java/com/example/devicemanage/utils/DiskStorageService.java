package com.example.devicemanage.utils;

import lombok.Data;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
@ConfigurationProperties(prefix = "storage")
@Data
public class DiskStorageService {
    @Value("${file.upload-dir:C:/uploads}")
    private String rootPath;

    /**
     * 保存原图并返回访问 URL
     */
    public String save(String zcbh, MultipartFile file) throws IOException {
        Path dir = Paths.get(rootPath, zcbh);
        Files.createDirectories(dir);

        String ext = FilenameUtils.getExtension(file.getOriginalFilename());
        String fileName = UUID.randomUUID() + "." + ext;
        Path target = dir.resolve(fileName);
        Files.copy(file.getInputStream(), target);

        // 访问 URL（需配置静态资源映射，见第 7 步）
        return "/static/device-photos/" + zcbh + "/" + fileName;
    }

    /**
     * 生成缩略图并返回缩略图 URL
     */
    public String generateThumb(String zcbh, String fileName) throws IOException {
        Path src  = Paths.get(rootPath, zcbh, fileName);
        String thumbName = FilenameUtils.removeExtension(fileName) + "_thumb.jpg";
        Path dest = Paths.get(rootPath, zcbh, thumbName);

        Thumbnails.of(src.toFile())
                .size(150, 150)
                .toFile(dest.toFile());

        return "/static/device-photos/" + zcbh + "/" + thumbName;
    }

    /**
     * 物理删除文件
     */
    public void delete(String zcbh, String fileName, String thumbName) {
        try {
            // 删原图
            Path dir = Paths.get(rootPath, zcbh);
            Files.deleteIfExists(dir.resolve(fileName));
            // 删缩略图
            if (thumbName != null) {
                Files.deleteIfExists(dir.resolve(thumbName));
            }

            // 空文件夹清理
            try (var stream = Files.newDirectoryStream(dir)) {
                if (!stream.iterator().hasNext()) {
                    Files.delete(dir);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("删除文件失败", e);
        }
    }
}