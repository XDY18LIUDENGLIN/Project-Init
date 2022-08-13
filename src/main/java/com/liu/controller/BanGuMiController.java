package com.liu.controller;

import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.liu.entity.req.BaGuMiRankReq;
import com.liu.entity.vo.CalendarVo;
import com.liu.service.BanGuMiApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @author : LiuDengLin
 * Email : 2092669791@qq.com
 * date : 2022/7/25 23:33
 * describe: BanGuMi相关接口
 */
@Api(tags = "BanGuMi相关接口")
@RestController("/bgm")
public class BanGuMiController {
    @Autowired
    private BanGuMiApiService bangumiApiService;

    @ApiOperation(value = "获取每日更新番剧")
    @RequestMapping(value = "/calendar", method = RequestMethod.GET)
    public List<CalendarVo> listCalendar() throws HttpProcessException {
        return bangumiApiService.listCalendar();
    }

    @ApiOperation(value = "获取条目的所有信息")
    @RequestMapping(value = "/subject/{id}",method = RequestMethod.GET)
    public Object getSubjectById(@PathVariable("id")Integer subjectId) throws HttpProcessException {
       return bangumiApiService.getSimpleSubject(subjectId);
    }

    @ApiOperation(value = "获取条目的简略信息")
    @RequestMapping(value = "/subjectAll/{subjectId}/{pageSize}", method = RequestMethod.GET)
    public Object getSubjectAll(@PathVariable("subjectId") Integer subjectId,
                                @PathVariable(value = "pageSize") String pageSize,
                                @RequestParam(value = "time", required = false) String timeStamp) throws HttpProcessException {
         return bangumiApiService.getSubject(subjectId, pageSize, timeStamp);
    }

    @ApiOperation(value = "获取动漫排行榜信息")
    @RequestMapping(value = "/animeRank" ,method = RequestMethod.POST)
    public Object getAnimeRanking(@RequestBody BaGuMiRankReq baGuMiRankVo) throws IOException {
        return bangumiApiService.listAnimeRankInfo(baGuMiRankVo);
    }
}
