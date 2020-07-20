package com.qyy.oa.modules.vacation.service.impl;

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
import com.qyy.oa.enums.BooleanEnum;
import com.qyy.oa.enums.VacationTypeEnum;
import com.qyy.oa.modules.admin.dao.AdminMapper;
import com.qyy.oa.modules.admin.dto.AdminMoreDto;
import com.qyy.oa.modules.admin.entity.AdminEntity;
import com.qyy.oa.modules.user.dao.UserMapper;
import com.qyy.oa.modules.user.entity.UserEntity;
import com.qyy.oa.modules.vacation.dao.VacationMapper;
import com.qyy.oa.modules.vacation.dto.VacationCountDto;
import com.qyy.oa.modules.vacation.dto.VacationExcelDto;
import com.qyy.oa.modules.vacation.dto.VacationMoreDto;
import com.qyy.oa.modules.vacation.entity.VacationEntity;
import com.qyy.oa.modules.vacation.service.VacationService;
import com.qyy.oa.modules.vacationtype.dao.VacationTypeMapper;
import com.qyy.oa.modules.vacationtype.entity.VacationTypeEntity;
import com.qyy.oa.util.DaysUtils;
import com.qyy.oa.util.EmptyUtil;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: qiyayu
 * @date: 2020-07-08 14:27
 * @description: 请假
 */
@Service
public class VacationServiceImpl implements VacationService {

    @Resource
    private VacationMapper vacationMapper;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private VacationTypeMapper vacationTypeMapper;

    @Value("${emailFrom}")
    private String emailFrom;

    /**
     * @author: qiyayu
     * @description: 请假信息列表
     * @date: 2020-07-08 14:32
     * @param: [vacationMoreDto]
     * @return: com.qyy.oa.config.ResultData
     */
    @Override
    public ResultData getVacationList(VacationMoreDto vacationMoreDto) {
        //分页
        PageHelper.startPage(vacationMoreDto.getPageNum(), vacationMoreDto.getPageLimit());
        Page<VacationEntity> dList = vacationMapper.selectVacationList(vacationMoreDto);
        PageView<VacationEntity> pageInfo = new PageView<>(dList);
        return ResultData.success(pageInfo);
    }

    /**
     * @author: qiyayu
     * @description: 新增请假信息
     * @date: 2020-07-08 15:03
     * @param: [vacationEntity]
     * @return: com.qyy.oa.config.ResultData
     */
    @Override
    public ResultData insertVacation(VacationEntity vacationEntity) throws Exception {
        //校验参数
        if (EmptyUtil.isEmpty(vacationEntity.getUserId()) || EmptyUtil.isEmpty(vacationEntity.getVacationTypeId())) {
            return ResultData.error("参数错误");
        }
        // 将所有的请假信息都完成才能再次填写
        int count = vacationMapper.selectUserCount(vacationEntity);
        if (count > 0) {
            return ResultData.error("目前有尚未完成的请假信息，请完成后再次请假");
        }


        //校验请假信息参数
        Map<String, String> map = insertOrUpdateValidate(vacationEntity);
        String flag = map.get("flag");
        if (BooleanEnum.FALSE.getName().equalsIgnoreCase(flag)) {
            return ResultData.error(map.get("message"));
        }

        //计算请假时长 排除周末，排除节假日，但是只有一整天，没有0.5天
        double days = DaysUtils.calLeaveDays(vacationEntity.getStartTime(), vacationEntity.getEndTime());
        //设置请假时长
        vacationEntity.setDays(days);

        //新增到数据库
        int res = vacationMapper.insertVacation(vacationEntity);
        if (res < 0) {
            return ResultData.error("新增失败");
        } else {
            return ResultData.success("新增成功");
        }

    }

