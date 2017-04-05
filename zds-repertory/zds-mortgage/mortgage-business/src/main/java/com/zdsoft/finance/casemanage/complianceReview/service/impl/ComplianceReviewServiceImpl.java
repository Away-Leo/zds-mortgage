package com.zdsoft.finance.casemanage.complianceReview.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.casemanage.complianceReview.entity.ComplianceReview;
import com.zdsoft.finance.casemanage.complianceReview.entity.ReviewInformation;
import com.zdsoft.finance.casemanage.complianceReview.repository.ComplianceReviewRepository;
import com.zdsoft.finance.casemanage.complianceReview.service.ComplianceReviewService;
import com.zdsoft.finance.casemanage.complianceReview.service.ReviewInformationService;
import com.zdsoft.finance.casemanage.handleMortgage.entity.MaterialPromise;
import com.zdsoft.finance.casemanage.handleMortgage.service.MaterialPromiseService;
import com.zdsoft.framework.core.common.util.ObjectHelper;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ComplianceReviewServiceImpl.java 
 * @ClassName: ComplianceReviewServiceImpl 
 * @Description: 合规复核service
 * @author xj 
 * @date 2017年2月22日 下午7:38:37 
 * @version V1.0
 */
@Service("complianceReviewService")
public class ComplianceReviewServiceImpl  extends BaseServiceImpl<ComplianceReview, ComplianceReviewRepository>
implements ComplianceReviewService {
	@Autowired
	private CED CED;
	@Autowired
	private ReviewInformationService reviewInformationService;
	@Autowired
	private MaterialPromiseService materialPromiseService;
	
	@Transactional(rollbackFor=Exception.class)
	@Override
	public ComplianceReview saveOrUpdateReviewAndData(ComplianceReview complianceReview,
			List<ReviewInformation> reviewInformations,List<MaterialPromise> materialPromises) throws Exception{
		EmpDto empDto = CED.getLoginUser();
		String empCd = empDto.getEmpCd();
		String orgCd = empDto.getOrgCd();
		List<MaterialPromise> dbMaterialPromises = new ArrayList<MaterialPromise>();
		if(ObjectHelper.isNotEmpty(materialPromises)){
			for (MaterialPromise materialPromise : materialPromises) {
				materialPromise.setCreateBy(empCd);
				materialPromise.setCreateOrgCd(orgCd);
				if(ObjectHelper.isNotEmpty(materialPromise.getPredictDate()) || ObjectHelper.isNotEmpty(materialPromise.getPromiseRemark())){
					dbMaterialPromises.add(materialPromise);
				}else if(ObjectHelper.isNotEmpty(materialPromise.getId())){
					materialPromiseService.deleteById(materialPromise.getId());
				}
			}
			materialPromiseService.saveMaterialPromiseList(dbMaterialPromises);
		}
		//修改或者保存 合规复核
		if(ObjectHelper.isNotEmpty(complianceReview.getId())){
			//修改
			ComplianceReview dbComplianceReview = this.customReposity.getOne(complianceReview.getId());
			dbComplianceReview.setReviewResult(complianceReview.getReviewResult());
			dbComplianceReview.setRemark(complianceReview.getRemark());
			complianceReview = this.customReposity.updateEntity(dbComplianceReview);
		}else{
			//保存
			complianceReview.setCreateBy(empCd);
			complianceReview.setCreateOrgCd(orgCd);
			complianceReview = this.customReposity.saveEntity(complianceReview);
		}
		//通过
		if(ComplianceReview.REVIEW_PASS.equals(complianceReview.getReviewResult())){
			reviewInformationService.deleteByComplianceReviewId(complianceReview.getId());
		}else{
			//复核未通过 否决
			reviewInformationService.deleteByComplianceReviewId(complianceReview.getId());
			reviewInformationService.addReviewInformation(reviewInformations,complianceReview.getId());
		}
		return complianceReview;
	}

	@Override
	public ComplianceReview findByCaseApplyId(String caseApplyId) {
		return this.customReposity.findByCaseApplyId(caseApplyId);
	}



}
