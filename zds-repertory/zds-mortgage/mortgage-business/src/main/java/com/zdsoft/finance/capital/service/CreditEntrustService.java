package com.zdsoft.finance.capital.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.capital.entity.CreditEntrust;
import com.zdsoft.finance.casemanage.limitApply.entity.CaseLimitApply;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CreditEntrustService.java
 * @ClassName: CreditEntrustService
 * @Description: 信托计划service
 * @author liuwei
 * @date 2017年2月8日 上午10:37:13
 * @version V1.0
 */
public interface CreditEntrustService extends BaseService<CreditEntrust> {

	/**
	 * 
	 * @Title: saveCreditEntrustAndOthers
	 * @Description: 保存信托计划信息及附属信息
	 * @author liuwei
	 * @param creditEntrust
	 *            信托计划信息
	 * @return 信托计划信息
	 * @throws Exception
	 *             调用平台接口出现Exception
	 */
	public CreditEntrust saveCreditEntrustAndOthers(CreditEntrust creditEntrust) throws Exception;

	/**
	 * 
	 * @Title: updateCreditEntrust
	 * @Description: 修改信托计划信息
	 * @author liuwei
	 * @param creditEntrust
	 *            信托计划信息
	 * @return 信托计划信息
	 * @throws Exception
	 */
	public CreditEntrust updateCreditEntrust(CreditEntrust creditEntrust) throws Exception;

	/**
	 * 
	 * @Title: reportSql
	 * @Description: sql分页查询
	 * @author liuwei
	 * @param pageable
	 *            分页信息
	 * @param queryObj
	 *            查询条件
	 * @return Page<Map> 信息
	 */
	public Page<Map<String, Object>> reportSql(Pageable pageable, Map<String, Object> queryObj);

	/**
	 * 
	 * @Title: reportPlanDistributionSql
	 * @Description: sql分页查询额度分配
	 * @author liuwei
	 * @param pageable
	 *            分页信息
	 * @param queryObj
	 *            查询条件
	 * @return Page<Map> 信息
	 */
	public Page<Map<String, Object>> reportPlanDistributionSql(Pageable pageable, Map<String, Object> queryObj);

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
	 * @Title: updateIncomeBalance
	 * @Description: 修改收入信息
	 * @author liuwei
	 * @param creditEntrust
	 *            信托计划
	 * @param amount
	 *            增加的收入
	 * @return 信托计划
	 * @throws BusinessException
	 */
	public CreditEntrust updateIncomeBalance(CreditEntrust creditEntrust, BigDecimal amount) throws BusinessException;

	/**
	 * 
	 * @Title: updatePaymentBalance
	 * @Description: 修改备付金资金池
	 * @author liuwei
	 * @param creditEntrust
	 *            信托计划
	 * @param amount
	 *            分配的金额
	 * @return 信托计划
	 * @throws BusinessException
	 */
	public CreditEntrust updatePaymentBalance(CreditEntrust creditEntrust, BigDecimal amount) throws BusinessException;

	/**
	 * 
	 * @Title: reservePaymentBalance
	 * @Description: 恢复备付金资金池
	 * @author liuwei
	 * @param creditEntrust
	 *            信托计划
	 * @param amount
	 *            分配的金额
	 * @return 信托计划
	 * @throws BusinessException
	 */
	public CreditEntrust reservePaymentBalance(CreditEntrust creditEntrust, BigDecimal amount) throws BusinessException;

	/**
	 * 
	 * @Title: confirmInfo
	 * @Description: 确认转入
	 * @author liuwei
	 * @param creditEntrust
	 *            原有信托计划
	 * @param creditEntrustNew
	 *            转入信托计划
	 * @param amount
	 *            分配的金额
	 * @return 信托计划
	 * @throws BusinessException
	 */
	public CreditEntrust confirmInfo(CreditEntrust creditEntrust, CreditEntrust creditEntrustNew, BigDecimal amount,
			String caseLimitApplyId) throws BusinessException;

