package com.zdsoft.finance.customer.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.customer.entity.AfterPersonalCustomer;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AfterPersonalCustomerService.java 
 * @ClassName: AfterPersonalCustomerService 
 * @Description: 贷后个人客户信息接口
 * @author zhangchao 
 * @date 2017年2月6日 上午10:23:09 
 * @version V1.0
 */
public interface AfterPersonalCustomerService extends BaseService<AfterPersonalCustomer>{

	/**
	 *
	 * @Title: findByCredentiaTypeAndCredentialNo 
	 * @Description:  查询客户
	 * @author zhangchao 
	 * @param credentiaType 证件类型
	 * @param credentialNo 证件编号
	 * @return 客户信息
	 */
	public AfterPersonalCustomer findByCredentiaTypeAndCredentialNo(String credentiaType, String credentialNo);

	/**
	 * 
	 * @Title: findByCaseApplyIdAndJoinType 
	 * @Description: 通过案件id和参与类型查询客户信息
	 * @author xj 
	 * @param caseApplyId 案件id
	 * @param joinType 参与类型
	 * @return
	 */
	public List<AfterPersonalCustomer> findByCaseApplyIdAndJoinType(String caseApplyId,String joinType);
	
	/**
	 * 
	 * @Title: findByCredentialTypeAndCredentialNoAndIsLatest 
	 * @Description: 通过证件和是否是最新查询
	 * @author xj 
	 * @param credentialType 证件类型
	 * @param credentialNo 证件号码
	 * @param isLatest 是否最新 true：最新，false：不是最新
	 * @return
	 */
	public List<AfterPersonalCustomer> findByCredentialTypeAndCredentialNoAndIsLatest(String credentialType, String credentialNo,boolean isLatest);
}
