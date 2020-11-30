package com.bbz.serviceimp;

import com.bbz.annotcation.AddLog;
import com.bbz.dao.VideoDAO;
import com.bbz.entity.Video;
import com.bbz.po.VideoPO;
import com.bbz.service.VideoService;
import com.bbz.util.AliyunOSSUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class VideoServiceImp implements VideoService {
    @Resource
    private VideoDAO videoDAO;
    @Override
    public List<Video> selectPageService(Integer pageNow, Integer size) {
        Integer begin=(pageNow-1)*size;
        Integer end=pageNow*size;
        return videoDAO.selectPage(begin,end);
    }

    @Override
    public Integer countService() {
        return videoDAO.count();
    }

    @Override
    @AddLog("添加上传视频")
    public String  add(Video video) {                                   //添加
        String uuid=UUID.randomUUID().toString();
        video.setId(uuid);
        video.setCoverPath("???");//封面
//        video.setVideoPath("https://yingx-bz.oss-cn-beijing.aliyuncs.com/"+objectName);//视频
        video.setUploadTime(new Date());//上传时间
        video.setLikeCount(1);
        video.setPlayCount(1);
//        video.setCategoryId("1");
        video.setUserId("1");
        video.setGroupId("1");
        video.setStatus("1");
        videoDAO.insertSelective(video);
        return uuid;
    }

    @Override
    public void AliyunUpload(MultipartFile videoPath,String id) {                      //上传
        //1.视频上传至阿里云   字节数组
        //获取文件名
        String filename = videoPath.getOriginalFilename();
        //拼接时间戳    1606185263426-草原.mp4
        String newName=new Date().getTime()+"-"+filename;
        //拼接视频文件夹
        String videoName="video/"+newName;
        /*
         * 上传视频至阿里云
         * 参数:
         *   videoPath: MultipartFile类型的文件
         *   bucketName:存储空间名
         *   objectName:文件名
         * */
        AliyunOSSUtil.uploadFileByte(videoPath,"yingx-bz",videoName);

        //截取文件名
        String[] split = newName.split("\\.");
        //拼接图片名
        String coverName="cover/"+split[0]+".jpg";
        /*
         * 2.截取视频第一帧
         * 参数:
         *   bucketName:存储空间名
         *   videoName:视频名  文件夹
         *   coverName:封面名
         * */
        AliyunOSSUtil.interceptVideoCover("yingx-bz", videoName,coverName);
        Video video = new Video();
        video.setId(id);
        video.setVideoPath("https://yingx-bz.oss-cn-beijing.aliyuncs.com/"+videoName);//视频
        video.setCoverPath("https://yingx-bz.oss-cn-beijing.aliyuncs.com/"+coverName);//封面
        System.out.println("-----------");
        videoDAO.update(video);
    }

    @Override
    public void AliyunUpdate(MultipartFile videoPath, String id) {
        if(videoPath.getSize()!=0){
            Video video1 = videoDAO.selectByPrimaryKey(id);//查到详细数据
            String videoPaths = video1.getVideoPath().replace("https://yingx-bz.oss-cn-beijing.aliyuncs.com/", "");//截取视频文件名
            String coverPath= video1.getCoverPath().replace("https://yingx-bz.oss-cn-beijing.aliyuncs.com/","");//截取封面文件名
            //删除云
            AliyunOSSUtil.deleteFile("yingx-bz",videoPaths);
            AliyunOSSUtil.deleteFile("yingx-bz",coverPath);
            videoDAO.deleteByPrimaryKey(video1);

            //1.视频上传至阿里云   字节数组
            //获取文件名
            String filename = videoPath.getOriginalFilename();
            //拼接时间戳    1606185263426-草原.mp4
            String newName=new Date().getTime()+"-"+filename;
            //拼接视频文件夹
            String videoName="video/"+newName;
            /*
             * 上传视频至阿里云
             * 参数:
             *   videoPath: MultipartFile类型的文件
             *   bucketName:存储空间名
             *   objectName:文件名
             * */
            AliyunOSSUtil.uploadFileByte(videoPath,"yingx-bz",videoName);

            //截取文件名
            String[] split = newName.split("\\.");
            //拼接图片名
            String coverName="cover/"+split[0]+".jpg";
            /*
             * 2.截取视频第一帧
             * 参数:
             *   bucketName:存储空间名
             *   videoName:视频名  文件夹
             *   coverName:封面名
             * */
            AliyunOSSUtil.interceptVideoCover("yingx-bz", videoName,coverName);
            Video video = new Video();
            video.setId(id);
            video.setVideoPath("https://yingx-bz.oss-cn-beijing.aliyuncs.com/"+videoName);//视频
            video.setCoverPath("https://yingx-bz.oss-cn-beijing.aliyuncs.com/"+coverName);//封面
            System.out.println("-----------"+video);
            videoDAO.update(video);
        }
    }
    @Override
    @AddLog("删除视频")
    public void delete(Video video) {                                                   //删除
        Video video1 = videoDAO.selectByPrimaryKey(video);//查到详细数据
        String videoPath = video1.getVideoPath().replace("https://yingx-bz.oss-cn-beijing.aliyuncs.com/", "");//截取视频文件名
        String coverPath= video1.getCoverPath().replace("https://yingx-bz.oss-cn-beijing.aliyuncs.com/","");//截取封面文件名
        //删除云
        AliyunOSSUtil.deleteFile("yingx-bz",videoPath);
        AliyunOSSUtil.deleteFile("yingx-bz",coverPath);
        videoDAO.deleteByPrimaryKey(video);
    }

    @Override
    public String update(Video video) {
        if(video.getVideoPath()==""){
            video.setVideoPath(null);
        }
        Video video1 = videoDAO.selectByPrimaryKey(video);
        Video video2 = new Video();
        video2.setId(video1.getId());
        video2.setTitle(video1.getTitle());
        video2.setBrief(video1.getBrief());
        videoDAO.updateByPrimaryKeySelective(video2);
        return video2.getId();
    }

    @Override
    public List<VideoPO> queryByReleaseTime() {                     //前台展示所有视频
        List<VideoPO> videoPOS = videoDAO.queryByReleaseTime();
        for (VideoPO videoPO : videoPOS) {
            //获取视频id
            String id = videoPO.getId();
            videoPO.setLikeCount(10);//点赞数

        }
        return videoPOS;
    }

    @Override
    public List<VideoPO> queryByLikeVideoName(String key) {
        return videoDAO.queryByLikeVideoName(key);
    }

    @Override
    public List<VideoPO> queryCateVideoList(String cateId) {        //前台APP根据二级类别ID查所有
        return videoDAO.queryCateVideoList(cateId);
    }


}
