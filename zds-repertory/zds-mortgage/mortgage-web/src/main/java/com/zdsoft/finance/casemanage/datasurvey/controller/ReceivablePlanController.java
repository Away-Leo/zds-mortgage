package com.zdsoft.finance.casemanage.datasurvey.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.finance.casemanage.receivablePlan.entity.BankAccount;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivableInfo;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivablePlanTemp;
import com.zdsoft.finance.casemanage.receivablePlan.service.ReceivablePlanInfoService;
import com.zdsoft.finance.casemanage.receivablePlan.service.ReceivablePlanTempService;
import com.zdsoft.finance.casemanage.receivablePlan.vo.LoanAccountVo;
import com.zdsoft.finance.casemanage.receivablePlan.vo.ReceivableAccountVo;
import com.zdsoft.finance.casemanage.receivablePlan.vo.ReceivableInfoVo;
import com.zdsoft.finance.casemanage.receivablePlan.vo.ReceivablePlanTempVo;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.customer.entity.BeforePersonalCustomer;
import com.zdsoft.finance.customer.service.BeforePersonalCustomerService;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.marketing.vo.CaseApplyVo;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:ReceivablePlanController.java
 * @Package:com.zdsoft.finance.casemanage.datasurvey.controller
 * @Description:案件还款计划
 * @author: wrw
 * @date:2017年1月13日 上午9:37:45
 * @version:v1.0
 */
@Controller
@RequestMapping("/casemanage/datasurvey/receivableplan")
public class ReceivablePlanController extends BaseController {

	@Autowired
	public CaseApplyService caseApplyService;

	@Autowired
	private ReceivablePlanInfoService planService;
	
	@Autowired
	private BeforePersonalCustomerService beforePersonalCustomerService;
	
	@Autowired
	private ReceivablePlanTempService receivablePlanTempService;
	
	@Log
	private Logger log;
	

	/** 
	 * @Title: intoReceivablePlanDetail 
	 * @Description: 进入还款计划基本信息页签页面
	 * @author zjx 
	 * @param caseApplyId案件ID
	 * @return  
	 */ 
	@RequestMapping(value = "/intoReceivablePlanDetail")
	@UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.intoReceivablePlanDetail")
	public ModelAndView intoReceivablePlanDetail(String caseApplyId) {
		ModelMap model = new ModelMap();
		try {
			model=initReceivablePlanInfo(caseApplyId);
		} catch (BusinessException e) {
			e.printStackTrace();
			log.error("初始化还款计划基本信息失败");
		}
		
		return new ModelAndView("casemanage/datasurvey/capital_receivable_plan_edit", model);
	}

	/** 
	 * @Title: viewReceivablePlanDetail 
	 * @Description: 进入还款计划基本信息查看页签页面
	 * @author zjx 
	 * @param caseApplyId案件
	 * @return  
	 */ 
	@RequestMapping(value = "/viewReceivablePlanDetail")
	@UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.viewReceivablePlanDetail")
	public ModelAndView viewReceivablePlanDetail(String caseApplyId) {
		ModelMap model = new ModelMap();
		try {
			model=initReceivablePlanInfo(caseApplyId);
		} catch (BusinessException e) {
			e.printStackTrace();
			log.error("初始化还款计划基本信息失败");
		}
		
		return new ModelAndView("casemanage/datasurvey/capital_receivable_plan_view", model);
	}
	/** 
	 * @Title: initReceivablePlanInfo 
	 * @Description: 根据案件ID查询出还款计划基本信息（编辑查看通用）
	 * @author zjx 
	 * @param caseApplyId案件ID
	 * @return  
	 * @throws BusinessException 
	 */ 
	private ModelMap initReceivablePlanInfo(String caseApplyId) throws BusinessException {
		ModelMap model = new ModelMap();
		CaseApplyVo vo = new CaseApplyVo();
		ReceivableInfoVo infoVo = new ReceivableInfoVo();
		ReceivableAccountVo receivableAccountVo = new ReceivableAccountVo();
		LoanAccountVo loanAccountVo = new LoanAccountVo();
		List<BankAccount> bankAccountList = new ArrayList<>();
		// 获取案件信息
		CaseApply caseApply = caseApplyService.findOne(caseApplyId);
		ReceivableInfo po = caseApply.getReceivableInfo();
		vo = new CaseApplyVo(caseApply);
		if (ObjectHelper.isEmpty(po)) {
			infoVo.setLoanMonthRate(vo.getApplyRate());
			infoVo.setLoanMonthRateUnit(vo.getApplyRateUnit());
		}else{
			infoVo = new ReceivableInfoVo(po);
		}
		// 查询案件主借人
		List<BeforePersonalCustomer> listPersonal = beforePersonalCustomerService
				.queryCustomerByCaseApplyIdAndJoinType(caseApplyId, CaseApplyBeforeCustomer.MAIN_BORROW);
		if (ObjectHelper.isNotEmpty(listPersonal) && listPersonal.size() > 0) {
			vo.setCustomerName(listPersonal.get(0).getCustomerName());
		}
		// 银行卡信息
		bankAccountList = caseApply.getCaseBankAccount();
		if (ObjectHelper.isNotEmpty(bankAccountList) && bankAccountList.size() > 0) {
			for (BankAccount bankAccount : bankAccountList) {
				if (bankAccount.getAccountType() == 0) {
					loanAccountVo = new LoanAccountVo(bankAccount);
				} else {
					receivableAccountVo = new ReceivableAccountVo(bankAccount);
				}
			}
		}
		model.put("caseApply", vo);
		model.put("receivableInfo", infoVo);
		model.put("receivableAccountVo", receivableAccountVo);
		model.put("loanAccountVo", loanAccountVo);
		return model;
	}
	
