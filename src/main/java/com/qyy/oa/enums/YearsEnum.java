package com.qyy.oa.enums;

/**
 * @author: qiyayu
 * @date: 2020-07-14 15:00
 * @description: 年假时间枚举
 */
public enum YearsEnum {
    /**
     * 五天时间
     */
    FIVNUMS(5, "五天假期"),

    /**
     * 十天假期
     */
    TENNUMS(10, "十天假期"),

    /**
     * 十五天假期
     */
    FIFTEENNUMS(15, "十五天假期");

    private int code;
    private String name;

    private YearsEnum(int code, String name) {
        this.code = code;
        this.name = name();
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }
}
