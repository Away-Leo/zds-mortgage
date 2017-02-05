package com.zdsoft.finance.capital.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.capital.entity.LoanCapital;

/**
 * 专户贷方资金跟踪Service
 * 
 * @createTime:2017年1月10日
 * @author liuwei
 * @version 1.0
 */
public interface LoanCapitalService extends BaseService<LoanCapital> {

	/**
	 * 保存或修改专户贷方资金跟踪
	 * 
	 * @param loanCapital
	 *            专户贷方资金跟踪信息
	 * @return 专户贷方资金跟踪信息
	 * @throws Exception
	 *             调用平台接口出现Exception
	 */
	public LoanCapital saveOrUpdateLoanCapital(LoanCapital loanCapital) throws Exception;

	/**
	 * 通过临时id查询资金跟踪
	 * 
	 * @param tempUuid
	 *            临时id
	 * @return 资金跟踪集合
	 */
	public List<LoanCapital> findByTempUuid(String tempUuid);

	/**
	 * 通过查询条件专户贷方资金跟踪
	 * 
	 * @param conditions
	 *            查询条件
	 * @return 本金投入列表
	 */
	public List<LoanCapital> findByConditions(Map<String, Object> conditions);
}
