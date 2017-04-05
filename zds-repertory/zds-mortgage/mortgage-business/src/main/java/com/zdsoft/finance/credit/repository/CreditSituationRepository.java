
package com.zdsoft.finance.credit.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.credit.entity.CreditSituation;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CreditSituationRepository.java 
 * @ClassName: CreditSituationRepository 
 * @Description: 征信综合情况 Repository
 * @author dengyy 
 * @date 2017年2月22日 下午9:51:20 
 * @version V1.0 
 */
public interface CreditSituationRepository extends CustomRepository<CreditSituation, String> {

	/**
	 * @Title: findByCreditId 
	 * @Description: 根据征信id获取征信综合信息
	 * @author zhongyong 
	 * @param creditId 征信id
	 * @return
	 */
	public CreditSituation findByCreditId(String creditId);
	
	/**
	 * @Title: findByCustomerId 
	 * @Description: 根据客户id获取征信综合信息
	 * @author zhongyong 
	 * @param customerId 客户id
	 * @return
	 */
	public CreditSituation findByCustomerId(String customerId);

    /** 
     * @Title: findByCaseApplyId 
     * @Description: 获取案件的征信记录
     * @author dengyy 
     * @param caseApplyId 案件id
     * @return  
     */ 
    public List<CreditSituation> findByCaseApplyId(String caseApplyId);
}
