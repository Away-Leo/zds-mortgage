package com.zdsoft.finance.marketing.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.customer.entity.BeforeCustomer;
import com.zdsoft.finance.customer.entity.BeforePersonalCustomer;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:CaseApplyBeforeCustomerService.java
 * @Package:com.zdsoft.finance.marketing.service
 * @Description:申请人（案件的担保人、合作人）
 * @author: xj
 * @date:2017年1月11日 下午9:48:20
 * @version:v1.0
 */
public interface CaseApplyBeforeCustomerService extends BaseService<CaseApplyBeforeCustomer> {
	/**
	 * 
	 * 根据案件id和关系查询
	 *
	 * @author xj
	 * @param caseApplyId
	 * @param joinType
	 * @return
	 */
	public List<CaseApplyBeforeCustomer> queryByCaseApplyIdAndJoinType(String caseApplyId,String joinType);
	/**
	 * 
	 * 根据案件id
	 * 
	 * @author xj
	 * @param caseApplyId
	 * @return
	 */
	public List<CaseApplyBeforeCustomer> queryByCaseApplyId(String caseApplyId);
	/**
	 * 
	 * 删除通过案件id
	 *
	 * @author xj
	 * @param caseApplyId
	 * @throws Exception 
	 */
	public void deleteByCaseApplyId(String caseApplyId) throws Exception;
	/**
	 * 
	 * 通过客户id和案件id删除参与关系
	 *
	 * @author xj
	 * @param customerId 客户id
	 * @param caseApplyId 案件id
	 */
	public void deleteByCustomerIdAndCaseApplyId(String customerId,String caseApplyId);
	/**
	 * 
	 * 设置参与类型
	 *
	 * @author xj
	 * @param mainCustomer 客户
	 * @param caseApply 案件
	 * @param joinType 参与类型
	 * @throws BusinessException
	 */
	public void setJoinType(BeforePersonalCustomer mainCustomer,CaseApply caseApply,String joinType) throws BusinessException;
	
	/**
	 * 
	 * 根据案件Id和关联类型查询贷前客户
	 *
	 * @author jingyh
	 * @param caseApplyId
	 * @param joinType
	 * @return
	 */
	public List<BeforeCustomer> findCustomerByCaseApplyIdAndJoinType(String caseApplyId,String joinType) throws BusinessException;
	
	
	/**
	 * 查询出客户相关联系的案件信息
	 * @param pageable
	 * @param queryObjs
	 * @return
	 */
	public List<Map<String,Object>> queryCaseApplyByCustomer(Map<String, Object> condition);
	/**
	 * 
	 * 根据客户id案件id查询客户参与类型
	 *
	 * @author laijun
	 * @date:2017年1月19日 下午7:40:01
	 * @param customerId
	 * @param caseApplyId
	 * @return
	 */
	public CaseApplyBeforeCustomer findByCaseApplyIdAndBeforeCustomerId(String caseApplyId,String customerId);
	
}
