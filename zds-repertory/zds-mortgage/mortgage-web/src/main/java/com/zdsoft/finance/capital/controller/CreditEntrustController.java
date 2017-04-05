package com.zdsoft.finance.capital.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.businesssetting.entity.Bank;
import com.zdsoft.finance.businesssetting.service.BankService;
import com.zdsoft.finance.capital.entity.CreditCostTracking;
import com.zdsoft.finance.capital.entity.CreditEntrust;
import com.zdsoft.finance.capital.entity.CreditEntrustAttom;
import com.zdsoft.finance.capital.entity.CreditEntrustDebit;
import com.zdsoft.finance.capital.entity.CreditEntrustFeeItem;
import com.zdsoft.finance.capital.entity.CreditEntrustPrincipal;
import com.zdsoft.finance.capital.entity.CreditEntrustRedemPrinci;
import com.zdsoft.finance.capital.entity.FeeItem;
import com.zdsoft.finance.capital.entity.LoanCapital;
import com.zdsoft.finance.capital.entity.SpareCapital;
import com.zdsoft.finance.capital.service.CreditCostTrackingService;
import com.zdsoft.finance.capital.service.CreditEntrustAttomService;
import com.zdsoft.finance.capital.service.CreditEntrustDebitService;
import com.zdsoft.finance.capital.service.CreditEntrustFeeItemService;
import com.zdsoft.finance.capital.service.CreditEntrustPrincipalService;
import com.zdsoft.finance.capital.service.CreditEntrustRedemPrinciService;
import com.zdsoft.finance.capital.service.CreditEntrustService;
import com.zdsoft.finance.capital.service.CreditEntrustToolService;
import com.zdsoft.finance.capital.service.FeeItemService;
import com.zdsoft.finance.capital.service.LoanCapitalService;
import com.zdsoft.finance.capital.service.SpareCapitalService;
import com.zdsoft.finance.capital.vo.CreditCostTrackingVo;
import com.zdsoft.finance.capital.vo.CreditEntrustAttomVo;
import com.zdsoft.finance.capital.vo.CreditEntrustDebitVo;
import com.zdsoft.finance.capital.vo.CreditEntrustFeeItemVo;
import com.zdsoft.finance.capital.vo.CreditEntrustPrincipalVo;
import com.zdsoft.finance.capital.vo.CreditEntrustRedemPrinciVo;
import com.zdsoft.finance.capital.vo.CreditEntrustVo;
import com.zdsoft.finance.capital.vo.FeeItemVo;
import com.zdsoft.finance.capital.vo.LoanCapitalVo;
import com.zdsoft.finance.capital.vo.SpareCapitalVo;
import com.zdsoft.finance.casemanage.limitApply.entity.CaseLimitApply;
import com.zdsoft.finance.casemanage.limitApply.service.CaseLimitApplyService;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.cooperator.entity.Capitalist;
import com.zdsoft.finance.cooperator.service.CapitalistService;
import com.zdsoft.finance.spi.common.dto.StatusNm;
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
 * @Title: CreditEntrustController.java
 * @ClassName: CreditEntrustController
 * @Description: 信托计划主Controller
 * @author liuwei
 * @date 2017年2月6日 上午9:41:38
 * @version V1.0
 */
@Controller
@RequestMapping("/creditEntrust")
public class CreditEntrustController extends BaseController {

	@Autowired
	CreditEntrustService creditEntrustService;

	@Autowired
	CreditEntrustAttomService creditEntrustAttomService;

	@Autowired
	CreditEntrustDebitService creditEntrustDebitService;

	@Autowired
	CreditEntrustPrincipalService creditEntrustPrincipalService;

	@Autowired
	LoanCapitalService loanCapitalService;

	@Autowired
	SpareCapitalService spareCapitalService;

	@Autowired
	CreditCostTrackingService creditCostTrackingService;

	@Autowired
	FeeItemService feeItemService;

	@Autowired
	CreditEntrustFeeItemService creditEntrustFeeItemService;

	@Autowired
	CapitalistService capitalistService;

	@Autowired
	CreditEntrustRedemPrinciService creditEntrustRedemPrinciService;

	@Autowired
	CreditEntrustToolService creditEntrustToolService;

	@Autowired
	CED CED;

	@Autowired
	CaseLimitApplyService caseLimitApplyService;

	@Autowired
	BankService bankService;

	/**
	 * 
	 * @Title: initFundsManagement
	 * @Description: 资金管理界面入口
	 * @author liuwei
	 * @return ModelAndView
	 */
	@RequestMapping("/initFundsManagement")
	@UriKey(key = "com.zdsoft.finance.capital.initFundsManagement")
	@Menu(resource = "com.zdsoft.finance.capital.initFundsManagement", label = "资金管理", group = "capital", order = 1)
	public ModelAndView initFundsManagement() {
		return new ModelAndView("/capital/credit_capitalist_list");
	}

	/**
	 * 
	 * @Title: initCreditEntrust
	 * @Description: 进入资金计划列表页面
	 * @author liuwei
	 * @param capitalistId
	 *            资方id
	 * @return ModelAndView
	 */
	@RequestMapping("/initCreditEntrust")
	@UriKey(key = "com.zdsoft.finance.capital.initCreditEntrust")
	@Reference(resource = "com.zdsoft.finance.capital.initCreditEntrust", label = "信托资金计划")
	public ModelAndView initCreditEntrust(String capitalistId) {
		ModelMap modelMap = new ModelMap();
		modelMap.put("capitalistId", capitalistId);
		return new ModelAndView("/capital/credit_entrust_list", modelMap);
	}

