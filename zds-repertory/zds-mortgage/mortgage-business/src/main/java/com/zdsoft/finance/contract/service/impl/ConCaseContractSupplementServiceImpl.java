package com.zdsoft.finance.contract.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.contract.entity.ConCaseContractSupplement;
import com.zdsoft.finance.contract.repository.ConCaseContractSupplementRepository;
import com.zdsoft.finance.contract.service.ConCaseContractSupplementService;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ConCaseContractSupplementServiceImpl.java 
 * @ClassName: ConCaseContractSupplementServiceImpl 
 * @Description: 查询合同信息补充
 * @author wangnengduo
 * @date 2017年3月2日 下午4:16:38 
 * @version V1.0
 */
@Service("conCaseContractSupplementService")
public class ConCaseContractSupplementServiceImpl extends BaseServiceImpl<ConCaseContractSupplement, CustomRepository<ConCaseContractSupplement, String>>
implements ConCaseContractSupplementService {
	
	@Autowired
	private ConCaseContractSupplementRepository conCaseContractSupplementRepository;

	@Override
	public List<ConCaseContractSupplement> findAll() {
	List<ConCaseContractSupplement> ConCaseContractSupplements = conCaseContractSupplementRepository.findAll();
	return ConCaseContractSupplements;

	}
	/*
     * 
     * 根据案件ID查询合同信息补充表
     */
	@Override
	public List<ConCaseContractSupplement> getConCaseContractSupplementByCaseContractId(String caseContractId) {
	return conCaseContractSupplementRepository.getConCaseContractSupplementByCaseContractId(caseContractId);
	}

	
	
	/*@Override
	public List<Map<String, Object>> getConCaseContractSupplementByCaseContractId(String caseApplyId) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from con_case_contract_supplement cccs ");
		sql.append(" where cccs.caseContractId='"+caseApplyId+"' and cccs.isdeleted='F' ");
		return this.customReposity.findListMapByCondition(sql.toString(),new HashMap<String,Object>());
	}*/

}
