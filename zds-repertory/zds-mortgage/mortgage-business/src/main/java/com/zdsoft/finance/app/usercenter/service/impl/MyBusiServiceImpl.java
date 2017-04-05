package com.zdsoft.finance.app.usercenter.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.app.usercenter.MyBusiInfoDto;
import com.zdsoft.finance.app.usercenter.repository.MyBusiRepository;
import com.zdsoft.finance.app.usercenter.service.MyBusiService;
import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.finance.busiform.entity.BusiFormStatus;
import com.zdsoft.finance.busiform.service.BusiFormService;
import com.zdsoft.finance.common.enums.busiform.ApplyModelTypeEnum;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.customer.entity.BeforeCustomer;
import com.zdsoft.finance.customer.service.BlanckListService;
import com.zdsoft.finance.marketing.entity.BeforehandApply;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.entity.CaseApplyStageEnumSimpleCodeEnum;
import com.zdsoft.finance.marketing.service.BeforehandApplyService;
import com.zdsoft.finance.marketing.service.CaseApplyBeforeCustomerService;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.product.entity.Product;
import com.zdsoft.finance.product.service.ProcessConfigService;
import com.zdsoft.finance.product.service.ProductService;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:MyBusiServiceImpl.java
 * @Package:com.zdsoft.finance.app.usercenter.impl
 * @Description:我的业务处理Service
 * @author: jingyh
 * @date:2017年1月13日 下午8:14:44
 * @version:v1.0
 */
@Service("myBusiService")
public class MyBusiServiceImpl implements MyBusiService {

	@Log
	private Logger log;
	
	@Resource
	private CED CED;
	
	@Resource
	private MyBusiRepository myBusiRepository;
	
	@Resource
	private CaseApplyService caseApplyService;
	
	@Resource
	private ProductService productService;
	
	@Resource
	private ProcessConfigService processConfigService;
	
	@Resource
	private BusiFormService busiFormService;
	
	@Resource
	private BlanckListService blanckListService;
	
	@Resource
	private BeforehandApplyService beforehandApplyService;
	
	@Resource
	private CaseApplyBeforeCustomerService caseApplyBeforeCustomerService;
	
	@Override
	public List<MyBusiInfoDto> findMyBusiPageInfo(Map<String, Object> params,
			Pageable pageInfo) throws Exception {
		log.info("查询我的业务信息");
		log.debug("查询参数为:{}", params);
		if (ObjectHelper.isEmpty(params)) {
			params = new HashMap<String, Object>();
		}
		EmpDto emp = CED.getLoginUser();
		// 当前的登录者查询
		params.put("currentEmpCd", emp.getEmpCd());
		params.put("currentEmpId", emp.getId());
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
		/*// 判断黑名单
		Boolean blackFlg = blanckListService.checkBlankList(caseApplyId);
		// true为黑名单
		if (ObjectHelper.isEmpty(blackFlg) || blackFlg ) {
			throw new BusinessException("10010012", "黑名单校验不通过！案件记录为：" + caseApplyId);
		}*/
		log.debug("产品子类Id：{}", apply.getProductSubtypeId());
		//产品
		Product product = productService.findOne(apply.getProductSubtypeId());
		if (ObjectHelper.isEmpty(product)) {
			// 定位产品失败
			throw new BusinessException("10010002", "定位产品失败！案件Id：" + caseApplyId);
		}
		List<BeforeCustomer> relationCustion = caseApplyBeforeCustomerService.findCustomerByCaseApplyIdAndJoinType(apply.getId(), CaseApplyBeforeCustomer.MAIN_BORROW);
		if (ObjectHelper.isEmpty(relationCustion)) {
			// 定位产品失败
			throw new BusinessException("10010002", "定位主借人！案件Id：" + caseApplyId);
		}
		// 标题 ：案件号+主借人+产品
		String applyTitle = apply.getCaseApplyCode();
		applyTitle += "+" + relationCustion.get(0).getCustomerName();
		applyTitle += "+" + product.getProductName();
		// 表单信息
		BusiForm busiForm = apply.getBusiForm();
		if(ObjectHelper.isEmpty(busiForm)){
			busiForm = new BusiForm();
			//申请时间
			busiForm.setApplyDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));
		}
		busiForm.setBusinessEntityId(apply.getId());
		busiForm.setBusinessEntityName(CaseApply.class.getSimpleName());
		busiForm.setComponentsEntityId(apply.getId());
		busiForm.setComponentsEntityName(CaseApply.class.getSimpleName());
		busiForm.setBusinessCode(apply.getCaseApplyCode());
		busiForm.setModelType(ApplyModelTypeEnum.CASE_APPLY.value);
		busiForm.setApplyTitle(applyTitle);
		busiForm.setProcessKey(null);
		busiForm.setProductId(apply.getProductSubtypeId());
		busiForm.setFunctionCode(CaseApply.PROCESS_KEY_CODE);
		busiForm = this.busiFormService.saveOrUpdateEntity(busiForm);
		// 添加一审校验
		if(!beforehandApplyService.firstApprovalDetermine(apply, busiForm)){
			// 启动流程
			busiForm = busiFormService.startProcess(busiForm, null, null);
		} else {
			log.info("更改案件状态为拒绝，否决");
			// busiForm状态置为规则拒绝
			busiForm.setFormStatus(BusiFormStatus.RULESREFUSE.value);
			apply.setStage(CaseApplyStageEnumSimpleCodeEnum.VETO.value);
			busiForm.setHadRulesRefuse(true);
		}
		apply.setBusiForm(busiForm);
		this.caseApplyService.updateEntity(apply);
		log.info("启动流程成功");
		return busiForm;
	}
}

