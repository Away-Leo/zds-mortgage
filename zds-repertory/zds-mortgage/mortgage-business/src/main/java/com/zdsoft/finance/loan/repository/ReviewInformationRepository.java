package com.zdsoft.finance.loan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.loan.entity.ReviewInformation;

/** 	
* 版权所有：重庆正大华日软件有限公司	
* @Title: ReviewInformationRepository.java 	
* @Package com.zdsoft.finance.loan.repository 	
* @Description:  合规复核中的复核信息	
* @author liuhuan 	
* @date 2017年1月18日 上午9:51:56 	
* @version V1.0 	
*/
public interface ReviewInformationRepository extends CustomRepository<ReviewInformation, String>{
	
	/**
	 * 根据三级标示的选择查询复核信息
	 * @return
	 */
	@Query(" from ReviewInformation where firstMark=:firstMark and secondMark=:secondMark and thirdMark=:thirdMark ")
	public List<ReviewInformation> findAllByFirstMarkAndSecondMarkAndThirdMark(@Param("firstMark")String firstMark, @Param("secondMark")String secondMark, @Param("thirdMark")String thirdMark);

	/**
	 * 查询所有复核信息
	 */
	@Query(" from ReviewInformation where 1=1 ")
	public List<ReviewInformation> findAllInfo();
	
}
