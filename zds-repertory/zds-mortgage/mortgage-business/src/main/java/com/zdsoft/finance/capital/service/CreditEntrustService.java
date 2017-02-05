package com.zdsoft.finance.capital.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.capital.entity.CreditEntrust;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

/**
 * 信托计划service
 * 
 * @createTime:2017年1月10日
 * @author liuwei
 * @version 1.0
 */
public interface CreditEntrustService extends BaseService<CreditEntrust> {

	/**
	 * 保存信托计划信息及附属信息
	 * 
	 * @param creditEntrust
	 *            信托计划信息
	 * @return 信托计划信息
	 * @throws Exception
	 *             调用平台接口出现Exception
	 */
	public CreditEntrust saveCreditEntrustAndOthers(CreditEntrust creditEntrust) throws Exception;

	/**
	 * 修改信托计划信息
	 * 
	 * @param creditEntrust
	 *            信托计划信息
	 * @return 信托计划信息
	 * @throws BusinessException
	 * @throws Exception 
	 */
	public CreditEntrust updateCreditEntrust(CreditEntrust creditEntrust) throws Exception;

	/**
	 * sql分页查询
	 * 
	 * @param pageable
	 *            分页信息
	 * @param queryObj
	 *            查询条件
	 * @return Page<Map> 信息
	 */
	public Page<Map<String, Object>> reportSql(Pageable pageable, Map<String, Object> queryObj);

	/**
	 * sql分页查询额度分配
	 * 
	 * @param pageable
	 *            分页信息
	 * @param queryObj
	 *            查询条件
	 * @return Page<Map> 信息
	 */
	public Page<Map<String, Object>> reportPlanDistributionSql(Pageable pageable, Map<String, Object> queryObj);

	/**
	 * 根据查询条件查询信托计划列表
	 * 
	 * @param conditions
	 *            查询条件
	 * @return 信托计划列表
	 */
	public List<CreditEntrust> findByConditions(Map<String, Object> conditions);

	/**
	 * 修改收入信息
	 * 
	 * @param creditEntrust
	 *            信托计划
	 * @param amount
	 *            增加的收入
	 * @return 信托计划
	 * @throws BusinessException
	 */
	public CreditEntrust updateIncomeBalance(CreditEntrust creditEntrust, BigDecimal amount) throws BusinessException;

	/**
	 * 修改备付金资金池
	 * 
	 * @param creditEntrust
	 *            信托计划
	 * @param amount
	 *            分配的金额
	 * @return 信托计划
	 * @throws BusinessException
	 */
	public CreditEntrust updatePaymentBalance(CreditEntrust creditEntrust, BigDecimal amount) throws BusinessException;

	/**
	 * 恢复备付金资金池
	 * 
	 * @param creditEntrust
	 *            信托计划
	 * @param amount
	 *            分配的金额
	 * @return 信托计划
	 * @throws BusinessException
	 */
	public CreditEntrust reservePaymentBalance(CreditEntrust creditEntrust, BigDecimal amount) throws BusinessException;

	/**
	 * 确认转入
	 * 
	 * @param creditEntrust
	 *            原有信托计划
	 * @param creditEntrustNew
	 *            转入信托计划
	 * @param amount
	 *            分配的金额
	 * @return 信托计划
	 * @throws BusinessException
	 */
	public CreditEntrust confirmInfo(CreditEntrust creditEntrust, CreditEntrust creditEntrustNew, BigDecimal amount)
			throws BusinessException;

	/**
	 * 一键资金匹配
	 * 
	 * @param creditEntrustId
	 *            信托计划id
	 * @return 信托计划
	 * @throws BusinessException
	 */
	public CreditEntrust oneKeyFundMatching(String creditEntrustId, String state) throws BusinessException;

}
