package com.zdsoft.finance.casemanage.complianceReview.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.casemanage.complianceReview.entity.ComplianceReview;
import com.zdsoft.finance.casemanage.complianceReview.entity.ReviewInformation;
import com.zdsoft.finance.casemanage.handleMortgage.entity.MaterialPromise;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ComplianceReviewService.java 
 * @ClassName: ComplianceReviewService 
 * @Description: 合规复核service
 * @author xj 
 * @date 2017年2月20日 下午7:28:29 
 * @version V1.0
 */
public interface ComplianceReviewService extends BaseService<ComplianceReview> {
	
	/**
	 * 
	 * @Title: saveOrUpdateReviewAndData 
	 * @Description: 合规复核保存
	 * @author xj 
	 * @param complianceReview 合规复核主表
	 * @param reviewInformations 合规复核资料
	 * @param materialPromises 复核信息
	 * @return
	 */
	public ComplianceReview saveOrUpdateReviewAndData(ComplianceReview complianceReview,List<ReviewInformation> reviewInformations, List<MaterialPromise> materialPromises) throws Exception;
	
	/**
	 * 
	 * @Title: findByCaseApplyId 
	 * @Description: 通过案件id查询
	 * @author xj 
	 * @param caseApplyId 案件id
	 * @return
	 */
	public ComplianceReview findByCaseApplyId(String caseApplyId);

}
