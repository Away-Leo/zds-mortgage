package com.zdsoft.finance.marketing.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.marketing.entity.CaseMaterialPromise;

/** 	
* 版权所有：重庆正大华日软件有限公司	
* @Title: CaseMaterialPromiseService.java 	
* @Package com.zdsoft.finance.marketing.service 	
* @Description: 后补资料service	
* @author liuhuan 	
* @date 2017年1月17日 下午4:10:09 	
* @version V1.0 	
*/
public interface CaseMaterialPromiseService extends BaseService<CaseMaterialPromise>{
	
	/**
	 * 根据案件ID 查询后补资料
	 * @param caseApplyId 案件id
	 * @return
	 */
	public List<CaseMaterialPromise> queryByCaseApplyId(String caseApplyId) throws Exception;
	
}
