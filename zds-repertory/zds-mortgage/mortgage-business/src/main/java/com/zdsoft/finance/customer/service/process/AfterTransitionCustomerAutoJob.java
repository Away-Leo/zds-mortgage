package com.zdsoft.finance.customer.service.process;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.bpm.annotation.client.AutoJob;
import com.zdsoft.finance.customer.entity.AfterAddress;
import com.zdsoft.finance.customer.entity.AfterContact;
import com.zdsoft.finance.customer.entity.AfterPersonaAssociation;
import com.zdsoft.finance.customer.entity.AfterPersonalCustomer;
import com.zdsoft.finance.customer.entity.AfterWorkUnit;
import com.zdsoft.finance.customer.entity.BeforeAddress;
import com.zdsoft.finance.customer.entity.BeforeContact;
import com.zdsoft.finance.customer.entity.BeforeCustomer;
import com.zdsoft.finance.customer.entity.BeforePersonalAssociation;
import com.zdsoft.finance.customer.entity.BeforePersonalCustomer;
import com.zdsoft.finance.customer.entity.BeforeWorkUnit;
import com.zdsoft.finance.customer.service.AfterAddressService;
import com.zdsoft.finance.customer.service.AfterContactService;
import com.zdsoft.finance.customer.service.AfterPersonaAssociationService;
import com.zdsoft.finance.customer.service.AfterPersonalCustomerService;
import com.zdsoft.finance.customer.service.AfterWorkUnitService;
import com.zdsoft.finance.customer.service.BeforeAddressService;
import com.zdsoft.finance.customer.service.BeforeContactService;
import com.zdsoft.finance.customer.service.BeforePersonalAssociationService;
import com.zdsoft.finance.customer.service.BeforeWorkUnitService;
import com.zdsoft.finance.marketing.entity.CaseApplyAfterCustomer;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.service.CaseApplyAfterCustomerService;
import com.zdsoft.finance.marketing.service.CaseApplyBeforeCustomerService;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.dto.DelegateExecution;
import com.zdsoft.framework.core.common.service.JavaDelegate;
import com.zdsoft.framework.core.common.util.ObjectHelper;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AfterTransitionCustomerAutoJob.java 
 * @ClassName: AfterTransitionCustomerAutoJob 
 * @Description: 贷前客户转换为贷后客户
 * @author xj 
 * @date 2017年2月24日 下午4:24:47 
 * @version V1.0
 */
