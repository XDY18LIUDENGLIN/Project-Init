package com.liu.entity.req;

import lombok.Data;

/**
 * @author : LiuDengLin
 * Email : 2092669791@qq.com
 * date : 2022/7/28 23:47
 * describe: bagumi动漫排行查询vo
 */
@Data
public class BaGuMiRankReq {
    /**
     *   类型 只接受五种 分别是web tv movie ova misc
     */
    private String type;
    /**
     * 时间 格式为2022-1或者2022
     */
    private String airtime;
    /**
     * 排序方式 只接受三种 rank date title
     */
    private String sort;
    /**
     * order_by 可以以某个字母为开头进行筛选 只接受a-z
     */
    private String order;
    /**
     * 页数 一页最多为20条数据
     */
    private Integer page;
    /**
     * 标签 可接受任意标签
     */
    private String tag;
    /**
     * 大类 接受五个类型 分别为music real book anime game
     */
    private String bigType;
}
