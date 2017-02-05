package com.zdsoft.finance.parameter.controller;

import java.sql.Timestamp;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.zdsoft.finance.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.parameter.entity.Bank;
import com.zdsoft.finance.parameter.entity.CostItem;
import com.zdsoft.finance.parameter.service.CostItemService;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;

import io.netty.util.internal.StringUtil;
@RequestMapping("/paramter")
@Controller
public class CostItemController extends BaseController{
	@Autowired
	private CostItemService costItemService;
	@Autowired
	private CED ced;
	/**
	 * 费用项管理初始化
	 * 
	 * @return 费用项管理页面
	 */
	@RequestMapping("/initCostItem")
	@UriKey(key = "com.zdsoft.finance.parameter.initCostItem")
	@Menu(resource = "com.zdsoft.finance.parameter.initCostItem", label = "费用项管理", group = "businessSetting", order = 1)
	public ModelAndView initCostItem() {
		return new ModelAndView("/parameter/costitem_list");
	}
	
	/**
	 * 获取费用项列表
	 * @param request
	 * @param jsoncallback
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/costItem")
	@UriKey(key = "com.zdsoft.finance.parameter.getCostItem")
	@ResponseBody
	public String getCostItem(HttpServletRequest request, String jsoncallback, PageRequest pageable) {
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
			Page<CostItem> CostItemPage = costItemService.findByHqlConditions(pageable, queryObjs);
			//msg.setMsg("列表查询成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setTotal(CostItemPage.getTotalRows());
			msg.setRows(CostItemPage.getRecords());
		}
		catch(Exception e)
		{
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}

		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 * 更新或添加费用项
	 * @param label
	 * @param jsoncallback
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/updateCostItem")
	@UriKey(key = "com.zdsoft.finance.parameter.updateCostItem")
	@ResponseBody
	public String updateCostItem(CostItem costItem, String jsoncallback, PageRequest pageable) {
		ResponseMsg msg = new ResponseMsg();
		try {
			List<CostItem> costItems=costItemService.findByName(costItem.getName());
			if(costItems!=null&&costItems.size()>0&&!costItems.get(0).getId().equals(costItem.getId()))
			{
				msg.setMsg("操作失败！费用项名称不能重复.");
				msg.setResultStatus(ResponseMsg.APP_ERROR);
			}
			else
			{
				costItem.setUpdateBy(ced.getLoginUser().getEmpCd());
				List<EmpDto> emp=ced.findEmployeesByCodes(new String[]{costItem.getUpdateBy()});
				costItem.setEmpName(emp.get(0).getEmpNm());
				costItem.setUpdateTime(new Timestamp(new Date().getTime()));
				//如果是添加信息，则新建变编码
				if(StringUtil.isNullOrEmpty(costItem.getCode()))
				{
					costItem.setCode("FY"+new Date().getTime());
				}
				costItemService.saveOrUpdateEntity(costItem);
				msg.setMsg("操作成功！");
				msg.setResultStatus(ResponseMsg.SUCCESS);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			msg.setMsg("操作失败！");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 * 删除费用项
	 * @param person
	 * @param jsoncallback
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/deleteCostItem")
	@UriKey(key = "com.zdsoft.finance.parameter.deleteCostItem")
	@ResponseBody
	public String deleteCostItem(CostItem costItem, String jsoncallback, PageRequest pageable) {
		ResponseMsg msg = new ResponseMsg();
		try {
			costItemService.deleteCostItem(costItem.getId());
			msg.setMsg("操作成功！");
			msg.setResultStatus(ResponseMsg.SUCCESS);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			msg.setMsg("操作失败！");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

	/**
	 * 查找所有有效的费用项自定义simpleCode
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping(value = "/findAllEffectiveItemSimpleCode")
	@UriKey(key = "com.zdsoft.finance.findAllEffectiveItemSimpleCode")
	@ResponseBody
	public String findAllEffectiveItemSimpleCode(String jsoncallback){
		List<Map<String,Object>> returnData=new ArrayList<Map<String, Object>>();
		try{
			List<CostItem> sourceData=this.costItemService.findAllEffectiveItem();
			for(CostItem temp : sourceData){
				Map<String,Object> rowData=new HashMap<String,Object>();
				rowData.put("id",temp.getCode());
				rowData.put("text",temp.getName());
				returnData.add(rowData);
			}
		}catch (BusinessException e){
			logger.error("查找所有有效费用项出错！",e);
			e.printStackTrace();
		}
		return ObjectHelper.objectToJson(returnData,jsoncallback);
	}
	
	/**
	 * 获取一个费用项的基本信息
	 * @param label
	 * @param jsoncallback
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/getCostitemBasicMessage")
	@UriKey(key = "com.zdsoft.finance.parameter.getCostitemBasicMessage")
	@ResponseBody
	public String getBasicMessage(HttpServletRequest request, String jsoncallback, PageRequest pageable)
	{
		ResponseMsg msg = new ResponseMsg();
		try {
			Map<String,Object> result=new HashMap<String,Object>();
			result.put("code", "FY"+new Date().getTime());
			result.put("empName", ced.getLoginUser().getEmpNm());
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setOptional(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			msg.setMsg("操作失败！");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

}
