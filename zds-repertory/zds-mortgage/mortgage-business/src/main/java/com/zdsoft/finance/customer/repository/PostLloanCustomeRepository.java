package com.zdsoft.finance.customer.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.customer.entity.PostLloanCustome;
/**
 * 贷后客户
 * @author zhangchao
 * 2016/12/21
 */
public interface PostLloanCustomeRepository extends CustomRepository<PostLloanCustome, String> {
	
	/**
	 * 根据客户名称查询
	 * @param clientNm 客户姓名
	 * @return
	 */
	public List<PostLloanCustome> findByClientNm(String clientNm);
	
	/**
	 * 根据证件号查询
	 * @param credentialNo 证件号
	 * @return
	 */
	public List<PostLloanCustome> findByCredentialNo(String credentialNo);

}
