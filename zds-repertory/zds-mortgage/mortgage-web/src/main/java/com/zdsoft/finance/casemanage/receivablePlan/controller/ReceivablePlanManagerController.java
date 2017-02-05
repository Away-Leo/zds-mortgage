package com.zdsoft.finance.casemanage.receivablePlan.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.finance.casemanage.receivablePlan.entity.BankAccount;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivableInfo;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivablePlan;
import com.zdsoft.finance.casemanage.receivablePlan.service.BankAccountService;
import com.zdsoft.finance.casemanage.receivablePlan.service.ReceivablePlanService;
import com.zdsoft.finance.casemanage.receivablePlan.vo.BankAccountVo;
import com.zdsoft.finance.casemanage.receivablePlan.vo.ReceivableInfoVo;
import com.zdsoft.finance.casemanage.receivablePlan.vo.ReceivablePlanVo;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.customer.entity.BeforePersonalCustomer;
import com.zdsoft.finance.customer.service.BeforePersonalCustomerService;
import com.zdsoft.finance.customer.vo.BeforePersonalCustomerVo;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.marketing.vo.CaseApplyVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:ReceivablePlanManagerController.java
 * @Package:com.zdsoft.finance.casemanage.receivablePlan.controller
 * @Description:还款计划管理功能contraller
 * @author: wrw
 * @date:2017年1月11日 下午5:02:08
 * @version:v1.0
 */
@Controller
@RequestMapping("/loanReceivablePlan")
public class ReceivablePlanManagerController extends BaseController {

	@Autowired
	private CaseApplyService caseApplyService;

	@Autowired
	private ReceivablePlanService receivablePlanService;

	@Autowired
	private BankAccountService bankAccountService;

	@Autowired
	private BeforePersonalCustomerService beforePersonalCustomerService;

	/**
	 * 还款计划管理项目列表（页面跳转）
	 */
	@RequestMapping("/receivablePlanManager")
	@UriKey(key = "com.zdsoft.finance.caseManager.receivableplanManager")
	@Menu(resource = "com.zdsoft.finance.caseManager.receivableplanManager", label = "还款计划管理", group = "project", order = 1)
	public ModelAndView receivevableplanManager() {
		return new ModelAndView("casemanage/receivablePlanManager/receivable_plan_manager_list", null);
	}

	/**
	 * 初始化放款后（页面跳转）
	 */
	@RequestMapping("/initBeforeLoanPage")
	@UriKey(key = "com.zdsoft.finance.caseManager.initBeforeLoanPage")
	public ModelAndView initBeforeLoan(String caseApplyId, ModelMap model) {
		model.put("caseApplyId", caseApplyId);
		try {
			CaseApply caseApply = caseApplyService.findOne(caseApplyId);
			ReceivableInfo info = caseApply.getReceivableInfo();
			if (ObjectHelper.isNotEmpty(info)) {
				model.put("receivableInfoId", info.getId());
			}
		} catch (BusinessException e) {
			logger.error("查询案件出错 ID为：" + caseApplyId);
			e.printStackTrace();
		}
		model.put("caseApplyId", caseApplyId);
		return new ModelAndView("casemanage/receivablePlanManager/before_loan_main", model);
	}

	/**
	 * 初始化放款后（页面跳转）
	 */
	@RequestMapping("/initFollowUp")
	@UriKey(key = "com.zdsoft.finance.caseManager.initFollowUp")
	public ModelAndView initFollowUp(String caseApplyId) {
		return new ModelAndView("casemanage/receivablePlanManager/follow_up_info", null);
	}

