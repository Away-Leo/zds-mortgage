package com.zdsoft.finance.capital.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.capital.entity.LoanCapital;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: LoanCapitalService.java
 * @ClassName: LoanCapitalService
 * @Description: 专户贷方资金跟踪Service
 * @author liuwei
 * @date 2017年2月8日 上午10:37:32
 * @version V1.0
 */
public interface LoanCapitalService extends BaseService<LoanCapital> {

	/**
	 * 
	 * @Title: saveOrUpdateLoanCapital
	 * @Description: 保存或修改专户贷方资金跟踪
	 * @author liuwei
	 * @param loanCapital
	 *            专户贷方资金跟踪信息
	 * @return 专户贷方资金跟踪信息
	 * @throws Exception
	 *             调用平台接口出现Exception
	 */
	public LoanCapital saveOrUpdateLoanCapital(LoanCapital loanCapital) throws Exception;

	/**
	 * 
	 * @Title: findByTempUuid
	 * @Description: 通过临时id查询资金跟踪
	 * @author liuwei
	 * @param tempUuid
	 *            临时id
	 * @return 资金跟踪集合
	 */
	public List<LoanCapital> findByTempUuid(String tempUuid);

	/**
	 * 
	 * @Title: findByConditions
	 * @Description: 通过查询条件专户贷方资金跟踪
	 * @author liuwei
	 * @param conditions
	 *            查询条件
	 * @return 本金投入列表
	 */
	public List<LoanCapital> findByConditions(Map<String, Object> conditions);
}
