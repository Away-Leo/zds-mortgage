package com.zdsoft.finance.customer.service.process;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.bpm.annotation.client.AutoJob;
import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.customer.entity.BeforePersonalCustomer;
import com.zdsoft.finance.customer.entity.Credit;
import com.zdsoft.finance.customer.service.BeforePersonalCustomerService;
import com.zdsoft.finance.customer.service.CreditService;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.dto.DelegateExecution;
import com.zdsoft.framework.core.common.service.JavaDelegate;
import com.zdsoft.framework.core.common.util.ObjectHelper;

@Service
@AutoJob(label = "征信信息初始化", resource = "com.zdsoft.finance.projectatta.service.process.initCustomerCreditAutoJob.autoJob")
@Lazy(false)
public class InitCustomerCreditAutoJob implements JavaDelegate{
	@Autowired
	private BeforePersonalCustomerService beforePersonalCustomerService;
	@Autowired
	private CED CED;
	@Autowired
	private CreditService creditService;
	@Log
	private Logger log;
	@Transactional(rollbackFor=Exception.class)
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		log.info("开始初始化资料清单");
		String caseApplyId = (String)execution.getVariable("projectId");
		log.info("caseApplyId:"+caseApplyId);
		List<BeforePersonalCustomer> beforePersonalCustomers = beforePersonalCustomerService.queryByCaseApplyId(caseApplyId);
		for (BeforePersonalCustomer beforePersonalCustomer : beforePersonalCustomers) {
			Credit credit = new Credit();
			//案件id
			credit.setCaseApplyId(caseApplyId);
			//客户id
			credit.setCustomerId(beforePersonalCustomer.getId());
			//客户名称
			String customerName = beforePersonalCustomer.getCustomerName();
			credit.setCustomerName(customerName);
			//客户证件类型
			String credentialType = beforePersonalCustomer.getCredentialType();
			credit.setCredentialType(credentialType);
			//客户证件类型名称
			if(ObjectHelper.isNotEmpty(credentialType)){
				credit.setCredentialTypeName(CED.loadSimpleCodeNameByFullCode(credentialType));
			}
			//证件号码
			String credentialNo = beforePersonalCustomer.getCredentialNo();
			credit.setCredentialNo(credentialNo);
			//参与类型
			String joinType = beforePersonalCustomer.getJoinType();
			credit.setJoinType(joinType);
			//设置参与类型名称
			if(ObjectHelper.isNotEmpty(joinType)){
				credit.setJoinTypeName(CED.loadSimpleCodeNameByFullCode(joinType));
			}
			//是否实际用款人
			String actualUsePerson = beforePersonalCustomer.getActualUsePerson();
			credit.setActualUsePerson(actualUsePerson);
			if(ObjectHelper.isNotEmpty(actualUsePerson)){
				credit.setActualUsePersonName(CED.loadSimpleCodeNameByFullCode(actualUsePerson));
			}
			
			creditService.saveCredit(credit);
			//配偶
			BeforePersonalCustomer spouse = beforePersonalCustomer.getSpouse();
			if(ObjectHelper.isNotEmpty(spouse)){
				Credit spouseCredit = new Credit();
				//案件id
				spouseCredit.setCaseApplyId(caseApplyId);
				//客户id
				spouseCredit.setCustomerId(spouse.getId());
				//客户名称
				String spouseCustomerName = spouse.getCustomerName();
				spouseCredit.setCustomerName(spouseCustomerName);
				//客户证件类型
				String spouseCredentialType = spouse.getCredentialType();
				spouseCredit.setCredentialType(spouseCredentialType);
				//客户证件类型名称
				if(ObjectHelper.isNotEmpty(spouseCredentialType)){
					spouseCredit.setCredentialTypeName(CED.loadSimpleCodeNameByFullCode(spouseCredentialType));
				}
				//证件号码
				String spouseCredentialNo = spouse.getCredentialNo();
				spouseCredit.setCredentialNo(spouseCredentialNo);
				//参与类型
				String spouseJoinType = spouse.getJoinType();
				spouseCredit.setJoinType(spouseJoinType);
				//设置参与类型名称
				if(ObjectHelper.isNotEmpty(joinType)){
					spouseCredit.setJoinTypeName(CED.loadSimpleCodeNameByFullCode(joinType)+"配偶");
				}
				//是否实际用款人
				String spouseActualUsePerson = spouse.getActualUsePerson();
				spouseCredit.setActualUsePerson(spouseActualUsePerson);
				if(ObjectHelper.isNotEmpty(spouseActualUsePerson)){
					spouseCredit.setActualUsePersonName(CED.loadSimpleCodeNameByFullCode(spouseActualUsePerson));
				}
				creditService.saveCredit(spouseCredit);
				
			}
			
			
			
			
		}
		log.info("征信信息初始化结束");
		
	}

}
