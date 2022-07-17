package com.liu.common.statusEnum;

/**
 * @author : LiuDengLin
 * Email : 2092669791@qq.com
 * date : 2022/7/17 14:40
 */
public interface StatusCode {
    /**
     * 得到状态码
     * @return
     */
    int getCode();

    /**
     * 得到状态信息
     * @return
     */
    String getMsg();
}
