package com.zdsoft.finance.common.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HttpServletRequestParams.java 
 * @ClassName: HttpServletRequestParams 
 * @Description: request参数
 * @author gufeng 
 * @date 2017年3月13日 下午5:11:15 
 * @version V1.0
 */
public class HttpServletRequestParams {

	
	/**
	 * @Title: setBusiForm 
	 * @Description: 获取所有request参数
	 * @author gufeng 
	 * @param request 请求
	 */
	 public static Set<Map.Entry<String, String>> getPrams(HttpServletRequest request) {
	        Map<String,String> map = new HashMap<String,String>();
			Enumeration<?> paramNames = request.getParameterNames();
	        while (paramNames.hasMoreElements()) {
	            String paramName = (String) paramNames.nextElement();

	            String[] paramValues = request.getParameterValues(paramName);
	            if (paramValues.length == 1) {
	                String paramValue = paramValues[0];
	                if (paramValue.length() != 0) {
	                    map.put(paramName, paramValue);
	                }
	            }
	        }
	        Set<Map.Entry<String, String>> set = map.entrySet();
	        return set;
	    }
}
