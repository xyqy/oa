package com.qyy.oa.modules.department.service;

import com.qyy.oa.config.ResultData;
import com.qyy.oa.modules.department.dto.DepartmentMoreDto;
import com.qyy.oa.modules.department.entity.DepartmentEntity;

/**
 * @author: qiyayu
 * @date: 2020-07-07 16:25
 * @description: 部门service
 */
public interface DepartmentService {

    /**
     * 查询部门列表
     *
     * @param departmentMoreDto 部门查询参数
     * @author: qiyayu
     * @description: 查询部门列表
     * @date: 2020-07-07 15:47
     * @return: com.qyy.oa.config.ResultData
     **/
    ResultData getDepartmentList(DepartmentMoreDto departmentMoreDto);

    /**
     * 查询单个部门详情
     *
     * @param id 部门id
     * @author: qiyayu
     * @description: 查询单个部门详情
     * @date: 2020-07-07 15:55
     * @return: com.qyy.oa.config.ResultData
     **/
    ResultData getDepartmentDetail(Integer id);

    /**
     * 新增部门信息
     *
     * @param departmentEntity 部门实体
     * @author: qiyayu
     * @description: 新增部门信息
     * @date: 2020-07-07 15:57
     * @return: com.qyy.oa.config.ResultData
     **/
    ResultData insertDepartment(DepartmentEntity departmentEntity);

    /**
     * 更新部门信息
     *
     * @param departmentEntity 部门实体
     * @author: qiyayu
     * @description: 更新部门信息
     * @date: 2020-07-07 16:13
     * @return: com.qyy.oa.config.ResultData
     **/
    ResultData updateDepartment(DepartmentEntity departmentEntity);

    /**
     * 删除部门信息
     *
     * @param departmentEntity 部门实体
     * @author: qiyayu
     * @description: 删除部门信息
     * @date: 2020-07-07 16:18
     * @return: com.qyy.oa.config.ResultData
     **/
    ResultData deleteDepartment(DepartmentEntity departmentEntity);

    /**
     * 统计部门人数信息
     *
     * @author: qiyayu
     * @description: 统计部门人数信息
     * @date: 2020-07-15 17:28
     * @param: []
     * @return: com.qyy.oa.config.ResultData
     **/
    ResultData countDepartment();

}
