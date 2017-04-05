package com.zdsoft.finance.capital.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.capital.entity.InstitutionFunds;
import com.zdsoft.finance.common.base.CustomRepository;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: InstitutionFundsRepository.java
 * @ClassName: InstitutionFundsRepository
 * @Description: 机构资金分配Repository
 * @author liuwei
 * @date 2017年2月8日 上午10:31:59
 * @version V1.0
 */
public interface InstitutionFundsRepository extends CustomRepository<InstitutionFunds, String> {

	/**
	 * 
	 * @Title: findByOrgCd
	 * @Description: 查询所有有效的机构资金分配信息
	 * @author liuwei
	 * @return 机构资金配置信息
	 */
	@Query("select t from InstitutionFunds t where t.isDeleted = false ")
	public List<InstitutionFunds> findAll();

	/**
	 * 
	 * @Title: findByOrgCd
	 * @Description: 根据分公司编号查询机构资金配置信息
	 * @author liuwei
	 * @param orgCd
	 *            分公司编号
	 * @return 机构资金配置信息
	 */
	@Query("select t from InstitutionFunds t where t.isDeleted = false and t.orgCd = :orgCd ")
	public InstitutionFunds findByOrgCd(@Param("orgCd") String orgCd);
}
