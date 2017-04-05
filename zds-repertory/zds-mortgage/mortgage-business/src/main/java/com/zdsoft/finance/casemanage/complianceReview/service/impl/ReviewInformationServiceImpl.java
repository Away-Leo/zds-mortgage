package com.zdsoft.finance.casemanage.complianceReview.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.casemanage.complianceReview.entity.ReviewInformation;
import com.zdsoft.finance.casemanage.complianceReview.repository.ReviewInformationRepository;
import com.zdsoft.finance.casemanage.complianceReview.service.ReviewInformationService;
import com.zdsoft.framework.core.common.util.ObjectHelper;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ReviewInformationServiceImpl.java 
 * @ClassName: ReviewInformationServiceImpl 
 * @Description: 案件复核资料service
 * @author xj 
 * @date 2017年2月22日 下午7:50:19 
 * @version V1.0
 */
@Service("reviewInformationService")
public class ReviewInformationServiceImpl extends BaseServiceImpl<ReviewInformation,ReviewInformationRepository>
		implements ReviewInformationService {
	
	@Transactional(rollbackFor=Exception.class)
	@Override
	public void deleteByComplianceReviewId(String complianceReviewId) throws Exception {
		if(ObjectHelper.isEmpty(complianceReviewId)){
			throw new Exception("complianceReviewId is null");
		}
		List<ReviewInformation> reviewInformations = this.customReposity.findByComplianceReviewId(complianceReviewId);
		if(ObjectHelper.isNotEmpty(reviewInformations)){
			this.customReposity.delete(reviewInformations);
		}
		
	}
	
	@Transactional(rollbackFor=Exception.class)
	@Override
	public void addReviewInformation(List<ReviewInformation> reviewInformations, String complianceReviewId)
			throws Exception {
		if(ObjectHelper.isEmpty(complianceReviewId)){
			throw new Exception("complianceReviewId is null");
		}
		if(ObjectHelper.isNotEmpty(reviewInformations)){
			for (ReviewInformation reviewInformation : reviewInformations) {
				reviewInformation.setComplianceReviewId(complianceReviewId);
				this.customReposity.saveEntity(reviewInformation);
			}
		}
	}
	
	@Override
	public List<ReviewInformation> findByComplianceReviewId(String complianceReviewId) {
		return this.customReposity.findByComplianceReviewId(complianceReviewId);
		
	}


}
