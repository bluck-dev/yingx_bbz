package com.bbz.yingx_bbz;

import com.bbz.dao.*;
import com.bbz.entity.Admin;
import com.bbz.entity.Video;
import com.bbz.po.VideoPO;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class YingxBbzApplicationTests {
    private static final Logger log = LoggerFactory.getLogger(YingxBbzApplicationTests.class);
    @Resource
    private AdminDAO adminDAO;
    @Resource
    private UserDAO userDAO;
    @Resource
    private CateGoryDAO cateGoryDAO;
    @Resource
    private VideoDAO videoDAO;
    @Resource
    private LogDAO logDAO;


    @Test
    void contextLoads() {
//        List<Admin> admins = adminDAO.selectAll();
//        admins.forEach(admin -> System.out.println(admin));
        Admin admin = new Admin();
        admin.setUserName("1");
        Admin admin1 = adminDAO.selectOne(admin);
        System.out.println(admin1);
    }

    @Test
    public void test(){
//        List<CateGory> gories = cateGoryDAO.selectB("1",0, 2 );
//        gories.forEach(cateGory -> System.out.println(cateGory));
//        List<CateGory> cateGories = cateGoryDAO.selectA(0, 1);
//        cateGories.forEach(cateGory -> System.out.println(cateGory));
//        System.out.println(cateGoryDAO.countB());
//        List<CateGory> gories = cateGoryDAO.selectA(0, 2);
//        gories.forEach(cateGory -> System.out.println(cateGory));
        System.out.println(cateGoryDAO.countB("31214c91-5ef3-4f2e-bd7a-4aa1e318137f"));
    }
    @Test
    public void videoTest() {
//        List<Video> videos = videoDAO.selectPage(0, 2);
//        videos.forEach(video -> System.out.println(video));
        Video video = videoDAO.seletOne("ce1d3089-fbcd-416a-9ec5-c635c5f93c5b");
        video.setVideoPath("222");
        videoDAO.update(video);

    }
    @Test
    public void logTest(){
//        List<Log> logs = logDAO.selectPage(0, 1);
//        logs.forEach(log1 -> System.out.println(log1));
//        System.out.println(logDAO.count());
//        List<VideoPO> videoPOS = videoDAO.queryByLikeVideoName("p");
////        for (VideoPO videoPO : videoPOS) {
////            System.out.println(videoPO);
        List<VideoPO> videoPOS = videoDAO.queryCateVideoList("3c861f61-4bcf-4e14-9148-890aea00487e");
        for (VideoPO videoPO : videoPOS) {
            System.out.println(videoPO);
        }
    }





}
