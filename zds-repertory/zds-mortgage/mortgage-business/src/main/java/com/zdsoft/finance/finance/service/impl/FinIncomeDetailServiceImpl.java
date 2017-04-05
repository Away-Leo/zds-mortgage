package com.zdsoft.finance.finance.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.finance.entity.FinIncomeDetail;
import com.zdsoft.finance.finance.repository.FinIncomeDetailRepository;
import com.zdsoft.finance.finance.service.FinIncomeDetailService;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: FinIncomeDetailServiceImpl.java 
 * @ClassName: FinIncomeDetailServiceImpl 
 * @Description:费用明细接口实现
 * @author longwei 
 * @date 2017年2月16日 下午3:03:32 
 * @version V1.0
 */
@Service("finIncomeDetailService")
public class FinIncomeDetailServiceImpl extends BaseServiceImpl<FinIncomeDetail, FinIncomeDetailRepository> implements FinIncomeDetailService{

	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void deleteByFinIncomeId(String finIncomeId) throws Exception{
		 this.customReposity.delete(finIncomeId);
	}

	@Override
	public List<FinIncomeDetail> selectByExsistData(String caseApplyId,String finIncomeId) throws Exception {
		return this.customReposity.selectByExsistData(caseApplyId,finIncomeId);
	}

	@Override
	public List<FinIncomeDetail> findByFinIncomeId(String finIncomeId) {
		return this.customReposity.findByFinIncomeId( finIncomeId);
	}


}
