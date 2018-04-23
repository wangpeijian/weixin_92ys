package spring.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by peijian.wang on 2017/3/21.
 */

@Component
@Aspect
public class LogAspect {
    private static Logger log = Logger.getLogger(LogAspect.class);

    LogAspect(){
        log.debug("----------启动了呀！！！！！！！----------");
    }

    //切点
    @Pointcut("execution(* mvc.controller.*API.*(..))")
    public void controllerPointcut(){
    }

    @Before("controllerPointcut()")
    public void before(JoinPoint jp){
        log.debug("----------前置通知----------");
        log.debug(jp.getSignature().getName());
    }

    @After("controllerPointcut()")
    public void after(JoinPoint jp){
        log.debug("----------最终通知----------");
    }

}
