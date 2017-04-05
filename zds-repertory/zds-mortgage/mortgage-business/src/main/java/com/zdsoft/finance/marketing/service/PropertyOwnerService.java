package com.zdsoft.finance.marketing.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.marketing.entity.PropertyOwner;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: PropertyOwnerService.java 
 * @ClassName: PropertyOwnerService 
 * @Description: 产权人服务类
 * @author zhoushichao 
 * @date 2017年3月14日 下午7:41:46 
 * @version V1.0
 */
public interface PropertyOwnerService extends BaseService<PropertyOwner>{

	/**
	 * 
	 * @Title: findByHousePropertyId 
	 * @Description: 根据房产id查询产权人情况
	 * @author zhoushichao 
	 * @param housePropertyId  房产id
	 * @return
	 */
	public List<PropertyOwner> findByHousePropertyId(String housePropertyId);
}
