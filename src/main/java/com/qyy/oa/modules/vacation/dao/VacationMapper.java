package com.qyy.oa.modules.vacation.dao;

import com.github.pagehelper.Page;
import com.qyy.oa.modules.vacation.dto.VacationCountDto;
import com.qyy.oa.modules.vacation.dto.VacationMoreDto;
import com.qyy.oa.modules.vacation.entity.VacationEntity;

import java.util.List;

/**
 * @author: qiyayu
 * @date: 2020-07-08 14:28
 * @description: 请假
 */
public interface VacationMapper {

    /**
     * 请假列表
     *
     * @param vacationMoreDto 请假参数
     * @author: qiyayu
     * @description: 请假列表
     * @date: 2020-07-08 14:34
     * @return: com.github.pagehelper.Page<com.qyy.oa.modules.vacationtype.entity.VacationTypeEntity>
     **/
    Page<VacationEntity> selectVacationList(VacationMoreDto vacationMoreDto);

    /**
     * 查询用户五分钟内是否有相同请假
     *
     * @param vacationEntity 请假参数
     * @author: qiyayu
     * @description: 将所有的请假信息都完成才能再次填写
     * @date: 2020-07-08 15:48
     * @return: int
     **/
    int selectUserCount(VacationEntity vacationEntity);

    /**
     * 新增请假信息
     *
     * @param vacationEntity 请假实体
     * @author: qiyayu
     * @description: 新增请假信息
     * @date: 2020-07-08 16:41
     * @return: int
     **/
    int insertVacation(VacationEntity vacationEntity);

    /**
     * 查询单个请假信息
     *
     * @param id 请假id
     * @author: qiyayu
     * @description: 查询单个请假信息
     * @date: 2020-07-08 20:27
     * @return: com.qyy.oa.modules.vacation.dto.VacationMoreDto
     **/
    VacationMoreDto selectVacationDetail(Integer id);

    /**
     * 更新假条操作
     *
     * @param vacationEntity 请假实体
     * @author: qiyayu
     * @description: 更新假条操作
     * @date: 2020-07-09 10:07
     * @return: int
     **/
    int updateVacation(VacationEntity vacationEntity);

    /**
     * 删除操作
     *
     * @param vacationEntity 请假实体
     * @author: qiyayu
     * @description: 删除操作
     * @date: 2020-07-09 10:13
     * @return: int
     **/
    int deleteVacation(VacationEntity vacationEntity);

    /**
     * 查询所有信息
     *
     * @author: qiyayu
     * @description: 查询所有信息
     * @date: 2020-07-09 18:53
     * @param: []
     * @return: java.util.List<com.qyy.oa.modules.vacation.dto.VacationExcelDto>
     **/
    List<VacationMoreDto> selectVacationListForExcel();

    /**
     * 按照月份统计信息
     *
     * @param vacationCountDto 查询参数
     * @author: qiyayu
     * @description: 按照月份统计信息
     * @date: 2020-07-10 11:47
     * @return: java.util.List<com.qyy.oa.modules.vacation.dto.VacationCountDto>
     **/
    List<VacationCountDto> countVacation(VacationCountDto vacationCountDto);

    /**
     * 查询当前请的假是否已经请过
     *
     * @param vacationEntity 请假实体
     * @author: qiyayu
     * @description: 查询当前请的假是否已经请过
     * @date: 2020-07-11 16:10
     * @return: int
     **/
    int selectUserVacationCount(VacationEntity vacationEntity);

    /**
     * 统计请假类型信息
     *
     * @author: qiyayu
     * @description: 统计请假类型信息
     * @date: 2020-07-16 11:11
     * @param: []
     * @return: java.util.List<com.qyy.oa.modules.vacation.dto.VacationCountDto>
     **/
    List<VacationCountDto> countVacationType();
}