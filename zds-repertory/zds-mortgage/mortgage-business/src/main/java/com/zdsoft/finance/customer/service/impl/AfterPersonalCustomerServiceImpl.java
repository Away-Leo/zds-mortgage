package com.zdsoft.finance.customer.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.customer.entity.AfterPersonalCustomer;
import com.zdsoft.finance.customer.repository.AfterPersonalCustomerRepository;
import com.zdsoft.finance.customer.service.AfterPersonalCustomerService;
import com.zdsoft.finance.marketing.entity.CaseApplyAfterCustomer;
import com.zdsoft.finance.marketing.service.CaseApplyAfterCustomerService;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AfterPersonalCustomerServiceImpl.java 
 * @ClassName: AfterPersonalCustomerServiceImpl 
 * @Description: 贷后客户service实现类
 * @author xj 
 * @date 2017年3月8日 下午5:58:32 
 * @version V1.0
 */
@Service("afterPersonalCustomerService")
public class AfterPersonalCustomerServiceImpl extends BaseServiceImpl<AfterPersonalCustomer, AfterPersonalCustomerRepository> 
implements AfterPersonalCustomerService {
	@Autowired
	private CaseApplyAfterCustomerService caseApplyAfterCustomerService;

	@Override
	public AfterPersonalCustomer findByCredentiaTypeAndCredentialNo(String credentiaType, String credentialNo) {
		AfterPersonalCustomer postLoanPersonal = null;
		postLoanPersonal = this.customReposity.findByCredentialTypeAndCredentialNo(credentiaType, credentialNo);
		return postLoanPersonal;
	}

	@Override
	public List<AfterPersonalCustomer> findByCaseApplyIdAndJoinType(String caseApplyId, String joinType) {
		List<AfterPersonalCustomer> postLoanPersonals = new ArrayList<AfterPersonalCustomer>();
		List<CaseApplyAfterCustomer> caseApplyAfterCustomers = caseApplyAfterCustomerService.findByCaseApplyIdAndJoinType(caseApplyId, joinType);
		if(ObjectHelper.isNotEmpty(caseApplyAfterCustomers)){
			for (CaseApplyAfterCustomer caseApplyAfterCustomer : caseApplyAfterCustomers) {
				postLoanPersonals.add((AfterPersonalCustomer)caseApplyAfterCustomer.getAfterCustomer());
			}
		}
		return postLoanPersonals;
	}

	@Override
	public List<AfterPersonalCustomer> findByCredentialTypeAndCredentialNoAndIsLatest(String credentialType,
			String credentialNo, boolean isLatest) {
		return this.customReposity.findByCredentialTypeAndCredentialNoAndIsLatest(credentialType, credentialNo, isLatest);
	}

}
