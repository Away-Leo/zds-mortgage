package com.zdsoft.finance.casemanage.material.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.casemanage.material.entity.CaseMaterialListAtta;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:CaseMaterialListAttaService.java
 * @Package:com.zdsoft.finance.casemanage.material.service
 * @Description:案件资料清单附件服务接口
 * @author: gonght
 * @date:2017年1月15日 下午2:51:31
 * @version:v1.0
 */
public interface CaseMaterialListAttaService extends BaseService<CaseMaterialListAtta> {

	/**
	 * 
	 * 保存案件资料清单附件(如果是多个附件id以,号分割)
	 *
	 * @author gonght
	 * @param caseMaterialListAtta
	 * @return
	 * @throws Exception
	 */
	public void save(CaseMaterialListAtta caseMaterialListAtta) throws Exception;

	/**
	 * 
	 * 保存案件下的客户征信资料清单附件(如果是多个附件id以,号分割)
	 *
	 * @author gonght
	 * @param caseMaterialListAtta
	 * @return
	 * @throws Exception
	 */
	public void saveCustomerCreditAtta(CaseMaterialListAtta caseMaterialListAtta) throws Exception;

	/**
	 * 申请表附件保存(如果materiaCode在CaseMaterialList表中不存在，需要通过产品+资料小类初始化CaseMaterialList表.保存附件方式为先清除业务表单id+资料小类下的所有，在添加)
	 * 
	 * @author gonght
	 * @param caseApplyId
	 *            案件申请id
	 * @param businessId
	 *            业务表单id
	 * @param productId
	 *            产品id
	 * @param materiaCode
	 *            附件对应类别(资料清单小类Code)
	 * @param attachmentIds
	 *            附件id数组
	 * @throws Exception
	 */
	public void saveCaseApplyCreditAtta(String caseApplyId, String businessId, String productId, String materiaCode,
			String[] attachmentIds) throws Exception;

	/**
	 * 通过业务表单id+资料清单小类别，查找满足条件的附件
	 * 
	 * @author gonght
	 * @param businessId
	 *            业务表单id
	 * @param materiaCode
	 *            附件对应类别(资料清单小类Code)
	 * @return
	 * @throws Exception
	 */
	public List<CaseMaterialListAtta> findByBusinessIdAndMateriaCode(String businessId, String materiaCode) throws Exception;
}