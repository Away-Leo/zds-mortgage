package com.zdsoft.finance.product.repository;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.product.entity.MateriaListAuth;
import com.zdsoft.finance.product.entity.MaterialList;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 资料清单节点权限操作库
 * @author LiaoGuoWei
 * @create 2016-12-26 15:36
 **/
public interface MateriaListAuthRepository extends CustomRepository<MateriaListAuth,String> {

    /**
     * 按照产品编号和资料清单查找
     * @param productCode 产品编号
     * @param materialList 资料清单
     * @return
     */
    public List<MateriaListAuth> findByProductCodeAndMaterialList(String productCode,MaterialList materialList);

    /**
     * 根据产品ID、资料清单ID、流程ID查找
     * @param productId 产品ID
     * @param materiaListId 资料清单ID
     * @param processCode 流程ID
     * @return
     */
    @Query(" select ma from MateriaListAuth ma where ma.isDeleted=false and ma.productCode = :productId and ma.materialList.id = :mateiraListId and ma.processCode = :processCode ")
    public List<MateriaListAuth> findByProductIdAndMateriaListIdAndProcessCode(@Param("productId")String productId,@Param("mateiraListId")String materiaListId,@Param("processCode")String processCode);

    /**
     * 根据产品ID和资料ID查找
     * @param productId
     * @param materiaListId
     * @return
     */
    @Query(" select ma from MateriaListAuth ma where ma.isDeleted=false and ma.productCode = :productId and ma.materialList.id = :mateiraListId  ")
    public List<MateriaListAuth> findByProductIdAndMateriaListId(@Param("productId")String productId,@Param("mateiraListId")String materiaListId);


    /**
     * 按照条件物理删除
     * @param productId
     * @param materiaListId
     * @param processCode
     */
    @Transactional
    @Modifying
    @Query(" delete from MateriaListAuth ma where ma.productCode = :productId and ma.materialList.id = :mateiraListId and ma.processCode = :processCode")
    public void deleteByCondition(@Param("productId")String productId,@Param("mateiraListId")String materiaListId,@Param("processCode")String processCode);
}
