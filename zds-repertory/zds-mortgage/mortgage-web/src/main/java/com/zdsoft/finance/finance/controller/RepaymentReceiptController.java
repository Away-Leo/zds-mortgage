package com.zdsoft.finance.finance.controller;

import java.io.File;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.essential.client.aop.annotation.DataAuthResource;
import com.zdsoft.essential.dto.permission.DataOperPermDto;
import com.zdsoft.finance.casemanage.datasurvey.entity.FeeInfomation;
import com.zdsoft.finance.casemanage.datasurvey.service.FeeInfomationService;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.utils.ParameterUtil;
import com.zdsoft.finance.common.utils.TimeUtil;
import com.zdsoft.finance.contract.entity.ConCaseContract;
import com.zdsoft.finance.contract.service.ConCaseContractService;
import com.zdsoft.finance.finance.entity.RepaymentAmountAllot;
import com.zdsoft.finance.finance.entity.RepaymentReceipt;
import com.zdsoft.finance.finance.entity.RepaymentUseAmount;
import com.zdsoft.finance.finance.service.RepaymentAmountAllotService;
import com.zdsoft.finance.finance.service.RepaymentReceiptService;
import com.zdsoft.finance.finance.service.RepaymentUseAmountService;
import com.zdsoft.finance.finance.vo.RepaymentReceiptVo;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.CaseApplyAfterCustomer;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.service.CaseApplyAfterCustomerService;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.marketing.vo.CaseApplyVo;
import com.zdsoft.finance.specialapprove.entity.SpecialApproveManage;
import com.zdsoft.finance.specialapprove.service.SpecialApproveManageService;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.dto.ResultDto;
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
 * @Title: RepaymentReceiptController.java 
 * @ClassName: RepaymentReceiptController 
 * @Description: 案件还款Controller 
 * @author jincheng 
 * @date 2017年2月17日 下午5:06:43 
 * @version V1.0
 */
@Controller
@RequestMapping("repaymentReceipt")
public class RepaymentReceiptController extends BaseController {
	
	@Autowired
	private RepaymentReceiptService repaymentReceiptService;
	
	@Autowired
	private CaseApplyService caseApplyService;
	
	@Autowired
	private SpecialApproveManageService  specialApproveManageService;
	
	@Autowired
	private CaseApplyAfterCustomerService caseApplyAfterCustomerService;
	
	@Autowired
	private ConCaseContractService  conCaseContractService;
	
	@Autowired
	private RepaymentAmountAllotService repaymentAmountAllotService;
	
	@Autowired
	private com.zdsoft.essential.client.service.CED CED;
	
	@Autowired
	private FeeInfomationService feeInfomationService;
	
	@Autowired
	private RepaymentUseAmountService repaymentUseAmountService;
	
	/**
	 * @Title: caseList 
	 * @Description: 案件收款
	 * @author jincheng 
	 * @return
	 */
	@RequestMapping("/caseList")
	@UriKey(key = "com.zdsoft.finance.repayment.caseList")
	@Menu(resource = "com.zdsoft.finance.repayment.caseList", label = "案件收款查询(机构)", group = "finance", order = 7)
	@DataAuthResource(resource="com.zdsoft.finance.repayment.caseList.dataAuth",label="案件收款查询(机构)",group="com.zdsoft.finance.repayment.caseList")
	public ModelAndView caseList() {
		return new ModelAndView("/finance/repayment/repayment_case_list");
	}
	
	/**
	 * @Title: repaymentList 
	 * @Description: 案件还款查询
	 * @author jincheng 
	 * @return
	 */
	@RequestMapping("/repaymentList")
	@UriKey(key = "com.zdsoft.finance.repayment.repaymentList")
	@Menu(resource = "com.zdsoft.finance.repayment.repaymentList", label = "还款收费查询", group = "finance", order = 8)
	@DataAuthResource(resource="com.zdsoft.finance.repayment.repaymentList.dataAuth",label="还款收费查询",group="com.zdsoft.finance.repayment.repaymentList")
	public ModelAndView repaymentList() {
		return new ModelAndView("/finance/repayment/repayment_paid_case_list");
	}
	

