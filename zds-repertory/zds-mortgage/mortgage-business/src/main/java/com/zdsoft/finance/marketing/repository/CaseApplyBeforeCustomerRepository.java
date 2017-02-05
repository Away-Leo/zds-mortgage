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
 * 
 * @Title:CaseApplyBeforeCustomerRepository.java
 * @Package:com.zdsoft.finance.marketing.repository
 * @Description:案件客户关系表（贷前案件申请人）
 * @author: xj
 * @date:2017年1月11日 下午4:01:59
 * @version:v1.0
 */
public interface CaseApplyBeforeCustomerRepository extends CustomRepository<CaseApplyBeforeCustomer, String> {
	/**
	 * 
	 * 根据案件id和关系查询
	 *
	 * @author xj
	 * @param caseApplyId
	 * @param joinType
	 * @return
	 */
	public List<CaseApplyBeforeCustomer> findByCaseApplyIdAndJoinType(String caseApplyId, String joinType);

	/**
	 * 
	 * 根据案件id
	 *
	 * @author xj
	 * @param caseApplyId
	 * @param joinType
	 * @return
	 */
	public List<CaseApplyBeforeCustomer> findByCaseApplyId(String caseApplyId);

	/**
	 * 
	 * 通过案件id和客户id查询关联关系
	 *
	 * @author xj
	 * @param caseApplyId
	 * @param beforeCustomerId
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
	 * 关联贷款信息,sql
	 * 
	 * @author liuhuan
	 */
	public StringBuffer sql = new StringBuffer(
			" SELECT cbc.joinType   joinType, "
			+ " cbp.customerName   customerName, "
			+ " mca.caseApplyCode   caseApplyCode, "
			+ " mhp.floorAge  floorAge, "
			+ " mhp.area   area, "
			+ " mpi.pledgeType   pledgeType, "
			+ " mhp.estateProperties   "
			+ " estateProperties, "
			+ " mpi.pledgeAmout   pledgeAmout "
			+ " FROM mark_collateral   mc, "
			+ " mark_house_property   mhp, "
			+ " mark_pledge_info   mpi, "
			+ " case_before_customer   cbc "
			+ " LEFT JOIN cus_before_customer   cbp ON cbc.customerId = cbp.id "
			+ " LEFT JOIN mark_case_apply   mca ON cbc.caseApplyId = mca.id "
			+ " WHERE 1 = 1 "
			+ " AND mc.caseApplyId = mca.id "
			+ " AND mc.collateralType = 'HouseProperty' "
			+ " AND mhp.id = mc.id "
			+ " AND mpi.housePropertyId = mhp.id "
			+ " AND mpi.id =:customerId ");

}
