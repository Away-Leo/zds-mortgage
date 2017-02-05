package com.zdsoft.finance.customer.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.customer.entity.Contact;
/**
 * 联系方式
 * @author zhangchao
 * 2016/12/21
 */
public interface ContactRepository extends CustomRepository<Contact, String> {
	
	/**
	 * 根据客户ID查询联系方式
	 * @param clientId 客户ID
	 * @return
	 */
	public List<Contact> findByClientId(String clientId);

}
