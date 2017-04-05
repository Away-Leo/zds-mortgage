package com.zdsoft.finance.capital.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.zdsoft.finance.capital.entity.CreditEntrust;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CreditEntrustRepository.java
 * @ClassName: CreditEntrustRepository
 * @Description: 信托计划Repository
 * @author liuwei
 * @date 2017年2月8日 上午10:28:57
 * @version V1.0
 */
public interface CreditEntrustRepository extends CustomRepository<CreditEntrust, String> {

	/**
	 * 
	 * @Title: findByConditions
	 * @Description: 根据查询条件查询信托计划列表
	 * @author liuwei
	 * @param conditions
	 *            查询条件
	 * @return 信托计划列表
	 */
	public List<CreditEntrust> findByConditions(Map<String, Object> conditions);

	/**
	 * 
	 * @Title: getListMapBySql
	 * @Description: sql查询信托计划列表
	 * @author liuwei
	 * @param pageable
	 *            分页信息
	 * @param conditions
	 *            查询参数
	 * @return 信托计划Page
	 */
	public Page<Map<String, Object>> getListMapBySql(Pageable pageable, Map<String, Object> conditions);

	/**
	 * 
	 * @Title: reportPlanDistributionSql
	 * @Description: sql查询额度分配
	 * @author liuwei
	 * @param pageable
	 *            分页信息
	 * @param conditions
	 *            查询参数
	 * @return 信托计划Page
	 */
	public Page<Map<String, Object>> reportPlanDistributionSql(Pageable pageable, Map<String, Object> conditions);

	/**
	 * 
	 * @Title: cumulativeDischarge
	 * @Description: 累计查询放款额
	 * @author liuwei
	 * @param creditEntrustId
	 *            信托计划id
	 * @param nowDate
	 *            指定日期
	 * @return 累计放款额
	 */
	public BigDecimal cumulativeDischarge(String creditEntrustId, Long nowDate);

	/**
	 * 
	 * @Title: countTodayPaidAmount
	 * @Description: 计算今日还款数据(本金,利息,罚息,违约金)
	 * @author liuwei
	 * @param creditEntrustId
	 *            信托计划id
	 * @return Map(cumulativeRecoveryPrincipal,cumulativeRecoveryInterest,cumulativeRecoveryPenalty,cumulativeRecoveryLiqDamages)
	 */
	public Map<String, BigDecimal> countTodayPaidAmount(String creditEntrustId);

}
