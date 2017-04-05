package com.zdsoft.finance.capital.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.capital.entity.CreditEntrustTieOff;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CreditEntrustTieOffService.java
 * @ClassName: CreditEntrustTieOffService
 * @Description: 信托计划扎帐统计Service
 * @author liuwei
 * @date 2017年2月16日 下午8:30:03
 * @version V1.0
 */
public interface CreditEntrustTieOffService extends BaseService<CreditEntrustTieOff> {

	/**
	 * 
	 * @Title: findByCreditEntrustIdAndOrderGroup
	 * @Description: 根据信托计划id排序并分组查询扎帐信息集合
	 * @author liuwei
	 * @param creditEntrustId
	 *            信托计划id
	 * @return 信托计划扎帐信息集合
	 */
	public List<CreditEntrustTieOff> findByCreditEntrustIdAndOrderGroup(String creditEntrustId);

}
