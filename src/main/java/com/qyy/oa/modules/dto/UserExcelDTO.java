package com.qyy.oa.modules.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

/**
 * @author: qiyayu
 * @date: 2020-07-09 16:50
 * @description: 用户的导出表格
 */
@Data
public class UserExcelDTO {
    /**
     * id
     */
    @ExcelProperty(value = "序号", index = 0)
    private Integer id;

    /**
     * 用户姓名
     */
    @ExcelProperty(value = "姓名", index = 1)
    private String name;

    /**
     * 用户账号
     */
    @ExcelProperty(value = "账号", index = 2)
    private String account;

    /**
     * 用户编号
     */
    @ExcelProperty(value = "用户编号", index = 3)
    private String code;


    /**
     * 部门名称
     */
    @ExcelProperty(value = "部门名称", index = 4)
    private String  departmentName;

    /**
     * 手机号
     */
    @ExcelProperty(value = "手机号", index = 5)
    private String tel;

    /**
     * 用户邮件
     */
    @ExcelProperty(value = "邮箱", index = 6)
    private String email;

}
