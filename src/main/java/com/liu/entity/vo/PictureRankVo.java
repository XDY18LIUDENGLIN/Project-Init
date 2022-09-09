package com.liu.entity.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author 王离（柳登林 wangli.liu@tuya.com）
 * @describe 图片排行返回Vo
 * @since 2022/9/10 12:47 AM
 */
@Data
public class PictureRankVo extends PictureVo {
    @JsonProperty("ranking")
    private Ranking ranking;
}
