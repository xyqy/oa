package com.qyy.oa.modules.controller;

import com.qyy.oa.config.ResultData;
import com.qyy.oa.modules.dto.AdminMoreDTO;
import com.qyy.oa.modules.entity.AdminEntity;
import com.qyy.oa.modules.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: qiyayu
 * @date: 2020-07-07 11:28
 * @description: 管理员controller
 */
@RestController
@RequestMapping("/admin")
@Api(description = "管理员模块")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * @author: qiyayu
     * @description: 查询管理员列表
     * @date: 2020-07-07 11:38
     * @param: [adminEntity]
     * @return: com.qyy.oa.config.ResultData
     **/
    @GetMapping("/list")
    @ApiOperation(value = "查询管理员列表", notes = "")
    public ResultData getAdminList(@ModelAttribute AdminMoreDTO adminMoreDto) {
        return adminService.getAdminList(adminMoreDto);
    }

    /**
     * @author: qiyayu
     * @description: 查询单个管理员详情
     * @date: 2020-07-07 14:16
     * @param: [id]
     * @return: com.qyy.oa.config.ResultData
     **/
    @GetMapping("/{id}")
    @ApiOperation(value = "查询单个管理员详情", notes = "")
    public ResultData getAdminDetail(@PathVariable(name = "id") Integer id) {
        return adminService.getAdminDetail(id);
    }

    /**
     * @author: qiyayu
     * @description: 新增管理员信息
     * @date: 2020-07-07 13:31
     * @param: [adminEntity]
     * @return: com.qyy.oa.config.ResultData
     **/
    @PostMapping("/insertAdmin")
    @ApiOperation(value = "新增管理员信息", notes = "")
    public ResultData insertAdmin(@RequestBody AdminEntity adminEntity) {
        return adminService.insertAdmin(adminEntity);
    }

    /**
     * @author: qiyayu
     * @description: 修改管理员信息
     * @date: 2020-07-07 13:49
     * @param: [adminEntity]
     * @return: com.qyy.oa.config.ResultData
     **/
    @PostMapping("/updateAdmin")
    @ApiOperation(value = "修改管理员信息", notes = "")
    public ResultData updateAdmin(@RequestBody AdminEntity adminEntity) {
        return adminService.updateAdmin(adminEntity);
    }

    /**
     * @author: qiyayu
     * @description: 删除操作
     * @date: 2020-07-07 14:13
     * @param: [adminEntity]
     * @return: com.qyy.oa.config.ResultData
     **/
    @PostMapping("/deleteAdmin")
    @ApiOperation(value = "删除管理员信息", notes = "")
    public ResultData deleteAdmin(@RequestBody AdminEntity adminEntity) {
        return adminService.deleteAdmin(adminEntity);
    }

    /**
     * @author: qiyayu
     * @description: 管理员登录
     * @date: 2020-07-09 11:23
     * @param: [adminEntity]
     * @return: com.qyy.oa.config.ResultData
     **/
    @PostMapping("/login")
    @ApiOperation(value = "管理员登录", notes = "")
    public ResultData login(@RequestBody AdminEntity adminEntity) {
        return adminService.login(adminEntity);
    }
}
