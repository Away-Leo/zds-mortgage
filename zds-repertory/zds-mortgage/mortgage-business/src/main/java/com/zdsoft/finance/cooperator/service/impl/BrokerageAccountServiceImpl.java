package com.zdsoft.finance.cooperator.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.cooperator.entity.BrokerageAccount;
import com.zdsoft.finance.cooperator.entity.CooperatorTerminal;
import com.zdsoft.finance.cooperator.repository.BrokerageAccountRepository;
import com.zdsoft.finance.cooperator.service.BrokerageAccountService;
import com.zdsoft.finance.cooperator.service.CooperatorTerminalService;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: BrokerageAccountServiceImpl.java
 * @ClassName: BrokerageAccountServiceImpl
 * @Description: 返佣账户ServiceImpl
 * @author liuwei
 * @date 2017年3月2日 上午10:30:24
 * @version V1.0
 */
@Service("brokerageAccountService")
public class BrokerageAccountServiceImpl
		extends BaseServiceImpl<BrokerageAccount, CustomRepository<BrokerageAccount, String>>
		implements BrokerageAccountService {

	@Autowired
	BrokerageAccountRepository brokerageAccountRepository;

	@Autowired
	CooperatorTerminalService cooperatorTerminalService;

	@Autowired
	CED CED;

	@Override
	@Transactional
	public BrokerageAccount saveOrUpdateBrokerageAccount(BrokerageAccount brokerageAccount) throws Exception {

		// 查询终端信息
		CooperatorTerminal ter = cooperatorTerminalService.findOne(brokerageAccount.getTerminalId());

		if (ObjectHelper.isEmpty(brokerageAccount.getId())) {
			// 设置基础信息
			brokerageAccount.setCreateBy(CED.getLoginUser().getEmpCd());
			brokerageAccount.setCreateOrgCd(CED.getLoginUser().getOrgCd());
			brokerageAccount.setCreateTime(new Date());

			// 设置终端信息
			brokerageAccount.setCooperatorTerminal(ter);
			brokerageAccount = brokerageAccountRepository.saveEntity(brokerageAccount);
		} else {

			// 查询原有联系信息
			BrokerageAccount oldAccount = brokerageAccountRepository.findOne(brokerageAccount.getId());

			// 修改值
			oldAccount.setAccountDetail(brokerageAccount.getAccountDetail());
			oldAccount.setBankName(brokerageAccount.getBankName());
			oldAccount.setAccountName(brokerageAccount.getAccountName());
			oldAccount.setBankAccount(brokerageAccount.getBankAccount());
			oldAccount.setBrokeragePersonName(brokerageAccount.getBrokeragePersonName());
			oldAccount.setBrokeragePersonPhone(brokerageAccount.getBrokeragePersonPhone());
			oldAccount.setApproveType(brokerageAccount.getApproveType());
			oldAccount.setAliveAccount(brokerageAccount.getAliveAccount());
			oldAccount.setCooperatorTerminal(ter);

			// 设置修改信息
			oldAccount.setUpdateBy(CED.getLoginUser().getEmpCd());
			oldAccount.setUpdateOrgCd(CED.getLoginUser().getOrgCd());
			oldAccount.setUpdateTime(new Date());

			brokerageAccount = brokerageAccountRepository.updateEntity(oldAccount);
		}

		return brokerageAccount;
	}

}