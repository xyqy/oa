package com.qyy.oa.modules.service.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qyy.oa.config.PageView;
import com.qyy.oa.config.ResultData;
import com.qyy.oa.enums.YearsEnum;
import com.qyy.oa.modules.dao.UserMapper;
import com.qyy.oa.modules.dto.UserExcelDTO;
import com.qyy.oa.modules.dto.UserMoreDTO;
import com.qyy.oa.modules.entity.UserEntity;
import com.qyy.oa.modules.service.UserService;
import com.qyy.oa.util.EmptyUtil;
import com.qyy.oa.util.Md5Util;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: qiyayu
 * @date: 2020-07-08 11:09
 * @description: 用户
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * @author: qiyayu
     * @description: 查询用户列表
     * @date: 2020-07-08 11:16
     * @param: [userMoreDto]
     * @return: com.qyy.oa.config.ResultData
     */
    @Override
    public ResultData getUserList(UserMoreDTO userMoreDto) {
        //分页
        PageHelper.startPage(userMoreDto.getPageNum(), userMoreDto.getPageLimit());
        Page<UserEntity> userList = userMapper.selectUserList(userMoreDto);
        PageView<UserEntity> pageInfo = new PageView<>(userList);
        return ResultData.success(pageInfo);
    }

    /**
     * @author: qiyayu
     * @description: 查询单个用户详情
     * @date: 2020-07-08 11:26
     * @param: [id]
     * @return: com.qyy.oa.config.ResultData
     */
    @Override
    public ResultData getUserDetail(Integer id) {
        if (EmptyUtil.isEmpty(id)) {
            return ResultData.error("参数错误");
        }
        // 查询单个用户详情
        UserMoreDTO user = userMapper.getUserDetail(id);
        return ResultData.success(user);
    }

    /**
     * @author: qiyayu
     * @description: 新增用户
     * @date: 2020-07-08 13:10
     * @param: [userEntity]
     * @return: com.qyy.oa.config.ResultData
     **/
    @Override
    public ResultData insertUser(UserEntity userEntity) throws ParseException {
        if (EmptyUtil.isEmpty(userEntity.getAccount()) || EmptyUtil.isEmpty(userEntity.getPassword())) {
            return ResultData.error("参数错误");
        }
        //先判断用户是否存在
        int count = userMapper.selectUserAccountCount(userEntity.getAccount());
        if (count > 0) {
            return ResultData.error("该账号已存在");
        }
        // 设置密码加密
        userEntity.setPassword(Md5Util.md5(userEntity.getPassword()));
        //设置年假到期时间
        Date date = userEntity.getEntryTime();
        // 偏移一年
        Date newDate = DateUtil.offset(date, DateField.YEAR, 1);
        userEntity.setExpireTime(newDate);

        //得到工作几年
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int yearsNum = getYearNums(sdf.format(userEntity.getFirstWorkTime()), sdf.format(userEntity.getEntryTime()));
        //设置年假天数
        //五天假期
        if (yearsNum < YearsEnum.FIVNUMS.getCode()) {
            userEntity.setExpireDays(5.0);
        }//十天假期
        else if (yearsNum < YearsEnum.TENNUMS.getCode()) {
            userEntity.setExpireDays(10.0);
        }// 十五天假期
        else if (yearsNum > YearsEnum.FIFTEENNUMS.getCode()) {
            userEntity.setExpireDays(15.0);
        }

        //新增用户
        int res = userMapper.insertUser(userEntity);
        if (res > 0) {
            return ResultData.success("新增成功");
        } else {
            return ResultData.error("新增失败");
        }
    }

    /**
     * @author: qiyayu
     * @description: 更新用户
     * @date: 2020-07-08 13:26
     * @param: [userEntity]
     * @return: com.qyy.oa.config.ResultData
     **/
    @Override
    public ResultData updateUser(UserEntity userEntity) {
        if (EmptyUtil.isEmpty(userEntity.getId())) {
            return ResultData.error("参数错误");
        }
        //如果密码不为空，则更新密码
        if (!EmptyUtil.isEmpty(userEntity.getPassword())) {
            userEntity.setPassword(Md5Util.md5(userEntity.getPassword()));
        }
        //更新操作
        int res = userMapper.updateUser(userEntity);
        if (res > 0) {
            return ResultData.success("修改成功");
        } else {
            return ResultData.error("修改失败");
        }
    }

    /**
     * @author: qiyayu
     * @description: 删除用户
     * @date: 2020-07-08 13:33
     * @param: [userEntity]
     * @return: com.qyy.oa.config.ResultData
     */
    @Override
    public ResultData deleteUser(UserEntity userEntity) {
        if (EmptyUtil.isEmpty(userEntity.getId())) {
            return ResultData.error("参数错误");
        }
        //删除操作
        int res = userMapper.deleteUser(userEntity);
        if (res > 0) {
            return ResultData.success("删除成功");
        } else {
            return ResultData.error("删除失败");
        }
    }

    /**
     * @author: qiyayu
     * @description: 登录
     * @date: 2020-07-09 20:24
     * @param: [userEntity]
     * @return: com.qyy.oa.config.ResultData
     */
    @Override
    public ResultData login(UserEntity userEntity) {
        if (EmptyUtil.isEmpty(userEntity.getAccount()) || EmptyUtil.isEmpty(userEntity.getPassword())) {
            return ResultData.error("参数错误");
        }
        //设置密码加密
        userEntity.setPassword(Md5Util.md5(userEntity.getPassword()));
        //查询管理员信息
        UserEntity userInfo = userMapper.getUserInfo(userEntity);

        if (EmptyUtil.isEmpty(userInfo)) {
            return ResultData.error("密账号密码错误");
        } else {
            return ResultData.success(userInfo);
        }
    }

    /**
     * @author: qiyayu
     * @description: 导出用户表格信息
     * @date: 2020-07-15 18:57
     * @param: [response]
     * @return: void
     **/
    @Override
    public void download(HttpServletResponse response) throws IOException {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            //返回数据
            List<UserExcelDTO> list = userMapper.selectUserListForExcel();
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fileName = URLEncoder.encode(df.format(date) + "-用户信息", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

            //背景设置为白色
            WriteCellStyle headWriteCellStyle = new WriteCellStyle();
            headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
            WriteFont headWriteFont = new WriteFont();
            headWriteFont.setFontHeightInPoints((short) 13);
            headWriteCellStyle.setWriteFont(headWriteFont);
            //内容的策略
            WriteCellStyle contentWriteCellStyle = new WriteCellStyle();

            HorizontalCellStyleStrategy horizontalCellStyleStrategy =
                    new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);


            // 不关闭流
            EasyExcel.write(response.getOutputStream(), UserExcelDTO.class).autoCloseStream(Boolean.FALSE)
                    .sheet("sheet")
                    .registerWriteHandler(horizontalCellStyleStrategy)
                    .doWrite(list);
        } catch (Exception e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = new HashMap<>(16);
            map.put("code", "500");
            map.put("message", "下载文件失败" + e.getMessage());
            response.getWriter().println(JSON.toJSONString(map));
        }
    }

    /**
     * @author: qiyayu
     * @description: 计算两个日期直接相差几年
     * @date: 2020-07-14 11:42
     * @param: [str1, str2]
     * @return: java.lang.Integer
     **/
    private Integer getYearNums(String str1, String str2) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar bef = Calendar.getInstance();
        Calendar aft = Calendar.getInstance();
        bef.setTime(sdf.parse(str1));
        aft.setTime(sdf.parse(str2));
        int surplus = aft.get(Calendar.DATE) - bef.get(Calendar.DATE);
        int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
        int year = aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR);
        if (result < 0) {
            result = 1;
        } else if (result == 0) {
            result = surplus <= 0 ? 0 : 1;
        } else {
            result = 0;
        }
        return year + result;
    }

}
