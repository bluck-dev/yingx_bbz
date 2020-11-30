package com.bbz.controller;

import com.bbz.entity.Log;
import com.bbz.service.LogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("log")
public class LogController {
    @Resource
    private LogService logService;
    @ResponseBody
    @RequestMapping("pageAll")
    public HashMap<String, Object> pageAll(Integer page,Integer rows){
        List<Log> logs = logService.selectPageService(page, rows);
        Integer count = logService.count();
        Integer maxPage=count%rows==0 ? count/rows:count/rows+1;
        HashMap<String,Object> map=new HashMap<>();
        map.put("page",page);
        map.put("records",count);
        map.put("total",maxPage);
        map.put("rows",logs);
        return map;
    }
}
