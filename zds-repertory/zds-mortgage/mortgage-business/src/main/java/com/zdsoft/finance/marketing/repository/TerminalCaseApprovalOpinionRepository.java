package com.zdsoft.finance.marketing.repository;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.marketing.entity.TerminalCaseApprovalOpinion;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: TerminalCaseApprovalOpinionRepository.java 
 * @ClassName: TerminalCaseApprovalOpinionRepository 
 * @Description: 终端进件审批意见基础实现
 * @author xiongpan
 * @date 2017年3月4日 上午11:13:15 
 * @version V1.0 
 */ 
public interface TerminalCaseApprovalOpinionRepository extends CustomRepository<TerminalCaseApprovalOpinion, String> {

	/**
	 * 
	 * @Title: findByCaseApplyId 
	 * @Description: 通过案件id来查询终端进件审批意见
	 * @author xiongpan
	 * @param caseApplyId 案件id
	 * @return
	 */
	TerminalCaseApprovalOpinion findByCaseApplyId(String caseApplyId);

}
