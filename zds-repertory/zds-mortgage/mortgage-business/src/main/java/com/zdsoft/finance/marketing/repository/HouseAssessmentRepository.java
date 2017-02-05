package com.zdsoft.finance.marketing.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.marketing.entity.HouseAssessment;

/** 	
* 版权所有：重庆正大华日软件有限公司	
* @Title: HouseAssessmentRepository.java 	
* @Package com.zdsoft.finance.marketing.repository 	
* @Description: TODO	
* @author Administrator 	
* @date 2017年1月15日 下午4:00:25 	
* @version V1.0 	
*/
public interface HouseAssessmentRepository extends CustomRepository<HouseAssessment, String>{
	
	/**
	 * 根据房产id查询评估信息
	 * @param housePropertyId
	 * @return
	 */
	public List<HouseAssessment> findByHousePropertyId(String housePropertyId);
	
}
