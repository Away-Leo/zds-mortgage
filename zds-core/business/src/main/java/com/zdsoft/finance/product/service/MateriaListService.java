package com.zdsoft.finance.product.service;

import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.MaterialList;
import com.zdsoft.finance.product.entity.Product;

import java.util.List;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: MateriaListService.java 
 * @ClassName: MateriaListService 
 * @Description: 资料清单
 * @author gufeng 
 * @date 2017年3月2日 下午8:09:47 
 * @version V1.0
 */
public interface MateriaListService extends BaseService<MaterialList>{


    /**
     * @Title: findMateriaListById
     * @Description: 通过ID查找
     * @author liaoguowei
     * @param id
     * @return com.zdsoft.finance.product.entity.MaterialList
     * @throws
     */
    public MaterialList findMateriaListById(String id) throws BusinessException;

	/**
     * @Title: onlyOne 
     * @Description: 唯一性验证
     * @author gufeng 
     * @param bean 数据验证
     */
	public void onlyOne(MaterialList bean) throws BusinessException;
	
	/**
	 * @Title: saveOrUpdateMateriaList 
	 * @Description: 保存或更新
	 * @author gufeng 
	 * @param materialList 数据
	 * @return 保存后的资料
	 * @throws BusinessException 异常
	 */
    public MaterialList saveOrUpdateMateriaList(MaterialList materialList) throws BusinessException;
    /**
     * @Title: findByProductId 
     * @Description: 查询
     * @author gufeng 
     * @param productId 产品id
     * @return 资料数据
     * @throws BusinessException 查询异常
     */
    public List<MaterialList> findByProductId(String productId) throws BusinessException;
    
    /**
     * @Title: findByProductIdAndMaterial 
     * @Description: 资料
     * @author gufeng 
     * @param productId 产品id
     * @param material 助记码
     * @return 资料数据
     * @throws BusinessException 查询异常
     */
    public List<MaterialList> findByProductIdAndMaterial(String productId,String material)throws BusinessException;
    
   //////////////////TODO 下面代码暂时无用
	

    /**
     * 按照产品ID和资料大类和资料编号查找
     * @param materiaTypeCode  资料大类编号
     * @param productId 产品编号
     * @param materiaCode 资料编号
     * @return
     * @throws BusinessException
     */
    public MaterialList findByMateriaTypeCdAndProductIdAndMateriaCd(String materiaTypeCode,String productId,String materiaCode) throws BusinessException;

    /**
     * @Title: findByProductIdAndMateriaCode
     * @Description: 通过产品ID和资料编号查找
     * @author liaoguowei
     * @param productId
     * @param materiaCode
     * @return com.zdsoft.finance.product.entity.MaterialList
     * @throws BusinessException
     */
    public MaterialList findByProductIdAndMateriaCode(String productId,String materiaCode) throws Exception;
    /**
     * @Title: findByProductIdAndMateriaCode
     * @Description: 通过产品ID和资料名称查找
     * @author liaoguowei
     * @param productId
     * @param materiaName
     * @return com.zdsoft.finance.product.entity.MaterialList
     * @throws BusinessException
     */
    public MaterialList findByProductIdAndMateriaName(String productId,String materiaName) throws BusinessException;

//    /**
//     * 得到表格二维码数据
//     * @param materialLists 资料清单集合
//     * @return
//     * @throws BusinessException
//     */
//    public Map<String,List<List<Map<String,Object>>>> getTwoCodeData(List<MaterialList> materialLists,String outPath) throws BusinessException;

    /**
     * @Title: copy 
     * @Description: 复制
     * @author gufeng 
     * @param oldProduct 旧产品
     * @param newProduct 新产品
     * @param empDto 员工
     * @throws BusinessException 复制异常
     */
    public void copy(Product oldProduct,Product newProduct,EmpDto empDto) throws BusinessException;
    
    /**
     * 
     * @Title: logicDeleteMaterialAndMaterialLimit 
     * @Description: 逻辑删除资料清单以及权限
     * @author liuwei
     * @param id 资料清单id
     * @throws BusinessException 
     */
    public void logicDeleteMaterialAndMaterialLimit(String id) throws BusinessException;

    /**
     * @Title: findByProductAndClass
     * @Description: 按照产品类型和唯一编码（包括资料中文名称、助记码、数字助记码）进行查找
     * @author liaoguowei
     * @param productId
     * @param classType
     * @return com.zdsoft.finance.product.entity.MaterialList
     * @throws BusinessException
     */
    public MaterialList findByProductAndClass(String productId,String classType) throws BusinessException;


}
