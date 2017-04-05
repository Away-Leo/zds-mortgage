package com.zdsoft.finance.marketing.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.marketing.entity.HouseAssessment;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HouseAssessmentRepository.java 
 * @ClassName: HouseAssessmentRepository 
 * @Description: 房产评估信息Repository
 * @author zhoushichao 
 * @date 2017年3月14日 下午6:02:35 
 * @version V1.0
 */
public interface HouseAssessmentRepository extends CustomRepository<HouseAssessment, String>{
	
	/**
	 * 
	 * @Title: findByHousePropertyId 
	 * @Description: 根据房产id查询评估信息
	 * @author zhoushichao 
	 * @param housePropertyId 房产id
	 * @return
	 */
	public List<HouseAssessment> findByHousePropertyId(String housePropertyId);
	
}
