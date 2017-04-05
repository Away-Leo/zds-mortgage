package com.zdsoft.finance.capital.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.capital.entity.CreditEntrust;
import com.zdsoft.finance.capital.entity.CreditEntrustPrincipal;
import com.zdsoft.finance.capital.service.CreditEntrustPrincipalService;
import com.zdsoft.finance.capital.service.CreditEntrustService;
import com.zdsoft.finance.capital.vo.CreditEntrustVo;
import com.zdsoft.finance.casemanage.handleMortgage.entity.Detain;
import com.zdsoft.finance.casemanage.handleMortgage.service.DetainService;
import com.zdsoft.finance.casemanage.limitApply.entity.CaseLimitApply;
import com.zdsoft.finance.casemanage.limitApply.service.CaseLimitApplyService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.HouseProperty;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.marketing.service.HousePropertyService;
import com.zdsoft.finance.marketing.vo.CaseApplyVo;
import com.zdsoft.finance.product.entity.CreditEntrustPlanConfig;
import com.zdsoft.finance.product.service.CreditEntrustPlanConfigService;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;
import com.zdsoft.framework.cra.annotation.Reference;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: PlanDistributionController.java
 * @ClassName: PlanDistributionController
 * @Description: 案件资金计划管理controller
 * @author liuwei
 * @date 2017年2月8日 上午10:17:09
 * @version V1.0
 */
@Controller
@RequestMapping("/planDistribution")
public class PlanDistributionController extends BaseController {

	@Autowired
	CaseApplyService caseApplyService;

	@Autowired
	CreditEntrustService creditEntrustService;

	@Autowired
	HousePropertyService housePropertyService;

	@Autowired
	DetainService detainService;

	@Autowired
	CaseLimitApplyService caseLimitApplyService;

	@Autowired
	CreditEntrustPrincipalService creditEntrustPrincipalService;

	@Autowired
	CreditEntrustPlanConfigService creditEntrustPlanConfigService;

	@Autowired
	CED CED;

	/**
	 * 
	 * @Title: planDistributionList
	 * @Description: 案件资金计划管理
	 * @author liuwei
	 * @return ModelAndView
	 */
	@RequestMapping("/planDistributionList")
	@UriKey(key = "com.zdsoft.finance.capital.planDistributionList")
	@Menu(resource = "com.zdsoft.finance.capital.planDistributionList", label = "案件资金计划管理", group = "capital", order = 2)
	public ModelAndView planDistributionList() {
		return new ModelAndView("/capital/plan_distribution_list");
	}

