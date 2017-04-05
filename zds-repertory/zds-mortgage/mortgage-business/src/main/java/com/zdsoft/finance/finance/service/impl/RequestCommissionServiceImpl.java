package com.zdsoft.finance.finance.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.finance.busiform.entity.BusiFormStatus;
import com.zdsoft.finance.busiform.service.BusiFormService;
import com.zdsoft.finance.common.enums.busiform.ApplyModelTypeEnum;
import com.zdsoft.finance.finance.entity.RequestFunds;
import com.zdsoft.finance.finance.entity.RequestFundsDetail;
import com.zdsoft.finance.finance.repository.RequestFundsRepostory;
import com.zdsoft.finance.finance.service.RequestCommissionService;
import com.zdsoft.finance.finance.service.RequestFundsDetailService;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:RequestCommissionServiceImpl.java
 * @Package:com.zdsoft.finance.finance.service.impl
 * @Description:支佣管理Service
 * @author: xiangjx
 * @date:2017年3月2日 上午10:49:12
 * @version:v1.0
 */
@Service("requestCommissionService")
public class RequestCommissionServiceImpl extends BaseServiceImpl<RequestFunds, RequestFundsRepostory> implements RequestCommissionService {

	@Autowired  
	private com.zdsoft.essential.client.service.CED CED;
	
	@Autowired
	private BusiFormService busiFormService;
	
	@Autowired
	private RequestFundsDetailService requestFundsDetailService;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public RequestFunds saveOrSubmitRequestCommission(RequestFunds entity, String feeList) throws Exception {
		EmpDto emp=CED.getLoginUser();
		if(ObjectHelper.isEmpty(entity.getId())){
			entity.setCreateTime(new Date());
			entity.setCreateBy(emp.getEmpCd());
			entity.setCreateOrgCd(emp.getOrgCd());
			entity.setOrgId(emp.getOrgId());
			entity.setOrgName(emp.getOrgNm());
			this.saveEntity(entity);
		}else{
			RequestFunds old_entity=this.customReposity.findOne(entity.getId());
			BeanUtils.copyProperties(entity, old_entity,new String[]{"createBy","createOrgCd","createTime","applyState","orgId","orgName","busiForm"});
			old_entity.setUpdateBy(emp.getEmpCd());
			old_entity.setUpdateOrgCd(emp.getOrgCd());
			old_entity.setUpdateTime(new Date());
			entity.setBusiForm(old_entity.getBusiForm());
			entity=this.updateEntity(old_entity);
			//删除以前的费用项目
			List<RequestFundsDetail> detailList=requestFundsDetailService.findRequestFundsDetailByReqFundsId(entity.getId());
			for(RequestFundsDetail detail:detailList){
				requestFundsDetailService.deleteRequestFundsDetailById(detail.getId());
			}
		}
		
		//1、将列表数据转成List对象
		List<RequestFundsDetail> feeInfomationList=JSONObject.parseArray(feeList, RequestFundsDetail.class);
		//2、保存费用项纪录
		BigDecimal reqFundsAmount=BigDecimal.ZERO;//请款金额
		for(RequestFundsDetail fee:feeInfomationList){
			RequestFundsDetail detail=new RequestFundsDetail();
			BeanUtils.copyProperties(fee, detail,new String[]{"id"});
			detail.setReqFundsId(entity.getId());
			requestFundsDetailService.saveEntity(detail);
			reqFundsAmount=reqFundsAmount.add(fee.getReqFundsAmount());
			entity.setReqFundsAmount(reqFundsAmount);
			this.updateEntity(entity);
		}
		
		if(ObjectHelper.isEmpty(entity.getBusiForm())){
			BusiForm busiForm = new BusiForm();
			busiForm.setBusinessEntityId(entity.getId());
	        busiForm.setBusinessEntityName(RequestFunds.class.getSimpleName());
	        busiForm.setComponentsEntityId(entity.getId());
	        busiForm.setComponentsEntityName(RequestFunds.class.getSimpleName());
	        busiForm.setFormStatus(BusiFormStatus.DRAFT.value);
	        busiForm.setApplyTitle("支佣请款-"+entity.getBillCode());
	        busiForm.setBusinessCode(entity.getBillCode());
        	busiForm.setModelType(ApplyModelTypeEnum.REQUESTCOMMISSION_APPLY.value);
        	busiForm.setFunctionCode(RequestFunds.proceessCode2);
	        busiForm = busiFormService.saveOrUpdateBusiForm(busiForm);
	        entity.setApplyState(0);
	        entity.setBusiForm(busiForm);
	        this.updateEntity(entity);
		}
		
		if(entity.getIsSubmit()){//启动流程
			BusiForm busiForm = entity.getBusiForm();
			busiForm.setFormStatus(BusiFormStatus.APPROVAL.value);
			busiForm.setProcessKey(ApplyModelTypeEnum.REQUESTCOMMISSION_APPLY.name);
	        busiForm = busiFormService.startProcess(busiForm, null, null);  //启动流程
	        entity.setApplyState(1);
	        entity.setMsg(busiForm.getCurrentDealEmpNm()); //获取下一节点处理人
	        entity.setBusiForm(busiForm);
	        this.updateEntity(entity);
		}
		return entity;
	}

}
