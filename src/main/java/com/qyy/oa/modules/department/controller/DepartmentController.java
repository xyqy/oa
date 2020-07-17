package com.qyy.oa.modules.department.controller;

import com.qyy.oa.config.ResultData;
import com.qyy.oa.modules.department.dto.DepartmentMoreDto;
import com.qyy.oa.modules.department.entity.DepartmentEntity;
import com.qyy.oa.modules.department.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: qiyayu
 * @date: 2020-07-07 15:40
 * @description: 部门信息
 */
@RestController
@RequestMapping("/department")
@Api(description = "部门信息")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * @author: qiyayu
     * @description: 查询部门列表
     * @date: 2020-07-07 15:53
     * @param: [depaermentMoreDto]
     * @return: com.qyy.oa.config.ResultData
     **/
    @GetMapping("/list")
    @ApiOperation(value = "部门列表")
    public ResultData getDepartmentList(@ModelAttribute DepartmentMoreDto departmentMoreDto) {
        return departmentService.getDepartmentList(departmentMoreDto);
    }

    /**
     * @author: qiyayu
     * @description: 查询单个部门详情
     * @date: 2020-07-07 15:54
     * @param: [id]
     * @return: com.qyy.oa.config.ResultData
     **/
    @GetMapping("/{id}")
    @ApiOperation(value = "查询单个部门详情")
    public ResultData getDepartmentDetail(@PathVariable(name = "id") Integer id) {
        return departmentService.getDepartmentDetail(id);
    }

    /**
     * @author: qiyayu
     * @description: 新增部门信息
     * @date: 2020-07-07 16:06
     * @param: [departmentEntity]
     * @return: com.qyy.oa.config.ResultData
     **/
    @PostMapping("/insertDepartment")
    @ApiOperation(value = "新增部门信息")
    public ResultData insertDepartment(@RequestBody DepartmentEntity departmentEntity) {
        return departmentService.insertDepartment(departmentEntity);
    }

    /**
     * @author: qiyayu
     * @description: 更新部门信息
     * @date: 2020-07-07 16:13
     * @param: [departmentEntity]
     * @return: com.qyy.oa.config.ResultData
     **/
    @PostMapping("/updateDepartment")
    @ApiOperation(value = "修改部门信息")
    public ResultData updateDepartment(@RequestBody DepartmentEntity departmentEntity) {
        return departmentService.updateDepartment(departmentEntity);
    }

    /**
     * @author: qiyayu
     * @description: 删除部门信息
     * @date: 2020-07-07 16:17
     * @param: [departmentEntity]
     * @return: com.qyy.oa.config.ResultData
     **/
    @PostMapping("/deleteDepartment")
    @ApiOperation(value = "删除部门信息")
    public ResultData deleteDepartment(@RequestBody DepartmentEntity departmentEntity) {
        return departmentService.deleteDepartment(departmentEntity);
    }

    /**
     * @author: qiyayu
     * @description: 统计部门人数信息
     * @date: 2020-07-15 17:27
     * @param: []
     * @return: com.qyy.oa.config.ResultData
     **/
    @GetMapping("/countDepartment")
    @ApiOperation(value = "统计部门人数信息")
    public ResultData countDepartment() {
        return departmentService.countDepartment();
    }
}
