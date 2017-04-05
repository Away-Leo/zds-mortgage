package com.zdsoft.finance.casemanage.receivablePlan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivablePlan;
import com.zdsoft.finance.common.base.CustomRepository;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ReceivablePlanRepository.java 
 * @ClassName: ReceivablePlanRepository 
 * @Description: 还款计划Repository 
 * @author jincheng 
 * @date 2017年2月17日 下午2:07:39 
 * @version V1.0
 */
public interface ReceivablePlanRepository extends CustomRepository<ReceivablePlan, String>{
	
	/** 
	 * @Title: findCurrentReceivalbe 
	 * @Description: 根据案件ID查询制定日期范围内的还款计划
	 * @author wangrongwei
	 * @param caseApplyId 案件ID
	 * @param planDate 指定日期
	 * @return  
	 */ 
	@Query("from ReceivablePlan where caseApplyId=:caseApplyId and startDate < :planDate and endDate > :planDate ")
	public ReceivablePlan queryByCaseApplyIdAndPlanDate(@Param("caseApplyId")String caseApplyId,@Param("planDate")Long planDate);

	/**
	 * 通过还款计划基本信息ID查询还款计划
	 *
	 * @author wrw
	 * @param receivableInfoId
	 * @return
	 */
	public List<ReceivablePlan> findByReceivableInfoIdOrderByCreateTimeAsc(String receivableInfoId);
	
	/**
	 * 通过还款计划基本信息ID删除还款计划
	 *
	 * @author wrw
	 * @param receivableInfoId
	 */
	@Modifying
	@Query("delete from ReceivablePlan where receivableInfoId = :id")
	public void deleteByReceivableInfoId(@Param("id")String receivableInfoId);

	/**
	 * @Title: findReceivablePlanByCaseApplyId 
	 * @Description: 根据案件id获取还款计划
	 * @author jincheng 
	 * @param caseApplyId
	 * @return
	 */
	public List<ReceivablePlan> findReceivablePlanByCaseApplyIdOrderByCreateTimeAsc(String caseApplyId);

	/**
	 * @Title: findReceivablePlanByCaseApplyId 
	 * @Description: 根据案件id,时间获取未结清还款计划
	 * @author jingjy 
	 * @param caseApplyId
	 * 			案件ID
	 * @param planRepayDate
	 * 			查询时间
	 * @return
	 */
	@Query(" from ReceivablePlan where caseApplyId =:caseApplyId AND planRepayDate <=:planRepayDate AND settlement='F' order by createTime asc ")
	public List<ReceivablePlan> findReceivablePlanByCaseApplyIdAndPlanRepayDate(@Param(value="caseApplyId")String caseApplyId,@Param(value="planRepayDate")Long planRepayDate);
	
	/**
	 * @Title: findReceivablePlanByLoanApplyId 
	 * @Description: 根据放款id获取还款计划
	 * @author jincheng 
	 * @param loanApplyId
	 * @return
	 */
	public List<ReceivablePlan> findReceivablePlanByLoanApplyIdOrderByCreateTimeAsc(String loanApplyId);

	/**
	 * @Title: findByCaseApplyIdAndIsDeleted 
	 * @Description: 还款计划
	 * @author gufeng 
	 * @param caseApplyId 案件id
	 * @param isDeleted 是否删除
	 * @return 还款计划
	 */
	public List<ReceivablePlan> findByCaseApplyIdAndIsDeleted(String caseApplyId, boolean isDeleted);
}
