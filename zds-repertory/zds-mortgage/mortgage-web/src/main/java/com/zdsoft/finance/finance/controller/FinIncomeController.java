package com.zdsoft.finance.finance.controller;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.zdsoft.essential.client.aop.annotation.DataAuthResource;
import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.essential.dto.permission.DataOperPermDto;
import com.zdsoft.finance.businesssetting.entity.InComeBody;
import com.zdsoft.finance.businesssetting.service.InComeBodyService;
import com.zdsoft.finance.casemanage.datasurvey.entity.FeeInfomation;
import com.zdsoft.finance.casemanage.datasurvey.service.FeeInfomationService;
import com.zdsoft.finance.casemanage.datasurvey.vo.FeeInfomationVo;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.common.utils.ParameterUtil;
import com.zdsoft.finance.common.utils.TimeUtil;
import com.zdsoft.finance.customer.entity.BeforeCustomer;
import com.zdsoft.finance.customer.vo.BeforePersonalCustomerVo;
import com.zdsoft.finance.finance.entity.FinIncome;
import com.zdsoft.finance.finance.entity.FinIncomeDetail;
import com.zdsoft.finance.finance.service.FinIncomeDetailService;
import com.zdsoft.finance.finance.service.FinIncomeService;
import com.zdsoft.finance.finance.vo.FinIncomeDetailVo;
import com.zdsoft.finance.finance.vo.FinIncomeVo;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.service.CaseApplyBeforeCustomerService;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.marketing.vo.CaseApplyVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.exception.AppException;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.common.util.StoreHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: FinIncomeController.java
 * @ClassName: FinIncomeController
 * @Description: 收费管理控制器
 * @author longwei
 * @date 2017年2月9日 上午11:30:47
 * @version V1.0
 */
@Controller
@RequestMapping("/finIncome")
public class FinIncomeController extends BaseController {

	@Autowired
	private CaseApplyService caseApplyService;

	@Autowired
	private FinIncomeDetailService finIncomeDetailService;

	@Autowired
	private FinIncomeService finIncomeService;

	@Autowired
	private InComeBodyService inComeBodyService;
	
	@Autowired
	private CaseApplyBeforeCustomerService  caseApplyBeforeCustomerService;
	
	@Autowired
	private FeeInfomationService feeInfomationService;
	
	@Autowired
	private CED CED;
	
	/**
	 * 
	 * @Title: caseApplyList
	 * @Description: 前端费用收费管理
	 * @author xiangjx
	 * @return
	 */
	@RequestMapping("/caseApplyList")
	@UriKey(key = "com.zdsoft.finance.finance.finIncome.caseApplyList")
	@Menu(resource = "com.zdsoft.finance.finance.finIncome.caseApplyList", label = "前端费用收费管理", group = "finance", order = 1)
	public ModelAndView caseApplyList() {
		return new ModelAndView("finance/finIncome/finIncome_caseApplyList");
	}

