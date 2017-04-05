package com.zdsoft.finance.marketing.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.marketing.entity.PropertyOwner;
import com.zdsoft.finance.marketing.repository.PropertyOwnerRepository;
import com.zdsoft.finance.marketing.service.PropertyOwnerService;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: PropertyOwnerServiceImpl.java 
 * @ClassName: PropertyOwnerServiceImpl 
 * @Description: 产权人实现服务类
 * @author zhoushichao 
 * @date 2017年3月14日 下午7:32:47 
 * @version V1.0
 */
@Service("propertyOwnerService")
public class PropertyOwnerServiceImpl extends BaseServiceImpl<PropertyOwner, PropertyOwnerRepository> implements PropertyOwnerService{

	@Override
	public List<PropertyOwner> findByHousePropertyId(String housePropertyId) {
		return this.customReposity.findByHousePropertyId(housePropertyId);
	}
}