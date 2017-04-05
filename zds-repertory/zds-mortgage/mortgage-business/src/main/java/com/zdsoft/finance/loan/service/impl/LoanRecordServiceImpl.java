package com.zdsoft.finance.loan.service.impl;

import org.springframework.stereotype.Service;


import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.loan.entity.LoanRecord;
import com.zdsoft.finance.loan.service.LoanRecordService;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: LoanRecordServiceImpl.java 
 * @ClassName: LoanRecordServiceImpl 
 * @Description: 放款操作记录ServiceImpl 
 * @author huangwei 
 * @date 2017年2月28日 下午2:06:39 
 * @version V1.0
 */
@Service
public class LoanRecordServiceImpl extends BaseServiceImpl<LoanRecord, CustomRepository<LoanRecord, String>>
		implements LoanRecordService {
}
