package com.bbz.serviceimp;

import com.bbz.annotcation.AddLog;
import com.bbz.dao.UserDAO;
import com.bbz.entity.User;
import com.bbz.mc.City;
import com.bbz.mc.Mc;
import com.bbz.service.UserService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImp implements UserService {
    @Resource
    private UserDAO userDAO;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> selectPageService(Integer pageNow, Integer size) {  //分页
       Integer begin= (pageNow-1)*size;
       Integer end=pageNow*size;
        List<User> users = userDAO.selectPage(begin, end);
        return users;
    }

    @Override
    public Integer countService() {//总行数
        return userDAO.count();
    }

    @Override
    @AddLog("修改状态")
    public void update(User user) {//修改
        userDAO.updateByPrimaryKeySelective(user);
    }

    @Override
    public String add(User user) {
        user.setId(UUID.randomUUID().toString());
        user.setCreateDate(new Date());
        userDAO.insertSelective(user);
        return user.getId();
    }

    @Override
    public void Upload(MultipartFile picImg, String id, HttpServletRequest request) {
        System.out.println(picImg);
        String filename = picImg.getOriginalFilename();//获取到文件名字
        String extension = FilenameUtils.getExtension(filename);//获取文件后缀名
        String newFilName = UUID.randomUUID().toString().replace("-", "") + "." + extension;//拼接新的文件名字
        //b.当前上传文件以日期文件夹存放
        String dateDir = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String realPath = request.getSession().getServletContext().getRealPath("/imgs");
        File dir = new File(realPath, dateDir);//创建日期目录
        if (!dir.exists()) dir.mkdirs(); //不存在创建目录
        //上传到服务器
        try {
            picImg.transferTo(new File(dir, newFilName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        User user = new User();
        user.setId(id);
        user.setPicImg(realPath+"\\"+dateDir+"\\"+newFilName);
        userDAO.updateByPrimaryKeySelective(user);
    }

    @Override
    public List<Mc> selectMouth(String sex,Integer mouth) {
        return userDAO.selectMouth(sex,mouth);
    }

    @Override
    public List<City> selectCity(String sex) {
        return userDAO.selectCity(sex);
    }
}
