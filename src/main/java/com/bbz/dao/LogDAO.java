package com.bbz.dao;

import com.bbz.entity.Log;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface LogDAO extends Mapper<Log> {
    List<Log> selectPage(@Param("pageNow") Integer pageNow,@Param("size") Integer size);

    Integer count();
}
