package com.bbz.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.bbz.dao.UserDAO;
import com.bbz.entity.User;
import com.bbz.mc.City;
import com.bbz.mc.Mc;
import com.bbz.mc.Sc;
import com.bbz.service.UserService;
import com.bbz.util.AliyunUtil;
import com.bbz.util.ImageCodeUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    @Resource
    private UserDAO userDAO;
    @Resource
    private UserService userService;
    @RequestMapping("pageAll")
    @ResponseBody
    public HashMap<String,Object> pageAll(Integer page,Integer rows){//分页查询
        List<User> users = userService.selectPageService(page, rows);
        Integer count = userService.countService();
        Integer maxPage=count%rows==0 ? count/rows:count/rows+1;
        HashMap<String,Object> map=new HashMap<>();
        map.put("page",page);
        map.put("records",count);
        map.put("total",maxPage);
        map.put("rows",users);
        return map;
    }
    @RequestMapping("edit")
    @ResponseBody
    public String edit(User user, String oper) throws IOException {
        if(oper.equals("edit")){
            userService.update(user);
        }
        if(oper.equals("add")){
            String add = userService.add(user);
            return add;
        }
        return "";
    }
    @RequestMapping("phone")
    @ResponseBody
    public HashMap<String,Object> phone(String phoneNumbers){       //发送验证码
        String sign=null;
        HashMap<String, Object> map = new HashMap<>();
        //获得随机字符
        try {
            String securityCode = ImageCodeUtil.getSecurityCode();
            sign=AliyunUtil.sendPhoneMsg(phoneNumbers,securityCode);
            map.put("sign",sign);
            map.put("status","11");
        }catch (Exception e){
            e.printStackTrace();
            map.put("sign",sign);
            map.put("status","22");
        }
        return map;
    }
    @RequestMapping("Upload")
    @ResponseBody
    public void Upload(MultipartFile picImg , String id, HttpServletRequest request){
        userService.Upload(picImg,id,request);
    }

    @RequestMapping("EasyPoi")
    @ResponseBody
     public void Poiexport(){
            List<User> users = userDAO.selectAll();
            //导出设置的参数  参数:大标题,工作表名
            ExportParams exportParams = new ExportParams("YXAPP用户","用户");
            Workbook workbook = ExcelExportUtil.exportExcel(exportParams, User.class, users);
            try {
                workbook.write(new FileOutputStream(new File("E:\\YingxUser.xls")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    @RequestMapping("selectMouth")
    @ResponseBody
    public HashMap<String,Object> selectMouth(){
        HashMap<String,Object> map=new HashMap<>();
        ArrayList<Object> mouth = new ArrayList<>();
        ArrayList<Object> boy = new ArrayList<>();
        ArrayList<Object> girl = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            mouth.add(i+"月");
            Integer counts=0;
            List<Mc> mcs = userService.selectMouth("男", i);
            for (Mc mc : mcs) {
                counts = mc.getCounts();
            }
            boy.add(counts);
            Integer countss=0;
            List<Mc> girls = userService.selectMouth("女", i);
            for (Mc mc : girls) {
                countss = mc.getCounts();
            }
            girl.add(countss);
        }
        map.put("mouth",mouth);
        map.put("boys",boy);
        map.put("girls",girl);
        return map;
    }

    @RequestMapping("selectCity")
    @ResponseBody
    public ArrayList<Sc> selectCity(){
        ArrayList<Sc> arrayList= new ArrayList<>();
        List<City> boys = userService.selectCity("男");
        List<City> girls = userService.selectCity("女");
        arrayList.add(new Sc("小男孩",boys));
        arrayList.add(new Sc("小姑娘",girls));
        return arrayList;
    }





}
