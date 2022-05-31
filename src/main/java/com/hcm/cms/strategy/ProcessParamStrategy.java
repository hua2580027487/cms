package com.hcm.cms.strategy;

import com.hcm.cms.vo.ProcessPriceParamResponseVO;

/**
 * @Author hcm
 * @Date 2022/4/6 11:39
 * @Version 1.0
 */
public interface ProcessParamStrategy {
    ProcessPriceParamResponseVO getProcessPriceParam(Integer sommId);
}
