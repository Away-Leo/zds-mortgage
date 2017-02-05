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

import com.zdsoft.finance.capital.entity.CreditEntrust;
import com.zdsoft.finance.capital.service.CreditEntrustService;
import com.zdsoft.finance.capital.vo.CreditEntrustVo;
import com.zdsoft.finance.casemanage.dealpledge.detain.service.DetainService;
import com.zdsoft.finance.casemanage.limitapply.entity.CaseLimitApply;
import com.zdsoft.finance.casemanage.limitapply.service.CaseLimitApplyService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.HouseProperty;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.marketing.service.HousePropertyService;
import com.zdsoft.finance.marketing.vo.CaseApplyVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;
import com.zdsoft.framework.cra.annotation.Reference;

/**
 * 资金分配controller
 * 
 * @createTime:2017年1月10日
 * @author liuwei
 * @version 1.0
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

	/**
	 * 资金计划分配
	 * 
	 * @return 资金计划分配页面
	 */
	@RequestMapping("/planDistributionList")
	@UriKey(key = "com.zdsoft.finance.capital.planDistributionList")
	@Menu(resource = "com.zdsoft.finance.capital.planDistributionList", label = "资金计划分配", group = "capital", order = 2)
	public ModelAndView planDistributionList() {
		return new ModelAndView("/capital/plan_distribution_list");
	}

	/**
	 * 查询列表
	 * 
	 * @param request
	 *            请求
	 * @param jsoncallback
	 * @param pageable
	 * @return
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

	@RequestMapping("/initQuotaAllocation")
	@UriKey(key = "com.zdsoft.finance.capital.initQuotaAllocation")
	@Reference(resource = "com.zdsoft.finance.capital.initQuotaAllocation", label = "案件计划分配")
	public ModelAndView initQuotaAllocation(String id, String creditEntrustId) {

		ModelMap modelMap = new ModelMap();

		CaseApply caseApply;
		try {
			// 案件信息
			caseApply = caseApplyService.findOne(id);
			List<HouseProperty> houseProperties = housePropertyService.findByCaseApplyId(caseApply.getId());
			CaseApplyVo caseApplyVo = new CaseApplyVo(caseApply);

			// 抵押信息
			if (ObjectHelper.isNotEmpty(houseProperties) && (houseProperties.size() != 0)) {
				HouseProperty houseProperty = houseProperties.get(0);
			}

			// 额度信息
			List<CaseLimitApply> caseLimitAppies = caseLimitApplyService.findByCaseApplyId(caseApply.getId());

			// TODO ... 这一段关系太混乱了。查一个信息太复杂。

			// 信托计划信息
			CreditEntrust creditEntrust = creditEntrustService.findOne(creditEntrustId);
			CreditEntrustVo creditEntrustVo = new CreditEntrustVo(creditEntrust);
			modelMap.put("creditEntrustVo", creditEntrustVo);

			modelMap.put("caseApplyVo", caseApplyVo);
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error("初始化案件信息失败", e);
		}

		return new ModelAndView("/capital/plan_distribution_add", modelMap);
	}

	/**
	 * 申请额度
	 * 
	 * @param caseApplyId
	 *            案件id
	 * @param creditEntrustId
	 *            信托计划id
	 * @return msg 处理信息
	 */
	@RequestMapping("/quotaApply")
	@UriKey(key = "com.zdsoft.finance.capital.quotaApply")
	@ResponseBody
	public ResponseMsg quotaApply(String caseApplyId, String creditEntrustId) {
		ResponseMsg msg = new ResponseMsg();
		try {
			CaseApply caseApply = caseApplyService.findOne(caseApplyId);
			CreditEntrust creditEntrust = creditEntrustService.findOne(creditEntrustId);

			// 修改信托计划信息
			creditEntrustService.updateIncomeBalance(creditEntrust, caseApply.getActualApplyAmount());
			msg.setMsg("申请额度成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error("申请额度失败", e);
			msg.setMsg("申请额度失败");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}

		return msg;
	}

	/**
	 * 取消额度
	 * 
	 * @param caseApplyId
	 *            案件id
	 * @param creditEntrustId
	 *            信托计划id
	 * @return msg 处理信息
	 */
	@RequestMapping("/quotaCancel")
	@UriKey(key = "com.zdsoft.finance.capital.quotaCancel")
	@ResponseBody
	public ResponseMsg quotaCancel(String caseApplyId, String creditEntrustId) {
		ResponseMsg msg = new ResponseMsg();
		try {
			CaseApply caseApply = caseApplyService.findOne(caseApplyId);
			CreditEntrust creditEntrust = creditEntrustService.findOne(creditEntrustId);

			// 修改信托计划信息
			creditEntrustService.updateIncomeBalance(creditEntrust,
					new BigDecimal("-" + caseApply.getActualApplyAmount().toString()));
			msg.setMsg("取消额度成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error("取消额度失败", e);
			msg.setMsg("取消额度失败");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}

		return msg;
	}

	/**
	 * 分配备付金
	 * 
	 * @param caseApplyId
	 *            案件id
	 * @param creditEntrustId
	 *            信托计划id
	 * @return msg 处理信息
	 */
	@RequestMapping("/reserveFund")
	@UriKey(key = "com.zdsoft.finance.capital.reserveFund")
	@ResponseBody
	public ResponseMsg reserveFund(String caseApplyId, String creditEntrustId) {
		ResponseMsg msg = new ResponseMsg();

		try {
			CaseApply caseApply = caseApplyService.findOne(caseApplyId);
			CreditEntrust creditEntrust = creditEntrustService.findOne(creditEntrustId);

			// 修改信托计划信息，并减去未分配金额
			creditEntrustService.updatePaymentBalance(creditEntrust, caseApply.getActualApplyAmount());
			msg.setMsg("分配备付金成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error("分配备付金失败", e);
			msg.setMsg("分配备付金失败");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}

		return msg;
	}

	/**
	 * 取消备付金分配
	 * 
	 * @param caseApplyId
	 *            案件id
	 * @param creditEntrustId
	 *            信托计划id
	 * @return msg 处理信息
	 */
	@RequestMapping("/reserveFundCancel")
	@UriKey(key = "com.zdsoft.finance.capital.reserveFundCancel")
	@ResponseBody
	public ResponseMsg reserveFundCancel(String caseApplyId, String creditEntrustId) {
		ResponseMsg msg = new ResponseMsg();

		try {
			CaseApply caseApply = caseApplyService.findOne(caseApplyId);
			CreditEntrust creditEntrust = creditEntrustService.findOne(creditEntrustId);

			// 修改信托计划信息，并恢复未分配金额
			creditEntrustService.reservePaymentBalance(creditEntrust, caseApply.getActualApplyAmount());
			msg.setMsg("分配备付金成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error("分配备付金失败", e);
			msg.setMsg("分配备付金失败");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}

		return msg;
	}

	/**
	 * 确认转入
	 * 
	 * @param caseApplyId
	 *            案件id
	 * @param creditEntrustId
	 *            信托计划id
	 * @param creditEntrustNewId
	 *            转入的信托计划id
	 * @return msg 处理信息
	 */
	@RequestMapping("/confirmInfo")
	@UriKey(key = "com.zdsoft.finance.capital.confirmInfo")
	@ResponseBody
	public ResponseMsg confirmInfo(String caseApplyId, String creditEntrustId, String creditEntrustNewId) {
		ResponseMsg msg = new ResponseMsg();

		try {
			CaseApply caseApply = caseApplyService.findOne(caseApplyId);
			CreditEntrust creditEntrust = creditEntrustService.findOne(creditEntrustId);
			CreditEntrust creditEntrustNew = creditEntrustService.findOne(creditEntrustNewId);
			// 修改信托计划信息，并恢复未分配金额
			creditEntrustService.confirmInfo(creditEntrust, creditEntrustNew, caseApply.getActualApplyAmount());
			msg.setMsg("分配备付金成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error("分配备付金失败", e);
			msg.setMsg("分配备付金失败");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}

		return msg;
	}

}
