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
import com.zdsoft.finance.parameter.entity.MortgagePerson;
import com.zdsoft.finance.parameter.service.MortgagePersonService;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;

@RequestMapping("/parameter")
@Controller
public class MortgagePersonController extends BaseController {
	@Autowired
	private MortgagePersonService mortgagePersonService;
	@Autowired
	private CED ced;

	/**
	 * 抵押权人管理初始化
	 * 
	 * @return 抵押权人管理页面
	 */
	@RequestMapping("/initPerson")
	@UriKey(key = "com.zdsoft.finance.parameter.initPerson")
	@Menu(resource = "com.zdsoft.finance.parameter.initPerson", label = "抵押权人管理", group = "businessSetting", order = 1)
	public ModelAndView initPerson() {
		return new ModelAndView("/parameter/mortgageperson_list");
	}
	
	/**
	 * 获取抵押权人列表
	 * @param request
	 * @param jsoncallback
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/mortgagePerson")
	@UriKey(key = "com.zdsoft.finance.parameter.getMortgagePerson")
	@ResponseBody
	public String getMortgagePerson(HttpServletRequest request, String jsoncallback, PageRequest pageable) {
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
			List<MortgagePerson> poList=new ArrayList<MortgagePerson>();
			// 分页抵押权人
			Page<MortgagePerson> mortgagePersonPage = mortgagePersonService.findByHqlConditions(pageable, queryObjs);
			poList=mortgagePersonPage.getRecords();
			for(MortgagePerson po : poList){
				po.setOrgName(ced.loadOrganizationByCode(po.getOrgId()).getOrgNm());
			}
			//msg.setMsg("列表查询成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setTotal(mortgagePersonPage.getTotalRows());
			msg.setRows(mortgagePersonPage.getRecords());
		}
		catch(Exception e)
		{
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}

		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 * 更新或者添加抵押权人
	 * @param person
	 * @param jsoncallback
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/updateMortgagePerson")
	@UriKey(key = "com.zdsoft.finance.parameter.updateMortgagePerson")
	@ResponseBody
	public String updateMortgagePerson(MortgagePerson person, String jsoncallback, PageRequest pageable) {
		ResponseMsg msg = new ResponseMsg();
		try {
			person.setUpdateTime(new Timestamp(new Date().getTime()));
			mortgagePersonService.saveOrUpdateEntity(person);
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
	 * 删除抵押权人
	 * @param person
	 * @param jsoncallback
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/deleteMortgagePerson")
	@UriKey(key = "com.zdsoft.finance.parameter.deleteMortgagePerson")
	@ResponseBody
	public String deleteMortgagePerson(MortgagePerson person, String jsoncallback, PageRequest pageable) {
		ResponseMsg msg = new ResponseMsg();
		try {
			mortgagePersonService.deletePerson(person.getId());
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
