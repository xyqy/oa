package com.qyy.oa.modules.dto;

import com.qyy.oa.modules.entity.UserEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author: qiyayu
 * @date: 2020-07-08 11:15
 * @description: 用户
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserMoreDTO extends UserEntity implements Serializable {

    private static final long serialVersionUID = -7138585023680480863L;
    /**
     * 默认页码
     */
    private Integer pageNum = 1;

    /**
     * 默认数量
     */
    private Integer pageLimit = 20;

    /**
     * 部门名称
     */
    private String departmentName;
}
