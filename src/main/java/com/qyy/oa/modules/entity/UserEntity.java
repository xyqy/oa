package com.qyy.oa.modules.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: qiyayu
 * @date: 2020-07-08 11:07
 * @description: 用户实体
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserEntity implements Serializable {

    private static final long serialVersionUID = -1602029943588027014L;
    /**
     * id
     */
    private Integer id;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 用户账号
     */
    private String account;

    /**
     * 用户编号
     */
    private String code;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 部门id
     */
    private Integer departmentId;

    /**
     * 手机号
     */
    private String tel;

    /**
     * 用户邮件
     */
    private String email;

    /**
     * 用户性别，1男2女
     */
    private Integer sex;

    /**
     * 状态 0未启用，1启用
     */
    private Integer state;

    /**
     * 入职时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date entryTime;

    /**
     * 下一年 年假到到期时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expireTime;

    /**
     * 首次工作时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date firstWorkTime;


    /**
     * 年假剩余时间
     */
    private Double expireDays;

    /**
     * 新建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 是否删除，0未删除，1已删除
     */
    private Integer isDelete;
}