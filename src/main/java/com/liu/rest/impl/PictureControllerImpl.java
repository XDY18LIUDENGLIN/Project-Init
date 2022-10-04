package com.liu.rest.impl;

import com.liu.rest.IPictureController;
import com.liu.entity.vo.AuthorInfoVo;
import com.liu.entity.vo.PictureInfoVo;
import com.liu.entity.vo.PictureRankVo;
import com.liu.entity.vo.PictureTagVo;
import com.liu.entity.vo.PictureVo;
import com.liu.service.PictureService;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @author 王离（柳登林 wangli.liu@tuya.com）
 * @describe 图片Api
 * @since 2022/9/4 11:37 PM
 */
@DubboService
public class PictureControllerImpl implements IPictureController {

    private PictureService pictureService;

    @Autowired
    public void setPictureService(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    public List<PictureTagVo> getAllPictureTag(){
        try {
            return pictureService.getAllPictureTag();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<PictureRankVo> listPictureRank(Integer limit,Integer offset,Integer type){
        try {
            return pictureService.listPictureRank(limit, offset, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public PictureInfoVo getPictureInfo(String pictureId){
        try {
            return pictureService.getPictureInfo(pictureId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<PictureVo> listRecommendedWorks(Integer limit, Integer offset){
        try {
            return pictureService.listRecommendWorks(limit, offset);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<PictureVo> listPublicWorks(Integer limit,Integer offset,String sort, Integer type){
        try {
            return pictureService.listPublicWorks(limit, offset, sort, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<PictureVo> listBestPictureInAuthor(Integer limit,Integer offset,String pictureId,String userId){
        try {
            return pictureService.listBestPictureInAuthor(limit,offset, pictureId, userId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<PictureVo> listAllPictureByUserId(String userId,Integer limit,Integer offset,Integer type,String sort){
        try {
            return pictureService.listAllPictureByUserId(sort, type, userId, limit, offset);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Pair<List<AuthorInfoVo>, List<PictureVo>> listSearch(String type, String keyword,Integer limit,Integer offset){
        try {
            return pictureService.listSearch(type, keyword, limit, offset);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
