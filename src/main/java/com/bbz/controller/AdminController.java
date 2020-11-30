package com.bbz.controller;

import com.bbz.entity.Admin;
import com.bbz.service.AdminService;
import com.bbz.util.ImageCodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

@Controller
@RequestMapping("admin")
public class AdminController {
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);
    @Resource
    private AdminService adminService;

    @RequestMapping("code")
    //验证码
    public String creatImg(HttpServletResponse response,HttpServletRequest request) throws IOException {

        //获得随机字符
        String securityCode = ImageCodeUtil.getSecurityCode();
        //打印随机字符
        System.out.println("===="+securityCode);
        //生成图片
        BufferedImage image = ImageCodeUtil.createImage(securityCode);
        //将生成的验证码图片保存到浏览器
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "png",out);
        HttpSession session = request.getSession();
        session.setAttribute("ServCode",securityCode);
        return null;
    }
    //登录
    @RequestMapping("login")
    @ResponseBody
    public HashMap<String,Object> login(Admin admin, String code, HttpServletRequest request){
        return adminService.login(admin,code,request);
    }
    //退出
    @RequestMapping("end")
    public String end(HttpServletRequest request){
        HttpSession session = request.getSession();
        //清除session作用域
        session.invalidate();
        return "redirect:/login/login.jsp";

    }
}
