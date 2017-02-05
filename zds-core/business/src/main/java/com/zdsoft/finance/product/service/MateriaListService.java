package com.zdsoft.finance.product.service;

import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.MaterialList;
import com.zdsoft.finance.product.entity.Product;

import java.util.List;
import java.util.Map;

/**
 * 资料清单service
 * @author LiaoGuoWei
 * @create 2016-12-26 11:09
 **/
public interface MateriaListService extends BaseService<MaterialList>{

    /**
     * 按照ID查找
     * @param materiaListId 资料清单ID
     * @return
     * @throws BusinessException
     */
    public MaterialList findByMaterialListById(String materiaListId) throws BusinessException;


    /**
     * 保存资料清单数据
     * @param materialList 资料清单单条数据
     * @return
     * @throws BusinessException
     */
    public MaterialList saveMateriaList(MaterialList materialList) throws BusinessException;

    /**
     * 更新资料清单
     * @param materialList 资料清单单条数据
     * @return
     * @throws BusinessException
     */
    public MaterialList updateMateriaList(MaterialList materialList) throws BusinessException;

    /**
     * 更新或保存资料清单
     * @param materialList 资料清单单条数据
     * @return
     * @throws BusinessException
     */
    public MaterialList saveOrUpdateMateriaList(MaterialList materialList) throws BusinessException;

    /**
     * 同一产品下按照资料大类和资料名称编号查找
     * @param materiaTypeCode  资料大类编号
     * @param productCode 产品编号
     * @param materiaCode 资料编号
     * @return
     */
    public MaterialList findByMateriaTypeCdAndProductIdAndMateriaCd(String materiaTypeCode,String productId,String materiaCode) throws BusinessException;

    /**
     * 按照产品编号和资料大类和资料编号查找
     * @param materiaTypeCode  资料大类编号
     * @param productCode 产品编号
     * @param materiaCode 资料编号
     * @return
     * @throws BusinessException
     */
    public List<MaterialList> findByTypeCdAndProductIdAndMateriaCd(String materiaTypeCode,String productId,String materiaCode) throws BusinessException;

    /**
     * 得到表格二维码数据
     * @param materialLists 资料清单集合
     * @return
     * @throws BusinessException
     */
    public Map<String,List<List<Map<String,Object>>>> getTwoCodeData(List<MaterialList> materialLists,String outPath) throws BusinessException;

    /**
     * 查询
     * @param productId
     * @return
     * @throws BusinessException
     */
    public List<MaterialList> findByProductId(String productId) throws BusinessException;

    /**
     * 复制
     */
    public void copy(Product oldProduct,Product newProduct,EmpDto empDto) throws BusinessException;

    /**
     * 按照产品类型和唯一编码（包括资料中文名称、助记码、数字助记码）进行查找
     * @param productId
     * @param classType
     * @return
     * @throws BusinessException
     */
    public MaterialList findByProductAndClass(String productId,String classType) throws BusinessException;
}
