package com.zdsoft.finance.casemanage.limitapply.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.casemanage.limitapply.entity.CaseLimitApply;
import com.zdsoft.finance.common.base.CustomRepository;

/**
 * 案件额度申请
 * 
 * @author xiongpan
 * @date 2017-1-6
 */

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:CaseLimitApplyRepository.java
 * @Package:com.zdsoft.finance.casemanage.limitapply.repository
 * @Description: 案件额度申请的repository
 * @author: xiongpan
 * @date:2017年1月6日 下午10:50:12
 * @version:v1.0
 */
public interface CaseLimitApplyRepository extends CustomRepository<CaseLimitApply, String> {

	/**
	 * 查询案件目前的额度申请信息(通过外键和最新的申请时间)
	 * 
	 * @param caseApplyId
	 * @return
	 */
	@Query(" from CaseLimitApply l where l.caseApply.id =:caseApplyId order by l.applyDate desc")
	public List<CaseLimitApply> findByCaseApplyId(@Param("caseApplyId") String caseApplyId);

	/**
	 * 通过信托计划id查询额度申请集合
	 * 
	 * @param creditEntrustId
	 *            信托计划id
	 * @param effectiveStatus
	 *            生效状态
	 * @return 额度申请集合
	 */
	@Query(" from CaseLimitApply l where l.creditEntrust.id = :creditEntrustId and l.isDeleted = false and l.caseApply.actualLimitStatus = :effectiveStatus ")
	public List<CaseLimitApply> findByCreditEntrustId(@Param("creditEntrustId") String creditEntrustId,
			@Param("effectiveStatus") String effectiveStatus);

}
