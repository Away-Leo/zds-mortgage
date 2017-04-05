package com.zdsoft.finance.casemanage.complianceReview.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.casemanage.complianceReview.entity.ReviewInformation;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ReviewInformationService.java 
 * @ClassName: ReviewInformationService 
 * @Description: 案件复核资料service
 * @author xj 
 * @date 2017年2月20日 下午7:23:43 
 * @version V1.0
 */
public interface ReviewInformationService extends BaseService<ReviewInformation> {
	
	/**
	 * @Title: deleteByComplianceReviewId 
	 * @Description: 通过合规复核id删除案件复核资料
	 * @author xj 
	 * @param complianceReviewId 合规复核id
	 */
	public void deleteByComplianceReviewId(String complianceReviewId) throws Exception;
	
	/**
	 * 
	 * @Title: addReviewInformation 
	 * @Description: 添加复核资料
	 * @author xj 
	 * @param reviewInformations 复核资料
	 * @param complianceReviewId 合规复核id
	 * @throws Exception
	 */
	public void addReviewInformation(List<ReviewInformation> reviewInformations,String complianceReviewId) throws Exception;
	
	/**
	 * 
	 * @Title: findByComplianceReviewId 
	 * @Description: 通过合规复核id查询案件复核资料
	 * @author xj 
	 * @param complianceReviewId 合规复核id
	 */
	public List<ReviewInformation> findByComplianceReviewId(String complianceReviewId);
}
