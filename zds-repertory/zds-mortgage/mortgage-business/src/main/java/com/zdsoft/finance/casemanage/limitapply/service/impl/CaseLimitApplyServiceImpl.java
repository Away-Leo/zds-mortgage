package com.zdsoft.finance.casemanage.limitApply.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.capital.entity.CreditEntrust;
import com.zdsoft.finance.capital.service.CreditEntrustService;
import com.zdsoft.finance.casemanage.limitApply.entity.CaseLimitApply;
import com.zdsoft.finance.casemanage.limitApply.repository.CaseLimitApplyRepository;
import com.zdsoft.finance.casemanage.limitApply.service.CaseLimitApplyService;
import com.zdsoft.finance.common.exception.AllotAmountException;
import com.zdsoft.finance.common.exception.AmountLackBusiness;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.common.exception.NotOpenLimitException;
import com.zdsoft.finance.contract.entity.ConCaseContract;
import com.zdsoft.finance.contract.service.ConCaseContractService;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.CaseApplyStageEnumSimpleCodeEnum;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CaseLimitApplyServiceImpl.java 
 * @ClassName: CaseLimitApplyServiceImpl 
 * @Description: 案件额度申请服务接口
 * @author xj 
 * @date 2017年2月21日 下午6:23:21 
 * @version V1.0
 */
