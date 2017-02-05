package com.zdsoft.finance.marketing.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.customer.entity.BeforeCustomer;
import com.zdsoft.finance.customer.entity.BeforePersonalCustomer;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.repository.CaseApplyBeforeCustomerRepository;
import com.zdsoft.finance.marketing.service.CaseApplyBeforeCustomerService;
import com.zdsoft.framework.core.common.util.ObjectHelper;
@Service("caseApplyBeforeCustomerService")
public class CaseApplyBeforeCustomerServiceImpl extends BaseServiceImpl<CaseApplyBeforeCustomer, CustomRepository<CaseApplyBeforeCustomer, String>>
		implements CaseApplyBeforeCustomerService {
	@Autowired
	private CaseApplyBeforeCustomerRepository caseApplyBeforeCustomerRepository;
	
	@Transactional(rollbackFor=Exception.class)
	@Override
	public void deleteByCaseApplyId(String caseApplyId) throws Exception {
		List<CaseApplyBeforeCustomer> caseApplyBeforeCustomers = this.queryByCaseApplyId(caseApplyId);
		if(ObjectHelper.isNotEmpty(caseApplyBeforeCustomers)){
			this.caseApplyBeforeCustomerRepository.delete(caseApplyBeforeCustomers);
		}
		
	}


	@Override
	public List<CaseApplyBeforeCustomer> queryByCaseApplyIdAndJoinType(String caseApplyId, String joinType) {
		return caseApplyBeforeCustomerRepository.findByCaseApplyIdAndJoinType(caseApplyId, joinType);
	}


	@Override
	public List<CaseApplyBeforeCustomer> queryByCaseApplyId(String caseApplyId) {
		return caseApplyBeforeCustomerRepository.findByCaseApplyId(caseApplyId);
	}


	@Transactional(rollbackFor=Exception.class)
	@Override
	public void deleteByCustomerIdAndCaseApplyId(String customerId, String caseApplyId) {
		CaseApplyBeforeCustomer caseApplyBeforeCustomer = caseApplyBeforeCustomerRepository.findByCaseApplyIdAndBeforeCustomerId(caseApplyId, customerId);
		if(ObjectHelper.isNotEmpty(caseApplyBeforeCustomer)){
			caseApplyBeforeCustomerRepository.delete(caseApplyBeforeCustomer);
		}
		
	}

	@Transactional(rollbackFor=Exception.class)
	@Override
	public void setJoinType(BeforePersonalCustomer mainCustomer, CaseApply caseApply, String joinType)
			throws BusinessException {
		//删除客户参与类型
		this.deleteByCustomerIdAndCaseApplyId(mainCustomer.getId(), caseApply.getId());
		CaseApplyBeforeCustomer caseApplyBeforeCustomer = new CaseApplyBeforeCustomer();
		caseApplyBeforeCustomer.setBeforeCustomer(mainCustomer);
		caseApplyBeforeCustomer.setCaseApply(caseApply);
		caseApplyBeforeCustomer.setJoinType(joinType);
		this.saveEntity(caseApplyBeforeCustomer);
		
	}
	
	@Override
	public List<BeforeCustomer> findCustomerByCaseApplyIdAndJoinType(String caseApplyId, String joinType)
			throws BusinessException {
		if (ObjectHelper.isEmpty(caseApplyId) || ObjectHelper.isEmpty(joinType)) {
			throw new BusinessException("10010004", "传入参数为空！");
		}
		return caseApplyBeforeCustomerRepository.findCustomerByCaseApplyIdAndJoinType(caseApplyId, joinType);
	}


	@SuppressWarnings("static-access")
	@Override
	public List<Map<String,Object>> queryCaseApplyByCustomer(Map<String, Object> condition) {
		try {
			return caseApplyBeforeCustomerRepository.findListMapByCondition(caseApplyBeforeCustomerRepository.sql.toString(), condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询关联信息失败",e);
		}
		return null;
	}


	@Override
	public CaseApplyBeforeCustomer findByCaseApplyIdAndBeforeCustomerId(String caseApplyId, String customerId) {
		try{
			return caseApplyBeforeCustomerRepository.findByCaseApplyIdAndBeforeCustomerId(caseApplyId,customerId);
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("查询关联信息失败",e);
		}
		return null;
	}


	
}



