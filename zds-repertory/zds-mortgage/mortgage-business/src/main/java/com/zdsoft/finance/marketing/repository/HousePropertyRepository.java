package com.zdsoft.finance.marketing.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.marketing.entity.HouseProperty;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HousePropertyRepository.java 
 * @ClassName: HousePropertyRepository 
 * @Description: 房产Repository 
 * @author zhoushichao 
 * @date 2017年3月14日 下午6:03:07 
 * @version V1.0
 */
public interface HousePropertyRepository extends CustomRepository<HouseProperty, String>{
	

	/**
	 * 
	 * @Title: findByCaseApplyIdOrderByCreateTime 
	 * @Description: 根据案件id 查询房产信息
	 * @author zhoushichao 
	 * @param caseApplyId  案件id
	 * @return
	 */
	public List<HouseProperty> findByCaseApplyIdOrderByCreateTime(String caseApplyId);
	
}
