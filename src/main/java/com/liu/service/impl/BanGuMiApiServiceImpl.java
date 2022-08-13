package com.liu.service.impl;

import com.alibaba.fastjson2.JSON;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.liu.entity.req.BaGuMiRankReq;
import com.liu.entity.vo.BaGuMiRankVo;
import com.liu.entity.vo.CalendarVo;
import com.liu.service.BanGuMiApiService;
import com.liu.utils.BaGuMiUtils;
import com.liu.utils.ReptileUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author : LiuDengLin
 * Email : 2092669791@qq.com
 * date : 2022/7/25 23:54
 * describe: XXXXXXXX
 */
@Service
public class BanGuMiApiServiceImpl implements BanGuMiApiService {
    private ReptileUtils reptileUtils;

    private String[] userAgents={"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.93 Safari/537.36",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:83.0) Gecko/20100101 Firefox/83.0",
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.61 Safari/537.36",
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Safari/537.36",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.69 Safari/537.36",
            "Mozilla/5.0 (Linux; Android 10; HLK-AL00) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.92 Mobile Safari/537.36",
            "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36"};
    @Autowired
    public void setReptileUtils(ReptileUtils reptileUtils) {
        this.reptileUtils = reptileUtils;
    }

    @Override
    public List<CalendarVo> listCalendar() throws HttpProcessException {
        //TODO 设置定时任务,每天爬取数据一次,缓存到Redis中
        String dataObject = JSON.toJSONString(reptileUtils.listCalendarApi());
        List<CalendarVo> calendarVos = JSON.parseArray(dataObject, CalendarVo.class);
        //TODO 判断这个是否存在数据，不存在把数据保存到redis中或数据库中
        return calendarVos;
    }

    @Override
    public Object getSimpleSubject(Integer subjectId) throws HttpProcessException {
        StringBuffer pathUrl = new StringBuffer("/v0/subjects/").append(subjectId);
        Object dataWithHttpUtils = reptileUtils.listCalendarApi(pathUrl.toString());
        return dataWithHttpUtils;
    }

    @Override
    public Object getSubject(Integer subjectId, String pageSize, String timeStamp) throws HttpProcessException {
        StringBuffer pathUrl = new StringBuffer("/subject/").append(subjectId);
        pathUrl.append("?responseGroup=").append(pageSize);
        if (timeStamp!=null){
            pathUrl.append("&timestamp=").append(timeStamp);
        }
        Object dataWithHttpUtils = reptileUtils.listCalendarApi(pathUrl.toString());
        return dataWithHttpUtils;
    }

    @Override
    public Object listAnimeRankInfo(BaGuMiRankReq baGuMiRankVo) throws IOException {
        Document document = getAnimeRankRequest(baGuMiRankVo);
        List<BaGuMiRankVo> baGuMiRankVos = parseDocument(document);
        System.err.println(baGuMiRankVos);
        Integer pageTotalDocument = getPageTotalDocument(document);
        return baGuMiRankVos;
    }

    /**
     * 解析爬取BaGuMi结果
     * @param doc
     * @return
     */
    private List<BaGuMiRankVo> parseDocument(Document doc){
        //开始爬虫
        Element browserItemList = doc.getElementById("browserItemList");
        Elements items = browserItemList.getElementsByTag("li");
        List<BaGuMiRankVo> baGuMiRankVos = items.stream().map(item -> {
            BaGuMiRankVo bgmRankVo = new BaGuMiRankVo();
            bgmRankVo.setId(Long.valueOf(item.attr("id").split("_")[1]));
            //获取下面的a标签
            Element imgATag = item.getElementsByTag("a").get(0);
            String src = imgATag.select(".image img").attr("src");
            //处理图片链接
            StringBuffer imageUrl = new StringBuffer(src);
            imageUrl.insert(0, "https:");
            bgmRankVo.setImageUrl(imageUrl.toString());
            //获取信息
            Elements innerTitle = item.select(".inner h3");
            Elements small = innerTitle.select("small");
            if (!small.isEmpty()) {
                bgmRankVo.setName(small.get(0).text());
                bgmRankVo.setNameCn(innerTitle.select("a").get(0).text());
            } else {
                bgmRankVo.setName(innerTitle.get(0).text());
            }
            Elements rank = item.select(".inner .rank");
            if (!rank.isEmpty()) {
                int realRank = BaGuMiUtils.getNumInString(rank.text());
                bgmRankVo.setRank(realRank);
            }
            //获取缩略信息
            Elements info = item.select(".inner .info");
            if (!info.isEmpty()) {
                bgmRankVo.setInfoTip(info.get(0).text());
            }
            //获取评分
            Elements rate = item.select(".inner .rateInfo");
            if (!rate.isEmpty()) {
                Elements score = rate.select(".fade");
                if (!score.isEmpty()) {
                    bgmRankVo.setScore(Double.parseDouble(score.get(0).text()));
                }
                Elements counts = rate.select(".tip_j");
                if (!counts.isEmpty()) {
                    int count = BaGuMiUtils.getNumInString(counts.get(0).text());
                    bgmRankVo.setCount(count);
                }
            }
            return bgmRankVo;
        }).collect(Collectors.toList());
        return baGuMiRankVos;
    }

    /**
     * 爬取页数
     * @param doc
     * @return
     */
    private Integer getPageTotalDocument(Document doc){
        Integer pageTotal=0;
        Elements page_inner = doc.getElementsByClass("page_inner");
        if(!page_inner.isEmpty()){
            String[] num = page_inner.get(0).text().split("/");
            if(num.length==1){
                //少于10页 直接获取>>后的
                char chatIndex=num[0].charAt(num[0].indexOf('›')-1);
                pageTotal = Integer.parseInt(String.valueOf(chatIndex));
            }else{
                pageTotal = BaGuMiUtils.getNumInString(num[1]);
            }
        }
        return pageTotal;
    }

    /**
     * 构造爬取请求并得到请求结果
     * @param baGuMiRankReq
     * @return
     * @throws IOException
     */
    private Document getAnimeRankRequest(BaGuMiRankReq baGuMiRankReq) throws IOException {
        StringBuffer urlPath = new StringBuffer(ReptileUtils.RANK_BASE_URL);
        if (baGuMiRankReq.getBigType()!=null){
            urlPath =new StringBuffer().append(String.format("https://bgm.tv/%s/browser,", baGuMiRankReq.getBigType()));
        }
        if(baGuMiRankReq.getTag()!=null){
            urlPath=new StringBuffer(urlPath.toString().replace("browser","tag/"));
            urlPath.append(baGuMiRankReq.getTag());
        }
        if(baGuMiRankReq.getType()!=null){
            urlPath.append("/").append(baGuMiRankReq.getType());
        }
        if(baGuMiRankReq.getAirtime()!=null){
            urlPath.append("/airtime/").append(baGuMiRankReq.getAirtime());
        }
        Connection connect = Jsoup.connect(urlPath.toString());
        connect.userAgent(userAgents[new Random().nextInt(userAgents.length)]);
        connect.timeout(8000);
        if(baGuMiRankReq.getPage()!=null){
            connect.data("page",baGuMiRankReq.getPage().toString());
        }
        if(baGuMiRankReq.getOrder()!=null){
            connect.data("orderby",baGuMiRankReq.getOrder().toString());
        }
        if(baGuMiRankReq.getSort()!=null){
            connect.data("sort",baGuMiRankReq.getSort());
        }
        Document document = connect.get();
        return document;
    }


}
