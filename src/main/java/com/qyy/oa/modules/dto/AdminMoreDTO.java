package com.qyy.oa.modules.dto;

import com.qyy.oa.modules.entity.AdminEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author: qiyayu
 * @date: 2020-07-07 11:43
 * @description: 管理员
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AdminMoreDTO extends AdminEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer pageNum = 1;

    private Integer pageLimit = 20;

    /**
     * 部门名称
     */
    private String departmentName;
}
