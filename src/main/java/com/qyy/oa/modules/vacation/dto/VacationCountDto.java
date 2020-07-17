package com.qyy.oa.modules.vacation.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author: qiyayu
 * @date: 2020-07-10 11:33
 * @description: 请假统计实体
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class VacationCountDto implements Serializable {

    private static final long serialVersionUID = 2173197044386283132L;

    /**
     * 月份时间
     */
    private String monthTime;
    /**
     * 部门id
     */
    private Integer departmentId;

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 请假类型id
     */
    private Integer vacationTypeId;

    /**
     * 请假类型数量
     */
    private Integer vacationTypeCount;

    /**
     * 请假类型名称
     */
    private String vacationTypeName;

    /**
     * 用户数量
     */
    private Integer userCount;

    /**
     * 请假天数
     */
    private Double days;

    /**
     * 用户姓名
     */
    private String userName;
}
