package com.zdsoft.finance.contract.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.contract.entity.ConCaseContractSupplement2;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ConCaseContractSupplement2Service.java 
 * @ClassName: ConCaseContractSupplement2Service 
 * @Description: 查询合同信息补充2
 * @author wangnengduo
 * @date 2017年3月2日 下午4:22:02 
 * @version V1.0
 */
public interface ConCaseContractSupplement2Service  extends BaseService<ConCaseContractSupplement2>{

	/**
	 * 查询合同信息补充2
	 * @return
	 */
	public List<ConCaseContractSupplement2> findAll();
	
	/**
	 * 根据案件ID查询合同信息补充表
	 * @param caseApplyId
	 * @return
	 */
	public List<ConCaseContractSupplement2>  getConCaseContractSupplement2ByCaseContractId(String caseContractId);
	
	/**
	 * 根据案件ID查询合同信息补充表
	 * @param caseApplyId
	 * @return
	 */
	/*List<Map<String, Object>> getConCaseContractSupplement2ByCaseContractId(String caseApplyId) throws Exception;*/
}
