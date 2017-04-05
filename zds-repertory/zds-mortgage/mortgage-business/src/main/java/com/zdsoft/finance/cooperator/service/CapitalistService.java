package com.zdsoft.finance.cooperator.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.cooperator.entity.Capitalist;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CapitalistService.java
 * @ClassName: CapitalistService
 * @Description: 资方Service
 * @author liuwei
 * @date 2017年2月25日 下午3:12:06
 * @version V1.0
 */
public interface CapitalistService extends BaseService<Capitalist> {

	/**
	 * 
	 * @Title: findList
	 * @Description: 查询有效的资方列表
	 * @author liuwei
	 * @return 资方列表
	 */
	public List<Capitalist> findList();

	/**
	 * 
	 * @Title: findListByStatus
	 * @Description: 根据状态查询资方集合
	 * @author liuwei
	 * @param isStop
	 *            资方状态
	 * @return 资方集合
	 */
	public List<Capitalist> findListByStatus(String isStop);

	/**
	 * 
	 * @Title: findListByStatusAndOrgCd
	 * @Description: 根据状态以及创建人部门编号查询资方集合
	 * @author liuwei
	 * @param createOrgCd
	 *            创建人部门编号
	 * @param isStop
	 *            状态
	 * @return 资方集合
	 */
	public List<Capitalist> findListByStatusAndOrgCd(String createOrgCd, String isStop);

	/*
	 * 标准合同查询资方
	 * 
	 * @author wangnengduo
	 * 
	 * @date 2017-1-17
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
	public List<Capitalist> findCapitalistByCapitalistType(String capitalistType, String isStop);

	/**
	 * 
	 * @Title: saveCapitalTemp
	 * @Description: 保存临时资方信息
	 * @author liuwei
	 * @return 资方信息
	 */
	public Capitalist saveCapitalTemp(Capitalist capitalist);

	/**
	 * 
	 * @Title: saveOrUpdateCapitalist
	 * @Description: 保存修改资方信息
	 * @author liuwei
	 * @param capitalist
	 *            资方信息
	 * @return 资方信息
	 * @throws Exception
	 */
	public Capitalist saveOrUpdateCapitalist(Capitalist capitalist) throws Exception;

	/**
	 * 
	 * @Title: findByCooperatorNameAndCapitalistType
	 * @Description: 根据资方名称以及类型查找资方信息
	 * @author liuwei
	 * @param cooperatorName
	 *            资方名称
	 * @param capitalistType
	 *            资方类型
	 * @return 资方信息
	 */
	public Capitalist findByCooperatorNameAndCapitalistType(String cooperatorName, String capitalistType);

}
