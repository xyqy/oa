package com.qyy.oa.modules.admin.service;

import com.qyy.oa.config.ResultData;
import com.qyy.oa.modules.admin.dto.AdminMoreDto;
import com.qyy.oa.modules.admin.entity.AdminEntity;

/**
 * @author: qiyayu
 * @date: 2020-07-07 14:21
 * @description: 管理员service
 */
public interface AdminService {
    /**
     * 查询管理员列表
     *
     * @param adminMoreDto 管理员查询参数
     * @author: qiyayu
     * @date: 2020-07-07 11:38
     * @return: com.qyy.oa.config.ResultData
     **/
    ResultData getAdminList(AdminMoreDto adminMoreDto);

    /**
     * 新增管理员信息
     *
     * @param adminEntity 管理员实体
     * @author: qiyayu
     * @date: 2020-07-07 13:32
     * @return: com.qyy.oa.config.ResultData
     **/
    ResultData insertAdmin(AdminEntity adminEntity);

    /**
     * 修改管理员信息
     *
     * @param adminEntity 管理员实体
     * @author: qiyayu
     * @date: 2020-07-07 13:49
     * @return: com.qyy.oa.config.ResultData
     **/
    ResultData updateAdmin(AdminEntity adminEntity);

    /**
     * 删除管理员信息
     *
     * @param adminEntity 管理员实体
     * @author: qiyayu
     * @date: 2020-07-07 14:08
     * @return: com.qyy.oa.config.ResultData
     **/
    ResultData deleteAdmin(AdminEntity adminEntity);

    /**
     * 查询单个管理员详情
     *
     * @param id 管理员id
     * @author: qiyayu
     * @date: 2020-07-07 14:17
     * @return: com.qyy.oa.config.ResultData
     **/
    ResultData getAdminDetail(Integer id);

    /**
     * 管理员登录
     *
     * @param adminEntity 管理员实体
     * @author: qiyayu
     * @description: 管理员登录
     * @date: 2020-07-09 11:24
     * @return: com.qyy.oa.config.ResultData
     **/
    ResultData login(AdminEntity adminEntity);
}
