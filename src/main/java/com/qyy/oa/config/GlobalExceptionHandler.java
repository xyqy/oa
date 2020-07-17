package com.qyy.oa.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: qiyayu
 * @date: 2020-07-07 17:29
 * @description: 统一异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 返回的Map对象会被@ResponseBody注解转换为JSON数据返回
     *
     * @return ResultData
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handleException(HttpServletRequest request, Exception e) {
        System.out.println("###出现异常！");
        System.out.println(e.getMessage());
        System.out.println("###出现异常！");
        return ResultData.error(e.getMessage());
    }

}
