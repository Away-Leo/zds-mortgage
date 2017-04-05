package com.zdsoft.finance.casemanage.datasurvey.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.casemanage.datasurvey.entity.FamilyIncomeDetail;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: FamilyIncomeDetailService.java 
 * @ClassName: FamilyIncomeDetailService 
 * @Description: 家庭收入详情
 * @author liuhuan 
 * @date 2017年2月13日 上午10:40:39 
 * @version V1.0
 */
public interface FamilyIncomeDetailService extends BaseService<FamilyIncomeDetail>{
	
	/** 
	 * @Title: findByFamilyIncomeId 
	 * @Description: 根据家庭收入id 查询家庭收入详情
	 * @author liuhuan 
	 * @param familyIncomeId 家庭收入id
	 * @return  
	 */ 
	public List<FamilyIncomeDetail> findByFamilyIncomeId(String familyIncomeId);
	
	/** 
	 * @Title: deleteByFamilyIncomeId 
	 * @Description: 根据家庭收入id 删除家庭收入详情
	 * @author liuhuan 
	 * @param familyIncomeId  家庭收入id
	 */ 
	public void deleteByFamilyIncomeId(String familyIncomeId);
}
