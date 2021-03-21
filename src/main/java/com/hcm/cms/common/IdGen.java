//package com.hcm.cms.common;
//
//import org.activiti.engine.impl.cfg.IdGenerator;
//
//import java.util.UUID;
//
///**
// * @Author hcm
// * @Date 2020/12/28 21:31
// * @Version 1.0
// */
//public class IdGen implements IdGenerator {
//
//    /**
//     * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
//     */
//    public static String uuid() {
//        return UUID.randomUUID().toString().replaceAll("-", "");
//    }
//
//    /**
//     * activity ID生成
//     *
//     * @return
//     */
//    @Override
//    public String getNextId() {
//        return IdGen.uuid();
//    }
//}
