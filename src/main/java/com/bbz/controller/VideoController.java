package com.bbz.controller;

import com.bbz.entity.Video;
import com.bbz.service.VideoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("video")
public class VideoController {
    @Resource
    private VideoService videoService;
    @RequestMapping("page")
    @ResponseBody
    public HashMap<String,Object> selectA(Integer page, Integer rows){                   //分页查
        List<Video> videos = videoService.selectPageService( page, rows);
        Integer count = videoService.countService();
        Integer maxPage=count%rows==0 ? count/rows:count/rows+1;
        HashMap<String,Object> map=new HashMap<>();
        map.put("page",page);
        map.put("records",count);
        map.put("total",maxPage);
        map.put("rows",videos);
        return map;
    }
    @RequestMapping("edit")
    @ResponseBody
    public String edit(Video video, MultipartFile videoPath,String oper){
        System.out.println(videoPath);
        if(oper.equals("add")){
            String add = videoService.add(video);
            return add;
        }
        if(oper.equals("edit")){
            String id = videoService.update(video);
            return id;
        }
        if(oper.equals("del")){
            videoService.delete(video);
        }
        return "";
    }
    @RequestMapping("headUpload")
    @ResponseBody
    public void headUpload(MultipartFile videoPath ,String id){
        videoService.AliyunUpload(videoPath,id);
    }

    @RequestMapping("headUpdate")
    @ResponseBody
    public void headUpdate(MultipartFile videoPath ,String id){
        videoService.AliyunUpdate(videoPath,id);
    }
}
