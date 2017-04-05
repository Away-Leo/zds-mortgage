package com.zdsoft.finance.marketing.repository;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.marketing.entity.Search;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: SearchRepository.java 
 * @ClassName: SearchRepository 
 * @Description: 房产产权状态repository
 * @author zhoushichao 
 * @date 2017年3月8日 下午4:52:19 
 * @version V1.0
 */
public interface SearchRepository extends CustomRepository<Search, String>{

	/**
	 * 
	 * @Title: findByHouseProperyId 
	 * @Description: 通过房产Id查找房产产权状态
	 * @author zhoushichao 
	 * @param housePropertyId 房产Id
	 * @return
	 */			  
    public Search findByHousePropertyId(String housePropertyId);
}