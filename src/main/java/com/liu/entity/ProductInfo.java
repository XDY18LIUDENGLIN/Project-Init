package com.liu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author : LiuDengLin
 * Email : 2092669791@qq.com
 * date : 2022/7/17 15:08
 */
@Data
@TableName("product_info")
public class ProductInfo {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private String productName;

    private BigDecimal productPrice;

    private String productDescription;

    private Integer productStatus;
}
