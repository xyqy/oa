package com.qyy.oa.job;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.Page;
import com.qyy.oa.enums.YearsEnum;
import com.qyy.oa.modules.dao.UserMapper;
import com.qyy.oa.modules.dto.UserMoreDTO;
import com.qyy.oa.modules.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author: qiyayu
 * @date: 2020-07-15 13:35
 * @description: 定时任务
 */
@Component
public class DateJob {

    @Autowired
    private UserMapper userMapper;

    /**
     * 每天凌晨一点执行一次
     * 更新用户的年假时间
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void userJob() throws ParseException {

        Page<UserEntity> userEntities = userMapper.selectUserList(new UserMoreDTO());
        for (int i = 0; i < userEntities.getResult().size(); i++) {
            System.out.println(userEntities.getResult().get(i).getPassword());

            //下一年 年假 到期时间
            // 格式化一下
            String formatDate = DateUtil.formatDate(userEntities.getResult().get(i).getExpireTime());
            Date expireDate = DateUtil.parse(formatDate);
            //现在时间
            Date date = DateUtil.date();
            //差值 如果年假日期和今天一样，则年假日期往后加一年，年假天数恢复
            long betweenDay = DateUtil.between(expireDate, date, DateUnit.DAY);
            if (betweenDay == 0) {
                UserEntity user = new UserEntity();
                user.setId(userEntities.getResult().get(i).getId());
                //设置年假过期时间顺延一年
                Date newDate = DateUtil.offset(userEntities.getResult().get(i).getExpireTime(), DateField.YEAR, 1);
                //设置年假到期时间
                user.setExpireTime(newDate);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                //用户信息
                UserEntity userInfo = userMapper.getUserDetail(userEntities.getResult().get(i).getId());
                int yearsNum = getYearNums(sdf.format(userInfo.getFirstWorkTime()), sdf.format(newDate));
                //设置年假天数
                //五天假期
                if (yearsNum < YearsEnum.FIVNUMS.getCode()) {
                    user.setExpireDays(5.0);
                }//十天假期
                else if (yearsNum < YearsEnum.TENNUMS.getCode()) {
                    user.setExpireDays(10.0);
                }// 十五天假期
                else if (yearsNum > YearsEnum.FIFTEENNUMS.getCode()) {
                    user.setExpireDays(15.0);
                }
                userMapper.updateUser(user);
            }

        }
        System.out.println("【计算年假程序】开始执行：" + DateUtil.formatDateTime(new Date()));
    }

    /**
     * @author: qiyayu
     * @description: 计算两个日期相差几年
     * @date: 2020-07-15 13:28
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