	/**
	 * @Title: exportReceivableMonthToExcel 
	 * @Description: 导出本月应还
	 * @author jincheng 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/exportReceivableMonthToExcel")
	@UriKey(key = "com.zdsoft.finance.repayment.exportReceivableMonthToExcel")
	@ResponseBody
	public void exportReceivableMonthToExcel(HttpServletRequest request,HttpServletResponse response)  {
		String fileName = "本月应还_" + TimeUtil.getCurrentDateInteger();
		try {
			String path=request.getSession().getServletContext().getRealPath(File.separator)+File.separator+"WEB-INF"+File.separator+"modules"+File.separator+"exportExcel"+File.separator+"month_receivable.xlsx"; 
			XSSFWorkbook	wb = repaymentReceiptService.exportReceivableMonthToExcel(path);
			
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
	
	
	/**
	 * @Title: exportRepaymentReceiptExcel 
	 * @Description: 导出收款复核
	 * @author jincheng 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/exportRepaymentReceiptExcel")
	@UriKey(key = "com.zdsoft.finance.repayment.exportRepaymentReceiptExcel")
	@ResponseBody
	public void exportRepaymentReceiptExcel(HttpServletRequest request,HttpServletResponse response,String receipts)  {
		String fileName = "收款复核" + TimeUtil.getCurrentDateInteger();
		try {
			XSSFWorkbook	wb = repaymentReceiptService.exportRepaymentReceiptExcel(receipts);
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
	
	
	/**
	 * @Title: applyList 
	 * @Description: 收款单明细
	 * @author jincheng 
	 * @return
	 */
	@RequestMapping("/applyList")
	@UriKey(key = "com.zdsoft.finance.repayment.applyList")
	@Menu(resource = "com.zdsoft.finance.repayment.applyList", label = "还款收费管理", group = "finance", order = 8)
	public ModelAndView applyList() {
		return new ModelAndView("/finance/repayment/repayment_apply_list");
	}
	
	
	//TODO -----------------------------------------------------------------------收款单---------------------------------------------------------------------
	/**
	 * @Title: repaymentReceiptEditPage 
	 * @Description: 新增收款单页面
	 * @author jincheng 
	 * @param caseApplyId
	 * @return
	 */
	@RequestMapping("/repaymentReceiptEditPage")
    @UriKey(key = "com.zdsoft.finance.repayment.repaymentReceiptEditPage")
    public ModelAndView repaymentReceiptEditPage(String caseApplyId,String receiptId){
    	String viewName = "/finance/repayment/repaymentReceipt_edit";
    	ModelMap model = new ModelMap();
    	try {
    		CaseApply apply=caseApplyService.findOne(caseApplyId);
    		CaseApplyVo  applyVo=new CaseApplyVo(apply);
    		model.put("caseApply", applyVo);
    		//获取主借人
			List<CaseApplyAfterCustomer> customerList=caseApplyAfterCustomerService.findByCaseApplyIdAndJoinType(applyVo.getId(), CaseApplyBeforeCustomer.MAIN_BORROW);
			for(CaseApplyAfterCustomer cust:customerList){
				applyVo.setCustomerName(cust.getAfterCustomer().getCustomerName());
			}
			ConCaseContract cct=conCaseContractService.findByCaseApplyId(applyVo.getId());
			if(ObjectHelper.isNotEmpty(cct)){
				applyVo.setContractStartDate(cct.getContractStartDate());
				applyVo.setContractEndDate(cct.getContractEndDate());
			}
			
    		RepaymentReceiptVo receiptVo=new RepaymentReceiptVo();
    		if(ObjectHelper.isNotEmpty(receiptId)){
    			RepaymentReceipt receipt=repaymentReceiptService.findOne(receiptId);
    			BeanUtils.copyProperties(receipt, receiptVo);
    		}else{
    			receiptVo=new RepaymentReceiptVo();
    			receiptVo.setBillCode("FH"+TimeUtil.getCurrentDate());
    			receiptVo.setPaidRepayDate(TimeUtil.getCurrentDateInteger().longValue());
    			receiptVo.setCollectionAmount(BigDecimal.ZERO);
    		}
    		model.put("receipt", receiptVo);
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	return new ModelAndView(viewName, model);
    }
	
	/**
	 * @Title: getRecordMethod 
	 * @Description: 重组录款方式
	 * @author jincheng 
	 * @param caseApplyId
	 * @return
	 */
	@RequestMapping(value = "/getRecordMethod")
	@UriKey(key = "com.zdsoft.finance.repayment.getRecordMethod")
	@ResponseBody
	public String getRecordMethod(String caseApplyId,boolean isSettent)  {
		List<Map<String,String>> mapList=new ArrayList<Map<String,String>>();
		Map<String,String> map1=new HashMap<String,String>();
		map1.put("id", "YWDM009401");
		map1.put("text", "按系统规则录入金额");
		mapList.add(map1);
		
		//判断是否走自由录入特批
		List<SpecialApproveManage> samList=specialApproveManageService.findByCaseApplyId(caseApplyId);
		if(ObjectHelper.isNotEmpty(samList)){
			for(SpecialApproveManage sam:samList){
				if(SpecialApproveManage.SPECIAL_APPROVE_STATUS_ADOPT.equals(sam.getSpecialApproveStatus())&&"2".equals(sam.getSpecialApproveType())&&sam.isAvailable()){
					Map<String,String> map2=new HashMap<String,String>();
					map2.put("id", "YWDM009402");
					map2.put("text", "不按规则自由录入");
					mapList.add(map2);
					break;
				}
			}
		}
		
		if(isSettent){
			Map<String,String> map3=new HashMap<String,String>();
			map3.put("id", "YWDM009403");
			map3.put("text", "保证金转供款");
			mapList.add(map3);
		}
		
		Map<String,String> map4=new HashMap<String,String>();
		map4.put("id", "YWDM009404");
		map4.put("text", "预收款转供款");
		mapList.add(map4);
		return ObjectHelper.objectToJson(mapList);
	}
	
	
	/**
	 * @Title: repaymentReceiptReviewPage 
	 * @Description: 还款复核
	 * @author jincheng 
	 * @param receiptId
	 * @return
	 */
	@RequestMapping("/repaymentReceiptReviewPage")
    @UriKey(key = "com.zdsoft.finance.repayment.repaymentReceiptReviewPage")
    public ModelAndView repaymentReceiptReviewPage(String receiptId,boolean scan){
    	String viewName = "/finance/repayment/repaymentReceipt_view";
    	ModelMap model = new ModelMap();
    	try {
    		RepaymentReceipt receipt=repaymentReceiptService.findOne(receiptId);
    		RepaymentReceiptVo receiptVo = new RepaymentReceiptVo(receipt);
    		if(receipt.getReceiptType()==2){
    			 viewName = "/finance/repayment/repaymentAdvanceReceipt_view";
    		}
    		CaseApply apply=caseApplyService.findOne(receipt.getCaseApplyId());
    		CaseApplyVo  applyVo=new CaseApplyVo(apply);
    		//获取主借人
			List<CaseApplyAfterCustomer> customerList=caseApplyAfterCustomerService.findByCaseApplyIdAndJoinType(applyVo.getId(), CaseApplyBeforeCustomer.MAIN_BORROW);
			for(CaseApplyAfterCustomer cust:customerList){
				applyVo.setCustomerName(cust.getAfterCustomer().getCustomerName());
			}
			model.put("caseApply", applyVo);
    		model.put("receipt", receiptVo);
    		model.put("scan", scan);
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	return new ModelAndView(viewName, model);
    }
	
	
	
	/**
	 * @Title: findRepaymentAmountAllotByReceiptId 
	 * @Description: 获取还款分配
	 * @author jincheng 
	 * @param request
	 * @param pageable
	 * @return
	 */
	@RequestMapping(value="/findRepaymentAmountAllotByReceiptId")
	@UriKey(key = "com.zdsoft.finance.repayment.findRepaymentAmountAllotByReceiptId")
	@ResponseBody
	public ResponseMsg  findRepaymentAmountAllotByReceiptId(String receiptId) {
	        ResponseMsg msg = new ResponseMsg();
			List<RepaymentAmountAllot>	page = repaymentAmountAllotService.findByReceiptId(receiptId);
			msg.setResultStatus(ResponseMsg.SUCCESS);
	        msg.setRows(page);
	        msg.setTotal(Long.parseLong(""+page.size()));
        return msg;
	}
	
	
	/**
	 * @Title: saveOrUpdateRepaymentReceipt 
	 * @Description: 新增或修改还款
	 * @author jincheng 
	 * @param receiptVo
	 * @param receiptList
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveOrUpdateRepaymentReceipt")
	@UriKey(key = "com.zdsoft.finance.repayment.saveOrUpdateRepaymentReceipt")
	public ResponseMsg saveOrUpdateRepaymentReceipt(RepaymentReceiptVo  receiptVo,String receiptList) {
		ResponseMsg msg = new ResponseMsg();
		try{
			RepaymentReceipt feceipt=receiptVo.toPo();
			feceipt=repaymentReceiptService.saveOrUpdateRepaymentReceipt(feceipt,receiptList); 
			msg.setId(feceipt.getId());
			msg.setResultStatus(ResultDto.SUCCESS);
		}catch (Exception e) {
			e.printStackTrace();
			msg.setResultStatus(ResultDto.APP_ERROR);
		}
		return msg;
	}
	
	/**
	 * @Title: findByCaseApplyIdAndReceiptTypeAndStatus 
	 * @Description: 根据案件id获取预收款
	 * @author jincheng 
	 * @param caseApplyId
	 * @param receiptType
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findByCaseApplyIdAndReceiptTypeAndStatus")
	@UriKey(key = "com.zdsoft.finance.repayment.findByCaseApplyIdAndReceiptTypeAndStatus")
	public String findByCaseApplyIdAndReceiptTypeAndStatus(String caseApplyId,String receiptType) {
			List<RepaymentReceipt> rrtList=repaymentReceiptService.findByCaseApplyIdAndReceiptTypeAndStatus(caseApplyId,2,2); 
			BigDecimal receiptAmount=BigDecimal.ZERO;
			for(RepaymentReceipt rrt:rrtList){
				List<RepaymentUseAmount> ruaList=repaymentUseAmountService.findByFeeAmountId(rrt.getId());
				if(ObjectHelper.isEmpty(ruaList)||ruaList.size()==0){
					receiptAmount=receiptAmount.add(rrt.getCollectionAmount());
				}
			}
		return ObjectHelper.objectToJson(receiptAmount);
	}
	
	/**
	 * @Title: findByCaseApplyIdCash 
	 * @Description: 保证金转供款
	 * @author jincheng 
	 * @param caseApplyId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findByCaseApplyIdCash")
	@UriKey(key = "com.zdsoft.finance.repayment.findByCaseApplyIdCash")
	public String findByCaseApplyIdCash(String caseApplyId) {
		BigDecimal amount=BigDecimal.ZERO;
			try {
				List<FeeInfomation> rrtList= feeInfomationService.findByCaseApplyId(caseApplyId);
				for(FeeInfomation rrt:rrtList){
					if("FYDM000003".equals(rrt.getFeeItem())){
						List<RepaymentUseAmount> ruaList=repaymentUseAmountService.findByFeeAmountId(rrt.getId());
						if(ObjectHelper.isEmpty(ruaList)||ruaList.size()==0){
							amount=amount.add(rrt.getReceivedAmount());
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return ObjectHelper.objectToJson(amount);
	}
	
	
	/**
	 * @Title: deleteRepaymentReceipt 
	 * @Description: 删除收款单
	 * @author jincheng 
	 * @param receiptId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteRepaymentReceipt")
	@UriKey(key = "com.zdsoft.finance.repayment.deleteRepaymentReceipt")
	public ResponseMsg deleteRepaymentReceipt(String receiptId) {
		ResponseMsg msg = new ResponseMsg();
		try{
			repaymentReceiptService.deleteRepaymentReceipt(receiptId); 
			msg.setResultStatus(ResultDto.SUCCESS);
		}catch (Exception e) {
			e.printStackTrace();
			msg.setResultStatus(ResultDto.APP_ERROR);
		}
		return msg;
	}
	
	//TODO -----------------------------------------------------------------------预收款---------------------------------------------------------------------
	@RequestMapping("/repaymentAdvanceReceiptEditPage")
    @UriKey(key = "com.zdsoft.finance.repayment.repaymentAdvanceReceiptEditPage")
    public ModelAndView repaymentAdvanceReceiptEditPage(String caseApplyId,String receiptId){
    	String viewName = "/finance/repayment/repaymentAdvanceReceipt_edit";
    	ModelMap model = new ModelMap();
    	try {
    		CaseApply apply=caseApplyService.findOne(caseApplyId);
    		CaseApplyVo  applyVo=new CaseApplyVo(apply);
    		model.put("caseApply", applyVo);
    		//获取主借人
			List<CaseApplyAfterCustomer> customerList=caseApplyAfterCustomerService.findByCaseApplyIdAndJoinType(applyVo.getId(), CaseApplyBeforeCustomer.MAIN_BORROW);
			for(CaseApplyAfterCustomer cust:customerList){
				applyVo.setCustomerName(cust.getAfterCustomer().getCustomerName());
			}
			ConCaseContract cct=conCaseContractService.findByCaseApplyId(applyVo.getId());
			if(ObjectHelper.isNotEmpty(cct)){
				applyVo.setContractStartDate(cct.getContractStartDate());
				applyVo.setContractEndDate(cct.getContractEndDate());
			}
    		RepaymentReceiptVo receiptVo=new RepaymentReceiptVo();
    		if(ObjectHelper.isNotEmpty(receiptId)){
    			RepaymentReceipt receipt=repaymentReceiptService.findOne(receiptId);
    			BeanUtils.copyProperties(receipt, receiptVo);
    		}else{
    			receiptVo=new RepaymentReceiptVo();
    			receiptVo.setBillCode("FK"+TimeUtil.getCurrentDate());
    			receiptVo.setPaidRepayDate(TimeUtil.getCurrentDateInteger().longValue());
    		}
    		model.put("receipt", receiptVo);
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	return new ModelAndView(viewName, model);
    }
	
	
	//TODO -----------------------------------------------------------------------收款复核---------------------------------------------------------------------
	/**
	 * @Title: reviewList 
	 * @Description: 收款单复核
	 * @author jincheng 
	 * @return
	 */
	@RequestMapping("/reviewList")
	@UriKey(key = "com.zdsoft.finance.repayment.reviewList")
	@Menu(resource = "com.zdsoft.finance.repayment.reviewList", label = "还款收费复核", group = "finance", order =9)
	public ModelAndView reviewList() {
		return new ModelAndView("/finance/repayment/repayment_review_list");
	}
	
	/**
	 * 
	 * @Title: updateStatus 
	 * @Description: 复核状态确认
	 * @author xiangjx 
	 * @param id
	 * @param status
	 * @return
	 */
	@RequestMapping(value="/updateStatus")
	@UriKey(key = "com.zdsoft.finance.repayment.updateStatus")
	@ResponseBody
	public ResponseMsg  updateStatus(String id,Integer status) {
        ResponseMsg msg = new ResponseMsg();
        try {
			repaymentReceiptService.updateStatus(id,status);
			msg.setResultStatus(ResponseMsg.SUCCESS);
        } catch (Exception e) {
        	msg.setResultStatus(ResponseMsg.APP_ERROR);
			e.printStackTrace();
		}
        return msg;
	}
	
	/**
	 * 
	 * @Title: repaymentApplyList 
	 * @Description: 收费详单/复核  列表数据
	 * @author xiangjx 
	 * @param request
	 * @param pageRequest
	 * @return
	 */
	@RequestMapping(value="/repaymentApplyList")
	@UriKey(key = "com.zdsoft.finance.repayment.repaymentApplyList")
	@ResponseBody
	public ResponseMsg  repaymentApplyList(HttpServletRequest request,PageRequest pageRequest){
	        ResponseMsg msg = new ResponseMsg();
	        try {
				DataOperPermDto dataOperPermDto = CED.findDataPerms(StoreHelper.getApplication(), "com.zdsoft.finance.repayment.repaymentList.dataAuth");
				List<QueryObj> li = ParameterUtil.getQueryObjByParameter(request, new String[]{ "receipt","mca","cc","cla","pp"});
				Page<Map<String, Object>>	page = repaymentReceiptService.repaymentApplyList(pageRequest, li,dataOperPermDto);
				msg.setResultStatus(ResponseMsg.SUCCESS);
		        msg.setRows(page.getRecords());
		        msg.setTotal(Long.parseLong(""+page.getTotalRows()));
			} catch (AppException e) {
				e.printStackTrace();
			}
        return msg;
	}
	
	
}
