package com.zdsoft.finance.busiform.service.impl;

import com.zdsoft.bpm.dto.ProcessInstanceDto;
import com.zdsoft.bpm.service.client.BPM;
import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.finance.busiform.entity.BusiFormStatus;
import com.zdsoft.finance.busiform.entity.MyDraft;
import com.zdsoft.finance.busiform.repository.BusiFormRepository;
import com.zdsoft.finance.busiform.service.BusiFormService;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.enums.busiform.ApplyModelTypeEnum;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.common.utils.ObjectProperUtil;
import com.zdsoft.finance.product.entity.ProcessConfig;
import com.zdsoft.finance.product.service.ProcessConfigService;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.*;

@Service("busiFormService")
public class BusiFormServiceImpl extends BaseServiceImpl<BusiForm, BusiFormRepository> implements BusiFormService {

	@Autowired
	BusiFormRepository busiFormRepository;

	@Autowired
	private CED CED;
	
	@Resource
	private BPM BPM;
	
	@Resource
	private ProcessConfigService processConfigService;

	@Override
	public BusiForm findByBusinessEntityIdAndBusinessEntityNm(String businessEntityId, String businessEntityNm) {
		return busiFormRepository.findByBusinessEntityIdAndBusinessEntityName(businessEntityId, businessEntityNm);
	}

