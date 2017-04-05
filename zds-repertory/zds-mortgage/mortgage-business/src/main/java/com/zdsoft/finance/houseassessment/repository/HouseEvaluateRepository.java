package com.zdsoft.finance.houseassessment.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.houseassessment.entity.HouseEvaluate;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HouseEvaluateRepository.java 
 * @ClassName: HouseEvaluateRepository 
 * @Description: HouseEvaluateRepository
 * @author caixiekang 
 * @date 2017年2月27日 下午8:04:26 
 * @version V1.0
 */
public interface HouseEvaluateRepository extends CustomRepository<HouseEvaluate, String>{
	
	/**
	 * 
	 * @Title: findByHousePropertyId 
	 * @Description: 根据房产id获得HouseEvaluate
	 * @author caixiekang 
	 * @param housePropertyId 房产id
	 * @return
	 */
	@Query("from HouseEvaluate h where h.bizId=:housePropertyId and h.isDeleted=false")
	public HouseEvaluate findByHousePropertyId(@Param("housePropertyId")String housePropertyId);

}
