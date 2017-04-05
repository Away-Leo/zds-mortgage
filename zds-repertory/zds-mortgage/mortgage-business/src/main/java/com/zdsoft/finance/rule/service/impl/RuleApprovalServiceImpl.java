package com.zdsoft.finance.rule.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.finance.businesssetting.entity.Questionnaire;
import com.zdsoft.finance.businesssetting.service.QuestionnaireService;
import com.zdsoft.finance.casemanage.datasurvey.entity.FeeInfomation;
import com.zdsoft.finance.casemanage.datasurvey.service.FeeInfomationService;
import com.zdsoft.finance.casemanage.promotion.otherproperty.entity.CustomerHouse;
import com.zdsoft.finance.casemanage.promotion.otherproperty.service.CustomerHouseService;
import com.zdsoft.finance.credit.entity.CreditSituation;
import com.zdsoft.finance.credit.entity.CustomerCreditStatistics;
import com.zdsoft.finance.credit.entity.HmCreditCardInfo;
import com.zdsoft.finance.credit.entity.HmLoanInfo;
import com.zdsoft.finance.credit.entity.HmReportBasics;
import com.zdsoft.finance.credit.service.CreditManageService;
import com.zdsoft.finance.credit.service.CreditSituationService;
import com.zdsoft.finance.credit.service.CustomerCreditStatisticsService;
import com.zdsoft.finance.customer.entity.BeforeAddress;
import com.zdsoft.finance.customer.entity.BeforeCustomer;
import com.zdsoft.finance.customer.entity.BeforePersonalAssociation;
import com.zdsoft.finance.customer.entity.BeforePersonalCustomer;
import com.zdsoft.finance.customer.entity.BeforeWorkUnit;
import com.zdsoft.finance.customer.entity.Credit;
import com.zdsoft.finance.customer.service.BeforeAddressService;
import com.zdsoft.finance.customer.service.BeforePersonalAssociationService;
import com.zdsoft.finance.customer.service.BeforeWorkUnitService;
import com.zdsoft.finance.customer.service.CreditService;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.entity.CaseApplyStageEnumSimpleCodeEnum;
import com.zdsoft.finance.marketing.entity.HouseProperty;
import com.zdsoft.finance.marketing.entity.PropertyOwner;
import com.zdsoft.finance.marketing.service.CaseApplyBeforeCustomerService;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.marketing.service.HousePropertyService;
import com.zdsoft.finance.marketing.service.PropertyOwnerService;
import com.zdsoft.finance.risk.entity.DaasBasic;
import com.zdsoft.finance.risk.entity.HuifaRequest;
import com.zdsoft.finance.risk.entity.HuifaResultInfo;
import com.zdsoft.finance.risk.huifa.service.BusinessDetailService;
import com.zdsoft.finance.risk.huifa.service.HuifaDetailService;
import com.zdsoft.finance.risk.huifa.service.bo.BusinessDetailBo;
import com.zdsoft.finance.rule.enums.RuleApprovalFinal;
import com.zdsoft.finance.rule.service.RuleApprovalService;
import com.zdsoft.finance.specialapprove.entity.FeeRules;
import com.zdsoft.finance.specialapprove.entity.RiskRules;
import com.zdsoft.finance.specialapprove.entity.ScoreCardRisk;
import com.zdsoft.finance.specialapprove.service.FeeRulesService;
import com.zdsoft.finance.specialapprove.service.RiskRulesService;
import com.zdsoft.finance.specialapprove.service.ScoreCardRiskService;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.util.ObjectHelper;

import cnfh.FeeSpecItem;
import cnfh.RiskSpecItem;
import cnfh.VerifyResult;
import cnfh.cnfh_rule.Calldrools;
import cnfh.cnfh_rule.InParam;
import cnfh.cnfh_rule.PFBeforeAddress;
import cnfh.cnfh_rule.PFBeforeCustomer;
import cnfh.cnfh_rule.PFCaseApply;
import cnfh.cnfh_rule.PFCustomerCreditStatistics;
import cnfh.cnfh_rule.PFCustomerHouse;
import cnfh.cnfh_rule.PFHmLoanInfo;
import cnfh.cnfh_rule.RuleInfo;
import cnfh.first_audit.YSCustomerCreditStatistics;
import cnfh.first_audit.YSDaasBasic;
import cnfh.first_audit.YSHouseProperty;
import cnfh.first_audit.YSHuifaResultInfo;
import cnfh.second_audit.ESCaseApply;
import cnfh.second_audit.ESCreditSituation;
import cnfh.second_audit.ESFeeInfomation;
import cnfh.second_audit.ESHmReportBasics;
import cnfh.second_audit.ESHouseProperty;
import cnfh.second_audit.ESPropertyOwner;
import cnfh.second_audit.ESQuestionnaire;

