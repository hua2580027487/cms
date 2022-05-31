package com.hcm.cms.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author hcm
 * @Date 2022/5/25 15:11
 * @Version 1.0
 */
@Component
@Aspect
@Slf4j
public class RequestLogAspect {
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * com.hcm.cms.controller..*.*(..))")
    public void controllerLog() {
        log.info("切入点");
    }

    @Before("controllerLog()")
    public void before(JoinPoint joinPoint) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        log.info("方法描述:{}", getMethodDescription(joinPoint));
        log.info("请求路径:{}", request.getRequestURL());
//        Map<String, String[]> parameterMap = request.getParameterMap();
//        log.info("请求参数:{}", parameterMap != null ? JSONObject.fromObject(parameterMap) : null);
    }

    //使用环绕通知
    @Around("controllerLog()")
    public Object myLogger(ProceedingJoinPoint pjp) throws Throwable {
        startTime.set(System.currentTimeMillis());
        //使用ServletRequestAttributes请求上下文获取方法更多
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String className = pjp.getSignature().getDeclaringTypeName();
        String methodName = pjp.getSignature().getName();
        //使用数组来获取参数
        Object[] array = pjp.getArgs();
        ObjectMapper mapper = new ObjectMapper();
        //执行函数前打印日志
        log.info("调用前：{}：{},传递的参数为：{}", className, methodName, mapper.writeValueAsString(array));
        log.info("URL:{}", request.getRequestURL().toString());
        log.info("IP地址：{}", request.getRemoteAddr());
        //调用整个目标函数执行
        Object obj = pjp.proceed();
        //执行函数后打印日志
        log.info("调用后：{}：{},返回值为：{}", className, methodName, mapper.writeValueAsString(obj));
        log.info("耗时：{}ms", System.currentTimeMillis() - startTime.get());
        return obj;
    }

}
