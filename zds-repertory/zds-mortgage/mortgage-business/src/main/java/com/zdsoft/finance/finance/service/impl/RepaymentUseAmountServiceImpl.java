package com.zdsoft.finance.finance.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.finance.entity.RepaymentUseAmount;
import com.zdsoft.finance.finance.repository.RepaymentUseAmountRepostory;
import com.zdsoft.finance.finance.service.RepaymentUseAmountService;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: RepaymentUseAmountServiceImpl.java 
 * @ClassName: RepaymentUseAmountServiceImpl 
 * @Description: 收款单资金转供款接口实现
 * @author jincheng 
 * @date 2017年3月21日 下午10:20:24 
 * @version V1.0
 */
@Service("repaymentUseAmountService")
public class RepaymentUseAmountServiceImpl extends BaseServiceImpl<RepaymentUseAmount, RepaymentUseAmountRepostory>  implements RepaymentUseAmountService{
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public RepaymentUseAmount saveOrUpdateRepaymentUseAmount(RepaymentUseAmount entity) throws Exception {
		return this.saveEntity(entity);
	}

	@Override
	public List<RepaymentUseAmount> findByReceiptId(String receiptId) {
		return this.customReposity.findByReceiptId(receiptId);
	}

	@Override
	public List<RepaymentUseAmount> findByCaseApplyId(String caseApplyId) {
		return this.customReposity.findByCaseApplyId(caseApplyId);
	}
	
	@Override
	public List<RepaymentUseAmount> findByFeeAmountId(String feeAmountId) {
		return this.customReposity.findByFeeAmountId(feeAmountId);
	}
}
