package com.qyy.oa.modules.vacationtype.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qyy.oa.config.PageView;
import com.qyy.oa.config.ResultData;
import com.qyy.oa.modules.vacationtype.dao.VacationTypeMapper;
import com.qyy.oa.modules.vacationtype.dto.VacationTypeMoreDto;
import com.qyy.oa.modules.vacationtype.entity.VacationTypeEntity;
import com.qyy.oa.modules.vacationtype.service.VacationTypeService;
import com.qyy.oa.util.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: qiyayu
 * @date: 2020-07-08 09:32
 * @description: 假期类型
 */
@Service
public class VacationTypeServiceImpl implements VacationTypeService {

    @Autowired
    private VacationTypeMapper vacationTypeMapper;

    /**
     * @author: qiyayu
     * @description: 假期类型列表
     * @date: 2020-07-08 09:27
     * @param: [vacationTypeMoreDto]
     * @return: com.qyy.oa.config.ResultData
     */
    @Override
    public ResultData getVacationTypeList(VacationTypeMoreDto vacationTypeMoreDto) {
        //分页
        PageHelper.startPage(vacationTypeMoreDto.getPageNum(), vacationTypeMoreDto.getPageLimit());
        Page<VacationTypeEntity> dList = vacationTypeMapper.selectVacationTypeList(vacationTypeMoreDto);
        PageView<VacationTypeEntity> pageInfo = new PageView<>(dList);
        return ResultData.success(pageInfo);
    }

    /**
     * @author: qiyayu
     * @description: 新增假期类型
     * @date: 2020-07-08 09:53
     * @param: [vacationTypeEntity]
     * @return: com.qyy.oa.config.ResultData
     */
    @Override
    public ResultData insertVacationType(VacationTypeEntity vacationTypeEntity) {
        //判断name是否有值
        if (EmptyUtil.isEmpty(vacationTypeEntity.getName())) {
            return ResultData.error("参数错误");
        }
        //判断是否存在相同的假期名称
        int count = vacationTypeMapper.selectVacationTypeNameCount(vacationTypeEntity.getName());
        if (count > 0) {
            return ResultData.error("该假期已存在");
        }
        //新增假期类型
        int res = vacationTypeMapper.insertVacationType(vacationTypeEntity);
        if (res > 0) {
            return ResultData.success("新增成功");
        } else {
            return ResultData.error("新增失败");
        }

    }

    /**
     * @author: qiyayu
     * @description: 修改假期名称
     * @date: 2020-07-08 09:58
     * @param: [vacationTypeEntity]
     * @return: com.qyy.oa.config.ResultData
     */
    @Override
    public ResultData updateVacationType(VacationTypeEntity vacationTypeEntity) {
        if (EmptyUtil.isEmpty(vacationTypeEntity.getId())) {
            return ResultData.error("参数错误");
        }
        //判断是否存在相同的假期名称
        int count = vacationTypeMapper.selectVacationTypeNameCount(vacationTypeEntity.getName());
        if (count > 0) {
            return ResultData.error("该假期已存在");
        }
        //更新操作
        int res = vacationTypeMapper.updateVacationType(vacationTypeEntity);
        if (res > 0) {
            return ResultData.success("修改成功");
        } else {
            return ResultData.error("修改失败");
        }
    }

    /**
     * @author: qiyayu
     * @description: 删除假期
     * @date: 2020-07-08 10:06
     * @param: [vacationTypeEntity]
     * @return: com.qyy.oa.config.ResultData
     */
    @Override
    public ResultData deleteVacationType(VacationTypeEntity vacationTypeEntity) {
        if (EmptyUtil.isEmpty(vacationTypeEntity.getId())) {
            return ResultData.error("参数错误");
        }
        //删除操作
        int res = vacationTypeMapper.deleteVacationType(vacationTypeEntity.getId());
        if (res > 0) {
            return ResultData.success("删除成功");
        } else {
            return ResultData.error("删除失败");
        }
    }

}
