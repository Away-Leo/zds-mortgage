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
 * 信托计划管理controller
 * 
 * @createTime:2017年1月10日
 * @author liuwei
 * @version 1.0
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
	CED CED;

	/**
	 * 资金管理页面
	 * 
	 * @return 资金管理页面
	 */
	@RequestMapping("/initFundsManagement")
	@UriKey(key = "com.zdsoft.finance.capital.initFundsManagement")
	@Menu(resource = "com.zdsoft.finance.capital.initFundsManagement", label = "资金管理", group = "capital", order = 1)
	public ModelAndView initFundsManagement() {
		return new ModelAndView("/capital/credit_capitalist_list");
	}

	/**
	 * 信托计划管理初始化
	 * 
	 * @return 信托计划管理页面
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
	 * 查询列表
	 * 
	 * @param request
	 *            请求
	 * @param jsoncallback
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/getCreditEntrusts")
	@UriKey(key = "com.zdsoft.finance.capital.getCreditEntrusts")
	@ResponseBody
	public String getCreditEntrusts(HttpServletRequest request, String jsoncallback, PageRequest pageable) {

		String capitallist_id = request.getParameter("capitallist_id");
		String creditEntrustName = request.getParameter("creditEntrustName");
		String state = request.getParameter("state");

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
			BigDecimal residualDistribution = inflowsubtotal.subtract(outflowsubtotal)
					.subtract(new BigDecimal(map.get("amount31").toString()));

			map.put("amount31", residualDistribution);

			Date amount44 = (Date) map.get("amount44");
			map.put("amount44", DateHelper.dateToString(amount44, DateHelper.DATE_SHORT_SIMPLE_FORMAT_WITHMINUTE));
		}

		return oldValues;
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
	@RequestMapping("/getCreditEntrustList")
	@UriKey(key = "com.zdsoft.finance.capital.getCreditEntrustList")
	@ResponseBody
	public String getCreditEntrustList(HttpServletRequest request, String jsoncallback, PageRequest pageable) {

		Map<String, Object> conditions = new HashMap<String, Object>();
		List<CreditEntrust> creditEntrustList = creditEntrustService.findByConditions(conditions);
		List<CreditEntrustVo> creditEntrustVos = new ArrayList<CreditEntrustVo>();

		for (CreditEntrust creditEntrust : creditEntrustList) {
			CreditEntrustVo vo = new CreditEntrustVo(creditEntrust);
			creditEntrustVos.add(vo);
		}
		return ObjectHelper.objectToJson(creditEntrustVos, jsoncallback);
	}

	/**
	 * 信托计划详情页面
	 * 
	 * @return
	 */
	@RequestMapping("/initCreditEntrustAdd")
	@UriKey(key = "com.zdsoft.finance.capital.initCreditEntrustAdd")
	@Reference(resource = "com.zdsoft.finance.capital.initCreditEntrustAdd", label = "信托计划详情")
	public ModelAndView initCreditEntrustAdd(String capitalistId, String id) {
		ModelMap modelMap = new ModelMap();
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
			modelMap.put("cooperatorName", capitalist.getCooperatorName());

			return new ModelAndView("/capital/credit_entrust_add", modelMap);
		} else {
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
	 * 出资信息
	 * 
	 * @param pageable
	 *            分页信息
	 * @param request
	 * @param jsoncallback
	 * @return
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

		for (CreditEntrustPrincipal principal : principalPage.getRecords()) {
			CreditEntrustPrincipalVo principalVo = new CreditEntrustPrincipalVo(principal);
			principalVos.add(principalVo);
		}

		// 判断初始本金
		String tempType = "";
		if (pageable.getPage() == 1) { // 第一页才进行处理
			for (int i = 0; i < principalVos.size(); i++) {
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
	 * 信托计划本金投入
	 * 
	 * @param pageable
	 * @param request
	 * @param jsoncallback
	 * @return
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

		for (CreditEntrustPrincipal principal : principalPage.getRecords()) {
			CreditEntrustPrincipalVo principalVo = new CreditEntrustPrincipalVo(principal, new String[] {},
					new String[] { "contributionType" });
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
	 * 信托专户贷方资金（非本金）跟踪
	 * 
	 * @param pageable
	 * @param request
	 * @param jsoncallback
	 * @return
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

		for (LoanCapital loanCapital : loanCapitalPage.getRecords()) {
			LoanCapitalVo loanCapitalVo = new LoanCapitalVo(loanCapital, new String[] {},
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
	 * 应付费用跟踪
	 * 
	 * @param pageable
	 * @param request
	 * @param jsoncallback
	 * @return
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

		for (CreditCostTracking costTracking : costTrackingPage.getRecords()) {
			CreditCostTrackingVo loanCapitalVo = new CreditCostTrackingVo(costTracking, new String[] {},
					new String[] { "expenditureType" });
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
	 * 借方资金跟踪
	 * 
	 * @param pageable
	 * @param request
	 * @param jsoncallback
	 * @return
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

		for (CreditEntrustDebit debit : debitTrackingPage.getRecords()) {
			CreditEntrustDebitVo debitVo = new CreditEntrustDebitVo(debit, new String[] {},
					new String[] { "debitType", "debtorType", "capitalState" });
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
	 * 备付资金跟踪
	 * 
	 * @param pageable
	 * @param request
	 * @param jsoncallback
	 * @return
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

		for (SpareCapital spareCapital : spareCapitalPage.getRecords()) {
			SpareCapitalVo spareCapitalVo = new SpareCapitalVo(spareCapital, new String[] {},
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
	 * 备付资金跟踪
	 * 
	 * @param pageable
	 * @param request
	 * @param jsoncallback
	 * @return
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

		for (CreditEntrustAttom creditEntrustAttom : attomPage.getRecords()) {
			CreditEntrustAttomVo attomVo = new CreditEntrustAttomVo(creditEntrustAttom, new String[] {},
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
	 * 本金赎回list
	 * 
	 * @param pageable
	 * @param request
	 * @param jsoncallback
	 * @return
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

		for (CreditEntrustRedemPrinci creditEntrustRedemPrinci : attomPage.getRecords()) {
			CreditEntrustRedemPrinciVo attomVo = new CreditEntrustRedemPrinciVo(creditEntrustRedemPrinci,
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
	 * 计算本金规模
	 * 
	 * @param tempUuid
	 *            临时id
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/countPrincipaScale")
	@UriKey(key = "com.zdsoft.finance.capital.countPrincipaScale")
	@ResponseBody
	public String countPrincipaScale(String tempUuid, String creditEntrustId, String jsoncallback) {

		BigDecimal totalAmount = BigDecimal.ZERO;
		List<CreditEntrustPrincipal> principals = new ArrayList<CreditEntrustPrincipal>();
		List<CreditEntrustRedemPrinci> redemPrincis = new ArrayList<CreditEntrustRedemPrinci>();

		if (ObjectHelper.isNotEmpty(tempUuid)) {
			principals = creditEntrustPrincipalService.findByTempUuid(tempUuid);
			redemPrincis = creditEntrustRedemPrinciService.findByTempUuid(tempUuid);
		} else {
			Map<String, Object> conditions = new HashMap<String, Object>();
			conditions.put("creditEntrustId", creditEntrustId);
			principals = creditEntrustPrincipalService.findByConditions(conditions);
			redemPrincis = creditEntrustRedemPrinciService.findByConditions(conditions);
		}

		// 计算总金额
		if (ObjectHelper.isNotEmpty(principals)) {
			for (CreditEntrustPrincipal creditEntrustPrincipal : principals) {
				totalAmount = totalAmount.add(creditEntrustPrincipal.getPrincipalAmount() == null ? BigDecimal.ZERO
						: creditEntrustPrincipal.getPrincipalAmount());
			}
		}
		if (ObjectHelper.isNotEmpty(redemPrincis)) {
			for (CreditEntrustRedemPrinci creditEntrustRedemPrinci : redemPrincis) {
				totalAmount = totalAmount.subtract(creditEntrustRedemPrinci.getRedemptionAmount() == null
						? BigDecimal.ZERO : creditEntrustRedemPrinci.getRedemptionAmount());
			}
		}

		return ObjectHelper.objectToJson(totalAmount, jsoncallback);
	}

	/**
	 * 专户贷方资金（非本金）跟踪
	 * 
	 * @return
	 */
	@RequestMapping("/initNonPrincipalTracking")
	@UriKey(key = "com.zdsoft.finance.capital.initNonPrincipalTracking")
	@Reference(resource = "com.zdsoft.finance.capital.initNonPrincipalTracking", label = "专户贷方资金（非本金）跟踪")
	public ModelAndView initNonPrincipalTracking(String tempUuid, String id, String creditEntrustId) {
		ModelMap modelMap = new ModelMap();
		modelMap.put("tempUuid", tempUuid);
		modelMap.put("creditEntrustId", creditEntrustId);
		if (ObjectHelper.isNotEmpty(id)) {
			try {
				LoanCapital loanCapital = loanCapitalService.findOne(id);
				LoanCapitalVo loanCapitalVo = new LoanCapitalVo(loanCapital);
				modelMap.put("loanCapitalVo", loanCapitalVo);

				// 封装已有费用项
				List<CreditEntrustFeeItem> feeItems = creditEntrustFeeItemService.findByBusinessId(loanCapital.getId());
				List<CreditEntrustFeeItemVo> feeItemVos = new ArrayList<CreditEntrustFeeItemVo>();
				for (CreditEntrustFeeItem feeItem : feeItems) {
					CreditEntrustFeeItemVo feeItemVo = new CreditEntrustFeeItemVo(feeItem);
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
	 * 专户贷方资金（非本金）跟踪
	 * 
	 * @return
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
				for (CreditEntrustFeeItem feeItem : feeItems) {
					CreditEntrustFeeItemVo feeItemVo = new CreditEntrustFeeItemVo(feeItem);
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
	 * 信托计划应付费用跟踪
	 * 
	 * @return
	 */
	@RequestMapping("/initCostTracking")
	@UriKey(key = "com.zdsoft.finance.capital.initCostTracking")
	@Reference(resource = "com.zdsoft.finance.capital.initCostTracking", label = "信托计划应付费用跟踪")
	public ModelAndView initCostTracking(String tempUuid, String id, String creditEntrustId) {
		ModelMap modelMap = new ModelMap();
		modelMap.put("tempUuid", tempUuid);
		modelMap.put("creditEntrustId", creditEntrustId);
		if (ObjectHelper.isNotEmpty(id)) {
			try {
				CreditCostTracking costTracking = creditCostTrackingService.findOne(id);
				CreditCostTrackingVo creditCostTrackingVo = new CreditCostTrackingVo(costTracking);
				modelMap.put("creditCostTrackingVo", creditCostTrackingVo);

				// 封装已有费用项
				List<CreditEntrustFeeItem> feeItems = creditEntrustFeeItemService
						.findByBusinessId(costTracking.getId());
				List<CreditEntrustFeeItemVo> feeItemVos = new ArrayList<CreditEntrustFeeItemVo>();
				for (CreditEntrustFeeItem feeItem : feeItems) {
					CreditEntrustFeeItemVo feeItemVo = new CreditEntrustFeeItemVo(feeItem);
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
	 * 信托计划应付费用跟踪查看
	 * 
	 * @return
	 */
	@RequestMapping("/initCostTrackingView")
	@UriKey(key = "com.zdsoft.finance.capital.initCostTrackingView")
	@Reference(resource = "com.zdsoft.finance.capital.initCostTrackingView", label = "信托计划应付费用跟踪查看")
	public ModelAndView initCostTrackingView(String tempUuid, String id, String creditEntrustId) {
		ModelMap modelMap = new ModelMap();
		modelMap.put("tempUuid", tempUuid);
		modelMap.put("creditEntrustId", creditEntrustId);
		if (ObjectHelper.isNotEmpty(id)) {
			try {
				CreditCostTracking costTracking = creditCostTrackingService.findOne(id);
				CreditCostTrackingVo creditCostTrackingVo = new CreditCostTrackingVo(costTracking, new String[] {},
						new String[] { "expenditureType" });
				modelMap.put("creditCostTrackingVo", creditCostTrackingVo);

				// 封装已有费用项
				List<CreditEntrustFeeItem> feeItems = creditEntrustFeeItemService
						.findByBusinessId(costTracking.getId());
				List<CreditEntrustFeeItemVo> feeItemVos = new ArrayList<CreditEntrustFeeItemVo>();
				for (CreditEntrustFeeItem feeItem : feeItems) {
					CreditEntrustFeeItemVo feeItemVo = new CreditEntrustFeeItemVo(feeItem);
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
	 * 信托计划借方资金（非放款）跟踪
	 * 
	 * @return
	 */
	@RequestMapping("/initTrackingTrustPlan")
	@UriKey(key = "com.zdsoft.finance.capital.initTrackingTrustPlan")
	@Reference(resource = "com.zdsoft.finance.capital.initTrackingTrustPlan", label = "信托计划借方资金（非放款）跟踪")
	public ModelAndView initTrackingTrustPlan(String tempUuid, String id, String creditEntrustId) {
		ModelMap modelMap = new ModelMap();
		modelMap.put("tempUuid", tempUuid);
		modelMap.put("creditEntrustId", creditEntrustId);
		if (ObjectHelper.isNotEmpty(id)) {
			try {
				CreditEntrustDebit creditEntrustDebit = creditEntrustDebitService.findOne(id);
				CreditEntrustDebitVo creditEntrustDebitVo = new CreditEntrustDebitVo(creditEntrustDebit);
				modelMap.put("creditEntrustDebitVo", creditEntrustDebitVo);

				// 封装已有费用项
				List<CreditEntrustFeeItem> feeItems = creditEntrustFeeItemService
						.findByBusinessId(creditEntrustDebit.getId());
				List<CreditEntrustFeeItemVo> feeItemVos = new ArrayList<CreditEntrustFeeItemVo>();
				for (CreditEntrustFeeItem feeItem : feeItems) {
					CreditEntrustFeeItemVo feeItemVo = new CreditEntrustFeeItemVo(feeItem);
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
	 * 信托计划借方资金（非放款）跟踪查看
	 * 
	 * @return
	 */
	@RequestMapping("/initTrackingTrustPlanView")
	@UriKey(key = "com.zdsoft.finance.capital.initTrackingTrustPlanView")
	@Reference(resource = "com.zdsoft.finance.capital.initTrackingTrustPlanView", label = "信托计划借方资金（非放款）跟踪查看")
	public ModelAndView initTrackingTrustPlanView(String tempUuid, String id, String creditEntrustId) {
		ModelMap modelMap = new ModelMap();
		modelMap.put("tempUuid", tempUuid);
		modelMap.put("creditEntrustId", creditEntrustId);
		if (ObjectHelper.isNotEmpty(id)) {
			try {
				CreditEntrustDebit creditEntrustDebit = creditEntrustDebitService.findOne(id);
				CreditEntrustDebitVo creditEntrustDebitVo = new CreditEntrustDebitVo(creditEntrustDebit,
						new String[] {}, new String[] { "debitType", "debtorType", "capitalState" });
				modelMap.put("creditEntrustDebitVo", creditEntrustDebitVo);

				// 封装已有费用项
				List<CreditEntrustFeeItem> feeItems = creditEntrustFeeItemService
						.findByBusinessId(creditEntrustDebit.getId());
				List<CreditEntrustFeeItemVo> feeItemVos = new ArrayList<CreditEntrustFeeItemVo>();
				for (CreditEntrustFeeItem feeItem : feeItems) {
					CreditEntrustFeeItemVo feeItemVo = new CreditEntrustFeeItemVo(feeItem);
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
				for (CreditEntrustFeeItem feeItem : feeItems) {
					CreditEntrustFeeItemVo feeItemVo = new CreditEntrustFeeItemVo(feeItem);
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
	 * 备付资金跟踪
	 * 
	 * @return
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
				SpareCapital spareCapital = spareCapitalService.findOne(id);
				SpareCapitalVo spareCapitalVo = new SpareCapitalVo(spareCapital, new String[] {},
						new String[] { "operationType" });
				modelMap.put("spareCapitalVo", spareCapitalVo);

				// 封装已有费用项
				List<CreditEntrustFeeItem> feeItems = creditEntrustFeeItemService
						.findByBusinessId(spareCapital.getId());
				List<CreditEntrustFeeItemVo> feeItemVos = new ArrayList<CreditEntrustFeeItemVo>();
				for (CreditEntrustFeeItem feeItem : feeItems) {
					CreditEntrustFeeItemVo feeItemVo = new CreditEntrustFeeItemVo(feeItem);
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
	 * 信托计划转让信息
	 * 
	 * @return
	 */
	@RequestMapping("/initTransferInformation")
	@UriKey(key = "com.zdsoft.finance.capital.initTransferInformation")
	@Reference(resource = "com.zdsoft.finance.capital.initTransferInformation", label = "信托计划转让信息")
	public ModelAndView initTransferInformation(String tempUuid, String id, String creditEntrustId) {
		ModelMap modelMap = new ModelMap();
		modelMap.put("tempUuid", tempUuid);
		modelMap.put("creditEntrustId", creditEntrustId);
		if (ObjectHelper.isNotEmpty(id)) {
			try {
				CreditEntrustAttom creditEntrustAttom = creditEntrustAttomService.findOne(id);
				CreditEntrustAttomVo creditEntrustAttomVo = new CreditEntrustAttomVo(creditEntrustAttom);
				modelMap.put("creditEntrustAttomVo", creditEntrustAttomVo);

				// 封装已有费用项
				List<CreditEntrustFeeItem> feeItems = creditEntrustFeeItemService
						.findByBusinessId(creditEntrustAttom.getId());
				List<CreditEntrustFeeItemVo> feeItemVos = new ArrayList<CreditEntrustFeeItemVo>();
				for (CreditEntrustFeeItem feeItem : feeItems) {
					CreditEntrustFeeItemVo feeItemVo = new CreditEntrustFeeItemVo(feeItem);
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
	 * 信托计划转让信息查看
	 * 
	 * @return
	 */
	@RequestMapping("/initTransferInformationView")
	@UriKey(key = "com.zdsoft.finance.capital.initTransferInformationView")
	@Reference(resource = "com.zdsoft.finance.capital.initTransferInformationView", label = "信托计划转让信息查看")
	public ModelAndView initTransferInformationView(String tempUuid, String id, String creditEntrustId) {
		ModelMap modelMap = new ModelMap();
		modelMap.put("tempUuid", tempUuid);
		modelMap.put("creditEntrustId", creditEntrustId);
		if (ObjectHelper.isNotEmpty(id)) {
			try {
				CreditEntrustAttom creditEntrustAttom = creditEntrustAttomService.findOne(id);
				CreditEntrustAttomVo creditEntrustAttomVo = new CreditEntrustAttomVo(creditEntrustAttom,
						new String[] {}, new String[] { "acceptType", "attomState" });
				modelMap.put("creditEntrustAttomVo", creditEntrustAttomVo);

				// 封装已有费用项
				List<CreditEntrustFeeItem> feeItems = creditEntrustFeeItemService
						.findByBusinessId(creditEntrustAttom.getId());
				List<CreditEntrustFeeItemVo> feeItemVos = new ArrayList<CreditEntrustFeeItemVo>();
				for (CreditEntrustFeeItem feeItem : feeItems) {
					CreditEntrustFeeItemVo feeItemVo = new CreditEntrustFeeItemVo(feeItem);
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
	 * 信托计划本金投入
	 * 
	 * @return
	 */
	@RequestMapping("/initPrincipalInvestment")
	@UriKey(key = "com.zdsoft.finance.capital.initPrincipalInvestment")
	@Reference(resource = "com.zdsoft.finance.capital.initPrincipalInvestment", label = "信托计划本金投入")
	public ModelAndView initPrincipalInvestment(String tempUuid, String id, String creditEntrustId) {
		ModelMap modelMap = new ModelMap();
		modelMap.put("tempUuid", tempUuid);
		modelMap.put("creditEntrustId", creditEntrustId);
		if (ObjectHelper.isNotEmpty(id)) {
			try {
				CreditEntrustPrincipal creditEntrustPrincipal = creditEntrustPrincipalService.findOne(id);
				CreditEntrustPrincipalVo creditEntrustPrincipalVo = new CreditEntrustPrincipalVo(
						creditEntrustPrincipal);
				modelMap.put("creditEntrustPrincipalVo", creditEntrustPrincipalVo);

				// 封装已有费用项
				List<CreditEntrustFeeItem> feeItems = creditEntrustFeeItemService
						.findByBusinessId(creditEntrustPrincipal.getId());
				List<CreditEntrustFeeItemVo> feeItemVos = new ArrayList<CreditEntrustFeeItemVo>();
				for (CreditEntrustFeeItem feeItem : feeItems) {
					CreditEntrustFeeItemVo feeItemVo = new CreditEntrustFeeItemVo(feeItem);
					feeItemVos.add(feeItemVo);
				}

				modelMap.put("feeItemVos", feeItemVos);
			} catch (BusinessException e) {
				e.printStackTrace();
				logger.error("查询数据失败", e);
			}
		}

		return new ModelAndView("/capital/credit_entrust_principal_add", modelMap);
	}

	/**
	 * 信托计划本金投入确认
	 * 
	 * @return
	 */
	@RequestMapping("/initPrincipalInvestmentConfirm")
	@UriKey(key = "com.zdsoft.finance.capital.initPrincipalInvestmentConfirm")
	@Reference(resource = "com.zdsoft.finance.capital.initPrincipalInvestmentConfirm", label = "信托计划本金投入确认")
	public ModelAndView initPrincipalInvestmentConfirm(String tempUuid, String id, String creditEntrustId) {
		ModelMap modelMap = new ModelMap();
		modelMap.put("tempUuid", tempUuid);
		modelMap.put("creditEntrustId", creditEntrustId);
		if (ObjectHelper.isNotEmpty(id)) {
			try {
				CreditEntrustPrincipal creditEntrustPrincipal = creditEntrustPrincipalService.findOne(id);
				CreditEntrustPrincipalVo creditEntrustPrincipalVo = new CreditEntrustPrincipalVo(
						creditEntrustPrincipal);
				modelMap.put("creditEntrustPrincipalVo", creditEntrustPrincipalVo);

				// 封装已有费用项
				List<CreditEntrustFeeItem> feeItems = creditEntrustFeeItemService
						.findByBusinessId(creditEntrustPrincipal.getId());
				List<CreditEntrustFeeItemVo> feeItemVos = new ArrayList<CreditEntrustFeeItemVo>();
				for (CreditEntrustFeeItem feeItem : feeItems) {
					CreditEntrustFeeItemVo feeItemVo = new CreditEntrustFeeItemVo(feeItem);
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
	 * 备付金跟踪到账确认
	 * 
	 * @return
	 */
	@RequestMapping("/initStandbyTrackingConfirm")
	@UriKey(key = "com.zdsoft.finance.capital.initStandbyTrackingConfirm")
	@Reference(resource = "com.zdsoft.finance.capital.initStandbyTrackingConfirm", label = "信托计划备付金跟踪到账确认")
	public ModelAndView initStandbyTrackingConfirm(String tempUuid, String id, String creditEntrustId) {
		ModelMap modelMap = new ModelMap();
		modelMap.put("tempUuid", tempUuid);
		modelMap.put("creditEntrustId", creditEntrustId);
		if (ObjectHelper.isNotEmpty(id)) {
			try {
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
	 * 信托计划本金投入
	 * 
	 * @return
	 */
	@RequestMapping("/initPrincipalInvestmentView")
	@UriKey(key = "com.zdsoft.finance.capital.initPrincipalInvestmentView")
	@Reference(resource = "com.zdsoft.finance.capital.initPrincipalInvestmentView", label = "信托计划本金投入查看")
	public ModelAndView initPrincipalInvestmentView(String tempUuid, String id, String creditEntrustId) {
		ModelMap modelMap = new ModelMap();
		modelMap.put("tempUuid", tempUuid);
		modelMap.put("creditEntrustId", creditEntrustId);
		if (ObjectHelper.isNotEmpty(id)) {
			try {
				CreditEntrustPrincipal creditEntrustPrincipal = creditEntrustPrincipalService.findOne(id);
				CreditEntrustPrincipalVo creditEntrustPrincipalVo = new CreditEntrustPrincipalVo(creditEntrustPrincipal,
						new String[] {}, new String[] { "contributionType", "payoutPeriod" });
				modelMap.put("creditEntrustPrincipalVo", creditEntrustPrincipalVo);

				// 封装已有费用项
				List<CreditEntrustFeeItem> feeItems = creditEntrustFeeItemService
						.findByBusinessId(creditEntrustPrincipal.getId());
				List<CreditEntrustFeeItemVo> feeItemVos = new ArrayList<CreditEntrustFeeItemVo>();
				for (CreditEntrustFeeItem feeItem : feeItems) {
					CreditEntrustFeeItemVo feeItemVo = new CreditEntrustFeeItemVo(feeItem);
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
	 * 信托计划本金赎回
	 * 
	 * @return
	 */
	@RequestMapping("/initRedemePrinci")
	@UriKey(key = "com.zdsoft.finance.capital.initRedemePrinci")
	@Reference(resource = "com.zdsoft.finance.capital.initRedemePrinci", label = "信托计划本金赎回")
	public ModelAndView initRedemePrinci(String tempUuid, String id, String creditEntrustId) {
		ModelMap modelMap = new ModelMap();
		modelMap.put("tempUuid", tempUuid);
		modelMap.put("creditEntrustId", creditEntrustId);
		if (ObjectHelper.isNotEmpty(id)) {
			try {
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
	 * 信托计划本金赎回
	 * 
	 * @return
	 */
	@RequestMapping("/initRedemePrinciView")
	@UriKey(key = "com.zdsoft.finance.capital.initRedemePrinciView")
	@Reference(resource = "com.zdsoft.finance.capital.initRedemePrinciView", label = "信托计划本金赎回查看")
	public ModelAndView initRedemePrinciView(String tempUuid, String id, String creditEntrustId) {
		ModelMap modelMap = new ModelMap();
		modelMap.put("tempUuid", tempUuid);
		modelMap.put("creditEntrustId", creditEntrustId);
		if (ObjectHelper.isNotEmpty(id)) {
			try {
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
	 * 获取费用项并转换为json
	 * 
	 * @param attribution
	 * @param id
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/getFeeItems")
	@UriKey(key = "com.zdsoft.finance.capital.getFeeItems")
	@ResponseBody
	public String getFeeItems(String attribution, String id, String jsoncallback) {

		List<FeeItem> feeItems = feeItemService.findByAttribution(attribution);
		List<FeeItemVo> feeItemVos = new ArrayList<FeeItemVo>();
		for (FeeItem feeItem : feeItems) {
			FeeItemVo feeItemVo = new FeeItemVo(feeItem);
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
	 * 获取费用项并转换为json
	 * 
	 * @param businessId
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/getCreditFeeItems")
	@UriKey(key = "com.zdsoft.finance.capital.getCreditFeeItems")
	@ResponseBody
	public String getCreditFeeItems(String businessId, String jsoncallback) {
		List<CreditEntrustFeeItem> feeItems = creditEntrustFeeItemService.findByBusinessId(businessId);
		return ObjectHelper.objectToJson(feeItems, jsoncallback);
	}

	/**
	 * 保存专户贷方资金跟踪
	 * 
	 * @param loanCapitalVo
	 * @param jsoncallback
	 * @return
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
	 * 保存应付费用跟踪
	 * 
	 * @param creditCostTrackingVo
	 * @param jsoncallback
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
	 * 保存借方资金跟踪
	 * 
	 * @param creditEntrustDebitVo
	 * @param jsoncallback
	 * @return
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
	 * 保存备付资金跟踪
	 * 
	 * @param spareCapitalVo
	 * @param jsoncallback
	 * @return
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
	 * 保存信托计划转让
	 * 
	 * @param entrustAttomVo
	 * @param jsoncallback
	 * @return
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
	 * 保存信托计划本金投入
	 * 
	 * @param principalVo
	 * @param jsoncallback
	 * @return
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
	 * 到账确认信托计划本金投入
	 * 
	 * @param principalVo
	 * @param jsoncallback
	 * @return
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
			principal.setActualDate(DateHelper.dateToLong(new Date(), "yyyyMMddHHmmss"));
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
	 * 到账确认备付金跟踪
	 * 
	 * @param spareCapitalVo
	 *            备付金跟踪Vo
	 * @param jsoncallback
	 * @return
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
			CreditEntrust creditEntrust = creditEntrustService.findOne(creditEntrustId);
			spareCapital.setCreditEntrust(creditEntrust);
		} catch (BusinessException e1) {
			e1.printStackTrace();
			logger.error("查询信托计划失败", e1);
		}
		try {

			// 持久对象
			spareCapital.setStatus(StatusNm.ARRIVAL.value);
			spareCapital = spareCapitalService.saveOrUpdateSpareCapital(spareCapital);
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
	 * 保存本金赎回信息
	 * 
	 * @param principalVo
	 * @param jsoncallback
	 * @return
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
	 * 保存整体信托计划
	 * 
	 * @param creditEntrustVo
	 * @return
	 */
	@RequestMapping("/saveCreditEntrust")
	@UriKey(key = "com.zdsoft.finance.capital.saveCreditEntrust")
	@ResponseBody
	public ResponseMsg saveCreditEntrust(CreditEntrustVo creditEntrustVo) {

		ResponseMsg msg = new ResponseMsg();

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
	 * 修改整体信托计划
	 * 
	 * @param creditEntrustVo
	 * @return
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
	 * 一键资金匹配
	 * 
	 * @param creditEntrustId
	 *            信托计划id
	 * @return 处理结果
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
	 * 费用Vo集合转换为Po集合
	 * 
	 * @param feeItemVos
	 * @return 费用Po集合
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
