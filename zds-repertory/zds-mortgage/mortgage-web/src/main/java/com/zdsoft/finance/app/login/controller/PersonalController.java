package com.zdsoft.finance.app.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.app.LoginInformationDto;
import com.zdsoft.finance.app.login.service.LoginInformationService;
import com.zdsoft.finance.app.login.vo.PersonalVo;
import com.zdsoft.finance.common.utils.AppStatus;
import com.zdsoft.finance.common.utils.app.AppServerUtil;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.exception.AppException;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.client.service.CRA;
import com.zdsoft.framework.cra.dto.AccountDTO;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:PersonalController.java
 * @Package:com.zdsoft.finance.app.login.controller
 * @Description:app个人中心
 * @author: jingjy
 * @date:2017年1月11日 下午8:30:50
 * @version:v1.0
 */
@Controller
@RequestMapping("server/clientService")
public class PersonalController extends BaseController{

    @Log
    private Logger log;
    
    @Autowired
    private CED CED ;
    
    @Autowired
    private CRA CRA ;
    
    @Autowired
    private LoginInformationService loginInformationService ;
    
    
    /**
     * 
     * @Title: initPersonal 
     * @Description: 个人中心主页面
     * @author jingjiyan 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getLoginUserInfo",produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String initPersonal(HttpServletRequest request,HttpServletResponse response){
        log.info("个人中心主页面");
        String token=request.getParameter(AppServerUtil.Token);
        if(ObjectHelper.isEmpty(token)){
            return AppServerUtil.buildError(AppStatus.ArgsError);
        }
        try {
            //员工信息
            //账户信息
            AccountDTO dto2;
                dto2 = CRA.getAccount(token);
            EmpDto emp = CED.getLoginUser(dto2.getId());
            LoginInformationDto dto = loginInformationService.findLoginInformationByUserName(dto2.getAccount());
            PersonalVo vo = new PersonalVo();
            vo.setClientId(emp.getId());
            vo.setName(emp.getEmpNm());
            vo.setLatestDevice(dto.getDevice());
            vo.setLatestLogDate(DateHelper.longToDate(dto.getLoginDate(), DateHelper.DATE_WITHSECOND_FORMAT));
//            emp.get
            vo.setGender(emp.getGendar());
            return AppServerUtil.buildJsonObject(AppStatus.Succeed, vo);
        } catch (AppException e) {
            e.printStackTrace();
            return AppServerUtil.buildError(AppStatus.SystemError,e);
        } catch (Exception e) {
            e.printStackTrace();
            return AppServerUtil.buildError(AppStatus.SystemError,e);
        }
    }
    
    /**
     * 
     * @Title: updatePassword 
     * @Description: 修改密码
     * @author jingjiyan 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/changePwd",produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String updatePassword(HttpServletRequest request,HttpServletResponse response){
        log.info("进入修改密码!");
        String token=request.getParameter(AppServerUtil.Token);
        //String clientId=request.getParameter("clientId");
        String oldPwd=request.getParameter("oldPwd");
        String newPwd=request.getParameter("newPwd");
        try{
            //获取登录信息
            //AccountDTO accountDTO = CRA.getAccountByTiket(token);
            if (ObjectHelper.isEmpty(oldPwd)
                    ||ObjectHelper.isEmpty(newPwd)) {
                return AppServerUtil.buildError(AppStatus.ArgsError);
            }
            if (oldPwd.equals(newPwd)) {
                return AppServerUtil.buildError(AppStatus.LogicError,"新密码和原密码相同！");
            }
            //判断新密码和确认密码
            AccountDTO dto2;
            dto2 = CRA.getAccount(token);
            boolean password = CRA.modifyAccountPassword(dto2.getId(), oldPwd, newPwd);
            if (password) {
                return AppServerUtil.buildJsonMessage(AppStatus.Succeed);
            }
        }catch(Exception e){
            e.printStackTrace();
            return AppServerUtil.buildError(AppStatus.SystemError, e);
        }
        return AppServerUtil.buildError(AppStatus.SystemError);
    }
    
   /**
    * 
    * @Title: initUpdateClientBaseInfo 
    * @Description: 个人中心-更改个人资料信息初始化
    * @author jingjiyan 
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/initUpdateClientBaseInfo",produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String initUpdateClientBaseInfo(HttpServletRequest request,HttpServletResponse response){
        String token=request.getParameter(AppServerUtil.Token);
        log.info("进入更改个人资料初始化！");
        try {
            //员工信息
            AccountDTO dto2 = CRA.getAccount(token);
            EmpDto employee = CED.getLoginUser(dto2.getId());
            PersonalVo vo = new PersonalVo();
            vo.setClientId(employee.getId());
            vo.setName(employee.getEmpNm());
            vo.setEmail(employee.getEmpEmail());
            vo.setPhone(employee.getEmpMobile());
            vo.setQq(employee.getEmpQQ());
            vo.setGender(employee.getGendar());
            return AppServerUtil.buildJsonObject(vo);
        } catch (AppException e) {
            return AppServerUtil.buildError(AppStatus.SystemError, e);
        } catch (Exception e) {
            return AppServerUtil.buildError(AppStatus.SystemError, e);
        } 
    }
    
	/**
	 * 
	 * @Title: updateClientBaseInfo 
	 * @Description: 个人中心-更改个人资料信息保存
	 * @author jingjiyan 
	 * @param request
	 * @param response
	 * @return
	 */
    @RequestMapping(value = "/myProfile",produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String updateClientBaseInfo(HttpServletRequest request,HttpServletResponse response){
        log.info("进入更改个人资料保存！");
        String token=request.getParameter(AppServerUtil.Token);
        String clientNm = request.getParameter("name");
        String sex = request.getParameter("gender");
        String mobile = request.getParameter("phone");
        String email = request.getParameter("email");
        String qq = request.getParameter("qq");
        if (ObjectHelper.isEmpty(sex)||ObjectHelper.isEmpty(mobile)) {
            return AppServerUtil.buildError(AppStatus.ArgsError, "参数传入不全！");
        }
        try {
            AccountDTO dto2 = CRA.getAccount(token);
            dto2.setMobile(mobile);
            dto2.setEmail(email);
            dto2.setAccountNm(clientNm);
            EmpDto employee  = CED.updateContract(mobile, email, qq);
            if(ObjectHelper.isEmpty(employee)){
            	 return AppServerUtil.buildError(AppStatus.SystemError);
            }
            return AppServerUtil.buildJsonMessage(AppStatus.Succeed);
        } catch (Exception e) {
            return AppServerUtil.buildError(AppStatus.SystemError, e);
        }
    }
}
