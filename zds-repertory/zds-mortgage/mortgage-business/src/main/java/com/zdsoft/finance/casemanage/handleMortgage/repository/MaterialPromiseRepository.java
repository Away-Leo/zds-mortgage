package com.zdsoft.finance.casemanage.handleMortgage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.casemanage.handleMortgage.entity.MaterialPromise;
import com.zdsoft.finance.common.base.CustomRepository;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: MaterialPromiseRepository.java 
 * @ClassName: MaterialPromiseRepository 
 * @Description: 后补资料承诺Repository
 * @author zhoushichao 
 * @date 2017年2月18日 下午2:15:24 
 * @version V1.0 
 */ 
public interface MaterialPromiseRepository extends CustomRepository<MaterialPromise, String>{

	/**
     * 
     * @Title: findByCaseApplyId 
     * @Description: 根据案件Id查询后补资料承诺列表
     * @author zhoushichao 
     * @param caseApplyId 案件Id
     * @return
     */
	public List<MaterialPromise> findByCaseApplyId(String caseApplyId);
	
	/**
	 * 
	 * @Title: queryByCaseApplyIdAndMaterialTypeCode 
	 * @Description: 根据案件和后补资料类型升序查询
	 * @author xj 
	 * @param caseApplyId 案件id
	 * @param codes 后补资料类型code集合
	 * @return
	 */
	@Query("select ma from MaterialPromise ma where ma.caseApplyId = :caseApplyId and ma.materialTypeCode in (:materialTypeCodes) order by ma.createTime ")
	public List<MaterialPromise> queryByCaseApplyIdAndMaterialTypeCode(@Param("caseApplyId")String caseApplyId,@Param("materialTypeCodes")List<String> codes);
	
	/**
	 * 
	 * @Title: findByCaseApplyIdAndMaterialTypeCode 
	 * @Description: 根据案件和后补资料类型查询
	 * @author xj 
	 * @param caseApplyId  案件id
	 * @param materialTypeCode 后补资料类型
	 * @return
	 */
	public MaterialPromise findByCaseApplyIdAndMaterialTypeCode(String caseApplyId,String materialTypeCode);
}
