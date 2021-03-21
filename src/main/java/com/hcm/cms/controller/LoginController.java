package com.hcm.cms.controller;


import com.hcm.cms.common.ActiverUser;
import com.hcm.cms.common.Constast;
import com.hcm.cms.common.ResultObj;
import com.hcm.cms.common.WebUtils;
import com.hcm.cms.domain.LogLogin;
import com.hcm.cms.domain.User;
import com.hcm.cms.redis.RedisCache;
import com.hcm.cms.service.LogLoginService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 登陆前端控制器
 * </p>
 *
 * @author hcm
 * @since 2019-09-20
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LogLoginService logLoginService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedisCache redisCache;

    private ValueOperations<String, String> opsValue = null;
    @RequestMapping("/login")
    public ResultObj login(String loginname, String pwd) {
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken(loginname, pwd);
        opsValue = redisTemplate.opsForValue();
        String userName = opsValue.get("username");
        if(!StringUtils.isEmpty(userName)){
            User redisUser = new User();
            redisUser.setName(userName);
            redisCache.setCacheObject("username",redisUser, Constast.expireTime, TimeUnit.MINUTES);
        }
        try {
            subject.login(token);
            ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
            opsValue.set("username",activerUser.getUser().getName());
            WebUtils.getSession().setAttribute("user", activerUser.getUser());
            //记录登录日志
            LogLogin logLogin = new LogLogin();
            logLogin.setLoginname(activerUser.getUser().getName() + "-" + activerUser.getUser().getLoginname());
            logLogin.setLoginip(WebUtils.getRequest().getRemoteAddr());
            logLogin.setLogintime(new Date());
            logLoginService.save(logLogin);
            return ResultObj.LOGIN_SUCCESS;
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return ResultObj.LOGIN_ERROR_PASS;
        }
    }
}

