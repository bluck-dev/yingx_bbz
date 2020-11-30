package com.bbz.service;

import com.bbz.entity.User;
import com.bbz.mc.City;
import com.bbz.mc.Mc;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {
    //分页
    List<User> selectPageService(@Param("pageNow") Integer pageNow, @Param("size") Integer size);
    //总行数
    Integer countService();
    //修改
    void update(User user);

    //添加
    String add(User user);
    //文件上传
     void Upload(MultipartFile picImg, String id, HttpServletRequest request);         //上传

    //查询用户在几月注册的数量
    List<Mc> selectMouth(String sex,Integer mouth);

    //查询在地区用户创建的数量
    List<City> selectCity(String sex);
}
