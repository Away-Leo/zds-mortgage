package com.zdsoft.finance.marketing.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.marketing.entity.CaseTask;

/** 	
* 版权所有：重庆正大华日软件有限公司	
* @Title: CaseTaskService.java 	
* @Package com.zdsoft.finance.marketing.service 	
* @Description: 资信员 信息	
* @author liuhuan 	
* @date 2017年1月18日 下午8:41:34 	
* @version V1.0 	
*/
public interface CaseTaskService extends BaseService<CaseTask>{
	
	/**
	 * 根据案件id 查询资信员 信息
	 * @param caseApplyId
	 * @return
	 */
	public CaseTask findByCaseApplyId(String caseApplyId);
	
}
