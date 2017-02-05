package com.zdsoft.finance.cooperator.service.impl;

import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.cooperator.entity.TerminalHistory;
import com.zdsoft.finance.cooperator.service.TerminalHistoryService;

@Service("terminalHistoryService")
public class TerminalHistoryServiceImpl extends BaseServiceImpl<TerminalHistory, CustomRepository<TerminalHistory, String>>
implements TerminalHistoryService{

}