@Service
@AutoJob(label = "贷前客户转换为贷后客户", resource = "com.zdsoft.finance.customer.service.process.postLoanTransitionCustomer.autoJob")
public class AfterTransitionCustomerAutoJob implements JavaDelegate {
	@Log
	private Logger logger;
	@Autowired
	private AfterPersonaAssociationService afterPersonaAssociationService;
	@Autowired
	private CaseApplyBeforeCustomerService caseApplyBeforeCustomerService;
	@Autowired 
	private CaseApplyAfterCustomerService caseApplyAfterCustomerService;
	@Autowired
	private BeforePersonalAssociationService beforePersonalAssociationService;
	@Autowired
	private AfterPersonalCustomerService afterPersonalCustomerService;
	@Autowired
	private BeforeAddressService beforeAddressService;
	@Autowired
	private AfterAddressService afterAddressService;
	@Autowired
	private BeforeContactService beforeContactService;
	@Autowired
	private AfterContactService afterContactService;
	@Autowired
	private BeforeWorkUnitService beforeWorkUnitService;
	@Autowired
	private AfterWorkUnitService afterWorkUnitService;
	@Transactional(rollbackFor=Exception.class)
	@Override
	public void execute(DelegateExecution delegateexecution) throws Exception {
		String caseApplyId = (String) delegateexecution.getVariable("projectId");
		logger.info("caseApplyId:"+caseApplyId);
		logger.info("---------------------------开始转换客户--------------------------------");
		//用于复制参与类型和关系（key：贷前客户id，value：对应的贷后客户）
		Map<String, AfterPersonalCustomer> record = new HashMap<String,AfterPersonalCustomer>();
		//这个案件有关的所有客户(去重复)
		Set<BeforePersonalCustomer> allOldCustomer = new HashSet<BeforePersonalCustomer>();
		//查询参与类型
		List<CaseApplyBeforeCustomer> caseApplyBeforeCustomers = caseApplyBeforeCustomerService.queryByCaseApplyId(caseApplyId);
		for (CaseApplyBeforeCustomer caseApplyBeforeCustomer : caseApplyBeforeCustomers) {
			BeforeCustomer beforeCustomer = caseApplyBeforeCustomer.getBeforeCustomer();
			if(beforeCustomer instanceof BeforePersonalCustomer){
				BeforePersonalCustomer bc = (BeforePersonalCustomer) beforeCustomer;
				//添加参与类型里面的客户
				allOldCustomer.add(bc);
				//添加关系里面的客户
				List<BeforePersonalAssociation> beforePersonalAssociations = beforePersonalAssociationService.queryCustomerId(bc.getId());
				if(ObjectHelper.isNotEmpty(beforePersonalAssociations)){
					for (BeforePersonalAssociation ba : beforePersonalAssociations) {
						BeforePersonalCustomer beforePersonalCusomer = ba.getBeforePersonalCusomer();
						allOldCustomer.add(beforePersonalCusomer);
					}
				}
			}
		}
		//复制客户
		Iterator<BeforePersonalCustomer> iterator = allOldCustomer.iterator();
		while(iterator.hasNext()){
			BeforePersonalCustomer bc = iterator.next();
			//设置当前添加的客户为最新客户，便于客户管理列表查询
			List<AfterPersonalCustomer> oldPers = afterPersonalCustomerService.findByCredentialTypeAndCredentialNoAndIsLatest(bc.getCredentialType(), bc.getCredentialNo(), true);
			if(ObjectHelper.isNotEmpty(oldPers)){
				for (AfterPersonalCustomer postLoanPersonal : oldPers) {
					postLoanPersonal.setIsLatest(false);
					afterPersonalCustomerService.updateEntity(postLoanPersonal);
				}
			}
			//1.复制客户
			AfterPersonalCustomer postLoanPersonal = this.copyCustomer(bc);
			//记录
			record.put(bc.getId(), postLoanPersonal);
			//2.复制客户地址
			this.copyAddress(bc,record.get(bc.getId()));
			//3.复制联系方式
			this.copyContact(bc,record.get(bc.getId()));
			//4.复制工作单位
			this.copyWorkUnit(bc,record.get(bc.getId()));
		}
		//5.复制参与类型
		for (CaseApplyBeforeCustomer caseApplyBeforeCustomer : caseApplyBeforeCustomers) {
			CaseApplyAfterCustomer afterCustomer = new CaseApplyAfterCustomer();
			BeanUtils.copyProperties(caseApplyBeforeCustomer, afterCustomer, new String[]{"id","version","updateBy","updateOrgCd"});
			afterCustomer.setAfterCustomer(record.get(caseApplyBeforeCustomer.getBeforeCustomer().getId()));
			caseApplyAfterCustomerService.saveEntity(afterCustomer);
		}
		//6.复制关系类型
		iterator = allOldCustomer.iterator();
		while(iterator.hasNext()){
			BeforePersonalCustomer bc = iterator.next();
			//查询关系
			List<BeforePersonalAssociation> associations = beforePersonalAssociationService.queryCustomerId(bc.getId());
			if(ObjectHelper.isNotEmpty(associations)){
				for (BeforePersonalAssociation ba : associations) {
					AfterPersonaAssociation pa = new AfterPersonaAssociation();
					BeanUtils.copyProperties(ba, pa, new String[]{"id","version","updateBy","updateOrgCd","customerId"});
					pa.setCustomerId(record.get(ba.getCustomerId()).getId());
					pa.setAfterPersonalCustomer(record.get(ba.getBeforePersonalCusomer().getId()));
					pa = afterPersonaAssociationService.saveEntity(pa);
				}
			}
		}
		//查询关系类型 复制关系
		logger.info("-------------------客户转换成功--------------------------");
		
	}
	/**
	 * 
	 * @Title: copyWorkUnit 
	 * @Description: 复制工作单位
	 * @author xj 
	 * @param bc 贷前客户
	 * @param pp 贷后客户
	 * @throws Exception 
	 */
	private void copyWorkUnit(BeforePersonalCustomer bc, AfterPersonalCustomer pp) throws Exception {
		List<BeforeWorkUnit> beforeWorkUnits = beforeWorkUnitService.queryByCustomerId(bc.getId());
		if(ObjectHelper.isNotEmpty(beforeWorkUnits)){
			for (BeforeWorkUnit bu : beforeWorkUnits) {
				AfterWorkUnit plu = new AfterWorkUnit();
				BeanUtils.copyProperties(bu, plu, new String[]{"id","version","updateBy","updateOrgCd","customerId"});
				plu.setCustomerId(pp.getId());
				afterWorkUnitService.saveEntity(plu);
			}
		}
		
	}
	/**
	 * 
	 * @Title: copyContact 
	 * @Description: 复制联系方式
	 * @author xj 
	 * @param bc 贷前客户
	 * @param pp 贷后客户
	 * @throws Exception
	 */
	private void copyContact(BeforePersonalCustomer bc, AfterPersonalCustomer pp) throws Exception {
		List<BeforeContact> beforeContacts = beforeContactService.queryContact(bc.getId());
		if(ObjectHelper.isNotEmpty(beforeContacts)){
			for (BeforeContact beforeContact : beforeContacts) {
				AfterContact plc = new AfterContact();
				BeanUtils.copyProperties(beforeContact, plc, new String[]{"id","version","updateBy","updateOrgCd","customerId"});
				plc.setCustomerId(pp.getId());
				plc = afterContactService.saveEntity(plc);
			}
		}
		
	}
	/**
	 * 
	 * @Title: copyAddress 
	 * @Description: 复制地址
	 * @author xj 
	 * @param bc 贷前客户
	 * @param pp 贷后客户
	 * @throws Exception
	 */
	private void copyAddress(BeforePersonalCustomer bc, AfterPersonalCustomer pp) throws Exception {
		List<BeforeAddress> beforeAddresss = beforeAddressService.queryAddresss(bc.getId());
		if(ObjectHelper.isNotEmpty(beforeAddresss)){
			for (BeforeAddress ba : beforeAddresss) {
				AfterAddress pa = new AfterAddress();
				BeanUtils.copyProperties(ba, pa,  new String[]{"id","version","updateBy","updateOrgCd","customerId"});
				pa.setCustomerId(pp.getId());
				pa = afterAddressService.saveEntity(pa);
			}
		}
		
	}
	/**
	 * 
	 * @Title: copyCustomer 
	 * @Description: 保存客户
	 * @author xj 
	 * @param bc 贷前客户
	 * @return
	 * @throws Exception
	 */
	private AfterPersonalCustomer copyCustomer(BeforePersonalCustomer bc) throws Exception {
		AfterPersonalCustomer postLoanPersonal = new AfterPersonalCustomer();
		BeanUtils.copyProperties(bc, postLoanPersonal,  new String[]{"id","version","updateBy","updateOrgCd","customerId"});
		return afterPersonalCustomerService.saveEntity(postLoanPersonal);
	}

}
