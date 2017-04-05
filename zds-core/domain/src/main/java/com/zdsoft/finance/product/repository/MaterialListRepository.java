package com.zdsoft.finance.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.product.entity.MaterialList;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: MaterialListRepository.java 
 * @ClassName: MaterialListRepository 
 * @Description: 资料清单列表操作库
 * @author gufeng 
 * @date 2017年3月2日 下午8:10:42 
 * @version V1.0
 */
public interface MaterialListRepository extends CustomRepository<MaterialList,String> {

	/**
     * @Title: findByProductIdAndMateriaTypeCodeAndMateriaCode 
     * @Description: 数据查询
     * @author gufeng 
     * @param productId 产品id
     * @param materiaTypeCode 类型编号
     * @param materiaCode 编号
     * @param isDeleted 是否删除
     * @return 数据
     */
	public List<MaterialList> findByProductIdAndMateriaTypeCodeAndMateriaCodeAndIsDeleted(String productId, String materiaTypeCode,
			String materiaCode,Boolean isDeleted);

	/**
	 * @Title: findByProductId 
	 * @Description: 资料查询
	 * @author gufeng 
	 * @param productId 产品id
	 * @return 资料
	 */
    @Query("select ml from MaterialList ml where ml.isDeleted=false and ml.productId=:productId ")
    public List<MaterialList> findByProductId(@Param("productId")String productId);
	
    /**
     * @Title: findByProductIdAndMateriaCode
     * @Description: 按照产品ID和资料编号查找
     * @author liaoguowei
     * @param productId
     * @param materiaCode
     * @return com.zdsoft.finance.product.entity.MaterialList
     * @throws
     */
    @Query(" from MaterialList m where m.isDeleted = false and m.productId = :productId and m.materiaCode = :materiaCode ")
    public MaterialList findByProductIdAndMateriaCode(@Param("productId")String productId,@Param("materiaCode")String materiaCode) throws Exception;

    /**
     * @Title: findByProductIdAndMateriaName
     * @Description: 通过产品ID和资料名称查找
     * @author liaoguowei
     * @param productId
     * @param materiaName
     * @return com.zdsoft.finance.product.entity.MaterialList
     * @throws
     */
    @Query(" from MaterialList m where m.isDeleted = false and m.productId = :productId and m.materiaName = :materiaName  ")
    public List<MaterialList> findByProductIdAndMateriaName(@Param("productId")String productId,@Param("materiaName")String materiaName);

    /**
     * @Title: findByProductIdAndMateriaNm 
     * @Description: 产品id和资料名称查询
     * @author gufeng 
     * @param productId 产品id
     * @param material 资料名称
     * @return 资料数据
     */
    @Query(" from MaterialList m where m.isDeleted = false and m.productId = :productId and m.materiaName = :materiaName ")
	public List<MaterialList> findByProductIdAndMateriaNm(@Param("productId")String productId, @Param("materiaName")String materiaName);
    
    /**
     * @Title: findByProductIdAndMateriaCd 
     * @Description: 产品id和资料编号
     * @author gufeng 
     * @param productId 产品id
     * @param materiaCode 资料编号
     * @return 资料数据
     */
    @Query(" from MaterialList m where m.isDeleted = false and m.productId = :productId and m.materiaCode = :materiaCode ")
    public List<MaterialList> findByProductIdAndMateriaCd(@Param("productId")String productId, @Param("materiaCode")String materiaCode);
    /**
     * @Title: findByProductIdAndRememberCode 
     * @Description: 产品id和助记码查询
     * @author gufeng 
     * @param productId 产品id
     * @param material 助记码
     * @return 资料数据
     */
    @Query(" from MaterialList m where m.isDeleted = false and m.productId = :productId and m.rememberCode = :rememberCode ")
	public List<MaterialList> findByProductIdAndRememberCode(@Param("productId")String productId, @Param("rememberCode")String rememberCode);

    /**
     * @Title: findByProductIdAndRememberNo 
     * @Description: 产品id和助记码查询
     * @author gufeng 
     * @param productId 产品id
     * @param rememberNo 数字助记码
     * @return 资料数据
     */
    @Query(" from MaterialList m where m.isDeleted = false and m.productId = :productId and m.rememberNo = :rememberNo ")
	public List<MaterialList> findByProductIdAndRememberNo(@Param("productId")String productId, @Param("rememberNo")Long rememberNo);
    
    /**
     * @Title: findByproductidAndMateriaTypeCodeAndIsDeleted 
     * @Description: 资料查询
     * @author gufeng 
     * @param productId 产品id
     * @param materiaTypeCode 大类编号
     * @param isDeleted 是否删除
     * @return 资料数据
     */
	public List<MaterialList> findByProductIdAndMateriaTypeCodeAndIsDeleted(String productId, String materiaTypeCode,
			boolean isDeleted);
    
}
