package com.zdsoft.finance.casemanage.datasurvey.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.casemanage.datasurvey.entity.FamilyIncome;
import com.zdsoft.finance.casemanage.datasurvey.entity.FamilyIncomeDetail;
import com.zdsoft.finance.casemanage.datasurvey.repository.FamilyIncomeDetailRepository;
import com.zdsoft.finance.casemanage.datasurvey.repository.FamilyIncomeRepository;
import com.zdsoft.finance.casemanage.datasurvey.service.FamilyIncomeDetailService;
import com.zdsoft.finance.casemanage.datasurvey.service.FamilyIncomeService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: FamilyIncomeServiceImpl.java 
 * @ClassName: FamilyIncomeServiceImpl 
 * @Description: 家庭收入
 * @author liuhuan 
 * @date 2017年2月13日 上午10:44:44 
 * @version V1.0
 */
@Service("familyIncomeService")
public class FamilyIncomeServiceImpl extends BaseServiceImpl<FamilyIncome, FamilyIncomeRepository>
implements FamilyIncomeService{

	@Autowired
	private FamilyIncomeDetailRepository familyIncomeDetailRepository;
	@Autowired
	private FamilyIncomeDetailService familyIncomeDetailService;

	@Override
	public List<FamilyIncome> findByCaseApplyId(String caseApplyId) {
		return this.customReposity.findByCaseApplyId(caseApplyId);
	}

	@Override
	@Transactional
	public void deleteByFamilyIncomeId(String familyIncomeId) {
		this.customReposity.delete(familyIncomeId);
		List<FamilyIncomeDetail> familyIncomeDetailList = familyIncomeDetailRepository.findByFamilyIncomeIdOrderByRealMonthDesc(familyIncomeId);
		for (FamilyIncomeDetail familyIncomeDetail : familyIncomeDetailList) {
			familyIncomeDetailRepository.delete(familyIncomeDetail);
		}
	}

	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public FamilyIncome saveFamilyIncome(FamilyIncome familyIncome, List<FamilyIncomeDetail> familyIncomeDetailList) throws BusinessException {
		
		FamilyIncome family = new FamilyIncome();
		if (ObjectHelper.isNotEmpty(familyIncome.getId())) {
			family = this.findOne(familyIncome.getId());
		}
		BeanUtils.copyProperties(familyIncome, family,new String[]{"id","createTime"});
		family = this.saveOrUpdateEntity(family);
		
		//查询当前户主的收入情况
		List<FamilyIncomeDetail> detailList = familyIncomeDetailService.findByFamilyIncomeId(family.getId());
		int i = 0;
		for (FamilyIncomeDetail familyIncomeDetail : familyIncomeDetailList) {
			FamilyIncomeDetail detail = new FamilyIncomeDetail();
			if(ObjectHelper.isNotEmpty(detailList) && detailList.size() > 0){
				detail = familyIncomeDetailService.findOne(detailList.get(i).getId());
				i++;
			}
			detail.setFamilyIncomeId(family.getId());
			detail.setRealMonth(familyIncomeDetail.getRealMonth());
			detail.setRealAmount(familyIncomeDetail.getRealAmount());
			familyIncomeDetailService.saveOrUpdateEntity(detail);
		}
		return family;
	}
}