	/**
	 * 
	 * @Title: confirmInfo
	 * @Description: 确认转入
	 * @author liuwei
	 * @param creditEntrust
	 *            原信托计划
	 * @param creditEntrustNew
	 *            新信托计划
	 * @param caseLimitApply
	 *            额度申请信息
	 * @return 信托计划信息
	 * @throws BusinessException
	 */
	public CreditEntrust confirmInfo(CreditEntrust creditEntrust, CreditEntrust creditEntrustNew,
			CaseLimitApply caseLimitApply) throws BusinessException;

	/**
	 * 
	 * @Title: oneKeyFundMatching
	 * @Description: 一键资金匹配
	 * @author liuwei
	 * @param creditEntrustId
	 *            信托计划id
	 * @param state
	 *            额度状态
	 * @return 信托计划
	 * @throws Exception
	 */
	public CreditEntrust oneKeyFundMatching(String creditEntrustId, String state) throws Exception;

	/**
	 * 
	 * @Title: cumulativeDischarge
	 * @Description: 累计查询放款额(如果传了日期，则按照指定日期查询，如果没有传日期，则直接查询出该信托计划下面所有的)
	 * @author liuwei
	 * @param creditEntrustId
	 *            信托计划id
	 * @param appointedDate
	 *            扎帐时间
	 * @return 累计放款额
	 */
	public BigDecimal cumulativeDischarge(String creditEntrustId, Long appointedDate);

	/**
	 * 
	 * @Title: countTodayPaidAmount
	 * @Description: 计算今日还款数据(本金,利息,罚息,违约金)
	 * @author liuwei
	 * @param creditEntrustId
	 *            信托计划id
	 * @return Map(cumulativeRecoveryPrincipal,cumulativeRecoveryInterest,cumulativeRecoveryPenalty,cumulativeRecoveryLiqDamages)
	 * @throws BusinessException
	 */
	public Map<String, BigDecimal> countTodayPaidAmount(String creditEntrustId) throws BusinessException;

	/**
	 * 
	 * @Title: quotaApply
	 * @Description: 额度申请
	 * @author liuwei
	 * @param caseApplyId
	 *            案件申请id
	 * @param creditEntrustId
	 *            信托计划id
	 * @return CreditEntrust 信托计划
	 * @throws Exception
	 */
	public CreditEntrust quotaApply(String caseApplyId, String creditEntrustId) throws Exception;

	/**
	 * 
	 * @Title: quotaCancel
	 * @Description: 额度取消
	 * @author liuwei
	 * @param caseApplyId
	 *            案件申请id
	 * @param creditEntrustId
	 *            信托计划id
	 * @param caseLimitApplyId
	 *            额度申请id
	 * @return CreditEntrust 信托计划
	 * @throws Exception
	 */
	public CreditEntrust quotaCancel(String caseApplyId, String creditEntrustId, String caseLimitApplyId)
			throws Exception;

	/**
	 * 
	 * @Title: reserveFund
	 * @Description: 分配备付金
	 * @author liuwei
	 * @param caseApplyId
	 *            案件申请id
	 * @param creditEntrustId
	 *            信托计划id
	 * @param caseLimitApplyId
	 *            额度申请id
	 * @return CreditEntrust 信托计划
	 * @throws Exception
	 */
	public CreditEntrust reserveFund(String caseApplyId, String creditEntrustId, String caseLimitApplyId)
			throws Exception;

	/**
	 * 
	 * @Title: reserveFundCancel
	 * @Description: 取消备付金分配
	 * @author liuwei
	 * @param caseApplyId
	 *            案件申请id
	 * @param creditEntrustId
	 *            信托计划id
	 * @param caseLimitApplyId
	 *            额度申请id
	 * @return CreditEntrust 信托计划
	 * @throws Exception
	 */
	public CreditEntrust reserveFundCancel(String caseApplyId, String creditEntrustId, String caseLimitApplyId)
			throws Exception;

}
