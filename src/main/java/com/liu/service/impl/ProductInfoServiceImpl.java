package com.liu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liu.entity.ProductInfo;
import com.liu.mapper.ProductInfoDao;
import com.liu.service.ProductInfoService;
import org.springframework.stereotype.Service;

/**
 * @author : LiuDengLin
 * Email : 2092669791@qq.com
 * date : 2022/7/17 14:11
 */
@Service
public class ProductInfoServiceImpl extends ServiceImpl<ProductInfoDao, ProductInfo> implements ProductInfoService {
}
