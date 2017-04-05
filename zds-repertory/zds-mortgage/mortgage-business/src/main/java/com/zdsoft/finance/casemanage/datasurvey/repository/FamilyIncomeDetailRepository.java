package com.zdsoft.finance.casemanage.datasurvey.repository;

import java.util.List;

import com.zdsoft.finance.casemanage.datasurvey.entity.FamilyIncomeDetail;
import com.zdsoft.finance.common.base.CustomRepository;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: FamilyIncomeDetailRepository.java 
 * @ClassName: FamilyIncomeDetailRepository 
 * @Description: 家庭收入详情
 * @author liuhuan 
 * @date 2017年2月13日 上午10:39:01 
 * @version V1.0
 */
public interface FamilyIncomeDetailRepository extends CustomRepository<FamilyIncomeDetail, String> {

	
	/** 
	 * @Title: findByFamilyIncomeId 
	 * @Description: 根据家庭收入id 查询家庭收入详情
	 * @author liuhuan 
	 * @param familyIncomeId 家庭收入id
	 * @return  
	 */ 
	public List<FamilyIncomeDetail> findByFamilyIncomeIdOrderByRealMonthDesc(String familyIncomeId);
	
	/** 
	 * @Title: deleteByFamilyIncomeId 
	 * @Description: 根据家庭收入id 删除家庭收入详情
	 * @author liuhuan 
	 * @param familyIncomeId  家庭收入id
	 */ 
	public void deleteByFamilyIncomeId(String familyIncomeId);
	
}
