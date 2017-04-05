package com.zdsoft.finance.specialapprove.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.finance.specialapprove.entity.SpecialApproveRemission;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: SpecialApproveRemissionService.java
 * @ClassName: SpecialApproveRemissionService
 * @Description: 费用减免特批service
 * @author wangrongwei
 * @date 2017年2月15日 下午5:48:20
 * @version V1.0
 */
public interface SpecialApproveRemissionService extends BaseService<SpecialApproveRemission> {

	/** 
	 * @Title: saveOrCommitSpecialApproveRemissionApply 
	 * @Description: 保存提交费用特批申请
	 * @author wangrongwei
	 * @param caseApplyId 案件ID 必填
	 * @param specialApproveManageId 特批管理ID 可选
	 * @param remark 备注
	 * @param penaltyUseStandard 罚息挂钩标准
	 * @param list 费用减免项
	 * @param isSubmit 是否提交
	 * @return
	 * @throws Exception  
	 */ 
	public BusiForm saveOrCommitSpecialApproveRemissionApply(String caseApplyId, String specialApproveManageId, String remark,
			String penaltyUseStandard,List<SpecialApproveRemission> list,Boolean isSubmit) throws Exception;
}
