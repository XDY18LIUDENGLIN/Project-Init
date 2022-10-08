package com.liu.rest.impl;

import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.liu.common.exception.ApiException;
import com.liu.common.statusEnum.impl.ResultCode;
import com.liu.entity.req.BaGuMiRankReq;
import com.liu.entity.req.BaGuMiSearchSubjectReq;
import com.liu.entity.vo.BaGuMiRankVo;
import com.liu.entity.vo.CalendarVo;
import com.liu.entity.vo.CherryBlossomSearchVo;
import com.liu.entity.vo.CherryBlossomVideoVo;
import com.liu.rest.IAnimeSubjectController;
import com.liu.service.BanGuMiSubjectApiService;
import com.liu.service.CherryBlossomApiService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

/**
 * @author : LiuDengLin
 * Email : 2092669791@qq.com
 * date : 2022/7/25 23:33
 * describe: BanGuMi相关接口
 */
@Slf4j
@DubboService
public class AnimeSubjectControllerImpl implements IAnimeSubjectController {
    private BanGuMiSubjectApiService banGuMiSubjectApiService;

    private CherryBlossomApiService cherryBlossomApiService;

    @Autowired
    public void setBanGuMiApiService(BanGuMiSubjectApiService banGuMiSubjectApiService) {
        this.banGuMiSubjectApiService = banGuMiSubjectApiService;
    }

    @Autowired
    public void setCherryBlossomApiService(CherryBlossomApiService cherryBlossomApiService) {
        this.cherryBlossomApiService = cherryBlossomApiService;
    }

    public List<CalendarVo> listBanGuMiCalendar(){
        try {
            return banGuMiSubjectApiService.listCalendar();
        } catch (HttpProcessException e) {
            throw new ApiException(ResultCode.ANIME_NETWORK_REQUEST_LESS);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public JsonNode getBanGuMiSubjectById(Integer subjectId){
        try {
            return banGuMiSubjectApiService.getSimpleSubject(subjectId);
        } catch (HttpProcessException e) {
            throw new ApiException(ResultCode.ANIME_NETWORK_REQUEST_LESS);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public JsonNode getBanGuMiSubjectAll(Integer subjectId,String responseGroup, String timeStamp) {
        try {
            return banGuMiSubjectApiService.getSubject(subjectId, responseGroup, timeStamp);
        } catch (HttpProcessException e) {
            throw new ApiException(ResultCode.ANIME_NETWORK_REQUEST_LESS);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public List<BaGuMiRankVo> getBanGuMiAnimeRanking(BaGuMiRankReq baGuMiRankVo){
        try {
            return banGuMiSubjectApiService.listAnimeRankInfo(baGuMiRankVo);
        } catch (IOException e) {
            throw new ApiException(ResultCode.ANIME_REQUEST_IO_EXCEPTION);
        }
    }

    public List<CherryBlossomSearchVo> getSearchInfo(String animeName){
        try {
            return cherryBlossomApiService.getSearchInfo(animeName);
        } catch (IOException e) {
            throw new ApiException(ResultCode.ANIME_REQUEST_IO_EXCEPTION);
        }
    }

    public CherryBlossomVideoVo getVideoUrl(String url) {
        try {
            return cherryBlossomApiService.getVideoUrl(url);
        } catch (IOException e) {
            throw new ApiException(ResultCode.ANIME_REQUEST_IO_EXCEPTION);
        }
    }

    public List<Object> getSearch(BaGuMiSearchSubjectReq baGuMiSearchSubjectReq){
        return null;
    }
}
