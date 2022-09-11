package com.liu.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.liu.entity.vo.AuthorInfoVo;
import com.liu.entity.vo.PictureInfoVo;
import com.liu.entity.vo.PictureRankVo;
import com.liu.entity.vo.PictureTagVo;
import com.liu.entity.vo.PictureVo;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

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

    /**
     * 通过图片id获取图片详情
     * @param pictureId
     * @return
     */
    PictureInfoVo getPictureInfo(String pictureId) throws IOException;

    /**
     * 获取作者最好的图片
     * @param limit
     * @param offset
     * @param pictureId
     * @param userId
     * @return
     */
    List<PictureVo> listBestPictureInAuthor(Integer limit, Integer offset, String pictureId, String userId) throws IOException;

    /**
     * 得到指定作者的所有作品
     * @param sort
     * @param type
     * @param userId
     * @param limit
     * @param offset
     * @return
     */
    List<PictureVo> listAllPictureByUserId(String sort, Integer type, String userId, Integer limit, Integer offset)
            throws IOException;

    /**
     * 检索图片
     * @param type
     * @param keyword
     * @param limit
     * @param offset
     * @return
     */
    Pair<List<AuthorInfoVo>, List<PictureVo>> listSearch(String type, String keyword, Integer limit, Integer offset) throws IOException;
}
