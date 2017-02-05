package com.zdsoft.finance.parameter.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.parameter.entity.Bank;
import com.zdsoft.finance.parameter.entity.Bank;
import com.zdsoft.finance.parameter.service.BankService;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;
@RequestMapping("/paramter")
@Controller
public class BankController extends BaseController{
	@Autowired
	private BankService bankService;
	/**
	 * 银行代码配置初始化
	 * 
	 * @return 银行代码配置页面
	 */
	@RequestMapping("/initBank")
	@UriKey(key = "com.zdsoft.finance.parameter.initBank")
	@Menu(resource = "com.zdsoft.finance.parameter.initBank", label = "银行代码配置", group = "businessSetting", order = 1)
	public ModelAndView initBank() {
		return new ModelAndView("/parameter/bank_list");
	}
	
	/**
	 * 银行代码列表
	 * @param request
	 * @param jsoncallback
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/bank")
	@UriKey(key = "com.zdsoft.finance.parameter.getBank")
	@ResponseBody
	public String getBank(HttpServletRequest request, String jsoncallback, PageRequest pageable) {
		ResponseMsg msg = new ResponseMsg();
		try
		{
			// 获取页面封装的查询参数
			List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
			// 分页抵押权人
			Page<Bank> BankPage = bankService.findByHqlConditions(pageable, queryObjs);
			//msg.setMsg("列表查询成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setTotal(BankPage.getTotalRows());
			msg.setRows(BankPage.getRecords());
		}
		catch(Exception e)
		{
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}

		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 * 更新银行代码
	 * @param label
	 * @param jsoncallback
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/updateBank")
	@UriKey(key = "com.zdsoft.finance.parameter.updateBank")
	@ResponseBody
	public String updateBank(Bank bank, String jsoncallback, PageRequest pageable) {
		ResponseMsg msg = new ResponseMsg();
		try {
			List<Bank> banks=bankService.findByCode(bank.getCode());
			if(banks!=null&&banks.size()>0&&!banks.get(0).getId().equals(bank.getId()))
			{
				msg.setMsg("操作失败！银行代码不能重复.");
				msg.setResultStatus(ResponseMsg.APP_ERROR);
			}
			else
			{
				bankService.saveOrUpdateEntity(bank);
				msg.setMsg("操作成功！");
				msg.setResultStatus(ResponseMsg.SUCCESS);
			}

		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			msg.setMsg("操作失败！");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 * 删除银行代码
	 * @param person
	 * @param jsoncallback
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/deleteBank")
	@UriKey(key = "com.zdsoft.finance.parameter.deleteBank")
	@ResponseBody
	public String deleteBank(Bank bank, String jsoncallback, PageRequest pageable) {
		ResponseMsg msg = new ResponseMsg();
		try {
				bankService.deleteBank(bank.getId());
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
