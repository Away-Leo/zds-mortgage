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
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:AppServerUtil.java
 * @Package:com.zdsoft.guarantee.web.util
 * @Description:app 返回工具类
 * @author: dengyy
 * @date:2016年8月2日 下午2:41:20
 * @version:v1.0
 */
public class AppServerUtil {

	public static int DefaultPageIndex=1;
	public static int DefaultPageSize=10;
	public static String Token="token";
	/**
	 * 
	 * @param status 状态码
	 * @param data 单条数据
	 * @return
	 */
	public static String buildJsonObject(AppStatus status,Object data)
	{
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("status", status.value());
		jsonMap.put("message", status.name());
		jsonMap.put("data", data);
		return ObjectHelper.objectToJson(jsonMap);
	}
	/**
     * 
     * @param status 状态码
     * @param data 单条数据
     * @return
     */
    public static String buildJsonObjectTwo(Map<String, Object> map)
    {
        return ObjectHelper.objectToJson(map);
    }
	/**
	 * 
	 * @param status 状态码
	 * @param message 消息说明
	 * @param data 单条数据
	 * @return
	 */
	public static String buildJsonObject(AppStatus status,String msg,Object data)
	{
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("status", status.value());
		jsonMap.put("message",msg);
		jsonMap.put("data", data);
		return ObjectHelper.objectToJson(jsonMap);
	}
	public static String buildJsonObject(Object data)
	{
		return buildJsonObject(AppStatus.Succeed, data);
	}
	public static String buildJsonMessage(AppStatus status,String msg)
	{
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("status", status.value());
		jsonMap.put("message", msg);
		return ObjectHelper.objectToJson(jsonMap);
	}
	public static String buildJsonMessage(String msg)
	{
		return buildJsonMessage(AppStatus.Succeed, msg);
	}
	public static String buildJsonMessage(AppStatus status)
	{
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("status", status.value());
		jsonMap.put("message", status.name());
		return ObjectHelper.objectToJson(jsonMap);
	}
	public static String buildError(AppStatus status,Exception e)
	{
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("status", status.value());
		jsonMap.put("message", e.getClass().getName()+":"+e.getMessage());
		return ObjectHelper.objectToJson(jsonMap);
	}
	public static String buildError(AppStatus status)
	{
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("status", status.value());
		jsonMap.put("message", status.name());
		return ObjectHelper.objectToJson(jsonMap);
	}
	public static String buildError(AppStatus status,String errorInfo)
	{
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("status",status.value());
		jsonMap.put("message",errorInfo);
		return ObjectHelper.objectToJson(jsonMap);
	}
	
	/**
	 * 
	 * @param status 状态
	 * @param list    多条数据
	 * @return
	 */
	public static String buildJsonList(AppStatus status,List<?> list)
	{
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Map<String, Object> data = new HashMap<String, Object>();
		int size = 0 ;
		if (ObjectHelper.isNotEmpty(list)) {
		    size = list.size() ;
        }
		data.put("totalCount", size);
		data.put("list", list);
		jsonMap.put("status", status.value());
		jsonMap.put("message", status.name());
		jsonMap.put("data", data);
		return ObjectHelper.objectToJson(jsonMap);
	}
	/**
	 * @param list 多条数据
	 * @return
	 */
	public static String buildJsonList(List<?> list)
	{
		return buildJsonList(AppStatus.Succeed, list);
	}
	
	/**
	 * @param status 状态
	 * @param message 消息说明
	 * @param list    多条数据
	 * @return
	 */
	public static String buildJsonList(AppStatus status,String msg,List<?> list)
	{
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Map<String, Object> data = new HashMap<String, Object>();
		int size = 0 ;
        if (ObjectHelper.isNotEmpty(list)) {
            size = list.size() ;
        }
		data.put("totalCount", size);
		data.put("list", list);
		jsonMap.put("status", status.value());
		jsonMap.put("message",msg);
		jsonMap.put("data", data);
		return ObjectHelper.objectToJson(jsonMap);
	}
	
	
	public static void responseJson(String json,HttpServletResponse response)
	{
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
