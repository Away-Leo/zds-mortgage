package com.zdsoft.finance.casemanage.datasurvey.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.finance.casemanage.receivablePlan.entity.BankAccount;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivableInfo;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivablePlan;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivablePlanForm;
import com.zdsoft.finance.casemanage.receivablePlan.entity.RepayPlan;
import com.zdsoft.finance.casemanage.receivablePlan.service.BankAccountService;
import com.zdsoft.finance.casemanage.receivablePlan.service.ReceivablePlanInfoService;
import com.zdsoft.finance.casemanage.receivablePlan.vo.LoanAccountVo;
import com.zdsoft.finance.casemanage.receivablePlan.vo.ReceivableAccountVo;
import com.zdsoft.finance.casemanage.receivablePlan.vo.ReceivableInfoVo;
import com.zdsoft.finance.casemanage.receivablePlan.vo.ReceivablePlanVo;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.customer.entity.BeforePersonalCustomer;
import com.zdsoft.finance.customer.service.BeforePersonalCustomerService;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.marketing.vo.CaseApplyVo;
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
	private BankAccountService bankAccountService;
	
	@Autowired
	private BeforePersonalCustomerService beforePersonalCustomerService;

	/**
	 * 查询案件信息
	 * 
	 * @param caseApplyId
	 *            案件ID
	 * @return
	 */
	@RequestMapping(value = "/caseApplyDetail")
	@UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.caseApplyDetail")
	public ModelAndView caseApplyDetail(String caseApplyId) {
		ModelMap model = new ModelMap();
		CaseApplyVo vo = new CaseApplyVo();
		ReceivableInfoVo infoVo = new ReceivableInfoVo();
		ReceivableAccountVo receivableAccountVo = new ReceivableAccountVo();
		LoanAccountVo loanAccountVo = new LoanAccountVo();
		List<BankAccount> bankAccountList = new ArrayList<>();
		try {
			// 获取案件信息
			CaseApply caseApply = caseApplyService.findOne(caseApplyId);
			ReceivableInfo po = caseApply.getReceivableInfo();
			if (ObjectHelper.isNotEmpty(po)) {
				infoVo = new ReceivableInfoVo(po);
			}
			vo = new CaseApplyVo(caseApply);
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
						receivableAccountVo = new ReceivableAccountVo(bankAccount);
					} else {
						loanAccountVo = new LoanAccountVo(bankAccount);
					}
				}
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		model.put("caseApply", vo);
		model.put("receivableInfo", infoVo);
		model.put("receivableAccountVo", receivableAccountVo);
		model.put("loanAccountVo", loanAccountVo);
		return new ModelAndView("casemanage/datasurvey/capital_receivable_plan_edit", model);
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
		List<RepayPlan> list = new ArrayList<RepayPlan>();
		List<ReceivablePlanVo> listVo = new ArrayList<ReceivablePlanVo>();
		ResponseMsg responseMsg = new ResponseMsg();
		try {
			if (ObjectHelper.isNotEmpty(receivableInfo.getCaseApplyId())) {
				// 获取案件信息
				CaseApply caseApply = caseApplyService.findOne(receivableInfo.getCaseApplyId());
				if (ObjectHelper.isEmpty(reqType)&&ObjectHelper.isNotEmpty(caseApply.getReceivableInfo())) {
					// 直接查询项目还款计划
					List<ReceivablePlan> listPo = caseApply.getReceivableInfo().getReceivablePlan();
					for (ReceivablePlan receivablePlan : listPo) {
						listVo.add(new ReceivablePlanVo(receivablePlan));
					}
				} else {
					// 保存还款计划信息
					receivableInfo = bankAccountService.saveReceivableInfo(receivableInfo, null, null, null);
					// 重新计算还款计划
					if (ObjectHelper.isNotEmpty(caseApply) && ObjectHelper.isNotEmpty(receivableInfo)) {
						ReceivablePlanForm planForm = new ReceivablePlanForm();
						// 模拟数据
						planForm.setApplyLoanDt(receivableInfo.getExpectLoanDate().intValue());// 放款�?
						planForm.setApplyRepayDt(receivableInfo.getExpectStartDate().intValue());
						planForm.setLoanPeriodMonth(caseApply.getApplyDeadline() + "");// 贷款期限-�?
						planForm.setPiecewisePeriod("3");// 分段还款还款期数
						// planForm.setLoanPeriodDay("35");//贷款期限-�?
						planForm.setSelectFixRepaymentDt("2");// 每期还款方式 1、还款日
																// 2、指定日 3、月末?
						planForm.setRepaymentDt(receivableInfo.getRepaymentDate() + "");// 指定还款日
						planForm.setaMT_Page(caseApply.getApplyAmount());// 贷款金额
						/*
						 * 还款方式 1、到期还本? 4、利随本清 5、等额本金 6、等额本息 7、等本等息?
						 * 9.等额本息（银行）10.分段还款 11 季度部分本息
						 */
						planForm.setRepayMethod(receivableInfo.getRepaymentType());
						planForm.setE_RateUnit("09310001");// 利率单位
						planForm.setInterestRate(receivableInfo.getLoanMonthRate() + "");// 利率
						planForm.setPiecewiseRate("15");// 分段还款利率
						planForm.setQuarterlyPrincipalRatio("12");// 季度本金比例
						planForm.setH_IsInterest(false);// 是否提前收息
						// 计算还款计划
						list = planService.receivablePlanGuarate(planForm);
						int i = 0;
						for (RepayPlan plan : list) {
							if (i % 2 == 1) {
								ReceivablePlanVo vo = new ReceivablePlanVo();
								vo.setReceivableInfoId(caseApply.getReceivableInfo().getId());
								// 期数
								vo.setPeriodsNo(Integer.parseInt(plan.getPeriodsNo()) + 1);
								// 利息
								vo.setRepaymentAmount(list.get(i - 1).getPlanAmount());
								// 本金
								vo.setInterestAmount(plan.getPlanAmount());
								// 应还日期
								vo.setRepaymentDate(plan.getPlanDueDt());
								vo.setSurplusRepaymentAmount(list.get(i - 1).getResidualprincipal());
								listVo.add(vo);
							}
							i++;
						}
					} else {
						responseMsg.setResultStatus(ResponseMsg.APP_ERROR);
						responseMsg.setMsg("案件信息异常");
					}
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
	 * 查询还款计划（caseApplyId 不为空查询案件预生成还款计划，反之则查询放款还款计划）
	 * 
	 * @param projectId
	 * @param loanApplyId
	 * @return
	 */
	@RequestMapping(value = "/receivablePlanQuery")
	@UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.receivablePlanQuery")
	public ModelAndView receivablePlanQuery(String caseApplyId) {
		ModelMap model = new ModelMap();
		List<RepayPlan> list = new ArrayList<RepayPlan>();
		try {
			if (ObjectHelper.isNotEmpty(caseApplyId)) {
				// 获取案件信息
				CaseApply caseApply = caseApplyService.findOne("xiong");
				if (ObjectHelper.isNotEmpty(caseApply)) {
					ReceivablePlanForm planForm = new ReceivablePlanForm();
					list = planService.receivablePlanGuarate(planForm);
				}
			} else {
				// 查询放款还款计划
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.put("caseApply", list);
		return new ModelAndView("casemanage/datasurvey/receivableplan_edit", model);
	}

}
