package com.zdsoft.finance.contract.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.contract.entity.ConCaseContractSupplement;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ConCaseContractSupplementService.java 
 * @ClassName: ConCaseContractSupplementService 
 * @Description: 合同信息补充
 * @author wangnengduo
 * @date 2017年3月2日 下午4:20:51 
 * @version V1.0
 */
public interface ConCaseContractSupplementService extends BaseService<ConCaseContractSupplement>{
	
	/**
	 * 查询合同信息补充
	 * @return
	 */
	public List<ConCaseContractSupplement> findAll();
	
	/**
	 * 根据案件ID查询合同信息补充表
	 * @param caseApplyId
	 * @return
	 */
	public List<ConCaseContractSupplement>  getConCaseContractSupplementByCaseContractId(String caseContractId);
	
	/**
	 * 根据案件ID查询合同信息补充表
	 * @param caseApplyId
	 * @return
	 */
	/*List<Map<String, Object>> getConCaseContractSupplementByCaseContractId(String caseApplyId) throws Exception;*/

}
