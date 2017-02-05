package com.zdsoft.finance.busilog.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.busilog.entity.BusinessLog;
import com.zdsoft.finance.busilog.entity.LogType;
import com.zdsoft.finance.busilog.repository.BusinessLogRepository;
import com.zdsoft.finance.busilog.service.BusinessLogService;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.framework.core.common.util.DateHelper;

/**
 * 业务日志实现类
 * 
 * @author liuwei
 *
 */
@Service("businessLogService")
public class BusinessLogServiceImpl extends BaseServiceImpl<BusinessLog, CustomRepository<BusinessLog, String>>
		implements BusinessLogService {

	@Autowired
	BusinessLogRepository businessLogRepository;

	@Override
	@Transactional
	public BusinessLog recordBusinessLog(BusinessLog businessLog) {
		return businessLogRepository.saveEntity(businessLog);
	}

	@Override
	@Transactional
	public BusinessLog recordBusinessLog(String operatorNm, String moduleNm, String actionNm, String description) {
		// 保存业务日志信息
		BusinessLog businessLog = new BusinessLog();
		businessLog.setOperatorNm(operatorNm);
		businessLog.setOperatorTime(DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));
		businessLog.setModuleNm(moduleNm);
		businessLog.setActionNm(actionNm);
		businessLog.setDescription(description);
		businessLog.setLogType(LogType.AUTO.value);
		return businessLogRepository.saveEntity(businessLog);
	}

}
