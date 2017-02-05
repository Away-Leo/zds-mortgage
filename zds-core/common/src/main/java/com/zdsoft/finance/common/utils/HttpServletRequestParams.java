package com.zdsoft.finance.common.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * request参数
 * @author <a href="mailto:gufeng@zdsoft.cn">gufeng</a>
 * @date 2016-04-14
 */
public class HttpServletRequestParams {

	/**
	 * 获取所有request参数
	 * @param request
	 * @return
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
