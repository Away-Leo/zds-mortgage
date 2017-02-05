package com.zdsoft.finance.cooperator.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.cooperator.entity.Capitalist;
import com.zdsoft.finance.cooperator.entity.CooperatorTerminal;

public interface CapitalistRepository extends CustomRepository<Capitalist, String> {

	@Query(" from Capitalist where isDeleted = false ")
	public List<Capitalist> findList();
	
	@Query(" from Capitalist where isDeleted = false and createOrgCd = :createOrgCd and logicDelelte = :logicDelelte")
	public List<Capitalist> findBylogicDelelte(@Param("createOrgCd") String createOrgCd,@Param("logicDelelte") String logicDelelte );
	
	
	public List<Capitalist> findBylogicDelelte(String logicDelelte);
	
	/*
	 * 标准合同查询资方
	 * @author wangnengduo
	 * @date 2017-1-17
	 */

	public List<Capitalist> findByCapitalistType(String capitalistType);
}
