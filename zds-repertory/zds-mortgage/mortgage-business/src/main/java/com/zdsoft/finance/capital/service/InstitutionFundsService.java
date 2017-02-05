package com.zdsoft.finance.capital.service;

import java.util.List;

import com.zdsoft.essential.dto.org.OrgDto;
import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.capital.entity.InstitutionFunds;

/**
 * 机构资方配置信息Service
 * 
 * @createTime:2017年1月10日
 * @author liuwei
 * @version 1.0
 */
public interface InstitutionFundsService extends BaseService<InstitutionFunds> {

	/**
	 * 查询所有的资金机构配置信息
	 * 
	 * @return 资金机构配置信息列表
	 */
	public List<InstitutionFunds> findAll();

	/**
	 * 将分公司数据保存为资金机构配置信息
	 * 
	 * @param organizations
	 *            分公司数据
	 * @throws Exception
	 *             调用平台接口出现Exception
	 */
	public void saveInstitutionFundsList(List<OrgDto> organizations) throws Exception;

	/**
	 * 对比分公司数据与资金机构配置信息，将最新的机构信息保存进资金机构配置信息
	 * 
	 * @param fundsList
	 *            机构资金配置集合
	 * @param organizations
	 *            分公司集合
	 * @throws Exception
	 *             调用平台接口出现Exception
	 */
	public void updateInstitutionFundsList(List<InstitutionFunds> fundsList, List<OrgDto> organizations)
			throws Exception;

	/**
	 * 修改资金机构配置信息
	 * 
	 * @param funds
	 *            资金机构配置信息
	 * @return 资金机构配置信息
	 */
	public InstitutionFunds updateInstitutionFunds(InstitutionFunds funds);

	/**
	 * 根据分公司编号查询资金机构配置信息
	 * 
	 * @param orgCd
	 *            分公司编号
	 * @return 资金机构配置信息
	 */
	public InstitutionFunds findByOrgCd(String orgCd);

}
