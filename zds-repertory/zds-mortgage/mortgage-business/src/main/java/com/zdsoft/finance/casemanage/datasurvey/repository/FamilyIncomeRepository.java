package com.zdsoft.finance.casemanage.datasurvey.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.casemanage.datasurvey.entity.FamilyIncome;
import com.zdsoft.finance.common.base.CustomRepository;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: FamilyIncomeRepository.java 
 * @ClassName: FamilyIncomeRepository 
 * @Description: 家庭收入的repository
 * @author liuhuan 
 * @date 2017年2月13日 上午10:28:43 
 * @version V1.0
 */
public interface FamilyIncomeRepository  extends CustomRepository<FamilyIncome, String>{
	
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
	 * @Title: deleteByCustomerId 
	 * @Description: 根据客户id 删除家庭收入
	 * @author liuhuan 
	 * @param customerId 客户id
	 */
	@Query("delete from FamilyIncome where customerId=:customerId")
	public void deleteByCustomerId(@Param("customerId")String customerId);
}
