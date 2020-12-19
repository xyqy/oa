package com.qyy.oa.modules.dto;

import com.qyy.oa.modules.entity.DepartmentEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author: qiyayu
 * @date: 2020-07-07 15:46
 * @description: 部门信息
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DepartmentMoreDTO extends DepartmentEntity implements Serializable {
    private static final long serialVersionUID = -7033953595447296651L;

    private Integer pageNum = 1;

    private Integer pageLimit = 20;

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 部门数量
     */
    private Integer departmentCount;
}
