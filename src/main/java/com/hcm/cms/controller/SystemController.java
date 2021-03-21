package com.hcm.cms.controller;

import com.hcm.cms.common.Constast;
import com.hcm.cms.common.ResultObj;
import com.hcm.cms.common.WebUtils;
import com.hcm.cms.domain.User;
import com.hcm.cms.redis.RedisCache;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @Author hcm
 * @Date 2020/10/24 11:36
 * @Version 1.0
 */
@Controller
@RequestMapping("sys")
public class SystemController {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 跳转到登录页
     */
    @RequestMapping("toLogin")
    public String toLogin() {
//        String userName = redisTemplate.opsForValue().get("username");
//        if (!StringUtils.isEmpty(userName)) {
//            User redisUser = new User();
//            redisUser.setName(userName);
//            WebUtils.getSession().setAttribute("user", redisUser);
//            return "system/index/index";
//        } else {
//            return "system/index/login";
//        }
        return "system/index/login";
    }

    /**
     * 退出登录
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        WebUtils.getSession().invalidate();
        String userName = redisTemplate.opsForValue().get("username");
        if (!StringUtils.isEmpty(userName)) {
            redisCache.deleteObject("username");
        }
        return "system/index/login";
    }

    /**
     * 跳转到首页
     */
    @RequestMapping("index")
    public String index() {
        return "system/index/index";
    }

    /**
     * 跳转到工作台
     */
    @RequestMapping("deskManager")
    public String toDeskManager() {
        return "system/index/deskManager";
    }

    /**
     * 跳转到日志管理
     */
    @RequestMapping("toLoginfoManager")
    public String toLoginfoManager() {
        return "system/loginfo/loginfoManager";
    }

    /**
     * 跳转到公告管理
     */
    @RequestMapping("toNoticeManager")
    public String toNoticeManager() {
        return "system/notice/noticeManager";
    }

    /**
     * 跳转到部门管理
     */
    @RequestMapping("toDeptManager")
    public String toDeptManager() {
        return "system/dept/deptManager";
    }

    /**
     * 跳转到部门管理-left
     */
    @RequestMapping("toDeptLeft")
    public String toDeptLeft() {
        return "system/dept/deptLeft";
    }

    /**
     * 跳转到部门管理-right
     */
    @RequestMapping("toDeptRight")
    public String toDeptRight() {
        return "system/dept/deptRight";
    }

    /**
     * 跳转到菜单管理
     */
    @RequestMapping("toMenuManager")
    public String toMenuManager() {
        return "system/menu/menuManager";
    }

    /**
     * 跳转到菜单管理-left
     */
    @RequestMapping("toMenuLeft")
    public String toMenuLeft() {
        return "system/menu/menuLeft";
    }


    /**
     * 跳转到菜单管理--right
     */
    @RequestMapping("toMenuRight")
    public String toMenuRight() {
        return "system/menu/menuRight";
    }

    /**
     * 跳转到权限管理
     */
    @RequestMapping("toPermissionManager")
    public String toPermissionManager() {
        return "system/permission/permissionManager";
    }

    /**
     * 跳转到权限管理-left
     */
    @RequestMapping("toPermissionLeft")
    public String toPermissionLeft() {
        return "system/permission/permissionLeft";
    }


    /**
     * 跳转到权限管理--right
     */
    @RequestMapping("toPermissionRight")
    public String toPermissionRight() {
        return "system/permission/permissionRight";
    }

    /**
     * 跳转到角色管理
     */
    @RequestMapping("toRoleManager")
    public String toRoleManager() {
        return "system/role/roleManager";
    }

    /**
     * 跳转到用户管理
     */
    @RequestMapping("toUserManager")
    public String toUserManager() {
        return "system/user/userManager";
    }
}
