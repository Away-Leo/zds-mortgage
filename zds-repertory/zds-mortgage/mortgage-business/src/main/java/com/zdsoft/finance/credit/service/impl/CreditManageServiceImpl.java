package com.zdsoft.finance.credit.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.dto.basedata.AttachmentDto;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.credit.dto.CreditReceiveDto;
import com.zdsoft.finance.credit.entity.HmCreditCardInfo;
import com.zdsoft.finance.credit.entity.HmLoanInfo;
import com.zdsoft.finance.credit.entity.HmPerGuaranteeInfo;
import com.zdsoft.finance.credit.entity.HmQuery;
import com.zdsoft.finance.credit.entity.HmReportBasics;
import com.zdsoft.finance.credit.repository.HmCreditCardInfoRepository;
import com.zdsoft.finance.credit.repository.HmLoanInfoRepository;
import com.zdsoft.finance.credit.repository.HmPerGuaranteeInfoRepository;
import com.zdsoft.finance.credit.repository.HmReportBasicsRepository;
import com.zdsoft.finance.credit.service.CreditManageService;
import com.zdsoft.finance.credit.service.HmQueryService;
import com.zdsoft.finance.credit.service.assist.CreditReceiveService;
import com.zdsoft.finance.credit.service.assist.CreditSummarize;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CreditManageServiceImpl.java 
 * @ClassName: CreditManageServiceImpl 
 * @Description: 征信管理
 * @author gufeng 
 * @date 2017年2月17日 下午3:45:47 
 * @version V1.0
 */
@Service("creditManageService")
public class CreditManageServiceImpl implements CreditManageService{

	@Autowired
	private HmQueryService hmQueryService;
	
	@Autowired
	private CreditReceiveService creditReceiveService;
	@Autowired
	private CreditSummarize creditSummarize;
	@Autowired
	private HmCreditCardInfoRepository hmCreditCardInfoRepository;
	@Autowired
	private HmReportBasicsRepository hmReportBasicsRepository;
	@Autowired
	private HmPerGuaranteeInfoRepository hmPerGuaranteeInfoRepository;
	@Autowired
	private HmLoanInfoRepository hmLoanInfoRepository;
	@Log
	private Logger logger;

	@Override
	public Boolean getCreditData(String creditNo, String caseApplyId)throws BusinessException {
		List<HmQuery> list = hmQueryService.findByQueryCredNum(creditNo,caseApplyId);
		if(ObjectHelper.isEmpty(list) || list.size() == 0){
			return false;
		}else{
			return true;
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void receiveData(CreditReceiveDto dto) throws BusinessException {
		if(ObjectHelper.isEmpty(dto) || ObjectHelper.isEmpty(dto.getFuncName()) || ObjectHelper.isEmpty(dto.getJsonData())){
			logger.error("====所解析数据错误！");
			throw new BusinessException("100000001","所解析数据错误！");
		}
		logger.info("====解析start====");
		//保存HmQuery
		HmQuery hq = hmQueryService.soloSave(dto);
		if(ObjectHelper.isEmpty(hq) || ObjectHelper.isEmpty(hq.getId())){
			logger.error("====保存查询数据失败！");
			throw new BusinessException("100000002","保存查询数据失败！");
		}
		if(dto.getFuncName().toLowerCase().indexOf("json") != -1){
			//数据
			creditReceiveService.receiveJson(hq.getId(),dto.getQueryCredNum(),dto.getJsonData());
			//计算
			creditSummarize.creditSummarizeSave(hq);
		}else if(dto.getFuncName().toLowerCase().indexOf("pdf") != -1){
			//pdf
			AttachmentDto attDto = creditReceiveService.receivePDF(hq.getId(),dto.getQueryName(),dto.getJsonData());
			hq = hmQueryService.saveQueryAttr(hq.getId(),attDto.getId());
		}
	}

	@Override
	public List<HmCreditCardInfo> findByCardNum(String cardNum) throws BusinessException {
		if(ObjectHelper.isEmpty(cardNum)){
			throw new BusinessException("1000000001","证件号为空");
		}
		List<HmQuery> queryList = hmQueryService.findByNewCardNum(cardNum,"0");
		if(ObjectHelper.isEmpty(queryList) || queryList.size() == 0){
			throw new BusinessException("100000002","未找到对应证件号的数据,cardNum:" + cardNum);
		}
		HmQuery query = queryList.get(0);
		return hmCreditCardInfoRepository.findByQueryId(query.getId());
	}

	@Override
	public List<HmReportBasics> findByCaseApplyId(String caseApplyId) throws BusinessException {
		if(ObjectHelper.isEmpty(caseApplyId)){
			throw new BusinessException("1000000001","案件号为空");
		}
		List<HmQuery> queryList = hmQueryService.findByObjectIdLike(caseApplyId);
		if(ObjectHelper.isEmpty(queryList) || queryList.size() == 0){
			return null;
		}
		HmQuery query = queryList.get(0);
		return hmReportBasicsRepository.findByQueryId(query.getId());
	}

	@Override
	public List<HmPerGuaranteeInfo> findPerGuaranteeInfo(String caseApplyId) throws BusinessException {
		if(ObjectHelper.isEmpty(caseApplyId)){
			throw new BusinessException("1000000001","案件号为空");
		}
		List<HmQuery> queryList = hmQueryService.findByObjectIdLike(caseApplyId);
		if(ObjectHelper.isEmpty(queryList) || queryList.size() == 0){
			return null;
		}
		HmQuery query = queryList.get(0);
		return hmPerGuaranteeInfoRepository.findByQueryId(query.getId());
	}

	@Override
	public List<HmCreditCardInfo> findCreditCardInfo(String caseApplyId) throws BusinessException {
		if(ObjectHelper.isEmpty(caseApplyId)){
			throw new BusinessException("1000000001","案件号为空");
		}
		List<HmQuery> queryList = hmQueryService.findByObjectIdLike(caseApplyId);
		if(ObjectHelper.isEmpty(queryList) || queryList.size() == 0){
			return null;
		}
		HmQuery query = queryList.get(0);
		return hmCreditCardInfoRepository.findByQueryId(query.getId());
	}

	@Override
	public List<HmLoanInfo> findLoanInfo(String caseApplyId) throws BusinessException {
		if(ObjectHelper.isEmpty(caseApplyId)){
			throw new BusinessException("1000000001","案件号为空");
		}
		List<HmQuery> queryList = hmQueryService.findByObjectIdLike(caseApplyId);
		if(ObjectHelper.isEmpty(queryList) || queryList.size() == 0){
			return null;
		}
		HmQuery query = queryList.get(0);
		return hmLoanInfoRepository.findByQueryId(query.getId());
	}

	
}
