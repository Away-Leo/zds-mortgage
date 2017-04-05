package com.zdsoft.finance.customer.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.customer.entity.BeforePersonalCustomer;
import com.zdsoft.finance.customer.entity.BeforeWorkUnit;
import com.zdsoft.finance.customer.repository.BeforeWorkUnitRepository;
import com.zdsoft.finance.customer.service.BeforePersonalCustomerService;
import com.zdsoft.finance.customer.service.BeforeWorkUnitService;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.cra.client.service.CRA;
import com.zdsoft.framework.cra.dto.AccountDTO;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: BeforeWorkUnitServiceImpl.java 
 * @ClassName: BeforeWorkUnitServiceImpl 
 * @Description: 工作单位
 * @author xj 
 * @date 2017年3月6日 下午6:17:08 
 * @version V1.0
 */
@Service("beforeWorkUnitService")
public class BeforeWorkUnitServiceImpl extends BaseServiceImpl<BeforeWorkUnit,BeforeWorkUnitRepository>  implements BeforeWorkUnitService {
	@Autowired
	private BeforePersonalCustomerService beforePersonalCustomerService;
	@Autowired
	private CRA CRA;
	@Autowired
	private CED CED;
	
	@Transactional(rollbackFor=Exception.class)
	@Override
	public BeforeWorkUnit saveOrUpdateWorkUnit(BeforeWorkUnit beforeWorkUnit,String customerId) throws Exception {
		if(ObjectHelper.isEmpty(customerId)){
			logger.error("保存贷前客户工作单位customerId为空");
			throw new Exception();
		}
		BeforePersonalCustomer beforePersonalCustomer = beforePersonalCustomerService.findOne(customerId);
		if(ObjectHelper.isEmpty(beforePersonalCustomer)){
			logger.error("保存贷前客户工作单位客户为空");
			throw new Exception();
		}
		//登陆人
			EmpDto loginUser = CED.getLoginUser();
		if(ObjectHelper.isNotEmpty(beforeWorkUnit.getId())){
			BeforeWorkUnit work = new BeforeWorkUnit();
			//新增
			BeanUtils.copyProperties(beforeWorkUnit, work);
			work.setCustomerId(beforePersonalCustomer.getId());
			work.setCreateBy(loginUser.getEmpCd());
			work.setCreateOrgCd(loginUser.getOrgCd());
			beforePersonalCustomer.getBeforeWorkUnits().add(work);
			work = this.customReposity.saveEntity(work);
			return work;
		}else{
			//修改
			BeforeWorkUnit work = this.customReposity.getOne(beforeWorkUnit.getId());
			BeanUtils.copyProperties(beforeWorkUnit, work,new String[]{"id","version","createTime","updateTime","createBy","createOrgCd","updateBy","updateOrgCd"});
			work.setUpdateBy(loginUser.getEmpCd());
			work = this.customReposity.updateEntity(work);
			return work;
		}
	}
	
	@Transactional(rollbackFor=Exception.class)
	@Override
	public List<BeforeWorkUnit> saveOrUpdateWorkUnit(List<BeforeWorkUnit> beforeWorkUnits,String customerId,String token) throws Exception {
		List<BeforeWorkUnit> resuts= new ArrayList<BeforeWorkUnit>();
		if(ObjectHelper.isEmpty(customerId)){
			logger.error("保存贷前客户工作单位customerId为空");
			throw new Exception();
		}
		BeforePersonalCustomer beforePersonalCustomer = beforePersonalCustomerService.findOne(customerId);
		if(ObjectHelper.isEmpty(beforePersonalCustomer)){
			logger.error("保存贷前客户工作单位客户为空");
			throw new Exception();
		}
		//登陆人
		EmpDto loginUser = null;
		if(ObjectHelper.isNotEmpty(token)){
			AccountDTO account = CRA.getAccount(token);
			 loginUser = CED.getLoginUser(account.getId());
		}else{
			
			 loginUser = CED.getLoginUser();
		}
		beforePersonalCustomer.getBeforeWorkUnits().clear();
		if(ObjectHelper.isNotEmpty(beforeWorkUnits)){
			for (BeforeWorkUnit beforeWorkUnit : beforeWorkUnits) {
				BeforeWorkUnit work = new BeforeWorkUnit();
				//新增
				BeanUtils.copyProperties(beforeWorkUnit, work);
				work.setCustomerId(beforePersonalCustomer.getId());
				work.setCreateBy(loginUser.getEmpCd());
				work.setCreateOrgCd(loginUser.getOrgCd());
				beforePersonalCustomer.getBeforeWorkUnits().add(work);
				work = this.customReposity.saveEntity(work);
				resuts.add(work);
			}
		}
		
		return resuts;
	}
	
	@Transactional(rollbackFor=Exception.class)
	@Override
	public void deleteById(String id) throws Exception {
		this.customReposity.delete(id);
		
	}
	
	@Override
	public List<BeforeWorkUnit> queryByCustomerId(String customerId) {
		
		return this.customReposity.findByCustomerId(customerId);
	}

}
