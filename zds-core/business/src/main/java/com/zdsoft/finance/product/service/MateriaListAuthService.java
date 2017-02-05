package com.zdsoft.finance.product.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.MateriaListAuth;

import java.util.List;

/**
 * 资料清单权限service
 * @author LiaoGuoWei
 * @create 2016-12-26 11:33
 **/
public interface MateriaListAuthService extends BaseService<MateriaListAuth> {

    public List<MateriaListAuth> findByProductCdAndMaterialListId(String productCode, String materialListId) throws BusinessException;

    /**
     * 保存资料清单节点权限
     * @param dataJsonStr
     * @param productId
     * @param materiaListId
     * @return
     * @throws BusinessException
     */
    public List<MateriaListAuth> saveMateriaAuth(String dataJsonStr,String productId,String materiaListId) throws Exception;

    /**
     * 更新资料清单节点权限
     * @param dataJsonStr
     * @param productId
     * @param materiaListId
     * @return
     * @throws BusinessException
     */
    public List<MateriaListAuth> updateMateriaAuth(String dataJsonStr,String productId,String materiaListId) throws Exception;

    /**
     * 保存或更新资料清单节点权限
     * @param dataJsonStr
     * @param productId
     * @param materiaListId
     * @return
     * @throws BusinessException
     */
    public List<MateriaListAuth> saveOrUpdateMateriaAuth(String dataJsonStr,String productId,String materiaListId) throws Exception;
}
