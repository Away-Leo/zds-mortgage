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
import com.zdsoft.finance.capital.entity.SpareCapital;
import com.zdsoft.finance.capital.repository.SpareCapitalRepository;
import com.zdsoft.finance.capital.service.CreditEntrustOperationLogService;
import com.zdsoft.finance.capital.service.CreditEntrustService;
import com.zdsoft.finance.capital.service.CreditEntrustToolService;
import com.zdsoft.finance.capital.service.SpareCapitalService;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.spi.common.dto.OperationTypeNm;
import com.zdsoft.finance.spi.common.dto.StatusNm;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: SpareCapitalServiceImpl.java
 * @ClassName: SpareCapitalServiceImpl
 * @Description: 备付资金跟踪ServiceImpl
 * @author liuwei
 * @date 2017年2月8日 上午10:40:56
 * @version V1.0
 */
@Service("spareCapitalService")
public class SpareCapitalServiceImpl extends BaseServiceImpl<SpareCapital, CustomRepository<SpareCapital, String>>
		implements SpareCapitalService {

	@Autowired
	SpareCapitalRepository spareCapitalRepository;

	@Autowired
	CreditEntrustOperationLogService creditEntrustOperationLogService;

	@Autowired
	CreditEntrustService creditEntrustService;

	@Autowired
	CreditEntrustToolService creditEntrustToolService;

	@Autowired
	CED CED;

	@Override
	@Transactional
	public SpareCapital saveOrUpdateSpareCapital(SpareCapital spareCapital) throws Exception {

		BigDecimal actualAmount = spareCapital.getDistributionAmount();

		if (ObjectHelper.isNotEmpty(spareCapital.getId())) {// id存在
			SpareCapital oldSpareCapital = spareCapitalRepository.findOne(spareCapital.getId());
			actualAmount = oldSpareCapital.getDistributionAmount();

			// 缓存未分配金额以及实际到账金额
			BigDecimal distributionAmount = oldSpareCapital.getDistributionAmount() == null ? BigDecimal.ZERO
					: oldSpareCapital.getDistributionAmount();
			BigDecimal spareActualAmount = oldSpareCapital.getActualAmount() == null ? BigDecimal.ZERO
					: oldSpareCapital.getActualAmount();

			// 设置基本信息
			oldSpareCapital.setUpdateBy(CED.getLoginUser().getEmpCd());
			oldSpareCapital.setUpdateTime(new Date());
			oldSpareCapital.setUpdateOrgCd(CED.getLoginUser().getOrgCd());

			// 保存跟踪信息
			oldSpareCapital.setOperationType(spareCapital.getOperationType());
			oldSpareCapital.setApplyAmount(spareCapital.getApplyAmount());
			oldSpareCapital.setUseDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));
			oldSpareCapital.setApplyRemark(spareCapital.getApplyRemark());
			oldSpareCapital.setActualAmount(spareCapital.getActualAmount());
			oldSpareCapital.setActualArrivalDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));
			oldSpareCapital.setActualRemark(spareCapital.getActualRemark());

			// 计算未分配到账金额
			BigDecimal lastAmount = spareActualAmount.subtract(distributionAmount);

			// 备付金实际余额 = 备付金到账金额 - 已分配备付金金额
			oldSpareCapital.setDistributionAmount(spareCapital.getActualAmount().subtract(lastAmount));
			// 判断是否添加到账日志
			if (ObjectHelper.isNotEmpty(spareCapital.getStatus())) {
				oldSpareCapital.setStatus(spareCapital.getStatus());

				CreditEntrustOperationLog operationLog = new CreditEntrustOperationLog();
				operationLog.setOperationType(OperationTypeNm.ARRIVAL.value);
				operationLog.setOperationContent("信托计划备付金跟踪确认到账");
				operationLog.setOperationEmpCd(CED.getLoginUser().getEmpCd());
				operationLog.setOperationEmpName(CED.getLoginUser().getEmpNm());
				operationLog.setOperationDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));
				operationLog.setActualDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));
				// operationLog.setStatus();
				operationLog.setBusinessId(oldSpareCapital.getId());
				creditEntrustOperationLogService.saveEntity(operationLog);
			}

			spareCapital = spareCapitalRepository.updateEntity(oldSpareCapital);

		} else { // id不存在

			// 保存基本信息
			spareCapital.setCreateBy(CED.getLoginUser().getEmpCd());
			spareCapital.setCreateTime(new Date());
			spareCapital.setCreateOrgCd(CED.getLoginUser().getOrgCd());

			spareCapital.setStatus(StatusNm.SUBMIT.value);

			// 提交人/提交时间
			spareCapital.setCompleteEmpCd(CED.getLoginUser().getEmpCd());
			spareCapital.setCompleteEmpName(CED.getLoginUser().getEmpNm());
			spareCapital.setCompleteDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));

			// 计算未分配到账金额
			spareCapital.setDistributionAmount(spareCapital.getActualAmount());

			// 保存贷方资金跟踪信息
			spareCapital = spareCapitalRepository.saveEntity(spareCapital);

			CreditEntrustOperationLog operationLog = new CreditEntrustOperationLog();
			operationLog.setOperationType(OperationTypeNm.SUBMIT.value);
			operationLog.setOperationContent("备付资金跟踪");
			operationLog.setOperationEmpCd(CED.getLoginUser().getEmpCd());
			operationLog.setOperationEmpName(CED.getLoginUser().getEmpNm());
			operationLog.setOperationDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));
			operationLog.setActualDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));
			// operationLog.setStatus();
			operationLog.setBusinessId(spareCapital.getId());
			creditEntrustOperationLogService.saveEntity(operationLog);

			actualAmount = BigDecimal.ZERO;
		}

		// 修改信托计划的备付金资金池
		if (ObjectHelper.isNotEmpty(spareCapital.getCreditEntrust())) {

			// 获得原有备付金资金池金额
			BigDecimal paymentBalance = spareCapital.getCreditEntrust().getPaymentBalance();
			CreditEntrust creditEntrust = spareCapital.getCreditEntrust();

			// 新的备付金资金池 = 原有备付金资金池 - 原备付金实际余额 + 现备付金实际余额
			paymentBalance = (paymentBalance == null ? BigDecimal.ZERO : paymentBalance)
					.subtract(actualAmount == null ? BigDecimal.ZERO : actualAmount)
					.add(spareCapital.getDistributionAmount() == null ? BigDecimal.ZERO
							: spareCapital.getDistributionAmount());
			creditEntrust.setPaymentBalance(paymentBalance);
			creditEntrust = creditEntrustService.updateEntity(creditEntrust);

			// 重新填充列表统计数据
			creditEntrust = creditEntrustToolService.listFill(creditEntrust);
			creditEntrustService.updateEntity(creditEntrust);
		}

		return spareCapital;
	}

	@Override
	public List<SpareCapital> findByTempUuid(String tempUuid) {
		return spareCapitalRepository.findByTempUuid(tempUuid);
	}

	@Override
	public List<SpareCapital> findByConditions(Map<String, Object> conditions) {
		return spareCapitalRepository.findByConditions(conditions);
	}

	@Override
	public List<SpareCapital> findByCreditEntrustIdAndOrderByCreteTime(String creditEntrustId) {
		return spareCapitalRepository.findByCreditEntrustIdAndOrderByCreteTime(creditEntrustId);
	}

	@Override
	@Transactional
	public SpareCapital saveOrUpdateSpareCapitalAndMatch(SpareCapital spareCapital) throws Exception {

		// 保存备付金跟踪信息
		spareCapital = this.saveOrUpdateSpareCapital(spareCapital);

		// 判断信托计划是否存在,并且已经开启自动分配
		if (ObjectHelper.isNotEmpty(spareCapital.getCreditEntrust())
				&& spareCapital.getCreditEntrust().getIsAutoMatch()) {
			creditEntrustService.oneKeyFundMatching(spareCapital.getCreditEntrust().getId(), "YWDM0051057");
		}
		return spareCapital;
	}

}
