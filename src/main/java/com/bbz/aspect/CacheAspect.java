package com.bbz.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;

@Configuration
@Aspect
public class CacheAspect {
    @Resource
    RedisTemplate redisTemplate; //存储对象
    @Resource
    StringRedisTemplate stringRedisTemplate;//对字符串比较友好,不能存储对象

    //添加缓存
    @Around("@annotation(com.bbz.annotcation.AddCache)")
    public Object addCache(ProceedingJoinPoint proceedingJoinPoint){
        System.out.println("环绕通知");
        StringRedisSerializer serializer = new StringRedisSerializer();//使用序列化策略
        redisTemplate.setKeySerializer(serializer);
        redisTemplate.setHashKeySerializer(serializer);
        StringBuilder stringBuilder = new StringBuilder();
        //获取类名
        String classname = proceedingJoinPoint.getTarget().getClass().getName();
        stringBuilder.append(classname);
        //获取方法名
        String methodName = proceedingJoinPoint.getSignature().getName();
        stringBuilder.append(methodName);
        //获取参数
        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg : args) {
            stringBuilder.append(arg);
        }
        String key = stringBuilder.toString();
        //判断key是否存在
        Boolean aBoolean = redisTemplate.hasKey(key);
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Object result =null;
        if(aBoolean){
            result=valueOperations.get(key);
        }else {
            try {
                result= proceedingJoinPoint.proceed();//放行
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            valueOperations.set(key,result);//存入redis缓存
        }
        return result;
    }
    @After("@annotation(com.bbz.annotcation.DelCache)")
    public void delCache(JoinPoint joinPoint){

        System.out.println("后置通知:");

        //获取类的全限定名
        String className = joinPoint.getTarget().getClass().getName();

        //清除缓存
        redisTemplate.delete(className);

    }

}
