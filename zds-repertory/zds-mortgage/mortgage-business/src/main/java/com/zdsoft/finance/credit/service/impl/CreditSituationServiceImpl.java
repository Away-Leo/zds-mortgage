package com.zdsoft.finance.credit.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.basedata.AttachmentDto;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.enums.ENUM_MEDIA_TYPE;
import com.zdsoft.finance.common.utils.DateUtil;
import com.zdsoft.finance.credit.entity.CreditSituation;
import com.zdsoft.finance.credit.entity.CustomerCreditStatistics;
import com.zdsoft.finance.credit.entity.MyCredit;
import com.zdsoft.finance.credit.entity.MyCreditCard;
import com.zdsoft.finance.credit.repository.CreditSituationRepository;
import com.zdsoft.finance.credit.service.CreditSituationService;
import com.zdsoft.finance.credit.service.CustomerCreditStatisticsService;
import com.zdsoft.finance.credit.service.MyCreditCardService;
import com.zdsoft.finance.credit.service.MyCreditService;
import com.zdsoft.finance.customer.entity.Credit;
import com.zdsoft.finance.customer.entity.CreditAttachment;
import com.zdsoft.finance.customer.service.CreditAttachmentService;
import com.zdsoft.finance.customer.service.CreditService;
import com.zdsoft.finance.filestore.entity.FileStore;
import com.zdsoft.finance.filestore.service.FileStoreService;
import com.zdsoft.framework.core.common.exception.BusinessException;
import com.zdsoft.framework.core.common.util.ObjectHelper;

import net.sf.json.JSONArray;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CreditSituationServiceImpl.java
 * @ClassName: CreditSituationServiceImpl
 * @Description: 征信综合情况 接口实现
 * @author dengyy
 * @date 2017年2月23日 上午9:40:47
 * @version V1.0
 */
