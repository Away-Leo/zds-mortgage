package com.zdsoft.finance.credit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.credit.entity.CustomerCreditStatistics;

/** 
* 版权所有：重庆正大华日软件有限公司
* @Title: CustomerCreditStatisticsRepository.java 
* @Package com.zdsoft.finance.credit.repository 
* @Description: 客户征信统计Repository层
* @author jingyh 
* @date 2017年2月22日 下午7:36:27 
* @version V1.0 
*/
public interface CustomerCreditStatisticsRepository extends CustomRepository<CustomerCreditStatistics, String> {

	/**
	 * 
	 * @Title: findStatisInfoByCustomerIdsAndCaseApplyIdAndStage 
	 * @Description: 根据案件，客户，案件阶段查询征信统计信息
	 * @author jingyh 
	 * @param caseApplyId
	 * @param customerIds
	 * @param caseApplyStage
	 * @return
	 */
	@Query("SELECT ccs FROM CustomerCreditStatistics ccs WHERE ccs.caseApplyId = :caseApplyId AND ccs.customerId IN (:customerIds) AND ccs.caseApplyStage = :caseApplyStage")
	public List<CustomerCreditStatistics> findStatisInfoByCustomerIdsAndCaseApplyIdAndStage(@Param("caseApplyId")String caseApplyId, 
			@Param("customerIds")List<String> customerIds, @Param("caseApplyStage")String caseApplyStage);
	
	/**
	 * 
	 * @Title: findByCustomerId 
	 * @Description: 根据案件，客户，阶段，来源定位唯一一条记录
	 * @author jingyh 
	 * @param customerId
	 * 			客户Id
	 * @param caseApplyId
	 * 			案件Id
	 * @param caseApplyStage 
	 * 			案件阶段
	 * @param SourceFrom
	 * 			来源
	 * @return
	 */
	public CustomerCreditStatistics findByCustomerIdAndCaseApplyIdAndCaseApplyStageAndSourceFrom(String customerId,String caseApplyId, String caseApplyStage, String SourceFrom);
	
	/**
	 * 
	 * @Title: findBySourceMarkId 
	 * @Description: 通过来源表示查询统计信息
	 * @author jingyh 
	 * @param sourceMarkId
	 * @return
	 */
	public CustomerCreditStatistics findBySourceMarkId(String sourceMarkId);
}
