package com.liu.service;

import com.liu.entity.vo.PictureRankVo;
import com.liu.entity.vo.PictureTagVo;
import com.liu.entity.vo.PictureVo;

import java.io.IOException;
import java.util.List;

/**
 * @author 王离（柳登林 wangli.liu@tuya.com）
 * @describ 图片服务
 * @since 2022/9/9 4:57 PM
 */
public interface PictureService {

    /**
     * 获取所有图片标签
     *
     * @return
     * @throws IOException
     */
    List<PictureTagVo> getAllPictureTag() throws IOException;

    /**
     * 获取推荐作品
     *
     * @param limit
     * @param offset
     * @return
     */
    List<PictureVo> listRecommendWorks(Integer limit, Integer offset) throws IOException;

    /**
     * 获取图片排行榜
     *
     * @param limit  页面大小
     * @param offset 偏移量
     * @param type
     * @return
     */
    List<PictureRankVo> listPictureRank(Integer limit, Integer offset, Integer type) throws IOException;

    /**
     * 获取原创作品
     *
     * @param limit
     * @param offset
     * @param sort
     * @param type
     * @return
     */
    List<PictureVo> listPublicWorks(Integer limit, Integer offset, String sort, Integer type) throws IOException;
}
