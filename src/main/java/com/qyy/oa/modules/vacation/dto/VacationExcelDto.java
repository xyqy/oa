package com.qyy.oa.modules.vacation.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author: qiyayu
 * @date: 2020-07-09 20:33
 * @description: 请假导出表格
 */
@Data
public class VacationExcelDto {


    /**
     * id
     */
    @ExcelProperty(value = "序号", index = 0)
    private Integer id;

    /**
     * 用户姓名
     */
    @ExcelProperty(value = "员工姓名", index = 1)
    private String userName;

    /**
     * 员工编号
     */
    @ExcelProperty(value = "员工编号", index = 2)
    private String code;

    /**
     * 所属部门
     */
    @ExcelProperty(value = "部门名称", index = 3)
    private String departmentName;

    /**
     * 请假类型
     */
    @ExcelProperty(value = "请假类型", index = 4)
    private String vacationTypeName;

    /**
     * 开始时间
     */
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    @ExcelProperty(value = "开始时间", index = 5)
    private Date startTime;

    /**
     * 结束时间
     */
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    @ExcelProperty(value = "结束时间", index = 6)
    private Date endTime;

    /**
     * 请假天数
     */
    @ExcelProperty(value = "请假天数", index = 7)
    private Double days;

    /**
     * 请假状态，默认0：审批中，1，同意，2不通过
     */
    @ExcelProperty(value = "状态", index = 8)
    private String state;

}
