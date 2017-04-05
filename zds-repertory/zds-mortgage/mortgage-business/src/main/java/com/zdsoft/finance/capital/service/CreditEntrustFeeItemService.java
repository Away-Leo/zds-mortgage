package com.zdsoft.finance.capital.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.capital.entity.CreditEntrustFeeItem;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CreditEntrustFeeItemService.java
 * @ClassName: CreditEntrustFeeItemService
 * @Description: 信托计划费用项Service
 * @author liuwei
 * @date 2017年2月8日 上午10:36:42
 * @version V1.0
 */
public interface CreditEntrustFeeItemService extends BaseService<CreditEntrustFeeItem> {

	/**
	 * 
	 * @Title: findByBusinessId
	 * @Description: 根据业务标识查询信托计划费用项
	 * @author liuwei
	 * @param businessId
	 *            业务标识
	 * @return 信托计划费用列表
	 */
	public List<CreditEntrustFeeItem> findByBusinessId(String businessId);

}
