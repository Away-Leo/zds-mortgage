package com.zdsoft.finance.casemanage.limitApply.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.casemanage.limitApply.entity.CaseLimitApply;
import com.zdsoft.finance.common.base.CustomRepository;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CaseLimitApplyRepository.java 
 * @ClassName: CaseLimitApplyRepository 
 * @Description: 案件额度申请的repository
 * @author xj 
 * @date 2017年2月17日 下午2:53:44 
 * @version V1.0
 */
public interface CaseLimitApplyRepository extends CustomRepository<CaseLimitApply, String> {

	/**
	 * 
	 * @Title: findByCreditEntrustId 
	 * @Description: 通过信托计划id查询额度申请集合
	 * @author xj 
	 * @param creditEntrustId 托计划id
	 * @param effectiveStatus 状态
	 * @return
	 */
	@Query(" from CaseLimitApply l where l.fundPlanId = :creditEntrustId and l.isDeleted = false and l.effectiveStatus = :effectiveStatus ")
	public List<CaseLimitApply> findByCreditEntrustId(@Param("creditEntrustId") String creditEntrustId,
			@Param("effectiveStatus") String effectiveStatus);
	
	/**
	 * 
	 * @Title: findByCaseApplyIdOrderByCreateTime 
	 * @Description: 通过案件id查询额度申请列表（根据创建时间升序）
	 * @author xj 
	 * @param caseApplyId 案件id
	 * @return
	 */
	public List<CaseLimitApply> findByCaseApplyIdAndIsDeletedOrderByCreateTime(String caseApplyId,Boolean isDeleted);
	
	/**
	 * 
	 * @Title: findByCaseApplyIdAndEffectiveStatusOrderByCreateTime 
	 * @Description: 通过案件id和额度状态查询
	 * @author xj 
	 * @param caseApplyId 案件id
	 * @param effectiveStatus 额度状态
	 * @return
	 */
	public List<CaseLimitApply> findByCaseApplyIdAndEffectiveStatusAndIsDeletedOrderByCreateTime(String caseApplyId,String effectiveStatus,Boolean isDeleted);

}