    /**
     * @author: qiyayu
     * @description: 查询单个请假信息
     * @date: 2020-07-08 20:24
     * @param: [id]
     * @return: com.qyy.oa.config.ResultData
     */
    @Override
    public ResultData getVacationDetail(Integer id) {
        //校验参数
        if (EmptyUtil.isEmpty(id)) {
            return ResultData.error("参数错误");
        }
        //详情信息
        VacationMoreDto vacationMoreDto = vacationMapper.selectVacationDetail(id);
        return ResultData.success(vacationMoreDto);
    }

    /**
     * @author: qiyayu
     * @description: 修改请假信息
     * @date: 2020-07-09 09:56
     * @param: [vacationEntity]
     * @return: com.qyy.oa.config.ResultData
     **/
    @Override
    public ResultData updateVacation(VacationEntity vacationEntity) throws Exception {
        //校验参数
        if (EmptyUtil.isEmpty(vacationEntity.getId())) {
            return ResultData.error("参数错误");
        }

        //如果状态为审批了则不等进行修改
        VacationMoreDto vacationMoreDto = vacationMapper.selectVacationDetail(vacationEntity.getId());
        // 默认0：审批中，1，同意，2不通过
        if (0 != vacationMoreDto.getState()) {
            return ResultData.error("该假条已经审批，不能修改");
        }

        //校验请假信息参数
        Map<String, String> map = insertOrUpdateValidate(vacationEntity);
        String flag = map.get("flag");
        if (BooleanEnum.FALSE.getName().equalsIgnoreCase(flag)) {
            return ResultData.error(map.get("message"));
        }

        //如果请假时间不为空的话，重新计算请假天数
        if (!(EmptyUtil.isEmpty(vacationEntity.getStartTime()) || EmptyUtil.isEmpty(vacationEntity.getEndTime()))) {

            //请假天数
            double days = DaysUtils.calLeaveDays(vacationEntity.getStartTime(), vacationEntity.getEndTime());

            //设置请假时长
            vacationEntity.setDays(days);
        }

        //更新操作
        int res = vacationMapper.updateVacation(vacationEntity);
        if (res > 0) {
            return ResultData.success("修改成功");
        } else {
            return ResultData.error("修改失败");
        }
    }

    /**
     * @author: qiyayu
     * @description: 删除请假信息
     * @date: 2020-07-09 10:11
     * @param: [vacationEntity]
     * @return: com.qyy.oa.config.ResultData
     */
    @Override
    public ResultData deleteVacation(VacationEntity vacationEntity) {
        //校验参数
        if (EmptyUtil.isEmpty(vacationEntity.getId())) {
            return ResultData.error("参数错误");
        }
        //如果状态为审批了则不等进行修改
        VacationMoreDto vacationMoreDto = vacationMapper.selectVacationDetail(vacationEntity.getId());
        // 默认0：1审批中，2，同意，3不通过
        if (0 != vacationMoreDto.getState()) {
            return ResultData.error("该假条已经审批，不能删除");
        }
        //删除操作
        int res = vacationMapper.deleteVacation(vacationEntity);
        if (res > 0) {
            return ResultData.success("删除成功");
        } else {
            return ResultData.error("删除失败");
        }
    }

