package com.zdsoft.finance.specialapprove.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.finance.specialapprove.entity.SpecialApproveThings;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: SpecialApproveThingsService.java
 * @ClassName: SpecialApproveThingsService
 * @Description: 风险特批service
 * @author wangrongwei
 * @date 2017年2月15日 下午5:48:20
 * @version V1.0
 */
public interface SpecialApproveThingsService extends BaseService<SpecialApproveThings> {

	/**
	 * @Title: findByCaseApplyIdAndType
	 * @Description: 通过案件ID查询所有风险特批事项
	 * @author wangrongwei
	 * @param caseApplyId
	 *            案件ID
	 * @return 风险特批事项集合
	 */
	public List<SpecialApproveThings> findByCaseApplyId(String caseApplyId);

	/** 
	 * @Title: saveOrCommitRiskSpecialApproveApply 
	 * @Description: 保存或提交风险特批申请
	 * @author wangrongwei
	 * @param riskItemMap 风险特批项
	 * @param submitStatus 是否提交
	 * @param caseApplyId 案件ID
	 * @param specialApproveManageId 特批ID
	 * @param remark 备注
	 * @param isSystem 是否系统触发的风险规则
	 * @return
	 * @throws Exception  
	 */ 
	public BusiForm saveOrCommitRiskSpecialApproveApply(Map<String, String> riskItemMap, Boolean submitStatus,
			String caseApplyId, String specialApproveManageId, String remark,boolean isSystem,String otherInfo) throws Exception;

	/**
	 * @Title: findByItemType
	 * @Description: 通过事项类别查询风险特批事项
	 * @author wangrongwei
	 * @param itemType
	 * @return
	 */
	public List<SpecialApproveThings> findByItemType(String itemType);
}
