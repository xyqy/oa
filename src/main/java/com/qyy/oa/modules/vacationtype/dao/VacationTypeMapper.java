package com.qyy.oa.modules.vacationtype.dao;

import com.github.pagehelper.Page;
import com.qyy.oa.modules.vacationtype.dto.VacationTypeMoreDto;
import com.qyy.oa.modules.vacationtype.entity.VacationTypeEntity;

/**
 * @author: qiyayu
 * @date: 2020-07-08 10:33
 * @description: 假期
 */
public interface VacationTypeMapper {

    /**
     * 查询假期类型列表
     *
     * @param vacationTypeMoreDto 假期参数查询
     * @author: qiyayu
     * @description: 查询假期类型列表
     * @date: 2020-07-08 09:30
     * @return: com.github.pagehelper.Page<com.qyy.oa.modules.vacation.entity.VacationEntity>
     **/
    Page<VacationTypeEntity> selectVacationTypeList(VacationTypeMoreDto vacationTypeMoreDto);

    /**
     * 新增假期类型
     *
     * @param vacationTypeEntity 假期实体
     * @author: qiyayu
     * @description: 新增假期类型
     * @date: 2020-07-08 09:55
     * @return: int
     **/
    int insertVacationType(VacationTypeEntity vacationTypeEntity);

    /**
     * 更新假期
     *
     * @param vacationTypeEntity 假期实体
     * @author: qiyayu
     * @description: 更新假期
     * @date: 2020-07-08 10:02
     * @return: int
     **/
    int updateVacationType(VacationTypeEntity vacationTypeEntity);

    /**
     * 删除假期
     *
     * @param id 假期id
     * @author: qiyayu
     * @description: 删除假期
     * @date: 2020-07-08 10:08
     * @return: int
     **/
    int deleteVacationType(Integer id);

    /**
     * 查询假期数量
     *
     * @param name 假期名称
     * @author: qiyayu
     * @description: 查询假期数量
     * @date: 2020-07-08 13:57
     * @return: int
     **/
    int selectVacationTypeNameCount(String name);

    /**
     * 假期详情
     *
     * @param vacationTypeId 假期类型id
     * @author: qiyayu
     * @description: 假期详情
     * @date: 2020-07-08 16:31
     * @return: com.qyy.oa.modules.vacationtype.entity.VacationTypeEntity
     **/
    VacationTypeEntity selectVacationDetail(Integer vacationTypeId);
}