	/**
	 * 初始化查看还款计划页面（页面跳转）
	 */
	@RequestMapping("/receivable")
	@UriKey(key = "com.zdsoft.finance.caseManager.receivable")
	public ModelAndView initReceivable(String caseApplyId, ModelMap model) {
		logger.info("查看还款计划 案件ID为：" + caseApplyId);
		try {
			if (ObjectHelper.isNotEmpty(caseApplyId)) {
				CaseApply caseApply = caseApplyService.findOne(caseApplyId);
				// 查询案件主借人
				List<BeforePersonalCustomer> listPersonal = beforePersonalCustomerService
						.queryCustomerByCaseApplyIdAndJoinType(caseApplyId, CaseApplyBeforeCustomer.MAIN_BORROW);
				if (ObjectHelper.isNotEmpty(listPersonal) && listPersonal.size() > 0) {
					BeforePersonalCustomerVo personalVo = new BeforePersonalCustomerVo(listPersonal.get(0));
					model.put("beforePersonal", personalVo);
				}

				model.put("caseApply", new CaseApplyVo(caseApply));
				if (ObjectHelper.isNotEmpty(caseApply.getReceivableInfo())) {
					model.put("receivableInfo", new ReceivableInfoVo(caseApply.getReceivableInfo()));
					// 还款计划
					List<ReceivablePlan> planList = caseApply.getReceivableInfo().getReceivablePlan();
					List<ReceivablePlanVo> planListVo = new ArrayList<>();
					if (ObjectHelper.isNotEmpty(planList)) {
						// 转换为VO对象
						for (ReceivablePlan receivablePlan : planList) {
							planListVo.add(new ReceivablePlanVo(receivablePlan));
						}
						model.put("planList", planListVo);
					}
				}

			}
		} catch (BusinessException e) {
			logger.error("查询案件信息错误 案件ID：" + caseApplyId);
			e.printStackTrace();
		}
		return new ModelAndView("casemanage/receivablePlanManager/detail_receivable_info", model);
	}

