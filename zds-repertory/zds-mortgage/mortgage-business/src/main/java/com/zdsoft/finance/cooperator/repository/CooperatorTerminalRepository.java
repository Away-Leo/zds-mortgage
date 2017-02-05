package com.zdsoft.finance.cooperator.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.cooperator.entity.CooperatorTerminal;

public interface CooperatorTerminalRepository extends CustomRepository<CooperatorTerminal, String> {

	@Query(" from CooperatorTerminal where isDeleted = false and createOrgCd = :createOrgCd and logicDelelte = :logicDelelte")
	public List<CooperatorTerminal> findBylogicDelelte(@Param("createOrgCd") String createOrgCd,@Param("logicDelelte") String logicDelelte );
	
	@Query(" from CooperatorTerminal where isDeleted = false and  (terminalFullName like :terminalFullName or belongTypeName like :belongTypeName)")
	public List<CooperatorTerminal> getBlurry(@Param("terminalFullName") String terminalFullName, @Param("belongTypeName") String belongTypeName);
}
