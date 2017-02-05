package com.zdsoft.finance.capital.service.impl;

import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.capital.entity.CreditEntrustOperationLog;
import com.zdsoft.finance.capital.service.CreditEntrustOperationLogService;
import com.zdsoft.finance.common.base.CustomRepository;

/**
 * 信托计划日志serviceImpl
 * 
 * @createTime:2017年1月12日
 * @author liuwei
 * @version 1.0
 */
@Service("creditEntrustOperationLogService")
public class CreditEntrustOperationLogServiceImpl
		extends BaseServiceImpl<CreditEntrustOperationLog, CustomRepository<CreditEntrustOperationLog, String>>
		implements CreditEntrustOperationLogService {

}
