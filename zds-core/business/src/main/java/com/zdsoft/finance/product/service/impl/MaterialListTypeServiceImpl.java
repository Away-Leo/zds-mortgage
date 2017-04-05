package com.zdsoft.finance.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.product.entity.MaterialListType;
import com.zdsoft.finance.product.repository.MaterialListTypeRepository;
import com.zdsoft.finance.product.service.MaterialListTypeService;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: MaterialListTypeServiceImpl.java 
 * @ClassName: MaterialListTypeServiceImpl 
 * @Description: 资料类型
 * @author gufeng 
 * @date 2017年3月16日 下午2:34:10 
 * @version V1.0
 */
@Service("materialListTypeService")
public class MaterialListTypeServiceImpl extends BaseServiceImpl<MaterialListType, MaterialListTypeRepository>
implements MaterialListTypeService{
	
	@Autowired
	private MaterialListTypeRepository materialListTypeRepository;
	
	@Override
	public List<MaterialListType> findAllMaterialListType() {
		return materialListTypeRepository.findByIsDeletedOrderByIdAsc(false);
	}

}