	/**
	 * 
	 * @Title: getCreditEntrustsBySql
	 * @Description: 信托计划列表查询(sql)
	 * @author liuwei
	 * @param request
	 *            请求
	 * @param jsoncallback
	 *            回调
	 * @param pageable
	 *            分页信息
	 * @return 列表json
	 */
	@RequestMapping("/getCreditEntrustsBySql")
	@UriKey(key = "com.zdsoft.finance.capital.getCreditEntrustsBySql")
	@ResponseBody
	public String getCreditEntrustsBySql(HttpServletRequest request, String jsoncallback, PageRequest pageable) {

		String capitallist_id = request.getParameter("capitallist_id");
		String creditEntrustName = request.getParameter("creditEntrustName");
		String state = request.getParameter("state");

		// 组装查询参数
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("capitallist_id", capitallist_id);
		conditions.put("creditEntrustName", creditEntrustName);
		conditions.put("state", state);

		// 查询所有
		pageable.setPage(1);
		pageable.setRows(10000);
		Page<Map<String, Object>> creditValue = creditEntrustService.reportSql(pageable, conditions);
		List<Map<String, Object>> creditValueRecord = creditValue.getRecords();
		List<Map<String, Object>> changeRecordList = changeCreditValueRecord(creditValueRecord);
		ResponseMsg msg = new ResponseMsg();
		msg.setMsg("列表查询成功");
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setTotal(creditValue.getTotalRows());
		msg.setRows(changeRecordList);

		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

	/**
	 * 
	 * @Title: getCreditEntrustsByHql
	 * @Description: 信托计划列表查询(hql)
	 * @author liuwei
	 * @param request
	 *            请求
	 * @param jsoncallback
	 *            回调
	 * @param pageable
	 *            分页信息
	 * @return 列表json
	 */
	@RequestMapping("/getCreditEntrustsByHql")
	@UriKey(key = "com.zdsoft.finance.capital.getCreditEntrustsByHql")
	@ResponseBody
	public String getCreditEntrustsByHql(HttpServletRequest request, String jsoncallback, PageRequest pageable) {

		// 获取页面封装的查询参数
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");

		// 分页查询
		Page<CreditEntrust> creditValue = creditEntrustService.findByHqlConditions(pageable, queryObjs);

		// 组装Vo
		List<CreditEntrustVo> creditEntrustVos = new ArrayList<CreditEntrustVo>();
		if (ObjectHelper.isNotEmpty(creditValue) && ObjectHelper.isNotEmpty(creditValue.getRecords())) {
			for (int i = 0; i < creditValue.getRecords().size(); i++) {
				CreditEntrustVo creditEntrustVo = new CreditEntrustVo(creditValue.getRecords().get(i), new String[] {},
						new String[] { "capitalStatus" });

				List<CaseLimitApply> caseLimitApplies = caseLimitApplyService
						.findByCreditEntrustId(creditEntrustVo.getId(), CaseLimitApply.FILED_WITHOUT_MONEY);
				List<CaseLimitApply> caseLimitApplies2 = caseLimitApplyService
						.findByCreditEntrustId(creditEntrustVo.getId(), CaseLimitApply.ALLOCATED_FUNDS);

				caseLimitApplies.addAll(caseLimitApplies2);

				BigDecimal tempAmount = BigDecimal.ZERO;
				if (ObjectHelper.isNotEmpty(caseLimitApplies) && caseLimitApplies.size() != 0) {
					for (int j = 0; j < caseLimitApplies.size(); j++) {
						tempAmount = tempAmount.add(caseLimitApplies.get(j).getApplyLimitAmount());
					}
				}

				creditEntrustVo.setResidualDistribution(creditEntrustVo.getResidualDistribution().subtract(tempAmount));

				// TODO ... liuwei 暂定为调用方法，如果效率低，则修改为存储过程
				Map<String, BigDecimal> sprecialFields = creditEntrustToolService
						.calculateSpecialFields(creditValue.getRecords().get(i), new Date());
				BigDecimal notEquippedPay = sprecialFields.get("notEquippedPay");
				BigDecimal bookBalance = sprecialFields.get("bookBalance");
				BigDecimal cumulativeRecoveryPrincipal = sprecialFields.get("cumulativeRecoveryPrincipal");
				BigDecimal cumulativeRecoveryInterest = sprecialFields.get("cumulativeRecoveryInterest");
				BigDecimal cumulativeRecoveryPenalty = sprecialFields.get("cumulativeRecoveryPenalty");
				BigDecimal cumulativeRecoveryLiqDamages = sprecialFields.get("cumulativeRecoveryLiqDamages");
				BigDecimal allocatedLoan = sprecialFields.get("allocatedLoan");
				creditEntrustVo.setNotEquippedPay(notEquippedPay);
				creditEntrustVo.setBookBalance(bookBalance);
				creditEntrustVo.setCumulativeRecoveryPrincipal(cumulativeRecoveryPrincipal);
				creditEntrustVo.setCumulativeRecoveryInterest(cumulativeRecoveryInterest);
				creditEntrustVo.setCumulativeRecoveryPenalty(cumulativeRecoveryPenalty);
				creditEntrustVo.setCumulativeRecoveryLiqDamages(cumulativeRecoveryLiqDamages);
				creditEntrustVo.setAllocatedLoan(allocatedLoan);
				creditEntrustVos.add(creditEntrustVo);
			}
		}

		// 构建返回信息
		ResponseMsg msg = new ResponseMsg();
		msg.setMsg("列表查询成功");
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setTotal(creditValue.getTotalRows());
		msg.setRows(creditEntrustVos);

		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

	/**
	 * 
	 * @Title: changeCreditValueRecord
	 * @Description: 私有方法组装返回数据
	 * @author liuwei
	 * @param oldValues
	 *            原来查询出来的数据
	 * @return 组装好的数据
	 */
	private List<Map<String, Object>> changeCreditValueRecord(List<Map<String, Object>> oldValues) {
		for (Map<String, Object> map : oldValues) {
			// 流入小计
			BigDecimal inflowsubtotal = new BigDecimal(map.get("amount1") == null ? "0" : map.get("amount1").toString())
					.add(map.get("amount2") == null ? BigDecimal.ZERO : new BigDecimal(map.get("amount2").toString()))
					.add(map.get("amount3") == null ? BigDecimal.ZERO : new BigDecimal(map.get("amount3").toString()))
					.add(map.get("amount4") == null ? BigDecimal.ZERO : new BigDecimal(map.get("amount4").toString()))
					.add(map.get("amount5") == null ? BigDecimal.ZERO : new BigDecimal(map.get("amount5").toString()))
					.add(map.get("amount6") == null ? BigDecimal.ZERO : new BigDecimal(map.get("amount7").toString()))
					.add(map.get("amount7") == null ? BigDecimal.ZERO : new BigDecimal(map.get("amount7").toString()))
					.add(map.get("amount8") == null ? BigDecimal.ZERO : new BigDecimal(map.get("amount8").toString()))
					.add(map.get("amount9") == null ? BigDecimal.ZERO : new BigDecimal(map.get("amount9").toString()))
					.add(map.get("amount10") == null ? BigDecimal.ZERO : new BigDecimal(map.get("amount10").toString()))
					.add(map.get("amount11") == null ? BigDecimal.ZERO : new BigDecimal(map.get("amount11").toString()))
					.add(map.get("amount12") == null ? BigDecimal.ZERO
							: new BigDecimal(map.get("amount12").toString()));
			map.put("amount13", inflowsubtotal);

			// 流出小计
			BigDecimal outflowsubtotal = new BigDecimal(
					map.get("amount14") == null ? "0" : map.get("amount14").toString())
							.add(map.get("amount15") == null ? BigDecimal.ZERO
									: new BigDecimal(map.get("amount15").toString()))
							.add(map.get("amount16") == null ? BigDecimal.ZERO
									: new BigDecimal(map.get("amount16").toString()))
							.add(map.get("amount17") == null ? BigDecimal.ZERO
									: new BigDecimal(map.get("amount17").toString()))
							.add(map.get("amount18") == null ? BigDecimal.ZERO
									: new BigDecimal(map.get("amount18").toString()))
							.add(map.get("amount19") == null ? BigDecimal.ZERO
									: new BigDecimal(map.get("amount19").toString()))
							.add(map.get("amount20") == null ? BigDecimal.ZERO
									: new BigDecimal(map.get("amount20").toString()))
							.add(map.get("amount21") == null ? BigDecimal.ZERO
									: new BigDecimal(map.get("amount21").toString()))
							.add(map.get("amount22") == null ? BigDecimal.ZERO
									: new BigDecimal(map.get("amount22").toString()))
							.add(map.get("amount23") == null ? BigDecimal.ZERO
									: new BigDecimal(map.get("amount23").toString()))
							.add(map.get("amount24") == null ? BigDecimal.ZERO
									: new BigDecimal(map.get("amount24").toString()))
							.add(map.get("amount25") == null ? BigDecimal.ZERO
									: new BigDecimal(map.get("amount25").toString()))
							.add(map.get("amount26") == null ? BigDecimal.ZERO
									: new BigDecimal(map.get("amount26").toString()))
							.add(map.get("amount27") == null ? BigDecimal.ZERO
									: new BigDecimal(map.get("amount27").toString()))
							.add(map.get("amount28") == null ? BigDecimal.ZERO
									: new BigDecimal(map.get("amount28").toString()));

			map.put("amount29", outflowsubtotal);

			// 剩余可分配
			BigDecimal residualDistribution = (inflowsubtotal == null ? BigDecimal.ZERO : inflowsubtotal)
					.subtract(outflowsubtotal == null ? BigDecimal.ZERO : outflowsubtotal)
					.subtract(new BigDecimal(map.get("amount31").toString()) == null ? BigDecimal.ZERO
							: new BigDecimal(map.get("amount31").toString()));

			map.put("amount31", residualDistribution);

			// 时间格式转换
			Date amount44 = (Date) map.get("amount44");
			map.put("amount44", DateHelper.dateToString(amount44, DateHelper.DATE_LONG_SIMPLE_FORMAT));
		}

		return oldValues;
	}

	/**
	 * 
	 * @Title: getCreditEntrustList
	 * @Description: 获取信托计划列表
	 * @author liuwei
	 * @param request
	 *            请求参数
	 * @param jsoncallback
	 * @param pageable
	 *            分页信息
	 * @return 列表信息json
	 */
	@RequestMapping("/getCreditEntrustList")
	@UriKey(key = "com.zdsoft.finance.capital.getCreditEntrustList")
	@ResponseBody
	public String getCreditEntrustList(HttpServletRequest request, String jsoncallback, PageRequest pageable) {

		Map<String, Object> conditions = new HashMap<String, Object>();
		List<CreditEntrust> creditEntrustList = creditEntrustService.findByConditions(conditions);
		List<CreditEntrustVo> creditEntrustVos = new ArrayList<CreditEntrustVo>();
		for (int i = 0; i < creditEntrustList.size(); i++) {
			CreditEntrust creditEntrust = creditEntrustList.get(i);
			CreditEntrustVo vo = new CreditEntrustVo(creditEntrust);
			creditEntrustVos.add(vo);
		}
		return ObjectHelper.objectToJson(creditEntrustVos, jsoncallback);
	}

	/**
	 * 
	 * @Title: getCreditEntrustList
	 * @Description: 获取信托计划列表
	 * @author liuwei
	 * @param request
	 *            请求参数
	 * @param jsoncallback
	 * @param pageable
	 *            分页信息
	 * @return 列表信息json
	 */
	@RequestMapping("/getCreditEntrustListByCapitalistId")
	@UriKey(key = "com.zdsoft.finance.capital.getCreditEntrustListByCapitalistId")
	@ResponseBody
	public String getCreditEntrustListByCapitalistId(String capitalistId, HttpServletRequest request,
			String jsoncallback, PageRequest pageable) {

		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("capitalistId", capitalistId);
		List<CreditEntrust> creditEntrustList = creditEntrustService.findByConditions(conditions);
		List<CreditEntrustVo> creditEntrustVos = new ArrayList<CreditEntrustVo>();
		for (int i = 0; i < creditEntrustList.size(); i++) {
			CreditEntrust creditEntrust = creditEntrustList.get(i);
			CreditEntrustVo vo = new CreditEntrustVo(creditEntrust);
			creditEntrustVos.add(vo);
		}
		return ObjectHelper.objectToJson(creditEntrustVos, jsoncallback);
	}

	/**
	 * 
	 * @Title: initCreditEntrustAdd
	 * @Description: 信托计划详情页面
	 * @author liuwei
	 * @param capitalistId
	 *            资方id
	 * @param id
	 *            信托计划id
	 * @return ModelAndView
	 */
	@RequestMapping("/initCreditEntrustAdd")
	@UriKey(key = "com.zdsoft.finance.capital.initCreditEntrustAdd")
	@Reference(resource = "com.zdsoft.finance.capital.initCreditEntrustAdd", label = "信托计划详情")
	public ModelAndView initCreditEntrustAdd(String capitalistId, String id) {
		ModelMap modelMap = new ModelMap();

		// 获取银行信息
		List<Bank> banks = bankService.findAllBank();
		List<Map<String, Object>> bankMap = new ArrayList<Map<String, Object>>();
		if (ObjectHelper.isNotEmpty(banks)) {
			for (int i = 0; i < banks.size(); i++) {
				Map<String, Object> objMap = new HashMap<String, Object>();
				objMap.put("py", banks.get(i).getBankName());
				objMap.put("name", banks.get(i).getBankName());
				objMap.put("code", banks.get(i).getBankCode());

				bankMap.add(objMap);
			}
		}
		modelMap.put("data", ObjectHelper.objectToJson(bankMap));

		modelMap.put("maxDate", DateHelper.dateToString(new Date(), DateHelper.DATE_SHORT_FORMAT));

		if (ObjectHelper.isEmpty(id)) { // 判断是新增还是修改,为了方便,分为新增页面和修改页面
			// 生成临时uuid
			String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
			modelMap.put("tempUuid", uuid);

			// 放置资方id
			modelMap.put("capitalistId", capitalistId);

			// 查询资金信息
			Capitalist capitalist = null;
			try {
				capitalist = capitalistService.findOne(capitalistId);
			} catch (BusinessException e) {
				e.printStackTrace();
				logger.error("获取资方信息失败", e);
			}
			modelMap.put("cooperatorName", capitalist.getCapitalName());

			return new ModelAndView("/capital/credit_entrust_add", modelMap);
		} else { // 如果id存在，则根据id查询出信托计划

			try {
				CreditEntrust creditEntrust = creditEntrustService.findOne(id);
				CreditEntrustVo creditEntrustVo = new CreditEntrustVo(creditEntrust);
				modelMap.put("creditEntrustVo", creditEntrustVo);
			} catch (BusinessException e) {
				e.printStackTrace();
				logger.error("信托计划详情查询失败", e);
			}

			return new ModelAndView("/capital/credit_entrust_edit", modelMap);
		}

	}

	/**
	 * 
	 * @Title: investmentList
	 * @Description: 出资信息列表
	 * @author liuwei
	 * @param pageable
	 *            分页信息
	 * @param request
	 *            请求
	 * @param jsoncallback
	 * @return 出资信息列表json
	 */
	@RequestMapping("/investmentList")
	@UriKey(key = "com.zdsoft.finance.capital.investmentList")
	@ResponseBody
	public String investmentList(PageRequest pageable, HttpServletRequest request, String jsoncallback) {
		// 获取页面封装的查询参数
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");

		// 组装排序参数
		QueryObj obj = new QueryObj();
		obj.setObj("createTime");
		obj.setElement("OB");
		obj.setOperator("OB");
		obj.setValue("ASC");
		queryObjs.add(obj);

		// 查询分页
		Page<CreditEntrustPrincipal> principalPage = creditEntrustPrincipalService.findByHqlConditions(pageable,
				queryObjs);

		// 组装返回vo
		List<CreditEntrustPrincipalVo> principalVos = new ArrayList<CreditEntrustPrincipalVo>();

		for (int i = 0; i < principalPage.getRecords().size(); i++) {
			CreditEntrustPrincipalVo principalVo = new CreditEntrustPrincipalVo(principalPage.getRecords().get(i));
			principalVos.add(principalVo);
		}

		// 判断初始本金
		String tempType = "";
		if (pageable.getPage() == 1) { // 第一页才进行处理
			for (int i = 0; i < principalVos.size(); i++) {
				// 需要特殊处理的是，每种类型的第一条数据
				if (principalVos.size() != 0 && i == 0) {
					principalVos.get(i).setInitialPrincipal(principalVos.get(i).getPrincipalAmount());
					principalVos.get(i).setPrincipalAmount(BigDecimal.ZERO);
					tempType = principalVos.get(i).getContributionType();
				} else if (principalVos.size() != 0 && (!tempType.equals(principalVos.get(i).getContributionType()))) { // 数量不为空，并且类型不为第一个类型，那么则设置为初始值，并退出循环
					principalVos.get(i).setInitialPrincipal(principalVos.get(i).getPrincipalAmount());
					principalVos.get(i).setPrincipalAmount(BigDecimal.ZERO);
					break;
				}
			}
		}

		// 返回msg
		ResponseMsg msg = new ResponseMsg();
		msg.setMsg("列表查询成功");
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setRows(principalVos);
		msg.setTotal(principalPage.getTotalRows());

		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

	/**
	 * 
	 * @Title: principalInputList
	 * @Description: 信托计划本金投入列表
	 * @author liuwei
	 * @param pageable
	 *            分页信息
	 * @param request
	 *            请求
	 * @param jsoncallback
	 * @return 信托计划本金投入列表json
	 */
	@RequestMapping("/principalInputList")
	@UriKey(key = "com.zdsoft.finance.capital.principalInputList")
	@ResponseBody
	public String principalInputList(PageRequest pageable, HttpServletRequest request, String jsoncallback) {
		// 获取页面封装的查询参数
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
		// 查询分页
		Page<CreditEntrustPrincipal> principalPage = creditEntrustPrincipalService.findByHqlConditions(pageable,
				queryObjs);

		// 组装返回vo
		List<CreditEntrustPrincipalVo> principalVos = new ArrayList<CreditEntrustPrincipalVo>();

		for (int i = 0; i < principalPage.getRecords().size(); i++) {
			CreditEntrustPrincipalVo principalVo = new CreditEntrustPrincipalVo(principalPage.getRecords().get(i),
					new String[] {}, new String[] { "contributionType" });
			principalVos.add(principalVo);
		}

		// 返回msg
		ResponseMsg msg = new ResponseMsg();
		msg.setMsg("列表查询成功");
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setRows(principalVos);
		msg.setTotal(principalPage.getTotalRows());

		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

	/**
	 * 
	 * @Title: loanCapitalList
	 * @Description: 信托专户贷方资金（非本金）跟踪列表
	 * @author liuwei
	 * @param pageable
	 *            分页信息
	 * @param request
	 *            请求
	 * @param jsoncallback
	 * @return 信托专户贷方资金（非本金）跟踪列表json
	 */
	@RequestMapping("/loanCapitalList")
	@UriKey(key = "com.zdsoft.finance.capital.loanCapitalList")
	@ResponseBody
	public String loanCapitalList(PageRequest pageable, HttpServletRequest request, String jsoncallback) {
		// 获取页面封装的查询参数
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
		// 查询分页
		Page<LoanCapital> loanCapitalPage = loanCapitalService.findByHqlConditions(pageable, queryObjs);

		// 组装返回vo
		List<LoanCapitalVo> loanCapitalVos = new ArrayList<LoanCapitalVo>();

		for (int i = 0; i < loanCapitalPage.getRecords().size(); i++) {
			LoanCapitalVo loanCapitalVo = new LoanCapitalVo(loanCapitalPage.getRecords().get(i), new String[] {},
					new String[] { "lenderType", "capitalState" });
			loanCapitalVos.add(loanCapitalVo);
		}

		// 返回msg
		ResponseMsg msg = new ResponseMsg();
		msg.setMsg("列表查询成功");
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setRows(loanCapitalVos);
		msg.setTotal(loanCapitalPage.getTotalRows());

		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

	/**
	 * 
	 * @Title: costTrackingList
	 * @Description: 应付费用跟踪列表
	 * @author liuwei
	 * @param pageable
	 *            分页信息
	 * @param request
	 *            请求
	 * @param jsoncallback
	 * @return 应付费用跟踪列表Json
	 */
	@RequestMapping("/costTrackingList")
	@UriKey(key = "com.zdsoft.finance.capital.costTrackingList")
	@ResponseBody
	public String costTrackingList(PageRequest pageable, HttpServletRequest request, String jsoncallback) {
		// 获取页面封装的查询参数
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
		// 查询分页
		Page<CreditCostTracking> costTrackingPage = creditCostTrackingService.findByHqlConditions(pageable, queryObjs);

		// 组装返回vo
		List<CreditCostTrackingVo> costTrackingVos = new ArrayList<CreditCostTrackingVo>();

		for (int i = 0; i < costTrackingPage.getRecords().size(); i++) {
			CreditCostTrackingVo loanCapitalVo = new CreditCostTrackingVo(costTrackingPage.getRecords().get(i),
					new String[] {}, new String[] { "expenditureType" });
			costTrackingVos.add(loanCapitalVo);
		}

		// 返回msg
		ResponseMsg msg = new ResponseMsg();
		msg.setMsg("列表查询成功");
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setRows(costTrackingVos);
		msg.setTotal(costTrackingPage.getTotalRows());

		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

	/**
	 * 
	 * @Title: debitTrackingList
	 * @Description: 借方资金跟踪列表
	 * @author liuwei
	 * @param pageable
	 *            分页信息
	 * @param request
	 *            请求
	 * @param jsoncallback
	 * @return 借方资金跟踪列表json
	 */
	@RequestMapping("/debitTrackingList")
	@UriKey(key = "com.zdsoft.finance.capital.debitTrackingList")
	@ResponseBody
	public String debitTrackingList(PageRequest pageable, HttpServletRequest request, String jsoncallback) {
		// 获取页面封装的查询参数
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
		// 查询分页
		Page<CreditEntrustDebit> debitTrackingPage = creditEntrustDebitService.findByHqlConditions(pageable, queryObjs);

		// 组装返回vo
		List<CreditEntrustDebitVo> debitTrackingVos = new ArrayList<CreditEntrustDebitVo>();

		for (int i = 0; i < debitTrackingPage.getRecords().size(); i++) {
			CreditEntrustDebitVo debitVo = new CreditEntrustDebitVo(debitTrackingPage.getRecords().get(i),
					new String[] {}, new String[] { "debitType", "debtorType", "capitalState" });
			debitTrackingVos.add(debitVo);
		}

		// 返回msg
		ResponseMsg msg = new ResponseMsg();
		msg.setMsg("列表查询成功");
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setRows(debitTrackingVos);
		msg.setTotal(debitTrackingPage.getTotalRows());

		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

	/**
	 * 
	 * @Title: spareCapitalList
	 * @Description: 备付资金跟踪列表
	 * @author liuwei
	 * @param pageable
	 *            分页信息
	 * @param request
	 *            请求信息
	 * @param jsoncallback
	 * @return 备付资金跟踪列表json
	 */
	@RequestMapping("/spareCapitalList")
	@UriKey(key = "com.zdsoft.finance.capital.spareCapitalList")
	@ResponseBody
	public String spareCapitalList(PageRequest pageable, HttpServletRequest request, String jsoncallback) {
		// 获取页面封装的查询参数
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
		// 查询分页
		Page<SpareCapital> spareCapitalPage = spareCapitalService.findByHqlConditions(pageable, queryObjs);

		// 组装返回vo
		List<SpareCapitalVo> spareCapitalVos = new ArrayList<SpareCapitalVo>();

		for (int i = 0; i < spareCapitalPage.getRecords().size(); i++) {
			SpareCapitalVo spareCapitalVo = new SpareCapitalVo(spareCapitalPage.getRecords().get(i), new String[] {},
					new String[] { "operationType" });
			spareCapitalVos.add(spareCapitalVo);
		}

		// 返回msg
		ResponseMsg msg = new ResponseMsg();
		msg.setMsg("列表查询成功");
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setRows(spareCapitalVos);
		msg.setTotal(spareCapitalPage.getTotalRows());

		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

	/**
	 * 
	 * @Title: attomCapitalList
	 * @Description: 信托计划转让列表
	 * @author liuwei
	 * @param pageable
	 *            分页信息
	 * @param request
	 *            请求
	 * @param jsoncallback
	 * @return 信托计划转让列表json
	 */
	@RequestMapping("/attomCapitalList")
	@UriKey(key = "com.zdsoft.finance.capital.attomCapitalList")
	@ResponseBody
	public String attomCapitalList(PageRequest pageable, HttpServletRequest request, String jsoncallback) {
		// 获取页面封装的查询参数
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
		// 查询分页
		Page<CreditEntrustAttom> attomPage = creditEntrustAttomService.findByHqlConditions(pageable, queryObjs);

		// 组装返回vo
		List<CreditEntrustAttomVo> attomVos = new ArrayList<CreditEntrustAttomVo>();

		for (int i = 0; i < attomPage.getRecords().size(); i++) {
			CreditEntrustAttomVo attomVo = new CreditEntrustAttomVo(attomPage.getRecords().get(i), new String[] {},
					new String[] { "acceptType", "attomState" });
			attomVos.add(attomVo);
		}

		// 返回msg
		ResponseMsg msg = new ResponseMsg();
		msg.setMsg("列表查询成功");
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setRows(attomVos);
		msg.setTotal(attomPage.getTotalRows());

		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

	/**
	 * 
	 * @Title: redemePrinciList
	 * @Description: 本金赎回列表
	 * @author liuwei
	 * @param pageable
	 *            分页信息
	 * @param request
	 *            请求
	 * @param jsoncallback
	 * @return 本金赎回列表json
	 */
	@RequestMapping("/redemePrinciList")
	@UriKey(key = "com.zdsoft.finance.capital.redemePrinciList")
	@ResponseBody
	public String redemePrinciList(PageRequest pageable, HttpServletRequest request, String jsoncallback) {
		// 获取页面封装的查询参数
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
		// 查询分页
		Page<CreditEntrustRedemPrinci> attomPage = creditEntrustRedemPrinciService.findByHqlConditions(pageable,
				queryObjs);

		// 组装返回vo
		List<CreditEntrustRedemPrinciVo> attomVos = new ArrayList<CreditEntrustRedemPrinciVo>();

		for (int i = 0; i < attomPage.getRecords().size(); i++) {
			CreditEntrustRedemPrinciVo attomVo = new CreditEntrustRedemPrinciVo(attomPage.getRecords().get(i),
					new String[] {}, new String[] { "contributionType" });
			attomVos.add(attomVo);
		}

		// 返回msg
		ResponseMsg msg = new ResponseMsg();
		msg.setMsg("列表查询成功");
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setRows(attomVos);
		msg.setTotal(attomPage.getTotalRows());

		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

	/**
	 * 
	 * @Title: countPrincipaScale
	 * @Description: 页面动态计划本金规模
	 * @author liuwei
	 * @param tempUuid
	 *            临时id
	 * @param creditEntrustId
	 *            信托计划id
	 * @param jsoncallback
	 * @return 计算后的本金规模
	 */
	@RequestMapping("/countPrincipaScale")
	@UriKey(key = "com.zdsoft.finance.capital.countPrincipaScale")
	@ResponseBody
	public String countPrincipaScale(String tempUuid, String creditEntrustId, String jsoncallback) {

		BigDecimal totalAmount = BigDecimal.ZERO;

		List<CreditEntrustPrincipal> principals = new ArrayList<CreditEntrustPrincipal>();
		List<CreditEntrustRedemPrinci> redemPrincis = new ArrayList<CreditEntrustRedemPrinci>();

		// 判断临时id是否存在
		if (ObjectHelper.isNotEmpty(tempUuid)) { // 临时id存在则直接计算出结果
			principals = creditEntrustPrincipalService.findByTempUuid(tempUuid);
			redemPrincis = creditEntrustRedemPrinciService.findByTempUuid(tempUuid);
		} else { // 临时id不存在则根据信托计划id计算出结果
			Map<String, Object> conditions = new HashMap<String, Object>();
			conditions.put("creditEntrustId", creditEntrustId);
			principals = creditEntrustPrincipalService.findByConditions(conditions);
			redemPrincis = creditEntrustRedemPrinciService.findByConditions(conditions);
		}

		// 计算总金额
		if (ObjectHelper.isNotEmpty(principals)) {
			for (int i = 0; i < principals.size(); i++) {
				totalAmount = totalAmount.add(principals.get(i).getPrincipalAmount() == null ? BigDecimal.ZERO
						: principals.get(i).getPrincipalAmount());
			}
		}
		if (ObjectHelper.isNotEmpty(redemPrincis)) {
			for (int i = 0; i < redemPrincis.size(); i++) {
				totalAmount = totalAmount.subtract(redemPrincis.get(i).getRedemptionAmount() == null ? BigDecimal.ZERO
						: redemPrincis.get(i).getRedemptionAmount());
			}
		}

		return ObjectHelper.objectToJson(totalAmount, jsoncallback);
	}

	/**
	 * 
	 * @Title: initNonPrincipalTracking
	 * @Description: 进入专户贷方资金（非本金）跟踪页面
	 * @author liuwei
	 * @param tempUuid
	 *            信托计划临时id
	 * @param id
	 *            专户贷方资金（非本金）跟踪id
	 * @param creditEntrustId
	 *            信托计划id
	 * @return ModelAndView
	 */
	@RequestMapping("/initNonPrincipalTracking")
	@UriKey(key = "com.zdsoft.finance.capital.initNonPrincipalTracking")
	@Reference(resource = "com.zdsoft.finance.capital.initNonPrincipalTracking", label = "专户贷方资金（非本金）跟踪")
	public ModelAndView initNonPrincipalTracking(String tempUuid, String id, String creditEntrustId) {
		ModelMap modelMap = new ModelMap();
		modelMap.put("tempUuid", tempUuid);
		modelMap.put("creditEntrustId", creditEntrustId);
		if (ObjectHelper.isNotEmpty(id)) { // 如果id存在则查询出数据
			try {
				LoanCapital loanCapital = loanCapitalService.findOne(id);
				LoanCapitalVo loanCapitalVo = new LoanCapitalVo(loanCapital);
				modelMap.put("loanCapitalVo", loanCapitalVo);

				// 封装已有费用项
				List<CreditEntrustFeeItem> feeItems = creditEntrustFeeItemService.findByBusinessId(loanCapital.getId());
				List<CreditEntrustFeeItemVo> feeItemVos = new ArrayList<CreditEntrustFeeItemVo>();
				for (int i = 0; i < feeItems.size(); i++) {
					CreditEntrustFeeItemVo feeItemVo = new CreditEntrustFeeItemVo(feeItems.get(i));
					feeItemVos.add(feeItemVo);
				}

				modelMap.put("feeItemVos", feeItemVos);
			} catch (BusinessException e) {
				e.printStackTrace();
				logger.error("查询数据失败", e);
			}
		}

		return new ModelAndView("/capital/loan_capital_add", modelMap);
	}

	/**
	 * 
	 * @Title: initNonPrincipalTrackingView
	 * @Description: 进入专户贷方资金（非本金）跟踪查看页面
	 * @author liuwei
	 * @param tempUuid
	 *            信托计划临时id
	 * @param id
	 *            专户贷方资金（非本金）跟踪id
	 * @param creditEntrustId
	 *            信托计划id
	 * @return ModelAndView
	 */
	@RequestMapping("/initNonPrincipalTrackingView")
	@UriKey(key = "com.zdsoft.finance.capital.initNonPrincipalTrackingView")
	@Reference(resource = "com.zdsoft.finance.capital.initNonPrincipalTrackingView", label = "专户贷方资金（非本金）跟踪查看")
	public ModelAndView initNonPrincipalTrackingView(String tempUuid, String id, String creditEntrustId) {
		ModelMap modelMap = new ModelMap();
		modelMap.put("tempUuid", tempUuid);
		modelMap.put("creditEntrustId", creditEntrustId);
		if (ObjectHelper.isNotEmpty(id)) {
			try {
				LoanCapital loanCapital = loanCapitalService.findOne(id);
				LoanCapitalVo loanCapitalVo = new LoanCapitalVo(loanCapital, new String[] {},
						new String[] { "lenderType", "capitalState" });
				modelMap.put("loanCapitalVo", loanCapitalVo);

				// 封装已有费用项
				List<CreditEntrustFeeItem> feeItems = creditEntrustFeeItemService.findByBusinessId(loanCapital.getId());
				List<CreditEntrustFeeItemVo> feeItemVos = new ArrayList<CreditEntrustFeeItemVo>();
				for (int i = 0; i < feeItems.size(); i++) {
					CreditEntrustFeeItemVo feeItemVo = new CreditEntrustFeeItemVo(feeItems.get(i));
					feeItemVos.add(feeItemVo);
				}

				modelMap.put("feeItemVos", feeItemVos);
			} catch (BusinessException e) {
				e.printStackTrace();
				logger.error("查询数据失败", e);
			}
		}

		return new ModelAndView("/capital/loan_capital_view", modelMap);
	}

	/**
	 * 
	 * @Title: initCostTracking
	 * @Description: 进入信托计划应付费用跟踪页面
	 * @author liuwei
	 * @param tempUuid
	 *            信托计划临时id
	 * @param id
	 *            信托计划应付费用跟踪id
	 * @param creditEntrustId
	 *            信托计划id
	 * @return ModelAndView
	 */
	@RequestMapping("/initCostTracking")
	@UriKey(key = "com.zdsoft.finance.capital.initCostTracking")
	@Reference(resource = "com.zdsoft.finance.capital.initCostTracking", label = "信托计划应付费用跟踪")
	public ModelAndView initCostTracking(String tempUuid, String id, String creditEntrustId) {

		ModelMap modelMap = new ModelMap();
		modelMap.put("tempUuid", tempUuid);
		modelMap.put("creditEntrustId", creditEntrustId);

		if (ObjectHelper.isNotEmpty(id)) { // 判断信托计划id是否存在
			try {

				// 查询信托计划应付费用跟踪
				CreditCostTracking costTracking = creditCostTrackingService.findOne(id);
				CreditCostTrackingVo creditCostTrackingVo = new CreditCostTrackingVo(costTracking);
				modelMap.put("creditCostTrackingVo", creditCostTrackingVo);

				// 封装已有费用项
				List<CreditEntrustFeeItem> feeItems = creditEntrustFeeItemService
						.findByBusinessId(costTracking.getId());
				List<CreditEntrustFeeItemVo> feeItemVos = new ArrayList<CreditEntrustFeeItemVo>();
				for (int i = 0; i < feeItems.size(); i++) {
					CreditEntrustFeeItemVo feeItemVo = new CreditEntrustFeeItemVo(feeItems.get(i));
					feeItemVos.add(feeItemVo);
				}

				modelMap.put("feeItemVos", feeItemVos);
			} catch (BusinessException e) {
				e.printStackTrace();
				logger.error("查询数据失败", e);
			}
		}

		return new ModelAndView("/capital/costTracking_add", modelMap);
	}

	/**
	 * 
	 * @Title: initCostTrackingView
	 * @Description: 信托计划应付费用跟踪查看
	 * @author liuwei
	 * @param tempUuid
	 *            信托计划临时id
	 * @param id
	 *            应付费用跟踪id
	 * @param creditEntrustId
	 *            信托计划id
	 * @return ModelAndView
	 */
	@RequestMapping("/initCostTrackingView")
	@UriKey(key = "com.zdsoft.finance.capital.initCostTrackingView")
	@Reference(resource = "com.zdsoft.finance.capital.initCostTrackingView", label = "信托计划应付费用跟踪查看")
	public ModelAndView initCostTrackingView(String tempUuid, String id, String creditEntrustId) {

		ModelMap modelMap = new ModelMap();
		modelMap.put("tempUuid", tempUuid);
		modelMap.put("creditEntrustId", creditEntrustId);

		if (ObjectHelper.isNotEmpty(id)) { // 判断信托计划id是否存在
			try {

				// 查询信托计划应付费用跟踪信息
				CreditCostTracking costTracking = creditCostTrackingService.findOne(id);

				// 转换vo
				CreditCostTrackingVo creditCostTrackingVo = new CreditCostTrackingVo(costTracking, new String[] {},
						new String[] { "expenditureType" });
				modelMap.put("creditCostTrackingVo", creditCostTrackingVo);

				// 封装已有费用项
				List<CreditEntrustFeeItem> feeItems = creditEntrustFeeItemService
						.findByBusinessId(costTracking.getId());
				List<CreditEntrustFeeItemVo> feeItemVos = new ArrayList<CreditEntrustFeeItemVo>();
				for (int i = 0; i < feeItems.size(); i++) {
					CreditEntrustFeeItemVo feeItemVo = new CreditEntrustFeeItemVo(feeItems.get(i));
					feeItemVos.add(feeItemVo);
				}

				modelMap.put("feeItemVos", feeItemVos);
			} catch (BusinessException e) {
				e.printStackTrace();
				logger.error("查询数据失败", e);
			}
		}

		return new ModelAndView("/capital/costTracking_view", modelMap);
	}

	/**
	 * 
	 * @Title: initTrackingTrustPlan
	 * @Description: 进入信托计划借方资金（非放款）跟踪页面
	 * @author liuwei
	 * @param tempUuid
	 *            信托计划临时id
	 * @param id
	 *            信托计划借方资金（非放款）跟踪id
	 * @param creditEntrustId
	 *            信托计划id
	 * @return ModelAndView
	 */
	@RequestMapping("/initTrackingTrustPlan")
	@UriKey(key = "com.zdsoft.finance.capital.initTrackingTrustPlan")
	@Reference(resource = "com.zdsoft.finance.capital.initTrackingTrustPlan", label = "信托计划借方资金（非放款）跟踪")
	public ModelAndView initTrackingTrustPlan(String tempUuid, String id, String creditEntrustId) {

		ModelMap modelMap = new ModelMap();
		modelMap.put("tempUuid", tempUuid);
		modelMap.put("creditEntrustId", creditEntrustId);

		if (ObjectHelper.isNotEmpty(id)) { // 判断信托计划id是否存在
			try {

				// 查询信托计划借方资金（非放款）跟踪信息
				CreditEntrustDebit creditEntrustDebit = creditEntrustDebitService.findOne(id);
				CreditEntrustDebitVo creditEntrustDebitVo = new CreditEntrustDebitVo(creditEntrustDebit);
				modelMap.put("creditEntrustDebitVo", creditEntrustDebitVo);

				// 封装已有费用项
				List<CreditEntrustFeeItem> feeItems = creditEntrustFeeItemService
						.findByBusinessId(creditEntrustDebit.getId());
				List<CreditEntrustFeeItemVo> feeItemVos = new ArrayList<CreditEntrustFeeItemVo>();
				for (int i = 0; i < feeItems.size(); i++) {
					CreditEntrustFeeItemVo feeItemVo = new CreditEntrustFeeItemVo(feeItems.get(i));
					feeItemVos.add(feeItemVo);
				}

				modelMap.put("feeItemVos", feeItemVos);
			} catch (BusinessException e) {
				e.printStackTrace();
				logger.error("查询数据失败", e);
			}
		}

		return new ModelAndView("/capital/credit_entrust_debit_add", modelMap);
	}

	/**
	 * 
	 * @Title: initTrackingTrustPlanView
	 * @Description: 信托计划借方资金（非放款）跟踪查看
	 * @author liuwei
	 * @param tempUuid
	 *            信托计划临时id
	 * @param id
	 *            信托计划借方资金（非放款）跟踪id
	 * @param creditEntrustId
	 *            信托计划id
	 * @return ModelAndView
	 */
	@RequestMapping("/initTrackingTrustPlanView")
	@UriKey(key = "com.zdsoft.finance.capital.initTrackingTrustPlanView")
	@Reference(resource = "com.zdsoft.finance.capital.initTrackingTrustPlanView", label = "信托计划借方资金（非放款）跟踪查看")
	public ModelAndView initTrackingTrustPlanView(String tempUuid, String id, String creditEntrustId) {

		ModelMap modelMap = new ModelMap();
		modelMap.put("tempUuid", tempUuid);
		modelMap.put("creditEntrustId", creditEntrustId);

		if (ObjectHelper.isNotEmpty(id)) { // 判断信托计划id是否存在
			try {

				// 查询信托计划借方资金（非放款）跟踪信息
				CreditEntrustDebit creditEntrustDebit = creditEntrustDebitService.findOne(id);
				CreditEntrustDebitVo creditEntrustDebitVo = new CreditEntrustDebitVo(creditEntrustDebit,
						new String[] {}, new String[] { "debitType", "debtorType", "capitalState" });
				modelMap.put("creditEntrustDebitVo", creditEntrustDebitVo);

				// 封装已有费用项
				List<CreditEntrustFeeItem> feeItems = creditEntrustFeeItemService
						.findByBusinessId(creditEntrustDebit.getId());
				List<CreditEntrustFeeItemVo> feeItemVos = new ArrayList<CreditEntrustFeeItemVo>();
				for (int i = 0; i < feeItems.size(); i++) {
					CreditEntrustFeeItemVo feeItemVo = new CreditEntrustFeeItemVo(feeItems.get(i));
					feeItemVos.add(feeItemVo);
				}

				modelMap.put("feeItemVos", feeItemVos);
			} catch (BusinessException e) {
				e.printStackTrace();
				logger.error("查询数据失败", e);
			}
		}

		return new ModelAndView("/capital/credit_entrust_debit_view", modelMap);
	}

	/**
	 * 备付资金跟踪
	 * 
	 * @return
	 */
	@RequestMapping("/initReserveFundTracking")
	@UriKey(key = "com.zdsoft.finance.capital.initReserveFundTracking")
	@Reference(resource = "com.zdsoft.finance.capital.initReserveFundTracking", label = "备付资金跟踪")
	public ModelAndView initReserveFundTracking(String tempUuid, String id, String creditEntrustId) {
		ModelMap modelMap = new ModelMap();
		modelMap.put("tempUuid", tempUuid);
		modelMap.put("creditEntrustId", creditEntrustId);
		if (ObjectHelper.isNotEmpty(id)) {
			try {
				SpareCapital spareCapital = spareCapitalService.findOne(id);
				SpareCapitalVo spareCapitalVo = new SpareCapitalVo(spareCapital);
				modelMap.put("spareCapitalVo", spareCapitalVo);

				// 封装已有费用项
				List<CreditEntrustFeeItem> feeItems = creditEntrustFeeItemService
						.findByBusinessId(spareCapital.getId());
				List<CreditEntrustFeeItemVo> feeItemVos = new ArrayList<CreditEntrustFeeItemVo>();
				for (int i = 0; i < feeItems.size(); i++) {
					CreditEntrustFeeItemVo feeItemVo = new CreditEntrustFeeItemVo(feeItems.get(i));
					feeItemVos.add(feeItemVo);
				}

				modelMap.put("feeItemVos", feeItemVos);
			} catch (BusinessException e) {
				e.printStackTrace();
				logger.error("查询数据失败", e);
			}
		}

		return new ModelAndView("/capital/spare_capital_add", modelMap);
	}

	/**
	 * 
	 * @Title: initReserveFundTrackingView
	 * @Description: 进入备付资金跟踪查看页面
	 * @author liuwei
	 * @param tempUuid
	 *            信托计划临时id
	 * @param id
	 *            备付资金跟踪id
	 * @param creditEntrustId
	 *            信托计划id
	 * @return ModelAndView
	 */
	@RequestMapping("/initReserveFundTrackingView")
	@UriKey(key = "com.zdsoft.finance.capital.initReserveFundTrackingView")
	@Reference(resource = "com.zdsoft.finance.capital.initReserveFundTrackingView", label = "备付资金跟踪查看")
	public ModelAndView initReserveFundTrackingView(String tempUuid, String id, String creditEntrustId) {
		ModelMap modelMap = new ModelMap();
		modelMap.put("tempUuid", tempUuid);
		modelMap.put("creditEntrustId", creditEntrustId);
		if (ObjectHelper.isNotEmpty(id)) {
			try {

				// 查询备付资金跟踪信息
				SpareCapital spareCapital = spareCapitalService.findOne(id);
				SpareCapitalVo spareCapitalVo = new SpareCapitalVo(spareCapital, new String[] {},
						new String[] { "operationType" });
				modelMap.put("spareCapitalVo", spareCapitalVo);

				// 封装已有费用项
				List<CreditEntrustFeeItem> feeItems = creditEntrustFeeItemService
						.findByBusinessId(spareCapital.getId());
				List<CreditEntrustFeeItemVo> feeItemVos = new ArrayList<CreditEntrustFeeItemVo>();
				for (int i = 0; i < feeItems.size(); i++) {
					CreditEntrustFeeItemVo feeItemVo = new CreditEntrustFeeItemVo(feeItems.get(i));
					feeItemVos.add(feeItemVo);
				}

				modelMap.put("feeItemVos", feeItemVos);
			} catch (BusinessException e) {
				e.printStackTrace();
				logger.error("查询数据失败", e);
			}
		}

		return new ModelAndView("/capital/spare_capital_view", modelMap);
	}

	/**
	 * 
	 * @Title: initTransferInformation
	 * @Description: 信托计划转让信息
	 * @author liuwei
	 * @param tempUuid
	 *            信托计划临时id
	 * @param id
	 *            信托计划转让id
	 * @param creditEntrustId
	 *            信托计划id
	 * @return ModelAndView
	 */
	@RequestMapping("/initTransferInformation")
	@UriKey(key = "com.zdsoft.finance.capital.initTransferInformation")
	@Reference(resource = "com.zdsoft.finance.capital.initTransferInformation", label = "信托计划转让信息")
	public ModelAndView initTransferInformation(String tempUuid, String id, String creditEntrustId) {

		ModelMap modelMap = new ModelMap();
		modelMap.put("tempUuid", tempUuid);
		modelMap.put("creditEntrustId", creditEntrustId);

		if (ObjectHelper.isNotEmpty(id)) { // 判断信托计划id是否存在
			try {

				// 查询信托计划转让信息
				CreditEntrustAttom creditEntrustAttom = creditEntrustAttomService.findOne(id);
				CreditEntrustAttomVo creditEntrustAttomVo = new CreditEntrustAttomVo(creditEntrustAttom);
				modelMap.put("creditEntrustAttomVo", creditEntrustAttomVo);

				// 封装已有费用项
				List<CreditEntrustFeeItem> feeItems = creditEntrustFeeItemService
						.findByBusinessId(creditEntrustAttom.getId());
				List<CreditEntrustFeeItemVo> feeItemVos = new ArrayList<CreditEntrustFeeItemVo>();
				for (int i = 0; i < feeItems.size(); i++) {
					CreditEntrustFeeItemVo feeItemVo = new CreditEntrustFeeItemVo(feeItems.get(i));
					feeItemVos.add(feeItemVo);
				}

				modelMap.put("feeItemVos", feeItemVos);
			} catch (BusinessException e) {
				e.printStackTrace();
				logger.error("查询数据失败", e);
			}
		}

		return new ModelAndView("/capital/credit_entrust_attom_add", modelMap);
	}

	/**
	 * 
	 * @Title: initTransferInformationView
	 * @Description: 信托计划转让信息查看
	 * @author liuwei
	 * @param tempUuid
	 *            信托计划临时id
	 * @param id
	 *            信托计划转让id
	 * @param creditEntrustId
	 *            信托计划id
	 * @return ModelAndView
	 */
	@RequestMapping("/initTransferInformationView")
	@UriKey(key = "com.zdsoft.finance.capital.initTransferInformationView")
	@Reference(resource = "com.zdsoft.finance.capital.initTransferInformationView", label = "信托计划转让信息查看")
	public ModelAndView initTransferInformationView(String tempUuid, String id, String creditEntrustId) {

		ModelMap modelMap = new ModelMap();
		modelMap.put("tempUuid", tempUuid);
		modelMap.put("creditEntrustId", creditEntrustId);

		if (ObjectHelper.isNotEmpty(id)) {// 判断信托计划id是否存在
			try {

				// 查询信托计划转让信息
				CreditEntrustAttom creditEntrustAttom = creditEntrustAttomService.findOne(id);
				CreditEntrustAttomVo creditEntrustAttomVo = new CreditEntrustAttomVo(creditEntrustAttom,
						new String[] {}, new String[] { "acceptType", "attomState" });
				modelMap.put("creditEntrustAttomVo", creditEntrustAttomVo);

				// 封装已有费用项
				List<CreditEntrustFeeItem> feeItems = creditEntrustFeeItemService
						.findByBusinessId(creditEntrustAttom.getId());
				List<CreditEntrustFeeItemVo> feeItemVos = new ArrayList<CreditEntrustFeeItemVo>();
				for (int i = 0; i < feeItems.size(); i++) {
					CreditEntrustFeeItemVo feeItemVo = new CreditEntrustFeeItemVo(feeItems.get(i));
					feeItemVos.add(feeItemVo);
				}

				modelMap.put("feeItemVos", feeItemVos);
			} catch (BusinessException e) {
				e.printStackTrace();
				logger.error("查询数据失败", e);
			}
		}

		return new ModelAndView("/capital/credit_entrust_attom_view", modelMap);
	}

	/**
	 * 
	 * @Title: initPrincipalInvestment
	 * @Description: 信托计划本金投入
	 * @author liuwei
	 * @param tempUuid
	 *            信托计划临时id
	 * @param id
	 *            信托计划本金投入id
	 * @param creditEntrustId
	 *            信托计划id
	 * @return ModelAndView
	 */
	@RequestMapping("/initPrincipalInvestment")
	@UriKey(key = "com.zdsoft.finance.capital.initPrincipalInvestment")
	@Reference(resource = "com.zdsoft.finance.capital.initPrincipalInvestment", label = "信托计划本金投入")
	public ModelAndView initPrincipalInvestment(String tempUuid, String id, String creditEntrustId) {

		ModelMap modelMap = new ModelMap();
		modelMap.put("tempUuid", tempUuid);
		modelMap.put("creditEntrustId", creditEntrustId);

		if (ObjectHelper.isNotEmpty(id)) { // 判断信托计划id是否存在
			try {

				// 查询信托计划本金投入信息
				CreditEntrustPrincipal creditEntrustPrincipal = creditEntrustPrincipalService.findOne(id);
				CreditEntrustPrincipalVo creditEntrustPrincipalVo = new CreditEntrustPrincipalVo(
						creditEntrustPrincipal);
				modelMap.put("creditEntrustPrincipalVo", creditEntrustPrincipalVo);

				// 封装已有费用项
				List<CreditEntrustFeeItem> feeItems = creditEntrustFeeItemService
						.findByBusinessId(creditEntrustPrincipal.getId());
				List<CreditEntrustFeeItemVo> feeItemVos = new ArrayList<CreditEntrustFeeItemVo>();
				for (int i = 0; i < feeItems.size(); i++) {
					CreditEntrustFeeItemVo feeItemVo = new CreditEntrustFeeItemVo(feeItems.get(i));
					feeItemVos.add(feeItemVo);
				}

				modelMap.put("feeItemVos", feeItemVos);
			} catch (BusinessException e) {
				e.printStackTrace();
				logger.error("查询数据失败", e);
			}
		} else {
			try {
				CreditEntrust creditEntrust = creditEntrustService.findOne(creditEntrustId);
				modelMap.put("contribution", creditEntrust.getCapitallist().getCapitalName());
			} catch (BusinessException e) {
				e.printStackTrace();
				logger.error("查询信托计划失败", e);
			}
		}

		return new ModelAndView("/capital/credit_entrust_principal_add", modelMap);
	}

	/**
	 * 
	 * @Title: initPrincipalInvestmentConfirm
	 * @Description: 信托计划本金投入确认
	 * @author liuwei
	 * @param tempUuid
	 *            信托计划临时id
	 * @param id
	 *            信托计划本金投入id
	 * @param creditEntrustId
	 *            信托计划id
	 * @return ModelAndView
	 */
	@RequestMapping("/initPrincipalInvestmentConfirm")
	@UriKey(key = "com.zdsoft.finance.capital.initPrincipalInvestmentConfirm")
	@Reference(resource = "com.zdsoft.finance.capital.initPrincipalInvestmentConfirm", label = "信托计划本金投入确认")
	public ModelAndView initPrincipalInvestmentConfirm(String tempUuid, String id, String creditEntrustId) {

		ModelMap modelMap = new ModelMap();
		modelMap.put("tempUuid", tempUuid);
		modelMap.put("creditEntrustId", creditEntrustId);

		if (ObjectHelper.isNotEmpty(id)) {// 判断信托计划id是否存在

			try {
				// 查询信托计划本金投入信息
				CreditEntrustPrincipal creditEntrustPrincipal = creditEntrustPrincipalService.findOne(id);
				CreditEntrustPrincipalVo creditEntrustPrincipalVo = new CreditEntrustPrincipalVo(
						creditEntrustPrincipal);
				modelMap.put("creditEntrustPrincipalVo", creditEntrustPrincipalVo);

				// 封装已有费用项
				List<CreditEntrustFeeItem> feeItems = creditEntrustFeeItemService
						.findByBusinessId(creditEntrustPrincipal.getId());
				List<CreditEntrustFeeItemVo> feeItemVos = new ArrayList<CreditEntrustFeeItemVo>();
				for (int i = 0; i < feeItems.size(); i++) {
					CreditEntrustFeeItemVo feeItemVo = new CreditEntrustFeeItemVo(feeItems.get(i));
					feeItemVos.add(feeItemVo);
				}

				modelMap.put("feeItemVos", feeItemVos);
			} catch (BusinessException e) {
				e.printStackTrace();
				logger.error("查询数据失败", e);
			}
		}

		return new ModelAndView("/capital/credit_entrust_principal_confirm", modelMap);
	}

	/**
	 * 
	 * @Title: initStandbyTrackingConfirm
	 * @Description: 进入备付金跟踪到账确认页面
	 * @author liuwei
	 * @param tempUuid
	 *            信托计划临时id
	 * @param id
	 *            信托计划备付金跟踪id
	 * @param creditEntrustId
	 *            信托计划id
	 * @return ModelAndView
	 */
	@RequestMapping("/initStandbyTrackingConfirm")
	@UriKey(key = "com.zdsoft.finance.capital.initStandbyTrackingConfirm")
	@Reference(resource = "com.zdsoft.finance.capital.initStandbyTrackingConfirm", label = "信托计划备付金跟踪到账确认")
	public ModelAndView initStandbyTrackingConfirm(String tempUuid, String id, String creditEntrustId) {

		ModelMap modelMap = new ModelMap();
		modelMap.put("tempUuid", tempUuid);
		modelMap.put("creditEntrustId", creditEntrustId);

		if (ObjectHelper.isNotEmpty(id)) { // 判断信托计划id是否存在
			try {

				// 查询信托计划备付金跟踪信息
				SpareCapital spareCapital = spareCapitalService.findOne(id);
				SpareCapitalVo spareCapitalVo = new SpareCapitalVo(spareCapital);
				modelMap.put("spareCapitalVo", spareCapitalVo);
			} catch (BusinessException e) {
				e.printStackTrace();
				logger.error("查询数据失败", e);
			}
		}

		return new ModelAndView("/capital/spare_capital_confirm", modelMap);
	}

	/**
	 * 
	 * @Title: initPrincipalInvestmentView
	 * @Description: 信托计划本金投入
	 * @author liuwei
	 * @param tempUuid
	 *            信托计划临时id
	 * @param id
	 *            信托计划本金投入id
	 * @param creditEntrustId
	 *            信托计划id
	 * @return ModelAndView
	 */
	@RequestMapping("/initPrincipalInvestmentView")
	@UriKey(key = "com.zdsoft.finance.capital.initPrincipalInvestmentView")
	@Reference(resource = "com.zdsoft.finance.capital.initPrincipalInvestmentView", label = "信托计划本金投入查看")
	public ModelAndView initPrincipalInvestmentView(String tempUuid, String id, String creditEntrustId) {

		ModelMap modelMap = new ModelMap();
		modelMap.put("tempUuid", tempUuid);
		modelMap.put("creditEntrustId", creditEntrustId);

		if (ObjectHelper.isNotEmpty(id)) { // 判断信托计划id是否存在
			try {

				// 查询信托计划本金投入信息
				CreditEntrustPrincipal creditEntrustPrincipal = creditEntrustPrincipalService.findOne(id);
				CreditEntrustPrincipalVo creditEntrustPrincipalVo = new CreditEntrustPrincipalVo(creditEntrustPrincipal,
						new String[] {}, new String[] { "contributionType", "payoutPeriod" });
				modelMap.put("creditEntrustPrincipalVo", creditEntrustPrincipalVo);

				// 封装已有费用项
				List<CreditEntrustFeeItem> feeItems = creditEntrustFeeItemService
						.findByBusinessId(creditEntrustPrincipal.getId());
				List<CreditEntrustFeeItemVo> feeItemVos = new ArrayList<CreditEntrustFeeItemVo>();
				for (int i = 0; i < feeItems.size(); i++) {
					CreditEntrustFeeItemVo feeItemVo = new CreditEntrustFeeItemVo(feeItems.get(i));
					feeItemVos.add(feeItemVo);
				}

				modelMap.put("feeItemVos", feeItemVos);
			} catch (BusinessException e) {
				e.printStackTrace();
				logger.error("查询数据失败", e);
			}
		}

		return new ModelAndView("/capital/credit_entrust_principal_view", modelMap);
	}

	/**
	 * 
	 * @Title: initRedemePrinci
	 * @Description: 信托计划本金赎回
	 * @author liuwei
	 * @param tempUuid
	 *            信托计划临时id
	 * @param id
	 *            信托计划本金赎回id
	 * @param creditEntrustId
	 *            信托计划id
	 * @return ModelAndView
	 */
	@RequestMapping("/initRedemePrinci")
	@UriKey(key = "com.zdsoft.finance.capital.initRedemePrinci")
	@Reference(resource = "com.zdsoft.finance.capital.initRedemePrinci", label = "信托计划本金赎回")
	public ModelAndView initRedemePrinci(String tempUuid, String id, String creditEntrustId) {

		ModelMap modelMap = new ModelMap();
		modelMap.put("tempUuid", tempUuid);
		modelMap.put("creditEntrustId", creditEntrustId);

		if (ObjectHelper.isNotEmpty(id)) { // 判断信托计划id是否存在
			try {

				// 查询信托计划本金赎回信息
				CreditEntrustRedemPrinci creditEntrustRedemPrinci = creditEntrustRedemPrinciService.findOne(id);
				CreditEntrustRedemPrinciVo creditEntrustRedemPrinciVo = new CreditEntrustRedemPrinciVo(
						creditEntrustRedemPrinci, new String[] {}, new String[] { "contributionType" });
				modelMap.put("creditEntrustRedemPrinciVo", creditEntrustRedemPrinciVo);

			} catch (BusinessException e) {
				e.printStackTrace();
				logger.error("查询数据失败", e);
			}
		}

		return new ModelAndView("/capital/credit_entrust_redemeprinci_add", modelMap);
	}

	/**
	 * 
	 * @Title: initRedemePrinciView
	 * @Description: 信托计划本金赎回查看
	 * @author liuwei
	 * @param tempUuid
	 *            信托计划临时id
	 * @param id
	 *            信托计划本金赎回id
	 * @param creditEntrustId
	 *            信托计划id
	 * @return ModelAndView
	 */
	@RequestMapping("/initRedemePrinciView")
	@UriKey(key = "com.zdsoft.finance.capital.initRedemePrinciView")
	@Reference(resource = "com.zdsoft.finance.capital.initRedemePrinciView", label = "信托计划本金赎回查看")
	public ModelAndView initRedemePrinciView(String tempUuid, String id, String creditEntrustId) {

		ModelMap modelMap = new ModelMap();
		modelMap.put("tempUuid", tempUuid);
		modelMap.put("creditEntrustId", creditEntrustId);

		if (ObjectHelper.isNotEmpty(id)) { // 判断信托计划id是否存在
			try {

				// 查询信托计划本金赎回信息
				CreditEntrustRedemPrinci creditEntrustRedemPrinci = creditEntrustRedemPrinciService.findOne(id);
				CreditEntrustRedemPrinciVo creditEntrustRedemPrinciVo = new CreditEntrustRedemPrinciVo(
						creditEntrustRedemPrinci, new String[] {}, new String[] { "contributionType" });
				modelMap.put("creditEntrustRedemPrinciVo", creditEntrustRedemPrinciVo);

			} catch (BusinessException e) {
				e.printStackTrace();
				logger.error("查询数据失败", e);
			}
		}

		return new ModelAndView("/capital/credit_entrust_redemeprinci_view", modelMap);
	}

	/**
	 * 
	 * @Title: getFeeItems
	 * @Description: 获取费用项并转换为json
	 * @author liuwei
	 * @param attribution
	 *            所属类别
	 * @param jsoncallback
	 * @return 费用项json
	 */
	@RequestMapping("/getFeeItems")
	@UriKey(key = "com.zdsoft.finance.capital.getFeeItems")
	@ResponseBody
	public String getFeeItems(String attribution, String jsoncallback) {

		List<FeeItem> feeItems = feeItemService.findByAttribution(attribution);
		List<FeeItemVo> feeItemVos = new ArrayList<FeeItemVo>();
		for (int i = 0; i < feeItems.size(); i++) {
			FeeItemVo feeItemVo = new FeeItemVo(feeItems.get(i));
			feeItemVos.add(feeItemVo);
		}
		ResponseMsg msg = new ResponseMsg();
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setMsg("费用项列表查询成功");
		msg.setRows(feeItemVos);
		msg.setTotal(Long.valueOf(feeItemVos.size()));

		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

	/**
	 * 
	 * @Title: getCreditFeeItems
	 * @Description: 获取费用项并转换为json
	 * @author liuwei
	 * @param businessId
	 *            业务标识id
	 * @param jsoncallback
	 * @return 费用项json
	 */
	@RequestMapping("/getCreditFeeItems")
	@UriKey(key = "com.zdsoft.finance.capital.getCreditFeeItems")
	@ResponseBody
	public String getCreditFeeItems(String businessId, String jsoncallback) {
		List<CreditEntrustFeeItem> feeItems = creditEntrustFeeItemService.findByBusinessId(businessId);
		return ObjectHelper.objectToJson(feeItems, jsoncallback);
	}

	/**
	 * 
	 * @Title: saveLoanCapital
	 * @Description: 保存专户贷方资金跟踪
	 * @author liuwei
	 * @param loanCapitalVo
	 *            专户贷方资金跟踪Vo
	 * @param feeItemCd
	 *            费用项cds
	 * @param feeItemNm
	 *            费用项nms
	 * @param feeAmount
	 *            费用项amount
	 * @return ResponseMsg处理消息
	 */
	@RequestMapping("/saveLoanCapital")
	@UriKey(key = "com.zdsoft.finance.capital.saveLoanCapital")
	@ResponseBody
	public ResponseMsg saveLoanCapital(LoanCapitalVo loanCapitalVo, String[] feeItemCd, String[] feeItemNm,
			BigDecimal[] feeAmount) {
		ResponseMsg msg = new ResponseMsg();
		// 转换vo
		LoanCapital loanCapital = loanCapitalVo.toPo();
		List<CreditEntrustFeeItem> feeItems = changeToFeeItemPos(feeItemCd, feeItemNm, feeAmount);
		// 临时存储费用
		loanCapital.setCreditEntrustFeeItems(feeItems);

		// 关联信托计划
		String creditEntrustId = loanCapitalVo.getCreditEntrustId();
		try {
			CreditEntrust creditEntrust = creditEntrustService.findOne(creditEntrustId);
			loanCapital.setCreditEntrust(creditEntrust);
		} catch (BusinessException e1) {
			e1.printStackTrace();
			logger.error("查询信托计划失败", e1);
		}
		try {
			// 持久对象
			loanCapital = loanCapitalService.saveOrUpdateLoanCapital(loanCapital);
			msg.setMsg("保存专户贷方资金跟踪成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("持久化专户贷方资金跟踪出现异常", e);
			msg.setMsg("保存专户贷方资金跟踪失败");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}

		// 返回信息
		return msg;
	}

	/**
	 * 
	 * @Title: saveCostTracking
	 * @Description: 保存应付费用跟踪
	 * @author liuwei
	 * @param creditCostTrackingVo
	 *            应付费用跟踪vo
	 * @param feeItemCd
	 *            费用项cds
	 * @param feeItemNm
	 *            费用项nms
	 * @param feeAmount
	 *            费用项amount
	 * @return
	 */
	@RequestMapping("/saveCostTracking")
	@UriKey(key = "com.zdsoft.finance.capital.saveCostTracking")
	@ResponseBody
	public ResponseMsg saveCostTracking(CreditCostTrackingVo creditCostTrackingVo, String[] feeItemCd,
			String[] feeItemNm, BigDecimal[] feeAmount) {
		ResponseMsg msg = new ResponseMsg();
		// 转换vo
		CreditCostTracking creditCostTracking = creditCostTrackingVo.toPo();
		List<CreditEntrustFeeItem> feeItems = changeToFeeItemPos(feeItemCd, feeItemNm, feeAmount);
		// 临时存储费用
		creditCostTracking.setCreditEntrustFeeItems(feeItems);

		// 关联信托计划
		String creditEntrustId = creditCostTrackingVo.getCreditEntrustId();
		try {
			CreditEntrust creditEntrust = creditEntrustService.findOne(creditEntrustId);
			creditCostTracking.setCreditEntrust(creditEntrust);
		} catch (BusinessException e1) {
			e1.printStackTrace();
			logger.error("查询信托计划失败", e1);
		}
		try {

			// 持久对象
			creditCostTracking = creditCostTrackingService.saveOrUpdateCostTracking(creditCostTracking);
			msg.setMsg("保存应付费用跟踪成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存应付费用跟踪出现异常", e);
			msg.setMsg("保存应付费用跟踪失败");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}

		// 返回信息
		return msg;
	}

	/**
	 * 
	 * @Title: saveEntrustDebit
	 * @Description: 保存借方资金跟踪
	 * @author liuwei
	 * @param creditEntrustDebitVo
	 *            借方资金跟踪vo
	 * @param feeItemCd
	 *            费用项cds
	 * @param feeItemNm
	 *            费用项nms
	 * @param feeAmount
	 *            费用项amount
	 * @return ResponseMsg处理消息
	 */
	@RequestMapping("/saveEntrustDebit")
	@UriKey(key = "com.zdsoft.finance.capital.saveEntrustDebit")
	@ResponseBody
	public ResponseMsg saveEntrustDebit(CreditEntrustDebitVo creditEntrustDebitVo, String[] feeItemCd,
			String[] feeItemNm, BigDecimal[] feeAmount) {
		ResponseMsg msg = new ResponseMsg();
		// 转换vo
		CreditEntrustDebit entrustDebit = creditEntrustDebitVo.toPo();
		List<CreditEntrustFeeItem> feeItems = changeToFeeItemPos(feeItemCd, feeItemNm, feeAmount);
		// 临时存储费用
		entrustDebit.setCreditEntrustFeeItems(feeItems);

		// 关联信托计划
		String creditEntrustId = creditEntrustDebitVo.getCreditEntrustId();
		try {

			// 查询信托计划信息
			CreditEntrust creditEntrust = creditEntrustService.findOne(creditEntrustId);
			entrustDebit.setCreditEntrust(creditEntrust);
		} catch (BusinessException e1) {
			e1.printStackTrace();
			logger.error("查询信托计划失败", e1);
		}

		try {

			// 持久对象
			entrustDebit = creditEntrustDebitService.saveOrUpdateEntrustDebit(entrustDebit);
			msg.setMsg("保存借方资金跟踪成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存借方资金跟踪出现异常", e);
			msg.setMsg("保存借方资金跟踪失败");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}

		// 返回信息
		return msg;
	}

	/**
	 * 
	 * @Title: saveSpareCapital
	 * @Description: 保存备付资金跟踪
	 * @author liuwei
	 * @param spareCapitalVo
	 *            备付资金跟踪vo
	 * @return ResponseMsg处理消息
	 */
	@RequestMapping("/saveSpareCapital")
	@UriKey(key = "com.zdsoft.finance.capital.saveSpareCapital")
	@ResponseBody
	public ResponseMsg saveSpareCapital(SpareCapitalVo spareCapitalVo) {
		ResponseMsg msg = new ResponseMsg();
		// 转换vo
		SpareCapital spareCapital = spareCapitalVo.toPo();

		// 关联信托计划
		String creditEntrustId = spareCapitalVo.getCreditEntrustId();
		try {
			CreditEntrust creditEntrust = creditEntrustService.findOne(creditEntrustId);
			spareCapital.setCreditEntrust(creditEntrust);
		} catch (BusinessException e1) {
			e1.printStackTrace();
			logger.error("查询信托计划失败", e1);
		}

		try {

			// 持久对象
			spareCapital = spareCapitalService.saveOrUpdateSpareCapital(spareCapital);
			msg.setMsg("保存备付资金跟踪成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存备付资金跟踪出现异常", e);
			msg.setMsg("保存备付资金跟踪失败");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}

		// 返回信息
		return msg;
	}

	/**
	 * 
	 * @Title: saveEntrustAttom
	 * @Description: 保存信托计划转让
	 * @author liuwei
	 * @param entrustAttomVo
	 *            信托计划转让vo
	 * @return ResponseMsg处理消息
	 */
	@RequestMapping("/saveEntrustAttom")
	@UriKey(key = "com.zdsoft.finance.capital.saveEntrustAttom")
	@ResponseBody
	public ResponseMsg saveEntrustAttom(CreditEntrustAttomVo entrustAttomVo) {
		ResponseMsg msg = new ResponseMsg();
		// 转换vo
		CreditEntrustAttom entrustAttom = entrustAttomVo.toPo();

		// 关联信托计划
		String creditEntrustId = entrustAttomVo.getCreditEntrustId();
		try {
			// 查询信托计划信息
			CreditEntrust creditEntrust = creditEntrustService.findOne(creditEntrustId);
			entrustAttom.setCreditEntrust(creditEntrust);
		} catch (BusinessException e1) {
			e1.printStackTrace();
			logger.error("查询信托计划失败", e1);
		}
		try {

			// 持久对象
			entrustAttom = creditEntrustAttomService.saveOrUpdateEntrustAttom(entrustAttom);
			msg.setMsg("保存信托计划转让成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("持久化信托计划转让出现异常", e);
			msg.setMsg("保存信托计划转让失败");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}

		// 返回信息
		return msg;
	}

	/**
	 * 
	 * @Title: saveEntrustPrincipal
	 * @Description: 保存信托计划本金投入
	 * @author liuwei
	 * @param principalVo
	 *            信托计划本金投入vo
	 * @return ResponseMsg处理消息
	 */
	@RequestMapping("/saveEntrustPrincipal")
	@UriKey(key = "com.zdsoft.finance.capital.saveEntrustPrincipal")
	@ResponseBody
	public ResponseMsg saveEntrustPrincipal(CreditEntrustPrincipalVo principalVo) {
		ResponseMsg msg = new ResponseMsg();
		// 转换vo
		CreditEntrustPrincipal principal = principalVo.toPo();

		// 关联信托计划
		String creditEntrustId = principalVo.getCreditEntrustId();
		try {
			// 查询信托计划信息
			CreditEntrust creditEntrust = creditEntrustService.findOne(creditEntrustId);
			principal.setCreditEntrust(creditEntrust);
		} catch (BusinessException e1) {
			e1.printStackTrace();
			logger.error("查询信托计划失败", e1);
		}
		try {

			// 持久对象
			principal = creditEntrustPrincipalService.saveOrUpdateEntrustPrincipal(principal);
			msg.setMsg("保存信托计划本金投入成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("持久化信托计划本金投入出现异常", e);
			msg.setMsg("保存信托计划本金投入失败");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}

		// 返回信息
		return msg;
	}

	/**
	 * 
	 * @Title: confirmEntrustPrincipal
	 * @Description: 到账确认信托计划本金投入
	 * @author liuwei
	 * @param principalVo
	 *            信托计划本金投入vo
	 * @return ResponseMsg处理消息
	 */
	@RequestMapping("/confirmEntrustPrincipal")
	@UriKey(key = "com.zdsoft.finance.capital.confirmEntrustPrincipal")
	@ResponseBody
	public ResponseMsg confirmEntrustPrincipal(CreditEntrustPrincipalVo principalVo) {
		ResponseMsg msg = new ResponseMsg();
		// 转换vo
		CreditEntrustPrincipal principal = principalVo.toPo();

		// 关联信托计划
		String creditEntrustId = principalVo.getCreditEntrustId();
		try {
			CreditEntrust creditEntrust = creditEntrustService.findOne(creditEntrustId);
			principal.setCreditEntrust(creditEntrust);
		} catch (BusinessException e1) {
			e1.printStackTrace();
			logger.error("查询信托计划失败", e1);
		}
		try {

			// 持久对象
			principal.setStatus(StatusNm.ARRIVAL.value);
			principal.setActualDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));
			principal = creditEntrustPrincipalService.saveOrUpdateEntrustPrincipal(principal);
			msg.setMsg("确认到账成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("确认到账出现异常", e);
			msg.setMsg("确认到账失败");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}

		// 返回信息
		return msg;
	}

	/**
	 * 
	 * @Title: confirmSpareCapital
	 * @Description: 到账确认备付金跟踪
	 * @author liuwei
	 * @param spareCapitalVo
	 *            备付金跟踪vo
	 * @return ResponseMsg处理消息
	 */
	@RequestMapping("/confirmSpareCapital")
	@UriKey(key = "com.zdsoft.finance.capital.confirmSpareCapital")
	@ResponseBody
	public ResponseMsg confirmSpareCapital(SpareCapitalVo spareCapitalVo) {
		ResponseMsg msg = new ResponseMsg();
		// 转换vo
		SpareCapital spareCapital = spareCapitalVo.toPo();

		// 关联信托计划
		String creditEntrustId = spareCapitalVo.getCreditEntrustId();
		try {

			// 查询信托计划信息
			CreditEntrust creditEntrust = creditEntrustService.findOne(creditEntrustId);
			spareCapital.setCreditEntrust(creditEntrust);
		} catch (BusinessException e1) {
			e1.printStackTrace();
			logger.error("查询信托计划失败", e1);
		}
		try {

			// 持久对象
			spareCapital.setStatus(StatusNm.ARRIVAL.value);
			spareCapital = spareCapitalService.saveOrUpdateSpareCapitalAndMatch(spareCapital);
			msg.setMsg("确认到账成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("确认到账出现异常", e);
			msg.setMsg("确认到账失败");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}

		// 返回信息
		return msg;
	}

	/**
	 * 
	 * @Title: saveRedemPrinci
	 * @Description: 保存本金赎回信息
	 * @author liuwei
	 * @param principalVo
	 *            本金赎回信息Vo
	 * @return ResponseMsg处理消息
	 */
	@RequestMapping("/saveRedemPrinci")
	@UriKey(key = "com.zdsoft.finance.capital.saveRedemPrinci")
	@ResponseBody
	public ResponseMsg saveRedemPrinci(CreditEntrustRedemPrinciVo principalVo) {
		ResponseMsg msg = new ResponseMsg();
		// 转换vo
		CreditEntrustRedemPrinci principal = principalVo.toPo();

		// 关联信托计划
		String creditEntrustId = principalVo.getCreditEntrustId();
		try {
			CreditEntrust creditEntrust = creditEntrustService.findOne(creditEntrustId);
			principal.setCreditEntrust(creditEntrust);
		} catch (BusinessException e1) {
			e1.printStackTrace();
			logger.error("查询信托计划失败", e1);
		}
		try {
			// 持久对象
			principal = creditEntrustRedemPrinciService.saveOrUpdateEntrustRedemPrinci(principal);
			msg.setMsg("保存本金赎回成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("持久化保存本金赎回成功出现异常", e);
			msg.setMsg("保存保存本金赎回失败");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		// 返回信息
		return msg;
	}

	/**
	 * 
	 * @Title: saveCreditEntrust
	 * @Description: 保存整体信托计划
	 * @author liuwei
	 * @param creditEntrustVo
	 *            信托计划Vo
	 * @return ResponseMsg处理消息
	 */
	@RequestMapping("/saveCreditEntrust")
	@UriKey(key = "com.zdsoft.finance.capital.saveCreditEntrust")
	@ResponseBody
	public ResponseMsg saveCreditEntrust(CreditEntrustVo creditEntrustVo) {

		ResponseMsg msg = new ResponseMsg();

		// 转换PO
		CreditEntrust creditEntrust = creditEntrustVo.toPo();

		// 保存资方信息
		String capitalistId = creditEntrustVo.getCapitalistId();
		if (ObjectHelper.isNotEmpty(capitalistId)) {
			try {
				Capitalist capitalist = capitalistService.findOne(capitalistId);
				creditEntrust.setCapitallist(capitalist);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		}

		// 执行保存
		try {
			creditEntrustService.saveCreditEntrustAndOthers(creditEntrust);
			msg.setMsg("保存信托计划成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setMsg("保存信托计划失败");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		return msg;
	}

	/**
	 * 
	 * @Title: updateCreditEntrust
	 * @Description: 修改整体信托计划
	 * @author liuwei
	 * @param creditEntrustVo
	 *            信托计划vo
	 * @return ResponseMsg消息处理
	 */
	@RequestMapping("/updateCreditEntrust")
	@UriKey(key = "com.zdsoft.finance.capital.updateCreditEntrust")
	@ResponseBody
	public ResponseMsg updateCreditEntrust(CreditEntrustVo creditEntrustVo) {

		ResponseMsg msg = new ResponseMsg();

		CreditEntrust creditEntrust = creditEntrustVo.toPo();

		// 执行修改
		try {
			creditEntrustService.updateCreditEntrust(creditEntrust);
			msg.setMsg("保存信托计划成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setMsg("保存信托计划失败");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		return msg;
	}

	/**
	 * 
	 * @Title: oneKeyFundMatching
	 * @Description: 一键资金匹配
	 * @author liuwei
	 * @param creditEntrustId
	 *            信托计划id
	 * @return ResponseMsg处理信息
	 */
	@RequestMapping("/oneKeyFundMatching")
	@UriKey(key = "com.zdsoft.finance.capital.oneKeyFundMatching")
	@ResponseBody
	public ResponseMsg oneKeyFundMatching(String creditEntrustId) {

		ResponseMsg msg = new ResponseMsg();
		try {
			// 查询已分配额度未分配资金的额度申请
			creditEntrustService.oneKeyFundMatching(creditEntrustId, "YWDM0051057");
			msg.setMsg("一键资金匹配成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setMsg("一键资金匹配失败");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		return msg;
	}

	/**
	 * 
	 * @Title: getBankLimit
	 * @Description: 银行选择
	 * @author liuwei
	 * @param jsoncallback
	 * @return 银行json数据
	 */
	@RequestMapping("/getBankLimit")
	@UriKey(key = "com.zdsoft.finance.capital.getBankLimit")
	@ResponseBody
	public String getBankLimit(String jsoncallback) {
		Map<String, Object> returnMap = new HashMap<String, Object>();

		List<Bank> banks = bankService.findAllBank();
		List<Map<String, Object>> bankMap = new ArrayList<Map<String, Object>>();
		if (ObjectHelper.isNotEmpty(banks)) {
			for (int i = 0; i < banks.size(); i++) {
				Map<String, Object> objMap = new HashMap<String, Object>();
				objMap.put("py", banks.get(i).getBankName());
				objMap.put("name", banks.get(i).getBankName());
				objMap.put("code", banks.get(i).getBankCode());

				bankMap.add(objMap);
			}
		}
		returnMap.put("data", bankMap);

		return ObjectHelper.objectToJson(returnMap, jsoncallback);
	}

	@RequestMapping("/getPrincipalAmounts")
	@UriKey(key = "com.zdsoft.finance.capital.getPrincipalAmounts")
	@ResponseBody
	public String getPrincipalAmounts(String tempUuid, String creditEntrustId, String jsoncallback) {
		List<CreditEntrustPrincipal> creditEntrustPrincipals = null;
		if (ObjectHelper.isNotEmpty(tempUuid)) {
			creditEntrustPrincipals = creditEntrustPrincipalService.findByTempUuid(null);
		} else if (ObjectHelper.isNotEmpty(creditEntrustId)) {
			Map<String, Object> conditions = new HashMap<String, Object>();
			conditions.put("creditEntrustId", creditEntrustId);
			creditEntrustPrincipals = creditEntrustPrincipalService.findByConditions(conditions);
		}

		BigDecimal totalAmount = BigDecimal.ZERO;
		if (ObjectHelper.isNotEmpty(creditEntrustPrincipals)) {
			for (int i = 0; i < creditEntrustPrincipals.size(); i++) {
				totalAmount = totalAmount.add(creditEntrustPrincipals.get(i).getPrincipalAmount());
			}
		}

		return ObjectHelper.objectToJson(totalAmount, jsoncallback);

	}

	/**
	 * 
	 * @Title: changeToFeeItemPos
	 * @Description: 费用Vo集合转换为Po集合
	 * @author liuwei
	 * @param feeItemCds
	 *            费用cds
	 * @param feeItemNms
	 *            费用nms
	 * @param feeAmounts
	 *            费用amounts
	 * @return 费用项集合
	 */
	private List<CreditEntrustFeeItem> changeToFeeItemPos(String[] feeItemCds, String[] feeItemNms,
			BigDecimal[] feeAmounts) {
		List<CreditEntrustFeeItem> feeItems = new ArrayList<CreditEntrustFeeItem>();
		for (int i = 0; i < feeItemCds.length; i++) {
			CreditEntrustFeeItem feeItem = new CreditEntrustFeeItem();
			feeItem.setFeeItemCd(feeItemCds[i]);
			feeItem.setFeeItemNm(feeItemNms[i]);
			feeItem.setFeeAmount(feeAmounts[i]);
			feeItems.add(feeItem);
		}
		return feeItems;
	}
}