	/** 
	 * @Title: deadlineConversionDay 
	 * @Description: 单位为年月转换为天
	 * @author wangrongwei
	 * @param termUnit 单位
	 * @param value 值
	 * @return
	 * @throws BusinessException  
	 */ 
	public Integer deadlineConversionDay(String termUnit,Long value) throws BusinessException{
		if (ObjectHelper.isEmpty(termUnit) || ObjectHelper.isEmpty(value)) {
			throw new BusinessException("期限单位或值为空");
		}
		if (CaseApply.DATEUNIT_YEAR.equals(termUnit)) {
			return (int) (value * 360);
		}
		if(CaseApply.DATEUNIT_MONTH.equals(termUnit)){
			return (int) (value * 30);
		}
		return value.intValue();
	}

	/**
	 * 预生成或查询成还款计划
	 *
	 * @author wrw
	 * @param caseApplyId
	 * @param jsoncallback
	 * @param reqType
	 *            0：预生成 1：查询
	 * @return
	 */
	@RequestMapping(value = "/receivablePlanGenerate")
	@UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.receivablePlanGenerate")
	@ResponseBody
	public String receivablePlanGenerate(ReceivableInfo receivableInfo, String jsoncallback, String reqType) {
		logger.info("查询或生成还款计划，案件ID为：" + receivableInfo.getCaseApplyId());
		logger.info("请求类型为：null 查询还款计划，0 预生成还款计划" + reqType);
		List<ReceivablePlanTemp> listPo = new ArrayList<ReceivablePlanTemp>();
		List<ReceivablePlanTempVo> listVo = new ArrayList<ReceivablePlanTempVo>();
		ResponseMsg responseMsg = new ResponseMsg();
		try {
			if (ObjectHelper.isNotEmpty(receivableInfo.getCaseApplyId())) {
				// 获取案件信息
				CaseApply caseApply = caseApplyService.findOne(receivableInfo.getCaseApplyId());
				if (ObjectHelper.isEmpty(reqType)&&ObjectHelper.isNotEmpty(caseApply.getReceivableInfo())) {
					// 直接查询项目还款计划
					listPo = receivablePlanTempService.findReceivablePlanTempByReceivableInfoId(caseApply.getReceivableInfo().getId());
				} else {
					listPo = planService.receivablePlanGenerate(receivableInfo);
				}
				for (ReceivablePlanTemp receivablePlan : listPo) {
					listVo.add(new ReceivablePlanTempVo(receivablePlan));
				}
			}
		} catch (Exception e) {
			responseMsg.setResultStatus(ResponseMsg.SYS_ERROR);
			responseMsg.setMsg("查询或预生成还款计划错误");
			e.printStackTrace();
		}
		responseMsg.setResultStatus(ResponseMsg.SUCCESS);
		responseMsg.setRows(listVo);
		return ObjectHelper.objectToJson(responseMsg, jsoncallback);
	}

	
	@RequestMapping(value = "/calculateOtherRate")
	@UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.calculateOtherRate")
	@ResponseBody
	public String calculateOtherRate(ReceivableInfo info, String jsoncallback) {
		logger.info("查询或生成还款计划，案件ID为：" + info.getCaseApplyId());
		logger.info("贷款利率为：" + info.getLoanMonthRate());
		logger.info("贷款利率单位为：" + info.getLoanMonthRateUnit());
		ResponseMsg responseMsg = new ResponseMsg();
		try {
			Map<String, Object> optional = planService.calculateOtherRate(info);
			responseMsg.setOptional(optional);
		} catch (Exception e) {
			responseMsg.setResultStatus(ResponseMsg.SYS_ERROR);
			responseMsg.setMsg("计算综合利率和实际利率出错");
			e.printStackTrace();
			logger.error("计算综合利率和实际利率出错:", e);
		}
		responseMsg.setResultStatus(ResponseMsg.SUCCESS);
		return ObjectHelper.objectToJson(responseMsg, jsoncallback);
	}
	
	/**
	 * 跳转预生成还款计划页面
	 * 
	 * @param projectId
	 * @return
	 */
	@RequestMapping(value = "/receivablePlanGeneratePage")
	@UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.receivablePlanGeneratePage")
	public ModelAndView receivablePlanGeneratePage(String caseApplyId, ModelMap model) {
		model.put("caseApplyId", caseApplyId);
		return new ModelAndView("casemanage/receivablePlanManager/receivable_plan_edit", model);
	}

	/**
	 * 跳转预生成还款计划页面
	 * 
	 * @param projectId
	 * @return
	 */
	@RequestMapping(value = "/receivablePlanGenerateView")
	@UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.receivablePlanGenerateView")
	public ModelAndView receivablePlanGenerateView(String caseApplyId, ModelMap model) {
		model.put("caseApplyId", caseApplyId);
		return new ModelAndView("casemanage/receivablePlanManager/receivable_plan_view", model);
	}
}
