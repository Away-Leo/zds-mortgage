package com.zdsoft.finance.casemanage.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.zdsoft.bpm.annotation.client.FinishJob;
import com.zdsoft.bpm.annotation.client.ManualJob;
import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.businesssetting.entity.ExceptMatter;
import com.zdsoft.finance.businesssetting.service.ExceptMatterService;
import com.zdsoft.finance.casemanage.datasurvey.entity.FeeInfomation;
import com.zdsoft.finance.casemanage.datasurvey.entity.RiskInfomation;
import com.zdsoft.finance.casemanage.datasurvey.service.FeeInfomationService;
import com.zdsoft.finance.casemanage.datasurvey.vo.RiskInformationVo;
import com.zdsoft.finance.casemanage.loanauditsheet.entity.PledgeTypeEnum;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivablePlanTemp;
import com.zdsoft.finance.casemanage.receivablePlan.service.ReceivablePlanTempService;
import com.zdsoft.finance.casemanage.vo.FeeRulesVo;
import com.zdsoft.finance.casemanage.vo.HousePropertyPledgeInfoVo;
import com.zdsoft.finance.casemanage.vo.RiskRulesVo;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.common.utils.BigDecimalCalculateTwo;
import com.zdsoft.finance.credit.entity.CustomerCreditStatistics;
import com.zdsoft.finance.credit.service.CustomerCreditStatisticsService;
import com.zdsoft.finance.customer.entity.BeforeAddress;
import com.zdsoft.finance.customer.entity.BeforePersonalAssociation;
import com.zdsoft.finance.customer.entity.BeforePersonalCustomer;
import com.zdsoft.finance.customer.service.BeforePersonalAssociationService;
import com.zdsoft.finance.customer.service.BeforePersonalCustomerService;
import com.zdsoft.finance.customer.vo.BeforeAddressVo;
import com.zdsoft.finance.customer.vo.BeforePersonalCustomerVo;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.entity.CaseApplyStageEnumSimpleCodeEnum;
import com.zdsoft.finance.marketing.entity.HouseProperty;
import com.zdsoft.finance.marketing.entity.PledgeInfo;
import com.zdsoft.finance.marketing.service.CaseApplyBeforeCustomerService;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.marketing.service.HousePropertyService;
import com.zdsoft.finance.marketing.service.PledgeInfoService;
import com.zdsoft.finance.marketing.vo.CaseApplyVo;
import com.zdsoft.finance.marketing.vo.HousePropertyVo;
import com.zdsoft.finance.specialapprove.entity.FeeRules;
import com.zdsoft.finance.specialapprove.entity.RiskRules;
import com.zdsoft.finance.specialapprove.entity.ScoreCardRisk;
import com.zdsoft.finance.specialapprove.entity.SpecialApproveManage;
import com.zdsoft.finance.specialapprove.entity.SpecialApproveThings;
import com.zdsoft.finance.specialapprove.service.FeeRulesService;
import com.zdsoft.finance.specialapprove.service.OrganizationRiskService;
import com.zdsoft.finance.specialapprove.service.RiskRulesService;
import com.zdsoft.finance.specialapprove.service.ScoreCardRiskService;
import com.zdsoft.finance.specialapprove.service.SpecialApproveManageService;
import com.zdsoft.finance.specialapprove.vo.SpecialApproveThingsVo;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * @ClassName LoanAuditSheetController
 * @Description 贷审审批单Controller
 * @author Liyb
 * @Date 2017年1月14日 下午3:42:16
 * @version 1.0.0
 */
@Controller
@RequestMapping("casemanage/loanauditsheet")
public class LoanAuditSheetController extends BaseController {

		@Autowired
	    private CaseApplyService caseApplyService;

	    @Autowired
	    private CaseApplyBeforeCustomerService caseApplyBeforeCustomerService;

	    @Autowired
	    private BeforePersonalCustomerService beforePersonalCustomerService;

	    @Autowired
	    private HousePropertyService housePropertyService;

