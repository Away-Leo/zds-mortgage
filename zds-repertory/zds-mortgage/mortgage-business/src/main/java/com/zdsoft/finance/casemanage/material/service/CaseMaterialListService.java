package com.zdsoft.finance.casemanage.material.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.casemanage.material.entity.CaseMaterialList;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:CaseMaterialListService.java
 * @Package:com.zdsoft.finance.casemanage.material.service
 * @Description:案件资料清单服务接口
 * @author: gonght
 * @date:2017年1月15日 下午2:26:45
 * @version:v1.0
 */
public interface CaseMaterialListService extends BaseService<CaseMaterialList> {

	/**
	 * 保存案件资料清单
	 *
	 * @author gonght
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public CaseMaterialList saveCredit(CaseMaterialList entity) throws Exception;

	/**
	 * 案件资料清单（附件）数据分页查询
	 *
	 * @author gonght
	 * @param pageRequest
	 *            分页请求对象
	 * @param li
	 *            封装后的查询参数集合
	 * @return
	 * @throws Exception
	 */
	Page<Map<String, Object>> findCaseMaterialList(PageRequest pageRequest, List<QueryObj> li) throws Exception;

	/**
	 * 通过资料类别code获取案件资料清单信息
	 *
	 * @author gonght
	 * @param materiaCode
	 *            资料类别code(小类)
	 * @return
	 * @throws Exception
	 */
	public List<CaseMaterialList> findByMateriaCode(String materiaCode) throws Exception;

	/**
	 * 通过产品资料清单Id获取案件资料清单信息
	 *
	 * @author gonght
	 * @param materialListId
	 *            产品资料清单Id
	 * @return
	 */
	public List<CaseMaterialList> findByMaterialListId(String materialListId) throws Exception;
}