	/**
	 * 通用还款计划管理项目列表（查询数据）
	 */
	@RequestMapping("/receivevableplanManagerList")
	@UriKey(key = "com.zdsoft.finance.casemanage.receivablePlanManager.receivablePlanCaseList")
	@ResponseBody
	public String receivevableplanManagerList(HttpServletRequest request, PageRequest pageable, String jsoncallback) {
		@SuppressWarnings("unchecked")
		List<QueryObj> list = (List<QueryObj>) request.getAttribute("listObj");
		ResponseMsg responseMsg = new ResponseMsg();
		try {
			Page<CaseApply> page = caseApplyService.findByHqlConditions(pageable, list);
			if (ObjectHelper.isNotEmpty(page)) {
				List<CaseApplyVo> listvo = new ArrayList<CaseApplyVo>();
				for (CaseApply caseApply : page.getRecords()) {
					caseApply.setFeeInfoList(null);
					caseApply.setRiskInfo(null);
					// 查询案件主借人
					List<BeforePersonalCustomer> listPersonal = beforePersonalCustomerService
							.queryCustomerByCaseApplyIdAndJoinType(caseApply.getId(), CaseApplyBeforeCustomer.MAIN_BORROW);
					CaseApplyVo vo = new CaseApplyVo(caseApply);
					if(ObjectHelper.isNotEmpty(listPersonal)&&listPersonal.size()>0){
						vo.setCustomerName(listPersonal.get(0).getCustomerName());
					}
					listvo.add(vo);
				}
				responseMsg.setRows(listvo);
				responseMsg.setTotal(page.getTotalRows());
			} else {
				responseMsg.setMsg("查询案件为空");
				responseMsg.setTotal(0L);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("***********查询案件列表出错*************");
		}
		return ObjectHelper.objectToJson(responseMsg, jsoncallback);
	}

	/**
	 * 通用保存还款计划
	 *
	 * @author wrw
	 * @param receivableInfoId
	 *            还款计划信息ID
	 * @param loanApplyId
	 *            放款ID
	 * @param listVo
	 *            还款计划列表
	 * @param jsoncallback
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/saveReceivablePlan")
	@UriKey(key = "com.zdsoft.finance.casemanage.receivablePlanManager.saveReceivablePlan")
	@ResponseBody
	public String saveReceivablePlan(String receivableInfoId, String loanApplyId, String receivablePlanJson,
			String jsoncallback) {
		ResponseMsg responseMsg = new ResponseMsg();
		logger.info("案件ID为：" + receivableInfoId);
		if (ObjectHelper.isEmpty(receivableInfoId) && ObjectHelper.isEmpty(loanApplyId)) {
			responseMsg.setResultStatus(1);
			responseMsg.setMsg("还款计划信息ID 与 放款ID为空");
			return ObjectHelper.objectToJson(responseMsg, jsoncallback);
		}

		// String转换vo
		JSONArray receivablePlanArray = JSONArray.fromObject(receivablePlanJson);
		List<ReceivablePlanVo> receivablePlanListVo = JSONArray.toList(receivablePlanArray, new ReceivablePlanVo(),
				new JsonConfig());
		List<ReceivablePlan> receivablePlanListPo = new ArrayList<ReceivablePlan>();

		try {
			// 还款计划vo转换为po
			for (ReceivablePlanVo receivablePlanVo : receivablePlanListVo) {
				receivablePlanListPo.add(receivablePlanVo.toPO());
			}
			// 保存还款计划
			receivablePlanService.saveReceivablePlan(receivableInfoId, loanApplyId, receivablePlanListPo);
			logger.info("保存还款计划成功");
			responseMsg.setMsg("保存成功");
		} catch (BusinessException e) {
			responseMsg.setMsg("保存还款计划失败");
			logger.error("保存还款计划失败");
			e.printStackTrace();
		}

		return ObjectHelper.objectToJson(responseMsg, jsoncallback);
	}

	/**
	 * （资调-还款计划）保存还款计划信息
	 *
	 * @author wrw
	 * @param receivableInfo
	 *            还款计划信息
	 * @param receivablePlanJson
	 *            还款计划列表
	 * @param bankReAccountVo
	 *            放款银行信息
	 * @param bankLoanAccountVo
	 *            收款银行信息
	 * @param jsoncallback
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/saveReceivableInfo")
	@UriKey(key = "com.zdsoft.finance.casemanage.receivablePlanManager.saveReceivableInfo")
	@ResponseBody
	public String saveReceivableInfo(ReceivableInfoVo receivableInfoVo, String receivablePlanJson, BankAccountVo vo,
			String jsoncallback) {
		ResponseMsg responseMsg = new ResponseMsg();
		logger.info("还款计划信息ID为：" + receivableInfoVo.getId());
		// json转换
		JSONArray receivablePlanArray = JSONArray.fromObject(receivablePlanJson);

		List<ReceivablePlanVo> receivablePlanListVo = JSONArray.toList(receivablePlanArray, new ReceivablePlanVo(),
				new JsonConfig());

		try {
			List<ReceivablePlan> receivablePlanListPo = new ArrayList<ReceivablePlan>();
			// 还款计划vo转换为po
			for (ReceivablePlanVo receivablePlanVo : receivablePlanListVo) {
				receivablePlanVo.setReceivableInfoId(receivableInfoVo.getId());
				// 设置创建时间
				receivablePlanVo.setCreateTime(new Date());
				// 设置是否删除
				receivablePlanVo.setIsDeleted(false);
				receivablePlanVo.setId("");
				receivablePlanListPo.add(receivablePlanVo.toPO());
			}
			// 保存
			bankAccountService.saveReceivableInfo(receivableInfoVo.toPo(receivableInfoVo, new ReceivableInfo()),
					vo.getRecAccountVo().toPo(vo.getRecAccountVo(), new BankAccount()),
					vo.getLoanAccountVo().toPo(vo.getLoanAccountVo(), new BankAccount()), receivablePlanListPo);
			logger.info("保存还款计划信息成功");
			responseMsg.setMsg("保存成功");
			responseMsg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (BusinessException e) {
			responseMsg.setResultStatus(ResponseMsg.SYS_ERROR);
			responseMsg.setMsg("保存还款计划信息失败");
			logger.error("保存还款计划信息失败");
			e.printStackTrace();
		}
		return ObjectHelper.objectToJson(responseMsg, jsoncallback);
	}

}
