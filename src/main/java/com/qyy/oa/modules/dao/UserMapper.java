package com.qyy.oa.modules.dao;

import com.github.pagehelper.Page;
import com.qyy.oa.modules.dto.UserExcelDTO;
import com.qyy.oa.modules.dto.UserMoreDTO;
import com.qyy.oa.modules.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: qiyayu
 * @date: 2020-07-08 13:37
 * @description: 用户
 */
@Mapper
public interface UserMapper {

    /**
     * 查询用户列表
     *
     * @param userMoreDto 用户查询参数
     * @author: qiyayu
     * @description: 查询用户列表
     * @date: 2020-07-08 11:18
     * @return: com.github.pagehelper.Page<com.qyy.oa.modules.entity.UserEntity>
     **/
    Page<UserEntity> selectUserList(UserMoreDTO userMoreDto);

    /**
     * 查询单个用户详情
     *
     * @param id 用户id
     * @author: qiyayu
     * @description: 查询单个用户详情
     * @date: 2020-07-08 11:30
     * @return: com.qyy.oa.modules.dto.UserMoreDto
     **/
    UserMoreDTO getUserDetail(Integer id);

    /**
     * 新增用户
     *
     * @param userEntity 用户实体
     * @author: qiyayu
     * @description: 新增用户
     * @date: 2020-07-08 13:21
     * @return: int
     **/
    int insertUser(UserEntity userEntity);

    /**
     * 更新用户
     *
     * @param userEntity 用户实体
     * @author: qiyayu
     * @description: 更新用户
     * @date: 2020-07-08 13:29
     * @return: int
     **/
    int updateUser(UserEntity userEntity);

    /**
     * 删除用户
     *
     * @param userEntity 用户
     * @author: qiyayu
     * @description: 删除用户
     * @date: 2020-07-08 13:34
     * @return: int
     **/
    int deleteUser(UserEntity userEntity);

    /**
     * 查询用户账号
     *
     * @param account 账号
     * @author: qiyayu
     * @description: 查询用户账号
     * @date: 2020-07-08 13:45
     * @return: int
     **/
    int selectUserAccountCount(String account);

    /**
     * 导出用户表格
     *
     * @author: qiyayu
     * @description: 导出用户表格
     * @date: 2020-07-09 17:02
     * @return: java.util.List<com.qyy.oa.modules.dto.UserExcelDto>
     **/
    List<UserExcelDTO> selectUserListForExcel();

    /**
     * 用户登录
     *
     * @param userEntity 用户实体
     * @author: qiyayu
     * @description: 用户登录
     * @date: 2020-07-09 20:29
     * @return: com.qyy.oa.modules.entity.UserEntity
     **/
    UserEntity getUserInfo(UserEntity userEntity);
}