	/**
	 * 
	 * @Title: getDistributions
	 * @Description: 分页查询额度分配
	 * @author liuwei
	 * @param request
	 * @param jsoncallback
	 * @param pageable
	 * @return 额度分配分页json
	 */
	@RequestMapping("/getDistributions")
	@UriKey(key = "com.zdsoft.finance.capital.getDistributions")
	@ResponseBody
	public String getDistributions(HttpServletRequest request, String jsoncallback, PageRequest pageable) {

		// 组装查询条件
		String caseApplyCode = request.getParameter("caseApplyCode");
		String customerName = request.getParameter("customerName");
		String createOrgCd = request.getParameter("createOrgCd");
		String creditEntrustName = request.getParameter("creditEntrustName");
		String caseApplyStatus = request.getParameter("caseApplyStatus");

		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("caseApplyCode", caseApplyCode);
		conditions.put("customerName", customerName);
		conditions.put("createOrgCd", createOrgCd);
		conditions.put("creditEntrustName", creditEntrustName);
		conditions.put("caseApplyStatus", caseApplyStatus);

		// sql查询
		Page<Map<String, Object>> creditValue = creditEntrustService.reportPlanDistributionSql(pageable, conditions);

		ResponseMsg msg = new ResponseMsg();
		msg.setMsg("列表查询成功");
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setRows(creditValue.getRecords());
		msg.setTotal(creditValue.getTotalRows());

		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

	/**
	 * 
	 * @Title: quotaApplication
	 * @Description: 信托计划额度申请：申请额度事件
	 * @author liuwei
	 * @param id
	 *            信托计划id
	 * @return ModelAndView
	 */
	@RequestMapping("/quotaApplication")
	@UriKey(key = "com.zdsoft.finance.capital.quotaApplication")
	@Reference(resource = "com.zdsoft.finance.capital.quotaApplication", label = "申请额度")
	public ModelAndView quotaApplication(String id) {
		ModelMap modelMap = assembleViewField(id);
		return new ModelAndView("/capital/plan_distribution_quota_apply", modelMap);
	}

	/**
	 * 
	 * @Title: cancellationQuota
	 * @Description: 信托计划额度申请：取消额度事件
	 * @author liuwei
	 * @param id
	 *            信托计划id
	 * @return ModelAndView
	 */
	@RequestMapping("/cancellationQuota")
	@UriKey(key = "com.zdsoft.finance.capital.cancellationQuota")
	@Reference(resource = "com.zdsoft.finance.capital.cancellationQuota", label = "额度取消")
	public ModelAndView cancellationQuota(String id) {
		ModelMap modelMap = assembleViewField(id);
		return new ModelAndView("/capital/plan_distribution_quota_cancel", modelMap);
	}

	/**
	 * 
	 * @Title: allocationAllowance
	 * @Description: 信托计划额度申请：申请备付金事件
	 * @author liuwei
	 * @param id
	 *            信托计划id
	 * @return ModelAndView
	 */
	@RequestMapping("/allocationAllowance")
	@UriKey(key = "com.zdsoft.finance.capital.allocationAllowance")
	@Reference(resource = "com.zdsoft.finance.capital.allocationAllowance", label = "申请备付金")
	public ModelAndView allocationAllowance(String id) {
		ModelMap modelMap = assembleViewField(id);
		return new ModelAndView("/capital/plan_distribution_allocation_apply", modelMap);
	}

	/**
	 * 
	 * @Title: cancellationAllowance
	 * @Description: 信托计划额度申请：取消备付金事件
	 * @author liuwei
	 * @param id
	 *            信托计划id
	 * @return ModelAndView
	 */
	@RequestMapping("/cancellationAllowance")
	@UriKey(key = "com.zdsoft.finance.capital.cancellationAllowance")
	@Reference(resource = "com.zdsoft.finance.capital.cancellationAllowance", label = "取消备付金")
	public ModelAndView cancellationAllowance(String id) {
		ModelMap modelMap = assembleViewField(id);
		return new ModelAndView("/capital/plan_distribution_allocation_cancel", modelMap);
	}

	/**
	 * 
	 * @Title: transferPlan
	 * @Description: 信托计划额度申请：转资金计划事件
	 * @author liuwei
	 * @param id
	 *            信托计划id
	 * @return ModelAndView
	 */
	@RequestMapping("/transferPlan")
	@UriKey(key = "com.zdsoft.finance.capital.transferPlan")
	@Reference(resource = "com.zdsoft.finance.capital.transferPlan", label = "转资金计划")
	public ModelAndView transferPlan(String id) {
		ModelMap modelMap = assembleViewField(id);
		return new ModelAndView("/capital/plan_distribution_transfer_plan", modelMap);
	}

	/**
	 * 
	 * @Title: quotaApply
	 * @Description: 申请额度
	 * @author liuwei
	 * @param caseApplyId
	 *            案件申请id
	 * @param creditEntrustId
	 *            信托计划id
	 * @return ResponseMsg处理消息
	 */
	@RequestMapping("/quotaApply")
	@UriKey(key = "com.zdsoft.finance.capital.quotaApply")
	@ResponseBody
	public ResponseMsg quotaApply(String caseApplyId, String creditEntrustId) {
		ResponseMsg msg = new ResponseMsg();
		try {
			creditEntrustService.quotaApply(caseApplyId, creditEntrustId);
			msg.setMsg("申请额度成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("申请额度失败", e);
			msg.setMsg("申请额度失败");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}

		return msg;
	}

	/**
	 * 
	 * @Title: quotaCancel
	 * @Description: 取消额度
	 * @author liuwei
	 * @param caseApplyId
	 *            案件id
	 * @param creditEntrustId
	 *            信托计划id
	 * @return ResponseMsg处理消息
	 */
	@RequestMapping("/quotaCancel")
	@UriKey(key = "com.zdsoft.finance.capital.quotaCancel")
	@ResponseBody
	public ResponseMsg quotaCancel(String caseApplyId, String creditEntrustId, String caseLimitApplyId) {
		ResponseMsg msg = new ResponseMsg();
		try {
			creditEntrustService.quotaCancel(caseApplyId, creditEntrustId, caseLimitApplyId);
			msg.setMsg("取消额度成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("取消额度失败", e);
			msg.setMsg("取消额度失败");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}

		return msg;
	}

	/**
	 * 
	 * @Title: reserveFund
	 * @Description: 分配备付金
	 * @author liuwei
	 * @param caseApplyId
	 *            案件申请id
	 * @param creditEntrustId
	 *            信托计划id
	 * @return ResponseMsg处理消息
	 */
	@RequestMapping("/reserveFund")
	@UriKey(key = "com.zdsoft.finance.capital.reserveFund")
	@ResponseBody
	public ResponseMsg reserveFund(String caseApplyId, String creditEntrustId, String caseLimitApplyId) {
		ResponseMsg msg = new ResponseMsg();

		try {
			creditEntrustService.reserveFund(caseApplyId, creditEntrustId, caseLimitApplyId);
			msg.setMsg("分配备付金成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("分配备付金失败", e);
			msg.setMsg("分配备付金失败");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}

		return msg;
	}

	/**
	 * 
	 * @Title: reserveFundCancel
	 * @Description: 取消备付金分配
	 * @author liuwei
	 * @param caseApplyId
	 *            案件申请id
	 * @param creditEntrustId
	 *            信托计划id
	 * @return ResponseMsg处理消息
	 */
	@RequestMapping("/reserveFundCancel")
	@UriKey(key = "com.zdsoft.finance.capital.reserveFundCancel")
	@ResponseBody
	public ResponseMsg reserveFundCancel(String caseApplyId, String creditEntrustId, String caseLimitApplyId) {
		ResponseMsg msg = new ResponseMsg();

		try {
			creditEntrustService.reserveFundCancel(caseApplyId, creditEntrustId, caseLimitApplyId);
			msg.setMsg("取消备付金分配成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("取消备付金分配失败", e);
			msg.setMsg("取消备付金分配失败");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}

		return msg;
	}

	/**
	 * 
	 * @Title: confirmInfo
	 * @Description: 确认转入
	 * @author liuwei
	 * @param caseApplyId
	 *            案件申请id
	 * @param creditEntrustId
	 *            信托计划id
	 * @param creditEntrustNewId
	 *            新的信托计划id
	 * @return ResponseMsg处理消息
	 */
	@RequestMapping("/confirmInfo")
	@UriKey(key = "com.zdsoft.finance.capital.confirmInfo")
	@ResponseBody
	public ResponseMsg confirmInfo(String caseApplyId, String creditEntrustId, String creditEntrustNewId,
			String caseLimitApplyId) {
		ResponseMsg msg = new ResponseMsg();

		try {
			// CaseApply caseApply = caseApplyService.findOne(caseApplyId);
			CaseLimitApply caseLimitApply = caseLimitApplyService.findOne(caseLimitApplyId);
			CreditEntrust creditEntrust = creditEntrustService.findOne(creditEntrustId);
			CreditEntrust creditEntrustNew = creditEntrustService.findOne(creditEntrustNewId);
			// 修改信托计划信息，并恢复未分配金额
			creditEntrustService.confirmInfo(creditEntrust, creditEntrustNew, caseLimitApply);
			msg.setMsg("计划转让成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error("计划转让失败", e);
			msg.setMsg(e.getExceptionMessage());
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}

		return msg;
	}

	/**
	 * 
	 * @Title: assembleViewField
	 * @Description: 组装额度申请需要信息(案件信息,额度信息,信托计划信息,房产信息)
	 * @author liuwei
	 * @param id
	 *            案件
	 * @return ModelMap
	 */
	private ModelMap assembleViewField(String id) {
		ModelMap modelMap = new ModelMap();

		// 查询案件信息
		CaseApply caseApply = null;
		try {
			caseApply = caseApplyService.findOne(id);
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error("根据id:" + id + ",查询案件信息失败", e);
		}
		CaseApplyVo caseApplyVo = new CaseApplyVo(caseApply);
		modelMap.put("caseApplyVo", caseApplyVo);

		// 额度信息
		List<CaseLimitApply> caseLimitAppies = caseLimitApplyService.findByCaseApplyId(caseApply.getId());
		BigDecimal totalQuato = BigDecimal.ZERO;
		String creditEntrustId = null;
		String cancelEmpName = null;
		Long cancelDate = null;
		String applyEmpName = null;
		Long applyDate = null;
		for (int i = 0; i < caseLimitAppies.size(); i++) {
			CaseLimitApply caseLimitApply = caseLimitAppies.get(i);
			// 判断是否已经取消，如果已经取消，则额度申请有效
			if (ObjectHelper.isEmpty(caseLimitApply.getCancelEmpCode())) {
				totalQuato = totalQuato.add(caseLimitApply.getApplyLimitAmount());
				creditEntrustId = caseLimitApply.getFundPlanId();

				// 申请信息
				applyEmpName = caseLimitApply.getApplyEmpName();
				applyDate = caseLimitApply.getApplyDate();

				// 判断状态
				if ("YWDM0051058".equals(caseLimitApply.getEffectiveStatus())) {
					modelMap.put("isDistribution", "是");
				} else {
					modelMap.put("isDistribution", "否");
				}
				if ("YWDM0051056".equals(caseLimitApply.getEffectiveStatus())) {
					modelMap.put("effectiveStatus", "未申请额度");
				} else if ("YWDM0051057".equals(caseLimitApply.getEffectiveStatus())) {
					modelMap.put("effectiveStatus", "已申请未配资金");
				} else if ("YWDM0051058".equals(caseLimitApply.getEffectiveStatus())) {
					modelMap.put("effectiveStatus", "已申请已配资金");
				}
				modelMap.put("caseLimitApplyId", caseLimitApply.getId());
			} else {

				// 取消信息
				cancelEmpName = caseLimitApply.getCancelEmpName();
				cancelDate = caseLimitApply.getCancelDate();
			}

		}
		if (totalQuato.compareTo(BigDecimal.ZERO) == 0) {
			modelMap.put("totalQuato", caseApply.getApplyAmount());
		} else {
			modelMap.put("totalQuato", totalQuato);
		}

		modelMap.put("cancelEmpName", cancelEmpName);
		modelMap.put("cancelDate", cancelDate);
		modelMap.put("applyEmpName", applyEmpName);
		modelMap.put("applyDate", applyDate);

		if (ObjectHelper.isEmpty(creditEntrustId)) {
			try {
				CreditEntrustPlanConfig creditEntrustPlanConfig = creditEntrustPlanConfigService
						.findByProductIdAndCapitalistId(caseApply.getProductSubtypeId(), caseApply.getCapitalSource());

				if (ObjectHelper.isEmpty(creditEntrustPlanConfig)) {
					throw new BusinessException("10010002", "未能找到产品中资金计划配置");
				}

				creditEntrustId = creditEntrustPlanConfig.getCapitalPlanId();
				modelMap.put("isDistribution", "否");
			} catch (BusinessException e) {
				e.printStackTrace();
				logger.error("查询资金配置失败", e);
			}
		}

		if (ObjectHelper.isNotEmpty(creditEntrustId)) {
			// 信托计划信息
			try {
				CreditEntrust creditEntrust = creditEntrustService.findOne(creditEntrustId);
				CreditEntrustVo creditEntrustVo = new CreditEntrustVo(creditEntrust);
				modelMap.put("creditEntrustVo", creditEntrustVo);

				// 组装查询条件
				Map<String, Object> conditions = new HashMap<String, Object>();
				conditions.put("creditEntrustId", creditEntrust.getId());
				// 查询信托计划本金投入信息
				List<CreditEntrustPrincipal> creditEntrustPrincipals = creditEntrustPrincipalService
						.findByConditions(conditions);
				if (ObjectHelper.isNotEmpty(creditEntrustPrincipals) && creditEntrustPrincipals.size() != 0) {
					CreditEntrustPrincipal creditEntrustPrincipal = creditEntrustPrincipals.get(0);
					modelMap.put("expectDate",
							creditEntrustPrincipal.getExpectDate() == null ? ""
									: DateHelper.dateToString(
											DateHelper.stringToDate(creditEntrustPrincipal.getExpectDate().toString(),
													DateHelper.DATE_SHORT_SIMPLE_FORMAT),
											DateHelper.DATE_SHORT_FORMAT));
					modelMap.put("actualDate",
							creditEntrustPrincipal.getActualDate() == null ? ""
									: DateHelper.dateToString(
											DateHelper.stringToDate(
													creditEntrustPrincipal.getActualDate().toString().substring(0, 8),
													DateHelper.DATE_SHORT_SIMPLE_FORMAT),
											DateHelper.DATE_SHORT_FORMAT));
				}
			} catch (BusinessException e) {
				e.printStackTrace();
				logger.error("查询信托计划失败", e);
			}
		}
		modelMap.put("creditEntrustId", creditEntrustId);

		// 查询房产信息
		List<HouseProperty> houseProperties = housePropertyService.findByCaseApplyId(caseApply.getId());
		if (ObjectHelper.isNotEmpty(houseProperties) && houseProperties.size() != 0) {
			HouseProperty houseProperty = houseProperties.get(0);
			try {
				// 查询入押信息
				Detain detain = detainService.findByHousePropertyId(houseProperty.getId());
				if (ObjectHelper.isNotEmpty(detain)) {
					if (ObjectHelper.isNotEmpty(detain.getPledgeType())) {
						modelMap.put("pledgeType", CED.loadSimpleCodeByFullCode(detain.getPledgeType()));
					} else {
						modelMap.put("pledgeType", null);
					}

				}
			} catch (BusinessException e) {
				e.printStackTrace();
				logger.error("根据房产id查询入押信息失败", e);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("加载simpleCode失败", e);
			}
		}
		return modelMap;
	}
}
