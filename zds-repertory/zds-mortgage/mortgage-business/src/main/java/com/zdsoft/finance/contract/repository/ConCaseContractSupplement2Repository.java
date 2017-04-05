package com.zdsoft.finance.contract.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.contract.entity.ConCaseContractSupplement2;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ConCaseContractSupplement2Repository.java 
 * @ClassName: ConCaseContractSupplement2Repository 
 * @Description: 合同信息补充
 * @author wangnengduo
 * @date 2017年3月2日 下午4:10:31 
 * @version V1.0
 */
public interface ConCaseContractSupplement2Repository  extends CustomRepository<ConCaseContractSupplement2,String>{
	/**
	 * 查询合同信息补充
	 */
	public List<ConCaseContractSupplement2> findAll();
	
	/**
	 * 
	 * @Title: getConContractTplList 
	 * @Description: 根据案件ID查询合同信息补充表
	 * @author wangnengduo
	 * @param tplApplyId
	 * @return
	 */
	@Query(" from ConCaseContractSupplement2 where isDeleted = false and  (caseContractId = :caseContractId)")
	public List<ConCaseContractSupplement2> getConCaseContractSupplement2ByCaseContractId(@Param("caseContractId") String caseContractId);
}
