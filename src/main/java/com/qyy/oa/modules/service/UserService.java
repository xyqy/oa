package com.qyy.oa.modules.service;

import com.qyy.oa.config.ResultData;
import com.qyy.oa.modules.dto.UserMoreDTO;
import com.qyy.oa.modules.entity.UserEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

/**
 * @author: qiyayu
 * @date: 2020-07-08 11:09
 * @description: 用户
 */
public interface UserService {

    /**
     * 查询用户列表
     *
     * @param userMoreDto 用户查询参数
     * @author: qiyayu
     * @description: 查询用户列表
     * @date: 2020-07-08 11:16
     * @return: com.qyy.oa.config.ResultData
     **/
    ResultData getUserList(UserMoreDTO userMoreDto);

    /**
     * 查询单个用户详情
     *
     * @param id 用户id
     * @author: qiyayu
     * @description: 查询单个用户详情
     * @date: 2020-07-08 11:26
     * @return: com.qyy.oa.config.ResultData
     **/
    ResultData getUserDetail(Integer id);

    /**
     * 新增用户
     *
     * @param userEntity 用户实体
     * @throws ParseException 异常
     * @author: qiyayu
     * @description: 新增用户
     * @date: 2020-07-08 13:09
     * @return: com.qyy.oa.config.ResultData
     **/
    ResultData insertUser(UserEntity userEntity) throws ParseException;

    /**
     * 更新用户
     *
     * @param userEntity 用户实体
     * @author: qiyayu
     * @description: 更新用户
     * @date: 2020-07-08 13:25
     * @return: com.qyy.oa.config.ResultData
     **/
    ResultData updateUser(UserEntity userEntity);

    /**
     * 删除用户
     *
     * @param userEntity 用户实体
     * @author: qiyayu
     * @description: 删除用户
     * @date: 2020-07-08 13:33
     * @return: com.qyy.oa.config.ResultData
     **/
    ResultData deleteUser(UserEntity userEntity);

    /**
     * 登录
     *
     * @param userEntity 用户实体
     * @author: qiyayu
     * @description: 登录
     * @date: 2020-07-09 20:24
     * @return: com.qyy.oa.config.ResultData
     **/
    ResultData login(UserEntity userEntity);

    /**
     * 导出用户表格信息
     *
     * @param response response
     * @throws IOException 异常信息
     * @author: qiyayu
     * @description: 导出用户表格信息
     * @date: 2020-07-15 18:58
     * @return: void
     **/
    void download(HttpServletResponse response) throws IOException;
}
