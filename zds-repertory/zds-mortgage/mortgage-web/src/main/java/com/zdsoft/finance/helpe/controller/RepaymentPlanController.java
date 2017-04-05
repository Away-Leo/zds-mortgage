package com.zdsoft.finance.helpe.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivableInfo;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivablePlan;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivablePlanForm;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivablePlanTemp;
import com.zdsoft.finance.casemanage.receivablePlan.entity.RepayPlan;
import com.zdsoft.finance.casemanage.receivablePlan.service.ReceivablePlanService;
import com.zdsoft.finance.casemanage.receivablePlan.service.ReceivablePlanTempService;
import com.zdsoft.finance.casemanage.receivablePlan.vo.ReceivablePlanTempVo;
import com.zdsoft.finance.casemanage.receivablePlan.vo.ReceivablePlanVo;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.common.utils.BigDecimalCalculateTwo;
import com.zdsoft.finance.finance.entity.RepaymentAmountAllot;
import com.zdsoft.finance.finance.entity.RepaymentReceipt;
import com.zdsoft.finance.finance.service.RepaymentAmountAllotService;
import com.zdsoft.finance.finance.vo.CaseReceivableVo;
import com.zdsoft.finance.finance.vo.RepaymentAmountAllotVo;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.spi.receivablePlan.CaseReceivableDto;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.dto.ResultDto;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: RepaymentPlanController.java 
 * @ClassName: RepaymentPlanController 
 * @Description: 还款计划
 * @author jincheng 
 * @date 2017年2月14日 下午3:16:51 
 * @version V1.0
 */
@Controller
@RequestMapping("/repaymentPlan")
public class RepaymentPlanController extends BaseController {

    @Autowired
    private  ReceivablePlanService receivablePlanService;
    @Autowired
	private CaseApplyService caseApplyService;
    
    @Autowired
    private ReceivablePlanTempService receivablePlanTempService;
    
    @Autowired
    private RepaymentAmountAllotService repaymentAmountAllotService;

    /**
     * @Title: interviewList 
     * @Description: 还款计划工具菜单
     * @author jincheng 
     * @return
     */
    @RequestMapping("/repaymentPlanList")
	@UriKey(key = "com.zdsoft.finance.help.repaymentPlanList")
	@Menu(resource = "com.zdsoft.finance.help.repaymentPlanList", label = "还款计划工具", group = "helper", order = 1)
	public ModelAndView interviewList() {
		return new ModelAndView("/help/repaymentPlan_list");
	}
    
    /**
     * @Title: repaymentPlan 
     * @Description:  预览生成还款计划
     * @author jincheng 
     * @param planForm  生成还款计划参数对象
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/repaymentPlan")
	@UriKey(key = "com.zdsoft.finance.help.repaymentPlan")
	@ResponseBody
	public ResponseMsg  repaymentPlan(ReceivablePlanForm planForm){
			ResponseMsg msg = new ResponseMsg();
			List<RepayPlan> planList=receivablePlanService.receivablePlanGuarate(planForm);
			msg.setResultStatus(ResponseMsg.SUCCESS);
	        msg.setRows(planList);
	        msg.setTotal(Long.parseLong(""+planList.size()));
        return msg;
	}
    
    /**
     * @Title: findReceivablePlanTempByCaseApplyId 
     * @Description: 根据案件id获取还款计划(放款前)
     * @author jincheng 
     * @param caseApplyId 案件id
     * @return
     */
    @RequestMapping(value="/findReceivablePlanTempByCaseApplyId")
   	@UriKey(key = "com.zdsoft.finance.help.findReceivablePlanTempByCaseApplyId")
   	@ResponseBody
   	public ResponseMsg  findReceivablePlanTempByCaseApplyId(String caseApplyId){
   			ResponseMsg msg = new ResponseMsg();
   			List<ReceivablePlanTemp> planList=receivablePlanTempService.findReceivablePlanTempByCaseApplyId(caseApplyId);
   			List<ReceivablePlanTempVo> planVoList=new ArrayList<ReceivablePlanTempVo>();
   			for(ReceivablePlanTemp temp:planList){
   				ReceivablePlanTempVo tempVo=new ReceivablePlanTempVo(temp);
   				planVoList.add(tempVo);
   			}
   			msg.setResultStatus(ResponseMsg.SUCCESS);
   	        msg.setRows(planVoList);
   	        msg.setTotal(Long.parseLong(""+planVoList.size()));
           return msg;
   	}
    