@Service("caseLimitApplyService")
public class CaseLimitApplyServiceImpl extends BaseServiceImpl<CaseLimitApply, CaseLimitApplyRepository>
		implements CaseLimitApplyService {
	
	@Autowired
	private CaseApplyService caseApplyService;
	@Autowired
	private CreditEntrustService creditEntrustService;
	@Autowired
	private CED CED;
	@Autowired
	private ConCaseContractService conCaseContractService;

	@Override
	public List<CaseLimitApply> findByCaseApplyId(String caseApplyId) {
		return this.customReposity.findByCaseApplyIdAndIsDeletedOrderByCreateTime(caseApplyId, false);
	}

	@Override
	public List<CaseLimitApply> findByCreditEntrustId(String creditEntrustId, String state) {
		return this.customReposity.findByCreditEntrustId(creditEntrustId, state);
	}

	@Override
	public List<CaseLimitApply> findByCaseApplyIdAndEffectiveStatus(String caseApplyId, String effectiveStatus) {
		return this.customReposity.findByCaseApplyIdAndEffectiveStatusAndIsDeletedOrderByCreateTime(caseApplyId, effectiveStatus, false);
	}
	
	@Transactional(rollbackFor=Exception.class)
	@Override
	public CaseLimitApply saveCaseLimitApply(CaseLimitApply caseLimitApply) throws Exception{
		CreditEntrust creditEntrust = creditEntrustService.findOne(caseLimitApply.getFundPlanId());
		CaseApply caseApply = caseApplyService.findOne(caseLimitApply.getCaseApplyId());
		BigDecimal applyLimitAmount = caseLimitApply.getApplyLimitAmount();
		//判断余额是否足够
		BigDecimal tempAmount = creditEntrust.getIncomeBalance();
		tempAmount=tempAmount==null?BigDecimal.ZERO:tempAmount;
		
		// 截留额度
		BigDecimal retain = creditEntrust.getRetain();
		retain=retain==null?BigDecimal.ZERO:retain;
		
		// 剩余金额
		tempAmount = tempAmount.subtract(retain);
		// 判断分配的金额是否大于余额
		if (tempAmount.compareTo(applyLimitAmount) < 0) {
			throw new AmountLackBusiness("资金余额不足");
		}
		//判断是否开放申请额度
		if(ObjectHelper.isEmpty(creditEntrust.getIsOpenApply()) || !creditEntrust.getIsOpenApply()){
			//没有开放申请额度
			throw new NotOpenLimitException("未开放申请额度");
		}
		//如果案件的额度状态不是额度未申请
		if(!CaseLimitApply.NOT_APPLY.equals(caseApply.getActualLimitStatus())){
			throw new BusinessException("10010003","该案件已经处于申请状态");
		}
		CaseLimitApply caseLimit = new CaseLimitApply();
		BeanUtils.copyProperties(caseLimitApply, caseLimit,new String[]{"id"});
		EmpDto empDto = CED.getLoginUser();
		//登录人
		String createBy = empDto.getEmpCd();
		//登录名称
		String empNm = empDto.getEmpNm();
		
		//登录人部门
		String orgCd = empDto.getOrgCd();
		//创建人部门
		caseLimit.setCreateOrgCd(orgCd);
		//创建人code
		caseLimit.setCreateBy(createBy);
		//申请人code
		caseLimit.setApplyEmpCode(createBy);
		//申请人姓名
		caseLimit.setApplyEmpName(empNm);
		//申请时间 
		caseLimit.setApplyDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));
		//已申请未配资金状态
		caseLimit.setEffectiveStatus(CaseLimitApply.FILED_WITHOUT_MONEY);
		//案件 已申请未配资金状态
		caseApply.setActualLimitStatus(CaseLimitApply.FILED_WITHOUT_MONEY);
		//申请额度
		creditEntrust = creditEntrustService.updateIncomeBalance(creditEntrust, applyLimitAmount);
		//案件状态
		if(CaseApplyStageEnumSimpleCodeEnum.QUOTAAPPLICATION.value.equals(caseApply.getStage())){
			caseApply.setStage(CaseApplyStageEnumSimpleCodeEnum.INTERVIEWBESPEAK.value);
		}
		
		caseLimit = this.saveEntity(caseLimit);
		caseApplyService.updateEntity(caseApply);
		
		
		return caseLimit;
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public CaseLimitApply allotCaseLimitApply(CaseLimitApply caseLimitApply) throws Exception {
		try {
			CreditEntrust creditEntrust = creditEntrustService.findOne(caseLimitApply.getFundPlanId());
			if(!creditEntrust.getIsAutoMatch()){
				//没有开启自动匹配额度
				throw new AllotAmountException("额度分配失败，没有开启自动匹配额度");
			}
			CaseApply caseApply = caseApplyService.findOne(caseLimitApply.getCaseApplyId());
			//如果案件的额度状态不是已申请未配资金
			if(!CaseLimitApply.FILED_WITHOUT_MONEY.equals(caseApply.getActualLimitStatus())){
				throw new AllotAmountException("只有已申请未配资金才能匹配额度！");
			}
			BigDecimal applyLimitAmount = caseLimitApply.getApplyLimitAmount();
			//分配金额
			creditEntrust = creditEntrustService.updatePaymentBalance(creditEntrust, applyLimitAmount);
			//分配金额成功 分配日期
			caseLimitApply
					.setAllotDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_SHORT_SIMPLE_FORMAT));
			//已配资金
			caseLimitApply.setEffectiveStatus(CaseLimitApply.ALLOCATED_FUNDS);
			//案件已配资金
			caseApply.setActualLimitStatus(CaseLimitApply.ALLOCATED_FUNDS);
			//案件已分配金额
			caseApply.setActualApplyAmount(applyLimitAmount);
			
			this.updateEntity(caseLimitApply);
			//案件合同合同金额设置
			ConCaseContract conCaseContract = conCaseContractService.findByCaseApplyId(caseLimitApply.getCaseApplyId());
			conCaseContract.setContractAmount(applyLimitAmount);
			conCaseContractService.updateEntity(conCaseContract);
			caseApplyService.updateEntity(caseApply);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("额度分配失败", e);
			throw new AllotAmountException("额度分配失败");
		}
		return caseLimitApply;
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void cancelCaseLimitApply(String caseLimitApplyId) throws Exception{
		CaseLimitApply caseLimitApply = this.customReposity.findOne(caseLimitApplyId);
		//案件
		CaseApply caseApply = caseApplyService.findOne(caseLimitApply.getCaseApplyId());
		if(CaseLimitApply.NOT_APPLY.equals(caseApply.getActualLimitStatus())){
			throw new BusinessException("10010003","该案件已经处于未申请状态！");
		}
		EmpDto empDto = CED.getLoginUser();
		//登录人
		String createBy = empDto.getEmpCd();
		//登录名称
		String empNm = empDto.getEmpNm();
		
		//取消人code
		caseLimitApply.setCancelEmpCode(createBy);
		//取消人name
		caseLimitApply.setCancelEmpName(empNm);
		//取消时间
		caseLimitApply.setCancelDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_SHORT_SIMPLE_FORMAT));
		//案件额度实际申请金额
		caseApply.setActualApplyAmount(BigDecimal.ZERO);
		//调用额度申请接口
		BigDecimal applyLimitAmount = caseLimitApply.getApplyLimitAmount();
		CreditEntrust creditEntrust = creditEntrustService.findOne(caseLimitApply.getFundPlanId());
		//取消申请额度
		creditEntrust = creditEntrustService.updateIncomeBalance(creditEntrust, applyLimitAmount.multiply(new BigDecimal(-1)));
		//取消额度分配
		//先判断是否额度分配了
		if(CaseLimitApply.ALLOCATED_FUNDS.equals(caseLimitApply.getEffectiveStatus())){
			//已分配资金，取消分配资金
			creditEntrust = creditEntrustService.reservePaymentBalance(creditEntrust, applyLimitAmount);
			//案件合同合同金额设置为0
			ConCaseContract conCaseContract = conCaseContractService.findByCaseApplyId(caseLimitApply.getCaseApplyId());
			conCaseContract.setContractAmount(BigDecimal.ZERO);
			conCaseContractService.updateEntity(conCaseContract);
		}
		//修改为未申请状态
		caseLimitApply.setEffectiveStatus(CaseLimitApply.NOT_APPLY);
		//修改案件为未申请状态
		caseApply.setActualLimitStatus(CaseLimitApply.NOT_APPLY);
		//跟新
		this.updateEntity(caseLimitApply);
		caseApplyService.updateEntity(caseApply);
	}
	
	@Transactional(rollbackFor=Exception.class)
	@Override
	public CaseLimitApply applyLimit(String caseLimitApplyId,String creditEntrustId,String caseApplyId,BigDecimal applyLimitAmount) throws Exception {
		if(ObjectHelper.isEmpty(caseApplyId) || ObjectHelper.isEmpty(creditEntrustId) || ObjectHelper.isEmpty(applyLimitAmount)){
			throw new BusinessException("10010013", "申请额度失败，参数不完整");
		}
				
		logger.info("caseApplyId:"+caseApplyId+"---申请金额："+applyLimitAmount);
		CaseApply caseApply = caseApplyService.findOne(caseApplyId);
		
		//案件状态
		if(CaseApplyStageEnumSimpleCodeEnum.QUOTAAPPLICATION.value.equals(caseApply.getStage())){
			caseApply.setStage(CaseApplyStageEnumSimpleCodeEnum.INTERVIEWBESPEAK.value);
		}
				
		if(!CaseLimitApply.NOT_APPLY.equals(caseApply.getActualLimitStatus())){
			throw new BusinessException("10010003","该案件已经处于申请状态");
		}
		
		CaseLimitApply caseLimitApply = new CaseLimitApply();
		if(ObjectHelper.isNotEmpty(caseLimitApplyId)){
			caseLimitApply = this.findOne(caseLimitApplyId);
		}
		
		//查询资金
		CreditEntrust creditEntrust = creditEntrustService.findOne(creditEntrustId);
		//案件id
		caseLimitApply.setCaseApplyId(caseApplyId);
		//申请金额
		caseLimitApply.setApplyLimitAmount(applyLimitAmount);
		//资金计划id
		caseLimitApply.setFundPlanId(creditEntrustId);
		//资金计划名称 
		caseLimitApply.setFundPlanName(creditEntrust.getCreditEntrustName());
		//贷款发放账户机构名称 
		caseLimitApply.setLoanOutAccountBranchName(creditEntrust.getWaitApprBank());
		//贷款发放账户账户名
		caseLimitApply.setLoanOutAccountName(creditEntrust.getWaitApprName());
		//贷款发放账户
		caseLimitApply.setLoanOutAccount(creditEntrust.getWaitApprNo());
		//贷款回款账户机构名称
		caseLimitApply.setLoanBackAccountBranchName(creditEntrust.getCollectionAccountBank());
		//贷款回款账户账户名 
		caseLimitApply.setLoanBackAccountName(creditEntrust.getCollectionAccountName());
		//贷款回款账户
		caseLimitApply.setLoanBackAccount(creditEntrust.getCollectionAccountNo());
		
		EmpDto empDto = CED.getLoginUser();
		//登录人
		String createBy = empDto.getEmpCd();
		//登录名称
		String empNm = empDto.getEmpNm();
		
		//登录人部门
		String orgCd = empDto.getOrgCd();
		//创建人部门
		caseLimitApply.setCreateOrgCd(orgCd);
		//创建人code
		caseLimitApply.setCreateBy(createBy);
		//申请人code
		caseLimitApply.setApplyEmpCode(createBy);
		//申请人姓名
		caseLimitApply.setApplyEmpName(empNm);
		//申请时间 
		caseLimitApply.setApplyDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));
		//已申请未配资金状态
		caseLimitApply.setEffectiveStatus(CaseLimitApply.FILED_WITHOUT_MONEY);
		//案件 已申请未配资金状态
		caseApply.setActualLimitStatus(CaseLimitApply.FILED_WITHOUT_MONEY);
		caseLimitApply = this.saveOrUpdateEntity(caseLimitApply);
		caseApplyService.updateEntity(caseApply);
		
		return caseLimitApply;
		
	}
	
	@Transactional(rollbackFor=Exception.class)
	@Override
	public void matchLimit(String caseLimitApplyId) throws Exception {
		CaseLimitApply caseLimitApply = this.findOne(caseLimitApplyId);
		logger.info("caseApplyId:"+caseLimitApply.getCaseApplyId()+"---匹配金额："+caseLimitApply.getApplyLimitAmount());
		CaseApply caseApply = caseApplyService.findOne(caseLimitApply.getCaseApplyId());
		
		if(!CaseLimitApply.FILED_WITHOUT_MONEY.equals(caseApply.getActualLimitStatus())){
			throw new BusinessException("10010003","已申请未配资金状态才能匹配资金");
		}
		
		BigDecimal applyLimitAmount = caseLimitApply.getApplyLimitAmount();
		//分配金额成功 分配日期
		caseLimitApply
				.setAllotDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_SHORT_SIMPLE_FORMAT));
		//已配资金
		caseLimitApply.setEffectiveStatus(CaseLimitApply.ALLOCATED_FUNDS);
		//案件已配资金
		caseApply.setActualLimitStatus(CaseLimitApply.ALLOCATED_FUNDS);
		//案件已分配金额
		caseApply.setActualApplyAmount(applyLimitAmount);
		this.updateEntity(caseLimitApply);
		//案件合同合同金额设置
		ConCaseContract conCaseContract = conCaseContractService.findByCaseApplyId(caseLimitApply.getCaseApplyId());
		conCaseContract.setContractAmount(applyLimitAmount);
		conCaseContractService.updateEntity(conCaseContract);
		caseApplyService.updateEntity(caseApply);
		
	}

	@Transactional(rollbackFor=Exception.class)
	@Override
	public void cancelApplyLimit(String caseLimitApplyId) throws Exception {

		CaseLimitApply caseLimitApply = this.customReposity.findOne(caseLimitApplyId);
		String effectiveStatus = caseLimitApply.getEffectiveStatus();
		if(!CaseLimitApply.FILED_WITHOUT_MONEY.equals(effectiveStatus)){
			throw new BusinessException("10010013", "已申请未配资金才能取消申请！");
		}
		//案件
		CaseApply caseApply = caseApplyService.findOne(caseLimitApply.getCaseApplyId());
		EmpDto empDto = CED.getLoginUser();
		//登录人
		String createBy = empDto.getEmpCd();
		//登录名称
		String empNm = empDto.getEmpNm();
		
		//取消人code
		caseLimitApply.setCancelEmpCode(createBy);
		//取消人name
		caseLimitApply.setCancelEmpName(empNm);
		//取消时间
		caseLimitApply.setCancelDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_SHORT_SIMPLE_FORMAT));
		//案件额度实际申请金额
		caseApply.setActualApplyAmount(BigDecimal.ZERO);
		//修改为未申请状态
		caseLimitApply.setEffectiveStatus(CaseLimitApply.NOT_APPLY);
		//修改案件为未申请状态
		caseApply.setActualLimitStatus(CaseLimitApply.NOT_APPLY);
		//跟新
		this.updateEntity(caseLimitApply);
		caseApplyService.updateEntity(caseApply);
	}

	@Transactional(rollbackFor=Exception.class)
	@Override
	public void cancelMatchLimit(String caseLimitApplyId) throws Exception {
		CaseLimitApply caseLimitApply = this.customReposity.findOne(caseLimitApplyId);
		//案件
		CaseApply caseApply = caseApplyService.findOne(caseLimitApply.getCaseApplyId());
		String effectiveStatus = caseLimitApply.getEffectiveStatus();
		if(!CaseLimitApply.ALLOCATED_FUNDS.equals(effectiveStatus)){
			throw new BusinessException("10010013", "已配资金才能取消匹配资金！");
		}
		//案件合同合同金额设置为0
		ConCaseContract conCaseContract = conCaseContractService.findByCaseApplyId(caseLimitApply.getCaseApplyId());
		conCaseContract.setContractAmount(BigDecimal.ZERO);
		conCaseContractService.updateEntity(conCaseContract);
				
		//修改为未申请状态
		caseLimitApply.setEffectiveStatus(CaseLimitApply.FILED_WITHOUT_MONEY);
		//修改案件为未申请状态
		caseApply.setActualLimitStatus(CaseLimitApply.FILED_WITHOUT_MONEY);
		//案件额度实际申请金额
		caseApply.setActualApplyAmount(BigDecimal.ZERO);
		
		this.updateEntity(caseLimitApply);
		caseApplyService.updateEntity(caseApply);
	}

	

}
