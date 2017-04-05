package com.zdsoft.finance.credit.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.credit.entity.CustomerCreditStatistics;
import com.zdsoft.finance.credit.entity.HmLoanInfo;
import com.zdsoft.finance.credit.entity.HmQuery;
import com.zdsoft.finance.credit.entity.MyCredit;
import com.zdsoft.finance.credit.repository.CustomerCreditStatisticsRepository;
import com.zdsoft.finance.credit.repository.HmLoanInfoRepository;
import com.zdsoft.finance.credit.repository.HmQueryRepository;
import com.zdsoft.finance.credit.service.CustomerCreditStatisticsService;
import com.zdsoft.finance.credit.service.HmDataResolverUtil;
import com.zdsoft.finance.credit.service.MyCreditService;
import com.zdsoft.finance.customer.service.CreditService;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/** 
* 版权所有：重庆正大华日软件有限公司
* @Title: CustomerCreditStatisticsServiceImpl.java 
* @Package com.zdsoft.finance.credit.service.impl 
* @Description: 客户征信统计Service实现
* @author jingyh 
* @date 2017年2月22日 下午7:39:36 
* @version V1.0 
*/
@Service("customerCreditStatisticsService")
public class CustomerCreditStatisticsServiceImpl extends BaseServiceImpl<CustomerCreditStatistics, CustomerCreditStatisticsRepository>
		implements CustomerCreditStatisticsService {

	@Resource
	private CaseApplyService caseApplyService;
	
	@Autowired
	private MyCreditService myCreditService;
	
	@Autowired
	private HmQueryRepository hmQueryRepository;
	
	@Autowired
	private HmLoanInfoRepository hmLoanInfoRepository;
	
	@Autowired
	private CreditService creditService;
	
	@Override
	public CustomerCreditStatistics findById(String id) {
		if (ObjectHelper.isNotEmpty(id)) {
			return this.customReposity.findOne(id);
		}
		return null;
	}
	
	@Override
	public List<CustomerCreditStatistics> findByCustomerIdsAndCaseApplyIdAndCaseApplyStage(String caseApplyId, List<String> customerIds, String caseApplyStage) {
		logger.info("根据客户Id集合查询征信统计信息");
		logger.debug("传入客户Id为：{}", customerIds);
		if (ObjectHelper.isNotEmpty(customerIds) && ObjectHelper.isNotEmpty(caseApplyId)) {
			return this.customReposity.findStatisInfoByCustomerIdsAndCaseApplyIdAndStage(caseApplyId, customerIds, caseApplyStage);
		}
		return null;
	}
	
	@Override
	@Transactional
	public CustomerCreditStatistics saveOrUpdateStatistic(CustomerCreditStatistics entity) throws Exception {
		CustomerCreditStatistics entityObj = null;
		boolean isCreate = false; 
		if (ObjectHelper.isEmpty(entity)) {
			throw new BusinessException("10010004", "传入数据为空!");
		}
		if (ObjectHelper.isEmpty(entity.getCaseApplyId()) || ObjectHelper.isEmpty(entity.getCustomerId()) || 
				ObjectHelper.isEmpty(entity.getCustomerName()) ||
				ObjectHelper.isEmpty(entity.getSourceMarkId()) || ObjectHelper.isEmpty(entity.getSourceFrom())) {
			throw new BusinessException("10010004", "必传字段为空!");
		}
		if (ObjectHelper.isNotEmpty(entity.getId())) {
			entityObj = this.customReposity.findOne(entity.getId());
		}
		if (ObjectHelper.isEmpty(entityObj) && ObjectHelper.isNotEmpty(entity.getSourceMarkId())) {
			entityObj = this.customReposity.findBySourceMarkId(entity.getSourceMarkId());
		}
		// 案件阶段为空时，自动获取案件的阶段信息
		if (ObjectHelper.isEmpty(entity.getCaseApplyStage())) {
			CaseApply caseApply = caseApplyService.findOne(entity.getCaseApplyId());
			if (ObjectHelper.isEmpty(caseApply)) {
				throw new BusinessException("10010002", "未找到对应的案件信息!案件Id：" + entity.getCaseApplyId());
			}
			entity.setCaseApplyStage(caseApply.getStage());	
		}
		if ( ObjectHelper.isEmpty(entityObj) && ObjectHelper.isNotEmpty(entity.getCaseApplyId()) && 
				ObjectHelper.isNotEmpty(entity.getCustomerId()) && ObjectHelper.isNotEmpty(entity.getCaseApplyStage()) && 
				ObjectHelper.isNotEmpty(entity.getSourceFrom()) ) {
			// 案件Id，客户Id，案件阶段，来源，定位唯一的一条统计记录，同阶段，同客户覆盖
			entityObj = this.customReposity.findByCustomerIdAndCaseApplyIdAndCaseApplyStageAndSourceFrom(
					entity.getCustomerId(), entity.getCaseApplyId(), entity.getCaseApplyStage(), entity.getSourceFrom());
		}
		if (ObjectHelper.isEmpty(entityObj)) {
			isCreate = true;
			// 新建对象
			entityObj = new CustomerCreditStatistics();
		}
		// 属性赋值
		BeanUtils.copyProperties(entity, entityObj, new String[]{"id", "version", "isDeleted","createTime","updateTime","deleteTime","customerId","caseApplyStage"});
		if (ObjectHelper.isEmpty(entityObj.getCaseApplyStage())) {
			entityObj.setCaseApplyStage(entity.getCaseApplyStage());
		}
		if (ObjectHelper.isEmpty(entityObj.getCustomerId())) {
			entityObj.setCustomerId(entity.getCustomerId());
		}
		if (ObjectHelper.isEmpty(entityObj.getCreateTime())) {
			entityObj.setCreateTime(new Date());
		}
		entityObj.setUpdateTime(new Date());
		// 线下数据来源时，更新征信状态
		creditService.updateCreditState(entityObj.getCaseApplyId(), entityObj.getCustomerId(), entityObj.getCaseApplyStage());
		if (isCreate) {
			return this.customReposity.saveEntity(entityObj);
		} else {
			return this.customReposity.updateEntity(entityObj);
		}
	}

	@Override
	public List<MyCredit> showLoanRecordBySourceMarkId(String sourceMarkId, String sourceFrom)
			throws BusinessException {
		List<MyCredit> list = new ArrayList<MyCredit>();
		if (CustomerCreditStatistics.SOURCE_OFFLINE.equals(sourceFrom)) {
			// 线下的场合，查HmLoanInfo
			HmQuery hmQuery = hmQueryRepository.findOne(sourceMarkId);
			if (hmQuery == null) {
				return list;
			}
			List<HmLoanInfo> loanInfoList = hmLoanInfoRepository.findByQueryId(hmQuery.getId());
			for (HmLoanInfo loanInfo : loanInfoList) {
				// 将HmLoanInfo翻译为MyCredit
				MyCredit credit = new MyCredit();
				credit.setAccountStatusName(loanInfo.getAccountStatus());
				credit.setLoanBalance(loanInfo.getPrincipalAmount());
				credit.setCurrentOverdue(loanInfo.getCurrentOverdueNum());
				credit.setCurrentOverdueAmount(loanInfo.getCurrentOverdueAmount());

				MyCredit resolvedRepayStatusCredit = HmDataResolverUtil
						.resolveRepayStatus(loanInfo.getMonth24RepayStatus());
				credit.setCumulativeOverdue(resolvedRepayStatusCredit.getCumulativeOverdue());
				credit.setMaximumOverdue(resolvedRepayStatusCredit.getMaximumOverdue());
				credit.setCumulativeOverdue12(resolvedRepayStatusCredit.getCumulativeOverdue12());
				credit.setMaximumOverdue12(resolvedRepayStatusCredit.getMaximumOverdue12());

				MyCredit resolvedTitleCredit = HmDataResolverUtil.resolveHmData(loanInfo.getTitleInfo());
				credit.setLoanDate(resolvedTitleCredit.getLoanDate());
				credit.setClosingDate(resolvedTitleCredit.getClosingDate());
				credit.setLoanAmount(resolvedTitleCredit.getLoanAmount());
				credit.setLoanTypesName(resolvedTitleCredit.getLoanTypesName());
				credit.setLoanTerm(resolvedTitleCredit.getLoanTerm());

				list.add(credit);
			}
		} else if (CustomerCreditStatistics.SOURCE_ONLINE.equals(sourceFrom)) {
			// 线上的场合，查询MyCredit
			list = myCreditService.findByCreditSituationId(sourceMarkId);
		} else {
			throw new BusinessException("1001003", "传入参数错误！");
		}
		return list;
	}
}
