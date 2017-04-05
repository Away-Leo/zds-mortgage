package com.zdsoft.finance.casemanage.datasurvey.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.casemanage.datasurvey.entity.FamilyIncome;
import com.zdsoft.finance.casemanage.datasurvey.entity.FamilyIncomeDetail;
import com.zdsoft.finance.common.exception.BusinessException;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: FamilyIncomeService.java 
 * @ClassName: FamilyIncomeService 
 * @Description: 家庭收入
 * @author liuhuan 
 * @date 2017年2月13日 上午10:42:33 
 * @version V1.0
 */
public interface FamilyIncomeService extends BaseService<FamilyIncome>{
	
	/**
	 * 
	 * @Title: findByCaseApplyId 
	 * @Description: 根据案件Id查询客户家庭收入
	 * @author zhoushichao 
	 * @param caseApplyId
	 * @return
	 */
	public List<FamilyIncome> findByCaseApplyId(String caseApplyId);
	
	/**
	 * 
	 * @Title: deleteByFamilyIncomeId 
	 * @Description: 根据家庭收入Id删除家庭收入
	 * @author zhoushichao 
	 * @param familyIncomeId
	 */
	public void deleteByFamilyIncomeId(String familyIncomeId);
	/**
	 * @Title: saveFamilyIncome 
	 * @Description: 保存家庭收入信息
	 * @author zhoushichao 
	 * @param familyIncome 家庭收入人员基本信息
	 * @param familyIncomeDetailList  客户每月收入信息
	 * @throws BusinessException
	 */
	public FamilyIncome saveFamilyIncome(FamilyIncome familyIncome,List<FamilyIncomeDetail> familyIncomeDetailList) throws BusinessException;
	
}