    /**
     * @author: qiyayu
     * @description: 管理员审批请假
     * @date: 2020-07-09 10:23
     * @param: [vacationEntity]
     * @return: com.qyy.oa.config.ResultData
     */
    @Override
    public ResultData modifyVacation(VacationEntity vacationEntity) {
        //校验参数
        if (EmptyUtil.isEmpty(vacationEntity.getId()) || EmptyUtil.isEmpty(vacationEntity.getState())) {
            return ResultData.error("参数错误");
        }

        //判断状态是否已经审批了
        VacationMoreDto vacationMoreDto = vacationMapper.selectVacationDetail(vacationEntity.getId());
        // 默认0  1审批中， 2同意，3不通过
        if (1 != vacationMoreDto.getState()) {
            return ResultData.error("该假条已经审批");
        }

        //如果是年假 减少年假时间
        if (VacationTypeEnum.YEARVACATION.getCode() == vacationMoreDto.getVacationTypeId()) {
            //再次判断用户的年假剩余时间
            UserEntity user = userMapper.getUserDetail(vacationMoreDto.getUserId());

            //如果请假信息上的天数大于用户剩余的天数则不能通过
            if (vacationMoreDto.getDays() > user.getExpireDays()) {
                return ResultData.error("该用户年假时间超过时长");
            }

            // 用户剩余年假信息
            double days = user.getExpireDays() - vacationMoreDto.getDays();
            UserEntity updateUser = new UserEntity();
            updateUser.setId(vacationMoreDto.getUserId());
            updateUser.setExpireDays(days);
            userMapper.updateUser(updateUser);
        }

        //审批操作
        int res = vacationMapper.updateVacation(vacationEntity);
        if (res < 0) {
            return ResultData.error("审批失败");
        }


        //先查询用户信息
        UserEntity userEntity = userMapper.getUserDetail(vacationMoreDto.getUserId());

        //如果用户有邮件
        if (!(EmptyUtil.isEmpty(userEntity.getEmail()))) {

            //判断是否在正确的邮箱地址
            if (isEmail(userEntity.getEmail())) {
                Map<String, Object> map = new HashMap<>(16);
                //邮件标题
                map.put("title", userEntity.getName() + "的请假信息");
                // 邮件内容
                map.put("text", "您的请假信息已经审批，请登录系统查看");
                // 邮件接收者
                map.put("to", userEntity.getEmail());
                //发送邮件
                sendMail(map);
            }
        }
        return ResultData.success("审批成功");
    }

    /**
     * @author: qiyayu
     * @description: 计算请假时长
     * @date: 2020-07-10 10:24
     * @param: [vacationEntity]
     * @return: com.qyy.oa.config.ResultData
     */
    @Override
    public ResultData countDays(VacationEntity vacationEntity) {
        //判断参数
        if (EmptyUtil.isEmpty(vacationEntity.getStartTime()) && EmptyUtil.isEmpty(vacationEntity.getEndTime())) {
            return ResultData.error("缺少参数");
        }

        //计算请假时长
        double days = DaysUtils.calLeaveDays(vacationEntity.getStartTime(), vacationEntity.getEndTime());

        return ResultData.success(days);
    }

    /**
     * @author: qiyayu
     * @description: 统计请假信息
     * @date: 2020-07-10 11:40
     * @param: []
     * @return: com.qyy.oa.config.ResultData
     **/
    @Override
    public ResultData countVacation(VacationCountDto vacationCountDto) {
        //按照月份统计信息
        List<VacationCountDto> list = vacationMapper.countVacation(vacationCountDto);

        return ResultData.success(list);
    }

