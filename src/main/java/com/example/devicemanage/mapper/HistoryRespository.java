package com.example.devicemanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.devicemanage.entity.Zcbdb;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper
public interface HistoryRespository extends BaseMapper<Zcbdb> {
    // 使用MyBatis-Plus的分页查询方式
    @Select("SELECT * FROM zcbdb WHERE zcbh = #{zcbh} LIMIT #{pageSize} OFFSET #{offset}")
    List<Zcbdb> findByZcbh(
        @Param("zcbh") String zcbh,
        @Param("offset") long offset,
        @Param("pageSize") int pageSize
    );

    // 新增总数查询
    @Select("SELECT COUNT(*) FROM zcbdb WHERE zcbh = #{zcbh}")
    long countByZcbh(@Param("zcbh") String zcbh);
}

