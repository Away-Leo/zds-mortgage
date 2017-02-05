package com.zdsoft.finance.parameter.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.parameter.entity.LitigationData;
import com.zdsoft.finance.parameter.entity.LitigationData;
import com.zdsoft.finance.parameter.service.LitigationDataService;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;
@RequestMapping("/paramter")
@Controller
public class LitigationDataController extends BaseController{
	@Autowired
	private LitigationDataService litigationDataService;
	/**
	 * 诉讼资料配置初始化
	 * 
	 * @return 诉讼资料配置页面
	 */
	@RequestMapping("/initLitigationData")
	@UriKey(key = "com.zdsoft.finance.parameter.initLitigationData")
	@Menu(resource = "com.zdsoft.finance.parameter.initLitigationData", label = "诉讼资料配置", group = "businessSetting", order = 1)
	public ModelAndView initLitigationData() {
		return new ModelAndView("/parameter/litigationdata_list");
	}
	
	/**
	 * 获取诉讼资料列表
	 * @param request
	 * @param jsoncallback
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/litigationData")
	@UriKey(key = "com.zdsoft.finance.parameter.getLitigationData")
	@ResponseBody
	public String getLitigationData(HttpServletRequest request, String jsoncallback, PageRequest pageable) {
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
			// 分页抵押权人
			Page<LitigationData> LitigationDataPage = litigationDataService.findByHqlConditions(pageable, queryObjs);
			//msg.setMsg("列表查询成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setTotal(LitigationDataPage.getTotalRows());
			msg.setRows(LitigationDataPage.getRecords());
		}
		catch(Exception e)
		{
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}

		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 * 更新标签
	 * @param label
	 * @param jsoncallback
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/updateLitigationData")
	@UriKey(key = "com.zdsoft.finance.parameter.updateLitigationData")
	@ResponseBody
	public String updateLitigationData(LitigationData label, String jsoncallback, PageRequest pageable) {
		ResponseMsg msg = new ResponseMsg();
		try {
			litigationDataService.saveOrUpdateEntity(label);
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
	 * 删除标签
	 * @param person
	 * @param jsoncallback
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/deleteLitigationData")
	@UriKey(key = "com.zdsoft.finance.parameter.deleteLitigationData")
	@ResponseBody
	public String deleteLitigationData(LitigationData label, String jsoncallback, PageRequest pageable) {
		ResponseMsg msg = new ResponseMsg();
		try {
			litigationDataService.deleteLitigationData(label.getId());
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
