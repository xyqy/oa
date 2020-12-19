package com.qyy.oa.modules.dto;

import com.qyy.oa.modules.entity.VacationTypeEntity;

import java.io.Serializable;
/**
 *  @author: qiyayu
 *  @date: 2020-07-08 09:25
 *  @description: 部门
 */
public class VacationTypeMoreDTO extends VacationTypeEntity implements Serializable {
    private static final long serialVersionUID = -6605451991056608237L;
    private Integer pageNum = 1;

    private Integer pageLimit = 20;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageLimit() {
        return pageLimit;
    }

    public void setPageLimit(Integer pageLimit) {
        this.pageLimit = pageLimit;
    }
}
