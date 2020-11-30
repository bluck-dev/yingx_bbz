package com.bbz.controller;

import com.alibaba.druid.util.StringUtils;
import com.bbz.entity.CateGory;
import com.bbz.service.CateGoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("category")
public class CateGoryController {
    private static final Logger log = LoggerFactory.getLogger(CateGoryController.class);
    @Resource
    private CateGoryService cateGoryService;
    @RequestMapping("page")
    @ResponseBody
    public HashMap<String,Object> selectA(Integer page,Integer rows){                   //一级分页查
        List<CateGory> cateGories = cateGoryService.selectA( page, rows);
        Integer count = cateGoryService.countSetvice();
        Integer maxPage=count%rows==0 ? count/rows:count/rows+1;
        HashMap<String,Object> map=new HashMap<>();
        map.put("page",page);
        map.put("records",count);
        map.put("total",maxPage);
        map.put("rows",cateGories);
        cateGories.forEach(cateGory -> System.out.println(cateGory));
        return map;
    }
    @RequestMapping("edit")
    @ResponseBody
    public HashMap<String,Object> edit(CateGory cateGory,String oper){              //操作
        HashMap<String,Object> map=new HashMap<>();
        log.debug("当前方法: {}"+oper);
        //添加
        if(StringUtils.equals("add",oper)){
            cateGoryService.add(cateGory);
        }
        //修改
        if(StringUtils.equals("edit",oper)){
            cateGoryService.update(cateGory);
        }
        //删除
        if(StringUtils.equals("del",oper)){
            String message = cateGoryService.delete(cateGory);
            map.put("message",message);
        }
        return map;
    }
    @RequestMapping("page2")
    @ResponseBody
    public HashMap<String,Object> selectB(String id,Integer page,Integer rows){                   //二级分页查
        List<CateGory> cateGories = cateGoryService.selectB(id,page, rows);
        Integer count = cateGoryService.countB(id);
        Integer maxPage=count%rows==0 ? count/rows:count/rows+1;
        HashMap<String,Object> map=new HashMap<>();
        map.put("page",page);
        map.put("records",count);
        map.put("total",maxPage);
        map.put("rows",cateGories);
        cateGories.forEach(cateGory -> System.out.println(cateGory));
        return map;
    }
    @RequestMapping("selectOnelevels")
    @ResponseBody
    public void selectOnelevels(HttpServletResponse response) throws IOException {          //查所有一级
        List<CateGory> cateGories = cateGoryService.selectOnelevels();
        //拼接
        StringBuilder builder = new StringBuilder();
        builder.append("<select>");
        //遍历 构建下拉标签 option
        cateGories.forEach(cateGorie -> {
            builder.append("<option value="+cateGorie.getId()+">"+cateGorie.getCateName()+"</option>");
        });
        builder.append("/select<>");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.getWriter().print(builder.toString());
    }
    @RequestMapping("selectTwolevels")
    @ResponseBody
    public void selectTwolevels(HttpServletResponse response) throws IOException {          //查所有一级
        List<CateGory> cateGories = cateGoryService.selectTwolevels();
        //拼接
        StringBuilder builder = new StringBuilder();
        builder.append("<select>");
        //遍历 构建下拉标签 option
        cateGories.forEach(cateGorie -> {
            builder.append("<option value="+cateGorie.getId()+">"+cateGorie.getCateName()+"</option>");
        });
        builder.append("/select<>");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.getWriter().print(builder.toString());
    }


}
