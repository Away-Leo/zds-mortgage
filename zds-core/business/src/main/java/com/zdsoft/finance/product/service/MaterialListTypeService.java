package com.zdsoft.finance.product.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.product.entity.MaterialListType;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: MaterialListTypeService.java 
 * @ClassName: MaterialListTypeService 
 * @Description: 资料类型
 * @author gufeng 
 * @date 2017年3月16日 下午2:31:35 
 * @version V1.0
 */
public interface MaterialListTypeService extends BaseService<MaterialListType>{
	
	/**
	 * @Title: findAllMaterialListType 
	 * @Description: 所有资料类型
	 * @author gufeng 
	 * @return 资料类型
	 */
	public List<MaterialListType> findAllMaterialListType();
	
}
