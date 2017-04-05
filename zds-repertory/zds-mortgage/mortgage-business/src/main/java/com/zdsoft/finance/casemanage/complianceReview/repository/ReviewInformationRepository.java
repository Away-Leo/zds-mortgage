package com.zdsoft.finance.casemanage.complianceReview.repository;

import java.util.List;

import com.zdsoft.finance.casemanage.complianceReview.entity.ReviewInformation;
import com.zdsoft.finance.common.base.CustomRepository;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ReviewInformationRepository.java 
 * @ClassName: ReviewInformationRepository 
 * @Description: 案件符合资料
 * @author xj 
 * @date 2017年2月20日 下午5:59:19 
 * @version V1.0
 */
public interface ReviewInformationRepository extends CustomRepository<ReviewInformation, String> {
	
	/**
	 * 
	 * @Title: findByComplianceReviewId 
	 * @Description: 通过合规复合ID查询列表
	 * @author xj 
	 * @param complianceReviewId 合规复合ID
	 */
	public List<ReviewInformation> findByComplianceReviewId(String complianceReviewId);
	
	/**
	 * 
	 * @Title: findByThirdMarkCode 
	 * @Description: 通过三级fullcode查询
	 * @author xj 
	 * @param thirdMarkCode 三级fullcode
	 * @return
	 */
	public ReviewInformation findByThirdMarkCode(String thirdMarkCode);
}
