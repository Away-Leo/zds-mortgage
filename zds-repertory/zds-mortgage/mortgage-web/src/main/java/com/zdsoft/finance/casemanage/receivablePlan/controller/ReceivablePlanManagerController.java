package com.zdsoft.finance.casemanage.receivablePlan.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.bpm.annotation.client.FinishJob;
import com.zdsoft.bpm.annotation.client.ManualJob;
import com.zdsoft.essential.client.aop.annotation.DataAuthResource;
import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.permission.DataOperPermDto;
import com.zdsoft.finance.casemanage.receivablePlan.entity.BankAccount;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivableInfo;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivablePlan;
import com.zdsoft.finance.casemanage.receivablePlan.service.BankAccountService;
import com.zdsoft.finance.casemanage.receivablePlan.service.ReceivableInfoService;
import com.zdsoft.finance.casemanage.receivablePlan.service.ReceivablePlanService;
import com.zdsoft.finance.casemanage.receivablePlan.vo.BankAccountVo;
import com.zdsoft.finance.casemanage.receivablePlan.vo.ReceivableInfoVo;
import com.zdsoft.finance.casemanage.receivablePlan.vo.ReceivablePlanPackageVo;
import com.zdsoft.finance.casemanage.receivablePlan.vo.ReceivablePlanVo;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.cooperator.entity.Capitalist;
import com.zdsoft.finance.cooperator.entity.CooperatorTerminal;
import com.zdsoft.finance.cooperator.service.CapitalistService;
import com.zdsoft.finance.cooperator.service.CooperatorTerminalService;
import com.zdsoft.finance.customer.entity.BeforePersonalCustomer;
import com.zdsoft.finance.customer.service.BeforePersonalCustomerService;
import com.zdsoft.finance.finance.entity.RepaymentAmountAllot;
import com.zdsoft.finance.finance.service.RepaymentAmountAllotService;
import com.zdsoft.finance.finance.vo.RepaymentAmountAllotVo;
import com.zdsoft.finance.loan.entity.LoanApply;
import com.zdsoft.finance.loan.service.LoanApplySerivce;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.marketing.vo.CaseApplyVo;
import com.zdsoft.finance.spi.receivablePlan.CaseReceivableDto;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.common.util.StoreHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ReceivablePlanManagerController.java 
 * @ClassName: ReceivablePlanManagerController 
 * @Description: 还款计划管理contraller
 * @author zhoushichao 
 * @date 2017年2月21日 上午10:43:00 
 * @version V1.0 
 */ 
@Controller
@RequestMapping("/receivablePlan")
public class ReceivablePlanManagerController extends BaseController {

	@Autowired
	private CaseApplyService caseApplyService;

	@Autowired
	private ReceivablePlanService receivablePlanService;
	@Autowired
	private ReceivableInfoService receivableInfoService;

	@Autowired
	private BankAccountService bankAccountService;

	@Autowired
	private BeforePersonalCustomerService beforePersonalCustomerService;
	@Autowired
	private CooperatorTerminalService cooperatorTerminalService;
	@Autowired
	private RepaymentAmountAllotService repaymentAmountAllotService;
	@Autowired
	private CapitalistService capitalistService;

	@Autowired
	private CED CED ;
	@Autowired
	private LoanApplySerivce loanApplyService;
	
	/**
	 * 
	 * @Title: receivablePlanManager 
	 * @Description: 还款计划管理列表（入口）
	 * @author zhoushichao 
	 * @return
	 */
	@RequestMapping("/receivablePlanManager")
	@UriKey(key = "com.cnfh.rms.casemanage.receivablePlan.receivablePlanManager")
	@Menu(resource = "com.cnfh.rms.casemanage.receivablePlan.receivablePlanManager", label = "还款计划管理", group = "project", order = 24)
	@DataAuthResource(resource="com.cnfh.rms.casemanage.receivablePlan.receivablePlanManager.dataAuth",label="还款计划管理",group="com.cnfh.rms.casemanage.receivablePlan.receivablePlanManager")
	public ModelAndView receivablePlanManager() {
		return new ModelAndView("casemanage/receivablePlanManager/receivable_plan_manager_list");
	}
	
