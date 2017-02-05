package com.zdsoft.finance.app.usercenter.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.bpm.dto.ProcessInstanceDto;
import com.zdsoft.bpm.service.client.BPM;
import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.app.usercenter.MyBusiInfoDto;
import com.zdsoft.finance.app.usercenter.repository.MyBusiRepository;
import com.zdsoft.finance.app.usercenter.service.MyBusiService;
import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.finance.busiform.entity.BusiFormStatus;
import com.zdsoft.finance.busiform.service.BusiFormService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.customer.service.BlanckListService;
import com.zdsoft.finance.marketing.entity.BeforehandApply;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.service.BeforehandApplyService;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.product.entity.ProcessConfig;
import com.zdsoft.finance.product.entity.Product;
import com.zdsoft.finance.product.service.ProcessConfigService;
import com.zdsoft.finance.product.service.ProductService;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.cra.client.service.CRA;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:MyBusiServiceImpl.java
 * @Package:com.zdsoft.finance.app.usercenter.impl
 * @Description:用一句话描述该文件做什么
 * @author: jingyh
 * @date:2017年1月13日 下午8:14:44
 * @version:v1.0
 */
@Service("myBusiService")
public class MyBusiServiceImpl implements MyBusiService {

	@Log
	private Logger log;
	
	@Resource
	private CRA CRA;
	
	@Resource
	private CED CED;
	
	@Resource
	private MyBusiRepository myBusiRepository;
	
	@Resource
	private CaseApplyService caseApplyService;
	
	@Resource
	private ProductService productService;
	
	@Resource
	private BPM BPM;
	
	@Resource
	private ProcessConfigService processConfigService;
	
	@Resource
	private BusiFormService busiFormService;
	
	@Resource
	private BlanckListService blanckListService;
	
	@Resource
	private BeforehandApplyService beforehandApplyService;
	
	@Override
	@Transactional
	public List<MyBusiInfoDto> findMyBusiPageInfo(String[] busiTypes, Map<String, Object> params,
			Pageable pageInfo) throws Exception {
		log.info("查询我的业务信息");
		log.debug("查询参数为:{}", params);
		if (ObjectHelper.isEmpty(busiTypes)) {
			throw new BusinessException("10010004", "传入参数不足！");
		}
		if (ObjectHelper.isEmpty(params)) {
			params = new HashMap<String, Object>();
		}
		EmpDto emp = CED.getLoginUser();
		// 当前的登录者查询
		params.put("currentEmpCd", emp.getEmpCd());
		params.put("currentEmpId", emp.getId());
		params.put("status", CaseApply.NORMAL);
		return myBusiRepository.findMyBusiPageInfo(params, pageInfo);
	}
	
	@Override
	@Transactional
	public BusiForm startCaseApplyMainProcess(String beforeAppyId) throws Exception {
		// APP根据案件Id启动流程
		// 传入为预申请Id
		if (ObjectHelper.isEmpty(beforeAppyId)) {
			throw new BusinessException("10010004", "预申请案件Id为空！");
		}
		BeforehandApply beforeApply = beforehandApplyService.findOne(beforeAppyId);
		if (ObjectHelper.isEmpty(beforeApply)) {
			throw new BusinessException("10010002", "预申请案件信息定位失败！预申请Id：" + beforeAppyId);
		}
		String caseApplyId = beforeApply.getCaseApplyId();
		// 获取案件信息
		CaseApply apply = this.caseApplyService.findOne(caseApplyId);
		if (ObjectHelper.isEmpty(apply)) {
			throw new BusinessException("10010002", "案件信息定位失败！" + caseApplyId);
		}
		// 判断黑名单
		Boolean blackFlg = blanckListService.checkBlankList(caseApplyId);
		// true为黑名单
		if (ObjectHelper.isEmpty(blackFlg) || blackFlg ) {
			throw new BusinessException("10010012", "黑名单校验不通过！案件记录为：" + caseApplyId);
		}
		// 表单信息
		BusiForm busiForm = apply.getBusiForm();
		log.debug("产品子类Id：{}", apply.getProductSubtypeId());
		//产品
		Product product = productService.findOne(apply.getProductSubtypeId());
		if (ObjectHelper.isEmpty(product)) {
			// 定位产品失败
			throw new BusinessException("10010002", "定位产品失败！案件Id：" + caseApplyId);
		}
		// 获取当前登录者
		EmpDto loginUser = CED.getLoginUser();
		if (ObjectHelper.isEmpty(loginUser)) {
			throw new BusinessException("10010006", "获取当前登录用户失败！");
		}
		if(ObjectHelper.isEmpty(busiForm)){
			busiForm = new BusiForm();
			//状态
			busiForm.setStatus(BusiFormStatus.DRAFT.value);
			//产品编号
			busiForm.setProductCd(apply.getProductSubtypeId());
			//产品名称
			busiForm.setProductNm(product.getProductName());
			//申请人编号
			busiForm.setApplyEmpCd(loginUser.getEmpCd());
			//申请人名称
			busiForm.setApplyEmpNm(loginUser.getEmpNm());
			//申请时间
			busiForm.setApplyTime(DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));
			//关联业务表单id
			busiForm.setBusinessEntityId(apply.getId());
			//关联表单域对象类名 例如表单为Project时 此字段存入的值为Project
			busiForm.setBusinessEntityNm(CaseApply.class.getSimpleName());
			//关联组件数据ID 例如 项目表ID 合同ID
			busiForm.setComponentsEntityId(apply.getId());
			//关联组件域对象类名 例如表单为Project时 此字段存入的值为Project
			busiForm.setComponentsEntityNm(CaseApply.class.getSimpleName());
		}
		//启动流程
		log.info("开始启动流程");
		ProcessConfig processConfig = processConfigService.findByProductIdAndProcessConfigCode(product.getId(), CaseApply.PROCESS_KEY_CODE);
		if (ObjectHelper.isEmpty(processConfig)) {
			// 定位产品流程配置失败
			throw new BusinessException("10010002", "定位产品流程配置失败！产品Id：" + product.getId());
		}
		busiForm.setStatus(BusiFormStatus.APPROVAL.value);
		String processKey = processConfig.getProcessKey();
		if (ObjectHelper.isEmpty(processKey)) {
			// 定位产品流程配置失败
			throw new BusinessException("10010002", "定位流程名称！产品配置Id：" + processConfig.getId());
		}
        //流程实例
        ProcessInstanceDto instanceDto = BPM.startMainProcess(processKey, caseApplyId, caseApplyId, processKey, null, null);
		//流程实例key
		busiForm.setProcessInstanceKey(instanceDto.getProcessInstanceId());
		log.info("processInstanceKey:{}",instanceDto.getProcessInstanceId());
		//流程开始时间
		busiForm.setProcessStartTime(DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));
		//流程发起人编号
		busiForm.setProcessApplayEmpCd(loginUser.getEmpCd());
		log.debug("processApplayEmpCd:{}",loginUser.getEmpCd());
		//流程发起人姓名
		busiForm.setProcessApplayEmpNm(loginUser.getEmpNm());
		//流程名称
		busiForm.setProcessKey(processKey);
		log.debug("currentDealEmpNm:{}",instanceDto.getCurrentCandidate());
		busiForm = busiFormService.saveOrUpdateEntity(busiForm);
		apply.setBusiForm(busiForm);
		this.caseApplyService.updateEntity(apply);
		log.info("启动流程成功");
		//当前处理人名称
		busiForm.setCurrentDealEmpNm(instanceDto.getCurrentCandidate());
		return busiForm;
	}
}

