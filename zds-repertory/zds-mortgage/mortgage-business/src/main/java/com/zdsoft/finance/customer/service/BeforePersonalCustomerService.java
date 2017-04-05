package com.zdsoft.finance.customer.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.customer.entity.BeforeAddress;
import com.zdsoft.finance.customer.entity.BeforeContact;
import com.zdsoft.finance.customer.entity.BeforePersonalCustomer;
import com.zdsoft.finance.customer.entity.BeforeWorkUnit;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: BeforePersonalCustomerService.java 
 * @ClassName: BeforePersonalCustomerService 
 * @Description: 个人客户service
 * @author xj 
 * @date 2017年3月9日 上午10:01:27 
 * @version V1.0
 */
public interface BeforePersonalCustomerService extends BaseService<BeforePersonalCustomer> {
	
	/**
	 * 
	 * @Title: loadCustomerByCredentiaTypeAndCredentialNo 
	 * @Description: 通过证件类型和证件号码查询客户
	 * @author xj 
	 * @param credentiaType 证件类型
	 * @param credentialNo  证件号码
	 * @return
	 */
	public List<BeforePersonalCustomer> loadCustomerByCredentiaTypeAndCredentialNo(String credentiaType,String credentialNo);
	
	/**
	 * 
	 * @Title: queryRelationCustomer 
	 * @Description: 通过客户id和关联关系查询联系人
	 * @author xj 
	 * @param id 客户id
	 * @param relationship 关系类型
	 * @return
	 */
	public List<BeforePersonalCustomer> queryRelationCustomer(String id,String relationship);
	
	/**
	 * 
	 * @Title: saveOrUpdateCustomer 
	 * @Description: 保存或者修改客户 配偶放在相应的里面，客户里面有relationship字段 表示和借款人的关系,joinType表示参与类型，如果是主借人关联关系不能填
	 * @author xj 
	 * @param beforePersonalCustomer 客户
	 * @param caseApplyId 案件id
	 * @return
	 * @throws Exception
	 */
	public BeforePersonalCustomer saveOrUpdateCustomer(BeforePersonalCustomer beforePersonalCustomer,String caseApplyId) throws Exception;
	
	/**
	 * 
	 * @Title: queryCustomerByCaseApplyIdAndJoinType 
	 * @Description: 通过案件id和参与类型查询客户
	 * @author xj 
	 * @param caseApplyId 案件id
	 * @param joinType  案件id
	 * @return
	 */
	public List<BeforePersonalCustomer> queryCustomerByCaseApplyIdAndJoinType(String caseApplyId,String joinType);
	
	/**
	 * 
	 * @Title: saveOrUpdateAppCustomer 
	 * @Description: app保存或者修改客户信息
	 * @author xj 
	 * @param caseApplyId 案件id
	 * @param customer 客户
	 * @param allAddress 地址
	 * @param beforeWorkUnits 工作单位
	 * @param contacts 联系方式
	 * @param isSpouse 是否是配偶 用于设置配偶关系的客户和关联客户
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public String saveOrUpdateAppCustomer(String caseApplyId,BeforePersonalCustomer customer,
			List<BeforeAddress> allAddress, List<BeforeWorkUnit> beforeWorkUnits, List<BeforeContact> contacts,boolean isSpouse,
			String token) throws Exception;
	
	/**
	 * 
	 * @Title: queryByCaseApplyId 
	 * @Description: 根据案件id查询参与客户，包含参与类型，和主借人关系
	 * @author xj 
	 * @param caseApplyId 案件id
	 * @return
	 */
	public List<BeforePersonalCustomer> queryByCaseApplyId(String caseApplyId);
	
	/**
	 * 
	 * @Title: findById 
	 * @Description: 通过Id查找客户
	 * @author caixiekang 
	 * @return
	 */
	public BeforePersonalCustomer findById(String id);

}
