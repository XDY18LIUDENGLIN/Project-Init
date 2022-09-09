package com.liu.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liu.entity.vo.PictureRankVo;
import com.liu.entity.vo.PictureTagVo;
import com.liu.entity.vo.PictureVo;
import com.liu.service.AllBaseConfig;
import com.liu.service.PictureService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author 王离（柳登林 wangli.liu@tuya.com）
 * @describe XXXXXX
 * @since 2022/9/9 5:08 PM
 */
@Service
public class PictureServiceImpl extends AllBaseConfig implements PictureService {
    @Override
    public List<PictureTagVo> getAllPictureTag() throws IOException {
        List<PictureTagVo> pictureTagVoList = new ArrayList<>();
        StringBuffer requestUrl = new StringBuffer(AllBaseConfig.PivixAPIUrl).append("/v1/search/recommand_tag");
        JsonNode tagAllData = getPivixApiData(requestUrl.toString());
        if (Objects.nonNull(tagAllData)) {
            ObjectMapper jacksonMapper = new ObjectMapper();
            pictureTagVoList = jacksonMapper.readValue(tagAllData.toString(), new TypeReference<List<PictureTagVo>>() {
            });
        }
        return pictureTagVoList;
    }

    @Override
    public List<PictureVo> listRecommendWorks(Integer limit, Integer offset) throws IOException {
        List<PictureVo> pictureVoList = new ArrayList<>();
        StringBuffer commendPathUrl = new StringBuffer(AllBaseConfig.PivixAPIUrl).append("/v1/picture/recommand");
        commendPathUrl.append("?limit=" + limit);
        commendPathUrl.append("&offset=" + offset);
        JsonNode tagAllData = getPivixApiData(commendPathUrl.toString());
        if (Objects.nonNull(tagAllData)) {
            ObjectMapper jacksonMapper = new ObjectMapper();
            pictureVoList = jacksonMapper.readValue(tagAllData.toString(), new TypeReference<List<PictureVo>>() {
            });
        }
        return pictureVoList;
    }

    @Override
    public List<PictureRankVo> listPictureRank(Integer limit, Integer offset, Integer type) throws IOException {
        List<PictureRankVo> pictureRankVoList = new ArrayList<>();
        StringBuffer commendPathUrl = new StringBuffer(AllBaseConfig.PivixAPIUrl).append("/v1/picture/ranking");
        commendPathUrl.append("?limit=" + limit);
        commendPathUrl.append("&offset=" + offset);
        commendPathUrl.append("&type=" + type);
        JsonNode tagAllData = getPivixApiData(commendPathUrl.toString());
        if (Objects.nonNull(tagAllData)) {
            ObjectMapper jacksonMapper = new ObjectMapper();
            pictureRankVoList = jacksonMapper.readValue(tagAllData.toString(), new TypeReference<List<PictureRankVo>>() {
            });
        }
        return pictureRankVoList;
    }

    @Override
    public List<PictureVo> listPublicWorks(Integer limit, Integer offset, String sort, Integer type) throws IOException {
        List<PictureVo> pictureVoList = new ArrayList<>();
        StringBuffer commendPathUrl = new StringBuffer(AllBaseConfig.PivixAPIUrl).append("/v1/picture/public");
        commendPathUrl.append("?limit=" + limit);
        commendPathUrl.append("&offset=" + offset);
        commendPathUrl.append("&sort=" + sort);
        commendPathUrl.append("&type=" + type);
        JsonNode tagAllData = getPivixApiData(commendPathUrl.toString());
        if (Objects.nonNull(tagAllData)) {
            ObjectMapper jacksonMapper = new ObjectMapper();
            pictureVoList = jacksonMapper.readValue(tagAllData.toString(), new TypeReference<List<PictureVo>>() {
            });
        }
        return pictureVoList;
    }

    /**
     * 获取Pixiv返回的body文本
     *
     * @param url
     * @return
     * @throws IOException
     */
    private JsonNode getPivixApiData(String url) throws IOException {
        Connection connect = Jsoup.connect(url);
        connect.userAgent(AllBaseConfig.geRandomUserAgent()).timeout(8000).ignoreContentType(true);
        Document document = connect.get();
        Elements bodyElement = document.select("body");
        String bodyHasText = bodyElement.text();
        ObjectMapper jacksonMapper = new ObjectMapper();
        JsonNode jsonNode = jacksonMapper.readTree(bodyHasText);
        JsonNode tagAllData = jsonNode.get("data").get("rows");
        return tagAllData;
    }
}
