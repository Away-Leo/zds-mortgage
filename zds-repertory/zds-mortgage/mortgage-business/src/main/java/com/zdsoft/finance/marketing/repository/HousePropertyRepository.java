package com.zdsoft.finance.marketing.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.marketing.entity.HouseProperty;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:HousePropertyRepository.java
 * @Package:com.zdsoft.finance.marketing.repository
 * @Description:房产实体类
 * @author: zhoushichao
 * @date:2017年1月10日 下午9:47:14
 * @version:v1.0
 */
public interface HousePropertyRepository extends CustomRepository<HouseProperty, String>{
	

	/**
	 * 根据案件id 查询房产信息
	 * @param caseApplyId
	 * @return
	 * @author liuhuan
	 */
	public List<HouseProperty> findByCaseApplyIdAndIsDeletedOrderByCreateTime(String caseApplyId,Boolean b);
	
}
