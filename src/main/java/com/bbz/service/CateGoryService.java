package com.bbz.service;

import com.bbz.entity.CateGory;
import com.bbz.po.CateGoryPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CateGoryService {
    //一级分页查
    List<CateGory> selectA(@Param("pageNow") Integer pageNow, @Param("size") Integer size);
    //查一级总行数
    Integer countSetvice();
    //添加(一二级)
    void add(CateGory cateGory);
    //修改(一二级)
    void update(CateGory cateGory);
    //分页查二级
    List<CateGory> selectB(@Param("id")String id,@Param("pageNow") Integer pageNow,@Param("size") Integer size);
    //二级总行数
    Integer countB(String id);
    //查所有一级类别
    List<CateGory> selectOnelevels();
    //查所有二级类别
    List<CateGory> selectTwolevels();
    //删除类别
    String delete(CateGory cateGory);

    //前台展示所有类别
    List<CateGoryPO> queryAllCategory();

}
