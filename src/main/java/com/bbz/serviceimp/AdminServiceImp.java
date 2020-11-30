package com.bbz.serviceimp;

import com.bbz.dao.AdminDAO;
import com.bbz.entity.Admin;
import com.bbz.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Service
@Transactional
public class AdminServiceImp implements AdminService {
    @Resource
    private AdminDAO adminDAO;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    //登录
    public HashMap<String,Object> login(Admin admin, String code, HttpServletRequest request) {
        HashMap<String,Object> map=new HashMap<>();
        String servCode = (String) request.getSession().getAttribute("ServCode");//获取验证码
        if (servCode.equals(code)){//判断验证码是否正确
            Admin admin1 = adminDAO.login(admin.getUserName());
            request.getSession().setAttribute("admin",admin1);
            if(admin1==null){
                map.put("message","用户名不存在...");
                map.put("status","201");
            }else if (admin.getPassWord().equals(admin1.getPassWord())){
                map.put("message","登录成功...");
                map.put("status","200");
            }else {
                map.put("message","密码错误...");
                map.put("status","201");
            }
        }else {
            map.put("message","验证码错误...");
            map.put("status","201");
        }

        return map;
    }
}
