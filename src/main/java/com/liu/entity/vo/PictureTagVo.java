package com.liu.entity.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 王离（柳登林 wangli.liu@tuya.com）
 * @describe 标签Vo
 * @since 2022/9/9 4:59 PM
 */
@Data
public class PictureTagVo implements Serializable {

    /**
     * tagID
     */
    @JsonProperty("tag_id")
    private String tagId;

    /**
     * 标题
     */
    @JsonProperty("title")
    private String title;

    /**
     * 中文名字
     */
    @JsonProperty("zh")
    private String zhTitle;
}
