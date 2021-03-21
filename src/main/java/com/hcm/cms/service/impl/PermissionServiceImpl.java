package com.hcm.cms.service.impl;

import com.hcm.cms.domain.Permission;
import com.hcm.cms.mapper.PermissionMapper;
import com.hcm.cms.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hcm
 * @since 2020-11-06
 */
@Service
@Transactional
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Override
    public boolean removeById(Serializable id) {
        PermissionMapper permissionMapper = this.getBaseMapper();
        //根据权限或菜单id删除表和角色之间的关系
        permissionMapper.deleteRolePermissionByPid(id);
        return super.removeById(id);
    }
}
