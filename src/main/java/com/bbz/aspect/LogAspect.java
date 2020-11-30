package com.bbz.aspect;

import com.bbz.annotcation.AddLog;
import com.bbz.dao.LogDAO;
import com.bbz.entity.Admin;
import com.bbz.entity.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

@Configuration
@Aspect
public class LogAspect {
    private static final Logger log1 = LoggerFactory.getLogger(LogAspect.class);
    @Resource
    HttpServletRequest request;//获取当前操作的用户
    @Resource
    LogDAO logDAO;

    @Around("@annotation(com.bbz.annotcation.AddLog)")
    public Object addLog(ProceedingJoinPoint proceedingJoinPoint){
        Admin admin = (Admin) request.getSession().getAttribute("admin");//获取用户数据
        String methodName = proceedingJoinPoint.getSignature().getName();//获取方法名
        MethodSignature signature= (MethodSignature) proceedingJoinPoint.getSignature();
        String value = signature.getMethod().getAnnotation(AddLog.class).value();//获取方法,获取注解,获取注解对应的属性值
        String message=null;//定义状态
        Object result =null;
        try {
            result=proceedingJoinPoint.proceed();
            String s = result.toString();
            message="success";
        } catch (Throwable throwable) {
            message="error";
        }
        Log log = new Log();
        log.setId(UUID.randomUUID().toString());
        log.setUserName(admin.getUserName());
        log.setOperationTime(new Date());
        log.setOper(methodName+"("+value+")");
        log.setStatus(message);
        log1.debug("用户操作:   {}"+log);
        logDAO.insertSelective(log);//数据入库
        return result;
    }
}
