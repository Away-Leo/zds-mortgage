package com.zdsoft.finance.capital.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.capital.entity.CreditEntrustDebit;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CreditEntrustDebitService.java
 * @ClassName: CreditEntrustDebitService
 * @Description: 信托计划借方资金Service
 * @author liuwei
 * @date 2017年2月8日 上午10:36:33
 * @version V1.0
 */
public interface CreditEntrustDebitService extends BaseService<CreditEntrustDebit> {

	/**
	 * 
	 * @Title: saveOrUpdateEntrustDebit
	 * @Description: 新增或保存借方资金
	 * @author liuwei
	 * @param creditEntrustDebit
	 *            借方资金
	 * @return 借方资金
	 * @throws Exception
	 */
	public CreditEntrustDebit saveOrUpdateEntrustDebit(CreditEntrustDebit creditEntrustDebit) throws Exception;

	/**
	 * 
	 * @Title: findByTempUuid
	 * @Description: 通过临时id查询借方资金列表
	 * @author liuwei
	 * @param tempUuid
	 *            临时id
	 * @return 借方资金列表
	 */
	public List<CreditEntrustDebit> findByTempUuid(String tempUuid);

	/**
	 * 
	 * @Title: findByConditions 
	 * @Description: 多条件查询借方资金列表
	 * @author liuwei
	 * @param conditions 查询条件
	 * @return 借方资金列表
	 */
	public List<CreditEntrustDebit> findByConditions(Map<String, Object> conditions);

}
