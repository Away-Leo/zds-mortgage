package com.zdsoft.finance.product.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.MateriaListLimits;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: MateriaListLimitsService.java 
 * @ClassName: MateriaListLimitsService 
 * @Description: 资料清单权限
 * @author gufeng 
 * @date 2017年3月2日 下午4:43:51 
 * @version V1.0
 */
public interface MateriaListLimitsService extends BaseService<MateriaListLimits>{

	/**
	 * @Title: getLimits 
	 * @Description: 获取权限
	 * @author gufeng 
	 * @param projectId 产品id
	 * @param materiaTypeCode 资料大类编号
	 * @return 权限
	 */
	public List<MateriaListLimits> getLimits(String productId,String materiaTypeCode);

	/**
	 * @Title: saveLimits 
	 * @Description: 保存权限
	 * @author gufeng 
	 * @param productId 产品id
	 * @param materiaTypeCode 资料大类
	 * @param materiaLimit 权限
	 * @throws BusinessException 保存异常
	 */
	public void saveLimits(String productId, String[] materiaTypeCode, String[] materiaLimit,EmpDto emp)throws BusinessException;


	/**
	 * @Title: findByAllCondition
	 * @Description: 按照全条件查找
	 * @author liaoguowei
	 * @param productId
	 * @param materiaTypeCode
	 * @param materiaLimitLike
	 * @return com.zdsoft.finance.product.entity.MateriaListLimits
	 * @throws BusinessException
	 */
	public MateriaListLimits findWithMateriaLimitLike(String productId,String materiaTypeCode,String materiaLimitLike) throws BusinessException;
	
	/**
	 * @Title: findLimitByProductId 
	 * @Description: 查找权限
	 * @author gufeng 
	 * @param productId 产品id
	 * @return 权限列表
	 * @throws BusinessException 查询异常
	 */
	public List<Map<String, Object>> findLimitByProductId(String productId)throws BusinessException;

}
