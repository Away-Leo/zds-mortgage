package com.zdsoft.finance.capital.service.impl;

import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.capital.entity.CreditEntrustOperationLog;
import com.zdsoft.finance.capital.service.CreditEntrustOperationLogService;
import com.zdsoft.finance.common.base.CustomRepository;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CreditEntrustOperationLogServiceImpl.java
 * @ClassName: CreditEntrustOperationLogServiceImpl
 * @Description: 信托计划日志serviceImpl
 * @author liuwei
 * @date 2017年2月8日 上午10:39:24
 * @version V1.0
 */
@Service("creditEntrustOperationLogService")
public class CreditEntrustOperationLogServiceImpl
		extends BaseServiceImpl<CreditEntrustOperationLog, CustomRepository<CreditEntrustOperationLog, String>>
		implements CreditEntrustOperationLogService {

}
