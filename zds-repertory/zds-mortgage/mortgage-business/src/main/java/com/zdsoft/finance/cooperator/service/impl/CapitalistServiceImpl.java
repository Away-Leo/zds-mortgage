package com.zdsoft.finance.cooperator.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.cooperator.entity.Capitalist;
import com.zdsoft.finance.cooperator.repository.CapitalistRepository;
import com.zdsoft.finance.cooperator.service.CapitalistService;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CapitalistServiceImpl.java
 * @ClassName: CapitalistServiceImpl
 * @Description: 资方管理ServiceImpl
 * @author liuwei
 * @date 2017年3月2日 下午3:35:18
 * @version V1.0
 */
@Service("capitalistService")
public class CapitalistServiceImpl extends BaseServiceImpl<Capitalist, CustomRepository<Capitalist, String>>
		implements CapitalistService {

	@Autowired
	CapitalistRepository capitalistRepository;

	@Autowired
	CED CED;

	@Override
	public List<Capitalist> findList() {
		return capitalistRepository.findList();
	}

	@Override
	public List<Capitalist> findListByStatus(String isStop) {
		return capitalistRepository.findByIsStop(isStop);
	}

	/*
	 * 标准合同查询资方
	 * 
	 * @author wangnengduo
	 * 
	 * @date 2017-1-17
	 */

	public List<Capitalist> findByCapitalistType(String id) {
		return capitalistRepository.findByCapitalistType(id);
	}

	@Override
	public List<Capitalist> findListByStatusAndOrgCd(String createOrgCd, String isStop) {
		return capitalistRepository.findListByStatusAndOrgCd(createOrgCd, isStop);
	}

	@Override
	@Transactional
	public Capitalist saveCapitalTemp(Capitalist capitalist) {
		capitalist = capitalistRepository.saveEntity(capitalist);
		capitalist = capitalistRepository.logicDelete(capitalist);
		return capitalist;
	}

	@Override
	@Transactional
	public Capitalist saveOrUpdateCapitalist(Capitalist capitalist) throws Exception {
		// 判断资方id是否存在
		if (ObjectHelper.isNotEmpty(capitalist.getId())) { // 存在则修改
			// 查询原有资方信息
			Capitalist capitalistOld = capitalistRepository.findOne(capitalist.getId());

			// 修改字段信息
			capitalistOld.setCapitalistType(capitalist.getCapitalistType());
			capitalistOld.setIsStop(capitalist.getIsStop());
			capitalistOld.setCapitalName(capitalist.getCapitalName());
			capitalistOld.setCapitalShortName(capitalist.getCapitalShortName());
			capitalistOld.setTelephone(capitalist.getTelephone());
			capitalistOld.setAddProvince(capitalist.getAddProvince());
			capitalistOld.setAddCity(capitalist.getAddCity());
			capitalistOld.setAddCountry(capitalist.getAddCountry());
			capitalistOld.setAddress(capitalist.getAddress());

			capitalistOld.setFoundDate(capitalist.getFoundDate());
			capitalistOld.setLegalPerson(capitalist.getLegalPerson());
			capitalistOld.setDutyParagraph(capitalist.getDutyParagraph());
			capitalistOld.setBankAccount(capitalist.getBankAccount());
			capitalistOld.setIndustry(capitalist.getIndustry());
			capitalistOld.setRemark(capitalist.getRemark());

			if (ObjectHelper.isNotEmpty(capitalist.getCapitalistCategory())) {
				capitalistOld.setCapitalistCategory(capitalist.getCapitalistCategory());
			}
			if (ObjectHelper.isNotEmpty(capitalist.getIsHasBankAgreement())) {
				capitalistOld.setIsHasBankAgreement(capitalist.getIsHasBankAgreement());
			}

			capitalistOld.setIsDeleted(false);
			// 设置基本信息
			capitalistOld.setUpdateBy(CED.getLoginUser().getEmpCd());
			capitalistOld.setUpdateOrgCd(CED.getLoginUser().getOrgCd());
			capitalistOld.setUpdateTime(new Date());

			// 判断出事信息是否存在
			if (ObjectHelper.isEmpty(capitalistOld.getCreateBy())) {
				capitalistOld.setCreateBy(CED.getLoginUser().getEmpCd());
				capitalistOld.setCreateOrgCd(CED.getLoginUser().getOrgCd());
				capitalistOld.setCreateTime(new Date());
			}

			capitalist = capitalistRepository.updateEntity(capitalistOld);

		} else { // 不存在则新增

			// 设置基础信息
			capitalist.setCreateBy(CED.getLoginUser().getEmpCd());
			capitalist.setCreateOrgCd(CED.getLoginUser().getOrgCd());
			capitalist.setCreateTime(new Date());

			capitalist.setIsDeleted(false);

			// 保存资方信息
			capitalist = capitalistRepository.saveEntity(capitalist);

		}
		return capitalist;
	}

	@Override
	public List<Capitalist> findCapitalistByCapitalistType(String capitalistType, String isStop) {
		return capitalistRepository.findCapitalistByCapitalistType(capitalistType, isStop);
	}

	@Override
	public Capitalist findByCooperatorNameAndCapitalistType(String cooperatorName, String capitalistType) {
		List<Capitalist> capitalists = capitalistRepository.findByCooperatorNameAndCapitalistType(cooperatorName,
				capitalistType);
		if (ObjectHelper.isEmpty(capitalists)) {
			return null;
		}
		return capitalists.get(0);
	}

}
