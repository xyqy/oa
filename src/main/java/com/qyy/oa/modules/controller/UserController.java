package com.qyy.oa.modules.controller;

import com.qyy.oa.config.ResultData;
import com.qyy.oa.modules.dto.UserMoreDTO;
import com.qyy.oa.modules.entity.UserEntity;
import com.qyy.oa.modules.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

/**
 * @author: qiyayu
 * @date: 2020-07-08 11:08
 * @description: 用户模块
 */
@RestController
@RequestMapping("/user")
@Api(description = "用户模块")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @author: qiyayu
     * @description: 查询用户列表
     * @date: 2020-07-08 11:15
     * @param: [userMoreDto]
     * @return: com.qyy.oa.config.ResultData
     **/
    @GetMapping("/list")
    @ApiOperation(value = "查询用户列表", notes = "查询用户列表")
    public ResultData getUserList(@ModelAttribute UserMoreDTO userMoreDto) {
        return userService.getUserList(userMoreDto);
    }

    /**
     * @author: qiyayu
     * @description: 查询单个用户详情
     * @date: 2020-07-08 11:26
     * @param: [id]
     * @return: com.qyy.oa.config.ResultData
     **/
    @GetMapping("/{id}")
    @ApiOperation(value = "查询单个用户详情", notes = "查询单个用户详情")
    public ResultData getUserDetail(@PathVariable(name = "id") Integer id) {
        return userService.getUserDetail(id);
    }

    /**
     * @author: qiyayu
     * @description: 新增用户
     * @date: 2020-07-08 11:46
     * @param: [userEntity]
     * @return: com.qyy.oa.config.ResultData
     **/
    @PostMapping("/insertUser")
    @ApiOperation(value = "新增用户信息", notes = "新增用户信息")
    public ResultData insertUser(@RequestBody UserEntity userEntity) throws ParseException {
        return userService.insertUser(userEntity);
    }

    /**
     * @author: qiyayu
     * @description: 更新用户信息
     * @date: 2020-07-08 13:24
     * @param: [userEntity]
     * @return: com.qyy.oa.config.ResultData
     **/
    @PostMapping("/updateUser")
    @ApiOperation(value = "修改用户信息", notes = "修改用户信息")
    public ResultData updateUser(@RequestBody UserEntity userEntity) {
        return userService.updateUser(userEntity);
    }

    /**
     * @author: qiyayu
     * @description: 删除用户
     * @date: 2020-07-08 13:32
     * @param: [userEntity]
     * @return: com.qyy.oa.config.ResultData
     **/
    @PostMapping("/deleteUser")
    @ApiOperation(value = "删除管理员信息", notes = "删除管理员信息")
    public ResultData deleteUser(@RequestBody UserEntity userEntity) {
        return userService.deleteUser(userEntity);
    }

    /**
     * @author: qiyayu
     * @description: 导出用户表格数据
     * @date: 2020-07-09 17:16
     * @param: [response]
     * @return: void
     **/
    @GetMapping("/download")
    @ApiOperation(value = "导出用户表格数据", notes = "导出用户表格数据")
    public void download(HttpServletResponse response) throws IOException {
        userService.download(response);
    }

    /**
     * @author: qiyayu
     * @description: 员工登录
     * @date: 2020-07-09 20:24
     * @param: [userEntity]
     * @return: com.qyy.oa.config.ResultData
     **/
    @PostMapping("/login")
    @ApiOperation(value = "员工登录", notes = "员工登录")
    public ResultData login(@RequestBody UserEntity userEntity) {
        return userService.login(userEntity);
    }
}
