package com.zdsoft.finance.finance.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.finance.entity.RequestCommissionDetail;
import com.zdsoft.finance.finance.repository.RequestCommissionDetailRepostory;
import com.zdsoft.finance.finance.service.RequestCommissionDetailService;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:RequestCommissionServiceImpl.java
 * @Package:com.zdsoft.finance.finance.service.impl
 * @Description:支佣管理详情Service
 * @author: xiangjx
 * @date:2017年3月2日 上午10:49:12
 * @version:v1.0
 */
@Service("requestCommissionDetailService")
public class RequestCommissionDetailServiceImpl
		extends BaseServiceImpl<RequestCommissionDetail, RequestCommissionDetailRepostory>
		implements RequestCommissionDetailService {

	@Autowired
	private CED CED;


	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public RequestCommissionDetail saveOrUpdateRequestCommissionDetail(RequestCommissionDetail entity)
			throws Exception {
		EmpDto emp=CED.getLoginUser();
		if(ObjectHelper.isEmpty(entity.getId())){
			entity.setCreateTime(new Date());
			entity.setCreateBy(emp.getEmpCd());
			entity.setCreateOrgCd(emp.getOrgCd());
			this.saveEntity(entity);
		}
		return entity;
	}


	@Override
	public List<RequestCommissionDetail> findRequestCommissionDetailByReqFundsId(String reqFundsId) {
		return this.customReposity.findRequestCommissionDetailByReqCommissionId(reqFundsId);
	}


	@Override
	public List<RequestCommissionDetail> findByReqFundsIdAndPayObjectName(String reqFundsId, String payObjectName) {
		return this.customReposity.findByReqCommissionIdAndPayObjectName(reqFundsId,payObjectName);
	}


	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void deleteRequestCommissionDetailById(String id) {
		this.customReposity.delete(id);
	}


	@Override
	public Page<Map<String, Object>> getRequestCommissionDetailGroupList(PageRequest pageRequest,
			List<QueryObj> queryObjs) {
		return null;
	}
}
