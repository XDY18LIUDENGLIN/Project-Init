package com.liu.controller;

import com.liu.entity.vo.AuthorInfoVo;
import com.liu.entity.vo.PictureInfoVo;
import com.liu.entity.vo.PictureRankVo;
import com.liu.entity.vo.PictureTagVo;
import com.liu.entity.vo.PictureVo;
import com.liu.service.PictureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * @author 王离（柳登林 wangli.liu@tuya.com）
 * @describe 图片Api
 * @since 2022/9/4 11:37 PM
 */
@Api(value = "图片Api", tags = "图片相关接口")
@RestController("picture")
public class PictureController {

    private PictureService pictureService;

    @Autowired
    public void setPictureService(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @ApiOperation(value = "获取所有图片的标签")
    @RequestMapping(value = "/getAllPictureTag", method = RequestMethod.GET)
    public List<PictureTagVo> getAllPictureTag() throws IOException {
        return pictureService.getAllPictureTag();
    }

    @ApiOperation(value = "获取所有图片排行榜")
    @RequestMapping(value = "/ranking", method = RequestMethod.GET)
    public List<PictureRankVo> listPictureRank(@RequestParam("limit") Integer limit,
                                               @RequestParam("offset") Integer offset,
                                               @RequestParam("type") Integer type) throws IOException {
        return pictureService.listPictureRank(limit, offset, type);
    }

    @ApiOperation(value = "获取图片的具体信息")
    @RequestMapping(value = "/getPictureInfo/{pictureId}", method = RequestMethod.GET)
    public PictureInfoVo getPictureInfo(@PathVariable("pictureId")String pictureId) throws IOException {
        return pictureService.getPictureInfo(pictureId);
    }


    @ApiOperation(value = "获取推荐作品")
    @RequestMapping(value = "/recommand", method = RequestMethod.GET)
    public List<PictureVo> listRecommendedWorks(@RequestParam("limit") Integer limit, @RequestParam("offset") Integer offset)
            throws IOException {
        return pictureService.listRecommendWorks(limit, offset);
    }

    @ApiOperation(value = "获取原创作品或最新作品")
    @RequestMapping(value = "/public", method = RequestMethod.GET)
    public List<PictureVo> listPublicWorks(@RequestParam("limit") Integer limit,
                                           @RequestParam("offset") Integer offset,
                                           @RequestParam("sort") String sort,
                                           @RequestParam("type") Integer type) throws IOException {
        return pictureService.listPublicWorks(limit, offset, sort, type);
    }

    @ApiOperation(value = "获取作者最出色的图片")
    @RequestMapping(value = "/bestPicture/{pictureId}/{userId}", method = RequestMethod.GET)
    public List<PictureVo> listBestPictureInAuthor(@RequestParam("limit") Integer limit,
                                                   @RequestParam("offset") Integer offset,
                                                   @PathVariable("pictureId") String pictureId,
                                                   @PathVariable("userId") String userId) throws IOException {
        return pictureService.listBestPictureInAuthor(limit,offset, pictureId, userId);
    }


    @ApiOperation(value = "获取作者的所有图片")
    @RequestMapping(value = "/getAllPicture/{userId}",method = RequestMethod.GET)
    public List<PictureVo> listAllPictureByUserId(@PathVariable("userId")String userId,
                                               @RequestParam("limit") Integer limit,
                                               @RequestParam("offset") Integer offset,
                                               @RequestParam("type") Integer type,
                                               @RequestParam("sort") String sort) throws IOException {
        return pictureService.listAllPictureByUserId(sort, type, userId, limit, offset);
    }

    @ApiOperation(value = "通过关键字检索接口")
    @RequestMapping(value = "/search/{seachrType}/{keyword}", method = RequestMethod.GET)
    public Pair<List<AuthorInfoVo>, List<PictureVo>> listSearch(@PathVariable("seachrType") String type,
                                                                @PathVariable("keyword") String keyword,
                                                                @RequestParam("limit") Integer limit,
                                                                @RequestParam("offset") Integer offset) throws IOException {
        return pictureService.listSearch(type, keyword, limit, offset);
    }
}
