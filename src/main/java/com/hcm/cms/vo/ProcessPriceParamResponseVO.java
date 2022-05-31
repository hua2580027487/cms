package com.hcm.cms.vo;

import lombok.Data;

/**
 * @Author hcm
 * @Date 2022/4/6 10:19
 * @Version 1.0
 */
@Data
public class ProcessPriceParamResponseVO {
    private Integer startWight;//起始重量
    private Integer startMoney;//起始价
    private Integer percentMoney;//阶梯价
    private Integer percentWight;//阶梯重量
    private Integer wavedWigth;//浮动值
    private Integer accessoriesNum;// 份数/百分比
    private Integer pId;
}
