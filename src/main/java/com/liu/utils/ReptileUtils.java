package com.liu.utils;

import com.alibaba.fastjson2.JSON;
import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.builder.HCB;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.common.HttpHeader;
import com.arronlong.httpclientutil.common.SSLs;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author : LiuDengLin
 * Email : 2092669791@qq.com
 * date : 2022/7/26 23:22
 * describe: 爬虫接口
 */
@Slf4j
@Component
public class ReptileUtils implements InitializingBean {

    /**
     *  爬取网址url
     */
    @Value("${bgm.url.base}")
    private String apiBaseUrl;
    /**
     *  排行榜url
     */
    @Value("${bgm.url.rank}")
    private String rankBaseUrl;
    /**
     *  buGuMi用户密钥
     */
    @Value("${bgm.agent}")
    private String baGuMiUserAgent;

    /**
     * 基本url
     */
    public static String API_BASE_URL;

    /**
     * 排行榜url
     */
    public static String RANK_BASE_URL;

    /**
     * baGuMi用户信息
     */
    public static String BAGUMI_USER_AGENT;

    @Override
    public void afterPropertiesSet() {
        API_BASE_URL=this.apiBaseUrl;
        RANK_BASE_URL=this.rankBaseUrl;
        BAGUMI_USER_AGENT=this.baGuMiUserAgent;
    }


    /**
     * 用于请求接口的公共方法 只需要传入一个url就能自动进行请求
     * @param url
     * @return
     */
    public Object getDataWithHttpUtils(String url) throws HttpProcessException {
        Header[] headers = HttpHeader.custom().userAgent(BAGUMI_USER_AGENT).build();
        HCB hcb = HCB.custom().sslpv(SSLs.SSLProtocolVersion.TLSv1_2);
        CloseableHttpClient httpClient = hcb.build();
        String result = HttpClientUtil.get(HttpConfig.custom().url(url).client(httpClient).headers(headers));
        return JSON.parse(result);
    }

    /**
     * 根据path路径查询条目信息 -- 公共接口
     * @param path
     * @return
     * @throws HttpProcessException
     */
    public Object listCalendarApi(String path) throws HttpProcessException {
        StringBuffer url = new StringBuffer(API_BASE_URL).append(path);
        return getDataWithHttpUtils(url.toString());
    }

    /**
     * 查询指定条目信息 -- baGuMi定制接口
     * @return
     * @throws HttpProcessException
     */
    public Object listCalendarApi() throws HttpProcessException {
        StringBuffer url = new StringBuffer(API_BASE_URL).append("/calendar");
        return getDataWithHttpUtils(url.toString());
    }
}
