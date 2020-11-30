package com.bbz.app;

import com.bbz.common.CommonResult;
import com.bbz.po.CateGoryPO;
import com.bbz.po.VideoPO;
import com.bbz.service.CateGoryService;
import com.bbz.service.VideoService;
import com.bbz.util.AliyunUtil;
import com.bbz.util.ImageCodeUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("app")
public class AppController {

    @Resource
    private VideoService videoService;

    @Resource
    private CateGoryService cateGoryService;
    @RequestMapping("getPhoneCode")
    public Object getPhoneCode(String phone){       //发送手机验证码
        String securityCode = ImageCodeUtil.getSecurityCode();//生成随机数(验证码)
        String message=null;
        try {
             message = AliyunUtil.sendPhoneMsg(phone, securityCode);//(发送手机验证码)
            return new CommonResult().success(message,phone);
        }catch (Exception e){
            return new CommonResult().success(message);
        }
    }
    @RequestMapping("queryByReleaseTime")
    public CommonResult queryByReleaseTime(){       //前台APP查所有视频
        try {
            List<VideoPO> videoPOS = videoService.queryByReleaseTime();
            return new CommonResult().success(videoPOS);
        }catch (Exception e){
            return new CommonResult().failed();
        }
    }
    @RequestMapping("queryAllCategory")
    public CommonResult queryAllCategory(){          //前台APP查所有类别
        try {
            List<CateGoryPO> cateGoryPOS = cateGoryService.queryAllCategory();
            return new CommonResult().success(cateGoryPOS);
        }catch (Exception e){
            return new CommonResult().failed();
        }
    }
    @RequestMapping("queryByLikeVideoName")
    public CommonResult queryByLikeVideoName(String content){          //前台APP查所有类别
        try {
            List<VideoPO> videoPOS = videoService.queryByLikeVideoName(content);
            return new CommonResult().success(videoPOS);
        }catch (Exception e){
            return new CommonResult().failed();
        }
    }
    @RequestMapping("queryCateVideoList")
    public CommonResult queryCateVideoList(String cateId){          //前台APP根据二级类别ID查所有
        try {
            List<VideoPO> videoPOS = videoService.queryCateVideoList(cateId);
            System.out.println(videoPOS);
            return new CommonResult().success(videoPOS);
        }catch (Exception e){
            return new CommonResult().failed();
        }
    }


}
