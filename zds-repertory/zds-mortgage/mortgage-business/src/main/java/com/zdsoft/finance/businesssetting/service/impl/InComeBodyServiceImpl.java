package com.zdsoft.finance.businesssetting.service.impl;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.businesssetting.entity.InComeBody;
import com.zdsoft.finance.businesssetting.repository.InComeBodyRepository;
import com.zdsoft.finance.businesssetting.service.InComeBodyService;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.common.utils.ObjectProperUtil;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @title InComeBodyServiceImpl.java
 * @className InComeBodyServiceImpl
 * @description 收款主体service
 * @author LiaoGuoWei
 * @create 2017/3/3 14:48
 * @version V1.0
 **/
@Service("inComeBodyService")
public class InComeBodyServiceImpl extends BaseServiceImpl<InComeBody, CustomRepository<InComeBody, String>>	implements InComeBodyService {

	@Autowired
	private InComeBodyRepository inComeBodyRepository;

	@Autowired
	private CED CED;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteInComeBody(String id) {
		InComeBody inComeBody=this.inComeBodyRepository.findOne(id);
		inComeBodyRepository.logicDelete(inComeBody);
	}

	@Override
	public List<InComeBody> findAllInComeBodyBySimpCode() {
		return inComeBodyRepository.findAll();
	}

	@Override
	@Transactional
	public InComeBody saveOrUpdateInComeBody(InComeBody inComeBody) throws Exception {

		// 判断id是否存在
		if (ObjectHelper.isNotEmpty(inComeBody.getId())) { // id存在
			InComeBody oldInComeBody = inComeBodyRepository.findOne(inComeBody.getId());
			oldInComeBody=(InComeBody) ObjectProperUtil.compareAndValue(inComeBody, oldInComeBody, false, new String[]{"remark"});
			// 设置值
			oldInComeBody.setUpdateBy(CED.getLoginUser().getEmpCd());
			oldInComeBody.setUpdateOrgCd(CED.getLoginUser().getOrgCd());
			oldInComeBody.setUpdateTime(new Date());

			inComeBody = inComeBodyRepository.updateEntity(oldInComeBody);

		} else { // id不存在

			inComeBody.setCreateBy(CED.getLoginUser().getEmpCd());
			inComeBody.setCreateOrgCd(CED.getLoginUser().getOrgCd());
			inComeBody.setCreateTime(new Date());
			inComeBody.setUpdateBy(CED.getLoginUser().getEmpCd());
			inComeBody.setUpdateOrgCd(CED.getLoginUser().getOrgCd());
			inComeBody.setUpdateTime(new Date());

			inComeBody = inComeBodyRepository.saveEntity(inComeBody);
		}
		return inComeBody;
	}

	@Override
	public Page<InComeBody> findIncomBodyByCondition(Pageable pageable, InComeBody inComeBody) throws BusinessException{
		if(ObjectHelper.isNotEmpty(inComeBody)&&ObjectHelper.isNotEmpty(inComeBody)){
			return this.inComeBodyRepository.findIncomBodyByCondition(pageable, inComeBody);
		}else{
			throw new BusinessException("10010004","未传入相关参数，收款主体查询出错");
		}
	}
	
	@Override
	public List<InComeBody> findByOrgId(String orgId) {
		return inComeBodyRepository.findByOrgId(orgId);
	}
}