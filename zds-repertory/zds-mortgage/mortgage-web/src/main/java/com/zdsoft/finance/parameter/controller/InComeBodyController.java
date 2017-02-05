package com.zdsoft.finance.parameter.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.parameter.entity.InComeBody;
import com.zdsoft.finance.parameter.entity.InComeBody;
import com.zdsoft.finance.parameter.entity.InComeBody;
import com.zdsoft.finance.parameter.entity.MortgagePerson;
import com.zdsoft.finance.parameter.service.InComeBodyService;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;
@RequestMapping("/paramter")
@Controller
public class InComeBodyController extends BaseController{
	@Autowired
	private InComeBodyService inComeBodyService;
	@Autowired
	private CED ced;
	/**
	 * 收款主体初始化
	 * 
	 * @return 收款主体页面
	 */
	@RequestMapping("/initInComeBody")
	@UriKey(key = "com.zdsoft.finance.parameter.initInComeBody")
	@Menu(resource = "com.zdsoft.finance.parameter.initInComeBody", label = "收款主体", group = "businessSetting", order = 1)
	public ModelAndView initInComeBody() {
		return new ModelAndView("/parameter/incomebody_list");
	}
	
	/**
	 * 收款主体列表
	 * @param request
	 * @param jsoncallback
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/inComeBody")
	@UriKey(key = "com.zdsoft.finance.parameter.getInComeBody")
	@ResponseBody
	public String getInComeBody(HttpServletRequest request, String jsoncallback, PageRequest pageable) {
		ResponseMsg msg = new ResponseMsg();
		try
		{
			// 获取页面封装的查询参数
			List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
			QueryObj obj = new QueryObj();
			obj.setObj("updateTime");
			obj.setElement("OB");
			obj.setOperator("OB");
			obj.setValue("DESC");
			queryObjs.add(obj);
			List<InComeBody> poList=new ArrayList<InComeBody>();
			// 分页抵押权人
			Page<InComeBody> inComeBodyPage = inComeBodyService.findByHqlConditions(pageable, queryObjs);
			poList=inComeBodyPage.getRecords();
			for(InComeBody po : poList){
				po.setOrgName(ced.loadOrganizationByCode(po.getOrgId()).getOrgNm());
			}
			//msg.setMsg("列表查询成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setTotal(inComeBodyPage.getTotalRows());
			msg.setRows(inComeBodyPage.getRecords());
		}
		catch(Exception e)
		{
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}

		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 * 更新收款主体
	 * @param label
	 * @param jsoncallback
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/updateInComeBody")
	@UriKey(key = "com.zdsoft.finance.parameter.updateInComeBody")
	@ResponseBody
	public String updateInComeBody(InComeBody inComeBody, String jsoncallback, PageRequest pageable) {
		ResponseMsg msg = new ResponseMsg();
		try {
			inComeBody.setUpdateTime(new Timestamp(new Date().getTime()));
			inComeBodyService.saveOrUpdateEntity(inComeBody);
			msg.setMsg("操作成功！");
			msg.setResultStatus(ResponseMsg.SUCCESS);

		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			msg.setMsg("操作失败！");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 * 删除收款主体
	 * @param person
	 * @param jsoncallback
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/deleteInComeBody")
	@UriKey(key = "com.zdsoft.finance.parameter.deleteInComeBody")
	@ResponseBody
	public String deleteInComeBody(InComeBody inComeBody, String jsoncallback, PageRequest pageable) {
		ResponseMsg msg = new ResponseMsg();
		try {
			inComeBodyService.deleteInComeBody(inComeBody.getId());
			msg.setMsg("操作成功！");
			msg.setResultStatus(ResponseMsg.SUCCESS);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			msg.setMsg("操作失败！");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

}
