package com.qyy.oa.enums;

/**
 * @author: qiyayu
 * @date: 2020-07-14 15:00
 * @description: true false
 */
public enum BooleanEnum {
    /**
     * 对
     */
    TRUE("true", "true"),

    /**
     * 十天假期
     */
    FALSE("false", "false");

    private String flag;
    private String name;

    private BooleanEnum(String flag, String name) {
        this.flag = flag;
        this.name = name();
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
