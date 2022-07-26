package com.liu.entity.vo;

import com.liu.entity.transfer.CalendarItemVo;
import com.liu.entity.transfer.WeekDay;
import lombok.Data;

import java.util.List;

/**
 * @author LIUDENGLIN
 * @describe 每日更新列表
 */
@Data
public class CalendarVo {
    /**
     *
     */
    private List<CalendarItemVo> items;
    /**
     *
     */
    private WeekDay weekday;
}
