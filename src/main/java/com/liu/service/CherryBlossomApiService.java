package com.liu.service;

import com.liu.entity.vo.CherryBlossomSearchVo;
import com.liu.entity.vo.CherryBlossomVideoVo;

import java.io.IOException;
import java.util.List;

/**
 * @author 王离（柳登林 wangli.liu@tuya.com）
 * @describe 樱花动漫接口
 * @since 2022/9/3 5:53 PM
 */
public interface CherryBlossomApiService {

    /**
     * 动漫检索信息
     *
     * @param keyWord 检索关键字
     * @return
     */
    List<CherryBlossomSearchVo> getSearchInfo(String keyWord) throws IOException;

    /**
     * 获取动漫视频url
     *
     * @param url 分集url地址
     * @return
     */
    CherryBlossomVideoVo getVideoUrl(String url) throws IOException;
}
