package com.zdsoft.finance.contract.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.contract.entity.ConCaseContractSupplement;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ConCaseContractSupplementRepository.java 
 * @ClassName: ConCaseContractSupplementRepository 
 * @Description: 合同信息补充
 * @author wangnengduo
 * @date 2017年3月2日 下午4:10:09 
 * @version V1.0
 */
public interface ConCaseContractSupplementRepository  extends CustomRepository<ConCaseContractSupplement,String>{
	/**
	 * 查询合同信息补充
	 */
	public List<ConCaseContractSupplement> findAll();
	
	/**
	 * 
	 * @Title: getConContractTplList 
	 * @Description: 根据案件ID查询合同信息补充表
	 * @author wangnengduo
	 * @param tplApplyId
	 * @return
	 */
	@Query(" from ConCaseContractSupplement where isDeleted = false and  (caseContractId = :caseContractId)")
	public List<ConCaseContractSupplement> getConCaseContractSupplementByCaseContractId(@Param("caseContractId") String caseContractId);
}
