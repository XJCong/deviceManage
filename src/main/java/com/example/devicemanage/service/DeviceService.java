// DeviceService.java
package com.example.devicemanage.service;

import com.example.devicemanage.common.Result;
import com.example.devicemanage.entity.Zczzb;
import com.example.devicemanage.mapper.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;

    // 白名单字段集合，防止非法字段更新
    private static final Set<String> ALLOWED_FIELDS = Set.of(
            "zcflh", "zcmc", "ppxh", "gg", "je", "jldw", "cj",
            "ggrq", "xz", "jfkm", "cfdbh", "cfdmc", "syrbh", "syr"
    );

    @Autowired
    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    /**
     * 获取分页设备数据
     * @param pageable 分页参数
     * @return 分页后的设备数据
     */
    public Result<Page<Zczzb>> getDevicesPaged(Pageable pageable) {
        int offset = pageable.getPageNumber() * pageable.getPageSize();
        int size = pageable.getPageSize();

        List<Zczzb> devices = deviceRepository.findPage(offset, size);
        int total = deviceRepository.countAll();

        Page<Zczzb> page = new PageImpl<>(devices, pageable, total);
        return Result.success(page);
    }

    /**
     * 安全更新设备字段（基于白名单校验）
     * @param id 设备编号
     * @param fieldName 字段名
     * @param newValue 新值
     * @return 是否更新成功
     */
    public boolean updateDeviceField(String id, String fieldName, String newValue) {
        if (!ALLOWED_FIELDS.contains(fieldName)) {
            throw new IllegalArgumentException("不允许更新的字段: " + fieldName);
        }
        deviceRepository.updateField(id, fieldName, newValue);
        return true;
    }

    public void updateDeviceInformation(List<Map<String, Object>> changes) throws Exception {
        for (Map<String, Object> change : changes) {
            String id = (String) change.get("zcbh");
            Map<String, Map<String, String>> fields = (Map<String, Map<String, String>>) change.get("changes");

            for (Map.Entry<String, Map<String, String>> entry : fields.entrySet()) {
                String fieldName = entry.getKey();
                Map<String, String> valuePair = entry.getValue();
                String oldValue = valuePair.get("old");
                String newValue = valuePair.get("new");
                deviceRepository.updateField(id, fieldName, newValue);
            }
        }
    }
}
