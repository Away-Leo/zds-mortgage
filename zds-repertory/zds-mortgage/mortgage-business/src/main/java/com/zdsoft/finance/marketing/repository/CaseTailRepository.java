package com.zdsoft.finance.marketing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.marketing.entity.CaseTail;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CaseTailRepository.java 
 * @ClassName: CaseTailRepository 
 * @Description: 案件跟踪Repository 
 * @author zhoushichao 
 * @date 2017年3月14日 下午6:01:32 
 * @version V1.0
 */
public interface CaseTailRepository extends CustomRepository<CaseTail, String>{
	
	/**
	 * 
	 * @Title: findByCaseApplyId 
	 * @Description: 根据案件Id获取营销跟踪信息
	 * @author caixiekang 
	 * @param caseApplyId
	 * @return
	 */
	@Query("from CaseTail c where c.caseApplyId =:caseApplyId and c.isDeleted = false order by c.tailDate desc")
	List<CaseTail> findByCaseApplyId(@Param("caseApplyId")String caseApplyId);

}
