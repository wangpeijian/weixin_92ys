package com.ys.config;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by libin on 2017/4/9.
 */
@Aspect
@Component
@Configuration
public class LogAOP {

    private final static Logger logger = LoggerFactory.getLogger(LogAOP.class);


    @Pointcut("execution(public * com.ys.mvc.controller.app.*.*(..))")
    public void appLog() {
    }

    @Before("appLog()")
    public void appBefore(JoinPoint joinPoint) {
        this.before(joinPoint);
    }

    @After("appLog()")
    public void appAfter() {
        //logger.info("End");
    }

    @AfterReturning(returning = "object", pointcut = "appLog()")
    public void appAfterReturning(Object object) {
        this.AfterReturning(object);
    }


    @Pointcut("execution(public * com.ys.mvc.controller.oa.*.*(..))")
    public void oaLog() {
    }

    @Before("oaLog()")
    public void oaBefore(JoinPoint joinPoint) {
        this.before(joinPoint);
    }

    @After("oaLog()")
    public void oaAfter() {
        //logger.info("End");
    }

    @AfterReturning(returning = "object", pointcut = "oaLog()")
    public void oaAfterReturning(Object object) {
        this.AfterReturning(object);
    }



    private void before(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(attributes == null){
            return;
        }
        HttpServletRequest request = attributes.getRequest();

        logger.info("--------------------request start--------------------");

        //user
        logger.info("userId={}",request.getHeader("userId") );
        //url
        logger.info("request url={}", request.getRequestURL());
        //method
        logger.info("method={}", request.getMethod());
        //ip
        logger.info("ip={}", request.getRemoteAddr());
        //类方法
        logger.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        //参数
        String args = "";
        try{
            args = JSON.toJSON(joinPoint.getArgs()).toString();
        }catch (Exception e){

        }
        logger.info("args={}", args);
    }

    private void AfterReturning(Object object){
        if(object != null){
            String response = "";
            try{
                response = JSON.toJSON(JSON.toJSON(object)).toString();
            }catch (Exception e){

            }
            logger.info("response={}", response);
            logger.info("--------------------request end--------------------");
        }
    }
}
