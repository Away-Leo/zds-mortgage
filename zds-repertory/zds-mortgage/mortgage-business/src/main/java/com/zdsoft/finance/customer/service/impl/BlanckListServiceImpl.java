package com.zdsoft.finance.customer.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.customer.entity.BeforeCustomer;
import com.zdsoft.finance.customer.entity.BeforePersonalAssociation;
import com.zdsoft.finance.customer.entity.BlanckList;
import com.zdsoft.finance.customer.repository.BlanckListRepository;
import com.zdsoft.finance.customer.service.BeforePersonalAssociationService;
import com.zdsoft.finance.customer.service.BlanckListService;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.entity.HouseProperty;
import com.zdsoft.finance.marketing.entity.PropertyOwner;
import com.zdsoft.finance.marketing.service.CaseApplyBeforeCustomerService;
import com.zdsoft.finance.marketing.service.HousePropertyService;
import com.zdsoft.finance.rule.enums.RuleApprovalFinal;
import com.zdsoft.framework.core.common.domain.BaseEntity;
import com.zdsoft.framework.core.common.util.ObjectHelper;

import cnfh.VerifyResult;
import cnfh.cnfh_rule.BeforeCustomerList;
import cnfh.cnfh_rule.Calldrools;
import cnfh.cnfh_rule.PFBeforeCustomer;

@Service
public class BlanckListServiceImpl extends BaseServiceImpl<BlanckList, BlanckListRepository>  implements BlanckListService {
	
	@Autowired
	CaseApplyBeforeCustomerService caseApplyBeforeCustomerService;
	@Autowired
	BeforePersonalAssociationService beforePersonalAssociationService;
	@Autowired
	HousePropertyService housePropertyService;
	
	@Autowired
	BlanckListRepository blanckListRepository;
	
	
    @Override
    public List<BlanckList> findByALL() throws BusinessException {
        StringBuffer hql = new StringBuffer(" from BlanckList t where 1=1");
        hql.append(" and t.isDeleted =:isDeleted");
        Map<String, Object> condition = new HashMap<>();
        condition.put("isDeleted", !BaseEntity.DELETED);
        List<BlanckList> list = this.customReposity.findByHql(hql.toString(), condition);
        return list;
    }

	@Override
	public Boolean checkBlankList(String caseApplyId) throws Exception {
		if(ObjectHelper.isEmpty(caseApplyId)){
			throw new BusinessException("10010004", "案件id不能为空！");
		}
		boolean falg = false ;
		//客户关系
        List<CaseApplyBeforeCustomer> customerList = caseApplyBeforeCustomerService.queryByCaseApplyId(caseApplyId);
        //设置客户
        List<PFBeforeCustomer> beforeCustomerlist= new ArrayList<PFBeforeCustomer>();
        for(CaseApplyBeforeCustomer caseApplyBeforeCustomer : customerList) {
        	BeforeCustomer beforeCustomer = caseApplyBeforeCustomer.getBeforeCustomer();
        	PFBeforeCustomer beCus = new PFBeforeCustomer();
			beCus.setCredentialNo(beforeCustomer.getCredentialNo());
			beCus.setCustomerName(beforeCustomer.getCustomerName());
			beforeCustomerlist.add(beCus);
			//客户关系（配偶等信息）（由于规则判断只要是人都查，配偶等信息可能不是共借人和担保人，故单独查询一次）
			List<BeforePersonalAssociation> associations = beforePersonalAssociationService.queryCustomerId(beforeCustomer.getId());
			for(BeforePersonalAssociation association : associations){
				BeforeCustomer customer2 = association.getBeforePersonalCusomer();
				PFBeforeCustomer beCus2 = new PFBeforeCustomer();
				beCus2.setCredentialNo(customer2.getCredentialNo());
				beCus2.setCustomerName(customer2.getCustomerName());
				beforeCustomerlist.add(beCus2);
			}
		}
        //获取产权人
		List<HouseProperty> houseProperties = housePropertyService.findByCaseApplyId(caseApplyId);
		for(HouseProperty houseProperty:houseProperties){
			//获取产权人信息（和上面客户信息重的几率很大 ）
			List<PropertyOwner> owners = houseProperty.getPropertyOwnerList();
			for(PropertyOwner owner : owners){
				PFBeforeCustomer beCus = new PFBeforeCustomer();
				beCus.setCredentialNo(owner.getCredentialNo());
				beCus.setCustomerName(owner.getOwnerName());
				beforeCustomerlist.add(beCus);
			}
		}
        
        BeforeCustomerList beforeCustomers = new BeforeCustomerList();
        beforeCustomers.setBeforeCustomers(beforeCustomerlist);
        Calldrools drools = new Calldrools();
        VerifyResult vr1 = new VerifyResult();
        vr1.setVrId(RuleApprovalFinal.RUN_NORMAL.getValue());
    	VerifyResult vr = drools.getBlacklistResult(beforeCustomers,vr1);
    	if(VerifyResult.REFUSE.equals(vr.getVrResult())){
    		falg = true;
		}
        return falg;
	}

	@Override
	public BlanckList findByCredentialNo(String credentialNo) {
		return this.customReposity.findByCredentialNo(credentialNo);
	}
	
	@Override
	public List<BlanckList> findByCredentiaTypeAndCredentialNo(String credentiaType,String credentialNo){
		return  blanckListRepository.findByCredentiaTypeAndCredentialNoAndIsDeleted(credentiaType, credentialNo,false);
	}
}
