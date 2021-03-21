package com.hcm.cms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcm.cms.common.DataGridView;
import com.hcm.cms.common.ResultObj;
import com.hcm.cms.domain.LogLogin;
import com.hcm.cms.domain.LoginfoVo;
import com.hcm.cms.service.LogLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hcm
 * @since 2020-11-06
 */
@RestController
@RequestMapping("/logLogin")
public class LogLoginController {

    @Autowired
    private LogLoginService logLoginService;

    /**
     * 全查询
     */
    @RequestMapping("loadAllLoginfo")
    public DataGridView loadAllLoginfo(LoginfoVo loginfoVo) {
        IPage<LogLogin> page = new Page<>(loginfoVo.getPage(), loginfoVo.getLimit());
        QueryWrapper<LogLogin> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNoneBlank(loginfoVo.getLoginname()), "loginname", loginfoVo.getLoginname());
        queryWrapper.like(StringUtils.isNoneBlank(loginfoVo.getLoginip()), "loginip", loginfoVo.getLoginip());
        queryWrapper.ge(loginfoVo.getStartTime() != null, "logintime", loginfoVo.getStartTime());
        queryWrapper.le(loginfoVo.getEndTime() != null, "logintime", loginfoVo.getEndTime());
        queryWrapper.orderByDesc("logintime");
        this.logLoginService.page(page, queryWrapper);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    /**
     * 删除
     */
    @RequestMapping("deleteLogininfo")
    public ResultObj deleteLogininfo(Integer id) {
        try {
            this.logLoginService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除
     */
    @RequestMapping("batchDeleteLogininfo")
    public ResultObj batchDeleteLogininfo(LoginfoVo loginfoVo) {
        try {
            Collection<Serializable> idList = new ArrayList<Serializable>();
            for (Integer id : loginfoVo.getIds()) {
                idList.add(id);
            }
            this.logLoginService.removeByIds(idList);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
