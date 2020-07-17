package com.qyy.oa.modules.department.dao;

import com.github.pagehelper.Page;
import com.qyy.oa.modules.department.dto.DepartmentMoreDto;
import com.qyy.oa.modules.department.entity.DepartmentEntity;

import java.util.List;

/**
 * @author: qiyayu
 * @date: 2020-07-07 15:39
 * @description: 部门mapper
 */
public interface DepartmentMapper {


    /**
     * 查询部门列表
     *
     * @param departmentMoreDto 查询部门参数
     * @author: qiyayu
     * @date: 2020-07-07 15:50
     * @return: com.github.pagehelper.Page<com.qyy.oa.modules.department.entity.DepartmentEntity>
     **/
    Page<DepartmentEntity> selectDepartmentList(DepartmentMoreDto departmentMoreDto);

    /**
     * 查询单个部门详情
     *
     * @param id 部门id
     * @author: qiyayu
     * @description: 查询单个部门详情
     * @date: 2020-07-07 16:09
     * @return: com.qyy.oa.modules.department.entity.DepartmentEntity
     **/
    DepartmentEntity getDepartmentDetail(Integer id);

    /**
     * 新增部门信息
     *
     * @param departmentEntity 部门实体
     * @author: qiyayu
     * @description: 新增部门信息
     * @date: 2020-07-07 16:01
     * @return: int
     **/
    int insertDepartment(DepartmentEntity departmentEntity);

    /**
     * 更新部门信息
     *
     * @param departmentEntity 部门实体
     * @author: qiyayu
     * @description: 更新部门信息
     * @date: 2020-07-07 16:14
     * @return: int
     **/
    int updateDepartment(DepartmentEntity departmentEntity);

    /**
     * 删除部门信息
     *
     * @param id 部门id
     * @author: qiyayu
     * @description: 删除部门信息
     * @date: 2020-07-07 16:22
     * @return: int
     **/
    int deleteDepartment(Integer id);

    /**
     * 查找部门数量
     *
     * @param name 部门名称
     * @author: qiyayu
     * @description: 查找部门数量
     * @date: 2020-07-08 13:54
     * @return: int
     **/
    int selectDepartmentNameCount(String name);

    /**
     * 统计部门人数信息
     *
     * @author: qiyayu
     * @description: 统计部门人数信息
     * @date: 2020-07-15 17:31
     * @param: []
     * @return: java.util.List<com.qyy.oa.modules.department.dto.DepartmentMoreDto>
     **/
    List<DepartmentMoreDto> countDepartment();
}