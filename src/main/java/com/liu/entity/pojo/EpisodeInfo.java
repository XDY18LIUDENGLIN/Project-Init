package com.liu.entity.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author 王离（柳登林 wangli.liu@tuya.com）
 * @describe 动漫分集信息
 * @since 2022/9/3 6:05 PM
 */
@Data
public class EpisodeInfo {
    /**
     * 分集标题
     */
    @JsonProperty("epTitle")
    private String episodeTitle;

    /**
     * 分集路径
     */
    @JsonProperty("epUrl")
    private String episodeUrl;


}
