package com.zdsoft.finance.casemanage.material.repository;

import java.util.List;

import com.zdsoft.finance.casemanage.material.entity.CaseMaterialList;
import com.zdsoft.finance.common.base.CustomRepository;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:CaseMaterialListRepository.java
 * @Package:com.zdsoft.finance.casemanage.material.repository
 * @Description:案件资料清单Dao
 * @author: gonght
 * @date:2017年1月15日 下午2:30:52
 * @version:v1.0
 */
public interface CaseMaterialListRepository extends CustomRepository<CaseMaterialList, String> {

	/**
	 * 通过资料类别code获取案件资料清单信息
	 *
	 * @author gonght
	 * @param materiaCode
	 *            资料类别code(小类)
	 * @return
	 */
	public List<CaseMaterialList> findByMateriaCode(String materiaCode);

	/**
	 * 通过产品资料清单Id获取案件资料清单信息
	 *
	 * @author gonght
	 * @param materialListId
	 *            产品资料清单Id
	 * @return
	 */
	public List<CaseMaterialList> findByMaterialListId(String materialListId);
}
