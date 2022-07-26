package com.liu.controller;

import com.liu.entity.vo.CalendarVo;
import com.liu.service.BanGuMiApiService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author : LiuDengLin
 * Email : 2092669791@qq.com
 * date : 2022/7/25 23:33
 * describe: XXXXXXXX
 */
@Api(tags = "BanGuMi相关接口")
@RequestMapping("/bgm")
public class BanGuMiController {
    @Autowired
    private BanGuMiApiService bangumiApiService;

    @RequestMapping(value = "/calendar", method = RequestMethod.GET)
    public List<CalendarVo> listCalendar(){
        return bangumiApiService.listCalendar();
    }
}
