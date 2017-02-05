package com.zdsoft.finance.marketing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.marketing.entity.CaseTask;

/** 	
* 版权所有：重庆正大华日软件有限公司	
* @Title: CaseTaskrRepository.java 	
* @Package com.zdsoft.finance.marketing.repository 	
* @Description: 派工	
* @author liuhuan 	
* @date 2017年1月18日 下午8:39:51 	
* @version V1.0 	
*/
public interface CaseTaskRepository extends CustomRepository<CaseTask, String>{
	
	/**
	 * 根据案件id 查询 派工人员信息
	 * @param caseApplyId
	 * @return
	 */
	@Query(" from CaseTask where isDeleted='F' and caseApplyId=:caseApplyId order by createTime desc ")
	public List<CaseTask> findByCaseApplyId(@Param(value="caseApplyId")String caseApplyId);
	
}
