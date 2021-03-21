package com.hcm.cms.vo;

import com.hcm.cms.domain.Permission;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author hcm
 * @Date 2020/11/6 16:03
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class PermissionVo extends Permission {
    private static final long serialVersionUID = 1L;

    private Integer page=1;
    private Integer limit=10;
}
