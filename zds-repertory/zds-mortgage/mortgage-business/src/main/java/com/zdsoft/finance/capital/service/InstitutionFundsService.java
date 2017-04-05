package com.zdsoft.finance.capital.service;

import java.util.List;

import com.zdsoft.essential.dto.org.OrgDto;
import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.capital.entity.InstitutionFunds;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: InstitutionFundsService.java
 * @ClassName: InstitutionFundsService
 * @Description: 机构资方配置信息Service
 * @author liuwei
 * @date 2017年2月8日 上午10:37:26
 * @version V1.0
 */
public interface InstitutionFundsService extends BaseService<InstitutionFunds> {

	/**
	 * 
	 * @Title: findAll
	 * @Description: 查询所有的资金机构配置信息
	 * @author liuwei
	 * @return 资金机构配置信息集合
	 */
	public List<InstitutionFunds> findAll();

	/**
	 * 
	 * @Title: saveInstitutionFundsList
	 * @Description: 将分公司数据保存为资金机构配置信息
	 * @author liuwei
	 * @param organizations
	 *            分公司数据
	 * @throws Exception
	 */
	public void saveInstitutionFundsList(List<OrgDto> organizations) throws Exception;

	/**
	 * 
	 * @Title: updateInstitutionFundsList
	 * @Description: 对比分公司数据与资金机构配置信息，将最新的机构信息保存进资金机构配置信息
	 * @author liuwei
	 * @param fundsList
	 *            机构资金配置集合
	 * @param organizations
	 *            分公司集合
	 * @throws Exception
	 */
	public void updateInstitutionFundsList(List<InstitutionFunds> fundsList, List<OrgDto> organizations)
			throws Exception;

	/**
	 * 
	 * @Title: updateInstitutionFunds
	 * @Description: 修改资金机构配置信息
	 * @author liuwei
	 * @param funds
	 *            资金机构配置信息
	 * @return 资金机构配置信息
	 */
	public InstitutionFunds updateInstitutionFunds(InstitutionFunds funds);

	/**
	 * 
	 * @Title: findByOrgCd
	 * @Description: 根据分公司编号查询资金机构配置信息
	 * @author liuwei
	 * @param orgCd
	 *            分公司编号
	 * @return 资金机构配置信息
	 */
	public InstitutionFunds findByOrgCd(String orgCd);

}
