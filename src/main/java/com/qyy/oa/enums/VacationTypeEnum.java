package com.qyy.oa.enums;

/**
 * @author: qiyayu
 * @date: 2020-07-15 10:15
 * @description: 请假类型枚举
 */
public enum VacationTypeEnum {

    /**
     * 事假
     */
    WORKVACATION(1, "事假"),

    /**
     * 病假
     */
    SICKVACATION(2, "病假"),

    /**
     * 年假
     */
    YEARVACATION(3, "年假");

    private int code;
    private String name;

    VacationTypeEnum(int code, String name) {
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
