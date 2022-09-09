package com.liu.entity.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.liu.entity.pojo.BgmCollections;
import com.liu.entity.pojo.BgmImages;
import com.liu.entity.pojo.Rating;
import lombok.Data;

import java.util.Date;

/**
 * @author LIUDENGLIN
 */
@Data
public class CalendarItemVo {
    /**
     * id
     */
    private Long id;
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
    @JsonProperty("name_cn")
    private String nameCn;
    /**
     *  概括 简介
     */
    private String summary;
    /**
     *  播出日期
     */
    @JsonProperty("air_date")
    private Date airDate;
    /**
     * 播出时间 （星期几）
     */
    @JsonProperty("air_weekday")
    private Integer airWeekday;
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
