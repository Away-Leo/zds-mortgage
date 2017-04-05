package com.zdsoft.finance.common.utils.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.zdsoft.finance.common.utils.AppStatus;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:AppServerUtil.java
 * @Package:com.zdsoft.guarantee.web.util
 * @Description:app 返回工具类
 * @author: dengyy
 * @date:2016年8月2日 下午2:41:20
 * @version:v1.0
 */
public class AppServerUtil {

    //分页 当前页
    public static int DefaultPageIndex = 1;
    //分页最大条数
    public static int DefaultPageSize = 10;
    
    public static String Token = "token";

    /**
     * 
     * @Title: buildJsonObject 
     * @Description: app 正常返回值
     * @author dengyy 
     * @param status 状态码
     * @param data 返回数据
     * @return json数据
     */
    public static String buildJsonObject(AppStatus status, Object data) {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("status", status.value());
        jsonMap.put("message", status.name());
        jsonMap.put("data", data);
        return ObjectHelper.objectToJson(jsonMap);
    }

    /**
     * 
     * @Title: buildJsonObjectTwo 
     * @Description: app 正常返回值
     * @author dengyy 
     * @param map map集合数据
     * @return json数据
     */
    public static String buildJsonObjectTwo(Map<String, Object> map) {
        return ObjectHelper.objectToJson(map);
    }

    /**
     * 
     * @Title: buildJsonObject 
     * @Description: app 正常返回值
     * @author dengyy 
     * @param status 返回状态
     * @param msg 返回信息
     * @param data 返回数据
     * @return json数据
     */
    public static String buildJsonObject(AppStatus status, String msg, Object data) {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("status", status.value());
        jsonMap.put("message", msg);
        jsonMap.put("data", data);
        return ObjectHelper.objectToJson(jsonMap);
    }

    /**
     * 
     * @Title: buildJsonObject 
     * @Description: app 正常返回值
     * @author dengyy 
     * @param data 返回数据
     * @return json格式
     */
    public static String buildJsonObject(Object data) {
        return buildJsonObject(AppStatus.Succeed, data);
    }

    /**
     * 
     * @Title: buildJsonMessage 
     * @Description: app 信息返回
     * @author dengyy 
     * @param status 状态值
     * @param msg 返回信息
     * @return json数据
     */
    public static String buildJsonMessage(AppStatus status, String msg) {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("status", status.value());
        jsonMap.put("message", msg);
        return ObjectHelper.objectToJson(jsonMap);
    }
    
    /**
     * 
     * @Title: buildJsonMessage 
     * @Description: app 信息返回
     * @author dengyy 
     * @param msg 异常信息
     * @return json数据
     */
    public static String buildJsonMessage(String msg) {
        return buildJsonMessage(AppStatus.SystemError, msg);
    }
    
    /**
     * 
     * @Title: buildJsonMessage 
     * @Description: app 信息返回
     * @author dengyy 
     * @param status 异常状态
     * @return json数据
     */
    public static String buildJsonMessage(AppStatus status) {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("status", status.value());
        jsonMap.put("message", status.name());
        return ObjectHelper.objectToJson(jsonMap);
    }

    /**
     * 
     * @Title: buildError 
     * @Description: app 异常信息返回
     * @author dengyy 
     * @param status 状态值
     * @param e 异常信息
     * @return json信息
     */
    public static String buildError(AppStatus status, Exception e) {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("status", status.value());
        jsonMap.put("message", e.getClass().getName() + ":" + e.getMessage());
        return ObjectHelper.objectToJson(jsonMap);
    }

    /**
     * 
     * @Title: buildError 
     * @Description: app 异常信息返回
     * @author dengyy 
     * @param status 异常状态
     * @return json数据
     */
    public static String buildError(AppStatus status) {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("status", status.value());
        jsonMap.put("message", status.name());
        return ObjectHelper.objectToJson(jsonMap);
    }
    
    /**
     * 
     * @Title: buildError 
     * @Description: app 异常信息返回
     * @author dengyy 
     * @param status 状态值
     * @param errorInfo 异常信息
     * @return json数据
     */
    public static String buildError(AppStatus status, String errorInfo) {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("status", status.value());
        jsonMap.put("message", errorInfo);
        return ObjectHelper.objectToJson(jsonMap);
    }

    /**
     * 
     * @Title: buildJsonList 
     * @Description: app 正常数据多条数据返回
     * @author dengyy 
     * @param status 状态值
     * @param list 数据集合
     * @return json数据
     */
    public static String buildJsonList(AppStatus status, List<?> list) {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        Map<String, Object> data = new HashMap<String, Object>();
        int size = 0;
        if (ObjectHelper.isNotEmpty(list)) {
            size = list.size();
        }
        data.put("totalCount", size);
        data.put("list", list);
        jsonMap.put("status", status.value());
        jsonMap.put("message", status.name());
        jsonMap.put("data", data);
        return ObjectHelper.objectToJson(jsonMap);
    }

    /**
     * 
     * @Title: buildJsonList 
     * @Description: app 正常数据多条数据返回
     * @author dengyy 
     * @param list 数据集合
     * @return json数据
     */
    public static String buildJsonList(List<?> list) {
        return buildJsonList(AppStatus.Succeed, list);
    }

    /**
     * 
     * @Title: buildJsonList 
     * @Description: app 正常数据多条数据返回
     * @author dengyy 
     * @param status 状态值
     * @param msg 返回信息
     * @param list 数据集合
     * @return json数据
     */
    public static String buildJsonList(AppStatus status, String msg, List<?> list) {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        Map<String, Object> data = new HashMap<String, Object>();
        int size = 0;
        if (ObjectHelper.isNotEmpty(list)) {
            size = list.size();
        }
        data.put("totalCount", size);
        data.put("list", list);
        jsonMap.put("status", status.value());
        jsonMap.put("message", msg);
        jsonMap.put("data", data);
        return ObjectHelper.objectToJson(jsonMap);
    }
    
    /**
     * 
     * @Title: responseJson 
     * @Description: 返回json请求信息
     * @author dengyy 
     * @param json json数据
     * @param response 请求
     */
    public static void responseJson(String json, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(json);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

}
