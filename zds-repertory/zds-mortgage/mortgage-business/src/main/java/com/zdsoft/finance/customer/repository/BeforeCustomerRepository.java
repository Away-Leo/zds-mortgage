package com.zdsoft.finance.customer.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.customer.entity.BeforeCustomer;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: BeforeCustomerRepository.java 
 * @ClassName: BeforeCustomerRepository 
 * @Description: 贷前客户Repository
 * @author jingyh 
 * @date 2017年2月24日 下午2:17:35 
 * @version V1.0
 */
public interface BeforeCustomerRepository extends CustomRepository<BeforeCustomer, String> {

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
	public default BeforeCustomer findByCredentialTypeAndCredentialNo(String credentialType,String credentialNo,String caseApplyId) {
		String hql = " select bc from BeforeCustomer bc , CaseApplyBeforeCustomer cabc where cabc.beforeCustomer.id=bc.id ";
		hql += " and cabc.caseApply.id = :caseApplyId ";
		hql += " and bc.credentialType = :credentialType ";
		hql += " and bc.credentialNo = :credentialNo ";
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("caseApplyId", caseApplyId);
		condition.put("credentialType", credentialType);
		condition.put("credentialNo", credentialNo);
		List<BeforeCustomer> list = this.findByHql(hql, condition);
		if (ObjectHelper.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	};
	
	/**
	 * 
	 * @Title: findRelationCustomerByCaseApplyId 
	 * @Description: 根据案件Id查询关联客户信息（含配偶）
	 * @author jingyh 
	 * @param caseApplyId
	 * @return
	 */
	public List<Map<String, Object>> findRelationCustomerByCaseApplyId(String caseApplyId);
}
