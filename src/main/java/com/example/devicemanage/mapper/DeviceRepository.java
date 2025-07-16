// DeviceRepository.java
package com.example.devicemanage.mapper;

import com.example.devicemanage.entity.Zczzb;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository {

    /**
     * 动态更新字段（使用白名单防止SQL注入）
     * @param id 设备编号 (zcbh)
     * @param fieldName 字段名
     * @param newValue 新值
     */
    @Update("update zczzb set ${fieldName} = #{newValue} where zcbh = #{id}")
    void updateField(@Param("id") String id, @Param("fieldName") String fieldName, @Param("newValue") String newValue);

    /**
     * 分页查询所有设备信息
     * @param offset 起始位置
     * @param size 查询数量
     * @return 当前页的设备列表
     */
    @Select("SELECT * FROM zczzb LIMIT #{size} OFFSET #{offset}")
    List<Zczzb> findPage(@Param("offset") int offset, @Param("size") int size);

    /**
     * 查询总记录数
     * @return 总设备数
     */
    @Select("SELECT COUNT(*) FROM zczzb")
    int countAll();


    /** 根据 zcbh 查询单条设备 */
    @Select("SELECT * FROM zczzb WHERE zcbh = #{zcbh}")
    Optional<Zczzb> findById(@Param("zcbh") String zcbh);

    /** 保存或更新整条记录（MyBatis-Plus 可用 insert/update，这里简单覆盖） */
    @Update("REPLACE INTO zczzb (zcbh, zcflh, zcmc, ppxh, gg, je, jldw, cj, ggrq, xz, jfkm, cfdbh, cfdmc, syrbh, syr, jsr, ywdh, shzt, jzr, rzrq, bz, zfby1, zfby2, szby1, szby2, rqby1, srr, srrq) " +
            "VALUES (#{zcbh}, #{zcflh}, #{zcmc}, #{ppxh}, #{gg}, #{je}, #{jldw}, #{cj}, #{ggrq}, #{xz}, #{jfkm}, #{cfdbh}, #{cfdmc}, #{syrbh}, #{syr}, #{jsr}, #{ywdh}, #{shzt}, #{jzr}, #{rzrq}, #{bz}, #{zfby1}, #{zfby2}, #{szby1}, #{szby2}, #{rqby1}, #{srr}, #{srrq})")
    void save(@Param("zcbh") Zczzb device);

    @Select("SELECT dwbh FROM dwb")
    List<String> getDwbList();
}
