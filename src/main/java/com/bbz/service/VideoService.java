package com.bbz.service;

import com.bbz.entity.Video;
import com.bbz.po.VideoPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VideoService {
    //分页
    List<Video> selectPageService(@Param("pageNow") Integer pageNow, @Param("size") Integer size);
    //总行数
    Integer countService();
    //添加
    String add(Video video);
    //上传Ali
    void AliyunUpload(MultipartFile videoPath,String id);
    //修改ali
    void AliyunUpdate(MultipartFile videoPath,String id);
    //删除
    void delete(Video video);
    //修改
    String update(Video video);

    //前台展示所有视频
    List<VideoPO> queryByReleaseTime();
    //前台APP模糊查所有视频
    List<VideoPO> queryByLikeVideoName(String key);
    //前台APP根据二级类别ID查所有
    List<VideoPO> queryCateVideoList(String cateId);

}
