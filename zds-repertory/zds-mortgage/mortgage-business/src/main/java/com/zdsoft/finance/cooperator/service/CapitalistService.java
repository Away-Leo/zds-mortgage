package com.zdsoft.finance.cooperator.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.cooperator.entity.Capitalist;

public interface CapitalistService extends BaseService<Capitalist> {

	public List<Capitalist> findList();
	
	public List<Capitalist> findLogicList(String status);
	public List<Capitalist> findLogicOrgList(String createOrgCd,String status);
	
	/*
	 * 标准合同查询资方
	 * @author wangnengduo
	 * @date 2017-1-17
	 */

	public List<Capitalist> findByCapitalistType(String capitalistType);

}
