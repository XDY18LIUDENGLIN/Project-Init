package com.liu.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liu.entity.vo.AuthorInfoVo;
import com.liu.entity.vo.PictureInfoVo;
import com.liu.entity.vo.PictureRankVo;
import com.liu.entity.vo.PictureTagVo;
import com.liu.entity.vo.PictureVo;
import com.liu.service.AllBaseConfig;
import com.liu.service.PictureService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author 王离（柳登林 wangli.liu@tuya.com）
 * @describe XXXXXX
 * @since 2022/9/9 5:08 PM
 */
@Service
public class PictureServiceImpl extends AllBaseConfig implements PictureService {

    @Override
    public List<PictureVo> listAllPictureByUserId(String sort, Integer type, String userId, Integer limit, Integer offset)
            throws IOException {
        List<PictureVo> pictureVoList = new ArrayList<>();
        StringBuffer allPicturePathUrl = new StringBuffer(AllBaseConfig.PivixAPIUrl).append("/v1/picture/public");
        allPicturePathUrl.append("?sort=").append(sort);
        allPicturePathUrl.append("&type=").append(type);
        allPicturePathUrl.append("&author_user_id=").append(userId);
        allPicturePathUrl.append("&limit=").append(limit);
        allPicturePathUrl.append("&offset=").append(offset);
        JsonNode allPictureData = this.getPivixApiData(allPicturePathUrl.toString());
        if (Objects.nonNull(allPictureData)) {
            ObjectMapper jacksonMapper = new ObjectMapper();
            pictureVoList = jacksonMapper.readValue(allPictureData.toString(), new TypeReference<List<PictureVo>>() {
            });
        }
        return pictureVoList;
    }

    @Override
    public List<PictureVo> listBestPictureInAuthor(Integer limit, Integer offset, String pictureId, String userId)
            throws IOException {
        List<PictureVo> pictureVoList = new ArrayList<>();
        StringBuffer bestPictureUrl = new StringBuffer(AllBaseConfig.PivixAPIUrl).append("/v1/picture/user_best_picture");
        bestPictureUrl.append("?limit=").append(limit);
        bestPictureUrl.append("&offset=").append(offset);
        bestPictureUrl.append("&picture_id=").append(pictureId);
        bestPictureUrl.append("&user_id=").append(userId);
        JsonNode tagAllData = getPivixApiData(bestPictureUrl.toString());
        if (Objects.nonNull(tagAllData)) {
            ObjectMapper jacksonMapper = new ObjectMapper();
            pictureVoList = jacksonMapper.readValue(tagAllData.toString(), new TypeReference<List<PictureVo>>() {
            });
        }
        return pictureVoList;
    }

    @Override
    public PictureInfoVo getPictureInfo(String pictureId) throws IOException {
        PictureInfoVo pictureInfoVo = new PictureInfoVo();
        StringBuffer pictureInfoUrl = new StringBuffer(AllBaseConfig.PivixBaseUrl).append("/illust/").append(pictureId);
        Connection connect = Jsoup.connect(pictureInfoUrl.toString());
        connect.userAgent(AllBaseConfig.geRandomUserAgent()).timeout(8000).ignoreContentType(true);
        Document document = connect.get();
        // 获取class=illust-rang的div节点下面的h2节点  作品标题
        Element titleElement = document.select("div.illust-rang h2").first();
        // 作品描述
        Element describeElement = document.select("div.illust-desc").first();
        // 作者头像
        Element authorImage = document.select("section.user-box img").first();
        // 作者名字
        Element authorName = document.select("section.user-box > div.info a").first();
        // 作者Id
        Element authorId = document.select("section.user-box > div.info > p").first();
        // 所有图片集
        Elements imageList = document.select("main.illust-content img");
        // 所有收藏，喜欢，浏览数据
        Elements countElements = document.select("ul.count-content dd");

        pictureInfoVo.setTitle(Objects.nonNull(titleElement) ? titleElement.text() : null);
        pictureInfoVo.setDescribe(Objects.nonNull(describeElement) ? describeElement.html(): null);
        pictureInfoVo.setAuthorAvatar(Objects.nonNull(authorImage) ? authorImage.attr("src") : null);
        pictureInfoVo.setAuthorName(Objects.nonNull(authorName) ? authorName.text() : null);
        pictureInfoVo.setAuthorId(Objects.nonNull(authorId) ? authorId.text().substring(3): null);
        pictureInfoVo.setListPictureInfo(Objects.nonNull(imageList) ? imageList.eachAttr("src"): null);

        if(!countElements.isEmpty()) {
            String likeCount = Optional.ofNullable(countElements.get(0)).orElse(new Element("span")).text();
            String collectCount = Optional.ofNullable(countElements.get(1)).orElse(new Element("span")).text();
            String lookCount = Optional.ofNullable(countElements.get(2)).orElse(new Element("span")).text();
            pictureInfoVo.setLikeCount(StringUtils.isEmpty(likeCount) ? 0 : Integer.parseInt(likeCount.replace(",","")));
            pictureInfoVo.setCollectCount(StringUtils.isEmpty(collectCount) ? 0 : Integer.parseInt(collectCount.replace(",","")));
            pictureInfoVo.setLookCount(StringUtils.isEmpty(lookCount) ? 0 : Integer.parseInt(lookCount.replace(",","")));
        }
        return pictureInfoVo;
    }

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
        commendPathUrl.append("?limit=").append(limit);
        commendPathUrl.append("&offset=").append(offset);
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
        commendPathUrl.append("?limit=").append(limit);
        commendPathUrl.append("&offset=").append(offset);
        commendPathUrl.append("&type=").append(type);
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
        commendPathUrl.append("?limit=").append(limit);
        commendPathUrl.append("&offset=").append(offset);
        commendPathUrl.append("&sort=").append(sort);
        commendPathUrl.append("&type=").append(type);
        JsonNode tagAllData = getPivixApiData(commendPathUrl.toString());
        if (Objects.nonNull(tagAllData)) {
            ObjectMapper jacksonMapper = new ObjectMapper();
            pictureVoList = jacksonMapper.readValue(tagAllData.toString(), new TypeReference<List<PictureVo>>() {
            });
        }
        return pictureVoList;
    }

    @Override
    public Pair<List<AuthorInfoVo>, List<PictureVo>> listSearch(String type, String keyword, Integer limit, Integer offset) throws IOException {
        if("users".equalsIgnoreCase(type)){
            List<AuthorInfoVo> authorInfoVoList = new ArrayList<>();
            StringBuffer searchPathUrl = new StringBuffer(AllBaseConfig.PivixAPIUrl).append("/v1/search/user");
            searchPathUrl.append("?type=author");
            searchPathUrl.append("&keyword=").append(keyword);
            searchPathUrl.append("&limit=").append(limit);
            searchPathUrl.append("&offset=").append(offset);
            JsonNode searchAllData = this.getPivixApiData(searchPathUrl.toString());
            if (Objects.nonNull(searchAllData)) {
                ObjectMapper jacksonMapper = new ObjectMapper();
                authorInfoVoList = jacksonMapper.readValue(searchAllData.toString(), new TypeReference<List<AuthorInfoVo>>() {
                });
            }
            return Pair.of(authorInfoVoList, null);
        } else {

        }
        return null;
    }

    /**
     * 获取Pixiv返回的body文本
     *
     * @param url 请求路径
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
