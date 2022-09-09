package com.liu.controller;

import com.liu.entity.vo.PictureRankVo;
import com.liu.entity.vo.PictureTagVo;
import com.liu.entity.vo.PictureVo;
import com.liu.service.PictureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

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
    public Object getPictureInfo() {
        return null;
    }


    @ApiOperation(value = "获取推荐作品")
    @RequestMapping(value = "/recommand", method = RequestMethod.GET)
    public List<PictureVo> listRecommendedWorks(@RequestParam("limit") Integer limit, @RequestParam("offset") Integer offset)
            throws IOException {
        return pictureService.listRecommendWorks(limit, offset);
    }

    @ApiOperation(value = "获取原创作品")
    @RequestMapping(value = "/public", method = RequestMethod.GET)
    public List<PictureVo> listPublicWorks(@RequestParam("limit") Integer limit,
                                           @RequestParam("offset") Integer offset,
                                           @RequestParam("sort") String sort,
                                           @RequestParam("type") Integer type) throws IOException {
        return pictureService.listPublicWorks(limit, offset, sort, type);
    }
}
