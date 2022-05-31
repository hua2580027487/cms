package com.hcm.cms.strategy.strategyenum;


public enum ParamEnum {
    // 膏方
    OINTMENT(4, "ointmentProcessPriceParam"),
    // 蜜丸
    HONEYPILL(5, "honeyPillProcessPriceParam"),
    // 水丸
    WATERPILL(6, "waterPillProcessPriceParam"),
    // 粉剂
    POWER(7, "powderProcessPriceParam");
    private int code;

    private String label;

    ParamEnum(int code, String label) {
        this.code = code;
        this.label = label;
    }

    public static String getNameByType(int type) {
        ParamEnum[] enums = ParamEnum.values();
        for (ParamEnum api : enums) {
            if (api.getCode() == type) {
                return api.getLabel();
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