    /**
     * @Title: importPlanExcelFile 
     * @Description: 导入还款计划
     * @author jincheng 
     * @param caseApplyId 案件id
     * @param fileID   excel文件id
     * @return
     */
    @ResponseBody
   	@RequestMapping("/importPlanExcelFile")
   	@UriKey(key = "com.zdsoft.finance.help.importPlanExcelFile")
   	public ResponseMsg importPlanExcelFile(String caseApplyId,String fileID) {
   		ResponseMsg msg = new ResponseMsg();
   		try{
   			receivablePlanTempService.importPlanExcelFile(caseApplyId, fileID);
   			msg.setResultStatus(ResultDto.SUCCESS);
   		}catch (Exception e) {
   			e.printStackTrace();
   			msg.setResultStatus(ResultDto.APP_ERROR);
   		}
   		return msg;
   	}
    
    /**
     * @Title: saveReceivablePlanTemp 
     * @Description: 保存还款计划(放款前)
     * @author jincheng 
     * @param caseApplyId
     * @param receivableInfoId
     * @param planList
     * @return
     */
    @ResponseBody
	@RequestMapping("/saveReceivablePlanTemp")
	@UriKey(key = "com.zdsoft.finance.help.saveReceivablePlanTemp")
	public ResponseMsg saveReceivablePlanTemp(String caseApplyId,String receivableInfoId,String planList) {
		ResponseMsg msg = new ResponseMsg();
		try{
			receivablePlanTempService.saveReceivablePlanTemp(caseApplyId, receivableInfoId, planList);
			msg.setResultStatus(ResultDto.SUCCESS);
		}catch (Exception e) {
			e.printStackTrace();
			msg.setResultStatus(ResultDto.APP_ERROR);
		}
		return msg;
	}
    
    /**
     * @Title: findReceivablePlanByCaseApplyId 
     * @Description: 根据案件ID获取还款计划(放款后)
     * @author jincheng 
     * @param caseApplyId 案件id
     * @return
     */
    @RequestMapping(value="/findReceivablePlanByCaseApplyId")
   	@UriKey(key = "com.zdsoft.finance.help.findReceivablePlanByCaseApplyId")
   	@ResponseBody
   	public ResponseMsg  findReceivablePlanByCaseApplyId(String caseApplyId){
   			ResponseMsg msg = new ResponseMsg();
   			List<ReceivablePlan> planList=receivablePlanService.findReceivablePlanByCaseApplyId(caseApplyId);
   			List<ReceivablePlanVo> planVoList=new ArrayList<ReceivablePlanVo>();
   			for(ReceivablePlan plan:planList){
   				ReceivablePlanVo planVo=new ReceivablePlanVo(plan);
   				planVoList.add(planVo);
   			}
   			msg.setResultStatus(ResponseMsg.SUCCESS);
   	        msg.setRows(planVoList);
   	        msg.setTotal(Long.parseLong(""+planVoList.size()));
           return msg;
   	}
    
    /**
     * 
     * @Title: findReceivablePlanByCaseApplyIdOrReqType 
     * @Description: 获取还款计划或重新生成还款计划(放款后)
     * @author zhoushichao 
     * @param receivableInfo
     * @param reqType
     * @return
     */
    @RequestMapping(value="/findReceivablePlanByCaseApplyIdOrReqType")
    @UriKey(key = "com.zdsoft.finance.help.findReceivablePlanByCaseApplyIdOrReqType")
    @ResponseBody
    public ResponseMsg  findReceivablePlanByCaseApplyIdOrReqType(ReceivableInfo receivableInfo, String reqType){
    	if (ObjectHelper.isNotEmpty(reqType)&&"0".equals(reqType)) {
    		ReceivablePlanForm planForm = new ReceivablePlanForm();
    		try {
    			CaseApply apply = caseApplyService.findOne(receivableInfo.getCaseApplyId());
				
	    		planForm.setPrincipalAmount(apply.getApplyAmount());
	    		planForm.setRepaymentDate(receivableInfo.getRepaymentDate());
	    		planForm.setLoanDate(receivableInfo.getExpectLoanDate());
	    		planForm.setRateNature(receivableInfo.getRateNature());
	    		planForm.setRepayMethod(receivableInfo.getRepaymentType());
	    		planForm.setBusinessType("1");//暂时使用
	    		Integer term;
	    		if(CaseApply.DATEUNIT_YEAR.equals(apply.getApplyTermUnit())){
	    			term = 12*apply.getApplyTerm();
	    		}else if(CaseApply.DATEUNIT_MONTH.equals(apply.getApplyTermUnit())){
	    			term = apply.getApplyTerm();
	    		}else{
	    			throw new BusinessException("暂不支持日算法");
	    		}
	    		planForm.setTerm(term);
	    		BigDecimal rate = BigDecimal.ZERO;
	    		if(ReceivableInfo.RECEIVABLEINFO_YEAR.equals(receivableInfo.getLoanMonthRateUnit())){
	    			rate = receivableInfo.getLoanMonthRate();
	    		}else if(ReceivableInfo.RECEIVABLEINFO_MONTH.equals(receivableInfo.getLoanMonthRateUnit())){
	    			rate = BigDecimalCalculateTwo.mul(receivableInfo.getLoanMonthRate(), BigDecimal.valueOf(12));
	    		}else{
	    			rate = BigDecimalCalculateTwo.mul(receivableInfo.getLoanMonthRate(), BigDecimal.valueOf(36.5));//日利息是千分号
	    		}
	    		planForm.setRate(rate);
    		} catch (BusinessException e) {
				e.printStackTrace();
				logger.error("查询还款计划失败：",e);
			}
			return this.repaymentPlan(planForm);
		}else{
			ResponseMsg msg = new ResponseMsg();
			List<ReceivablePlan> planList=receivablePlanService.findReceivablePlanByCaseApplyId(receivableInfo.getCaseApplyId());
			List<ReceivablePlanVo> planVoList=new ArrayList<ReceivablePlanVo>();
			for(ReceivablePlan plan:planList){
				ReceivablePlanVo planVo=new ReceivablePlanVo(plan);
				planVoList.add(planVo);
			}
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setRows(planVoList);
			msg.setTotal(Long.parseLong(""+planVoList.size()));
			return msg;
		}
    }
    
