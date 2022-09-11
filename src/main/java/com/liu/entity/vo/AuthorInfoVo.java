package com.liu.entity.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author : LiuDengLin
 * Email : 2092669791@qq.com
 * date : 2022/9/12 0:33
 * describe: 作者信息
 */
@Data
public class AuthorInfoVo {
    /**
     * 头像
     */
    private String avatar;
    /**
     * 描述
     */
    @JsonProperty("desc")
    private String describe;
    /**
     * 昵称
     */
    @JsonProperty("nick_name")
    private String nickName;
    /**
     * 状态
     */
    @JsonProperty("plan_status")
    private Integer planStatus;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 作者ID
     */
    @JsonProperty("user_id")
    private String userId;
}
