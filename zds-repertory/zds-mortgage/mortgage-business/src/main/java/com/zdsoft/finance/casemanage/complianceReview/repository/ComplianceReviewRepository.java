package com.zdsoft.finance.casemanage.complianceReview.repository;

import com.zdsoft.finance.casemanage.complianceReview.entity.ComplianceReview;
import com.zdsoft.finance.common.base.CustomRepository;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ComplianceReviewRepository.java 
 * @ClassName: ComplianceReviewRepository 
 * @Description: 合规复核
 * @author xj 
 * @date 2017年2月20日 下午5:57:30 
 * @version V1.0
 */
public interface ComplianceReviewRepository extends CustomRepository<ComplianceReview, String>{
	
	/**
	 * 
	 * @Title: findByCaseApplyId 
	 * @Description: 通过案件id查询合规复核
	 * @author xj 
	 * @param caseApplyId 案件id
	 * @return
	 */
	public ComplianceReview findByCaseApplyId(String caseApplyId);
}
