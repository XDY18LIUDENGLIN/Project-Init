package com.liu.entity.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : LiuDengLin
 * Email : 2092669791@qq.com
 * date : 2022/7/31 13:12
 * describe: XXXXXXXX
 */
@Data
public class BaGuMiRankVo implements Serializable {
    /**
     * id
     */
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 中文名称
     */
    @JsonProperty("name_cn")
    private String nameCn;
    /**
     * 排名
     */
    private Integer rank;
    /**
     * 信息提示
     */
    private String infoTip;
    /**
     * 评分
     */
    private Double score;
    /**
     * 数量
     */
    private Integer count;
    /**
     * 图片路径
     */
    private String imageUrl;
}
