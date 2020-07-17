package com.qyy.oa.modules.vacationtype.service;

import com.qyy.oa.config.ResultData;
import com.qyy.oa.modules.vacationtype.dto.VacationTypeMoreDto;
import com.qyy.oa.modules.vacationtype.entity.VacationTypeEntity;

/**
 * @author: qiyayu
 * @date: 2020-07-08 09:27
 * @description: 假期类型
 */
public interface VacationTypeService {

    /**
     * 假期类型列表
     *
     * @param vacationTypeMoreDto 假期类型参数
     * @author: qiyayu
     * @description: 假期类型列表
     * @date: 2020-07-08 09:27
     * @return: com.qyy.oa.config.ResultData
     **/
    ResultData getVacationTypeList(VacationTypeMoreDto vacationTypeMoreDto);

    /**
     * 新增假期类型
     *
     * @param vacationTypeEntity 假期实体
     * @author: qiyayu
     * @description: 新增假期类型
     * @date: 2020-07-08 09:53
     * @return: com.qyy.oa.config.ResultData
     **/
    ResultData insertVacationType(VacationTypeEntity vacationTypeEntity);

    /**
     * 修改假期
     *
     * @param vacationTypeEntity 假期实体
     * @author: qiyayu
     * @description: 修改假期
     * @date: 2020-07-08 09:58
     * @return: com.qyy.oa.config.ResultData
     **/
    ResultData updateVacationType(VacationTypeEntity vacationTypeEntity);

    /**
     * 删除假期
     *
     * @param vacationTypeEntity 假期实体
     * @author: qiyayu
     * @description: 删除假期
     * @date: 2020-07-08 10:06
     * @return: com.qyy.oa.config.ResultData
     **/
    ResultData deleteVacationType(VacationTypeEntity vacationTypeEntity);
}
