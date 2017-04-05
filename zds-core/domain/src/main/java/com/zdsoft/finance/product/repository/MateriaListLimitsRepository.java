package com.zdsoft.finance.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.product.entity.MateriaListLimits;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: MateriaListLimitsRepository.java 
 * @ClassName: MateriaListLimitsRepository 
 * @Description: 资料清单权限
 * @author gufeng 
 * @date 2017年3月2日 下午4:15:37 
 * @version V1.0
 */
public interface MateriaListLimitsRepository extends CustomRepository<MateriaListLimits,String>{

	/**
	 * @Title: findByProductIdAndMateriaTypeCode 
	 * @Description: 查询权限
	 * @author gufeng 
	 * @param productId 产品id
	 * @param materiaTypeCode 大类编号
	 * @return 权限
	 */
	@Query("from MateriaListLimits where isDeleted=false and productId=:productId and materiaTypeCode=:materiaTypeCode")
	public List<MateriaListLimits> findByProductIdAndMateriaTypeCode(@Param("productId")String productId, @Param("materiaTypeCode")String materiaTypeCode);

	/**
	 * @Title: findByProductId 
	 * @Description: 查询
	 * @author gufeng 
	 * @param productId 产品id
	 * @return 权限数据
	 */
	public List<MateriaListLimits> findByProductId(String productId);

	/**
	 * 权限查询sql
	 */
	public String limitSql = "select l.materiaTypeCode,l.materiaTypeName,(select TO_CHAR(wmsys.wm_concat(materiaLimit)) from prd_materialList_limits where isDeleted='F' and productId=l.productId and materiaTypeCode=l.materiaTypeCode) as limits from prd_materialList l where l.productId=:productId and l.isDeleted = 'F' group by l.materiaTypeCode,l.materiaTypeName,l.productId order by l.materiaTypeCode";

}