	    @Autowired
	    private PledgeInfoService pledgeInfoService;
	    @Autowired
	    CED CED;
		@Autowired
		private FeeRulesService feeRulesService;
		@Autowired
		private RiskRulesService riskRulesService;
		@Autowired
		private ScoreCardRiskService scoreCardRiskService;
		@Autowired
		private BeforePersonalAssociationService beforePersonalAssociationService;
		@Autowired
		private SpecialApproveManageService specialApproveManageService;
		@Autowired
		private ReceivablePlanTempService receivablePlanTempService;
		@Autowired
		private OrganizationRiskService organizationRiskService;
		
		@Autowired
		private ExceptMatterService exceptMatterService;
		@Autowired
		private CustomerCreditStatisticsService customerCreditStatisticsService;
		@Autowired
		private FeeInfomationService feeInfomationService;
		
		/**
		 * 
		 * @Title: initLoanAuditSheet 
		 * @Description: TODO 案件贷审审批单信息组装
		 * @author jingjiyan 
		 * @param request 
		 * @param projectId
		 * 			案件ID
		 * @param processInstanceId
		 * 			流程实例ID
		 * @param businessKey
		 * 			业务ID
		 * @return
		 */
	    @RequestMapping("/initLoanAuditSheet")
	    @UriKey(key = "com.zdsoft.finance.casemanage.loanauditsheet.initLoanAuditSheet")
	    @ManualJob(resource="com.zdsoft.finance.casemanage.initLoanAuditSheet",label="贷审审批单")
	    @FinishJob(resource="com.zdsoft.finance.casemanage.initLoanAuditSheet",businessId="businessKey",projectId="projectId")
	    public ModelAndView initLoanAuditSheet(HttpServletRequest request,String projectId, String processInstanceId, String businessKey) {
    	  ModelAndView mv = new ModelAndView("/casemanage/loanAuditSheet/loan_sheet_audit");
          try {
        	  BigDecimal principalAndInterestAmount =BigDecimal.ZERO;//每月还款额
              // 案件基本信息
              CaseApply caseApplyPo = caseApplyService.findOne(projectId);
              // 案件基本信息-转换VO
              CaseApplyVo caseApply = new CaseApplyVo(caseApplyPo, new String[] {}, new String[] {"capitalUseFor","applyTermUnit","repayMethod","applyRateUnit","stage","synthesizeRateUnit","overdueRateUnit" });
              // 获取主借人
              List<CaseApplyBeforeCustomer> mainCustomers = caseApplyBeforeCustomerService
                      .queryByCaseApplyIdAndJoinType(projectId, CaseApplyBeforeCustomer.MAIN_BORROW);
              // 主借人
              String mainCustomerName = "";
              String mainCustomerId="";
              if (!CollectionUtils.isEmpty(mainCustomers)) {
                  CaseApplyBeforeCustomer mainCustomer = mainCustomers.get(0);
                  mainCustomerId=mainCustomer.getBeforeCustomer().getId();
                  mainCustomerName = mainCustomer.getBeforeCustomer().getCustomerName();
              }
              List<ReceivablePlanTemp>  tmp = receivablePlanTempService.findReceivablePlanTempByCaseApplyId(projectId);
              if(ObjectHelper.isNotEmpty(tmp) && tmp.size()>0 && ("YWDM0051006".equals(caseApply.getRepayMethod()) || "YWDM0051007".equals(caseApply.getRepayMethod()) )){
            	  principalAndInterestAmount = BigDecimalCalculateTwo.add(tmp.get(0).getPlanPrincipalAmount(), tmp.get(0).getPlanInterestAmount());//每月还款额
              }
              caseApply.setCustomerName(mainCustomerName);
              // 参与人列表
              List<BeforePersonalCustomer> beforeCustomerPoList = beforePersonalCustomerService
                      .queryByCaseApplyId(projectId);
              // 参与人列表 转换VO
              List<BeforePersonalCustomerVo> beforeCustomerList = new ArrayList<>();
              for (BeforePersonalCustomer beforePersonalCustomer : beforeCustomerPoList) {
//              	BeforeAddress.
                  BeforePersonalCustomerVo beforePersonalCustomerVo = new BeforePersonalCustomerVo(beforePersonalCustomer,
                          new String[] {}, new String[] {"gender", "maritalStatus", "relationship", "joinType"});
                  List<BeforeAddress> addressList = beforePersonalCustomer.getBeforeAddresss();
                  for(BeforeAddress address:addressList){
                  	if(BeforeAddress.HOUSEHOLD_REGISTRATION_ADDRESS.equals(address.getAddressType())){
                  		BeforeAddressVo vo = new BeforeAddressVo(address);
                  		beforePersonalCustomerVo.setPermanentAddress(vo.getFullAddress());
                  	}
                  }
                  if(!mainCustomerId.equals(beforePersonalCustomer.getId())){
                  	BeforePersonalAssociation  association =	beforePersonalAssociationService.loadByAssociation(mainCustomerId,beforePersonalCustomer.getId());
                  	if(ObjectHelper.isNotEmpty(association)){
                  		beforePersonalCustomerVo.setRelationship(association.getRelationship());
                  		beforePersonalCustomerVo.setRelationshipName(CED.loadSimpleCodeNameByFullCode(association.getRelationship()));
                  	}
                  }
                  if(mainCustomerId.equals(beforePersonalCustomer.getId())){
                		beforePersonalCustomerVo.setRelationshipName("本人");
                	}
                  beforeCustomerList.add(beforePersonalCustomerVo);
              }
              // 偿债能力
              // TODO:模拟数据换为真实数据
            Map<String,Object> solvencyMap = organizationRiskService.getSolvencyInfo(projectId);
          String assetSituation  = mainCustomerName +"名下"+solvencyMap.get("total")+"笔资产，资产总值共计"+solvencyMap.get("sumAoumt")+"元。";
          List <String> mainCustomerIds = new ArrayList <String>();
          mainCustomerIds.add(mainCustomerId);
          List<CustomerCreditStatistics>  customerCredits = customerCreditStatisticsService.
        		  findByCustomerIdsAndCaseApplyIdAndCaseApplyStage(projectId, mainCustomerIds, CaseApplyStageEnumSimpleCodeEnum.EXAMINE.value);
          CustomerCreditStatistics  customerCredit = null;
          String stringCredit = "";
          if(ObjectHelper.isNotEmpty(customerCredits) && customerCredits.size()>0){
        	 for(CustomerCreditStatistics credit:customerCredits){
	        	  if(CustomerCreditStatistics.SOURCE_OFFLINE.equals(credit.getSourceFrom())){
	        		     customerCredit = credit;
	        		     break;
	        	  }else{
	        		  	 customerCredit = credit;
	        	  }
        	 }
        	String iSExcessiveCredit = ObjectHelper.isEmpty(customerCredit.getiSExcessiveCredit()) && !customerCredit.getiSExcessiveCredit()  ?"已" : "未";
             stringCredit = "贷款笔数"+customerCredit.getLoanTotalNum()+"笔，已结清"+customerCredit.getEndLoanTotalNum()+"笔，"
             		+ "未结清"+customerCredit.getLoaningTotalNum()+"笔，余额"+customerCredit.getLoaningTotalAmount()+"元；"
          		+ "信用卡张数"+customerCredit.getCreditApplyNum()+"张，已使用金额"+customerCredit.getCreditUsedAmount()+"元，使用率"+customerCredit.getCreditUsedRate()+"%；对外担保"+customerCredit.getExternalGuaranteeNum()+"笔，对外担保余额"+customerCredit.getExternalGuaranteeAmount()+"元；"
          		+ "一年内最高逾期累"+customerCredit.getYearOverNum()+"高"+customerCredit.getYearMaxOverNum()+"，有"+customerCredit.getExcessiveBadNum()+"呆账、冻结"+customerCredit.getExcessiveFreezeNum()+"记录，征信记录"+ iSExcessiveCredit +"超标。";
          }
          
           // 风险信息
           			RiskInfomation riskInfomation = caseApplyPo.getRiskInfo();

           			if (ObjectHelper.isNotEmpty(riskInfomation)) {
           				caseApply.setRiskInfoVo(new RiskInformationVo(caseApplyPo.getRiskInfo()));
           			}
           	        List<HouseProperty> housePropertyPoList = housePropertyService.findByCaseApplyId(projectId);
           	        // 抵押物列表转换VO
           	        List<HousePropertyVo> housePropertyList = new ArrayList<>();
           	        for (HouseProperty houseProperty : housePropertyPoList) {
           	            HousePropertyVo housePropertyVo = new HousePropertyVo(houseProperty,
           	                    new String[] {}, new String[] {"estateProperties", "province", "city", "district"});
           	            housePropertyList.add(housePropertyVo);
           	        }
           	        // 抵押情况列表
           	        List<HousePropertyPledgeInfoVo> casePledgeInfoList = new ArrayList<>();
           	        for (HouseProperty houseProperty : housePropertyPoList) {
           	            // 将同一抵押物的抵押情况记录归并到一条
           	            HousePropertyPledgeInfoVo hpPledgeInfoVo = new HousePropertyPledgeInfoVo();
           	
           	            hpPledgeInfoVo.setHousePropertyId(houseProperty.getId());
           	
           	            List<PledgeInfo> pledgeInfos = pledgeInfoService.findByHouseId(houseProperty.getId());
           	            for (PledgeInfo pledgeInfo : pledgeInfos) {
           	                // 一抵
           	                if (pledgeInfo.getPledgeType().equals(PledgeTypeEnum.FIRST_MORTGAGE.getKey())) {
           	                    hpPledgeInfoVo.setFirstLoanBalance(pledgeInfo.getLoanBalance());
           	                    hpPledgeInfoVo.setFirstLoanBank(pledgeInfo.getLoanBank());
           	                }
           	                // 二抵
           	                if (pledgeInfo.getPledgeType().equals(PledgeTypeEnum.SECOND_MORTGAGE.getKey())) {
           	                    hpPledgeInfoVo.setSecondLoanBank(pledgeInfo.getLoanBank());
           	                    hpPledgeInfoVo.setSecondLoanBalance(pledgeInfo.getLoanBalance());
           	                }
           	                if(ObjectHelper.isNotEmpty(caseApply.getLoanNumber())){//成数
           	                	hpPledgeInfoVo.setPercentage(new BigDecimal(caseApply.getLoanNumber()));
           	                }
           					if(ObjectHelper.isEmpty(hpPledgeInfoVo.getFloorAge())){//楼龄
           						hpPledgeInfoVo.setFloorAge(houseProperty.getFloorAge()); 	
           					}
           					if(ObjectHelper.isEmpty(hpPledgeInfoVo.getControlPrice())){//风控核定价
           						hpPledgeInfoVo.setControlPrice(houseProperty.getControlPrice());
           					}
           					if(ObjectHelper.isEmpty(hpPledgeInfoVo.getLivingState()) && ObjectHelper.isNotEmpty(houseProperty.getLivingState())){//居住状态
          						hpPledgeInfoVo.setLivingState(houseProperty.getLivingState());
          						hpPledgeInfoVo.setLivingStateName(CED.loadSimpleCodeNameByFullCode(houseProperty.getLivingState()));
          					}
           					if(ObjectHelper.isEmpty(hpPledgeInfoVo.getApplyAmount())){//居住状态
           						hpPledgeInfoVo.setApplyAmount(caseApplyPo.getApplyAmount());
           					}
           	
           	            }
           	            casePledgeInfoList.add(hpPledgeInfoVo);
           	        }
           	       

              // 抵押物综述
              // TODO:模拟数据换为真实数据

              // 风险特批
              // TODO:模拟数据换为真实数据
              JSONArray riskSpApprovalList = new JSONArray();

              List<FeeRules> feeList =  feeRulesService.findFeeRulesInfoByCaseApplyId(projectId);//费用数据
              List<FeeRulesVo> fvoList = new ArrayList<FeeRulesVo>();
              String feeType = "";
              BigDecimal expectedAmount =BigDecimal.ZERO;//应收金额
              BigDecimal receivedAmount =BigDecimal.ZERO;//实收金额
               for(FeeRules rules:feeList){
               	FeeRulesVo vo =	new	FeeRulesVo(rules);
               	FeeInfomation feeInfomation = feeInfomationService.findOne(rules.getReceiveFeeId());
	        	vo.setFeeItemName(feeInfomation.getFeeItemName());
	        	vo.setFeePerson(feeInfomation.getFeeeObjectName());
               	fvoList.add(vo);
               	expectedAmount=BigDecimalCalculateTwo.add(expectedAmount, vo.getExpectedAmount());
               	receivedAmount=BigDecimalCalculateTwo.add(receivedAmount, vo.getStandardAmount());
               	String feeItemName = vo.getFeeItemName();
               	if(feeItemName.indexOf(feeType)==-1 || ObjectHelper.isEmpty(feeType) ){
               		feeType += vo.getFeeItemName()+"，";
               	}
               }
               if(ObjectHelper.isNotEmpty(feeType)){
           		feeType=feeType.substring(0, feeType.length()-1);
           	}
               List<RiskRules> riskList =  riskRulesService.findRiskRulesInfoByCaseApplyId(projectId);//规则数据
               List<RiskRulesVo> rvoList = new ArrayList<RiskRulesVo>();
                for(RiskRules risk:riskList){
               	 RiskRulesVo vo =	new	RiskRulesVo(risk);
               	 //通过风险规则编码查询名称
               	 ExceptMatter exceptMatter = exceptMatterService.findByExceptMattercode(risk.getRulesType());
               	 if (ObjectHelper.isNotEmpty(exceptMatter)) {
               		 vo.setRulesTypeName(exceptMatter.getExceptMatterName());
               	 }
                	rvoList.add(vo);
                }
                List<SpecialApproveManage> thingList =  specialApproveManageService.findByCaseApplyId(projectId);//特批事项
                List<SpecialApproveThingsVo> tvoList = new ArrayList<SpecialApproveThingsVo>();
                for(SpecialApproveManage manage:thingList){
//                	SpecialApproveManageVo manageVo = new SpecialApproveManageVo(manage,new String[]{"itemType"});
                	SpecialApproveThingsVo vo = new SpecialApproveThingsVo();
                	String itemTypeName = "";
                	String riskTypeName = "";
                	List<SpecialApproveThings> thingsList = manage.getListSpecialApproveThings();
	                for(SpecialApproveThings risk:thingsList){
	               	 SpecialApproveThingsVo oldVo =	new SpecialApproveThingsVo(risk,new String[]{""},new String[]{"itemType","specialApproveStatus"});
	               	 if("YWDM007706".equals(oldVo.getItemType())){
	               		riskTypeName += oldVo.getItemName()+",";
	               	 }else{
	               		 itemTypeName += oldVo.getItemName()+",";
	               	 }
	                }
	                vo.setSpecialApproveStatus(CED.loadSimpleCodeNameByFullCode(manage.getSpecialApproveStatus()));
	                vo.setApplyDate(manage.getCreateTime()+"");
	                if (ObjectHelper.isNotEmpty(riskTypeName)) {
	                	vo.setRiskItemTypeName(riskTypeName.substring(0,riskTypeName.length()-1));
					}
	                if (ObjectHelper.isNotEmpty(itemTypeName)) {
	                	vo.setItemTypeName(itemTypeName.substring(0,itemTypeName.length()-1));
					}
	                tvoList.add(vo);
                }
                
                
              List<ScoreCardRisk> scoreList =  scoreCardRiskService.findByCaseApplyId(projectId);//评分卡数据
              mv.addObject("principalAndInterestAmount", principalAndInterestAmount);//每月还款额
              mv.addObject("assetSituation", assetSituation);
              mv.addObject("stringCredit", stringCredit);//征信情况
              mv.addObject("feeType", feeType);
              mv.addObject("expectedAmount", expectedAmount);
              mv.addObject("receivedAmount", receivedAmount);
              mv.addObject("caseApply", caseApply);//案件信息
              mv.addObject("mainCustomerName", mainCustomerName);
              mv.addObject("beforeCustomerList", beforeCustomerList);//主借人、担保人信息
              mv.addObject("housePropertyList", housePropertyList);//房产信息
              mv.addObject("casePledgeInfoList", casePledgeInfoList);//押品信息
              mv.addObject("tvoList", tvoList);//风险特批信息
              mv.addObject("feeList", fvoList);
              mv.addObject("riskList", rvoList);
              mv.addObject("scoreList", scoreList);
              mv.addObject("riskSpApprovalList", riskSpApprovalList);
              mv.addObject("projectId", projectId);
              mv.addObject("businessKey", businessKey);
              mv.addObject("processInstanceId", processInstanceId);
          } catch (BusinessException e) {
              logger.error("error:", e);
          } catch (Exception e) {
              logger.error("error:", e);
          }
          return mv;
    }

}
