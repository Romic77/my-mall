package com.example.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/*****
 * @Author:
 * @Description:URL工具
 ****/
public class UrlUtils {

    /**
     * 去掉URL中指定的参数
     */
    public static String replateUrlParameter(String url,String... names){
        for (String name : names) {
            url = url.replaceAll("(&"+name+"=([0-9\\w]+))|("+name+"=([0-9\\w]+)&)|("+name+"=([0-9\\w]+))", "");
        }
        return url;
    }

    /***
     * 当前请求地址组装
     */
    public static String map2url(String baseUrl,Map<String,Object> searchMap,String... names){
        //参数获取
        String parm = map2parm(searchMap);
        if(!StringUtils.isEmpty(parm)){
            baseUrl+="?"+parm;
        }
        //去掉指定参数
        baseUrl = replateUrlParameter(baseUrl,names);
        return baseUrl;
    }

    /**
     * 将map转换成url参数
     * @param map
     * @return
     */
    public static String map2parm(Map<String, Object> map) {
        if (map == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            sb.append(entry.getKey() + "=" + entry.getValue());
            sb.append("&");
        }
        String parameters = sb.toString();
        if (parameters.endsWith("&")) {
            parameters = StringUtils.substringBeforeLast(parameters ,"&");
        }
        return parameters;
    }
}
