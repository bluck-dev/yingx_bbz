package com.bbz.dao;

import com.bbz.entity.CateGory;
import com.bbz.po.CateGoryPO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CateGoryDAO extends Mapper<CateGory> {
        //分页查一级
        List<CateGory> selectA(@Param("pageNow") Integer pageNow,@Param("size") Integer size);
        //一级总行数
        Integer count();
        //分页查二级
        List<CateGory> selectB(@Param("id")String id,@Param("pageNow") Integer pageNow,@Param("size") Integer size);
        //二级总行数
        Integer countB(String id);
        //查所有一级类别
        List<CateGory> selectOnelevels();
        //查所有一级类别
        List<CateGory> selectTwolevels();
         //删除类别
       void de(String id);

       //前台展示所有类别
       List<CateGoryPO> queryAllCategory();

}