@Service("creditSituationService")
public class CreditSituationServiceImpl extends BaseServiceImpl<CreditSituation, CreditSituationRepository>
		implements CreditSituationService {

	@Autowired
	private CED CED;
	@Autowired
	private CreditAttachmentService creditAttachmentService;
	@Autowired
	private FileStoreService fileStoreService;
	@Resource
	private CustomerCreditStatisticsService customerCreditStatisticsService;
	@Autowired
	private MyCreditService myCreditService;
	@Autowired
	private MyCreditCardService myCreditCardService;
	@Autowired
	private CreditService creditService;
	
	@Override
	public CreditSituation findByCreditId(String creditId) {
		if(ObjectHelper.isEmpty(creditId)){
            throw new BusinessException("10010004", "征信id不能为空！");
        }
		return this.customReposity.findByCreditId(creditId);
	}

	@Override
	public List<AttachmentDto> findAttachments(String creditId) throws Exception {
		if(ObjectHelper.isEmpty(creditId)){
            throw new BusinessException("10010004", "征信id不能为空！");
        }
		List<AttachmentDto> list = new ArrayList<AttachmentDto>();
		List<CreditAttachment> atts = creditAttachmentService.findByCreditId(creditId);
		List<String> attachmentIds = new ArrayList<String>();
		if (!CollectionUtils.isEmpty(atts)) {
			for (CreditAttachment att : atts) {
				FileStore f = fileStoreService.findOne(att.getFileStore().getId());
				// update by jingyh FileType存储的为ENUM_MEDIA_TYPE的值
				if (ENUM_MEDIA_TYPE.IMG.classType.equals(f.getFileType()) && !f.getIsDeleted()) {
					attachmentIds.add(f.getAttachmentId());
				}
			}
		}

		if (!CollectionUtils.isEmpty(attachmentIds)) {
			list = CED.findAttachmentByIds(attachmentIds);
		}
		return list;
	}
	
	@Override
	@Transactional
	public CreditSituation saveCreditSituation(CreditSituation creditSituation) throws Exception{
		if(ObjectHelper.isEmpty(creditSituation)){
            throw new BusinessException("10010004", "综合征信信息不能为空！");
        }
		//保存征信综合信息
		CreditSituation entity = null;
		EmpDto empDto = CED.getLoginUser();
		if(ObjectHelper.isNotEmpty(creditSituation.getId())){
		    entity = this.customReposity.findOne(creditSituation.getId());
		    entity.setUpdateBy(empDto.getEmpCd());
		    entity.setUpdateOrgCd(empDto.getOrgCd());
		}else{
		    entity = new CreditSituation();
		    entity.setCreateBy(empDto.getEmpCd());
		    entity.setCreateOrgCd(empDto.getOrgCd());
		}
		
		BeanUtils.copyProperties(creditSituation, entity,new String[]{"id","createTime","isDeleted","createBy","createOrgCd","version"});
		entity = this.saveOrUpdateEntity(entity);
		
		
		//更新贷款信息 和信用卡信息
		List<MyCredit> myCredits = new  ArrayList<MyCredit>();
		List<MyCreditCard> myCreditCards = new ArrayList<MyCreditCard>() ;
		if(ObjectHelper.isNotEmpty(creditSituation.getCredit())){
		    //获取本人征信信息
		    myCredits = this.saveOrUpdateCredit(creditSituation.getCredit(), entity.getId());
		    entity.setMyCredit(myCredits);
		}
		if(ObjectHelper.isNotEmpty(creditSituation.getCreditCard())){
		    //获取本人信用卡信息
            myCreditCards = this.saveOrUpdateCreditCard(creditSituation.getCreditCard(), entity.getId());
            entity.setMyCreditCard(myCreditCards);
        }
		
		entity = this.saveOrUpdateEntity(entity);
		//贷款总额
		BigDecimal loanTotalAmount = BigDecimal.ZERO;
		// 贷款总笔数
		Integer loanTotalNum = myCredits.size();
		//已结清贷款笔数
		Integer endLoanTotalNum = 0;
		//已结清贷款总额
		BigDecimal endLoanTotalAmount = BigDecimal.ZERO;
		//未结清笔数
		Integer loaningTotalNum = 0;
		//未结清总额
		BigDecimal loaningTotalAmount = BigDecimal.ZERO;
		//信用卡发卡总额
		BigDecimal creditApplyTotalAmount = BigDecimal.ZERO;
		//发放张数
		Integer creditApplyNum = myCreditCards.size();
		//已使用额度
		BigDecimal creditUsedAmount = BigDecimal.ZERO;
		//信用卡使用率
		Double creditUsedRate = 0.0d;
		//贷款逾期：逾期总笔数
		Integer loanOverdueNum = 0;
		//贷款逾期：未逾期笔数
		Integer loanNotOverNum = 0;
		//贷款逾期：超标笔数 zy-后续处理
		Integer loanOverMarkNum = 0;
		//贷款逾期：单笔最高逾期期数
		Integer loanMaxOverduePeriods = 0;
		//贷款逾期：单笔最高逾期金额 
		BigDecimal loanMaxOverAmount = BigDecimal.ZERO;
		
		//贷款统计
		for (MyCredit myCredit : myCredits) {
		    loanTotalAmount = loanTotalAmount.add(myCredit.getLoanAmount());
			if (myCredit.getLoanBalance().compareTo(BigDecimal.ZERO) == 0) {
			    endLoanTotalNum = endLoanTotalNum+1;
			    endLoanTotalAmount = endLoanTotalAmount.add(myCredit.getLoanAmount());
			} else {
			    loaningTotalNum = loaningTotalNum+1;
			    loaningTotalAmount= loaningTotalAmount.add(myCredit.getLoanAmount());
			}
			if (myCredit.getCurrentOverdueAmount().compareTo(BigDecimal.ZERO) == 1) {
			    loanOverdueNum = loanOverdueNum+1;
				Integer tempVal = myCredit.getMaximumOverdue();
				BigDecimal tempAmt = myCredit.getCurrentOverdueAmount();
				loanMaxOverduePeriods = (loanMaxOverduePeriods.intValue() > tempVal.intValue()) ? loanMaxOverduePeriods
						: tempVal;
				loanMaxOverAmount = loanMaxOverAmount.compareTo(tempAmt) == 1 ? loanMaxOverAmount : tempAmt;
			} else {
			    loanNotOverNum = loanNotOverNum+1;
			}
			
			//超标笔数处理
			
		}
		
		//信用卡统计
		for (MyCreditCard myCreditCard : myCreditCards) {
		    creditApplyTotalAmount = creditApplyTotalAmount.add(myCreditCard.getCreditLimit());
		    creditUsedAmount= creditUsedAmount.add(myCreditCard.getCurrentOverdraft());
		}
		
		if (creditApplyTotalAmount.compareTo(BigDecimal.ZERO) == 1) {
			creditUsedRate = creditUsedAmount.divide(creditApplyTotalAmount, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
		}
		//获取客户征信统计
		CustomerCreditStatistics ccs = new CustomerCreditStatistics();
		ccs.setCaseApplyId(creditSituation.getCaseApplyId());
		ccs.setCustomerId(creditSituation.getCustomerId());
		ccs.setCustomerName(creditSituation.getCustomerName());
		ccs.setSourceFrom(CustomerCreditStatistics.SOURCE_ONLINE);
		ccs.setSourceMarkId(entity.getId());
		//保存征信统计信息
		ccs.setLoanTotalAmount(loanTotalAmount);
		ccs.setLoanTotalNum(loanTotalNum);
		ccs.setEndLoanTotalNum(endLoanTotalNum);
		ccs.setEndLoanTotalAmount(endLoanTotalAmount);
		ccs.setLoaningTotalNum(loaningTotalNum);
		ccs.setLoaningTotalAmount(loaningTotalAmount);
		ccs.setCreditApplyTotalAmount(creditApplyTotalAmount);
		ccs.setCreditApplyNum(creditApplyNum);
		ccs.setCreditUsedAmount(creditUsedAmount);
		ccs.setCreditUsedRate(creditUsedRate);
		ccs.setLoanOverdueNum(loanOverdueNum);
		ccs.setLoanNotOverNum(loanNotOverNum);
		ccs.setLoanOverMarkNum(loanOverMarkNum);
		ccs.setLoanMaxOverduePeriods(loanMaxOverduePeriods);
		ccs.setLoanMaxOverAmount(loanMaxOverAmount);
		// 保存或更新
		customerCreditStatisticsService.saveOrUpdateStatistic(ccs);
		
		//更新征信信息
		 Credit credit = creditService.findOne(creditSituation.getCreditId());
		 credit.setInputStatus("1");
		 credit.setInputDate(DateUtil.getCurrentDate());
		 credit.setInputManCode(CED.getLoginUser().getEmpCd());
		 credit.setLinkStatusCode(Credit.LINK_STATUS_SUCCESSFUL);
		 creditService.updateEntity(credit);
		
        return entity;
	}
	
	/**
	 * 
	 * @Title: saveOrUpdateCredit 
	 * @Description: 保存贷款信息   json数据
	 * @author dengyy  
	 * @param myCredit 贷款信息 json数据
	 * @param creditSituationId  征信综合情况id
	 * @return
	 * @throws com.zdsoft.finance.common.exception.BusinessException 
	 */
	@SuppressWarnings({ "static-access", "unchecked" })
    @Transactional
	private List<MyCredit> saveOrUpdateCredit(String myCredit,String creditSituationId) throws com.zdsoft.finance.common.exception.BusinessException{
	    //4、 解析案件房产信息  json解析
        JSONArray jsonArray = JSONArray.fromObject(myCredit);  
        List<MyCredit> list =  (List<MyCredit>)jsonArray.toCollection(jsonArray, MyCredit.class);
        List<MyCredit> listRt = new ArrayList<MyCredit>();
        for (MyCredit myCredit2 : list) {
            MyCredit credit = myCreditService.findOne(myCredit2.getId());
            credit.setCreditSituationId(creditSituationId);
            credit = myCreditService.saveOrUpdateEntity(credit);
            listRt.add(credit);
        }
        return listRt;
	}
	
	/**
	 * 
	 * @Title: saveOrUpdateCreditCard 
	 * @Description: 保存信用卡信息  json
	 * @author dengyy 
	 * @param myCreditCard 信用卡信息json
	 * @param creditSituationId  征信综合情况id
	 * @return
	 * @throws com.zdsoft.finance.common.exception.BusinessException
	 */
	@SuppressWarnings({ "static-access", "unchecked" })
    @Transactional
    private List<MyCreditCard> saveOrUpdateCreditCard(String myCreditCard,String creditSituationId) throws com.zdsoft.finance.common.exception.BusinessException{
        //4、 解析案件房产信息  json解析
        JSONArray jsonArray = JSONArray.fromObject(myCreditCard);  
        List<MyCreditCard> list =  (List<MyCreditCard>)jsonArray.toCollection(jsonArray, MyCreditCard.class);
        List<MyCreditCard> listRt = new ArrayList<MyCreditCard>();
        for (MyCreditCard myCreditCard2 : list) {
            MyCreditCard creditCard = myCreditCardService.findOne(myCreditCard2.getId());
            creditCard.setCreditSituationId(creditSituationId);
            creditCard = myCreditCardService.saveOrUpdateEntity(creditCard);
            listRt.add(creditCard);
        }
        return listRt;
    }

	@Override
	public CreditSituation findByCustomerId(String customerId) {
		if(ObjectHelper.isEmpty(customerId)){
            throw new BusinessException("10010004", "客户id不能为空！");
        }
		return this.customReposity.findByCustomerId(customerId) ;
	}
	
    @Override
    public List<CreditSituation> findByCaseApplyId(String caseApplyId) throws BusinessException {
        if(ObjectHelper.isEmpty(caseApplyId)){
            throw new BusinessException("10010004", "案件id不能为空！");
        }
        return this.customReposity.findByCaseApplyId(caseApplyId);
    }
}