/**
* 版权所有：重庆正大华日软件有限公司   
* @Title: RuleApprovalService.java 
* @Package com.zdsoft.finance.rule.service 
* @Description: 规则审批判断service(一审，二审判断)
* @author zjx  
* @date 2017年2月25日 下午2:49:22 
* @version V1.0   
*/
@Service("ruleApprovalService")
public class RuleApprovalServiceImpl implements RuleApprovalService {

	@Autowired
	HuifaDetailService huifaDetailService;
	@Autowired
	CaseApplyService caseApplyService;
	@Autowired
	CaseApplyBeforeCustomerService caseApplyBeforeCustomerService;
	@Autowired
	HousePropertyService housePropertyService;
	@Autowired
	CustomerCreditStatisticsService customerCreditStatisticsService;
	@Autowired
	RiskRulesService riskRulesService;
	@Autowired
	BusinessDetailService businessDetailService;
	@Autowired
	QuestionnaireService questionnaireService;
	@Autowired
	PropertyOwnerService propertyOwnerService;
	@Autowired
	CreditManageService creditManageService;
	@Autowired
	CreditSituationService creditSituationService;
	@Autowired
	FeeInfomationService feeInfomationService;
	@Autowired
	FeeRulesService feeRulesService;
	@Autowired
	BeforeWorkUnitService beforeWorkUnitService;
	@Autowired
	ScoreCardRiskService scoreCardRiskService;
	@Autowired
	BeforeAddressService beforeAddressService;
	@Autowired
	CustomerHouseService customerHouseService;
	@Autowired
	BeforePersonalAssociationService beforePersonalAssociationService;
	@Autowired
	CreditService creditService;
	
