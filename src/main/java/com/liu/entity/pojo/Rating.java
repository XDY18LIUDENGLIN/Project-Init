package com.liu.entity.pojo;

import lombok.Data;

/**
 * @author LIUDENGLIN
 *         评分
 */
@Data
public class Rating {
    /**
     * 条目数
     */
    private Integer total;
    /**
     * 这个是json文件转成object  前端再做解析
     */
    private Object count;
    /**
     * 评分
     */
    private Float score;
}
