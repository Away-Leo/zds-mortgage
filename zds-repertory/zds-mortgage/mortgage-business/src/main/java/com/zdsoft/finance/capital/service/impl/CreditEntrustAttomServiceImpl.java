package com.zdsoft.finance.capital.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.capital.entity.CreditEntrust;
import com.zdsoft.finance.capital.entity.CreditEntrustAttom;
import com.zdsoft.finance.capital.entity.CreditEntrustOperationLog;
import com.zdsoft.finance.capital.repository.CreditEntrustAttomRepository;
import com.zdsoft.finance.capital.service.CreditEntrustAttomService;
import com.zdsoft.finance.capital.service.CreditEntrustOperationLogService;
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
 * @Title: CreditEntrustAttomServiceImpl.java
 * @ClassName: CreditEntrustAttomServiceImpl
 * @Description: 信托计划转让ServiceImpl
 * @author liuwei
 * @date 2017年2月8日 上午10:38:30
 * @version V1.0
 */
@Service("creditEntrustAttomService")
public class CreditEntrustAttomServiceImpl
		extends BaseServiceImpl<CreditEntrustAttom, CustomRepository<CreditEntrustAttom, String>>
		implements CreditEntrustAttomService {

	@Autowired
	CreditEntrustAttomRepository creditEntrustAttomRepository;

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
	public CreditEntrustAttom saveOrUpdateEntrustAttom(CreditEntrustAttom creditEntrustAttom) throws Exception {
		if (ObjectHelper.isNotEmpty(creditEntrustAttom.getId())) {// id存在
			CreditEntrustAttom oldAttom = creditEntrustAttomRepository.findOne(creditEntrustAttom.getId());

			// 设置基本信息
			oldAttom.setUpdateBy(CED.getLoginUser().getEmpCd());
			oldAttom.setUpdateTime(new Date());
			oldAttom.setUpdateOrgCd(CED.getLoginUser().getOrgCd());

			// 保存跟踪信息
			oldAttom.setAcceptType(creditEntrustAttom.getAcceptType());
			oldAttom.setAcceptName(creditEntrustAttom.getAcceptName());
			oldAttom.setAttomState(creditEntrustAttom.getAttomState());
			oldAttom.setOrgCd(creditEntrustAttom.getOrgCd());
			oldAttom.setContactName(creditEntrustAttom.getContactName());
			oldAttom.setCardNo(creditEntrustAttom.getCardNo());
			oldAttom.setAddress(creditEntrustAttom.getAddress());
			oldAttom.setMobile(creditEntrustAttom.getMobile());
			oldAttom.setPhone(creditEntrustAttom.getPhone());

			// 合同要素信息
			oldAttom.setAcceptAmount(creditEntrustAttom.getAcceptAmount());
			oldAttom.setContractProfitRate(creditEntrustAttom.getContractProfitRate());
			oldAttom.setAttomEffect(creditEntrustAttom.getAttomEffect());
			oldAttom.setContractNo(creditEntrustAttom.getContractNo());
			oldAttom.setAttomContractNo(creditEntrustAttom.getAttomContractNo());
			oldAttom.setAttomEndDate(creditEntrustAttom.getAttomEndDate());

			// 其他信息
			oldAttom.setAssigneeAccBank(creditEntrustAttom.getAssigneeAccBank());
			oldAttom.setAssigneeAccName(creditEntrustAttom.getAssigneeAccName());
			oldAttom.setAssigneeAccNo(creditEntrustAttom.getAssigneeAccNo());
			oldAttom.setPayoutPeriod(creditEntrustAttom.getPayoutPeriod());
			oldAttom.setTermDay(creditEntrustAttom.getTermDay());
			oldAttom.setRemark(creditEntrustAttom.getRemark());

			creditEntrustAttom = creditEntrustAttomRepository.updateEntity(oldAttom);

		} else { // id不存在

			// 保存基本信息
			creditEntrustAttom.setCreateBy(CED.getLoginUser().getEmpCd());
			creditEntrustAttom.setCreateTime(new Date());
			creditEntrustAttom.setCreateOrgCd(CED.getLoginUser().getOrgCd());

			creditEntrustAttom.setStatus(StatusNm.SUBMIT.value);

			// 提交人/提交时间
			creditEntrustAttom.setCompleteEmpCd(CED.getLoginUser().getEmpCd());
			creditEntrustAttom.setCompleteEmpName(CED.getLoginUser().getEmpNm());
			creditEntrustAttom
					.setCompleteDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));

			// 保存贷方资金跟踪信息
			creditEntrustAttom = creditEntrustAttomRepository.saveEntity(creditEntrustAttom);

			CreditEntrustOperationLog operationLog = new CreditEntrustOperationLog();
			operationLog.setOperationType(OperationTypeNm.SUBMIT.value);
			operationLog.setOperationContent("信托计划转让");
			operationLog.setOperationEmpCd(CED.getLoginUser().getEmpCd());
			operationLog.setOperationEmpName(CED.getLoginUser().getEmpNm());
			operationLog.setOperationDate(
					DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));
			operationLog
					.setActualDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));
			// operationLog.setStatus();
			operationLog.setBusinessId(creditEntrustAttom.getId());

			creditEntrustOperationLogService.saveEntity(operationLog);

		}

		// 重新填充列表信息
		if (ObjectHelper.isNotEmpty(creditEntrustAttom.getCreditEntrust())) {
			CreditEntrust creditEntrust = creditEntrustToolService.listFill(creditEntrustAttom.getCreditEntrust());
			creditEntrust = creditEntrustService.updateEntity(creditEntrust);
		}

		return creditEntrustAttom;
	}

	@Override
	public List<CreditEntrustAttom> findByTempUuid(String tempUuid) {
		return creditEntrustAttomRepository.findByTempUuid(tempUuid);
	}

	@Override
	public List<CreditEntrustAttom> findByConditions(Map<String, Object> conditions) {
		return creditEntrustAttomRepository.findByConditions(conditions);
	}

}
