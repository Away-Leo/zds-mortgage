package com.zdsoft.finance.marketing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.customer.entity.BeforeCustomer;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;


/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CaseApplyBeforeCustomerRepository.java 
 * @ClassName: CaseApplyBeforeCustomerRepository 
 * @Description: 案件客户关系表（贷前案件申请人）
 * @author xj 
 * @date 2017年3月13日 上午11:18:29 
 * @version V1.0
 */
public interface CaseApplyBeforeCustomerRepository extends CustomRepository<CaseApplyBeforeCustomer, String> {
	
	/**
	 * 
	 * @Title: findByCaseApplyIdAndJoinType 
	 * @Description: 根据案件id和关系查询
	 * @author xj 
	 * @param caseApplyId 案件id
	 * @param joinType 参与类型
	 * @return
	 */
	public List<CaseApplyBeforeCustomer> findByCaseApplyIdAndJoinType(String caseApplyId, String joinType);

	/**
	 * 
	 * @Title: findByCaseApplyId 
	 * @Description: 根据案件id
	 * @author xj 
	 * @param caseApplyId 案件id
	 * @return
	 */
	public List<CaseApplyBeforeCustomer> findByCaseApplyId(String caseApplyId);

	/**
	 * 
	 * @Title: findByCaseApplyIdAndBeforeCustomerId 
	 * @Description: 通过案件id和客户id查询关联关系
	 * @author xj 
	 * @param caseApplyId 案件id
	 * @param beforeCustomerId 客户id
	 * @return
	 */
	public CaseApplyBeforeCustomer findByCaseApplyIdAndBeforeCustomerId(String caseApplyId, String beforeCustomerId);

	/**
	 * 
	 * 根据案件Id和类型查询贷前客户信息
	 *
	 * @author jingyh
	 * @param caseApplyId
	 * @param joinType
	 * @return
	 */
	@Query("select cabc.beforeCustomer from CaseApplyBeforeCustomer cabc where cabc.caseApply.id = :caseApplyId and cabc.joinType = :joinType")
	public List<BeforeCustomer> findCustomerByCaseApplyIdAndJoinType(@Param("caseApplyId") String caseApplyId,
			@Param("joinType") String joinType);
	
	/**
	 * @Title: findCustomerNameByLike 
	 * @Description: 根据案件Id和户主名模糊查询数据
	 * @author zhoushichao 
	 * @param caseApplyId  案件Id
	 * @param customerName 客户姓名
	 * @return
	 * @return
	 */
	@Query(" from CaseApplyBeforeCustomer t where t.caseApply.id=:caseApplyId and t.beforeCustomer.customerName like CONCAT('%',:customerName,'%')")
	public List<CaseApplyBeforeCustomer> findCustomerNameByLike(@Param("caseApplyId") String caseApplyId,@Param("customerName") String customerName);
	
	/**
	 * 
	 * @Title: findByBeforeCustomerId 
	 * @Description: 通过客户id查询案件参与类型
	 * @author xj 
	 * @param customerId 客户id
	 * @return
	 */
	public CaseApplyBeforeCustomer findByBeforeCustomerId(String customerId);

}
