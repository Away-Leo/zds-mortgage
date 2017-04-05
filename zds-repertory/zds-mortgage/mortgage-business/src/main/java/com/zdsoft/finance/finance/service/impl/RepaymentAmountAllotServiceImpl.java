package com.zdsoft.finance.finance.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.casemanage.receivablePlan.service.ReceivablePlanService;
import com.zdsoft.finance.finance.entity.RepaymentAmountAllot;
import com.zdsoft.finance.finance.entity.RepaymentReceipt;
import com.zdsoft.finance.finance.repository.RepaymentAmountAllotRepostory;
import com.zdsoft.finance.finance.service.RepaymentAmountAllotService;
import com.zdsoft.finance.spi.receivablePlan.CaseReceivableDto;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: RepaymentAmountAllotServiceImpl.java 
 * @ClassName: RepaymentAmountAllotServiceImpl 
 * @Description: 还款-收款单-金额分配接口实现
 * @author jincheng 
 * @date 2017年2月17日 下午5:06:29 
 * @version V1.0
 */
@Service("repaymentAmountAllotService")
public class RepaymentAmountAllotServiceImpl extends BaseServiceImpl<RepaymentAmountAllot, RepaymentAmountAllotRepostory>  implements RepaymentAmountAllotService{

	@Autowired
	private ReceivablePlanService receivablePlanService;  
	 
	@Override
	public List<RepaymentAmountAllot> getRepaymentAmountAllotList(RepaymentReceipt receipt) {
		
		if(ObjectHelper.isNotEmpty(receipt.getId())&&receipt.getIsEdit()){//进入修改页面调用此方法
			return this.customReposity.findByReceiptId(receipt.getId());
		}
		
		List<CaseReceivableDto> planList=receivablePlanService.getCaseReceivableList(receipt.getCaseApplyId(), receipt.getPaidRepayDate(), true);
		List<RepaymentAmountAllot> allotList=new ArrayList<RepaymentAmountAllot>();
		BigDecimal   collectionAmount=ObjectHelper.isNotEmpty(receipt.getCollectionAmount())?receipt.getCollectionAmount():BigDecimal.ZERO;
		for(CaseReceivableDto plan:planList){
			
			if(plan.getSettlement()){//判断当期是否还清
				continue;
			}
			
			RepaymentAmountAllot rat=new RepaymentAmountAllot();
			BeanUtils.copyProperties(plan, rat,new String[]{"id"});
			rat.setPaidDamages(BigDecimal.ZERO);
			rat.setPaidInterestAmount(BigDecimal.ZERO);
			rat.setPaidPrincipalAmount(BigDecimal.ZERO);
			rat.setPaidPenalty(BigDecimal.ZERO);
			rat.setPaidServiceFee(BigDecimal.ZERO);
			rat.setCurrentPaidPenalty(BigDecimal.ZERO);
			rat.setOvreDueDay(plan.getDays());//逾期天数
			//分配金额
			if(BigDecimal.ZERO.compareTo(collectionAmount)<0&&ObjectHelper.isNotEmpty(receipt.getRecordMethod())&&!"YWDM009402".equals(receipt.getRecordMethod())){
				
					if((rat.getDplanPenalty().compareTo(BigDecimal.ZERO)>0||rat.getDcurrentPlanPenalty().compareTo(BigDecimal.ZERO)>0)){
						if("YWDM009501".equals(receipt.getOverdueWay())){
							if(collectionAmount.compareTo(rat.getDplanPenalty())>=0){
								rat.setPaidPenalty(rat.getDplanPenalty());//罚息
								collectionAmount=collectionAmount.subtract(rat.getDplanPenalty());
							}else{
								rat.setPaidPenalty(collectionAmount);//罚息
								collectionAmount=BigDecimal.ZERO;
							}
						}else{
							if(collectionAmount.compareTo(rat.getDcurrentPlanPenalty())>=0){
								rat.setCurrentPaidPenalty(rat.getDcurrentPlanPenalty());//当期罚息
								collectionAmount=collectionAmount.subtract(rat.getDcurrentPlanPenalty());
							}else{
								rat.setCurrentPaidPenalty(collectionAmount);//当期罚息
								collectionAmount=BigDecimal.ZERO;
							}
						}
					}
					
					if(collectionAmount.compareTo(rat.getDplanInterestAmount())>=0){
						rat.setPaidInterestAmount(rat.getDplanInterestAmount());//利息
						collectionAmount=collectionAmount.subtract(rat.getDplanInterestAmount());
					}else{
						rat.setPaidInterestAmount(collectionAmount);//利息
						collectionAmount=BigDecimal.ZERO;
					}
					
					if(collectionAmount.compareTo(rat.getDplanPrincipalAmount())>=0){
						rat.setPaidPrincipalAmount(rat.getDplanPrincipalAmount());//本金
						collectionAmount=collectionAmount.subtract(rat.getDplanPrincipalAmount());
					}else{
						rat.setPaidPrincipalAmount(collectionAmount);//利息
						collectionAmount=BigDecimal.ZERO;
					}
					
					if("YWDM009301".equals(receipt.getResidual())){//剩余金额分配到罚息
						if("YWDM009501".equals(receipt.getOverdueWay())){
							rat.setPaidPenalty(rat.getDplanPenalty().add(collectionAmount));
							collectionAmount=BigDecimal.ZERO;
						}else{
							rat.setCurrentPaidPenalty(rat.getCurrentPaidPenalty().add(collectionAmount));//当期罚息
							collectionAmount=BigDecimal.ZERO;
						}
					}
			}
			allotList.add(rat);
		}
		return allotList;
	}

	@Override
	public List<RepaymentAmountAllot> findByReceiptId(String receiptId) {
		return this.customReposity.findByReceiptId( receiptId);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void deleteRepaymentAmountAllot(String id) throws Exception{
		this.customReposity.delete(id);
	}

	@Override
	public List<RepaymentAmountAllot> findByPlanId(String planId) {
		return this.customReposity.findByPlanId(planId);
	}

	@Override
	public List<RepaymentAmountAllot> findRepaymentAmountAllotByPlanId(String planId) {
		return this.customReposity.findRepaymentAmountAllotByPlanId(planId);
	}
}
