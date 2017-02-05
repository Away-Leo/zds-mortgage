package com.zdsoft.finance.common.utils.app;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zdsoft.framework.core.common.util.HttpClientHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:CasAppClient.java
 * @Package:com.zdsoft.finance.common.utils.app
 * @Description:CAS SSO APP客户端
 * @author: jingjy
 * @date:2017年1月11日 下午7:58:07
 * @version:v1.0
 */
public class CasAppClient {
	
	private static Logger logger = LoggerFactory.getLogger(CasAppClient.class);

	public static boolean validate(String server, String ticketGrantingTicket) throws Exception {
		if (ObjectHelper.isEmpty(ticketGrantingTicket))
			return false;
		Map<String, String> params = new HashMap<String, String>();
		params.put("ticketGrantingTicketId", ticketGrantingTicket);
		return Boolean.parseBoolean(HttpClientHelper.receivePostData(server, params));

	}

	public static String login(String server, String username, String password) throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("username", username);
		params.put("password", password);
		String result = HttpClientHelper.receivePostData(server, params);
		return result;

	}

	public static boolean logout(String server, String ticketGrantingTicket) throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("ticketGrantingTicketId", ticketGrantingTicket);
		return Boolean.parseBoolean(HttpClientHelper.receivePostData(server, params));
	}

	public static void main(String[] args) throws Exception {
	    
	    //http://192.168.34.155:8080/zg-web-luda/api/server/tokenSources/findToken?username=gonght&password=123456
	    
		//APP登陆平台使用
		String server = "http://192.168.34.154:9088/cas/v1/desktopLogin";
		String username = "admin";
		String password = "123456";
		String ticketGrantingTicket = CasAppClient.login(server, username, password);
		//String ticketGrantingTicket = "TGT-2-9dWrW6Qtnpe6U2J1VjBk5yufa7zbOuN17wPCF3TNhHvg4ibT9N-cas";
		logger.info("ticketGrantingTicket---->" + ticketGrantingTicket);
		
		//验证
		boolean validate=CasAppClient.validate("http://192.168.34.154:9088/cas/v1/desktopValidate",ticketGrantingTicket);
		logger.info("validate---->" + validate);
		
		
//		CRA.getAccountByTiket(ticketGrantingTicket);
//		
//		//后端应用验证App用户是否登陆使用
//		server = "http://127.0.0.1:8080/cas/v1/desktopValidate";
//		boolean result = CasAppClient.validate(server, ticketGrantingTicket);
//		logger.info("result---->" + result);
//		
//		//App用户登出
//		server = "http://127.0.0.1:8080/cas/v1/desktopLogout";
//		result = CasAppClient.logout(server, ticketGrantingTicket);
//		logger.info("delete result---->" + result);
	}
}
