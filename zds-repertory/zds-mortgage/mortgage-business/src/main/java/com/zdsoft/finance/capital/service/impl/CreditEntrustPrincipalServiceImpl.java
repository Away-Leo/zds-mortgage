package com.zdsoft.finance.capital.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.capital.entity.CreditEntrust;
import com.zdsoft.finance.capital.entity.CreditEntrustOperationLog;
import com.zdsoft.finance.capital.entity.CreditEntrustPrincipal;
import com.zdsoft.finance.capital.repository.CreditEntrustPrincipalRepository;
import com.zdsoft.finance.capital.service.CreditEntrustOperationLogService;
import com.zdsoft.finance.capital.service.CreditEntrustPrincipalService;
import com.zdsoft.finance.capital.service.CreditEntrustService;
import com.zdsoft.finance.capital.service.CreditEntrustToolService;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.spi.common.dto.OperationTypeNm;
import com.zdsoft.finance.spi.common.dto.StatusNm;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CreditEntrustPrincipalServiceImpl.java
 * @ClassName: CreditEntrustPrincipalServiceImpl
 * @Description: 信托计划本金投入serviceImpl
 * @author liuwei
 * @date 2017年2月8日 上午10:39:41
 * @version V1.0
 */
@Service("creditEntrustPrincipalService")
public class CreditEntrustPrincipalServiceImpl
		extends BaseServiceImpl<CreditEntrustPrincipal, CustomRepository<CreditEntrustPrincipal, String>>
		implements CreditEntrustPrincipalService {

	@Autowired
	CED CED;

	@Autowired
	CreditEntrustPrincipalRepository creditEntrustPrincipalRepository;

	@Autowired
	CreditEntrustOperationLogService creditEntrustOperationLogService;

	@Autowired
	CreditEntrustService creditEntrustService;

	@Autowired
	CreditEntrustToolService creditEntrustToolService;

	@Override
	@Transactional
	public CreditEntrustPrincipal saveOrUpdateEntrustPrincipal(CreditEntrustPrincipal creditEntrsutPrincipal)
			throws Exception {

		BigDecimal totalAmount = creditEntrsutPrincipal.getPrincipalAmount();

		if (ObjectHelper.isNotEmpty(creditEntrsutPrincipal.getId())) {// id存在
			CreditEntrustPrincipal oldPrincipal = creditEntrustPrincipalRepository
					.findOne(creditEntrsutPrincipal.getId());

			totalAmount = oldPrincipal.getPrincipalAmount();

			// 设置基本信息
			oldPrincipal.setUpdateBy(CED.getLoginUser().getEmpCd());
			oldPrincipal.setUpdateTime(new Date());
			oldPrincipal.setUpdateOrgCd(CED.getLoginUser().getOrgCd());

			// 保存特有信息
			oldPrincipal.setContribution(creditEntrsutPrincipal.getContribution());
			oldPrincipal.setContributionType(creditEntrsutPrincipal.getContributionType());
			oldPrincipal.setPrincipalAmount(creditEntrsutPrincipal.getPrincipalAmount());
			oldPrincipal.setProfitRate(creditEntrsutPrincipal.getProfitRate());
			oldPrincipal.setAddDate(creditEntrsutPrincipal.getAddDate());
			oldPrincipal.setExpectDate(creditEntrsutPrincipal.getExpectDate());
			oldPrincipal.setActualDate(creditEntrsutPrincipal.getActualDate());
			oldPrincipal.setPayoutPeriod(creditEntrsutPrincipal.getPayoutPeriod());
			oldPrincipal.setTermDay(creditEntrsutPrincipal.getTermDay());
			oldPrincipal.setMaturityDate(creditEntrsutPrincipal.getMaturityDate());
			oldPrincipal.setRemark(creditEntrsutPrincipal.getRemark());

			if (ObjectHelper.isNotEmpty(creditEntrsutPrincipal.getStatus())) {
				oldPrincipal.setStatus(creditEntrsutPrincipal.getStatus());

				CreditEntrustOperationLog operationLog = new CreditEntrustOperationLog();
				operationLog.setOperationType(OperationTypeNm.ARRIVAL.value);
				operationLog.setOperationContent("信托计划本金赎回");
				operationLog.setOperationEmpCd(CED.getLoginUser().getEmpCd());
				operationLog.setOperationEmpName(CED.getLoginUser().getEmpNm());
				operationLog.setOperationDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));
				operationLog.setActualDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));
				// operationLog.setStatus();
				operationLog.setBusinessId(oldPrincipal.getId());
				creditEntrustOperationLogService.saveEntity(operationLog);
			}
			creditEntrsutPrincipal = creditEntrustPrincipalRepository.updateEntity(oldPrincipal);

		} else { // id不存在

			// 保存基本信息
			creditEntrsutPrincipal.setCreateBy(CED.getLoginUser().getEmpCd());
			creditEntrsutPrincipal.setCreateTime(new Date());
			creditEntrsutPrincipal.setCreateOrgCd(CED.getLoginUser().getOrgCd());

			creditEntrsutPrincipal.setStatus(StatusNm.SUBMIT.value);

			// 提交人/提交时间
			creditEntrsutPrincipal.setCompleteEmpCd(CED.getLoginUser().getEmpCd());
			creditEntrsutPrincipal.setCompleteEmpName(CED.getLoginUser().getEmpNm());
			creditEntrsutPrincipal
					.setCompleteDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));

			// 保存贷方资金跟踪信息
			creditEntrsutPrincipal = creditEntrustPrincipalRepository.saveEntity(creditEntrsutPrincipal);

			CreditEntrustOperationLog operationLog = new CreditEntrustOperationLog();
			operationLog.setOperationType(OperationTypeNm.SUBMIT.value);
			operationLog.setOperationContent("信托计划本金投入");
			operationLog.setOperationEmpCd(CED.getLoginUser().getEmpCd());
			operationLog.setOperationEmpName(CED.getLoginUser().getEmpNm());
			operationLog.setOperationDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));
			operationLog.setActualDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));
			// operationLog.setStatus();
			operationLog.setBusinessId(creditEntrsutPrincipal.getId());
			creditEntrustOperationLogService.saveEntity(operationLog);
			totalAmount = BigDecimal.ZERO;
		}

		// 修改收入信息 并 重新填充列表统计信息
		if (ObjectHelper.isNotEmpty(creditEntrsutPrincipal.getCreditEntrust())) {
			CreditEntrust creditEntrust = creditEntrsutPrincipal.getCreditEntrust();
			BigDecimal incomeBalance = creditEntrust.getIncomeBalance();
			incomeBalance = (incomeBalance == null ? BigDecimal.ZERO : incomeBalance)
					.subtract(totalAmount == null ? BigDecimal.ZERO : totalAmount)
					.add(creditEntrsutPrincipal.getPrincipalAmount() == null ? BigDecimal.ZERO
							: creditEntrsutPrincipal.getPrincipalAmount());

			creditEntrust.setIncomeBalance(incomeBalance);
			creditEntrust = creditEntrustService.updateEntity(creditEntrust);

			creditEntrust = creditEntrustToolService.listFill(creditEntrust);
			creditEntrustService.updateEntity(creditEntrust);
		}

		return creditEntrsutPrincipal;
	}

	@Override
	public List<CreditEntrustPrincipal> findByTempUuid(String tempUuid) {
		return creditEntrustPrincipalRepository.findByTempUuid(tempUuid);
	}

	@Override
	public List<CreditEntrustPrincipal> findByConditions(Map<String, Object> conditions) {
		return creditEntrustPrincipalRepository.findByConditions(conditions);
	}

}
