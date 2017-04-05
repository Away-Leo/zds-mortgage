package com.zdsoft.finance.businesssetting.service.impl;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.businesssetting.entity.OrgBankAccount;
import com.zdsoft.finance.businesssetting.repository.OrgBankAccountRepository;
import com.zdsoft.finance.businesssetting.service.OrgBankAccountService;
import org.springframework.stereotype.Service;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: OrgBankAccountServiceImpl.java 
 * @ClassName: OrgBankAccountServiceImpl 
 * @Description:  机构银行账户接口实现
 * @author jincheng 
 * @date 2017年2月20日 下午9:05:41 
 * @version V1.0
 */
@Service("orgBankAccountService")
public class OrgBankAccountServiceImpl extends BaseServiceImpl<OrgBankAccount, OrgBankAccountRepository> implements OrgBankAccountService {

	@Override
	public OrgBankAccount findByOrgId(String orgId) {
		return this.customReposity.findByOrgId(orgId);
	}
	 
	
}
