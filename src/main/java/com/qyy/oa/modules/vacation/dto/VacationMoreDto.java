package com.qyy.oa.modules.vacation.dto;

import com.qyy.oa.modules.vacation.entity.VacationEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author: qiyayu
 * @date: 2020-07-08 14:31
 * @description: 请假
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class VacationMoreDto extends VacationEntity implements Serializable {
    private static final long serialVersionUID = 439171636973282588L;

    /**
     * 页数
     */
    private Integer pageNum = 1;

    /**
     * 数量
     */
    private Integer pageLimit = 20;

    /**
     * 员工编号
     */
    private String code;

    /**
     * 所属部门
     */
    private String departmentName;

    /**
     * 请假类型
     */
    private String vacationTypeName;

    /**
     * 用户姓名
     */
    private String userName;
}
