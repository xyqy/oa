package com.qyy.oa.modules.department.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qyy.oa.config.PageView;
import com.qyy.oa.config.ResultData;
import com.qyy.oa.modules.department.dao.DepartmentMapper;
import com.qyy.oa.modules.department.dto.DepartmentMoreDto;
import com.qyy.oa.modules.department.entity.DepartmentEntity;
import com.qyy.oa.modules.department.service.DepartmentService;
import com.qyy.oa.util.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: qiyayu
 * @date: 2020-07-07 16:28
 * @description: 部门
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    /**
     * @author: qiyayu
     * @description: 查询部门列表
     * @date: 2020-07-07 15:48
     * @param: [depaermentMoreDto]
     * @return: com.qyy.oa.config.ResultData
     **/
    @Override
    public ResultData getDepartmentList(DepartmentMoreDto departmentMoreDto) {
        //分页
        PageHelper.startPage(departmentMoreDto.getPageNum(), departmentMoreDto.getPageLimit());
        Page<DepartmentEntity> dList = departmentMapper.selectDepartmentList(departmentMoreDto);
        PageView<DepartmentEntity> pageInfo = new PageView<>(dList);
        return ResultData.success(pageInfo);
    }

    /**
     * @author: qiyayu
     * @description: 查询单个部门详情
     * @date: 2020-07-07 15:55
     * @param: [id]
     * @return: com.qyy.oa.config.ResultData
     */
    @Override
    public ResultData getDepartmentDetail(Integer id) {
        //判断id是否未空
        if (EmptyUtil.isEmpty(id)) {
            return ResultData.error("参数错误");
        }
        return ResultData.success(departmentMapper.getDepartmentDetail(id));
    }


    /**
     * @author: qiyayu
     * @description: 新增部门信息
     * @date: 2020-07-07 15:58
     * @param: [departmentEntity]
     * @return: com.qyy.oa.config.ResultData
     **/
    @Override
    public ResultData insertDepartment(DepartmentEntity departmentEntity) {
        //判断name是否有值
        if (EmptyUtil.isEmpty(departmentEntity.getName())) {
            return ResultData.error("参数错误");
        }
        //先判断是否存在相同的部门
        int count = departmentMapper.selectDepartmentNameCount(departmentEntity.getName());
        if (count > 0) {
            return ResultData.error("该部门已存在");
        }
        //新增部门
        int res = departmentMapper.insertDepartment(departmentEntity);
        if (res > 0) {
            return ResultData.success("新增成功");
        } else {
            return ResultData.error("新增失败");
        }
    }

    /**
     * @author: qiyayu
     * @description: 更新部门信息
     * @date: 2020-07-07 16:13
     * @param: [departmentEntity]
     * @return: com.qyy.oa.config.ResultData
     **/
    @Override
    public ResultData updateDepartment(DepartmentEntity departmentEntity) {
        if (EmptyUtil.isEmpty(departmentEntity.getId())) {
            return ResultData.error("参数错误");
        }
        //断是否存在相同的部门
        int count = departmentMapper.selectDepartmentNameCount(departmentEntity.getName());
        if (count > 0) {
            return ResultData.error("该部门已存在");
        }
        //更新操作
        int res = departmentMapper.updateDepartment(departmentEntity);
        if (res > 0) {
            return ResultData.success("修改成功");
        } else {
            return ResultData.error("修改失败");
        }
    }

    /**
     * @author: qiyayu
     * @description: 删除部门信息
     * @date: 2020-07-07 16:18
     * @param: [departmentEntity]
     * @return: com.qyy.oa.config.ResultData
     **/
    @Override
    public ResultData deleteDepartment(DepartmentEntity departmentEntity) {
        if (EmptyUtil.isEmpty(departmentEntity.getId())) {
            return ResultData.error("参数错误");
        }
        //删除操作
        int res = departmentMapper.deleteDepartment(departmentEntity.getId());
        if (res > 0) {
            return ResultData.success("删除成功");
        } else {
            return ResultData.error("删除失败");
        }

    }

    /**
     * @author: qiyayu
     * @description: 统计部门人数信息
     * @date: 2020-07-15 17:28
     * @param: []
     * @return: com.qyy.oa.config.ResultData
     **/
    @Override
    public ResultData countDepartment() {
        List<DepartmentMoreDto> list = departmentMapper.countDepartment();
        return ResultData.success(list);
    }

}
