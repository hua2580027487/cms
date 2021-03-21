package com.hcm.cms.service;

import com.hcm.cms.domain.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author hcm
 * @since 2020-12-05
 */
public interface RoleService extends IService<Role> {
    /**
     * 根据角色ID查询当前角色拥有的所有的权限或菜单ID
     *
     * @param roleId
     * @return
     */
    List<Integer> queryRolePermissionIdsByRid(Integer roleId);

    /**
     * 保存角色和菜单权限之间的关系
     *
     * @param roleId
     * @param ids
     */
    void saveRolePermission(Integer roleId, Integer[] ids);

    /**
     * 查询当前用户拥有的角色ID集合
     *
     * @param id
     * @return
     */
    List<Integer> queryUserRoleIdsByUid(Integer id);

}
