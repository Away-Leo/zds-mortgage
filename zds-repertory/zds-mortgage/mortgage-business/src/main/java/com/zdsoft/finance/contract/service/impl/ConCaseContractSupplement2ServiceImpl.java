package com.zdsoft.finance.contract.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.contract.entity.ConCaseContractSupplement2;
import com.zdsoft.finance.contract.repository.ConCaseContractSupplement2Repository;
import com.zdsoft.finance.contract.service.ConCaseContractSupplement2Service;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ConCaseContractSupplement2ServiceImpl.java 
 * @ClassName: ConCaseContractSupplement2ServiceImpl 
 * @Description: 查询合同信息补充2
 * @author wangnengduo
 * @date 2017年3月2日 下午4:22:36 
 * @version V1.0
 */

@Service("conCaseContractSupplement2Service")
public class ConCaseContractSupplement2ServiceImpl extends BaseServiceImpl<ConCaseContractSupplement2, CustomRepository<ConCaseContractSupplement2, String>>
implements ConCaseContractSupplement2Service {
	
	@Autowired
	private ConCaseContractSupplement2Repository conCaseContractSupplement2Repository;

	@Override
	public List<ConCaseContractSupplement2> findAll() {
	List<ConCaseContractSupplement2> ConCaseContractSupplement2s = conCaseContractSupplement2Repository.findAll();
	return ConCaseContractSupplement2s;

	}
	
	/*
     * 
     * 根据案件ID查询合同信息补充表
     */
	@Override
	public List<ConCaseContractSupplement2> getConCaseContractSupplement2ByCaseContractId(String caseContractId) {
	return conCaseContractSupplement2Repository.getConCaseContractSupplement2ByCaseContractId(caseContractId);
	}

	
	/*
     * 
     * 根据案件ID查询合同信息补充表
     
	@Override
	public List<Map<String, Object>> getConCaseContractSupplement2ByCaseContractId(String caseApplyId) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from con_case_contract_supplement2 cccss ");
		sql.append(" where cccss.caseContractId='"+caseApplyId+"' and cccss.isdeleted='F' ");
		return this.customReposity.findListMapByCondition(sql.toString(),new HashMap<String,Object>());
	}*/


}