	@Log
	private Logger logger;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public Boolean firstApproval(String caseApplyId,Integer isRefuse) throws Exception {
		logger.info("获取caseApplyId="+caseApplyId);
		CaseApply apply = caseApplyService.findOne(caseApplyId);
		logger.info("获取汇法工商数据(案件关联客户信息数据（不含产权人的信息）)");
		List<Object> obj = new ArrayList<>();
		BeforeCustomer mainCustomer = new BeforePersonalCustomer();
		//案件客户关系（案件参与人 共借人 担保人）
        List<CaseApplyBeforeCustomer> customers = caseApplyBeforeCustomerService.queryByCaseApplyId(caseApplyId);
        logger.info("获取案件关联客户信息数量为==="+customers.size());
		for(CaseApplyBeforeCustomer customer : customers){
			if(CaseApplyBeforeCustomer.MAIN_BORROW.equals(customer.getJoinType())){
				mainCustomer = customer.getBeforeCustomer();
			}
			BeforePersonalCustomer personalCustomer = (BeforePersonalCustomer) customer.getBeforeCustomer();
			//查询出汇法信息
			List<HuifaResultInfo> resultInfos = huifaDetailService.findSingleHuifaDetail(
					HuifaRequest.STYPE_PERSONAL,personalCustomer.getCredentialNo(),HuifaResultInfo.TYPE_PUBLIC);
			for(HuifaResultInfo resultInfo:resultInfos){
				YSHuifaResultInfo gzfa = new YSHuifaResultInfo();
				BeanUtils.copyProperties(resultInfo, gzfa);
				obj.add(gzfa);
			}
			//客户关系（配偶等信息）（由于规则判断只要是人都查，配偶等信息可能不是共借人和担保人，故单独查询一次）
			List<BeforePersonalAssociation> associations = beforePersonalAssociationService.queryCustomerId(personalCustomer.getId());
			for(BeforePersonalAssociation association : associations){
				BeforePersonalCustomer customer2 = association.getBeforePersonalCusomer();
				//查询出汇法信息
				List<HuifaResultInfo> resultInfos2 = huifaDetailService.findSingleHuifaDetail(
						HuifaRequest.STYPE_PERSONAL,customer2.getCredentialNo(),HuifaResultInfo.TYPE_PUBLIC);
				for(HuifaResultInfo resultInfo:resultInfos2){
					YSHuifaResultInfo gzfa = new YSHuifaResultInfo();
					BeanUtils.copyProperties(resultInfo, gzfa);
					obj.add(gzfa);
				}
			}
			List<BeforeWorkUnit> workUnits =  beforeWorkUnitService.queryByCustomerId(personalCustomer.getId());
			//判断是否有单位信息
			if(ObjectHelper.isNotEmpty(workUnits)){
				for(BeforeWorkUnit workUnit : workUnits){
					//判断是否是企业所有人（职务:法人/董事/股东，公司名义控制人，公司实际控制人
					if(BeforeWorkUnit.CHAIRMAN_OF_THE_BOARD.equals(workUnit.getPosition())
							||BeforeWorkUnit.COMPANY_NAME_CONTROLLER.equals(workUnit.getPosition())
							||BeforeWorkUnit.COMPANY_ACTUAL_CONTROLLER.equals(workUnit.getPosition())){
						
						//企业汇法信息
						List<HuifaResultInfo> entHFInfo = huifaDetailService.findSingleHuifaDetail(
								HuifaRequest.STYPE_COMPANY,workUnit.getCompanyName(),HuifaResultInfo.TYPE_PUBLIC);
						for(HuifaResultInfo resultInfo:entHFInfo){
							YSHuifaResultInfo gzfa = new YSHuifaResultInfo();
							BeanUtils.copyProperties(resultInfo, gzfa);
							obj.add(gzfa);
						}
						//查询出企业工商信息
						BusinessDetailBo bo = businessDetailService.findAllBusinessInfo(workUnit.getCompanyName());
						if(ObjectHelper.isNotEmpty(bo)){
							List<DaasBasic> basicList = bo.getBasicList();
							for(DaasBasic basic:basicList){
								YSDaasBasic gzbasic = new YSDaasBasic();
								BeanUtils.copyProperties(basic, gzbasic);
								obj.add(gzbasic);
							}
						}
					}
				}
			}
			
		}
		
		logger.info("获取房产数据（房产信息及产权人）");
		List<HouseProperty> houseProperties = housePropertyService.findByCaseApplyId(caseApplyId);
		logger.info("获取房产数量===="+houseProperties.size());
		for(HouseProperty houseProperty:houseProperties){
			YSHouseProperty gzhouse = new YSHouseProperty();
			BeanUtils.copyProperties(houseProperty, gzhouse);
			obj.add(gzhouse);
			//获取产权人汇法信息（和上面客户信息重的几率很大 ）
			List<PropertyOwner> owners = houseProperty.getPropertyOwnerList();
			for(PropertyOwner owner : owners){
				//查询出汇法信息
				List<HuifaResultInfo> resultInfos = huifaDetailService.findSingleHuifaDetail(
						HuifaRequest.STYPE_PERSONAL,owner.getCredentialNo(),HuifaResultInfo.TYPE_PUBLIC);
				for(HuifaResultInfo resultInfo:resultInfos){
					YSHuifaResultInfo gzfa = new YSHuifaResultInfo();
					BeanUtils.copyProperties(resultInfo, gzfa);
					obj.add(gzfa);
				}
			}
		}
		logger.info("获取征信数据");
        List<String> customerIds = new ArrayList<>();
        customerIds.add(mainCustomer.getId());
		List<CustomerCreditStatistics> list = customerCreditStatisticsService.findByCustomerIdsAndCaseApplyIdAndCaseApplyStage(caseApplyId,customerIds,apply.getStage());
		for(CustomerCreditStatistics statistics:list){
			YSCustomerCreditStatistics gzCustomerCreditStatistics = new YSCustomerCreditStatistics();
			BeanUtils.copyProperties(statistics, gzCustomerCreditStatistics);
			obj.add(gzCustomerCreditStatistics);
		}
		
		Boolean result = false;
		VerifyResult vr = createInParam("cnfh","first_audit","1.0",obj,isRefuse);
		if(VerifyResult.REFUSE.equals(vr.getVrResult())){
			result = true;
		}else if(VerifyResult.SPECIAL.equals(vr.getVrResult())){
			logger.info("触发规则特批");
			List<RiskSpecItem> items = vr.getRiskSpecItems();
			Map<String, Object> map = new HashMap<>();
			for(RiskSpecItem item : items){
				if(map.containsKey(item.getRiskFlag())){
					continue;
				}else{
					RiskRules rules = new RiskRules();
					rules.setCaseApplyId(caseApplyId);
					rules.setRulesNo(item.getRiskItemCode());
					rules.setRulesType(item.getRiskItemCode());
					rules.setRulesPrompt(item.getRiskItemTips());
					rules.setRulesResult(item.getRiskItemRes());
					riskRulesService.saveOrUpdateEntity(rules);
					map.put(item.getRiskFlag(), rules);
				}
			}
			logger.info("触发规则特批详细"+map.toString());
		}
		return result;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public Boolean secondApproval(String caseApplyId,Integer isRefuse) throws Exception {
		logger.info("获取caseApplyId="+caseApplyId);
		List<Object> obj = new ArrayList<>();
		logger.info("获取问题数据");
		List<Questionnaire> questionnaires = questionnaireService.findByCaseApplyId(caseApplyId);
		for(Questionnaire questionnaire : questionnaires){
			ESQuestionnaire gzQuestionnaire = new ESQuestionnaire();
			BeanUtils.copyProperties(questionnaire, gzQuestionnaire);
			obj.add(gzQuestionnaire);
		}
		logger.info("获取房产数据");
		List<HouseProperty> list = housePropertyService.findByCaseApplyId(caseApplyId);
		for(HouseProperty property : list){
			List<PropertyOwner> owners = propertyOwnerService.findByHousePropertyId(property.getId());
			for(PropertyOwner owner : owners){
				ESPropertyOwner gzPropertyOwner = new ESPropertyOwner();
				BeanUtils.copyProperties(owner, gzPropertyOwner);
				obj.add(gzPropertyOwner);
			}
			ESHouseProperty gzhouseProperty = new ESHouseProperty();
			BeanUtils.copyProperties(property, gzhouseProperty);
			obj.add(gzhouseProperty);
		}
		//从接口表中取数据
		logger.info("获取接口征信报告数据");
		List<HmReportBasics> basics = creditManageService.findByCaseApplyId(caseApplyId);
		if(ObjectHelper.isNotEmpty(basics)&&basics.size()>0){
			for(HmReportBasics basic : basics){
				ESHmReportBasics gzHmReportBasics = new ESHmReportBasics();
				BeanUtils.copyProperties(basic, gzHmReportBasics);
				obj.add(gzHmReportBasics);
			}
		}else{
			obj.add(new ESHmReportBasics());//如果没数据  new一个新的  确保规则那边100%有个对象
		}
		logger.info("获取征信报告数据");
		List<Credit> credits = creditService.findByCaseApplyIdAndCreditLinkCode(caseApplyId, Credit.LINK_CODE_SURVEY);
		if(ObjectHelper.isNotEmpty(credits)&&credits.size()>0){
			for(Credit credit : credits){
				CreditSituation creditSituation = creditSituationService.findByCreditId(credit.getId());
				if(ObjectHelper.isNotEmpty(creditSituation)){
					ESCreditSituation gzCreditSituation = new ESCreditSituation();
					BeanUtils.copyProperties(creditSituation, gzCreditSituation);
					obj.add(gzCreditSituation);
				}else{
					obj.add(new ESCreditSituation());//如果没数据  new一个新的  确保规则那边100%有个对象(多传  目前没事- -)
				}
			}
		}else{
			obj.add(new ESCreditSituation());//如果没数据  new一个新的  确保规则那边100%有个对象
		}
		Boolean result = false;
		VerifyResult vr = createInParam("cnfh","second_audit","1.0",obj,isRefuse);
		if(VerifyResult.REFUSE.equals(vr.getVrResult())){
			result = true;
		}else if(VerifyResult.SPECIAL.equals(vr.getVrResult())){
			logger.info("触发规则特批");
			List<RiskSpecItem> items = vr.getRiskSpecItems();
			Map<String, Object> map = new HashMap<>();
			for(RiskSpecItem item : items){
				if(map.containsKey(item.getRiskFlag())){
					continue;
				}else{
					RiskRules rules = new RiskRules();
					rules.setCaseApplyId(caseApplyId);
					rules.setRulesNo(item.getRiskItemCode());
					rules.setRulesType(item.getRiskItemCode());
					rules.setRulesPrompt(item.getRiskItemTips());
					rules.setRulesResult(item.getRiskItemRes());
					riskRulesService.saveOrUpdateEntity(rules);
					map.put(item.getRiskFlag(), rules);
				}
			}
			logger.info("触发规则特批详细"+map.toString());
		}
		return result;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void costApproval(String caseApplyId) throws Exception {
		logger.info("开始调用费用特批");
		logger.info("组装调用费用特批数据");
		CaseApply apply = caseApplyService.findOne(caseApplyId);
		List<Object> obj = new ArrayList<>();
		ESCaseApply gzCaseApply = new ESCaseApply();
		BeanUtils.copyProperties(apply, gzCaseApply);
		obj.add(gzCaseApply);
		List<FeeInfomation> list = feeInfomationService.findByCaseApplyId(caseApplyId);
		for(FeeInfomation infomation:list){
			ESFeeInfomation gzFeeInfomation = new ESFeeInfomation();
			BeanUtils.copyProperties(infomation, gzFeeInfomation);
			obj.add(gzFeeInfomation);
		}
		VerifyResult vr = createInParam("cnfh","second_audit","1.0",obj,RuleApprovalFinal.RUN_NORMAL.getValue());
		logger.info("规则引擎返回"+vr.getVrResult());
		if(VerifyResult.SPECIAL.equals(vr.getVrResult())){
			logger.info("触发费用特批");
			List<FeeSpecItem> items = vr.getFeeSpecItems();
			for(FeeSpecItem item : items){
				FeeRules rules = new FeeRules();
				rules.setCaseApplyId(caseApplyId);
				rules.setReceiveFeeId(item.getFeeItemId());
				rules.setExpectedAmount(item.getReceivableAmount());
				rules.setStandardAmount(item.getReceivableStandard());
				feeRulesService.saveOrUpdateEntity(rules);
			}
		}
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void caseScoreApproval(String caseApplyId) throws Exception {
		logger.info("组装评分卡数据");
		//案件信息
		CaseApply apply = caseApplyService.findOne(caseApplyId);
		logger.info("获取案件信息id"+apply.getId());
		PFCaseApply gzCaseApply = new PFCaseApply();
		BeanUtils.copyProperties(apply, gzCaseApply);
		List<Object> obj = new ArrayList<>();
		obj.add(gzCaseApply);
		//客户关系（客户信息）
        List<CaseApplyBeforeCustomer> customers = caseApplyBeforeCustomerService.queryByCaseApplyIdAndJoinType(caseApplyId, CaseApplyBeforeCustomer.MAIN_BORROW);
        BeforePersonalCustomer customer = (BeforePersonalCustomer) customers.get(0).getBeforeCustomer();
        logger.info("获取客户信息id="+customer.getId());
        PFBeforeCustomer pfBeforeCustomer = new PFBeforeCustomer();
        BeanUtils.copyProperties(customer, pfBeforeCustomer);
        obj.add(pfBeforeCustomer);
        //征信信息
        List<String> customerIds = new ArrayList<>();
        customerIds.add(customer.getId());
        logger.info("获取征信信息，传入参数："+caseApplyId+","+customerIds);
        List<CustomerCreditStatistics> list = customerCreditStatisticsService.findByCustomerIdsAndCaseApplyIdAndCaseApplyStage(caseApplyId,customerIds,CaseApplyStageEnumSimpleCodeEnum.EXAMINE.value);
        logger.info("返回征信信息条数："+list.size());
        for(CustomerCreditStatistics statistics:list){
			PFCustomerCreditStatistics pfCustomerCreditStatistics = new PFCustomerCreditStatistics();
			BeanUtils.copyProperties(statistics, pfCustomerCreditStatistics);
			obj.add(pfCustomerCreditStatistics);
		}
		//贷款信息
        logger.info("获取接口数据贷款信息");
		List<HmLoanInfo> loanInfos = creditManageService.findLoanInfo(caseApplyId);
		if(ObjectHelper.isEmpty(loanInfos)){
			//信用卡信息
			List<HmCreditCardInfo> cardInfos = creditManageService.findCreditCardInfo(caseApplyId);
			if(ObjectHelper.isEmpty(cardInfos)){
				//担保信息 暂时不知道取什么值
//						List<HmPerGuaranteeInfo> guaranteeInfos = creditManageService.findPerGuaranteeInfo(caseApplyId);
//						for(HmPerGuaranteeInfo guaranteeInfo:guaranteeInfos){
//							PFHmPerGuaranteeInfo pfHmPerGuaranteeInfo = new PFHmPerGuaranteeInfo();
//							BeanUtils.copyProperties(guaranteeInfo, pfHmPerGuaranteeInfo);
//							obj.add(pfHmPerGuaranteeInfo);
//						}
			}else{
				//取最小的一个值
				Integer titleFirstChar = null;
				for(HmCreditCardInfo cardInfo:cardInfos){
					Integer loanStatus=Integer.valueOf(cardInfo.getTitleInfo().substring(0, 1));
					if(ObjectHelper.isNotEmpty(titleFirstChar)){
						if(titleFirstChar>loanStatus){
							titleFirstChar = loanStatus;
						}
					}else{
						titleFirstChar = loanStatus;
					}
				}
				PFHmLoanInfo pfHmLoanInfo = new PFHmLoanInfo();
				pfHmLoanInfo.setAccountStatus(String.valueOf(titleFirstChar));
				pfHmLoanInfo.setTitleInfo(String.valueOf(titleFirstChar));
				obj.add(pfHmLoanInfo);
			}
		}else{
			//取最小的一个值
			Integer titleFirstChar = null;
			for(HmLoanInfo loanInfo:loanInfos){
				Integer loanStatus=Integer.valueOf(loanInfo.getTitleInfo().substring(0, 1));
				if(ObjectHelper.isNotEmpty(titleFirstChar)){
					if(titleFirstChar>loanStatus){
						titleFirstChar = loanStatus;
					}
				}else{
					titleFirstChar = loanStatus;
				}
			}
			PFHmLoanInfo pfHmLoanInfo = new PFHmLoanInfo();
			pfHmLoanInfo.setAccountStatus(String.valueOf(titleFirstChar));
			pfHmLoanInfo.setTitleInfo(String.valueOf(titleFirstChar));
			obj.add(pfHmLoanInfo);
		}
		//客户地址信息
		List<BeforeAddress> addresses = beforeAddressService.queryAddresss(customer.getId());
		for(BeforeAddress addresse:addresses){
			PFBeforeAddress pfBeforeAddress = new PFBeforeAddress();
			BeanUtils.copyProperties(addresse, pfBeforeAddress);
			obj.add(pfBeforeAddress);
		}
		//计算房产数量(- -)
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		List<HouseProperty> houseProperties = housePropertyService.findByCaseApplyId(caseApplyId);
		for(HouseProperty property : houseProperties){
			List<PropertyOwner> owners = propertyOwnerService.findByHousePropertyId(property.getId());
			for(PropertyOwner owner : owners){
				//判断产权人是不是借款人
				if(customer.getCustomerName().equals(owner.getOwnerName())){
					Boolean isSame = false;
					//根据地址去重处理（由于下面另一个类还会使用  故用map）
					for(Map<String, Object> e : maps){
						if(ObjectHelper.isNotEmpty(owner.getProvince())&&
								ObjectHelper.isNotEmpty(owner.getCity())&&
								ObjectHelper.isNotEmpty(owner.getDistrict())&&
								ObjectHelper.isNotEmpty(owner.getMailingAddress())){
							if(owner.getProvince().equals(String.valueOf(e.get("province")))&&
									owner.getCity().equals(String.valueOf(e.get("city")))&&
									owner.getDistrict().equals(String.valueOf(e.get("district")))&&
									owner.getMailingAddress().equals(String.valueOf(e.get("mailingAddress")))){
								isSame = true;
							}
						}
					}
					if(!isSame){
						Map<String, Object> map = new HashMap<>();
						map.put("province", owner.getProvince());
						map.put("city", owner.getCity());
						map.put("district", owner.getDistrict());
						map.put("mailingAddress", owner.getMailingAddress());
						maps.add(map);
					}
				}
			}
		}
		List<CustomerHouse> houses = customerHouseService.findByCaseApplyId(caseApplyId);
		for(CustomerHouse house : houses){
			//判断产权人是不是借款人
			if(customer.getCustomerName().equals(house.getOwner())){
				Boolean isSame = false;
				//根据地址去重处理（）
				for(Map<String, Object> e : maps){
					if(ObjectHelper.isNotEmpty(house.getProvince())&&
							ObjectHelper.isNotEmpty(house.getCity())&&
							ObjectHelper.isNotEmpty(house.getDistrict())&&
							ObjectHelper.isNotEmpty(house.getAddress())){
						if(house.getProvince().equals(String.valueOf(e.get("province")))&&
								house.getCity().equals(String.valueOf(e.get("city")))&&
								house.getDistrict().equals(String.valueOf(e.get("district")))&&
								house.getAddress().equals(String.valueOf(e.get("mailingAddress")))){
							isSame = true;
						}
					}
				}
				if(!isSame){
					Map<String, Object> map = new HashMap<>();
					map.put("province", house.getProvince());
					map.put("city", house.getCity());
					map.put("district", house.getDistrict());
					map.put("mailingAddress", house.getAddress());
					maps.add(map);
				}
			}
		}
		PFCustomerHouse pfCustomerHouse = new PFCustomerHouse();
		//赋值房产数量  规则评分调用（约定用的此字段）
		pfCustomerHouse.setOwner(String.valueOf(maps.size()));
		logger.info("获取房产数量："+String.valueOf(maps.size()));
		obj.add(pfCustomerHouse);
		VerifyResult vr = createInParam("cnfh","cnfh_rule","1.0",obj,RuleApprovalFinal.RUN_NORMAL.getValue());
		ScoreCardRisk cardRisk = new ScoreCardRisk();
		cardRisk.setCaseApplyId(apply.getId());
		cardRisk.setProductType(apply.getProductTypeName());
		cardRisk.setScoreCard(vr.getVrResult());
		cardRisk.setRiskSegment(vr.getVrType());
	    cardRisk.setAdviseHighPlies(vr.getVrContent());
	    cardRisk.setRiskTips(vr.getVrDesc());
	    scoreCardRiskService.saveOrUpdateEntity(cardRisk);
	}
	
	
	
	/** 
	 * @Title: saveRiskRules 
	 * @Description: 根据传入的caseApplyId和规则返回和类型  保存风险
	 * @author zjx 
	 * @param caseApplyId
	 * @param vr
	 * @param value
	 * @throws BusinessException  
	 *//* 
	private void saveRiskRules(String caseApplyId, VerifyResult vr, String value) throws BusinessException{
		RiskRules rules = new RiskRules();
		rules.setCaseApplyId(caseApplyId);
		rules.setRulesNo(value);
		rules.setRulesType(value);
		rules.setRulesPrompt(vr.getVrDesc());
		rules.setRulesResult(vr.getVrContent());
		riskRulesService.saveOrUpdateEntity(rules);
	}*/
	
	/** 
	 * @Title: createInParam 
	 * @Description: 初始化调用规则方法数据返回是否出发规则
	 * @author zjx 
	 * @param groupId
	 * @param artifactId
	 * @param versionCode
	 * @param obj
	 * @param isRefuse(是否跳过拒绝规则)0代表执行拒绝规则 1代表不执行拒绝规则
	 * @return
	 * @throws Exception  
	 */ 
	private VerifyResult createInParam(String groupId,String artifactId,String versionCode,List<Object> obj,Integer isRefuse) throws Exception{
		VerifyResult vr1 = new VerifyResult();
		vr1.setVrId(isRefuse);
		obj.add(vr1);
		Calldrools drools = new Calldrools();
		InParam inparam = new InParam();
		RuleInfo info = new RuleInfo();
		info.setGroupId(groupId);
		info.setArtifactId(artifactId);
		info.setVersionCode(versionCode);
		inparam.setRuleInfo(info);
		return drools.excuteCall(inparam, obj,vr1);
	}
}
