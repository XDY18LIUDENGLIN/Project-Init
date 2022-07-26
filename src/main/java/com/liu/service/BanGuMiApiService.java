package com.liu.service;

import com.liu.entity.vo.CalendarVo;

import java.util.List;

/**
 * @author : LiuDengLin
 * Email : 2092669791@qq.com
 * date : 2022/7/25 23:52
 * describe: Bangumi接口
 */
public interface BanGuMiApiService {
    /**
     * 获取每日更新番剧
     * @return
     */
    List<CalendarVo> listCalendar();
}
