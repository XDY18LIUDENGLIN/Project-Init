package com.liu.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.liu.entity.req.BaGuMiRankReq;
import com.liu.entity.req.BaGuMiSearchSubjectReq;
import com.liu.entity.vo.BaGuMiRankVo;
import com.liu.entity.vo.CalendarVo;
import com.liu.entity.vo.CherryBlossomSearchVo;
import com.liu.entity.vo.CherryBlossomVideoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : LiuDengLin
 * Email : 2092669791@qq.com
 * date : 2022/10/4 12:34
 * describe: XXXXXXXX
 */
@Api(tags = "动漫-条目相关接口")
@RestController("bgm/v0")
public interface IAnimeSubjectController {
    @ApiOperation(value = "获取BanGuMi每日更新番剧")
    @RequestMapping(value = "/calendar", method = RequestMethod.GET)
    List<CalendarVo> listBanGuMiCalendar();

    @ApiOperation(value = "获取BanGuMi条目的所有信息")
    @RequestMapping(value = "/subject/{subjectId}/detailed", method = RequestMethod.GET)
    JsonNode getBanGuMiSubjectById(@PathVariable("subjectId") Integer subjectId);

    @ApiOperation(value = "获取BanGuMi条目的简略信息")
    @RequestMapping(value = "/subject/{subjectId}/brief", method = RequestMethod.GET)
    JsonNode getBanGuMiSubjectAll(@PathVariable("subjectId") Integer subjectId,
                                  @RequestParam(value = "responseGroup") String responseGroup,
                                  @RequestParam(value = "time", required = false) String timeStamp);

    @ApiOperation(value = "获取BanGuMi动漫排行榜信息")
    @RequestMapping(value = "/animeRank", method = RequestMethod.POST)
    List<BaGuMiRankVo> getBanGuMiAnimeRanking(@RequestBody BaGuMiRankReq baGuMiRankVo);

    @ApiOperation(value = "通过樱花检索得到动漫分集url")
    @RequestMapping(value = "/getEpisodeInfo", method = RequestMethod.GET)
    List<CherryBlossomSearchVo> getSearchInfo(@RequestParam("animeName") String animeName);

    @ApiOperation(value = "获取分集url获取视频播放地址")
    @RequestMapping(value = "/getVideoUrl", method = RequestMethod.GET)
    CherryBlossomVideoVo getVideoUrl(@RequestParam("url") String url);

    @ApiOperation(value = "条目搜索")
    @RequestMapping(value = "/search/subjects", method = RequestMethod.POST)
    List<Object> getSearch(@RequestBody BaGuMiSearchSubjectReq baGuMiSearchSubjectReq);
}