	/**
	 * @Title: finIncomeList
	 * @Description: 前端收款单查询
	 * @author jincheng
	 * @return
	 */
	@RequestMapping("/finIncomeList")
	@UriKey(key = "com.zdsoft.finance.finance.finIncome.finIncomeList")
	@Menu(resource = "com.zdsoft.finance.finance.finIncome.finIncomeList", label = "前端收款单查询", group = "finance", order = 2)
	@DataAuthResource(resource="com.zdsoft.finance.finance.finIncome.finIncomeList.dataAuth",label="前端收款单查询",group="com.zdsoft.finance.finance.finIncome.finIncomeList")
	public ModelAndView finIncomeList() {
		ModelMap model = new ModelMap();
		try {
			EmpDto emp=CED.getLoginUser();
			model.put("empCd", emp.getEmpCd());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("finance/finIncome/finIncome_finIncomeList",model);
	}

	
	/**
	 * @Title: finIncomeConfirmList
	 * @Description: 前端费用收款确认
	 * @author jincheng
	 * @return
	 */
	@RequestMapping("/finIncomeConfirmList")
	@UriKey(key = "com.zdsoft.finance.finance.finIncome.finIncomeConfirmList")
	@Menu(resource = "com.zdsoft.finance.finance.finIncome.finIncomeConfirmList", label = "前端费用收款确认", group = "finance", order = 3)
	public ModelAndView finIncomeConfirmList() {
		return new ModelAndView("finance/finIncome/finIncome_finIncome_confirmList");
	}

	
	
	/**
	 * @Title: finIncomeList
	 * @Description: 获取费用收款单列表数据
	 * @author jincheng
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/finIncomeListData")
	@UriKey(key = "com.zdsoft.finance.finance.finIncomeDataList")
	@ResponseBody
	public ResponseMsg finIncomeDataList(HttpServletRequest request, PageRequest page) {
		ResponseMsg msg = new ResponseMsg();
		try {
			DataOperPermDto dataOperPermDto = CED.findDataPerms(StoreHelper.getApplication(), "com.zdsoft.finance.finance.finIncome.finIncomeList.dataAuth");
			List<QueryObj> li = ParameterUtil.getQueryObjByParameter(request, new String[]{"fin","mca","cus"});
			Page<Map<String, Object>> caseApplyPage = finIncomeService.getFinIncomeCaseApplyList(page, li,dataOperPermDto);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setTotal(caseApplyPage.getTotalRows());
			msg.setRows(caseApplyPage.getRecords());
		} catch (AppException e) {
			e.printStackTrace();
		}
		return msg;
	}

	/**
	 * @Title: caseFinIncomeList
	 * @Description: 案件收款明细查询
	 * @author jincheng
	 * @return
	 */
	@RequestMapping("/caseFinIncomeList")
	@UriKey(key = "com.zdsoft.finance.finance.finIncome.caseFinIncomeList")
	@Menu(resource = "com.zdsoft.finance.finance.finIncome.caseFinIncomeList", label = "案件收费明细查询", group = "finance", order = 4)
	@DataAuthResource(resource="com.zdsoft.finance.finance.finIncome.caseFinIncomeList.dataAuth",label="案件收费明细查询",group="com.zdsoft.finance.finance.finIncome.caseFinIncomeList")
	public ModelAndView caseFinIncomeList() {
		return new ModelAndView("finance/finIncome/finIncome_caseFinIncomeList");
	}
	
	/**
	 * @Title: getCaseApplyFinIncomeList 
	 * @Description: 获取案件收费明细列表数据
	 * @author jincheng 
	 * @param request
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/getCaseApplyFinIncomeList")
	@UriKey(key = "com.zdsoft.finance.finance.getCaseApplyFinIncomeList")
	@ResponseBody
	public String getCaseApplyFinIncomeList(HttpServletRequest request,  PageRequest pageable) {
		ResponseMsg msg = new ResponseMsg();
		List<QueryObj> queryObjs = ParameterUtil.getQueryObjByParameter(request, new String[]{"fin","mca"});
		try {
			DataOperPermDto dataOperPermDto = CED.findDataPerms(StoreHelper.getApplication(), "com.zdsoft.finance.finance.finIncome.caseFinIncomeList.dataAuth");
			Page<Map<String, Object>> caseApplyPage = finIncomeService.getCaseApplyFinIncomeList(pageable, queryObjs,dataOperPermDto);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setTotal(caseApplyPage.getTotalRows());
			msg.setRows(caseApplyPage.getRecords());
		} catch (Exception e) {
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
			e.printStackTrace();
		}
		return ObjectHelper.objectToJson(msg);
	}

	/**
	 * @Title: caseFinIncomeview 
	 * @Description: 案件收款明细
	 * @author jincheng 
	 * @param caseApplyId
	 * @return
	 */
	@RequestMapping("/caseFinIncomeview")
	@UriKey(key = "com.zdsoft.finance.finance.finIncome.caseFinIncomeview")
	public ModelAndView caseFinIncomeview(String caseApplyId) {
		ModelMap model = new ModelMap();
		try {
			CaseApply caseApply = caseApplyService.findOne(caseApplyId);
			CaseApplyVo applyVo = new CaseApplyVo(caseApply);
			//获取主借人
			List<BeforeCustomer> customerList=caseApplyBeforeCustomerService.findCustomerByCaseApplyIdAndJoinType(caseApply.getId(), CaseApplyBeforeCustomer.MAIN_BORROW);
			for(BeforeCustomer cust:customerList){
				applyVo.setCustomerName(cust.getCustomerName());
			}
			model.put("caseApply", applyVo);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return new ModelAndView("finance/finIncome/finIncome_caseFinIncomeView", model);
	}

	/**
	 * 
	 * @Title: receipt
	 * @Description: 收费
	 * @author xiangjx
	 * @param caseApplayId
	 * @param incomeId
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/receipt")
	@UriKey(key = "com.zdsoft.finance.finance.finIncome.receipt")
	public ModelAndView receipt(String caseApplyId, String businessKey) {
		ModelMap model=new ModelMap();
		try {
			CaseApply caseApply = null; 
			FinIncomeVo finIncomeVo = null;
			
			if (ObjectHelper.isEmpty(businessKey)) {
				 finIncomeVo = new FinIncomeVo();
				 finIncomeVo.setBillCode("FH"+TimeUtil.getCurrentDate());
				 caseApply = caseApplyService.findOne(caseApplyId); 
			} else {
				FinIncome finIncome = finIncomeService.findOne(businessKey);
				finIncomeVo = new FinIncomeVo(finIncome);
				caseApply = caseApplyService.findOne(finIncome.getCaseApplyId()); 
			}
			
			CaseApplyVo caseApplyVo = new CaseApplyVo(caseApply);
			
			//获取主借人
			List<BeforeCustomer> customerList=caseApplyBeforeCustomerService.findCustomerByCaseApplyIdAndJoinType(caseApply.getId(), CaseApplyBeforeCustomer.MAIN_BORROW);
			for(BeforeCustomer cust:customerList){
				caseApplyVo.setCustomerName(cust.getCustomerName());
			}
			
			List<InComeBody> 	list = inComeBodyService.findByOrgId(caseApplyVo.getMechanismCode());
			
			model.put("caseApplyVo", caseApplyVo);
			model.put("finIncomeVo", finIncomeVo);
			model.put("incomeSubject", ObjectHelper.objectToJson(list));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return new ModelAndView("finance/finIncome/finIncome_receipt_edit",model);
	}


	/**
	 * 
	 * @Title: view
	 * @Description: 查看页面
	 * @author xiangjx
	 * @param id
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/view")
	@UriKey(key = "com.zdsoft.finance.finance.finIncome.view")
	public ModelAndView view(String businessKey) throws BusinessException {
		ModelMap model=new ModelMap();
		FinIncome finIncome = finIncomeService.findOne(businessKey);
		FinIncomeVo finIncomeVo = new FinIncomeVo(finIncome);
		model.put("finIncomeVo", finIncomeVo);
		
		CaseApply caseApply = caseApplyService.findOne(finIncome.getCaseApplyId());
		CaseApplyVo caseVo = new CaseApplyVo(caseApply);
		//获取主借人
		List<BeforeCustomer> customerList=caseApplyBeforeCustomerService.findCustomerByCaseApplyIdAndJoinType(finIncome.getCaseApplyId(), CaseApplyBeforeCustomer.MAIN_BORROW);
		for(BeforeCustomer cust:customerList){
			caseVo.setCustomerName(cust.getCustomerName());
		}
		model.put("caseApplyVo", caseVo);
		return new ModelAndView("finance/finIncome/finIncome_receipt_view",model);
	}

	/**
	 * 
	 * @Title: save
	 * @Description:保存数据
	 * @author xiangjx
	 * @param finIncomeVo
	 * @param receiptItems
	 * @param isSubmit
	 * @return
	 */
	@RequestMapping("/save")
	@UriKey(key = "com.zdsoft.finance.finance.finIncome.save")
	@ResponseBody
	public ResponseMsg save(FinIncomeVo finIncomeVo, String receiptItems) {
		ResponseMsg msg = new ResponseMsg();
		try {
			List<FinIncomeDetail> finIncomeDetails = JSON.parseArray(receiptItems, FinIncomeDetail.class);// 数据组装
			FinIncome finIncome = finIncomeVo.toPO();
			finIncome = finIncomeService.saveOrUpdateFinIncome(finIncome, finIncomeDetails);
			msg.setId(finIncome.getId());
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg(finIncome.getMsg());
		} catch (Exception e) {
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return msg;
	}

	

	/**
	 * 收费项目列表
	 * 
	 * @Title: finIncomeDetailList
	 * @Description: 收费项目列表
	 * @author xiangjx
	 * @param request
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/finIncomeDetailList")
	@UriKey(key = "com.zdsoft.finance.finance.finIncomeDetail.list")
	@ResponseBody
	public ResponseMsg finIncomeDetailList(HttpServletRequest request, PageRequest page) {
		ResponseMsg msg = new ResponseMsg();
		List<FinIncomeDetailVo> listVo = new ArrayList<FinIncomeDetailVo>();
		List<QueryObj> li = (List<QueryObj>) request.getAttribute("listObj");
		Page<FinIncomeDetail> list = finIncomeDetailService.findByHqlConditions(page, li);
		for (FinIncomeDetail fee : list.getRecords()) {
			FinIncomeDetailVo feeVo = new FinIncomeDetailVo(fee);
			listVo.add(feeVo);
		}
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setRows(listVo);
		msg.setTotal(Long.parseLong("" + listVo.size()));
		return msg;
	}

	/**
	 * 
	 * @Title: finIncomeFirm
	 * @Description: 收费确认查看页面
	 * @author xiangjx
	 * @param businessKey
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/finIncomeFirm")
	@UriKey(key = "com.zdsoft.finance.finance.finIncome.finIncomeFirm")
	public ModelAndView finIncomeFirm( String businessKey) {
		ModelMap model = new ModelMap();
		try {
			FinIncome	finIncome = finIncomeService.findOne(businessKey);
			FinIncomeVo finIncomeVo = new FinIncomeVo(finIncome);
			model.put("finIncomeVo", finIncomeVo);
			
			CaseApply caseApply=caseApplyService.findOne(finIncome.getCaseApplyId());
			CaseApplyVo caseApplyVo=new CaseApplyVo(caseApply);
			//获取主借人
			List<BeforeCustomer> customerList=caseApplyBeforeCustomerService.findCustomerByCaseApplyIdAndJoinType(finIncome.getCaseApplyId(), CaseApplyBeforeCustomer.MAIN_BORROW);
			for(BeforeCustomer cust:customerList){
				caseApplyVo.setCustomerName(cust.getCustomerName());
			}
			model.put("caseApplyVo", caseApplyVo);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return new ModelAndView("finance/finIncome/finIncome_charge_confirm", model);
	}

	/**
	 * 
	 * @Title: finIncomeFirmSave
	 * @Description: 收费确认保存状态
	 * @author xiangjx
	 * @param businessKey
	 * @param status
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/finIncomeFirmSave")
	@UriKey(key = "com.zdsoft.finance.finance.finIncome.finIncomeFirmSave")
	@ResponseBody
	public String finIncomeFirmSave(String businessKey, String status) {
		ResponseMsg msg = new ResponseMsg();
		try {
			finIncomeService.updateFinIncomeStatus(businessKey,status);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("操作成功");
		} catch (Exception e) {
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("操作失败");
		}
		return ObjectHelper.objectToJson(msg);
	}
	
	/**
	 * @Title: destoryFinIncome 
	 * @Description: 废弃费用收费
	 * @author jincheng 
	 * @param finIncomeId
	 * @return
	 */
	@RequestMapping("/destoryFinIncome")
	@UriKey(key = "com.zdsoft.finance.finance.finIncome.destoryFinIncome")
	@ResponseBody
	public String destoryFinIncome(String finIncomeId) {
		ResponseMsg msg = new ResponseMsg();
		try {
			finIncomeService.destoryFinIncome(finIncomeId);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("操作成功");
		} catch (Exception e) {
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("操作失败");
		}
		return ObjectHelper.objectToJson(msg);
	}
	
	/**
	 * 
	 * @Title: finIncomeEdit
	 * @Description: 收费确认编辑
	 * @author xiangjx
	 * @param projectId
	 * @param processInstanceId
	 * @param businessKey
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/finIncomeEdit")
	@UriKey(key = "com.zdsoft.finance.finance.finIncome.finIncomeEdit")
	public ModelAndView finIncomeEdit(String projectId, String processInstanceId, String businessKey){
		ModelMap model = new ModelMap();
		try {
			FinIncome	finIncome = finIncomeService.findOne(projectId);
			FinIncomeVo finIncomeVo = new FinIncomeVo(finIncome);
			model.put("finIncomeVo", finIncomeVo);
			
			CaseApply caseApply=caseApplyService.findOne(finIncome.getCaseApplyId());
			CaseApplyVo caseApplyVo=new CaseApplyVo(caseApply);
			//获取主借人
			List<BeforeCustomer> customerList=caseApplyBeforeCustomerService.findCustomerByCaseApplyIdAndJoinType(finIncome.getCaseApplyId(), CaseApplyBeforeCustomer.MAIN_BORROW);
			for(BeforeCustomer cust:customerList){
				caseApplyVo.setCustomerName(cust.getCustomerName());
			}
			model.put("caseApplyVo", caseApplyVo);
			
			List<InComeBody> 	list = inComeBodyService.findByOrgId(caseApplyVo.getMechanismCode());
			model.put("incomeSubject", ObjectHelper.objectToJson(list));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return new ModelAndView("finance/finIncome/finIncome_receipt_process_edit", model);
	}


	/**
	 * @Title: printRequestFunds
	 * @Description: 打印明细
	 * @author jincheng
	 * @param finIncomeId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/printFinIncome")
	@UriKey(key = "com.zdsoft.finance.finIncome.printFinIncome")
	public ModelAndView printRequestFunds(String caseApplyId,String feeIds) {
		ModelMap model = new ModelMap();
		try {
			CaseApply caseApply = caseApplyService.findOne(caseApplyId);
			CaseApplyVo caseApplyVo = new CaseApplyVo(caseApply);
			//获取主借人
			List<BeforeCustomer> customerList=caseApplyBeforeCustomerService.findCustomerByCaseApplyIdAndJoinType(caseApply.getId(), CaseApplyBeforeCustomer.MAIN_BORROW);
			for(BeforeCustomer cust:customerList){
				caseApplyVo.setCustomerName(cust.getCustomerName());
			}
			model.put("caseApply", caseApplyVo);
			List<FeeInfomationVo> feeVoList=new ArrayList<FeeInfomationVo>();
			String[] feeArray=feeIds.split(",");
			BigDecimal expectedAmount=BigDecimal.ZERO;
			BigDecimal receivedAmount=BigDecimal.ZERO;
			BigDecimal expectedPayableAmount=BigDecimal.ZERO;
			
			for(String feeId:feeArray){
				if(ObjectHelper.isNotEmpty(feeId)){
					FeeInfomation fee=feeInfomationService.findOne(feeId);
					FeeInfomationVo feeVo=new FeeInfomationVo(fee);
					feeVo.setFeeId(fee.getId());
					feeVoList.add(feeVo);
					expectedAmount=expectedAmount.add(fee.getExpectedAmount());
					receivedAmount=receivedAmount.add(fee.getReceivedAmount());
					expectedPayableAmount=expectedPayableAmount.add(fee.getExpectedPayableAmount());
				}
			}
			model.put("detailList", feeVoList);
			model.put("total_expectedAmount", expectedAmount);
			model.put("total_receivedAmount", receivedAmount);
			model.put("total_expectedPayableAmount", expectedPayableAmount);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return new ModelAndView("/finance/finIncome/finIncome_print", model);
	}
	
	/**
	 * @Title: printFinIncomeDetail 
	 * @Description: 打印当次收费明细
	 * @author jincheng 
	 * @param caseApplyId
	 * @param finIncomeId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/printFinIncomeDetail")
	@UriKey(key = "com.zdsoft.finance.finIncome.printFinIncomeDetail")
	public ModelAndView printFinIncomeDetail(String caseApplyId,String finIncomeId) {
		ModelMap model = new ModelMap();
		try {
			CaseApply caseApply = caseApplyService.findOne(caseApplyId);
			CaseApplyVo caseApplyVo = new CaseApplyVo(caseApply);
			//获取主借人
			List<BeforeCustomer> customerList=caseApplyBeforeCustomerService.findCustomerByCaseApplyIdAndJoinType(caseApply.getId(), CaseApplyBeforeCustomer.MAIN_BORROW);
			for(BeforeCustomer cust:customerList){
				caseApplyVo.setCustomerName(cust.getCustomerName());
			}
			model.put("caseApply", caseApplyVo);
			List<FinIncomeDetailVo> feeVoList=new ArrayList<FinIncomeDetailVo>();
			BigDecimal expectedAmount=BigDecimal.ZERO;
			BigDecimal receivedAmount=BigDecimal.ZERO;
			BigDecimal expectedPayableAmount=BigDecimal.ZERO;
			List<FinIncomeDetail> detailList=finIncomeDetailService.findByFinIncomeId(finIncomeId);
			for(FinIncomeDetail detail:detailList){
					FinIncomeDetailVo feeVo=new FinIncomeDetailVo(detail);
					feeVoList.add(feeVo);
					expectedAmount=expectedAmount.add(feeVo.getExpectedAmount());
					receivedAmount=receivedAmount.add(feeVo.getReceivedAmount());
					expectedPayableAmount=expectedPayableAmount.add(ObjectHelper.isNotEmpty(feeVo.getExpectedPayableAmount())?feeVo.getExpectedPayableAmount():BigDecimal.ZERO);
			}
			model.put("detailList", feeVoList);
			model.put("total_expectedAmount", expectedAmount);
			model.put("total_receivedAmount", receivedAmount);
			model.put("total_expectedPayableAmount", expectedPayableAmount);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return new ModelAndView("/finance/finIncome/finIncomeDetail_print", model);
	}
	
	/**
	 * 
	 * @Title: feeInfomationVoList 
	 * @Description: 收费项目列表
	 * @author xiangjx 
	 * @param request
	 * @param caseApplyId
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value="/feeInfomationList")
	@UriKey(key = "com.zdsoft.finance.finance.feeInfomation.list")
	@ResponseBody
	public ResponseMsg  feeInfomationVoList(HttpServletRequest request,String caseApplyId,String payerType) throws BusinessException{
			ResponseMsg msg = new ResponseMsg();
			List<FeeInfomationVo> listVo=new ArrayList<FeeInfomationVo>();
			List<FeeInfomation> list = feeInfomationService.findByCaseApplyIdAndPayerType(caseApplyId, payerType);
			for(FeeInfomation fee:list){
				FeeInfomationVo feeVo=new FeeInfomationVo(fee);
				feeVo.setPaidAmount(BigDecimal.ZERO);
				feeVo.setFeeId(fee.getId());
				listVo.add(feeVo);
			}
			msg.setResultStatus(ResponseMsg.SUCCESS);
	        msg.setRows(listVo);
	        msg.setTotal(Long.parseLong(""+listVo.size()));
        return msg;
	}


	/**
	 * 
	 * @Title: getCustomerSimpCode 
	 * @Description: 付款人下拉
	 * @author xiangjx 
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/getCustomerSimpCode")
	@UriKey(key = "com.zdsoft.finance.finance.getCustomerSimpCode")
	@ResponseBody
	public String getCustomerSimpCode(HttpServletRequest request,String caseApplyId) throws BusinessException {
		return this.getCaseApplyCustomerJson(caseApplyId);
	}
	
	/**
	 * 
	 * @Title: getCustomerName 
	 * @Description: 获取主借人
	 * @author xiangjx 
	 * @param request
	 * @param caseApplyId
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/getCustomerName")
	@UriKey(key = "com.zdsoft.finance.finance.getCustomerName")
	@ResponseBody
	public String getCustomerName(HttpServletRequest request,String caseApplyId,String joinType) throws BusinessException {
		List<BeforeCustomer> caseApplyCustomers = caseApplyBeforeCustomerService.findCustomerByCaseApplyIdAndJoinType(caseApplyId, joinType);
		BeforePersonalCustomerVo bpv = new BeforePersonalCustomerVo();
		if (ObjectHelper.isEmpty(caseApplyCustomers)) {
			throw new BusinessException("10010002", "案件参与人员查询失败！");
		}
		for (BeforeCustomer cabc : caseApplyCustomers) {
			bpv.setCustomerName(cabc.getCustomerName());
		}
		return  ObjectHelper.objectToJson(bpv);
	}
	
	/**
	 * 
	 * 获得案件的参与人Id和name集合
	 *
	 * @author jingyh
	 * @param caseApplyId
	 * @return
	 * @throws Exception
	 */
	private String getCaseApplyCustomerJson(String caseApplyId) throws BusinessException{
		List<CaseApplyBeforeCustomer> caseApplyCustomers = caseApplyBeforeCustomerService.queryByCaseApplyId(caseApplyId);
		if (ObjectHelper.isEmpty(caseApplyCustomers)) {
			throw new BusinessException("10010002", "案件参与人员查询失败！");
		}
		Map<String,List<BeforeCustomer>> customerMap = new HashMap<String,List<BeforeCustomer>>();
		for (CaseApplyBeforeCustomer cabc : caseApplyCustomers) {
			if (!customerMap.containsKey(cabc.getJoinType())) {
				// 不存在对应的Key,新建key
				customerMap.put(cabc.getJoinType(), new ArrayList<BeforeCustomer>());
			}
			List<BeforeCustomer> list = customerMap.get(cabc.getJoinType());
			if (!list.contains(cabc.getBeforeCustomer())) {
				// 放入map中
				customerMap.get(cabc.getJoinType()).add(cabc.getBeforeCustomer());
			}
		}
		// 拼接Id和name的字符串
		Set<String> keySet = customerMap.keySet();
		Map<String,Map<String,String>> customerMapRes = new HashMap<String,Map<String,String>>();
		List<String> ids = null;
		List<String> names = null;
		for (String key : keySet) {
			Map<String,String> datas = new HashMap<String,String>();
			List<BeforeCustomer> list = customerMap.get(key);
			ids = new ArrayList<String>();
			names = new ArrayList<String>();
			for (BeforeCustomer bc : list) {
				if (!ids.contains(bc.getId())) {
					ids.add(bc.getId());
				}
				if (!names.contains(bc.getCustomerName())) {
					names.add(bc.getCustomerName());
				}
			}
			datas.put("ids", StringUtils.join(ids, ","));
			datas.put("names", StringUtils.join(names, ","));
			customerMapRes.put(key, datas);
		}
		// 转化为json
		return ObjectHelper.objectToJson(customerMapRes);
	}
	
	
	/**
	 * @Title: exportRepaymentReceiptExcel 
	 * @Description: 导出费用收费
	 * @author jincheng 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/exportFinIncomeExcel")
	@UriKey(key = "com.zdsoft.finance.finIncome.exportFinIncomeExcel")
	@ResponseBody
	public void exportFinIncomeExcel(HttpServletRequest request,HttpServletResponse response,String finIncomes)  {
		String fileName = "费用收款_" + TimeUtil.getCurrentDateInteger();
		try {
			DataOperPermDto dataOperPermDto = CED.findDataPerms(StoreHelper.getApplication(), "com.zdsoft.finance.finance.finIncome.finIncomeList.dataAuth");
			XSSFWorkbook	wb = finIncomeService.exportFinIncomeExcel(finIncomes,dataOperPermDto);
			response.reset();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/xml;charset=UTF-8");
			response.setHeader("Content-disposition", "attachment;filename="+new String(fileName.getBytes(),"iso-8859-1")+".xls");    
	        response.setContentType("application/vnd.ms-excel");    
	        OutputStream	  ouputStream = response.getOutputStream();
			wb.write(ouputStream);    
			ouputStream.flush();    
			ouputStream.close();  
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}
}
