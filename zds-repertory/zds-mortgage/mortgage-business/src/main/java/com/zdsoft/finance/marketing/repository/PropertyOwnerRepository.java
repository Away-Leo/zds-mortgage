package com.zdsoft.finance.marketing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.marketing.entity.PropertyOwner;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:PropertyOwnerRepository.java
 * @Package:com.zdsoft.finance.marketing.repository
 * @Description:产权人实现类
 * @author: zhoushichao
 * @date:2017年1月10日 下午9:49:12
 * @version:v1.0
 */
public interface PropertyOwnerRepository extends CustomRepository<PropertyOwner, String>{

	/**
	 * 根据房产id查询抵押情况
	 * @param housePropertyId
	 * @return
	 * @author zhoushicaho
	 */
	@Query(" from PropertyOwner where housePropertyId=:housePropertyId and isDeleted='F' order by createTime desc")
	public List<PropertyOwner> findByHousePropertyId(@Param(value="housePropertyId")String housePropertyId);
}
