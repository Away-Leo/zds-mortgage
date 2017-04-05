package com.zdsoft.finance.credit.service;

import java.util.List;

import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.credit.entity.CustomerCreditStatistics;
import com.zdsoft.finance.credit.entity.MyCredit;

/** 
* 版权所有：重庆正大华日软件有限公司
* @Title: CustomerCreditStatisticsService.java 
* @Package com.zdsoft.finance.credit.service 
* @Description: 客户征信统计Service
* @author jingyh 
* @date 2017年2月22日 下午7:39:07 
* @version V1.0 
*/
public interface CustomerCreditStatisticsService {

	/**
	 * 
	 * @Title: findByCustomerIdsAndCaseApplyIdAndCaseApplyStage 
	 * @Description: 根据案件，客户，案件阶段查询征信信息
	 * @author jingyh 
	 * @param caseApplyId
	 * @param customerIds
	 * @param caseApplyStage
	 * @return
	 */
	public List<CustomerCreditStatistics> findByCustomerIdsAndCaseApplyIdAndCaseApplyStage(String caseApplyId, List<String> customerIds, String caseApplyStage);
	
	/**
	 * 
	 * @Title: saveOrUpdateStatistic 
	 * @Description: 保存或更新征信统计信息
	 * @author jingyh 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public CustomerCreditStatistics saveOrUpdateStatistic(CustomerCreditStatistics entity) throws Exception;

	/**
	 * @Title: showLoanRecordBySourceMarkId 
	 * @Description: 根据征信来源id和来源类型取得贷款记录
	 * @author panshm 
	 * @param sourceMarkId 征信来源id
	 * @param sourceFrom 征信来源类型
	 * @return List<MyCredit>
	 */
	public List<MyCredit> showLoanRecordBySourceMarkId(String sourceMarkId, String sourceFrom) throws BusinessException;
	
	/**
	 * @Title: findById 
	 * @Description: 根据征信统计Id查询统计信息
	 * @author jingyh 
	 * @param id
	 * @return
	 */
	public CustomerCreditStatistics findById(String id);
	
}
