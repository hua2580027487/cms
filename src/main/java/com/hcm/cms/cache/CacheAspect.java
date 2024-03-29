package com.hcm.cms.cache;

import com.hcm.cms.domain.Dept;
import com.hcm.cms.domain.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @Author 华春萌
 * @Date 2020/12/5 16:46
 * @Version 1.0
 */
@Aspect
@Component
@EnableAspectJAutoProxy
public class CacheAspect {

    /**
     * 日志出处
     */
    private Log log = LogFactory.getLog(CacheAspect.class);

    //申明一个缓存容器
    private Map<String, Object> CACHE_CONTAINER = new HashMap<>();

    //申明切面表达式
    private static final String POINTCUT_DEPT_UPDATE = "execution(* com.hcm.cms.service.impl.DeptServiceImpl.updateById(..))";
    private static final String POINTCUT_DEPT_GET = "execution(* com.hcm.cms.service.impl.DeptServiceImpl.getById(..))";
    private static final String POINTCUT_DEPT_DELETE = "execution(* com.hcm.cms.service.impl.DeptServiceImpl.removeById(..))";

    private static final String CACHE_DEPT_PROFIX = "dept:";

    /**
     * 查询切入
     *
     * @throws Throwable
     */
    @Around(value = POINTCUT_DEPT_GET)
    public Object cacheDeptGet(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Integer object = (Integer) joinPoint.getArgs()[0];
        //从缓存里面取
        Object res1 = CACHE_CONTAINER.get(CACHE_DEPT_PROFIX + object);
        if (res1 != null) {
            return res1;
        } else {
            Dept res2 = (Dept) joinPoint.proceed();
            CACHE_CONTAINER.put(CACHE_DEPT_PROFIX + res2.getId(), res2);
            return res2;
        }
    }

    /**
     * 更新切入
     *
     * @throws Throwable
     */
    @Around(value = POINTCUT_DEPT_UPDATE)
    public Object cacheDeptUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Dept deptVo = (Dept) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess) {
            Dept dept = (Dept) CACHE_CONTAINER.get(CACHE_DEPT_PROFIX + deptVo.getId());
            if (null == dept) {
                dept = new Dept();
            }
            BeanUtils.copyProperties(deptVo, dept);
            CACHE_CONTAINER.put(CACHE_DEPT_PROFIX + dept.getId(), dept);
        }
        return isSuccess;
    }

    /**
     * 删除切入
     *
     * @throws Throwable
     */
    @Around(value = POINTCUT_DEPT_DELETE)
    public Object cacheDeptDelete(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Integer id = (Integer) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess) {
            //删除缓存
            CACHE_CONTAINER.remove(CACHE_DEPT_PROFIX + id);
        }
        return isSuccess;
    }

    //申明切面表达式
    private static final String POINTCUT_USER_UPDATE = "execution(* com.hcm.cms.service.impl.UserServiceImpl.updateById(..))";
    private static final String POINTCUT_USER_ADD = "execution(* com.hcm.cms.service.impl.UserServiceImpl.save(..))";
    private static final String POINTCUT_USER_GET = "execution(* com.hcm.cms.service.impl.UserServiceImpl.getById(..))";
    private static final String POINTCUT_USER_DELETE = "execution(* com.hcm.cms.service.impl.UserServiceImpl.removeById(..))";

    private static final String CACHE_USER_PROFIX = "user:";

    /**
     * 用户添加切入
     *
     * @throws Throwable
     */
    @Around(value = POINTCUT_USER_ADD)
    public Object cacheUserAdd(ProceedingJoinPoint joinPoint) throws Throwable {
        // 取出第一个参数
        User object = (User) joinPoint.getArgs()[0];
        Boolean res = (Boolean) joinPoint.proceed();
        if (res) {
            CACHE_CONTAINER.put(CACHE_USER_PROFIX + object.getId(), object);
        }
        return res;
    }

    /**
     * 查询切入
     *
     * @throws Throwable
     */
    @Around(value = POINTCUT_USER_GET)
    public Object cacheUserGet(ProceedingJoinPoint joinPoint) throws Throwable {
        // 取出第一个参数
        Integer object = (Integer) joinPoint.getArgs()[0];
        // 从缓存里面取
        Object res1 = CACHE_CONTAINER.get(CACHE_USER_PROFIX + object);
        if (res1 != null) {
            log.info("已从缓存里面找到用户对象" + CACHE_USER_PROFIX + object);
            return res1;
        } else {
            User res2 = (User) joinPoint.proceed();
            CACHE_CONTAINER.put(CACHE_USER_PROFIX + res2.getId(), res2);
            log.info("未从缓存里面找到用户对象，去数据库查询并放到缓存" + CACHE_USER_PROFIX + res2.getId());
            return res2;
        }
    }

    /**
     * 更新切入
     *
     * @throws Throwable
     */
    @Around(value = POINTCUT_USER_UPDATE)
    public Object cacheUserUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        // 取出第一个参数
        User userVo = (User) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess) {
            User user = (User) CACHE_CONTAINER.get(CACHE_USER_PROFIX + userVo.getId());
            if (null == user) {
                user = new User();
            }
            BeanUtils.copyProperties(userVo, user);
            log.info("用户对象缓存已更新" + CACHE_USER_PROFIX + userVo.getId());
            CACHE_CONTAINER.put(CACHE_USER_PROFIX + user.getId(), user);
        }
        return isSuccess;
    }

    /**
     * 删除切入
     *
     * @throws Throwable
     */
    @Around(value = POINTCUT_USER_DELETE)
    public Object cacheUserDelete(ProceedingJoinPoint joinPoint) throws Throwable {
        // 取出第一个参数
        Integer id = (Integer) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess) {
            // 删除缓存
            CACHE_CONTAINER.remove(CACHE_USER_PROFIX + id);
            log.info("用户对象缓存已删除" + CACHE_USER_PROFIX + id);
        }
        return isSuccess;
    }
}
