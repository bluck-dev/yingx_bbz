package com.bbz.dao;

import com.bbz.entity.User;
import com.bbz.mc.City;
import com.bbz.mc.Mc;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserDAO extends Mapper<User> {
    //分页
    List<User> selectPage(@Param("pageNow") Integer pageNow,@Param("size") Integer size);
    //总行数
    Integer count();

    //查询用户在几月注册的数量
    List<Mc> selectMouth(@Param("sex")String sex,@Param("mouth") Integer mouth);
    //查询在地区用户创建的数量
    List<City> selectCity(String sex);


}
