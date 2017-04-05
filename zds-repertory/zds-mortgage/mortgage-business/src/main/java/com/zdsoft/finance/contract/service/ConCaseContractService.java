package com.zdsoft.finance.contract.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.contract.entity.ConCaseContract;
import com.zdsoft.finance.contract.entity.ConCaseContractDetail;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ConCaseContractService.java 
 * @ClassName: ConCaseContractService 
 * @Description: 案件合同
 * @author wangnengduo
 * @date 2017年2月28日 上午11:01:56 
 * @version V1.0
 */
public interface ConCaseContractService  extends BaseService<ConCaseContract> {
	/**
	 * 
	 * @Title: findByCaseApplyId 
	 * @Description: 通过案件id查询案件合同
	 * @author xj 
	 * @param caseApplyId 案件合同
	 * @return
	 * @throws Exception 
	 */
	public ConCaseContract findByCaseApplyId(String caseApplyId) throws Exception;
	
	/*
	 * 根据案件ID获取案件基本信息(合同套打)
	 */
	List<Map<String, Object>> getConCaseContractById(String caseApplyId) throws Exception;
    /*
     * 
     * 根据案件ID获取合同明细中的所有相关合同
     */
	List<Map<String, Object>> getContractDetailByCaseContractId(String caseContractId) throws Exception;
	
	/*
     * 
     * 根据案件ID获取合同明细中的所有相关合同
     */
	List<Map<String, Object>> getContractPrintTplByCaseApplyId(String caseApplyId) throws Exception;

	/**
	 * 获取案件实体状态情况统计
	 * @return
	 * @throws Exception
	 */
	public Page<Map<String,Object>> getContractCancelReport(PageRequest pageable,List<QueryObj> queryObjs) throws Exception;

	public List<Map<String,Object>> getMaterialCountByCondition(String capitaIdC,String contractTypeC,String contractNameC,String fileNo);


	public void procContractCancel(List<Map<String, Object>> maps);

	/**
	 *
	 * @Title: getContractDetailByContractId
	 * @Description:  根据合同ID查询合同明细
	 * @author denglw
	 * @param contractId
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> getContractDetailByContractId(String contractId)throws Exception;

	/**
	 *
	 * @Title: getConCaseContractDetailById
	 * @Description: 根据合同明细ID查询合同明细
	 * @author denglw
	 * @param detailId
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getConCaseContractDetailById(String detailId)throws Exception;
}

