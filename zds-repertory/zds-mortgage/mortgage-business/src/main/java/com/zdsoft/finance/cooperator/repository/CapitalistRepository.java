package com.zdsoft.finance.cooperator.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.cooperator.entity.Capitalist;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CapitalistRepository.java
 * @ClassName: CapitalistRepository
 * @Description: 资方(刘伟二次维护)
 * @author liuwei
 * @date 2017年2月25日 下午2:33:57
 * @version V1.0
 */
public interface CapitalistRepository extends CustomRepository<Capitalist, String> {

	/**
	 * @Title: findList
	 * @Description: 查询所有有效的资方信息
	 * @author liuwei
	 * @return 资方信息集合
	 */
	@Query(" from Capitalist where isDeleted = false ")
	public List<Capitalist> findList();

	/**
	 * 
	 * @Title: findListByStatusAndOrgCd
	 * @Description: 根据创建人机构编码及资方状态查找资方信息
	 * @author liuwei
	 * @param createOrgCd
	 *            创建人机构编码
	 * @param isStop
	 *            资方状态
	 * @return 资方信息集合
	 */
	@Query(" from Capitalist where isDeleted = false and createOrgCd = :createOrgCd and isStop = :isStop")
	public List<Capitalist> findListByStatusAndOrgCd(@Param("createOrgCd") String createOrgCd,
			@Param("isStop") String isStop);

	/**
	 * 
	 * @Title: findByIsStop
	 * @Description: 根据资方状态查询资方信息集合
	 * @author liuwei
	 * @param isStop
	 *            资方状态
	 * @return 资方信息集合
	 */
	public List<Capitalist> findByIsStop(String isStop);

	/**
	 * 
	 * @Title: findByCapitalistType
	 * @Description: 标准合同查询资方
	 * @author liuwei
	 * @param id
	 * @return 标准合同查询资方
	 */
	public List<Capitalist> findByCapitalistType(String id);

	/**
	 * 
	 * @Title: findCapitalistByCapitalistType
	 * @Description: 根据资方类型以及启用状态查询资方列表
	 * @author liuwei
	 * @param capitalistType
	 *            资方类型
	 * @param isStop
	 *            启用状态
	 * @return 资方列表
	 */
	@Query(" from Capitalist where isDeleted = false and capitalistType = :capitalistType and isStop = :isStop ")
	public List<Capitalist> findCapitalistByCapitalistType(@Param("capitalistType") String capitalistType,
			@Param("isStop") String isStop);

	/**
	 * 
	 * @Title: findByCooperatorNameAndCapitalistType
	 * @Description: 根据资方名称以及资方类型查找资方信息
	 * @author liuwei
	 * @param cooperatorName
	 *            资方名称
	 * @param capitalistType
	 *            资方类型
	 * @return 资方信息
	 */
	@Query(" from Capitalist where isDeleted = false and capitalName = :cooperatorName and capitalistType = :capitalistType ")
	public List<Capitalist> findByCooperatorNameAndCapitalistType(@Param("cooperatorName") String cooperatorName,
			@Param("capitalistType") String capitalistType);
}
