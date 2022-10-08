package com.liu.service;

import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.liu.entity.req.BaGuMiRankReq;
import com.liu.entity.vo.BaGuMiRankVo;
import com.liu.entity.vo.CalendarVo;

import java.io.IOException;
import java.util.List;

/**
 * @author : LiuDengLin
 * Email : 2092669791@qq.com
 * date : 2022/7/25 23:52
 * describe: Bangumi接口
 */
public interface BanGuMiSubjectApiService {
    /**
     * 获取每日更新番剧
     *
     * @return
     * @throws HttpProcessException
     */
    List<CalendarVo> listCalendar() throws HttpProcessException, JsonProcessingException;

    /**
     * 获取条目的简略信息
     *
     * @param subjectId
     * @return
     * @throws HttpProcessException
     */
    JsonNode getSimpleSubject(Integer subjectId) throws HttpProcessException, JsonProcessingException;

    /**
     * 获取条目的详细信息
     *
     * @param subjectId
     * @param pageSize
     * @param timeStamp
     * @return
     * @throws HttpProcessException
     */
    JsonNode getSubject(Integer subjectId, String pageSize, String timeStamp) throws HttpProcessException, JsonProcessingException;

    /**
     * 获取动漫排名信息
     * @param baGuMiRankVo
     * @return
     * @throws IOException
     */
    List<BaGuMiRankVo> listAnimeRankInfo(BaGuMiRankReq baGuMiRankVo) throws IOException;
}