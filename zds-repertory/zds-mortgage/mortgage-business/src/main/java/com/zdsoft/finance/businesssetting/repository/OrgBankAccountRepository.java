package com.zdsoft.finance.businesssetting.repository;

import com.zdsoft.finance.businesssetting.entity.OrgBankAccount;
import com.zdsoft.finance.common.base.CustomRepository;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: OrgBankAccountRepository.java 
 * @ClassName: OrgBankAccountRepository 
 * @Description: 机构银行账户Repository 
 * @author jincheng 
 * @date 2017年2月20日 下午9:03:18 
 * @version V1.0
 */
public interface OrgBankAccountRepository extends CustomRepository<OrgBankAccount, String>{

	/**
	 * @Title: findByOrgId 
	 * @Description: 根据机构id,获取机构银行账户
	 * @author jincheng 
	 * @param orgId 机构ID
	 * @return
	 */
	public OrgBankAccount findByOrgId(String orgId);
	
}
