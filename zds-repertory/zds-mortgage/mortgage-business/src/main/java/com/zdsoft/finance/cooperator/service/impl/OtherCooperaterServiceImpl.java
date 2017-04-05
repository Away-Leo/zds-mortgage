package com.zdsoft.finance.cooperator.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.cooperator.entity.OtherCooperater;
import com.zdsoft.finance.cooperator.repository.OtherCooperaterRepository;
import com.zdsoft.finance.cooperator.service.OtherCooperaterService;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: OtherCooperaterServiceImpl.java
 * @ClassName: OtherCooperaterServiceImpl
 * @Description: 其他合作单位ServiceImpl
 * @author liuwei
 * @date 2017年3月2日 下午8:05:20
 * @version V1.0
 */
@Service("otherCooperaterService")
public class OtherCooperaterServiceImpl extends
		BaseServiceImpl<OtherCooperater, CustomRepository<OtherCooperater, String>> implements OtherCooperaterService {

	@Autowired
	CED CED;

	@Autowired
	OtherCooperaterRepository otherCooperaterRepository;

	@Override
	@Transactional
	public OtherCooperater saveOrUpdateOtherCooperater(OtherCooperater otherCooperater) throws Exception {
		// 判断id是否存在
		if (ObjectHelper.isNotEmpty(otherCooperater.getId())) { // id存在，修改
			OtherCooperater oldOtherCooperater = otherCooperaterRepository.findOne(otherCooperater.getId());

			// 设置基本信息
			oldOtherCooperater.setUpdateBy(CED.getLoginUser().getEmpCd());
			oldOtherCooperater.setUpdateOrgCd(CED.getLoginUser().getOrgCd());
			oldOtherCooperater.setUpdateTime(new Date());

			// 设置其他合作单位信息
			oldOtherCooperater.setEnterpriseType(otherCooperater.getEnterpriseType());
			oldOtherCooperater.setCompanyName(otherCooperater.getCompanyName());
			oldOtherCooperater.setShortName(otherCooperater.getShortName());
			oldOtherCooperater.setCompanyType(otherCooperater.getCompanyType());
			oldOtherCooperater.setParentOrg(otherCooperater.getParentOrg());
			oldOtherCooperater.setCompanyBelong(otherCooperater.getCompanyBelong());
			oldOtherCooperater.setCompanyBelongRelevanceCode(otherCooperater.getCompanyBelongRelevanceCode());
			oldOtherCooperater.setCompanyBelongRelevanceName(otherCooperater.getCompanyBelongRelevanceName());
			oldOtherCooperater.setTelephone(otherCooperater.getTelephone());
			oldOtherCooperater.setFax(otherCooperater.getFax());
			oldOtherCooperater.setZipCode(otherCooperater.getZipCode());
			oldOtherCooperater.setWebsite(otherCooperater.getWebsite());
			oldOtherCooperater.setIsStop(otherCooperater.getIsStop());
			oldOtherCooperater.setAddProvince(otherCooperater.getAddProvince());
			oldOtherCooperater.setAddCity(otherCooperater.getAddCity());
			oldOtherCooperater.setAddCountry(otherCooperater.getAddCountry());
			oldOtherCooperater.setDetailedAddress(otherCooperater.getDetailedAddress());
			oldOtherCooperater.setFoundDate(otherCooperater.getFoundDate());
			oldOtherCooperater.setLegalPerson(otherCooperater.getLegalPerson());
			oldOtherCooperater.setTaxNo(otherCooperater.getTaxNo());
			oldOtherCooperater.setBankAccount(otherCooperater.getBankAccount());
			oldOtherCooperater.setIndustry(otherCooperater.getIndustry());
			oldOtherCooperater.setRemark(otherCooperater.getRemark());

			otherCooperater = otherCooperaterRepository.updateEntity(oldOtherCooperater);

		} else { // id不存在，新增

			// 设置基本信息
			otherCooperater.setCreateBy(CED.getLoginUser().getEmpCd());
			otherCooperater.setCreateOrgCd(CED.getLoginUser().getOrgCd());
			otherCooperater.setCreateTime(new Date());

			otherCooperater = otherCooperaterRepository.saveEntity(otherCooperater);
		}
		return otherCooperater;
	}
}
