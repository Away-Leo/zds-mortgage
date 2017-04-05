package com.zdsoft.finance.marketing.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.essential.dto.permission.DataOperPermDto;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.finance.busiform.entity.BusiFormStatus;
import com.zdsoft.finance.busiform.service.BusiFormService;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.enums.busiform.ApplyModelTypeEnum;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.CaseApplyStageEnumSimpleCodeEnum;
import com.zdsoft.finance.marketing.entity.Chargeback;
import com.zdsoft.finance.marketing.repository.ChargebackRepository;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.marketing.service.ChargebackService;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ChargebackServiceImpl.java 
 * @ClassName: ChargebackServiceImpl 
 * @Description: ChargebackServiceImpl
 * @author caixiekang 
 * @date 2017年3月6日 下午2:24:41 
 * @version V1.0
 */
@Service
public class ChargebackServiceImpl extends BaseServiceImpl<Chargeback, CustomRepository<Chargeback,String>>
	implements ChargebackService{

	@Autowired
	private ChargebackRepository chargebackRepository;
	@Autowired
	private CED ced;
	@Autowired
	private BusiFormService busiFormService;
	@Autowired
	private CaseApplyService caseApplyService;
	@Override
	public Page<Map<String, Object>> findChargebackPages(PageRequest pageable, List<QueryObj> queryObjs,DataOperPermDto dtOperPermDto)
			throws Exception {
		StringBuffer _sql = ChargebackRepository.CHARGEBACK_LIST_SQL;
		_sql.append(this.developmentManagerDataAuth(dtOperPermDto, "ca"));
		Page<Map<String, Object>> page = chargebackRepository.getListObjectBySql(pageable, queryObjs, _sql, ChargebackRepository.LIST_EXTEND_SQL);
		if(ObjectHelper.isNotEmpty(page) && ObjectHelper.isNotEmpty(page.getRecords())){
			for (Map<String, Object> map : page.getRecords()) {
				//案件状态simpleCode拼接
				Object stage = map.get("stage");
				if(ObjectHelper.isNotEmpty(stage)){
					String stageName = ced.loadSimpleCodeNameByFullCode(stage.toString());
					map.put("stageName", stageName);
				}
			}
		}
		
		return page;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public Chargeback saveOrSubmitChargeback(Chargeback chargeback) throws Exception {
		//获取当前操作员工
		EmpDto emp=ced.getLoginUser();
		//是否提交
		Boolean submitted = chargeback.getSubmitted();
		//案件id
		String caseApplyId = chargeback.getCaseApplyId();
		
		CaseApply caseApply = caseApplyService.findOne(caseApplyId);
		//第一次保存
		if(ObjectHelper.isEmpty(chargeback.getId())){
			//设置当前操作员工
			chargeback.setCreateTime(new Date());
			chargeback.setCreateBy(emp.getEmpCd());
			chargeback.setCreateOrgCd(emp.getOrgCd());
			//设置busiForm并保存
			chargeback =configBusiForm(chargeback);
			//下一节点处理人
			String msg = null;
			//保存
			chargeback = this.saveEntity(chargeback);
			//一次性提交
			if(submitted){
				initChargebackProcess(chargeback);
				busiFormService.updateBusiFormStatus(chargeback.getBusiForm(), BusiFormStatus.APPROVAL.value);
				msg = chargeback.getMsg();
				//改变案件状态为退单审核中
				caseApplyService.changeCaseStage(caseApplyId, CaseApplyStageEnumSimpleCodeEnum.CHARGEBACK_INPROCESS.value);
				
			}else{
				BusiForm busiForm = chargeback.getBusiForm();
				busiForm.setComponentsEntityId(chargeback.getCaseApplyId());
				busiForm.setBusinessEntityId(chargeback.getId());
				busiFormService.updateEntity(busiForm);
			}
			//保存实体对象
			//获取下一节点处理人
			if(ObjectHelper.isNotEmpty(msg)){
				chargeback.setMsg(msg);
			}
		}else{
			//更新
			Chargeback old_entity=chargebackRepository.findOne(chargeback.getId());
			BeanUtils.copyProperties(chargeback, old_entity,new String[]{"createBy","createTime","busiForm"});
			old_entity.setUpdateBy(emp.getEmpCd());
			old_entity.setUpdateOrgCd(emp.getOrgCd());
			old_entity.setUpdateTime(new Date());
			//如果是提交,则改变busiForm状态
			if(submitted){
				initChargebackProcess(old_entity);
				//提交,改变案件状态为退单审核中
				caseApplyService.changeCaseStage(caseApplyId, CaseApplyStageEnumSimpleCodeEnum.CHARGEBACK_INPROCESS.value);
			}
			//保存案件当前状态
			old_entity.setCaseApplyStatus(caseApply.getStage());
			this.updateEntity(old_entity);
			return old_entity;
		}
		
		return chargeback;
	}
	/**
	 * 
	 * @Title: configBusiForm 
	 * @Description: 设置BusIForm
	 * @author caixiekang 
	 * @param chargeback 退单实体对象
	 * @return
	 * @throws Exception
	 */
	private Chargeback configBusiForm(Chargeback chargeback) throws Exception {
		BusiForm busiForm = new BusiForm();
		busiForm.setBusinessEntityId(chargeback.getId());
		busiForm.setBusinessEntityName(Chargeback.class.getSimpleName());
		busiForm.setComponentsEntityId(chargeback.getCaseApplyId());
		busiForm.setComponentsEntityName(CaseApply.class.getSimpleName());
		busiForm.setApplyTitle("退单申请-"+chargeback.getCaseApplyCode());
		busiForm.setModelType(ApplyModelTypeEnum.CHARGEBACK_APPLY.value);
		busiForm.setBusinessCode(chargeback.getCaseApplyCode());
		chargeback.setBusiForm(busiForm);
		busiForm.setFormStatus(BusiFormStatus.DRAFT.value);
		return chargeback;
	}
	/**
	 * 
	 * @Title: initChargebackProcess 
	 * @Description: 启动工作流
	 * @author caixiekang 
	 * @param chargeback 实体对象
	 * @return
	 * @throws Exception
	 */
	private Chargeback initChargebackProcess(Chargeback chargeback) throws Exception {
		BusiForm busiForm = chargeback.getBusiForm();
		busiForm.setProcessKey("退单确认");
		busiForm = busiFormService.startProcess(busiForm, null, null);  //启动流程
		chargeback.setMsg(busiForm.getCurrentDealEmpNm()); //获取下一节点处理人
		
		return chargeback;
	}

//	@Override
//	public Chargeback findByCaseApplyId(String caseApplyId) throws Exception {
//		//获取退单函的id
//		Map<String, Object> map = chargebackRepository.findByCaseApplyId(caseApplyId);
//		Chargeback chargeback = null;
//		if(ObjectHelper.isNotEmpty(map)){
//			Object oid = map.get("id");
//			String id = oid.toString();
//			chargeback = this.findOne(id);
//		}
//		return chargeback;
//	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public CaseApply opChargeBack(String caseApplyId) throws Exception {
		CaseApply caseApply = caseApplyService.changeCaseStage(caseApplyId, CaseApplyStageEnumSimpleCodeEnum.ACCEPTANCE.value);
		return caseApply;
		
	}
	
	@Override
	public Chargeback findByCaseApplyIdAndChargebackCauseIsNull(String caseApplyId) throws Exception {
		if(ObjectHelper.isEmpty(caseApplyId)){
			throw new Exception("案件id为null");
		}
		return chargebackRepository.findByCaseApplyIdAndChargebackCauseIsNull(caseApplyId);
	}
}
