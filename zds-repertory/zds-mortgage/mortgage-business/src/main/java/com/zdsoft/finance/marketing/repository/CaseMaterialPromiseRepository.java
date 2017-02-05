package com.zdsoft.finance.marketing.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.marketing.entity.CaseMaterialPromise;

/** 	
* 版权所有：重庆正大华日软件有限公司	
* @Title: CaseMaterialPromiseRepository.java 	
* @Package com.zdsoft.finance.marketing.repository 	
* @Description: 后补资料Repository
* @author liuhuan 	
* @date 2017年1月17日 下午4:08:50 	
* @version V1.0 	
*/
public interface CaseMaterialPromiseRepository extends CustomRepository<CaseMaterialPromise, String>{
	
	/**
	 * 根据案件ID 查询后补资料
	 * @param caseApplyId
	 * @return
	 */
	public List<CaseMaterialPromise> findByCaseApplyId(String caseApplyId);
	
}