    /**
     * @Title: findReceivablePlanByLoanApplyId 
     * @Description: 根据放款id获取放款计划(放款后)
     * @author jincheng 
     * @param loanApplyId  放款id
     * @return
     */
    @RequestMapping(value="/findReceivablePlanByLoanApplyId")
   	@UriKey(key = "com.zdsoft.finance.help.findReceivablePlanByLoanApplyId")
   	@ResponseBody
   	public ResponseMsg  findReceivablePlanByLoanApplyId(String loanApplyId){
   			ResponseMsg msg = new ResponseMsg();
   			List<ReceivablePlan> planList=receivablePlanService.findReceivablePlanByLoanApplyId(loanApplyId);
   			List<ReceivablePlanVo> planVoList=new ArrayList<ReceivablePlanVo>();
   			for(ReceivablePlan plan:planList){
   				ReceivablePlanVo planVo=new ReceivablePlanVo(plan);
   				planVoList.add(planVo);
   			}
   			msg.setResultStatus(ResponseMsg.SUCCESS);
   	        msg.setRows(planVoList);
   	        msg.setTotal(Long.parseLong(""+planVoList.size()));
           return msg;
   	}

    
    /**
     * @Title: getCaseReceivableList 
     * @Description: 根据案件id与还款日期获取还款计划(包括罚息)
     * @author jincheng 
     * @param caseApplyId 案件id
     * @param repayDate 还款日期
     * @param isAll  是否显示所有还款计划
     * @return
     */
    @RequestMapping(value="/getCaseReceivableList")
   	@UriKey(key = "com.zdsoft.finance.help.getCaseReceivableList")
   	@ResponseBody
   	@Deprecated
   	public ResponseMsg  getCaseReceivableList(String caseApplyId,Long repayDate,boolean isAll){
   			ResponseMsg msg = new ResponseMsg();
   			List<CaseReceivableVo> voList=new ArrayList<CaseReceivableVo>();
   			List<CaseReceivableDto> dtoList=receivablePlanService.getCaseReceivableList(caseApplyId,repayDate,isAll);
   			for(CaseReceivableDto dto:dtoList){
   				CaseReceivableVo vo=new CaseReceivableVo();
   				BeanUtils.copyProperties(dto, vo);
   				voList.add(vo);
   			}
   			msg.setResultStatus(ResponseMsg.SUCCESS);
   	        msg.setRows(voList);
   	        msg.setTotal(Long.parseLong(""+voList.size()));
           return msg;
   	}
    
    
    /**
     * @Title: getRepaymentAmountAllotList 
     * @Description: 还款-获取还款计划(包括罚息)
     * @author jincheng 
     * @param receipt
     * @return
     */
    @RequestMapping(value="/getRepaymentAmountAllotList")
   	@UriKey(key = "com.zdsoft.finance.help.getRepaymentAmountAllotList")
   	@ResponseBody
   	public ResponseMsg  getRepaymentAmountAllotList(RepaymentReceipt receipt){
   			ResponseMsg msg = new ResponseMsg();
   			List<RepaymentAmountAllotVo> voList=new ArrayList<RepaymentAmountAllotVo>();
   			List<RepaymentAmountAllot> dtoList=repaymentAmountAllotService.getRepaymentAmountAllotList(receipt); 
   			for(RepaymentAmountAllot dto:dtoList){
   				RepaymentAmountAllotVo vo=new RepaymentAmountAllotVo();
   				BeanUtils.copyProperties(dto, vo);
   				voList.add(vo);
   			}
   			msg.setResultStatus(ResponseMsg.SUCCESS);
   	        msg.setRows(voList);
   	        msg.setTotal(Long.parseLong(""+voList.size()));
           return msg;
   	}

}
