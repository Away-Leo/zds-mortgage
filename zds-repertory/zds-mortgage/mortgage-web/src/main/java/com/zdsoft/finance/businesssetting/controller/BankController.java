package com.zdsoft.finance.businesssetting.controller;

import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.businesssetting.entity.Bank;
import com.zdsoft.finance.businesssetting.service.BankService;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @title BankController.java
 * @className BankController
 * @description 银行代码设置controller
 * @author LiaoGuoWei
 * @create 2017/2/27 14:25
 * @version V1.0
 **/
@RequestMapping("/paramter")
@Controller
public class BankController extends BaseController {
	@Autowired
	private BankService bankService;

	/**
	 * @Title: initBank
	 * @Description: 银行代码配置初始化
	 * @author liaoguowei
	 * @param
	 * @return org.springframework.web.servlet.ModelAndView
	 * @throws
	 **/
	@RequestMapping("/initBank")
	@UriKey(key = "com.zdsoft.finance.parameter.initBank")
	@Menu(resource = "com.zdsoft.finance.parameter.initBank", label = "银行代码配置", group = "businessSetting", order = 1)
	public ModelAndView initBank() {
		return new ModelAndView("businesssetting/bank_list");
	}

	/**
	 * @Title: getBank
	 * @Description: 银行代码列表
	 * @author liaoguowei
	 * @param request 请求
	 * @param jsoncallback json回调
	 * @param pageable 分页参数
	 * @return java.lang.String @throws
	 */
	@RequestMapping(value = "/bank", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@UriKey(key = "com.zdsoft.finance.parameter.getBank")
	@ResponseBody
	public String getBank(HttpServletRequest request, String jsoncallback, PageRequest pageable) {
		ResponseMsg msg = new ResponseMsg();
		try {
			// 获取页面封装的查询参数
			List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
			// 分页抵押权人
			Page<Bank> BankPage = bankService.findByHqlConditions(pageable, queryObjs);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setTotal(BankPage.getTotalRows());
			msg.setRows(BankPage.getRecords());
		} catch (Exception e) {
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}

		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

	/**
	 * @Title: saveOrUpdateBank
	 * @Description: 更新或保存
	 * @author liaoguowei
	 * @param bank 银行
	 * @param jsoncallback 回调函数
	 * @param pageable 分页参数
	 * @return java.lang.String @throws
	 */
	@RequestMapping("/updateBank")
	@UriKey(key = "com.zdsoft.finance.parameter.updateBank")
	@ResponseBody
	public String saveOrUpdateBank(Bank bank, String jsoncallback, PageRequest pageable) {
		ResponseMsg msg = new ResponseMsg();
		try {
			Bank sourceDada = bankService.findByCodeAndName(bank);
			//如果按照新编码查出数据 则判断查出的数据是否为当前更新的数据
			if(ObjectHelper.isNotEmpty(sourceDada)){
				if(sourceDada.getId().equalsIgnoreCase(bank.getId())){
					bankService.saveOrUpdateBank(bank);
					msg.setMsg("操作成功！");
					msg.setResultStatus(ResponseMsg.SUCCESS);
				}else{
					msg.setMsg("操作失败！已经存在相同数据.");
					msg.setResultStatus(ResponseMsg.APP_ERROR);
				}
			}else{
				bankService.saveOrUpdateBank(bank);
				msg.setMsg("操作成功！");
				msg.setResultStatus(ResponseMsg.SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("更新银行代码出错",e);
			msg.setMsg("操作失败！");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

	/**
	 * @Title: deleteBank
	 * @Description: 删除银行代码
	 * @author liaoguowei
	 * @param bank 银行
	 * @param jsoncallback json回调
	 * @param pageable 分页参数
	 * @return java.lang.String @throws
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
			msg.setMsg("操作失败！");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			e.printStackTrace();
			logger.error("逻辑删除银行代码失败", e);
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

}
