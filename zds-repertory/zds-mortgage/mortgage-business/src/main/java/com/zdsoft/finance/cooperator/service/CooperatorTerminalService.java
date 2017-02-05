package com.zdsoft.finance.cooperator.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.cooperator.entity.CooperatorTerminal;

public interface CooperatorTerminalService extends BaseService<CooperatorTerminal>{
	/**
	 * 模糊查询终端名称和终端归属
	 * @param terminalFullName
	 * @param belongTypeName
	 * @return
	 */
	public List<CooperatorTerminal> getBlurry(String terminalFullName,String belongTypeName);
	/**
	 * 查询可用数据
	 * @param status
	 * @return
	 */
	public List<CooperatorTerminal>  getLogicAndOrgList(String createOrg,String status);

}
