package com.liu.entity.vo;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author : LiuDengLin
 * Email : 2092669791@qq.com
 * date : 2022/7/17 14:18
 */
@Data
public class ProductInfoVo {
    /**
     * 产品名称
     */
    @NotNull(message = "产品名称不允许为空")
    private String productName;

    /**
     * 产品价格
     */
    @Min(value = 0,message = "商品价格不允许为负数")
    private BigDecimal productPrice;

    /**
     * 上架状态
     */
    private Integer productStatus;
}
