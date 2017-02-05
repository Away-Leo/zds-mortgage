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
import com.zdsoft.finance.parameter.entity.SysLabel;
import com.zdsoft.finance.parameter.entity.SysLabel;
import com.zdsoft.finance.parameter.service.SysLabelService;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;
@RequestMapping("/paramter")
@Controller
public class SysLabelController extends BaseController{
	@Autowired
	private SysLabelService sysLabelService;
	/**
	 * 标签设置初始化
	 * 
	 * @return 标签设置页面
	 */
	@RequestMapping("/initSysLabel")
	@UriKey(key = "com.zdsoft.finance.parameter.initSysLabel")
	@Menu(resource = "com.zdsoft.finance.parameter.initSysLabel", label = "标签设置", group = "businessSetting", order = 1)
	public ModelAndView initSysLabel() {
		return new ModelAndView("/parameter/syslabel_list");
	}
	
	/**
	 * 获取标签列表
	 * @param request
	 * @param jsoncallback
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/sysLabel")
	@UriKey(key = "com.zdsoft.finance.parameter.getSysLabel")
	@ResponseBody
	public String getSysLabel(HttpServletRequest request, String jsoncallback, PageRequest pageable) {
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
			Page<SysLabel> sysLabelPage = sysLabelService.findByHqlConditions(pageable, queryObjs);
			//msg.setMsg("列表查询成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setTotal(sysLabelPage.getTotalRows());
			msg.setRows(sysLabelPage.getRecords());
		}
		catch(Exception e)
		{
			e.printStackTrace();
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
	@RequestMapping("/updateSysLabel")
	@UriKey(key = "com.zdsoft.finance.parameter.updateSysLabel")
	@ResponseBody
	public String updateSysLabel(SysLabel label, String jsoncallback, PageRequest pageable) {
		ResponseMsg msg = new ResponseMsg();
		try {
			sysLabelService.saveOrUpdateEntity(label);
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
	@RequestMapping("/deleteSysLabel")
	@UriKey(key = "com.zdsoft.finance.parameter.deleteSysLabel")
	@ResponseBody
	public String deleteSysLabel(SysLabel label, String jsoncallback, PageRequest pageable) {
		ResponseMsg msg = new ResponseMsg();
		try {
			sysLabelService.deleteLabel(label.getId());
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
