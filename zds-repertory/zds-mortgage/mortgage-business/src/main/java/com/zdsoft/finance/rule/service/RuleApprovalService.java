package com.zdsoft.finance.rule.service;

/**
* 版权所有：重庆正大华日软件有限公司   
* @Title: RuleApprovalService.java 
* @Package com.zdsoft.finance.rule.service 
* @Description: 规则审批判断service(一审，二审判断)
* @author zjx  
* @date 2017年2月25日 下午2:49:22 
* @version V1.0   
*/
public interface RuleApprovalService {

	/** 
	 * @Title: firstApproval 
	 * @Description: 一审调用规则
	 * @author zjx 
	 * @param caseApplyId
	 * @return  
	 */ 
	Boolean firstApproval(String caseApplyId,Integer isRefuse) throws Exception;
	
	/** 
	 * @Title: firstApproval 
	 * @Description: 二审调用规则
	 * @author zjx 
	 * @param caseApplyId
	 * @return  
	 */ 
	Boolean secondApproval(String caseApplyId,Integer isRefuse) throws Exception;
	
	/** 
	 * @Title: costApproval 
	 * @Description: 费用特批
	 * @author zjx 
	 * @param caseApplyId
	 * @throws Exception  
	 */ 
	void costApproval(String caseApplyId) throws Exception;
	
	/** 
	 * @Title: caseScoreApproval 
	 * @Description: 调用评分规则并写入数据
	 * @author zjx 
	 * @param caseApplyId
	 * @throws Exception  
	 */ 
	void caseScoreApproval(String caseApplyId) throws Exception;

}
