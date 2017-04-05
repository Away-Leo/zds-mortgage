package com.zdsoft.finance.specialapprove.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.finance.specialapprove.entity.SpecialApproveFee;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: SpecialApproveThingsService.java
 * @ClassName: SpecialApproveThingsService
 * @Description: 费用特批service
 * @author wangrongwei
 * @date 2017年2月15日 下午5:48:20
 * @version V1.0
 */
public interface SpecialApproveFeeService extends BaseService<SpecialApproveFee> {

	/** 
	 * @Title: saveOrCommitFeeSpecialApproveApply 
	 * @Description: 保存提交费用特批申请
	 * @author wangrongwei
	 * @param caseApplyId 案件ID 必填
	 * @param specialApproveManageId 特批管理ID 可选
	 * @param remark 备注
	 * @param isSubmit 是否提交
	 * @return  
	 */ 
	public BusiForm saveOrCommitFeeSpecialApproveApply(String caseApplyId, String specialApproveManageId, String remark,
			Boolean isSubmit) throws Exception;
}
