package com.zdsoft.finance.capital.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.capital.entity.FeeItem;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: FeeItemService.java
 * @ClassName: FeeItemService
 * @Description: 费用项Service
 * @author liuwei
 * @date 2017年2月8日 上午10:37:19
 * @version V1.0
 */
public interface FeeItemService extends BaseService<FeeItem> {

	/**
	 * 
	 * @Title: findByAttribution 
	 * @Description: 根据归属查询费用项信息
	 * @author liuwei
	 * @param attribution 归属信息
	 * @return 费用项列表
	 */
	public List<FeeItem> findByAttribution(String attribution);

}
