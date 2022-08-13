package com.liu.entity.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author LIUDENGLIN
 */
@Data
public class CalendarItem implements Serializable {
    /**
     * Id
     */
    private Integer id;
    /**
     * url
     */
    private String url;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 名字
     */
    private String name;
    /**
     * 中文名
     */
    private String nameCn;
    /**
     * 概括
     */
    private String summary;
    /**
     * 条目数
     */
    private Integer total;
    /**
     * 分数
     */
    private Float score;
    /**
     * 等级
     */
    private Integer rank;
    /**
     *
     */
    private String large;
    /**
     * 常见的
     */
    private String common;
    /**
     *
     */
    private String small;
    /**
     * 来源
     */
    private String medium;
    /**
     *
     */
    private String grid;
    /**
     *
     */
    private Integer doing;
    /**
     * 播出日期
     */
    private Date airDate;
    /**
     * 星耀日
     */
    private Integer airWeekday;
    /**
     * 直接存放json格式文件
     */
    private String count;
}
