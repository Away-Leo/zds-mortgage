package com.zdsoft.finance.app.token.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdsoft.finance.app.LoginInformationDto;
import com.zdsoft.finance.app.login.service.LoginInformationService;
import com.zdsoft.finance.common.utils.AppStatus;
import com.zdsoft.finance.common.utils.app.AppServerUtil;
import com.zdsoft.framework.core.client.RPCClient;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.configure.AppParameter;
import com.zdsoft.framework.core.common.dto.SecureWraper;
import com.zdsoft.framework.core.common.exception.InvalidArgumentException;
import com.zdsoft.framework.core.common.protocol.RPCRequest;
import com.zdsoft.framework.core.common.protocol.RPCResponse;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.dto.LoginDto;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:TokenSources.java
 * @Package:com.zdsoft.finance.app.token
 * @Description:app 登录接口
 * @author: jingjy
 * @date:2017年1月12日 下午2:38:10
 * @version:v1.0
 */
@Controller
@RequestMapping("/server/tokenSource")
public class TokenSources extends BaseController{
    
	@Log
	private Logger log;
	
	@Autowired
	private LoginInformationService loginInformationService ;
	
	/**
	 * 
	 * 用户登录的接口
	 *
	 * @author jingyh
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/userLogin",produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String findTokenTwo(HttpServletRequest request,HttpServletResponse response){
        LoginDto dto = new LoginDto();
         RPCRequest request1 = new RPCRequest();
         request1.setServiceName("com.zdsoft.framework.cra.login");
         String username=request.getParameter("username");
         String password=request.getParameter("password");
         //当前设备手机号
         String mobile = request.getParameter("mobile");
         //当前设备名称
         String device = request.getParameter("device");
         SecureWraper sw = new SecureWraper();
         sw.enroll("loginNm", username).enroll("password", password).enroll("ipAddr", "").enroll("deviceName", "");
         sw.enroll("mac", "");
         sw.enroll("computerNm", "");
         request1.put("param", sw.serialize());
         request1.put("ip",AppParameter.getResourceRpcIp());
         RPCResponse response1=null;
         try {
             response1 = RPCClient.getInstance().request(AppParameter.getResourceRpcIp(),Integer.valueOf(AppParameter.getResourceRpcPort()), request1);
             if (ObjectHelper.isNotEmpty(response1) && response1.getStatus().intValue() == RPCResponse.SUCCESS.intValue()) {
                 dto = (LoginDto) response1.getBodys().get("result");
               //保存用户登录记录
                 LoginInformationDto loginDto = new LoginInformationDto();
                 loginDto.setDevice(device);
                 loginDto.setMobile(mobile);
                 loginDto.setUsername(username);
                 loginInformationService.saveOrUpdate(loginDto);
                 String token =  dto.getUserToken();
                 Map<String,String> v=new HashMap<String,String>();
                 v.put("token",token);
                 return AppServerUtil.buildJsonObject(AppStatus.Succeed,v);
             } else {
                 dto.setResultStatus(LoginDto.APP_ERROR);
                 dto.setMsg("登录接口错误--->" + response1.getStatusDesc());
             }
         } catch (NumberFormatException e) {
        	 e.printStackTrace();
        	 return AppServerUtil.buildError(AppStatus.ArgsError,"无效的用户名或密码或者系统错误!");
         } catch (InvalidArgumentException e) {
        	 e.printStackTrace();
             return AppServerUtil.buildError(AppStatus.ArgsError,"无效的用户名或密码或者系统错误!");
         } catch (Exception e) {
        	 e.printStackTrace();
             return AppServerUtil.buildError(AppStatus.LogicError,"无效的用户名或密码或者系统错误!");
         }
         return AppServerUtil.buildError(AppStatus.ArgsError,"账号和密码无效!");
   }   
}
