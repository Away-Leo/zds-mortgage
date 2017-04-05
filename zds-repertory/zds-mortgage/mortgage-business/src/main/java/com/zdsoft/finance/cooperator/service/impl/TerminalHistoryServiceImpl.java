package com.zdsoft.finance.cooperator.service.impl;

import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.cooperator.entity.TerminalHistory;
import com.zdsoft.finance.cooperator.service.TerminalHistoryService;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: TerminalHistoryServiceImpl.java
 * @ClassName: TerminalHistoryServiceImpl
 * @Description: 终端历史记录ServiceImpl
 * @author liuwei
 * @date 2017年3月8日 上午9:55:29
 * @version V1.0
 */
@Service("terminalHistoryService")
public class TerminalHistoryServiceImpl extends
		BaseServiceImpl<TerminalHistory, CustomRepository<TerminalHistory, String>> implements TerminalHistoryService {

}