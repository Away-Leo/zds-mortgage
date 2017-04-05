package com.zdsoft.finance.finance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.finance.entity.RepaymentAmountAllot;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: RepaymentAmountAllotRepostory.java 
 * @ClassName: RepaymentAmountAllotRepostory 
 * @Description: 还款-收款单-金额分配Repostory
 * @author jincheng 
 * @date 2017年2月17日 下午5:05:37 
 * @version V1.0
 */
public interface RepaymentAmountAllotRepostory extends CustomRepository<RepaymentAmountAllot, String> {

	/**
	 * @Title: findByReceiptId 
	 * @Description: 通过收款id获取分配
	 * @author jincheng 
	 * @param receiptId
	 * @return
	 */
	@Query(" from RepaymentAmountAllot where   receiptId =?1  order by periods  ")
	List<RepaymentAmountAllot> findByReceiptId(String receiptId);

	/**
	 * @Title: findByPlanId 
	 * @Description: 根据还款计划id获取还款分配
	 * @author jincheng 
	 * @param planId
	 * @return
	 */
	@Query(" from RepaymentAmountAllot where isReview='F' and  planId =?1  order by createTime desc ")
	List<RepaymentAmountAllot> findByPlanId(String planId);
	
	
	/**
	 * @Title: findRepaymentAmountAllotByPlanId 
	 * @Description: 根据还款计划id获取还款分配
	 * @author jincheng 
	 * @param planId
	 * @return
	 */
	@Query(" from RepaymentAmountAllot where isReview='T' and  planId =?1  order by createTime desc ")
	List<RepaymentAmountAllot> findRepaymentAmountAllotByPlanId(String planId);

}