	/**
	 * 
	 * @Title: receivevableplanManagerList 
	 * @Description: 还款计划管理列表（查询数据）
	 * @author zhoushichao 
	 * @param request
	 * @param pageable
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/receivevableplanManagerList")
	@UriKey(key = "com.cnfh.rms.casemanage.receivablePlan.receivevableplanManagerList")
	@ResponseBody
	public String receivevableplanManagerList(HttpServletRequest request, PageRequest pageable, String jsoncallback) {
		
		// 获取页面封装的查询参数
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
		//风控状态
		String riskStatusName = request.getParameter("riskStatusName");
		ResponseMsg responseMsg = new ResponseMsg();
		
		try {
		    //数据权限
            DataOperPermDto dtOperPermDto=CED.findDataPerms(StoreHelper.getApplication(), "com.cnfh.rms.casemanage.receivablePlan.receivablePlanManager.dataAuth");
			Page<Map<String, Object>> page = receivablePlanService.findPageReceivablePlan(pageable, queryObjs,riskStatusName,dtOperPermDto);
			//gf 需求
			List<Map<String, Object>> LM = page.getRecords();
			for (Map<String, Object> map : LM) {
				BigDecimal loanApplyAnount = (BigDecimal)map.get("LOANAPPLYANOUNT");
			    BigDecimal caseApplyBalance = (BigDecimal)map.get("CASEAPPLYBALANCE");
				if(loanApplyAnount.compareTo(new BigDecimal("0")) == 0){
					map.put("riskStatusName", "未垫资");
				}else if(loanApplyAnount.compareTo(new BigDecimal("0")) > 0 && caseApplyBalance.compareTo(new BigDecimal("0")) != 0){
					map.put("riskStatusName", "已垫资");
				}else if(loanApplyAnount.compareTo(new BigDecimal("0")) > 0 && caseApplyBalance.compareTo(new BigDecimal("0")) == 0){
					map.put("riskStatusName", "已回款");
				}
				//服务费
				if(ObjectHelper.isEmpty(map.get("EXPECTEDAMOUNT"))){
					map.put("EXPECTEDAMOUNT", 0);
				}
				//利息
				if(ObjectHelper.isEmpty(map.get("PLANINTERESTAMOUNT"))){
					map.put("PLANINTERESTAMOUNT", 0);
				}
				//是否能发送
//				map.put("isCanSend",true);
//				if (ObjectHelper.isNotEmpty(map.get("EXPECTLOANDATE"))) {
//					map.put("isCanSend", this.isCanSend(new Long(map.get("EXPECTLOANDATE").toString())));
//				}
				List<LoanApply> loanApplyList=loanApplyService.findByCaseApplyId(map.get("ID").toString());
				//如果案件有放款信息，则显示放款后按钮
				map.put("isCanSend",false);
				if(loanApplyList.size()>0){
					if(loanApplyList.get(0).getLoanRecords().size()>0){
						map.put("isCanSend", true);
					}
				}
				
			}
			responseMsg.setRows(LM);
			responseMsg.setTotal(page.getTotalRows());
			responseMsg.setMsg("还款计划管理列表查询成功");
			responseMsg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			responseMsg.setMsg("还款计划管理列表查询失败");
			responseMsg.setResultStatus(ResponseMsg.SYS_ERROR);
			e.printStackTrace();
			logger.error("***********还款计划管理列表查询失败*************", e);
		}
		return ObjectHelper.objectToJson(responseMsg, jsoncallback);
	}
	
	/**
	 * @Title: isCanSend 
	 * @Description: 是否能发送
	 * @author gufeng 
	 * @param expectLoanDate 放款日期
	 * @return
	 */
	@SuppressWarnings("unused")
	private boolean isCanSend(Long expectLoanDate){
		if(ObjectHelper.isEmpty(expectLoanDate)){
			return true;
		}
		String loanDate = expectLoanDate.toString();
		Date date = DateHelper.stringToDate(loanDate, DateHelper.DATE_SHORT_SIMPLE_FORMAT);
		Date nowDate = new Date();
		if((nowDate.getTime() - date.getTime())/(24*3600*1000) > 8){//超过8天不能发送
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @Title: initBeforeLoan 
	 * @Description: 放款前页面入口
	 * @author zhoushichao 
	 * @param caseApplyId
	 * @return
	 */
	@RequestMapping("/initBeforeLoan")
	@UriKey(key = "com.cnfh.rms.casemanage.receivablePlan.initBeforeLoan")
	public ModelAndView initBeforeLoan(String caseApplyId) {
		ModelMap model = new ModelMap();
		try {
			CaseApply caseApply = caseApplyService.findOne(caseApplyId);
			CaseApplyVo caseApplyVo = new CaseApplyVo(caseApply);
			//查询终端
        	CooperatorTerminal cooperatorTerminal = cooperatorTerminalService.findOne(caseApply.getTerminalId());
        	caseApplyVo.setTerminalIdName(cooperatorTerminal.getTerminalFullName());
        	//资金来源	
    		String capitalSource = caseApplyVo.getCapitalSource();
    		if(ObjectHelper.isNotEmpty(capitalSource)){
    			Capitalist capitalist = capitalistService.findOne(capitalSource);
    			if(ObjectHelper.isNotEmpty(capitalist)){
    				caseApplyVo.setCapitalSourceName(capitalist.getCapitalName());
    			}
    		}
			model.put("caseApplyVo", caseApplyVo);
		} catch (BusinessException e) {
			logger.error("查询案件信息出错 ID为：" + caseApplyId, e);
			e.printStackTrace();
		}
		return new ModelAndView("casemanage/receivablePlanManager/before_loan_view", model);
	}
	
	/**
	 * 
	 * @Title: editRepaymentPlan 
	 * @Description: 放款后(编辑还款计划)页面入口
	 * @author zhoushichao 
	 * @param caseApplyId
	 * @return
	 */
	@RequestMapping("/editRepaymentPlan")
	@UriKey(key = "com.cnfh.rms.casemanage.receivablePlan.editRepaymentPlan")
	public ModelAndView editRepaymentPlan(String caseApplyId) {
		ModelMap model = new ModelMap();
		try {
			CaseApply caseApply = caseApplyService.findOne(caseApplyId);
			CaseApplyVo caseApplyVo = new CaseApplyVo(caseApply);
			// 查询案件主借人
			List<BeforePersonalCustomer> listPersonal = beforePersonalCustomerService
					.queryCustomerByCaseApplyIdAndJoinType(caseApplyId, CaseApplyBeforeCustomer.MAIN_BORROW);
			if (ObjectHelper.isNotEmpty(listPersonal)) {
				caseApplyVo.setCustomerName(listPersonal.get(0).getCustomerName());
			}
			ReceivableInfo receivableInfo = caseApply.getReceivableInfo();
			ReceivableInfoVo receivableInfoVo =null;
			if (ObjectHelper.isNotEmpty(receivableInfo)) {
				receivableInfoVo = new ReceivableInfoVo(receivableInfo);
			}
			model.put("caseApplyVo", caseApplyVo);
			model.put("receivableInfo", receivableInfoVo);
		} catch (BusinessException e) {
			logger.error("查询案件信息出错 ID为：" + caseApplyId, e);
			e.printStackTrace();
		}
		return new ModelAndView("casemanage/receivablePlanManager/repayment_plan_edit", model);
	}
	
	/**
	 * 
	 * @Title: receivableAfterPlan 
	 * @Description: 跳转生成还款计划页面
	 * @author zhoushichao 
	 * @param caseApplyId 案件Id
	 * @return
	 */
	@RequestMapping(value = "/receivableAfterPlan")
	@UriKey(key = "com.cnfh.rms.casemanage.receivablePlan.receivableAfterPlan")
	public ModelAndView receivableAfterPlan(String caseApplyId) {
		ModelMap model = new ModelMap();
		model.put("caseApplyId", caseApplyId);
		return new ModelAndView("casemanage/receivablePlanManager/receivable_after_plan_edit", model);
	}
	/**
	 * 
	 * @Title: initEditRepaymentPlan 
	 * @Description: 我的申请>编辑还款计划页面入口
	 * @author zhoushichao 
	 * @param projectId 
	 */
	@RequestMapping("/initEditRepaymentPlan")
	@UriKey(key="com.cnfh.rms.casemanage.receivablePlan.initEditRepaymentPlan")
	public ModelAndView initEditRepaymentPlan(String projectId){
	     return this.editRepaymentPlan(projectId);
	}
	/**
	 * 
	 * @Title: initViewRepaymentPlan 
	 * @Description: 我的申请>详情还款计划页面入口
	 * @author zhoushichao 
	 * @param projectId 
	 */
	@RequestMapping("/initViewRepaymentPlan")
	@UriKey(key="com.cnfh.rms.casemanage.receivablePlan.initViewRepaymentPlan")
	public ModelAndView initViewRepaymentPlan(String projectId){
		 return this.viewReceivablePlan(projectId);
	}
	
	/**
	 * 
	 * @Title: editRepaymentPlanJob 
	 * @Description: 流程中还款计划复核页面入口
	 * @author zhoushichao 
	 * @param projectId
	 * @param processInstanceId
	 * @param businessKey
	 * @return
	 */
	@RequestMapping("/editRepaymentPlanJob")
	@UriKey(key = "com.cnfh.rms.casemanage.receivablePlan.editRepaymentPlanJob")
	@ManualJob(resource = "com.cnfh.rms.casemanage.receivablePlan.editRepaymentPlanJob", label = "还款计划复核")
	@FinishJob(resource = "com.cnfh.rms.casemanage.receivablePlan.editRepaymentPlanJob", businessId = "businessKey", projectId = "projectId")
    public ModelAndView editRepaymentPlanJob(String projectId, String processInstanceId, String businessKey){
		ModelAndView modelAndView = this.editRepaymentPlan(projectId);
		modelAndView.setViewName("casemanage/receivablePlanManager/repayment_plan_job_edit");
		return modelAndView;
	}


	/**
	 * 
	 * @Title: viewReceivablePlan 
	 * @Description: 查看还款计划入口
	 * @author zhoushichao 
	 * @param caseApplyId
	 * @return
	 */
	@RequestMapping("/viewRepaymentPlan")
	@UriKey(key = "com.cnfh.rms.casemanage.receivablePlan.viewReceivablePlan")
	public ModelAndView viewReceivablePlan(String caseApplyId) {
		ModelMap model = new ModelMap();
		logger.info("查看还款计划 案件ID为：" + caseApplyId);
		try {
			CaseApply caseApply = caseApplyService.findOne(caseApplyId);
			CaseApplyVo caseApplyVo = new CaseApplyVo(caseApply);
			// 查询案件主借人
			List<BeforePersonalCustomer> listPersonal = beforePersonalCustomerService
					.queryCustomerByCaseApplyIdAndJoinType(caseApplyId, CaseApplyBeforeCustomer.MAIN_BORROW);
			if (ObjectHelper.isNotEmpty(listPersonal)) {
				caseApplyVo.setCustomerName(listPersonal.get(0).getCustomerName());
			}
			
			//查询还款计划信息
			if (ObjectHelper.isNotEmpty(caseApply.getReceivableInfo())) {
				caseApplyVo.setReceivableInfoVo(new ReceivableInfoVo(caseApply.getReceivableInfo()));
			}
			
			model.put("caseApplyVo", caseApplyVo);
			
			List<ReceivablePlanPackageVo> packageVoList = new ArrayList<ReceivablePlanPackageVo>();
			
			//查询还款计划
			List<CaseReceivableDto> planList=receivablePlanService.getCaseReceivableList(caseApplyId, null, true);
			List<RepaymentAmountAllotVo> planVoList =new ArrayList<RepaymentAmountAllotVo>();
			//Po转Vo
			for (CaseReceivableDto plan : planList) {
				RepaymentAmountAllotVo rat=new RepaymentAmountAllotVo();
				BeanUtils.copyProperties(plan, rat,new String[]{"id"});
				planVoList.add(rat);
			}
			
			for (RepaymentAmountAllotVo repaymentVo : planVoList) {
				ReceivablePlanPackageVo receivablePlanPackageVo = new ReceivablePlanPackageVo();
				List<RepaymentAmountAllotVo> allotList=new ArrayList<RepaymentAmountAllotVo>();
				
				//查询每期还款信息
				List<RepaymentAmountAllot> amountAllotList = repaymentAmountAllotService.findRepaymentAmountAllotByPlanId(repaymentVo.getPlanId());
				for (RepaymentAmountAllot repaymentAmountAllot : amountAllotList) {
					RepaymentAmountAllotVo repaymentAmountAllotVo = new RepaymentAmountAllotVo(repaymentAmountAllot); 
					allotList.add(repaymentAmountAllotVo);
				}
				
				//设置每期还款计划的还款情况
				receivablePlanPackageVo.setRepaymentAmountAllotVo(repaymentVo);
				receivablePlanPackageVo.setRepaymentAmountAllotVoList(allotList);
				packageVoList.add(receivablePlanPackageVo);
			}
			model.put("packageVoList", packageVoList);
		} catch (BusinessException e) {
			logger.error("查询案件信息错误 案件ID：" + caseApplyId, e);
			e.printStackTrace();
		}
		return new ModelAndView("casemanage/receivablePlanManager/repayment_plan_view", model);
	}
	
	/**
	 * 
	 * @Title: updateReceivableInfo 
	 * @Description: 修改还款计划基本信息
	 * @author zhoushichao 
	 * @param receivableInfoVo 还款计划基本信息
	 * @param receivablePlanJson 还款计划
	 * @param submitStatus 是否启动流程
	 * @return
	 */
	@RequestMapping("/updateReceivableInfo")
	@UriKey(key = "com.cnfh.rms.casemanage.receivablePlan.updateReceivableInfo")
	@ResponseBody
	public ResponseMsg updateReceivableInfo(ReceivableInfoVo receivableInfoVo, String receivablePlanJson,Boolean submitStatus) {
		ResponseMsg responseMsg = new ResponseMsg();
		logger.info("还款计划信息ID为：" + receivableInfoVo.getId());
		ReceivableInfo receivableInfo = receivableInfoVo.toPO();
		try {
			// 保存
			ReceivableInfo receivable = receivableInfoService.updateReceivableInfo(receivableInfo, receivablePlanJson, submitStatus);

			logger.info("保存还款计划信息成功");
			  if(ObjectHelper.isNotEmpty(receivable.getCurrentDealEmpName())){
				  responseMsg.setMsg("保存成功！下一处理人："+receivable.getCurrentDealEmpName());
	            }else{
	            	responseMsg.setMsg("保存成功！");
	            }
			responseMsg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			responseMsg.setResultStatus(ResponseMsg.SYS_ERROR);
			responseMsg.setMsg("保存还款计划信息失败");
			logger.error("保存还款计划信息失败", e);
			e.printStackTrace();
		}
		return responseMsg;
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
				receivablePlanListPo.add(receivablePlanVo.toPo());
			}
			// 保存还款计划
			receivablePlanService.saveReceivablePlan(receivableInfoId, loanApplyId, receivablePlanListPo);
			logger.info("保存还款计划成功");
			responseMsg.setMsg("保存成功");
		} catch (BusinessException e) {
			responseMsg.setMsg("保存还款计划失败");
			logger.error("保存还款计划失败", e);
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
	@RequestMapping("/saveReceivableInfo")
	@UriKey(key = "com.zdsoft.finance.casemanage.receivablePlanManager.saveReceivableInfo")
	@ResponseBody
	public String saveReceivableInfo(ReceivableInfoVo receivableInfoVo, String receivablePlanJson, BankAccountVo vo,
			String jsoncallback) {
		ResponseMsg responseMsg = new ResponseMsg();
		logger.info("还款计划信息ID为：" + receivableInfoVo.getId());
		try {
			// 保存
			ReceivableInfo info =bankAccountService.saveReceivableInfo(receivableInfoVo.toPo(receivableInfoVo, new ReceivableInfo()),
				vo.getRecAccountVo().toPo(vo.getRecAccountVo(), new BankAccount()),
				vo.getLoanAccountVo().toPo(vo.getLoanAccountVo(), new BankAccount()), receivablePlanJson,
				receivableInfoVo.getApplyTerm(),receivableInfoVo.getApplyTermUnit());
			responseMsg.setResultStatus(ResponseMsg.SUCCESS);
			responseMsg.setId(info.getId());
			responseMsg.setMsg("保存还款计划信息成功");
		} catch (Exception e) {
			responseMsg.setResultStatus(ResponseMsg.SYS_ERROR);
			responseMsg.setMsg("保存还款计划信息失败");
			logger.error("保存还款计划信息失败", e);
			e.printStackTrace();
		}
		return ObjectHelper.objectToJson(responseMsg, jsoncallback);
	}

}
