package com.hcm.cms.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Author hcm
 * @Date 2022/5/25 17:51
 * @Version 1.0
 */
@Aspect
@Component
public class AspectTest {
    @Pointcut("execution(* com.hcm.cms.controller..*.*(..))")
    public void hcmPointCut() {

    }

    @Before("hcmPointCut()")
    public void beforeNotify() {
        System.out.println("@Before 前置通知");
    }

    @After("hcmPointCut()")
    public void afterNotify() {
        System.out.println("@After 后置通知");
    }

    @AfterReturning("hcmPointCut()")
    public void afterReturningNotify() {
        System.out.println("@AfterReturning 前置通知");
    }

    @AfterThrowing("hcmPointCut()")
    public void afterThrowingNotify() {
        System.out.println("@AfterThrowing 异常通知");
    }

    @Around("hcmPointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object retVal;
        System.out.println("@Around 环绕通知之前");
        retVal = proceedingJoinPoint.proceed();
        return null;
//        System.out.println("@Around 环绕通知之后");
//        return retVal;
    }
}