    /**
     * @author: qiyayu
     * @description: 提交审批信息
     * @date: 2020-07-13 14:16
     * @param: [vacationEntity]
     * @return: com.qyy.oa.config.ResultData
     **/
    @Override
    public ResultData changeVacationState(VacationEntity vacationEntity) {
        //校验参数
        if (EmptyUtil.isEmpty(vacationEntity.getId())) {
            return ResultData.error("参数错误");
        }
        //如果状态为审核或未提交则不等进行修改
        VacationMoreDto vacationMoreDto = vacationMapper.selectVacationDetail(vacationEntity.getId());
        if (0 != vacationMoreDto.getState()) {
            return ResultData.error("已经提交过或审核过");
        }
        //提交操作
        vacationEntity.setState(1);
        int res = vacationMapper.updateVacation(vacationEntity);
        if (res < 0) {
            return ResultData.error("提交失败");
        }

        // 发送邮件
        //查询该用户的部门id
        UserEntity userEntity = userMapper.getUserDetail(vacationMoreDto.getUserId());

        //查询相同部门的管理员人员信息,发送邮件提醒
        AdminMoreDto adminMoreDto = new AdminMoreDto();
        adminMoreDto.setDepartmentId(userEntity.getDepartmentId());
        //该用户的同一部门的所有上级
        List<AdminEntity> adminList = adminMapper.selectAdminList(adminMoreDto);
        //如果结果不为空 发送邮件
        if (!EmptyUtil.isEmpty(adminList)) {

            for (AdminEntity adminEntity : adminList) {
                //如果管理员有有邮件 并且是一个部门
                if (!EmptyUtil.isEmpty(adminEntity.getEmail()) && (adminEntity.getDepartmentId().equals(userEntity.getDepartmentId()))) {

                    //判断是否是有效邮箱
                    if (isEmail(adminEntity.getEmail())) {
                        //查询请假类型
                        VacationTypeEntity vacationTypeEntity = vacationTypeMapper.selectVacationDetail(vacationMoreDto.getVacationTypeId());

                        Map<String, Object> map = new HashMap<>(16);
                        //邮件标题
                        map.put("title", userEntity.getName() + "的请假信息");
                        // 邮件内容
                        map.put("text", userEntity.getName() + "的请假信息，请假类型为:" + vacationTypeEntity.getName() + "。\n更多信息请登录系统查看");
                        // 邮件接收者
                        map.put("to", adminEntity.getEmail());
                        //发送邮件
                        sendMail(map);
                    }
                }
            }
        }

        return ResultData.success("提交成功");
    }

    /**
     * @author: qiyayu
     * @description: 下载文件
     * @date: 2020-07-15 18:49
     * @param: [response]
     * @return: void
     **/
    @Override
    public void download(HttpServletResponse response) throws IOException {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            //返回数据
            List<VacationMoreDto> list = vacationMapper.selectVacationListForExcel();

            List<VacationExcelDto> returnList = new ArrayList<>(16);
            for (int i = 0; i < list.size(); i++) {
                VacationExcelDto v = new VacationExcelDto();
                v.setId(i + 1);
                v.setCode(list.get(i).getCode());
                v.setDepartmentName(list.get(i).getDepartmentName());
                v.setDays(list.get(i).getDays());
                v.setEndTime(list.get(i).getEndTime());
                v.setStartTime(list.get(i).getStartTime());
                v.setUserName(list.get(i).getUserName());
                v.setVacationTypeName(list.get(i).getVacationTypeName());
                if (1 == list.get(i).getState()) {
                    v.setState("等待审批");
                }
                if (2 == list.get(i).getState()) {
                    v.setState("通过申请");
                }
                if (3 == list.get(i).getState()) {
                    v.setState("审批拒绝");
                }
                returnList.add(v);
            }
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fileName = URLEncoder.encode(df.format(date) + "-请假信息", "UTF-8");
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
            EasyExcel.write(response.getOutputStream(), VacationExcelDto.class).autoCloseStream(Boolean.FALSE)
                    .sheet("sheet")
                    .registerWriteHandler(horizontalCellStyleStrategy)
                    .doWrite(returnList);
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
     * @description: 统计请假类型信息
     * @date: 2020-07-16 11:10
     * @param: []
     * @return: com.qyy.oa.config.ResultData
     **/
    @Override
    public ResultData countVacationType() {
        //统计请假类型信息
        List<VacationCountDto> vacationCountDtoList = vacationMapper.countVacationType();

        return ResultData.success(vacationCountDtoList);
    }

    /**
     * @author: qiyayu
     * @description: 发送邮件
     * @date: 2020-07-08 17:30
     * @param: [map]
     * @return: void
     **/
    private void sendMail(Map<String, Object> map) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setSubject(map.get("title").toString());
        msg.setText(map.get("text").toString());
        msg.setFrom(emailFrom);
        msg.setSentDate(new Date());
        msg.setTo(map.get("to").toString());
        javaMailSender.send(msg);
    }

