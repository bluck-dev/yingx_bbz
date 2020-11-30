package com.bbz.dao;

import com.bbz.entity.Video;
import com.bbz.po.VideoPO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface VideoDAO extends Mapper<Video> {
    //分页
    List<Video> selectPage(@Param("pageNow") Integer pageNow, @Param("size") Integer size);
    //总行数
    Integer count();
    //根据ID查一个
    Video seletOne(String id);
    //修改
    void update(Video video);
    //删除
    void delet(String id);

    //前台APP查所有视频
    List<VideoPO> queryByReleaseTime();
    //前台APP模糊查所有视频
    List<VideoPO> queryByLikeVideoName(String key);
    //前台APP根据二级类别ID查所有
    List<VideoPO> queryCateVideoList(String cateId);
}
