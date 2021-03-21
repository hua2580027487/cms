package com.hcm.cms.service;

import com.hcm.cms.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author hcm
 * @since 2020-10-24
 */
public interface UserService extends IService<User> {
    /**
     * 保存用户和角色之间的关系
     *
     * @param uid
     * @param ids
     */
    void saveUserRole(Integer uid, Integer[] ids);
}
