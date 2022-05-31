package com.hcm.cms.strategy.impl;

import com.hcm.cms.strategy.ProcessParamStrategy;
import com.hcm.cms.vo.ProcessPriceParamResponseVO;
import org.springframework.stereotype.Service;

/**
 * @Author hcm
 * @Date 2022/4/6 13:43
 * @Version 1.0
 * 蜜丸加工费参数
 */
@Service("honeyPillProcessPriceParam")
public class HoneyPillProcessPriceParam implements ProcessParamStrategy {
//    @Resource
//    private UserMapper userMapper;

    /**
     * @return
     */
    @Override
    public ProcessPriceParamResponseVO getProcessPriceParam(Integer sommId) {
//        userMapper.selectById("1");
        ProcessPriceParamResponseVO processPriceParamResponseVO = new ProcessPriceParamResponseVO();
        processPriceParamResponseVO.setAccessoriesNum(1);
        processPriceParamResponseVO.setPercentMoney(2);
        processPriceParamResponseVO.setStartMoney(2);
        return processPriceParamResponseVO;
    }
}
