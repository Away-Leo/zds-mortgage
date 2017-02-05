package com.zdsoft.finance.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.MaterialList;

/**
 * 资料清单列表操作库
 * @author LiaoGuoWei
 * @create 2016-12-26 9:55
 **/
public interface MaterialListRepository extends CustomRepository<MaterialList,String> {

    /**
     * 同一产品下按照资料大类和资料名称编号查找
     * @param MateriaTypeCode
     * @param ProductCode
     * @param MateriaCode
     * @return
     */
    public MaterialList findByMateriaTypeCodeAndProductCodeAndMateriaCode(String MateriaTypeCode,String ProductCode,String MateriaCode);

    /**
     * 查询
     * @param productId
     * @return
     * @throws BusinessException
     */
    @Query("select ml from MaterialList ml where ml.isDeleted=false and ml.productCode=:productId ")
    public List<MaterialList> findByProductId(@Param("productId")String productId) throws BusinessException;

    /**
     * 按照产品ID和资料名称查找
     * @param productCode
     * @param materiaName
     * @return
     */
    @Query(" select m from MaterialList m where m.isDeleted=false and m.productCode = :productCode and m.materiaName = :materiaName")
    public List<MaterialList> findByProductCodeAndMateriaName(@Param("productCode")String productCode,@Param("materiaName")String materiaName);
    /**
     * 按照产品ID和助记码查找
     * @param productCode
     * @param rememberCode
     * @return
     */
    @Query(" select m from MaterialList m where m.isDeleted=false and m.productCode = :productCode and m.rememberCode = :rememberCode")
    public List<MaterialList> findByProductCodeAndRememberCode(@Param("productCode")String productCode,@Param("rememberCode")String rememberCode);
    /**
     * 按照产品ID和数字助记码查找
     * @param productCode
     * @param rememberNo
     * @return
     */
    @Query(" select m from MaterialList m where m.isDeleted=false and m.productCode = :productCode and m.rememberNo = :rememberNo")
    public List<MaterialList> findByProductCodeAndRememberNo(@Param("productCode")String productCode,@Param("rememberNo")Long rememberNo);
}
