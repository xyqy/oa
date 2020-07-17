package com.qyy.oa.modules.vacation.service;

import com.qyy.oa.config.ResultData;
import com.qyy.oa.modules.vacation.dto.VacationCountDto;
import com.qyy.oa.modules.vacation.dto.VacationMoreDto;
import com.qyy.oa.modules.vacation.entity.VacationEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: qiyayu
 * @date: 2020-07-08 14:27
 * @description: 请假
 */
public interface VacationService {

    /**
     * 请假信息列表
     *
     * @param vacationMoreDto 请假查询参数
     * @author: qiyayu
     * @description: 请假信息列表
     * @date: 2020-07-08 14:32
     * @return: com.qyy.oa.config.ResultData
     **/
    ResultData getVacationList(VacationMoreDto vacationMoreDto);

    /**
     * 新增请假信息
     *
     * @param vacationEntity 请求实体
     * @throws Exception 异常信息
     * @author: qiyayu
     * @description: 新增请假信息
     * @date: 2020-07-08 15:03
     * @return: com.qyy.oa.config.ResultData
     **/
    ResultData insertVacation(VacationEntity vacationEntity) throws Exception;

    /**
     * 查询单个请假信息
     *
     * @param id 请假id
     * @author: qiyayu
     * @description: 查询单个请假信息
     * @date: 2020-07-08 20:24
     * @return: com.qyy.oa.config.ResultData
     **/
    ResultData getVacationDetail(Integer id);

    /**
     * 修改请假信息
     *
     * @param vacationEntity 请假实体
     * @throws Exception 异常
     * @author: qiyayu
     * @description: 修改请假信息
     * @date: 2020-07-09 09:56
     * @return: com.qyy.oa.config.ResultData
     **/
    ResultData updateVacation(VacationEntity vacationEntity) throws Exception;

    /**
     * 删除请假信息
     *
     * @param vacationEntity 请假实体
     * @author: qiyayu
     * @description: 删除请假信息
     * @date: 2020-07-09 10:11
     * @return: com.qyy.oa.config.ResultData
     **/
    ResultData deleteVacation(VacationEntity vacationEntity);

    /**
     * 管理员审批请假
     *
     * @param vacationEntity 请假实体
     * @author: qiyayu
     * @description: 管理员审批请假
     * @date: 2020-07-09 10:23
     * @return: com.qyy.oa.config.ResultData
     **/
    ResultData modifyVacation(VacationEntity vacationEntity);

    /**
     * 计算请假时长
     *
     * @param vacationEntity 请假实体
     * @author: qiyayu
     * @description: 计算请假时长
     * @date: 2020-07-10 10:24
     * @return: com.qyy.oa.config.ResultData
     **/
    ResultData countDays(VacationEntity vacationEntity);

    /**
     * 统计请假信息
     *
     * @param vacationCountDto 查询参数
     * @author: qiyayu
     * @description: 统计请假信息
     * @date: 2020-07-10 11:27
     * @return: com.qyy.oa.config.ResultData
     **/
    ResultData countVacation(VacationCountDto vacationCountDto);

    /**
     * 提交审批信息
     *
     * @param vacationEntity 请假实体
     * @author: qiyayu
     * @description: 提交审批信息
     * @date: 2020-07-13 13:55
     * @return: com.qyy.oa.config.ResultData
     **/
    ResultData changeVacationState(VacationEntity vacationEntity);


    /**
     * 导出表格下载文件
     *
     * @param response response
     * @throws IOException 异常
     * @author: qiyayu
     * @description: 导出表格下载文件
     * @date: 2020-07-15 18:50
     * @return: void
     **/
    void download(HttpServletResponse response) throws IOException;

    /**
     * 统计请假类型信息
     *
     * @author: qiyayu
     * @description: 统计请假类型信息
     * @date: 2020-07-16 11:09
     * @param: []
     * @return: com.qyy.oa.config.ResultData
     **/
    ResultData countVacationType();
}
