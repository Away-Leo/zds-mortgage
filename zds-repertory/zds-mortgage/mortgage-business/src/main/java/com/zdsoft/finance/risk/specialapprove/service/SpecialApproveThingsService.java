package com.zdsoft.finance.risk.specialapprove.service;

import java.util.List;

import com.zdsoft.finance.risk.entity.SpecialApproveThings;


/** 	
* 版权所有：重庆正大华日软件有限公司	
* @Title: SpecialApproveService.java 	
* @Package com.zdsoft.finance.risk.specialapprove.service 	
* @Description: 风险特批
* @author Liyb 	
* @date 2017年1月15日 下午4:14:04 	
* @version V1.0 	
*/
public interface SpecialApproveThingsService{
    
    public List<SpecialApproveThings> querySpecialApproveThingsByCaseApplyId(String caseApplyId);

	
}
