package com.zdsoft.finance.capital.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.capital.entity.CreditEntrustFeeItem;

/**
 * 信托计划费用项Service
 * 
 * @createTime:2017年1月10日
 * @author liuwei
 * @version 1.0
 */
public interface CreditEntrustFeeItemService extends BaseService<CreditEntrustFeeItem> {

	/**
	 * 根据业务标识查询信托计划费用项
	 * 
	 * @param businessId
	 *            业务表示
	 * @return 信托计划费用列表
	 */
	public List<CreditEntrustFeeItem> findByBusinessId(String businessId);

}
