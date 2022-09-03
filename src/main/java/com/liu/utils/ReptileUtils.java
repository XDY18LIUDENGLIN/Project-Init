package com.liu.utils;

import com.alibaba.fastjson2.JSON;
import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.builder.HCB;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.common.HttpHeader;
import com.arronlong.httpclientutil.common.SSLs;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.liu.service.AllBaseConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.impl.client.CloseableHttpClient;

/**
 * @author : LiuDengLin
 * Email : 2092669791@qq.com
 * date : 2022/7/26 23:22
 * describe: 爬虫接口
 */
@Slf4j
public class ReptileUtils extends AllBaseConfig {
    /**
     * 用于请求接口的公共方法 只需要传入一个url就能自动进行请求
     *
     * @param url
     * @return
     */
    public static Object getDataWithHttpUtils(String url) throws HttpProcessException {
        Header[] headers = HttpHeader.custom().userAgent(AllBaseConfig.BanGuMiUserAgent).build();
        HCB hcb = HCB.custom().sslpv(SSLs.SSLProtocolVersion.TLSv1_2);
        CloseableHttpClient httpClient = hcb.build();
        String result = HttpClientUtil.get(HttpConfig.custom().url(url).client(httpClient).headers(headers));
        return JSON.parse(result);
    }

    /**
     * 根据path路径查询条目信息 -- 公共接口
     *
     * @param path
     * @return
     * @throws HttpProcessException
     */
    public static Object listCalendarApi(String path) throws HttpProcessException {
        StringBuffer url = new StringBuffer(AllBaseConfig.BanGuMiApiBaseUrl).append(path);
        return getDataWithHttpUtils(url.toString());
    }

    /**
     * 查询指定条目信息 -- baGuMi定制接口
     *
     * @return
     * @throws HttpProcessException
     */
    public static Object listCalendarApi() throws HttpProcessException {
        StringBuffer url = new StringBuffer(AllBaseConfig.BanGuMiApiBaseUrl).append("/calendar");
        return getDataWithHttpUtils(url.toString());
    }
}
