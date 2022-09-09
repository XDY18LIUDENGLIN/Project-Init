package com.liu.entity.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author 王离（柳登林 wangli.liu@tuya.com）
 * @describe 图片
 * @since 2022/9/9 11:08 PM
 */
@Data
public class PictureVo {
    /**
     *
     */
    @JsonProperty("comment_total")
    private String commentTotal;
    /**
     *
     */
    @JsonProperty("created_at")
    private String createdTime;
    /**
     *
     */
    private Integer height;
    /**
     *
     */
    private Integer width;
    /**
     *
     */
    @JsonProperty("like_total")
    private Integer likeTotal;
    /**
     * 页码
     */
    @JsonProperty("page_total")
    private Integer pageTotal;
    /**
     * 原始网址
     */
    @JsonProperty("original_url")
    private String originalUrl;
    /**
     * 常规网址
     */
    @JsonProperty("regular_url")
    private String regularUrl;
    /**
     * 图片id
     */
    @JsonProperty("picture_id")
    private String pictureId;
    /**
     *
     */
    private String status;
    /**
     * 标签
     */
    private String tags;
    /**
     * 标题
     */
    private String title;
    /**
     *
     */
    private Integer type;
}
