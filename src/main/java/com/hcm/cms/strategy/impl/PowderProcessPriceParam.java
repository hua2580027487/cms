package com.hcm.cms.strategy.impl;

import com.hcm.cms.strategy.ProcessParamStrategy;
import com.hcm.cms.vo.ProcessPriceParamResponseVO;
import org.springframework.stereotype.Service;

/**
 * @Author hcm
 * @Date 2022/4/6 13:43
 * @Version 1.0
 * 粉剂加工费参数
 */
@Service("powderProcessPriceParam")
public class PowderProcessPriceParam implements ProcessParamStrategy {

    /**
     * @return
     */
    @Override
    public ProcessPriceParamResponseVO getProcessPriceParam(Integer sommId) {

        return null;
    }
}
