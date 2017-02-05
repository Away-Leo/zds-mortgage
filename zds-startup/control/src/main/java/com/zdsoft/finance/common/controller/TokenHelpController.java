package com.zdsoft.finance.common.controller;

import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 防止重复令牌获取类
 * 
 * @author Maple
 * @version 1.0
 *
 */
@RequestMapping("/token")
@Controller
public class TokenHelpController extends BaseController {

	/**
	 * 重新获取key
	 * 
	 * @param sourceKey
	 *            资源key
	 * @param request
	 *            请求对象
	 * @return 获取的结果
	 */
	@RequestMapping("/getNewToken")
	@UriKey(key = "com.zdsoft.common.token.getNewToken")
	@ResponseBody
	public ResponseMsg getNewToken(String sourceKey, HttpServletRequest request) {
		ResponseMsg responseMsg = new ResponseMsg();
		String token = UUID.randomUUID().toString();
		request.getSession(false).setAttribute(sourceKey, token);
		logger.info("==========" + sourceKey + "=========获取到新key=====>" + token);
		responseMsg.setMsg("获取新key成功");
		Map<String, Object> optional = new HashMap<String, Object>();
		optional.put("serverToken", token);
		responseMsg.setOptional(optional);
		return responseMsg;
	}
}
