package com.zdsoft.finance.marketing.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.customer.entity.BeforeCustomer;
import com.zdsoft.finance.customer.entity.BeforePersonalCustomer;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.repository.CaseApplyBeforeCustomerRepository;
import com.zdsoft.finance.marketing.service.CaseApplyBeforeCustomerService;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CaseApplyBeforeCustomerServiceImpl.java 
 * @ClassName: CaseApplyBeforeCustomerServiceImpl 
 * @Description: 贷前申请人（案件的担保人、合作人）service实现
 * @author xj 
 * @date 2017年3月13日 上午11:20:43 
 * @version V1.0
 */
@Service("caseApplyBeforeCustomerService")
public class CaseApplyBeforeCustomerServiceImpl extends BaseServiceImpl<CaseApplyBeforeCustomer, CaseApplyBeforeCustomerRepository>
		implements CaseApplyBeforeCustomerService {
	
	@Transactional(rollbackFor=Exception.class)
	@Override
	public void deleteByCaseApplyId(String caseApplyId) throws Exception {
		List<CaseApplyBeforeCustomer> caseApplyBeforeCustomers = this.queryByCaseApplyId(caseApplyId);
		if(ObjectHelper.isNotEmpty(caseApplyBeforeCustomers)){
			this.customReposity.delete(caseApplyBeforeCustomers);
		}
		
	}


	@Override
	public List<CaseApplyBeforeCustomer> queryByCaseApplyIdAndJoinType(String caseApplyId, String joinType) {
		return this.customReposity.findByCaseApplyIdAndJoinType(caseApplyId, joinType);
	}


	@Override
	public List<CaseApplyBeforeCustomer> queryByCaseApplyId(String caseApplyId) {
		return this.customReposity.findByCaseApplyId(caseApplyId);
	}


	@Transactional(rollbackFor=Exception.class)
	@Override
	public void deleteByCustomerIdAndCaseApplyId(String customerId, String caseApplyId) {
		CaseApplyBeforeCustomer caseApplyBeforeCustomer = this.customReposity.findByCaseApplyIdAndBeforeCustomerId(caseApplyId, customerId);
		if(ObjectHelper.isNotEmpty(caseApplyBeforeCustomer)){
			this.customReposity.delete(caseApplyBeforeCustomer);
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
		return this.customReposity.findCustomerByCaseApplyIdAndJoinType(caseApplyId, joinType);
	}


	@Override
	public List<Map<String,Object>> queryCaseApplyByCustomer(Map<String, Object> condition) throws Exception{
		StringBuffer sql = new StringBuffer(" SELECT cbc.joinType   joinType, ");
		sql.append(" cbp.customerName   customerName, ");
		sql.append(" mca.caseApplyCode   caseApplyCode, ");
		sql.append(" mhp.floorAge  floorAge, ");
		sql.append(" mhp.area   area, ");
		sql.append(" mpi.pledgeType   pledgeType, ");
		sql.append(" mhp.estateProperties estateProperties, ");
		sql.append(" mpi.pledgeAmout   pledgeAmout ");
		sql.append(" FROM mkt_collateral   mc, ");
		sql.append(" mkt_house_property   mhp, ");
		sql.append(" mkt_pledge_info   mpi, ");
		sql.append(" case_before_customer   cbc ");
		sql.append(" LEFT JOIN cust_before_customer   cbp ON cbc.customerId = cbp.id ");
		sql.append(" LEFT JOIN mkt_case_apply   mca ON cbc.caseApplyId = mca.id ");
		sql.append(" WHERE 1 = 1 ");
		sql.append(" AND mc.caseApplyId = mca.id ");
		sql.append(" AND mc.collateralType = 'HouseProperty' ");
		sql.append(" AND mhp.id = mc.id' ");
		sql.append(" AND mpi.housePropertyId = mhp.id ");
		sql.append("  AND mpi.id =:customerId ");
		return this.customReposity.findListMapByCondition(sql.toString(), condition);
	}


	@Override
	public CaseApplyBeforeCustomer findByCaseApplyIdAndBeforeCustomerId(String caseApplyId, String customerId) {
		try{
			return this.customReposity.findByCaseApplyIdAndBeforeCustomerId(caseApplyId,customerId);
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("查询关联信息失败",e);
		}
		return null;
	}

	@Override
	public List<CaseApplyBeforeCustomer> findCustomerNameByLike(String caseApplyId, String customerName)
			throws BusinessException {
		return this.customReposity.findCustomerNameByLike(caseApplyId, customerName);
	}


	@Override
	public CaseApplyBeforeCustomer findByBeforeCustomerId(String customerId) throws Exception {
		return this.customReposity.findByBeforeCustomerId(customerId);
	}
}