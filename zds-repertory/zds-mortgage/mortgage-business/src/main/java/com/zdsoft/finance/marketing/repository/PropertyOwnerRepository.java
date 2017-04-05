package com.zdsoft.finance.marketing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.marketing.entity.PropertyOwner;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: PropertyOwnerRepository.java 
 * @ClassName: PropertyOwnerRepository 
 * @Description: 产权人Repository
 * @author zhoushichao 
 * @date 2017年3月14日 下午6:04:30 
 * @version V1.0
 */
public interface PropertyOwnerRepository extends CustomRepository<PropertyOwner, String>{

	/**
	 * 
	 * @Title: findByHousePropertyId 
	 * @Description: 根据房产id查询抵押情况
	 * @author zhoushichao 
	 * @param housePropertyId 房产id
	 * @return
	 */
	@Query(" from PropertyOwner where housePropertyId=:housePropertyId and isDeleted='F' order by createTime desc")
	public List<PropertyOwner> findByHousePropertyId(@Param(value="housePropertyId")String housePropertyId);
}
