package com.liu.entity.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.liu.entity.pojo.EpisodeInfo;
import lombok.Data;

import java.util.List;

/**
 * @author 王离（柳登林 wangli.liu@tuya.com）
 * @describe 樱花动漫视频Vo
 * @since 2022/9/3 11:03 PM
 */
@Data
public class CherryBlossomVideoVo {
    /**
     * 标题
     */
    private String title;

    /**
     * 视频url
     */
    private String videoUrl;

    /**
     * 分集信息
     */
    @JsonProperty("epInfo")
    private List<EpisodeInfo> episodeInfoList;
}
