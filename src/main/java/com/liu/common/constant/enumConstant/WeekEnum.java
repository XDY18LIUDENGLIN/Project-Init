package com.liu.common.constant.enumConstant;

/**
 * @author : LiuDengLin
 * Email : 2092669791@qq.com
 * date : 2022/7/26 20:56
 * describe: 日期枚举
 */
public enum WeekEnum {
    WEEKDAY_MONDAY("月耀日", "星期一", "MONDAY"), WEEKDAY_TUESDAY("火耀日", "星期二", "TUESDAY"),
    WEEKDAY_WEDNESDAY("水耀日", "星期三", "WEDNESDAY"), WEEKDAY_THURSDAY("木耀日", "星期四", "THURSDAY"),
    WEEKDAY_FRIDAY("金耀日", "星期五", "FRIDAY"), WEEKDAY_SATURDAY("土耀日", "星期六", "SATURDAY"),
    WEEKDAY_SUNDAY("日耀日", "星期天", "SUNDAY");
    /**
     * 星耀计算方式
     */
    private String starWeek;
    /**
     * 日常计算方式
     */
    private String overWeek;
    /**
     * 英文计算方式
     */
    private String enWeek;

    WeekEnum(String starWeek, String overWeek, String enWeek) {
        this.starWeek = starWeek;
        this.overWeek = overWeek;
        this.enWeek = enWeek;
    }
}
