package com.zdsoft.finance.capital.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.org.OrgDto;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.capital.entity.InstitutionFunds;
import com.zdsoft.finance.capital.repository.InstitutionFundsRepository;
import com.zdsoft.finance.capital.service.InstitutionFundsService;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.framework.core.common.exception.BusinessException;
import com.zdsoft.framework.core.common.util.ObjectHelper;

@Service("institutionFundsService")
public class InstitutionFundsServiceImpl
		extends BaseServiceImpl<InstitutionFunds, CustomRepository<InstitutionFunds, String>>
		implements InstitutionFundsService {

	@Autowired
	InstitutionFundsRepository institutionFundsRepository;

	@Autowired
	CED CED;

	@Override
	public List<InstitutionFunds> findAll() {
		return institutionFundsRepository.findAll();
	}

	@Override
	@Transactional
	public void saveInstitutionFundsList(List<OrgDto> organizations) throws Exception {
		for (OrgDto orgDto : organizations) {
			InstitutionFunds funds = new InstitutionFunds();

			// 保存基本信息
			funds.setCreateBy(CED.getLoginUser().getEmpCd());
			funds.setCreateTime(new Date());
			funds.setCreateOrgCd(CED.getLoginUser().getOrgCd());

			// 将修改时间默认为初始时间
			funds.setUpdateTime(new Date());

			// 设置机构名称
			funds.setOrgName(orgDto.getOrgNm());
			// 设置机构编号
			funds.setOrgCd(orgDto.getOrgCd());
			// 保存
			institutionFundsRepository.saveEntity(funds);
		}
	}

	@Override
	@Transactional
	public void updateInstitutionFundsList(List<InstitutionFunds> fundsList, List<OrgDto> organizations)
			throws Exception {

		List<OrgDto> addOrgTreeDto = new ArrayList<OrgDto>();

		// 对比资金机构配置信息和机构信息
		for (OrgDto orgDto : organizations) {
			Boolean isAdd = true;

			for (InstitutionFunds funds : fundsList) {
				// 如果资金机构配置信息与机构信息的部门编号一致，则不再新增
				if (funds.getOrgCd().equals(orgDto.getOrgCd())) {
					isAdd = false;
					break;
				}

			}
			if (isAdd) {
				addOrgTreeDto.add(orgDto);
			}
		}
		saveInstitutionFundsList(addOrgTreeDto);
	}

	@Override
	@Transactional
	public InstitutionFunds updateInstitutionFunds(InstitutionFunds funds) {

		if (ObjectHelper.isEmpty(funds.getId())) {
			throw new BusinessException("10010004", "传入id为空");
		}
		// 查询出原有配置信息
		InstitutionFunds oldFunds = institutionFundsRepository.findOne(funds.getId());

		// 进行数据修改
		oldFunds.setRemark(funds.getRemark());
		oldFunds.setUpdateTime(new Date());

		// 删除原有资方信息
		oldFunds.getCapitalists().clear();

		// 修改最新的资方信息
		oldFunds.setCapitalists(funds.getCapitalists());

		return institutionFundsRepository.updateEntity(oldFunds);
	}

	@Override
	public InstitutionFunds findByOrgCd(String orgCd) {
		return institutionFundsRepository.findByOrgCd(orgCd);
	}

}
