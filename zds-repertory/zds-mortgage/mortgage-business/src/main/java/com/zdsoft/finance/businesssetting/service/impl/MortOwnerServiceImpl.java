package com.zdsoft.finance.businesssetting.service.impl;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.org.OrgDto;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.businesssetting.entity.MortOwner;
import com.zdsoft.finance.businesssetting.repository.MortOwnerRepository;
import com.zdsoft.finance.businesssetting.service.MortOwnerService;
import com.zdsoft.finance.common.enums.ENUM_USING_STATUS;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.common.utils.ObjectProperUtil;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @title MortOwnerServiceImpl.java
 * @className MortOwnerServiceImpl
 * @description 抵押权人service实现
 * @author LiaoGuoWei
 * @create 2017/3/3 14:50
 * @version V1.0
 **/
@Service("mortOwnerService")
public class MortOwnerServiceImpl extends BaseServiceImpl<MortOwner, MortOwnerRepository> implements MortOwnerService {

	@Autowired
	private MortOwnerRepository mortOwnerRepository;

	@Autowired
	CED CED;

	@Override
	public MortOwner findById(String id) throws BusinessException {
		if (ObjectHelper.isNotEmpty(id)) {
			MortOwner mortOwner = this.mortOwnerRepository.findOne(id);
			if (ObjectHelper.isNotEmpty(mortOwner)) {
				return mortOwner;
			} else {
				throw new BusinessException("10010002", "根据相应参数未找到相应的数据");
			}
		} else {
			throw new BusinessException("10010004", "未传入相关参数");
		}
	}

	@Override
	@Transactional
	public Page<MortOwner> findByCondition(String ownerName, String beOrgCode, Pageable pageable) {

		// 查询所有的抵押权人
		List<MortOwner> mortOwners = mortOwnerRepository.findAll();
		if (ObjectHelper.isNotEmpty(mortOwners)) {
			for (int i = 0; i < mortOwners.size(); i++) {
				try {
					// 如果查询出机构信息为null，则表示机构已经失效，则直接修改数据状态
					OrgDto organization = CED.loadOrganizationByCode(mortOwners.get(i).getBeOrgCode());
					if (ObjectHelper.isEmpty(organization)) {
						mortOwnerRepository.logicDelete(mortOwners.get(i));
					}
				} catch (Exception e) {
					mortOwnerRepository.logicDelete(mortOwners.get(i));
					logger.error("根据ID查找单位出错", e);
					e.printStackTrace();
				}
			}
		}
		return this.mortOwnerRepository.findByCondition(ownerName, beOrgCode, pageable);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public MortOwner saveMortOwner(MortOwner mortOwner) throws BusinessException {
		if (ObjectHelper.isNotEmpty(mortOwner)) {
			if (ObjectHelper.isEmpty(mortOwner.getId())) {
				return this.mortOwnerRepository.saveEntity(mortOwner);
			} else {
				throw new BusinessException("10010003", "传入参数有误");
			}
		} else {
			throw new BusinessException("10010004", "未传入相关参数");
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public MortOwner updateMortOwner(MortOwner mortOwner) throws BusinessException {
		if (ObjectHelper.isNotEmpty(mortOwner)) {
			if (ObjectHelper.isNotEmpty(mortOwner.getId())) {
				MortOwner oldData = this.findById(mortOwner.getId());
				oldData = (MortOwner) ObjectProperUtil.compareAndValue(mortOwner, oldData, false, null);
				return this.mortOwnerRepository.updateEntity(oldData);
			} else {
				throw new BusinessException("10010003", "传入参数有误");
			}
		} else {
			throw new BusinessException("10010004", "未传入相关参数");
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public MortOwner saveOrUpdateMortOwner(MortOwner mortOwner) throws BusinessException {
		if (ObjectHelper.isNotEmpty(mortOwner)) {
			if (ObjectHelper.isNotEmpty(mortOwner.getId())) {
				return this.updateMortOwner(mortOwner);
			} else {
				return this.saveMortOwner(mortOwner);
			}
		} else {
			throw new BusinessException("10010004", "根据参数未找到相应数据");
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public MortOwner deleteMortOwner(String id) throws BusinessException {
		MortOwner mortOwner = this.findById(id);
		return this.mortOwnerRepository.logicDelete(mortOwner);
	}

	@Override
	public List<MortOwner> findByOrgCode(String orgCode, String ownerTypeCode) throws BusinessException {
		if (ObjectHelper.isNotEmpty(orgCode)) {
			List<MortOwner> sourceData = this.mortOwnerRepository.findByBeOrgCodeAndStatusAndOwnerTypeCode(orgCode,
					ENUM_USING_STATUS.USING.value, ownerTypeCode);
			if (ObjectHelper.isNotEmpty(sourceData) && sourceData.size() > 0) {
				return sourceData;
			} else {
				throw new BusinessException("10010002", "根据参数未找到相应数据");
			}
		} else {
			throw new BusinessException("10010004", "未传入相关参数");
		}
	}

	@Override
	public List<MortOwner> findBeOrgCodeAndOwnerTypeCodeAndOwnerName(MortOwner mortOwner) throws BusinessException{
		if(ObjectHelper.isNotEmpty(mortOwner)&&ObjectHelper.isNotEmpty(mortOwner.getBeOrgCode())&&ObjectHelper.isNotEmpty(mortOwner.getOwnerTypeCode())&&ObjectHelper.isNotEmpty(mortOwner.getOwnerName())){
			List<MortOwner> sourceData=this.mortOwnerRepository.findBeOrgCodeAndOwnerTypeCodeAndOwnerName(mortOwner.getBeOrgCode(),mortOwner.getOwnerTypeCode(),mortOwner.getOwnerName());
			return sourceData;
		}else{
			throw new BusinessException("10010004","未传入相关参数，按照所属机构、类别、名称查找抵押权人出错");
		}
	}
}
