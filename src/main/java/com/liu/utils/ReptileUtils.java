package com.liu.utils;

import com.alibaba.fastjson2.JSON;
import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.builder.HCB;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.common.HttpHeader;
import com.arronlong.httpclientutil.common.SSLs;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liu.entity.vo.CalendarVo;
import org.apache.http.Header;
import org.apache.http.impl.client.CloseableHttpClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author : LiuDengLin
 * Email : 2092669791@qq.com
 * date : 2022/7/26 23:22
 * describe: 爬虫接口
 */
public class ReptileUtils {

    /**
     *  爬取网址url
     */
    private String baseUrl;

    /**
     *  buGuMi用户密钥
     */
    private String baGuMiUserAgent;

    /**
     * 用于请求接口的公共方法 只需要传入一个url就能自动进行请求
     * @param url
     * @return
     */
    public Object getDataWithHttpUtils(String url) throws HttpProcessException {
        Header[] headers = HttpHeader.custom().userAgent(baGuMiUserAgent).build();
        HCB hcb = HCB.custom().sslpv(SSLs.SSLProtocolVersion.TLSv1_2);
        CloseableHttpClient httpClient = hcb.build();
        String result = HttpClientUtil.get(HttpConfig.custom().url(url).client(httpClient).headers(headers));
        return JSON.parse(result);
    }

    /**
     *
     * @return
     * @throws HttpProcessException
     */
    public List<CalendarVo> listCalendarApi() throws HttpProcessException {
        StringBuffer url = new StringBuffer(baseUrl).append("/calendar");
        Object sourceData = getDataWithHttpUtils(url.toString());
        List<Object> arrayList = new ObjectMapper().convertValue(sourceData, ArrayList.class);
        List<CalendarVo> calendarVoList = Optional.ofNullable(arrayList).orElse(new ArrayList<>()).stream().map(item -> {
            ObjectMapper objectMapper = new ObjectMapper();
            CalendarVo calendarVo = objectMapper.convertValue(item, CalendarVo.class);
            return calendarVo;
        }).collect(Collectors.toList());
        return calendarVoList;
    }
}
