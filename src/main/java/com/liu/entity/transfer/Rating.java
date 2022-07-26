package com.liu.entity.transfer;

import lombok.Data;

@Data
public class Rating {
    private Integer total;
    private Object count; //这个是json文件转成objec  前端再做解析
    private Float score;
}
