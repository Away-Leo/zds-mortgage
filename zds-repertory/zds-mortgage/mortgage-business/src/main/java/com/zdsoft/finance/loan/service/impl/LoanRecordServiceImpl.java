package com.zdsoft.finance.loan.service.impl;

import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.loan.entity.LoanRecord;
import com.zdsoft.finance.loan.service.LoanRecordService;
@Service
public class LoanRecordServiceImpl extends BaseServiceImpl<LoanRecord, CustomRepository<LoanRecord, String>>
		implements LoanRecordService {
}
