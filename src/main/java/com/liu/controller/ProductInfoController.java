package com.liu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liu.entity.ProductInfo;
import com.liu.entity.vo.ProductInfoVo;
import com.liu.service.ProductInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : LiuDengLin
 * Email : 2092669791@qq.com
 * date : 2022/7/17 14:08
 */
@Api(tags = "测试控制层",value = "TEST")
@RestController
@RequestMapping("/product/product-info")
public class ProductInfoController {

    private ProductInfoService productInfoService;

    @Autowired
    public void setProductInfoService(ProductInfoService productInfoService) {
        this.productInfoService=productInfoService;
    }

    /**
     * 测试接口
     *
     * @param vo 产品对象
     * @return 数据结果
     */
    @ApiOperation("测试接口")
    @RequestMapping(value = "/findByVo",method = RequestMethod.POST)
    public ProductInfo findByVo(@Validated ProductInfoVo vo){
        ProductInfo productInfo = new ProductInfo();
        BeanUtils.copyProperties(vo,productInfo);
        return productInfoService.getOne(new QueryWrapper<>(productInfo));
    }
}
