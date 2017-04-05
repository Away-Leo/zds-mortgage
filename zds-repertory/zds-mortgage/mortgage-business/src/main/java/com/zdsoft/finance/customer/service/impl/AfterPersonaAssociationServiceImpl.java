package com.zdsoft.finance.customer.service.impl;

import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.customer.entity.AfterPersonaAssociation;
import com.zdsoft.finance.customer.repository.AfterPersonaAssociationRepository;
import com.zdsoft.finance.customer.service.AfterPersonaAssociationService;
import com.zdsoft.framework.core.common.util.ObjectHelper;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AfterPersonaAssociationServiceImpl.java 
 * @ClassName: AfterPersonaAssociationServiceImpl 
 * @Description: 贷后客户关系service实现类
 * @author xj 
 * @date 2017年3月8日 下午5:57:12 
 * @version V1.0
 */
@Service("afterPersonaAssociationService")
public class AfterPersonaAssociationServiceImpl extends BaseServiceImpl<AfterPersonaAssociation,AfterPersonaAssociationRepository> 
implements AfterPersonaAssociationService{
	
	@Override
	public String findByPostLoanCustomerIdAndRelationship(String postLoanCustomerId, String relationship){
		AfterPersonaAssociation postLoanPersonaAssociation = null;
		postLoanPersonaAssociation = this.customReposity.findByCustomerIdAndRelationship(postLoanCustomerId, relationship);
		if(ObjectHelper.isNotEmpty(postLoanPersonaAssociation)){
			return postLoanPersonaAssociation.getAfterPersonalCustomer().getId();
		}else{
			return null;
		}
	}
}
