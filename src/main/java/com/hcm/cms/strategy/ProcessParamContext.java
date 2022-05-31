package com.hcm.cms.strategy;

import com.hcm.cms.vo.ProcessPriceParamResponseVO;

/**
 * @Author hcm
 * @Date 2022/4/6 14:17
 * @Version 1.0
 */
public class ProcessParamContext {
    private ProcessParamStrategy processParamStrategy;

    public ProcessParamContext(ProcessParamStrategy processParamStrategy) {
        this.processParamStrategy = processParamStrategy;
    }

    public ProcessPriceParamResponseVO getParam(Integer sommId) {
        return processParamStrategy.getProcessPriceParam(sommId);
    }
}
