package com.liu.rest.impl;

import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.liu.entity.req.BaGuMiRankReq;
import com.liu.entity.vo.BaGuMiRankVo;
import com.liu.entity.vo.CalendarVo;
import com.liu.entity.vo.CherryBlossomSearchVo;
import com.liu.entity.vo.CherryBlossomVideoVo;
import com.liu.rest.IAnimeController;
import com.liu.service.BanGuMiApiService;
import com.liu.service.CherryBlossomApiService;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

/**
 * @author : LiuDengLin
 * Email : 2092669791@qq.com
 * date : 2022/7/25 23:33
 * describe: BanGuMi相关接口
 */
@DubboService
public class AnimeControllerImpl implements IAnimeController {
    private BanGuMiApiService bangumiApiService;

    private CherryBlossomApiService cherryBlossomApiService;

    @Autowired
    public void setBanGuMiApiService(BanGuMiApiService bangumiApiService) {
        this.bangumiApiService = bangumiApiService;
    }

    @Autowired
    public void setCherryBlossomApiService(CherryBlossomApiService cherryBlossomApiService) {
        this.cherryBlossomApiService = cherryBlossomApiService;
    }

    @ApiOperation(value = "获取BanGuMi每日更新番剧")
    @RequestMapping(value = "/calendar", method = RequestMethod.GET)
    public List<CalendarVo> listBanGuMiCalendar() throws HttpProcessException, JsonProcessingException {
        return bangumiApiService.listCalendar();
    }

    @ApiOperation(value = "获取BanGuMi条目的所有信息")
    @RequestMapping(value = "/subject/{id}", method = RequestMethod.GET)
    public JsonNode getBanGuMiSubjectById(@PathVariable("id") Integer subjectId) throws HttpProcessException, JsonProcessingException {
       return bangumiApiService.getSimpleSubject(subjectId);
    }

    @ApiOperation(value = "获取BanGuMi条目的简略信息")
    @RequestMapping(value = "/subjectAll/{subjectId}", method = RequestMethod.GET)
    public JsonNode getBanGuMiSubjectAll(@PathVariable("subjectId") Integer subjectId,
                                         @RequestParam(value = "responseGroup") String responseGroup,
                                         @RequestParam(value = "time", required = false) String timeStamp)
            throws HttpProcessException, JsonProcessingException {
        return bangumiApiService.getSubject(subjectId, responseGroup, timeStamp);
    }

    @ApiOperation(value = "获取BanGuMi动漫排行榜信息")
    @RequestMapping(value = "/animeRank", method = RequestMethod.POST)
    public List<BaGuMiRankVo> getBanGuMiAnimeRanking(@RequestBody BaGuMiRankReq baGuMiRankVo) throws IOException {
        return bangumiApiService.listAnimeRankInfo(baGuMiRankVo);
    }

    @ApiOperation(value = "通过樱花检索得到动漫分集url")
    @RequestMapping(value = "/getEpisodeInfo", method = RequestMethod.GET)
    public List<CherryBlossomSearchVo> getSearchInfo(@RequestParam("animeName") String animeName) throws IOException {
        return cherryBlossomApiService.getSearchInfo(animeName);
    }

    @ApiOperation(value = "获取分集url获取视频播放地址")
    @RequestMapping(value = "/getVideoUrl", method = RequestMethod.GET)
    public CherryBlossomVideoVo getVideoUrl(@RequestParam("url") String url) throws IOException {
        return cherryBlossomApiService.getVideoUrl(url);
    }

    public List<Object> getSearch(){
        return null;
    }
}
