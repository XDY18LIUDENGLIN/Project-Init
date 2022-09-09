package com.liu.entity.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author 王离（柳登林 wangli.liu@tuya.com）
 * @describe 图片排行Vo
 * @since 2022/9/10 12:48 AM
 */
@Data
public class Ranking {
    /**
     * 企业编号
     */
    @JsonProperty("bussiness_id")
    private String bussinessId;

    @JsonProperty("created_at")
    private String createdTime;

    @JsonProperty("updated_at")
    private String updatedTime;
    /**
     * 最后排名
     */
    @JsonProperty("last_sort")
    private Integer lastSort;

    @JsonProperty("ranking_date")
    private String rankDate;
    /**
     * 排名
     */
    private Integer sort;
    /**
     *
     */
    private Integer status;
    /**
     *
     */
    private Integer type;
}
