package com.qyy.oa.util;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author: qiyayu
 * @date: 2020-07-08 17:40
 * @description: 计算时间工具
 */
public class DaysUtils {

    /**
     * @author: qiyayu
     * @description: 计算两个日期间隔时间，排除节假日
     * @date: 2020-07-08 17:40
     * @param: [startTime, endTime]
     * @return: double
     **/
    public static double calLeaveDays(Date startTime, Date endTime) {
        double leaveDays = 0;
        //从startTime开始循环，若该日期不是节假日或者不是周六日则请假天数+1
        //设置循环开始日期
        Date flag = startTime;

        Calendar cal = Calendar.getInstance();
        //循环遍历每个日期
        while (flag.compareTo(endTime) != 1) {
            cal.setTime(flag);
            //判断是否为周六日
            int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
            //0为周日，6为周六
            if (week == 0 || week == 6) {
                //跳出循环进入下一个日期
                cal.add(Calendar.DAY_OF_MONTH, +1);
                flag = cal.getTime();
                continue;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String day = sdf.format(flag);
            //如果存在这个日期则跳过
            if (!DaysUtils.daysList().contains(day)) {
                //不是节假日或者周末，天数+1
                leaveDays = leaveDays + 1;
            }

            //日期往后加一天
            cal.add(Calendar.DAY_OF_MONTH, +1);
            flag = cal.getTime();

        }
        return leaveDays;
    }


    /**
     * 所有节假日列表
     *
     * @author: qiyayu
     * @description: 所有节假日列表
     * @date: 2020-07-13 15:16
     * @param: []
     * @return: java.util.List<java.lang.String>
     **/
    public static List<String> daysList() {

        //当前时间
        DateTime dateTime = new DateTime(DateUtil.now(), DatePattern.NORM_DATETIME_FORMAT);
        //获取今年
        int year = dateTime.year();

        // 2020 年假期
        List<String> holiday2020 = new ArrayList<String>() {
            private static final long serialVersionUID = -6163763306078892945L;

            {
                this.add("2020-01-01");
                this.add("2020-01-24");
                this.add("2020-01-25");
                this.add("2020-01-26");
                this.add("2020-01-27");
                this.add("2020-01-28");
                this.add("2020-01-29");
                this.add("2020-01-30");
                this.add("2020-01-31");
                this.add("2020-02-01");
                this.add("2020-04-04");
                this.add("2020-04-05");
                this.add("2020-04-06");
                this.add("2020-05-01");
                this.add("2020-05-02");
                this.add("2020-05-03");
                this.add("2020-05-04");
                this.add("2020-05-05");
                this.add("2020-06-25");
                this.add("2020-06-26");
                this.add("2020-06-27");
                this.add("2020-10-01");
                this.add("2020-10-02");
                this.add("2020-10-03");
                this.add("2020-10-04");
                this.add("2020-10-05");
                this.add("2020-10-06");
                this.add("2020-10-07");
                this.add("2020-10-08");
            }
        };


        //2021 年假期
        List<String> holiday2021 = new ArrayList<String>() {

            private static final long serialVersionUID = 2184459189879229790L;

            {
                this.add("2021-01-01");
            }
        };

        //判断今年
        switch (year) {
            case 2020:
                return holiday2020;
            case 2021:
                return holiday2021;
            default:
        }
        return new ArrayList<String>() {
            private static final long serialVersionUID = 2247054294374914761L;
            {
            }
        };
    }
}
