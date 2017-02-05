package com.zdsoft.finance.marketing.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.marketing.entity.PropertyOwner;
import com.zdsoft.finance.marketing.repository.PropertyOwnerRepository;
import com.zdsoft.finance.marketing.service.PropertyOwnerService;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:PropertyOwnerServiceImpl.java
 * @Package:com.zdsoft.finance.marketing.service.impl
 * @Description:产权人实现服务类
 * @author: zhoushichao
 * @date:2017年1月10日 下午9:52:01
 * @version:v1.0
 */
@Service("propertyOwnerService")
public class PropertyOwnerServiceImpl extends BaseServiceImpl<PropertyOwner, CustomRepository<PropertyOwner, String>>
implements PropertyOwnerService{
	
	@Autowired
	PropertyOwnerRepository propertyOwnerRepository;

	@Override
	@Transactional
	public List<PropertyOwner> saveOrUpdatePropertyOwner(List<PropertyOwner> propertyOwners, String housePropertyId)
			throws Exception {
		List<PropertyOwner> lists = new ArrayList<PropertyOwner>();
		//添加或保存
		if(ObjectHelper.isNotEmpty(propertyOwners)){
			for (PropertyOwner propertyOwner : propertyOwners) {
				//设置房产Id
				propertyOwner.setHousePropertyId(housePropertyId);
				propertyOwner = this.saveOrUpdateEntity(propertyOwner);
				lists.add(propertyOwner);
			}
		}
		return lists;
	}

	@Override
	public List<PropertyOwner> findByHousePropertyId(String housePropertyId) {
		return propertyOwnerRepository.findByHousePropertyId(housePropertyId);
	}
	
}
