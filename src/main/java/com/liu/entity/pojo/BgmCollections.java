package com.liu.entity.pojo;

import lombok.Data;

/**
 * @author LIUDENGLIN
 */
@Data
public class BgmCollections {
    /**
     * 在看人数
     */
    private Integer doing;
    /**
     * 搁置
     */
    private Integer onHold;
    /**
     * 抛弃
     */
    private Integer dropped;
    /**
     * 看过
     */
    private Integer collect;
    /**
     * 想看
     */
    private Integer wish;
}
