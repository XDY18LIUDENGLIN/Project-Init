package com.liu.rest;

import com.liu.entity.vo.AuthorInfoVo;
import com.liu.entity.vo.PictureInfoVo;
import com.liu.entity.vo.PictureRankVo;
import com.liu.entity.vo.PictureTagVo;
import com.liu.entity.vo.PictureVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.web.bind.annotation.PathVariable;
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
@Api(value = "图片Api", tags = "图片相关接口")
@RestController("picture")
public interface IPictureController {

    @ApiOperation(value = "获取所有图片的标签")
    @RequestMapping(value = "/getAllPictureTag", method = RequestMethod.GET)
    List<PictureTagVo> getAllPictureTag();

    @ApiOperation(value = "获取所有图片排行榜")
    @RequestMapping(value = "/ranking", method = RequestMethod.GET)
    List<PictureRankVo> listPictureRank(@RequestParam("limit") Integer limit,
                                        @RequestParam("offset") Integer offset,
                                        @RequestParam("type") Integer type);

    @ApiOperation(value = "获取图片的具体信息")
    @RequestMapping(value = "/getPictureInfo/{pictureId}", method = RequestMethod.GET)
    PictureInfoVo getPictureInfo(@PathVariable("pictureId")String pictureId);

    @ApiOperation(value = "获取推荐作品")
    @RequestMapping(value = "/recommand", method = RequestMethod.GET)
    List<PictureVo> listRecommendedWorks(@RequestParam("limit") Integer limit, @RequestParam("offset") Integer offset);

    @ApiOperation(value = "获取原创作品或最新作品")
    @RequestMapping(value = "/public", method = RequestMethod.GET)
    List<PictureVo> listPublicWorks(@RequestParam("limit") Integer limit,
                                    @RequestParam("offset") Integer offset,
                                    @RequestParam("sort") String sort,
                                    @RequestParam("type") Integer type);

    @ApiOperation(value = "获取作者最出色的图片")
    @RequestMapping(value = "/bestPicture/{pictureId}/{userId}", method = RequestMethod.GET)
    List<PictureVo> listBestPictureInAuthor(@RequestParam("limit") Integer limit,
                                            @RequestParam("offset") Integer offset,
                                            @PathVariable("pictureId") String pictureId,
                                            @PathVariable("userId") String userId);

    @ApiOperation(value = "获取作者的所有图片")
    @RequestMapping(value = "/getAllPicture/{userId}",method = RequestMethod.GET)
    List<PictureVo> listAllPictureByUserId(@PathVariable("userId")String userId,
                                           @RequestParam("limit") Integer limit,
                                           @RequestParam("offset") Integer offset,
                                           @RequestParam("type") Integer type,
                                           @RequestParam("sort") String sort);

    @ApiOperation(value = "通过关键字检索接口")
    @RequestMapping(value = "/search/{seachrType}/{keyword}", method = RequestMethod.GET)
    Pair<List<AuthorInfoVo>, List<PictureVo>> listSearch(@PathVariable("seachrType") String type,
                                                         @PathVariable("keyword") String keyword,
                                                         @RequestParam("limit") Integer limit,
                                                         @RequestParam("offset") Integer offset);
}
