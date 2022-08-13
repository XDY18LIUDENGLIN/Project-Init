package com.liu.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : LiuDengLin
 * Email : 2092669791@qq.com
 * date : 2022/7/31 13:19
 * describe: BaGuMi处理工具类
 */
public class BaGuMiUtils {

    /**
     * 正则处理排名
     * @param s
     * @return
     */
    public static int getNumInString(String s){
        String regEx="[^0-9]";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(s);
        String trim = matcher.replaceAll("").trim();
        return Integer.parseInt(trim);
    }
}
