package com.liu.service.impl;

import com.liu.entity.pojo.EpisodeInfo;
import com.liu.entity.vo.CherryBlossomSearchVo;
import com.liu.entity.vo.CherryBlossomVideoVo;
import com.liu.service.AllBaseConfig;
import com.liu.service.CherryBlossomApiService;
import com.liu.utils.BaGuMiUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 王离（柳登林 wangli.liu@tuya.com）
 * @describe 樱花动漫检索信息实现类
 * @since 2022/9/3 6:15 PM
 */
@Service
public class CherryBlossomApiServiceImpl implements CherryBlossomApiService {

    @Override
    public List<CherryBlossomSearchVo> getSearchInfo(String keyWord) throws IOException {
        StringBuffer urlPath = new StringBuffer();
        urlPath.append(AllBaseConfig.CherryBlossomSearchUrl).append(keyWord);
        ArrayList<CherryBlossomSearchVo> blossomSearchVos = new ArrayList<>();
        List<Pair<String, String>> itemUrlList = new ArrayList<>();

        Connection connect = Jsoup.connect(urlPath.toString());
        connect.userAgent(AllBaseConfig.geRandomUserAgent());
        connect.timeout(8000);
        Document document = connect.get();
        Elements rootElements = document.select(".area .fire .lpic");

        //获取搜索结果前三关联的条目
        //备注 由于樱花动漫的搜索逻辑是 最相关的在前面 所以需要获取分页数据 找到最后一页并且倒序插入
        //每一页结果为20条
        Elements pages = rootElements.get(0).getElementsByClass("pages");

        // url的获取
        Elements ulElementList = rootElements.get(0).getElementsByTag("ul");

        if (!pages.isEmpty()) {
            Element totalNum = pages.get(0).getElementById("totalnum");
            int total = BaGuMiUtils.getNumInString(totalNum.text());
            int page = (int) Math.ceil(total / 20.0);
            //重新发起请求
            urlPath = urlPath.append("/?page=").append(page);
            connect = Jsoup.connect(urlPath.toString());
            connect.userAgent(AllBaseConfig.geRandomUserAgent());
            connect.timeout(8000);
            document = connect.get();
            rootElements = document.select(".area .fire .lpic");
            ulElementList = rootElements.get(0).getElementsByTag("ul");
        }

        if (!ulElementList.isEmpty()) {
            //如果是有分页数据的 我们需要到最后一页去寻找相应的搜索结果
            Elements liElementList = ulElementList.get(0).getElementsByTag("li");
            if (!liElementList.isEmpty()) {
                int count = Math.min(liElementList.size(), 5);
                //搜索三条关联条目
                for (int i = 0; i < count; i++) {
                    Element item = liElementList.get(liElementList.size() - i - 1);
                    Elements h2ElementsList = item.getElementsByTag("h2");
                    if (!h2ElementsList.isEmpty()) {
                        Elements aElementList = h2ElementsList.get(0).getElementsByTag("a");
                        String url = aElementList.get(0).attr("href");
                        String title = aElementList.get(0).attr("title");
                        Pair<String, String> tempDos = Pair.of(title, AllBaseConfig.CherryBlossomBaseUrl + url);
                        itemUrlList.add(tempDos);
                    }
                }
            }
        }

        if (itemUrlList.size() > 0) {
            for (Pair<String, String> itemUrl : itemUrlList) {
                CherryBlossomSearchVo cherryBlossomSearchVo = new CherryBlossomSearchVo();
                cherryBlossomSearchVo.setAnimeTitle(itemUrl.getLeft());
                Connection connection1 = Jsoup.connect(itemUrl.getRight());
                connection1.userAgent(AllBaseConfig.geRandomUserAgent());
                connection1.timeout(8000);
                Document doc2 = connection1.get();
                Elements movUrlElementList = doc2.getElementsByClass("movurl");
                List<EpisodeInfo> epList = new ArrayList<>();
                if (!movUrlElementList.isEmpty()) {
                    //获取到ul
                    ulElementList = movUrlElementList.get(0).getElementsByTag("ul");
                    if (!ulElementList.isEmpty()) {
                        Elements liElementList = ulElementList.get(0).getElementsByTag("li");
                        if (!liElementList.isEmpty()) {
                            for (Element li : liElementList) {
                                EpisodeInfo epInfo = new EpisodeInfo();
                                Elements aElementList = li.getElementsByTag("a");
                                if (!aElementList.isEmpty()) {
                                    Element a1 = aElementList.get(0);
                                    String epUrl = a1.attr("href");
                                    epInfo.setEpisodeTitle(a1.text());
                                    epInfo.setEpisodeUrl(epUrl);
                                    epList.add(epInfo);
                                }
                            }
                        }
                    }
                }
                cherryBlossomSearchVo.setEpisodeInfo(epList);
                blossomSearchVos.add(cherryBlossomSearchVo);
            }
        }
        return blossomSearchVos;
    }

    @Override
    public CherryBlossomVideoVo getVideoUrl(String url) throws IOException {
        //传进来的是 5479-2的样子  把分集数据和视频url一同返回过去
        StringBuffer connectUrl = new StringBuffer(AllBaseConfig.CherryBlossomBaseUrl).append("/v/").append(url).append(".html");
        CherryBlossomVideoVo cherryBlossomVideoVo = new CherryBlossomVideoVo();
        Connection connection = Jsoup.connect(connectUrl.toString());
        connection.userAgent(AllBaseConfig.geRandomUserAgent());
        connection.timeout(8000);
        Document doc = connection.get();
        Elements select = doc.select(".bofang div");
        if (!select.isEmpty()) {
            Element div = select.get(0);
            String videoUrl = div.attr("data-vid");
            String[] urls = videoUrl.split("$");
            cherryBlossomVideoVo.setVideoUrl(urls[0]);
        }
        Elements ulElementList = doc.select(".movurls ul");
        if (!ulElementList.isEmpty()) {
            Elements liElementList = ulElementList.get(0).getElementsByTag("li");
            List<EpisodeInfo> episodeInfoList = new ArrayList<>();
            if (!liElementList.isEmpty()) {
                for (Element element : liElementList) {
                    EpisodeInfo episodeInfo = new EpisodeInfo();
                    episodeInfo.setEpisodeTitle(element.text());
                    episodeInfo.setEpisodeUrl(element.getElementsByTag("a").attr("href"));
                    episodeInfoList.add(episodeInfo);
                }
                cherryBlossomVideoVo.setEpisodeInfoList(episodeInfoList);
            }
        }
        select = doc.select(".gohome");
        if (!select.isEmpty()) {
            Elements h1ElementList = select.get(0).getElementsByTag("h1");
            if (!h1ElementList.isEmpty()) {
                String animeName = h1ElementList.get(0).text();
                cherryBlossomVideoVo.setTitle(animeName);
            }
        }
        return cherryBlossomVideoVo;
    }
}
