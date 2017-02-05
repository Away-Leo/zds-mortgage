package com.zdsoft.finance.customer.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.customer.entity.BeforeAddress;
import com.zdsoft.finance.customer.entity.BeforeContact;
import com.zdsoft.finance.customer.entity.BeforePersonalCustomer;
import com.zdsoft.finance.customer.entity.BeforeWorkUnit;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:BeforePersonalCustomerService.java
 * @Package:com.zdsoft.finance.customer.service
 * @Description:个人客户service
 * @author: xj
 * @date:2017年1月11日 下午3:23:50
 * @version:v1.0
 */
public interface BeforePersonalCustomerService extends BaseService<BeforePersonalCustomer> {
	/**
	 * 
	 * 通过证件类型和证件号码查询客户
	 *
	 * @author xj
	 * @param credentiaType
	 * @param CredentialNo
	 * @return
	 */
	public List<BeforePersonalCustomer> loadCustomerByCredentiaTypeAndCredentialNo(String credentiaType,String credentialNo);
	/**
	 * 
	 * 通过客户id和关联关系查询联系人
	 *
	 * @author xj
	 * @param id 客户id
	 * @param relationship
	 * @return
	 */
	public List<BeforePersonalCustomer> queryRelationCustomer(String id,String relationship);
	/**
	 * 
	 * 保存或者修改客户 配偶放在相应的里面，客户里面有relationship字段 表示和借款人的关系,joinType表示参与类型，如果是主借人关联关系不能填
	 *
	 * @author xj
	 * @param associatedCustomers
	 * @param caseApplyId
	 * @throws Exception
	 */
	public BeforePersonalCustomer saveOrUpdateCustomer(BeforePersonalCustomer beforePersonalCustomer,String caseApplyId) throws Exception;
	/**
	 * 
	 * 通过案件id和参与类型查询客户
	 *
	 * @author xj
	 * @param caseApplyId 案件id
	 * @param joinType  参与类型
	 * @return
	 */
	public List<BeforePersonalCustomer> queryCustomerByCaseApplyIdAndJoinType(String caseApplyId,String joinType);
	/**
	 * 
	 * app保存或者修改客户信息
	 *
	 * @author xj
	 * @param customerId 案件id
	 * @param customer 客户
	 * @param allAddress 地址
	 * @param beforeWorkUnits 工作单位
	 * @param contacts 联系方式
	 * @param isSpouse 是否是配偶
	 * @param token 
	 * @return
	 * @throws Exception 
	 */
	public String saveOrUpdateAppCustomer(String caseApplyId,BeforePersonalCustomer customer,
			List<BeforeAddress> allAddress, List<BeforeWorkUnit> beforeWorkUnits, List<BeforeContact> contacts,boolean isSpouse,
			String token) throws Exception;
	/**
	 * 
	 * 根据案件id查询参与客户，包含参与类型，和主借人关系
	 *
	 * @author xj
	 * @param caseApplyId
	 * @return
	 */
	public List<BeforePersonalCustomer> queryByCaseApplyId(String caseApplyId);

}
