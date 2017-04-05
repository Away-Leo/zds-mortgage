package com.zdsoft.finance.marketing.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.marketing.entity.CaseApplyAfterCustomer;
import com.zdsoft.finance.marketing.repository.CaseApplyAfterCustomerRepository;
import com.zdsoft.finance.marketing.service.CaseApplyAfterCustomerService;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CaseApplyAfterCustomerServiceImpl.java 
 * @ClassName: CaseApplyAfterCustomerServiceImpl 
 * @Description: 贷后客户参与类型service实现
 * @author xj 
 * @date 2017年3月2日 下午5:41:30 
 * @version V1.0
 */
@Service("caseApplyAfterCustomerService")
public class CaseApplyAfterCustomerServiceImpl extends BaseServiceImpl<CaseApplyAfterCustomer, CaseApplyAfterCustomerRepository> implements CaseApplyAfterCustomerService {


	@Override
	public List<CaseApplyAfterCustomer> findByCaseApplyIdAndJoinType(String caseApplyId, String joinType) {
		return this.customReposity.findByCaseApplyIdAndJoinType(caseApplyId, joinType);
	}

}