	@Override
	@Transactional
	public BusiForm saveBusiForm(BusiForm busiForm) throws Exception {
		if (ObjectHelper.isNotEmpty(busiForm)) {
			if (ObjectHelper.isEmpty(busiForm.getApplyDate())) {
				busiForm.setApplyDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));
			}
			if (ObjectHelper.isEmpty(busiForm.getProcessStartDate())) {
				busiForm.setProcessStartDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));
			}
			if (ObjectHelper.isEmpty(busiForm.getCreateTime())) {
				busiForm.setCreateTime(new Date());
			}
			if (ObjectHelper.isEmpty(busiForm.getLaunchEmpCode())) {
				busiForm.setLaunchEmpCode(CED.getLoginUser().getEmpCd());
			}
			if (ObjectHelper.isEmpty(busiForm.getLaunchEmpName())) {
				busiForm.setLaunchEmpName(CED.getLoginUser().getEmpNm());
			}
			if (ObjectHelper.isEmpty(busiForm.getCreateBy())) {
				busiForm.setCreateBy(CED.getLoginUser().getEmpCd());
			}
			if (ObjectHelper.isEmpty(busiForm.getCreateOrgCd())) {
				busiForm.setCreateOrgCd(CED.getLoginUser().getOrgCd());
			}
			return busiFormRepository.saveEntity(busiForm);
		} else {
			throw new BusinessException("10010004", "保存busiform出错，传入参数为空！");
		}
	}

	@Override
	@Transactional
	public BusiForm updateBusiForm(BusiForm busiForm) throws BusinessException {
		if (ObjectHelper.isNotEmpty(busiForm)) {
			BusiForm target = busiFormRepository.findOne(busiForm.getId());
			target = copyPoperties(busiForm, target);
			return busiFormRepository.updateEntity(target);
		} else {
			throw new BusinessException("10010004", "传入参数为空！更新busiFrom出错");
		}
	}

	@Override
	@Transactional
	public BusiForm logicDelete(String businessEntityId, String businessEntityNm) {
		BusiForm busiForm = busiFormRepository.findByBusinessEntityIdAndBusinessEntityName(businessEntityId,
				businessEntityNm);
		busiForm = busiFormRepository.logicDelete(busiForm);
		return busiForm;
	}

	@Override
	public BusiForm findById(String id) throws BusinessException {
		if (ObjectHelper.isNotEmpty(id)) {
			BusiForm busiForm = this.busiFormRepository.findOne(id);
			if (ObjectHelper.isNotEmpty(busiForm) && ObjectHelper.isNotEmpty(busiForm.getId())) {
				return busiForm;
			} else {
				throw new BusinessException("10010002", "根据相应参数未找到相应的数据");
			}
		} else {
			throw new BusinessException("1001001", "通过ID查找busiform出错！参数为空");
		}
	}

	@Override
	@Transactional
	public BusiForm updateBusiFormStatus(BusiForm busiform, Integer status) throws BusinessException {
		if (ObjectHelper.isNotEmpty(busiform) && ObjectHelper.isNotEmpty(status)) {
			BusiForm newBusiFrom = this.findById(busiform.getId());
			newBusiFrom = (BusiForm) ObjectProperUtil.compareAndValue(busiform, newBusiFrom, false,null);
			return this.busiFormRepository.updateEntity(newBusiFrom);
		} else {
			throw new BusinessException("10010004", "传入参数为空！更新busiFrom状态出错");
		}
	}

	@Override
	public List<BusiForm> findByCondition(Map<String, Object> condition) {
		return null;
	}

	@Override
	public BusiForm saveOrUpdateBusiForm(BusiForm busiForm) throws Exception {
		if (ObjectHelper.isNotEmpty(busiForm)) {
			if (ObjectHelper.isNotEmpty(busiForm.getId())) {
				return this.updateBusiForm(busiForm);
			} else {
				return this.saveBusiForm(busiForm);
			}
		} else {
			throw new BusinessException("10010004", "传入参数为空！更新或保存BusiForm出错");
		}
	}

	/**
	 * 手动copy字段属性值
	 *
	 * @param source
	 *            源数据
	 * @param target
	 *            目标数据
	 * @return copy后的对象
	 */
	private BusiForm copyPoperties(BusiForm source, BusiForm target) throws BusinessException {
		target = (BusiForm) ObjectProperUtil.compareAndValue(source, target, false,null);
		return target;
	}

	@Override
	public BusiForm findByBusinessEntityId(String businessEntityId) throws BusinessException {
		if (ObjectHelper.isEmpty(businessEntityId)) {
			throw new BusinessException("10010004", "传入businessEntityId为空");
		}
		BusiForm busiForm = busiFormRepository.findByBusinessEntityId(businessEntityId);
		if (ObjectHelper.isEmpty(busiForm)) {
			throw new BusinessException("10010002", "根据传入businessEntityId未找到审批单信息");
		}
		return busiForm;
	}

	@Override
	public Page<MyDraft> findByPage(MyDraft myDraft, Pageable pageable) throws BusinessException {
		return busiFormRepository.findByPage(myDraft, pageable);
	}
	
	@Override
	@Transactional
	public BusiForm startProcess(BusiForm busiForm, Map<String, String> businessVarible, Map<String, String> engineVars)
			throws Exception {
		logger.info("启动流程");
		if (ObjectHelper.isEmpty(busiForm)) {
			throw new BusinessException("10010004", "传入参数为空！");
		}
		logger.debug("businessEntityId:{}", busiForm.getBusinessEntityId());
		logger.debug("businessEntityNm:{}", busiForm.getBusinessEntityName());
		logger.debug("componentsEntityId:{}", busiForm.getComponentsEntityId());
		logger.debug("componentsEntityNm:{}", busiForm.getComponentsEntityName());
		// 判断业务Id
		if (ObjectHelper.isEmpty(busiForm.getBusinessEntityId()) || ObjectHelper.isEmpty(busiForm.getBusinessEntityName())
				|| ObjectHelper.isEmpty(busiForm.getComponentsEntityId()) || ObjectHelper.isEmpty(busiForm.getComponentsEntityName())
				|| ObjectHelper.isEmpty(busiForm.getApplyTitle()) || ObjectHelper.isEmpty(busiForm.getBusinessCode())
				|| ObjectHelper.isEmpty(busiForm.getModelType())) {
			logger.error("业务数据不足！");
			throw new BusinessException("10010003", "必传值为空！");
		}
		// 申请类型判断
		ApplyModelTypeEnum modelType = ApplyModelTypeEnum.getModelType(busiForm.getModelType());
		if (ObjectHelper.isEmpty(modelType)) {
			logger.error("申请类型错误！");
			throw new BusinessException("10010003", "申请类型值错误！");
		}
		logger.debug("processKey:{}", busiForm.getProcessKey());
		logger.debug("productCd:{}", busiForm.getProductId());
		logger.debug("functionCd:{}", busiForm.getFunctionCode());
		// 判断流程名称
		if (ObjectHelper.isEmpty(busiForm.getProcessKey()) && 
				(ObjectHelper.isEmpty(busiForm.getProductId()) || ObjectHelper.isEmpty(busiForm.getFunctionCode()))) {
			logger.error("流程名称定位相关的值为空！");
			throw new BusinessException("10010003", "必传值为空！");
		}
		String processKey = busiForm.getProcessKey();
		if (ObjectHelper.isEmpty(processKey)) {
			// 根据参数读取产品功能表单配置信息
			ProcessConfig processConfig = processConfigService.findByProductIdAndProcessCode(busiForm.getProductId(), busiForm.getFunctionCode());
			if (ObjectHelper.isNotEmpty(processConfig)) {
				processKey = processConfig.getProcessKey();
			}
		}
		logger.debug("流程名称为：{}", processKey);
		if (ObjectHelper.isEmpty(processKey)) {
			logger.error("定位流程名称失败！");
			throw new BusinessException("10010002", "根据相应参数未找到相应的数据!启动流程失败");
		}
		// 保存流程表单信息
		busiForm = this.saveOrUpdateBusiForm(busiForm);
		// 业务变量
		if (ObjectHelper.isEmpty(businessVarible)) {
			businessVarible = new HashMap<String, String>();
		}
		logger.debug("流程表单的Id为：{}", busiForm.getId());
		businessVarible.put(BusiForm.PROCESS_STORE_KEY, busiForm.getId());
		// 启动流程
		ProcessInstanceDto processInstanceDto = this.BPM.startMainProcess(processKey, busiForm.getComponentsEntityId(),
				busiForm.getBusinessEntityId(), processKey, engineVars, businessVarible);
		if (ObjectHelper.isEmpty(processInstanceDto)) {
			logger.error("流程启动失败！得到的流程实例为空");
			throw new BusinessException("10010002", "启动流程失败，获得流程实体为空！");
		}
		EmpDto loginUser = CED.getLoginUser();
		// 发起人
		busiForm.setLaunchEmpCode(loginUser.getEmpCd());
		busiForm.setLaunchEmpName(loginUser.getEmpNm());
		// 设置申请时间
		busiForm.setProcessStartDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));
		if (ObjectHelper.isEmpty(busiForm.getApplyDate())) {
			busiForm.setApplyDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));
		}
		// 流程ID
		busiForm.setProcessInstanceKey(processInstanceDto.getProcessInstanceId());
		// 设置状态
		busiForm.setFormStatus(BusiFormStatus.APPROVAL.value);
		busiForm = this.updateEntity(busiForm);
		// 设置当前处理人
		busiForm.setCurrentDealEmpNm(processInstanceDto.getCurrentCandidate());
		return busiForm;
	}
	
	@Override
	public Page<BusiForm> findMyApplyInfos(Pageable pageable, List<QueryObj> queryObj) throws Exception {
		if (ObjectHelper.isEmpty(queryObj)) {
			queryObj = new ArrayList<QueryObj>();
		}
		// 当前登录者为发起人
		EmpDto loginUser = CED.getLoginUser();
		QueryObj tempQ = new QueryObj();
		tempQ.setOperator("E");
		tempQ.setElement("String");
		tempQ.setValue(loginUser.getEmpCd());
		tempQ.setObj("launchEmpCode");
		queryObj.add(tempQ);
		return this.customReposity.findByHqlConditions(pageable, queryObj);
	}
	
	@Override
	public void wasteApplyInfo(String id) throws BusinessException {
		if (ObjectHelper.isEmpty(id)) {
			throw new BusinessException("10010004", "必传值为空！");
		}
		BusiForm busiForm = this.findOne(id);
		if (ObjectHelper.isEmpty(busiForm)) {
			throw new BusinessException("10010002", "未找到对应的数据！");
		}
		if (!BusiFormStatus.DRAFT.value.equals(busiForm.getFormStatus())) {
			throw new BusinessException("10010003", "申请状态不符合废弃要求！");
		}
		busiForm.setFormStatus(BusiFormStatus.SCRAPPED.value);
		this.updateEntity(busiForm);
	}
	
	public List<BusiForm> findBusiFormByHql(String componentsEntityId,String businessEntityId,String processInstanceKey){
		
		return busiFormRepository.findBusiFormByHql(componentsEntityId, businessEntityId, processInstanceKey);
	}
}
