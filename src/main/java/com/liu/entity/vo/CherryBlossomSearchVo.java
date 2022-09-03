package com.liu.entity.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.liu.entity.pojo.EpisodeInfo;
import lombok.Data;

import java.util.List;

/**
 * @author 王离（柳登林 wangli.liu@tuya.com）
 * @describe 樱花动漫检索Vo
 * @since 2022/9/3 5:57 PM
 */
@Data
public class CherryBlossomSearchVo {
    /**
     * 检索标题
     */
    @JsonProperty("title")
    private String animeTitle;
    /**
     * 分集信息列表
     */
    @JsonProperty("epInfo")
    private List<EpisodeInfo> episodeInfo;
}
