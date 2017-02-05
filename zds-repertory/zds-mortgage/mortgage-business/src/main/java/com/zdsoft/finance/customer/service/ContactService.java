package com.zdsoft.finance.customer.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.customer.entity.Contact;

/**
 * 联系方式接口
 * @author zhangchao
 * @date 2016-12-22
 */
public interface ContactService extends BaseService<Contact>{
	
	/**
	 * 根据客户ID查询联系方式
	 * @param clientId 客户ID
	 * @return
	 */
	public List<Contact> findByClientId(String clientId);

}
