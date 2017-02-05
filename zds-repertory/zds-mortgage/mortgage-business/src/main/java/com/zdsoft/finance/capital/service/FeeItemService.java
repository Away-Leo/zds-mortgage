package com.zdsoft.finance.capital.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.capital.entity.FeeItem;

/**
 * 费用项Service
 * 
 * @createTime:2017年1月10日
 * @author liuwei
 * @version 1.0
 */
public interface FeeItemService extends BaseService<FeeItem> {

	/**
	 * 根据归属查询费用项信息
	 * 
	 * @param attribution
	 *            归属信息
	 * @return 费用项列表
	 */
	public List<FeeItem> findByAttribution(String attribution);

}
