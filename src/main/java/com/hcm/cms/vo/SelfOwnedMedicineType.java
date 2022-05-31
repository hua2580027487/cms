package com.hcm.cms.vo;

import lombok.Data;

/**
 * @Author hcm
 * @Date 2022/3/23 19:30
 * @Version 1.0
 */
@Data
public class SelfOwnedMedicineType {
    private Integer medicineTypeId;

    private String medicineTypeName;

    private String medicineTypeImage;

    private Integer status;

    private String effect;

    private String medicineTypeNamePinyin;

    private Integer powderEnable;

    private Integer medicineTypeCategory;

    private Integer specialHandle;
}
