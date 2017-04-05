package com.zdsoft.finance.casemanage.handleMortgage.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.casemanage.handleMortgage.entity.MaterialPromise;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: MaterialPromiseService.java 
 * @ClassName: MaterialPromiseService 
 * @Description: 后补资料承诺Service
 * @author zhoushichao 
 * @date 2017年2月18日 下午2:16:24 
 * @version V1.0 
 */ 
public interface MaterialPromiseService extends BaseService<MaterialPromise>{

	/**
	 * 
	 * @Title: findByCaseApplyId 
	 * @Description: 根据案件id查询后补资料承诺
	 * @author zhoushichao 
	 * @param caseApplyId 案件id
	 * @return
	 * @throws Exception
	 */
	public List<MaterialPromise> findByCaseApplyId(String caseApplyId) throws Exception;
	
	/**
	 * 
	 * @Title: saveMaterialPromise 
	 * @Description: 批量保存 后补资料承诺
	 * @author zhoushichao 
	 * @param materialPromiseList 后补资料承诺
	 * @return
	 * @throws Exception
	 */
	public void saveMaterialPromiseList(List<MaterialPromise> materialPromiseList) throws Exception;
	
	/**
	 * 
	 * @Title: queryByCaseApplyIdAndMaterialTypeCode 
	  * @Description: 根据案件和后补资料类型升序查询
	 * @author xj 
	 * @param caseApplyId 案件id
	 * @param codes 后补资料类型code集合
	 * @return
	 * @throws Exception
	 */
	public List<MaterialPromise> queryByCaseApplyIdAndMaterialTypeCode(String caseApplyId,List<String> codes) throws Exception;
	
	/**
	 * 
	 * @Title: findByCaseApplyIdAndMaterialTypeCode 
	 * @Description: 根据案件和后补资料类型查询
	 * @author xj 
	 * @param caseApplyId 案件id
	 * @param materialTypeCode 后补资料类型
	 * @return
	 * @throws Exception
	 */
	public MaterialPromise findByCaseApplyIdAndMaterialTypeCode(String caseApplyId,String materialTypeCode) throws Exception;
	
	/**
	 * 
	 * @Title: deteleById 
	 * @Description: 通过id物理删除
	 * @author xj 
	 * @param id id
	 */
	public void deleteById(String id);
}
