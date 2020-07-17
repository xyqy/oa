package com.qyy.oa.modules.admin.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: qiyayu
 * @date: 2020-07-07 14:22
 * @description: 管理员实体
 */
@ApiModel(value = "管理员实体")
@Data
@EqualsAndHashCode(callSuper = false)
public class AdminEntity implements Serializable {
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Integer id;

    /**
     * 管理员账户
     */
    @ApiModelProperty(value = "账号")
    private String account;

    /**
     * 管理员密码
     */
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 管理员姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    /**
     * 0,未锁定，1锁定
     */
    @ApiModelProperty(value = "是否锁定")
    private Integer state;

    private static final long serialVersionUID = 1L;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 部门id
     */
    private Integer departmentId;

    /**
     * 是否删除，0未删，1已删
     */
    private Integer isDelete;
}