package com.zdsoft.finance.casemanage.receivablePlan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivablePlan;
import com.zdsoft.finance.common.base.CustomRepository;

/**
 * 还款计划
 * @author wangrongwei
 * @create 2017-01-07 19:11
 */
public interface ReceivableRepository extends CustomRepository<ReceivablePlan, String>{

	/**
	 * 通过还款计划基本信息ID查询还款计划
	 *
	 * @author wrw
	 * @param receivableInfoId
	 * @return
	 */
	public List<ReceivablePlan> findByReceivableInfoId(String receivableInfoId);
	
	/**
	 * 通过还款计划基本信息ID删除还款计划
	 *
	 * @author wrw
	 * @param receivableInfoId
	 */
	@Modifying
	@Query("delete from ReceivablePlan where receivableInfoId = :id")
	public void deleteByReceivableInfoId(@Param("id")String receivableInfoId);
}
