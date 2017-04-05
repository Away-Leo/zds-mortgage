package com.zdsoft.finance.customer.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.customer.dto.CaseRelationCustomerDto;
import com.zdsoft.finance.customer.entity.BeforeCustomer;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: BeforeCustomerService.java 
 * @ClassName: BeforeCustomerService 
 * @Description: 客户Service
 * @author jingyh 
 * @date 2017年2月24日 下午2:17:06 
 * @version V1.0
 */
public interface BeforeCustomerService extends BaseService<BeforeCustomer>{
	
	
	/**
	 * 
	 * @Title: findByCredentialTypeAndCredentialNo 
	 * @Description: 根据证件类型和证件号查询客户对象
	 * @author jingyh 
	 * @param credentialType
	 * @param credentialNo
	 * @param caseApplyId
	 * @return
	 */
	public BeforeCustomer findByCredentialTypeAndCredentialNo(String credentialType,String credentialNo, String caseApplyId);
	
	/**
	 * 
	 * @Title: findRelationCustomerByCaseApplyId 
	 * @Description: 根据案件Id查询关联客户信息（含配偶）
	 * @author jingyh 
	 * @param caseApplyId
	 * @return
	 */
	public List<CaseRelationCustomerDto> findRelationCustomerByCaseApplyId(String caseApplyId) throws Exception;
}
