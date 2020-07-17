package com.qyy.oa.modules.admin.dao;

import com.github.pagehelper.Page;
import com.qyy.oa.modules.admin.dto.AdminMoreDto;
import com.qyy.oa.modules.admin.entity.AdminEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: qiyayu
 * @date: 2020-07-07 14:22
 * @description: 管理员mapper
 */
@Mapper
public interface AdminMapper {
    /**
     * 查询管理员列表
     *
     * @param adminMoreDto 管理员查询参数
     * @return: com.github.pagehelper.Page<com.qyy.oa.modules.admin.entity.AdminEntity>
     * @author: qiyayu
     * @date: 2020-07-07 13:43
     **/
    Page<AdminEntity> selectAdminList(AdminMoreDto adminMoreDto);

    /**
     * 新增管理员账号
     *
     * @param adminEntity 管理员实体
     * @author: qiyayu
     * @date: 2020-07-07 13:44
     * @return: int
     **/
    int insertAdmin(AdminEntity adminEntity);

    /**
     * 更新操作
     *
     * @param adminEntity 管理员实体
     * @author: qiyayu
     * @date: 2020-07-07 14:04
     * @return: int
     **/
    int updateAdmin(AdminEntity adminEntity);

    /**
     * 删除管理员信息
     *
     * @param adminEntity 管理员实体
     * @author: qiyayu
     * @date: 2020-07-07 14:11
     * @return: int
     **/
    int deleteAdmin(AdminEntity adminEntity);

    /**
     * 查询单个管理员详情
     *
     * @param id 管理员id
     * @author: qiyayu
     * @date: 2020-07-07 14:20
     * @return: com.qyy.oa.modules.admin.entity.AdminEntity
     **/
    AdminEntity selectAdminDetail(Integer id);

    /**
     * 查询管理员账号数量
     *
     * @param account 账号
     * @author: qiyayu
     * @description: 查询管理员账号数量
     * @date: 2020-07-08 13:50
     * @return: int
     **/
    int selectAdminAccountCount(String account);

    /**
     * 查询管理员信息
     *
     * @param adminEntity 管理员实体
     * @author: qiyayu
     * @description: 查询管理员信息
     * @date: 2020-07-09 11:28
     * @return: com.qyy.oa.modules.admin.entity.AdminEntity
     **/
    AdminEntity selectAdminInfo(AdminEntity adminEntity);
}