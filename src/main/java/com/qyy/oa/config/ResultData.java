package com.qyy.oa.config;

/**
 * @author: qiyayu
 * @date: 2020/7/7 10:40
 * @description: 返回信息实体
 */
public class ResultData {
    private Integer code;
    private String message;
    private Object data;

    public static ResultData error(String message) {
        return new ResultData(500, message);
    }

    public static ResultData error(String message, Object o) {
        return new ResultData(500, message, o);
    }

    public static ResultData success(String message) {
        return new ResultData(200, message);
    }

    public static ResultData success(Object o) {
        return new ResultData(200, "success", o);
    }

    public static ResultData success(String message, Object o) {
        return new ResultData(200, message, o);
    }

    public ResultData(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public ResultData(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultData(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
