package com.zdsoft.finance.capital.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.capital.entity.CreditEntrustOperationLog;
import com.zdsoft.finance.capital.entity.CreditEntrustRedemPrinci;
import com.zdsoft.finance.capital.repository.CreditEntrustRedemPrinciRepository;
import com.zdsoft.finance.capital.service.CreditEntrustOperationLogService;
import com.zdsoft.finance.capital.service.CreditEntrustRedemPrinciService;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.spi.common.dto.OperationTypeNm;
import com.zdsoft.finance.spi.common.dto.StatusNm;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;

@Service("creditEntrustRedemPrinciService")
public class CreditEntrustRedemPrinciServiceImpl
		extends BaseServiceImpl<CreditEntrustRedemPrinci, CustomRepository<CreditEntrustRedemPrinci, String>>
		implements CreditEntrustRedemPrinciService {

	@Autowired
	CreditEntrustRedemPrinciRepository creditEntrustRedemPrinciRepository;

	@Autowired
	CreditEntrustOperationLogService creditEntrustOperationLogService;

	@Autowired
	CED CED;

	@Override
	@Transactional
	public CreditEntrustRedemPrinci saveOrUpdateEntrustRedemPrinci(CreditEntrustRedemPrinci creditEntrustRedemPrinci)
			throws Exception {
		if (ObjectHelper.isNotEmpty(creditEntrustRedemPrinci.getId())) {// id存在
			CreditEntrustRedemPrinci oldRedemPrinci = creditEntrustRedemPrinciRepository
					.findOne(creditEntrustRedemPrinci.getId());

			// 设置基本信息
			oldRedemPrinci.setUpdateBy(CED.getLoginUser().getEmpCd());
			oldRedemPrinci.setUpdateTime(new Date());
			oldRedemPrinci.setUpdateOrgCd(CED.getLoginUser().getOrgCd());

			// 保存特有信息
			oldRedemPrinci.setContribution(creditEntrustRedemPrinci.getContribution());
			oldRedemPrinci.setContributionType(creditEntrustRedemPrinci.getContributionType());
			oldRedemPrinci.setRedemptionAmount(creditEntrustRedemPrinci.getRedemptionAmount());
			oldRedemPrinci.setRemark(creditEntrustRedemPrinci.getRemark());

			creditEntrustRedemPrinci = creditEntrustRedemPrinciRepository.updateEntity(oldRedemPrinci);

		} else { // id不存在

			// 保存基本信息
			creditEntrustRedemPrinci.setCreateBy(CED.getLoginUser().getEmpCd());
			creditEntrustRedemPrinci.setCreateTime(new Date());
			creditEntrustRedemPrinci.setCreateOrgCd(CED.getLoginUser().getOrgCd());

			creditEntrustRedemPrinci.setStatus(StatusNm.SUBMIT.value);

			// 提交人/提交时间
			creditEntrustRedemPrinci.setCompleteEmpCd(CED.getLoginUser().getEmpCd());
			creditEntrustRedemPrinci.setCompleteEmpName(CED.getLoginUser().getEmpNm());
			creditEntrustRedemPrinci
					.setCompleteDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_SHORT_SIMPLE_FORMAT_WITHMINUTE));

			// 保存贷方资金跟踪信息
			creditEntrustRedemPrinci = creditEntrustRedemPrinciRepository.saveEntity(creditEntrustRedemPrinci);

			CreditEntrustOperationLog operationLog = new CreditEntrustOperationLog();
			operationLog.setOperationType(OperationTypeNm.SUBMIT.value);
			operationLog.setOperationContent("信托计划本金赎回");
			operationLog.setOperationEmpCd(CED.getLoginUser().getEmpCd());
			operationLog.setOperationEmpName(CED.getLoginUser().getEmpNm());
			operationLog.setOperationDate(
					DateHelper.dateToLong(new Date(), DateHelper.DATE_SHORT_SIMPLE_FORMAT_WITHMINUTE));
			operationLog
					.setActualDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_SHORT_SIMPLE_FORMAT_WITHMINUTE));
			// operationLog.setStatus();
			operationLog.setBusinessId(creditEntrustRedemPrinci.getId());
			creditEntrustOperationLogService.saveEntity(operationLog);
		}

		return creditEntrustRedemPrinci;
	}

	@Override
	public List<CreditEntrustRedemPrinci> findByTempUuid(String tempUuid) {
		return creditEntrustRedemPrinciRepository.findByTempUuid(tempUuid);
	}

	@Override
	public List<CreditEntrustRedemPrinci> findByConditions(Map<String, Object> conditions) {
		return creditEntrustRedemPrinciRepository.findByConditions(conditions);
	}

}
