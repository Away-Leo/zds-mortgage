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
	 * @param caseApplyId 案件id
	 * @return
	 */
	public CaseTask findByCaseApplyId(String caseApplyId);
	
	/**
	 * 
	 * @Title: saveOrUpdateCaseTask 
	 * @Description: 保存或提交资调员信息
	 * @author caixiekang 
	 * @param caseTask 资调信息Po对象
	 * @param submitted 是否提交
	 * @return
	 */
	public CaseTask saveOrUpdateCaseTask(CaseTask caseTask) throws Exception;
	
}
