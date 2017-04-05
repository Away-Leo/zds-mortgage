package com.zdsoft.finance.cooperator.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.cooperator.entity.BrokerageAccount;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: BrokerageAccountService.java
 * @ClassName: BrokerageAccountService
 * @Description: 返佣账户Service
 * @author liuwei
 * @date 2017年2月25日 下午3:11:52
 * @version V1.0
 */
public interface BrokerageAccountService extends BaseService<BrokerageAccount> {

	/**
	 * 
	 * @Title: saveOrUpdateBrokerageAccount
	 * @Description: 新增或保存返佣信息
	 * @author liuwei
	 * @param brokerageAccount
	 *            返佣信息
	 * @return 返佣信息
	 * @throws Exception
	 */
	public BrokerageAccount saveOrUpdateBrokerageAccount(BrokerageAccount brokerageAccount) throws Exception;

}
