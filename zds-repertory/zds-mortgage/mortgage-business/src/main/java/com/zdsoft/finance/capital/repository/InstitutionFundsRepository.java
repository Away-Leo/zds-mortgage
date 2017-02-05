package com.zdsoft.finance.capital.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.capital.entity.InstitutionFunds;
import com.zdsoft.finance.common.base.CustomRepository;

/**
 * 机构资金分配Repository
 * 
 * @createTime:2017年1月10日
 * @author liuwei
 * @version 1.0
 */
public interface InstitutionFundsRepository extends CustomRepository<InstitutionFunds, String> {

	/**
	 * 查询所有机构资金分配信息
	 */
	@Query("select t from InstitutionFunds t where t.isDeleted = false ")
	public List<InstitutionFunds> findAll();

	/**
	 * 根据分公司编号查询机构资金配置信息
	 * 
	 * @param orgCd
	 *            分公司编号
	 * @return 机构资金配置信息
	 */
	@Query("select t from InstitutionFunds t where t.isDeleted = false and t.orgCd = :orgCd ")
	public InstitutionFunds findByOrgCd(@Param("orgCd")String orgCd);
}
