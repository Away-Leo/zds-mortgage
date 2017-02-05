package com.zdsoft.finance.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.multipart.MultipartResolver;

import com.zdsoft.finance.common.utils.AppStatus;
import com.zdsoft.finance.common.utils.app.AppServerUtil;
import com.zdsoft.framework.core.common.component.SpringContextHolder;
import com.zdsoft.framework.core.common.util.StoreHelper;
import com.zdsoft.framework.cra.client.service.CRA;
import com.zdsoft.framework.cra.dto.AccountDTO;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:APIValidateFilter.java
 * @Package:com.zdsoft.finance.filter
 * @Description:API请求拦截器
 * @author: jingyh
 * @date:2017年1月14日 下午4:39:32
 * @version:v1.0
 */
public class APIValidateFilter implements Filter {
	
	private CRA CRA;
	
	// 用于创建MultipartHttpServletRequest  
	private MultipartResolver multipartResolver = null;  
	
	private final static Logger log = LoggerFactory.getLogger(APIValidateFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext());
		CRA=wac.getBean(CRA.class);
		// 注入bean  
	    multipartResolver = ((MultipartResolver)SpringContextHolder.getBean("multipartResolver"));  
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException 
	{
	      HttpServletRequest httpRequest = (HttpServletRequest) request;
	        HttpServletResponse httpResponse = (HttpServletResponse) response;
	        showRequestInfo(httpRequest);
	        // 登录的uri
	        String tokenUri=httpRequest.getContextPath()+"/spi/server/tokenSource/userLogin";
	        if(httpRequest.getRequestURI().equals(tokenUri))
	        { //排除token获取控制器的拦截
	            log.info("如果是登录走这个条件");
	            log.info("RequestMapping('/server/tokenSource')tokenUri:"+tokenUri);
	            chain.doFilter(httpRequest, httpResponse);
	            return ;
	        }
	        
	        String enctype = request.getContentType();  
		    if (StringUtils.isNotBlank(enctype) && enctype.contains("multipart/form-data")) {
		    	// 返回 MultipartHttpServletRequest 用于获取 multipart/form-data 方式提交的请求中 上传的参数  
		    	httpRequest = multipartResolver.resolveMultipart((HttpServletRequest) request);  
		    }
	        
		    String token=(String) httpRequest.getParameter(AppServerUtil.Token);
	        if(token==null || "".equals(token))
	        {//当验证参数不完整!
	            log.info("token==null");
	            String json=AppServerUtil.buildError(AppStatus.ArgsError);
	            AppServerUtil.responseJson(json,httpResponse);
	            return ;
	        }
	        
	        log.info("验证token的合法性");
	        if(validateToken(token,httpRequest))
	        {
	            log.info("ValidateTokenSucceed!");
	            log.info("doController……");
	            try {
	            	StoreHelper.setUserToken(token);
	                chain.doFilter(httpRequest, httpResponse);
	            } catch (Exception e) {
	                log.info("doController error!");
	                String json=AppServerUtil.buildError(AppStatus.SystemError,"doController error!");
	                AppServerUtil.responseJson(json,httpResponse);
	                return ;
	            }
	        } else {//token验证试验外网地址容易出现验证超时
	            log.info("ValidateTokenFailed!");
	            String json=AppServerUtil.buildError(AppStatus.TokenValidateError,"token无效!");
	            AppServerUtil.responseJson(json,httpResponse);
	            return ;
	        }
	}
	
	private boolean validateToken(String token,HttpServletRequest request) {
		return validateTokenByClient(token);
	}
	
	/**
	 * 通过cas客户端验证token是否失效
	 * @param token
	 * @return
	 */
	private boolean validateTokenByClient(String token)
	{
		boolean ok=false;
		try {
			AccountDTO dto = CRA.getAccount(token);
			if (AccountDTO.SUCCESS.equals(dto.getResultStatus())) {
				log.info("ValidateTokenSucceed!");
				ok=true;
			} else {
				log.info("ValidateTokenFailed!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("ValidateTokenByCasAppClientError");
		}
		return ok;
	}
	/**
	 * 
	 * 打印请求参数
	 *
	 * @author jingyh
	 * @param request
	 */
	public void showRequestInfo(HttpServletRequest request)
	{
		log.info("*********showRequestInfo**********");
		log.info("RequestUri:"+request.getRequestURI());
		@SuppressWarnings("rawtypes")
        Enumeration names=request.getParameterNames();
		while(names.hasMoreElements())
		{
			Object obj=names.nextElement();
			if(obj instanceof String)
			{
				String key=(String)obj;
				log.info("key="+key+",value="+request.getParameter(key));
			}
		}
	}
	@Override
	public void destroy() {

	}
}
