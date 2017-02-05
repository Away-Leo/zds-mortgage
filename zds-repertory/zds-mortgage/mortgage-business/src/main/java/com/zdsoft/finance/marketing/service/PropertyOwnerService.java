package com.zdsoft.finance.marketing.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.marketing.entity.PropertyOwner;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:PropertyOwnerService.java
 * @Package:com.zdsoft.finance.marketing.service
 * @Description:产权人服务类
 * @author: zhoushichao
 * @date:2017年1月10日 下午9:50:30
 * @version:v1.0
 */
public interface PropertyOwnerService extends BaseService<PropertyOwner>{

	/**
	 * 
	 * 保存或修改产权人信息
	 *
	 * @author zhoushichao
	 * @param propertyOwner
	 * @param housePropertyId
	 * @return
	 * @throws Exception
	 */
	public List<PropertyOwner> saveOrUpdatePropertyOwner(List<PropertyOwner> propertyOwner,String housePropertyId) throws Exception;
	
	/**
	 * 根据房产id查询产权人情况
	 * @author zhoushichao
	 * @param housePropertyId
	 * @return
	 */
	public List<PropertyOwner> findByHousePropertyId(String housePropertyId);
}