    /**
     * @author: qiyayu
     * @description: 判断是否是正确邮箱
     * @date: 2020-07-13 14:27
     * @param: [email]
     * @return: boolean
     **/
    private boolean isEmail(String email) {
        if (null == email || "".equals(email)) {
            return false;
        }
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p = Pattern.compile(regEx1);
        Matcher m = p.matcher(email);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断一个日期是否在两个日期内
     *
     * @param nowDate   现在时间
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @author: qiyayu
     * @description: 判断一个日期是否在两个日期内
     * @date: 2020-07-14 15:40
     * @return: boolean
     **/
    private boolean dateBetween(Date nowDate, Date startDate, Date endDate) {

        long nowTime = nowDate.getTime();
        long startTime = startDate.getTime();
        long endTime = endDate.getTime();

        return nowTime >= startTime && nowTime <= endTime;
    }

    /**
     * @author: qiyayu
     * @description: 校验新增或者修改的请假事件
     * @date: 2020-07-14 17:06
     * @param: [vacationEntity]
     * @return: java.util.Map<java.lang.String, java.lang.String>
     **/
    private Map<String, String> insertOrUpdateValidate(VacationEntity vacationEntity) throws Exception {
        Map<String, String> map = new HashMap<>(16);

        // 判断当前请假时间是否已经请过
        VacationEntity vStartTime = new VacationEntity();
        VacationEntity vEndTime = new VacationEntity();
        //开始的时间
        vStartTime.setUserId(vacationEntity.getUserId());
        vStartTime.setStartTime(vacationEntity.getStartTime());
        //结束的时间
        vEndTime.setUserId(vacationEntity.getUserId());
        vEndTime.setStartTime(vacationEntity.getStartTime());
        if (vacationMapper.selectUserVacationCount(vStartTime) > 0 && vacationMapper.selectUserVacationCount(vEndTime) > 0) {
            map.put("message", "该日期已经被请过");
            map.put("flag", "false");
            return map;
        }

        //计算请假时长 排除周末，排除节假日，但是只有一整天，没有0.5天
        double days = DaysUtils.calLeaveDays(vacationEntity.getStartTime(), vacationEntity.getEndTime());
        if (days == 0) {
            map.put("message", "请重新填写日期");
            map.put("flag", "false");
            return map;
        }
        // 判断年假  3:年假
        if (vacationEntity.getVacationTypeId() == VacationTypeEnum.YEARVACATION.getCode()) {
            UserEntity user = userMapper.getUserDetail(vacationEntity.getUserId());
            // 用户年假剩余天数
            if (user.getExpireDays() <= 0) {
                map.put("message", "年假时间用完了");
                map.put("flag", "false");
                return map;
            }

            if (user.getExpireDays() - days < 0) {
                map.put("message", "一共请假时长大于剩余年假时间");
                map.put("flag", "false");
                return map;
            }

            //下次年假日期减去一年
            String nextDateTime = dateMinusYear(DateUtil.formatDateTime(user.getExpireTime()));

            if (!(dateBetween(vacationEntity.getStartTime(), DateUtil.parse(nextDateTime), user.getExpireTime())) ||
                    !(dateBetween(vacationEntity.getEndTime(), DateUtil.parse(nextDateTime), user.getExpireTime()))) {
                map.put("message", "年假时间超过范围");
                map.put("flag", "false");
                return map;
            }
        }
        map.put("flag", "true");
        return map;
    }

    /**
     * @author: qiyayu
     * @description: 日期减去一年
     * @date: 2020-07-16 15:53
     * @param: [str]
     * @return: java.lang.String
     **/
    private String dateMinusYear(String str) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dt = sdf.parse(str);
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        // 日期减1年
        rightNow.add(Calendar.YEAR, -1);
        Date dt1 = rightNow.getTime();
        return sdf.format(dt1);
    }
}
