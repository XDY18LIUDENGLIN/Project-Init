package com.liu.entity.transfer;

import lombok.Data;

/**
 * @author LIUDENGLIN
 */
@Data
public class CalendarItemVo {
    /**
     * id
     */
    private Integer id;
    /**
     * 封面url
     */
    private String url;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 名称
     */
    private String name;
    /**
     *  中文名称
     */
    private String name_cn;
    /**
     *  概括 简介
     */
    private String summary;
    /**
     *  播出日期
     */
    private String air_date;
    /**
     * 播出时间 （星期几）
     */
    private Integer air_weekday;
    /**
     * 权重
     */
    private Integer rank;
    /**
     * 评分
     */
    private Rating rating;
    /**
     * 图片
     */
    private BgmImages images;
    /**
     * bgm连接
     */
    private BgmCollections collection;
}
