package com.zdsoft.finance.businesssetting.service.impl.rule;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.businesssetting.service.QuestionJudgeRuleService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.customer.entity.BeforeCustomer;
import com.zdsoft.finance.customer.entity.BeforeWorkUnit;
import com.zdsoft.finance.customer.service.BeforeWorkUnitService;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.service.CaseApplyBeforeCustomerService;
import com.zdsoft.finance.spi.businesssetting.QuestionRuleParamDto;
import com.zdsoft.framework.core.common.annotation.Log;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: PersonCapitalTransferServiceImpl.java 
 * @ClassName: PersonCapitalTransferServiceImpl 
 * @Description: 申请人/用款人（含配偶）资调环节-个人
 * @author jincheng 
 * @date 2017年3月2日 上午10:06:36 
 * @version V1.0
 */
@Service("personCapitalTransferService")
public class PersonCapitalTransferServiceImpl implements QuestionJudgeRuleService {
	
	@Autowired
	private CaseApplyBeforeCustomerService caseApplyBeforeCustomerService;
	
	@Autowired
	private BeforeWorkUnitService beforeWorkUnitService;
	
	@Log
	private Logger log; 

	@Override
	public boolean analysisJudgeRule(QuestionRuleParamDto paramDto) {
		boolean bool=false;
		String caseApplyId=paramDto.getCaseApplyId();
		 try {
			List<BeforeCustomer> beforeCustomerList=caseApplyBeforeCustomerService.findCustomerByCaseApplyIdAndJoinType(caseApplyId, CaseApplyBeforeCustomer.MAIN_BORROW);
			String customerId=beforeCustomerList.get(0).getId();
			//1、电话核查工作信息是否一致
			//1.1、触发条件：资调-客户信息中工作单位信息的职位：一般员工，初级管理，中级管理，高级管理，其他 ；{cust_before_personal  BeforePersonalCustomer（贷前个人客户信息） 和 （cust_before_work_unit）BeforeWorkUnit（贷前工作单位） 关联，职位为 “一般员工，初级管理，中级管理，高级管理，其他”}
			//2、实地核查工作信息是否一致
			//2.1、触发条件：资调-客户信息中工作单位信息的职位：一般员工，初级管理，中级管理，高级管理，其他；{cust_before_personal（贷前个人客户信息） 和 （cust_before_work_unit）（贷前工作单位） 关联，职位为 “一般员工，初级管理，中级管理，高级管理，其他”}
//			YWDM008801	法人/董事/股东
//			YWDM008802	高级管理
//			YWDM008803	中级管理
//			YWDM008804	低级管理
//			YWDM008805	一般员工
//			YWDM008806	公司名义控制人
//			YWDM008807	公司实际控制人
//			YWDM008808	其他
			log.error("问卷题库进入personCapitalTransferService主借人id{}",customerId);
			log.error("需要回答的问题:{}",paramDto.getQuestionContent());
			List<BeforeWorkUnit> bwuList=beforeWorkUnitService.queryByCustomerId(customerId);
			for(BeforeWorkUnit bwu:bwuList){
				if("YWDM008805".equals(bwu.getPosition())){
					log.error("问卷题库进入判断一般员工:YWDM008805");
					bool=true;
					break;
				}
				if("YWDM008804".equals(bwu.getPosition())){
					log.error("问卷题库进入判断低级管理:YWDM008804");
					bool=true;
					break;
				}
				if("YWDM008803".equals(bwu.getPosition())){
					log.error("问卷题库进入判断中级管理:YWDM008803");
					bool=true;
					break;
				}
				if("YWDM008802".equals(bwu.getPosition())){
					log.error("问卷题库进入判断高级管理:YWDM008802");
					bool=true;
					break;
				}
				if("YWDM008808".equals(bwu.getPosition())){
					log.error("问卷题库进入判断其他:YWDM008808");
					bool=true;
					break;
				}
			}
		 } catch (BusinessException e) {
			e.printStackTrace();
		}
		
		return bool;
	}
}
