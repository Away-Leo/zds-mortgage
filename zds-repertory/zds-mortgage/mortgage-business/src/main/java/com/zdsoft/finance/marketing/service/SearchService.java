package com.zdsoft.finance.marketing.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.marketing.entity.Search;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: SearchService.java 
 * @ClassName: SearchService 
 * @Description: 房产产权状态实现类
 * @author zhoushichao 
 * @date 2017年3月8日 下午4:51:32 
 * @version V1.0
 */
public interface SearchService extends BaseService<Search>{

	/**
	 * 
	 * @Title: findByHouseProperyId 
	  * @Description: 通过房产Id查找房产产权状态
	 * @author zhoushichao 
	 * @param housePropertyId 房产Id
	 * @return
	 * @throws BusinessException
	 */
   public Search findByHousePropertyId(String housePropertyId) throws BusinessException;
}