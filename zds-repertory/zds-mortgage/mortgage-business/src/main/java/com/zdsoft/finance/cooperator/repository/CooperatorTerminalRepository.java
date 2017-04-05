package com.zdsoft.finance.cooperator.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.cooperator.entity.CooperatorTerminal;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CooperatorTerminalRepository.java
 * @ClassName: CooperatorTerminalRepository
 * @Description: 终端repository
 * @author liuwei
 * @date 2017年2月25日 下午2:46:40
 * @version V1.0
 */
public interface CooperatorTerminalRepository extends CustomRepository<CooperatorTerminal, String> {

	/**
	 * 
	 * @Title: findByTerminalStatus
	 * @Description: 根据创建部门编号及终端状态查询终端集合
	 * @author liuwei
	 * @param createOrgCd
	 *            创建部门编号
	 * @param terminalStatus
	 *            终端状态
	 * @return 终端集合
	 */
	@Query(" from CooperatorTerminal where isDeleted = false and createOrgCd = :createOrgCd and terminalStatus = :terminalStatus")
	public List<CooperatorTerminal> findByTerminalStatus(@Param("createOrgCd") String createOrgCd,
			@Param("terminalStatus") String terminalStatus);

	/**
	 * 
	 * @Title: findByBelongRelevanceCodeAndTerminalStatus
	 * @Description: 根据终端归属编号以及终端状态查询终端集合
	 * @author liuwei
	 * @param belongRelevanceCode
	 *            终端归属编号
	 * @param terminalStatus
	 *            终端状态
	 * @return 终端集合
	 */
	@Query(" from CooperatorTerminal where isDeleted = false and belongRelevanceCode = :belongRelevanceCode and terminalStatus = :terminalStatus ")
	public List<CooperatorTerminal> findByBelongRelevanceCodeAndTerminalStatus(
			@Param("belongRelevanceCode") String belongRelevanceCode, @Param("terminalStatus") String terminalStatus);

	/**
	 * 
	 * @Title: findByShareOrgCdAndTerminalStatus
	 * @Description: 根据终端共用编号以及终端状态查询终端集合
	 * @author liuwei
	 * @param shareOrgCode
	 *            终端共用编号
	 * @param terminalStatus
	 *            终端状态
	 * @return 终端集合
	 */
	@Query(" from CooperatorTerminal where isDeleted = false and shareOrgCode = :shareOrgCode and terminalStatus = :terminalStatus ")
	public List<CooperatorTerminal> findByShareOrgCdAndTerminalStatus(@Param("shareOrgCode") String shareOrgCode,
			@Param("terminalStatus") String terminalStatus);

}
