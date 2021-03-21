package com.hcm.cms.mapper;

import com.hcm.cms.domain.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.io.Serializable;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hcm
 * @since 2020-11-06
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    void deleteRolePermissionByPid(Serializable id);
}
