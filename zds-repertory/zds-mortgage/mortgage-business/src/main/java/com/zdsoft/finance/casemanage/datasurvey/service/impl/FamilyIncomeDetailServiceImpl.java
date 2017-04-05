package com.zdsoft.finance.casemanage.datasurvey.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.casemanage.datasurvey.entity.FamilyIncomeDetail;
import com.zdsoft.finance.casemanage.datasurvey.repository.FamilyIncomeDetailRepository;
import com.zdsoft.finance.casemanage.datasurvey.service.FamilyIncomeDetailService;
import com.zdsoft.finance.common.base.CustomRepository;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: FamilyIncomeDetailServiceImpl.java 
 * @ClassName: FamilyIncomeDetailServiceImpl 
 * @Description: 家庭收入详情 月份降序排列
 * @author liuhuan 
 * @date 2017年2月13日 上午10:46:28 
 * @version V1.0
 */
@Service("familyIncomeDetail")
public class FamilyIncomeDetailServiceImpl extends BaseServiceImpl<FamilyIncomeDetail, CustomRepository<FamilyIncomeDetail,String>>
implements FamilyIncomeDetailService{

	@Autowired
	private FamilyIncomeDetailRepository familyIncomeDetailRepository;
	
	@Override
	public List<FamilyIncomeDetail> findByFamilyIncomeId(String familyIncomeId) {
		return familyIncomeDetailRepository.findByFamilyIncomeIdOrderByRealMonthDesc(familyIncomeId);
	}

	@Override
	@Transactional
	public void deleteByFamilyIncomeId(String familyIncomeId) {
		familyIncomeDetailRepository.deleteByFamilyIncomeId(familyIncomeId);
	}
	
}
