package com.zdsoft.finance.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:HpiValidateFilter.java
 * @Package:com.zdsoft.finance.filter
 * @Description:三方接口拦截器
 * @author: jingjy
 * @date:2017年1月19日 下午4:57:18
 * @version:v1.0
 */
public class HpiValidateFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String path = httpRequest.getScheme() + "://"
				+ httpRequest.getServerName() + ":"
				+ httpRequest.getServerPort() + httpRequest.getRequestURI();
		if (path.indexOf("/hpi") > -1) {
			chain.doFilter(httpRequest, httpResponse);
			return;
		} else {
			httpResponse.sendError(404, "访问其他资源失败!");
		}
	}

	@Override
	public void destroy() {
	}

}
