package com.qyy.oa.modules.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qyy.oa.config.PageView;
import com.qyy.oa.config.ResultData;
import com.qyy.oa.modules.dao.AdminMapper;
import com.qyy.oa.modules.dto.AdminMoreDTO;
import com.qyy.oa.modules.entity.AdminEntity;
import com.qyy.oa.modules.service.AdminService;
import com.qyy.oa.util.EmptyUtil;
import com.qyy.oa.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: qiyayu
 * @date: 2020-07-07 14:21
 * @description: 管理员service实现
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    /**
     * @param: adminEntity
     * @author: qiyayu
     * @description: 查询管理员列表
     * @date: 2020-07-07 11:38
     * @return: com.qyy.oa.config.ResultData
     */
    @Override
    public ResultData getAdminList(AdminMoreDTO adminMoreDto) {
        //分页
        PageHelper.startPage(adminMoreDto.getPageNum(), adminMoreDto.getPageLimit());
        Page<AdminEntity> adminList = adminMapper.selectAdminList(adminMoreDto);
        PageView<AdminEntity> pageInfo = new PageView<>(adminList);
        return ResultData.success(pageInfo);
    }

    /**
     * @param: adminEntity
     * @author: qiyayu
     * @description: 新增管理员信息
     * @date: 2020-07-07 13:32
     * @return: com.qyy.oa.config.ResultData
     */
    @Override
    public ResultData insertAdmin(AdminEntity adminEntity) {
        //判断新增账号和密码是否为空
        if (EmptyUtil.isEmpty(adminEntity.getAccount()) || EmptyUtil.isEmpty(adminEntity.getPassword())) {
            return ResultData.error("参数错误");
        }
        //先判断用户是否存在
        int count = adminMapper.selectAdminAccountCount(adminEntity.getAccount());
        if (count > 0) {
            return ResultData.error("该账号已存在");
        }
        //设置密码为md5加密
        adminEntity.setPassword(Md5Util.md5(adminEntity.getPassword()));
        int res = adminMapper.insertAdmin(adminEntity);
        if (res > 0) {
            return ResultData.success("新增成功");
        } else {
            return ResultData.error("新增失败");
        }
    }

    /**
     * @author: qiyayu
     * @description: 修改管理员信息
     * @date: 2020-07-07 13:49
     * @param: [adminEntity]
     * @return: com.qyy.oa.config.ResultData
     */
    @Override
    public ResultData updateAdmin(AdminEntity adminEntity) {
        if (EmptyUtil.isEmpty(adminEntity.getId())) {
            return ResultData.error("参数错误");
        }
        //如果密码不为空，则更新密码
        if (!EmptyUtil.isEmpty(adminEntity.getPassword())) {
            adminEntity.setPassword(Md5Util.md5(adminEntity.getPassword()));
        }
        //更新操作
        int res = adminMapper.updateAdmin(adminEntity);
        if (res > 0) {
            return ResultData.success("修改成功");
        } else {
            return ResultData.error("修改失败");
        }
    }

    /**
     * @author: qiyayu
     * @description: 删除管理员信息
     * @date: 2020-07-07 14:08
     * @param: [adminEntity]
     * @return: com.qyy.oa.config.ResultData
     */
    @Override
    public ResultData deleteAdmin(AdminEntity adminEntity) {
        if (EmptyUtil.isEmpty(adminEntity.getId())) {
            return ResultData.error("参数错误");
        }
        //删除操作
        int res = adminMapper.deleteAdmin(adminEntity);
        if (res > 0) {
            return ResultData.success("删除成功");
        } else {
            return ResultData.error("删除失败");
        }
    }

    /**
     * @author: qiyayu
     * @description: 查询单个管理员详情
     * @date: 2020-07-07 14:17
     * @param: [id]
     * @return: com.qyy.oa.config.ResultData
     */
    @Override
    public ResultData getAdminDetail(Integer id) {
        if (EmptyUtil.isEmpty(id)) {
            return ResultData.error("参数错误");
        }
        return ResultData.success(adminMapper.selectAdminDetail(id));
    }

    /**
     * @author: qiyayu
     * @description: 管理员登录
     * @date: 2020-07-09 11:24
     * @param: [adminEntity]
     * @return: com.qyy.oa.config.ResultData
     */
    @Override
    public ResultData login(AdminEntity adminEntity) {
        if (EmptyUtil.isEmpty(adminEntity.getAccount()) || EmptyUtil.isEmpty(adminEntity.getPassword())) {
            return ResultData.error("参数错误");
        }
        //设置密码加密
        adminEntity.setPassword(Md5Util.md5(adminEntity.getPassword()));
        //查询管理员信息
        AdminEntity admin = adminMapper.selectAdminInfo(adminEntity);

        if (EmptyUtil.isEmpty(admin)) {
            return ResultData.error("密账号密码错误");
        } else {
            return ResultData.success(admin);
        }

    }

}
