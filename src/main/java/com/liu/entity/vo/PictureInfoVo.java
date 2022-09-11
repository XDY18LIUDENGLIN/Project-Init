package com.liu.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @author : LiuDengLin
 * Email : 2092669791@qq.com
 * date : 2022/9/11 13:09
 * describe: XXXXXXXX
 */
@Data
public class PictureInfoVo {
    /**
     * 用户Id
     */
    private String authorId;
    /**
     * 标题
     */
    private String title;
    /**
     * 作者头像
     */
    private String AuthorAvatar;
    /**
     * 作者名称
     */
    private String AuthorName;
    /**
     * 描述
     */
    private String describe;
    /**
     * 收藏
     */
    private Integer collectCount;
    /**
     * 喜欢
     */
    private Integer likeCount;
    /**
     * 浏览
     */
    private Integer lookCount;
    /**
     * 详情图片列表
     */
    private List<String> listPictureInfo;
 }
