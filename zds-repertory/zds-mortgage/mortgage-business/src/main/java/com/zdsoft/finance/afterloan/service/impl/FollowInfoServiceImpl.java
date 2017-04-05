package com.zdsoft.finance.afterloan.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.afterloan.entity.FollowInfo;
import com.zdsoft.finance.afterloan.repository.FollowInfoRepository;
import com.zdsoft.finance.afterloan.service.FollowInfoService;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.busiform.entity.BusiFormStatus;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: FollowInfoServiceImpl.java
 * @ClassName: FollowInfoServiceImpl
 * @Description: 跟催服务类实现
 * @author zhoushichao
 * @date 2017年2月16日 下午3:08:15
 * @version V1.0
 */
@Service("followInfoService")
public class FollowInfoServiceImpl extends BaseServiceImpl<FollowInfo, CustomRepository<FollowInfo, String>>
		implements FollowInfoService {

	@Autowired
	FollowInfoRepository followInfoRepository;

	@Override
	public List<FollowInfo> findByCaseApplyIdAndFormStatus(String caseApplyId, BusiFormStatus busiFormStatus)
			throws BusinessException {
		if (ObjectHelper.isEmpty(caseApplyId) || ObjectHelper.isEmpty(busiFormStatus)) {
			throw new BusinessException("10010003", "传入案件id或审批状态为null");
		}
		return followInfoRepository.findByCaseApplyIdAndFormStatus(caseApplyId);
	}

	@Override
	public List<FollowInfo> findByCaseApplyIdAndCreateOrgCdOrderByCreateTimeDesc(String caseApplyId,
			String createOrgCd) {
		return followInfoRepository.findByCaseApplyIdAndCreateOrgCdOrderByCreateTimeDesc(caseApplyId, createOrgCd);
	}

	@Override
	public List<FollowInfo> findByCaseApplyIdAndCompanyCodeOrderByCreateTimeDesc(String caseApplyId,
			String companyCode) {
		return followInfoRepository.findByCaseApplyIdAndCompanyCodeOrderByCreateTimeDesc(caseApplyId, companyCode);
	}

	@Override
	public Map<String, FollowInfo> findByCaseApplyIdAndOrgCds(String monitorCd, String afterLoanCd, String justiceCd,
			String mechanismCd, String caseApplyId) {

		Map<String, FollowInfo> returnMap = new HashMap<String, FollowInfo>();

		if (ObjectHelper.isNotEmpty(monitorCd)) {
			// 监控部门跟催信息
			List<FollowInfo> monitorFollowInfos = findByCaseApplyIdAndCreateOrgCdOrderByCreateTimeDesc(caseApplyId,
					monitorCd);
			if (ObjectHelper.isNotEmpty(monitorFollowInfos)) {
				returnMap.put("monitorFollowInfo", monitorFollowInfos.get(0));
			} else {
				returnMap.put("monitorFollowInfo", null);
			}
		}

		if (ObjectHelper.isNotEmpty(afterLoanCd)) {
			// 贷后部门跟催信息
			List<FollowInfo> afterLoanFollowInfos = findByCaseApplyIdAndCreateOrgCdOrderByCreateTimeDesc(caseApplyId,
					afterLoanCd);
			if (ObjectHelper.isNotEmpty(afterLoanFollowInfos)) {
				returnMap.put("afterLoanFollowInfo", afterLoanFollowInfos.get(0));
			} else {
				returnMap.put("afterLoanFollowInfo", null);
			}
		}

		if (ObjectHelper.isNotEmpty(justiceCd)) {
			// 法务部门跟催信息
			List<FollowInfo> justiceFollowInfos = findByCaseApplyIdAndCreateOrgCdOrderByCreateTimeDesc(caseApplyId,
					justiceCd);
			if (ObjectHelper.isNotEmpty(justiceFollowInfos)) {
				returnMap.put("justiceFollowInfo", justiceFollowInfos.get(0));
			} else {
				returnMap.put("justiceFollowInfo", null);
			}
		}

		if (ObjectHelper.isNotEmpty(mechanismCd)) {
			// 机构跟催信息
			List<FollowInfo> mechanismFollowInfos = findByCaseApplyIdAndCompanyCodeOrderByCreateTimeDesc(caseApplyId,
					mechanismCd);
			if (ObjectHelper.isNotEmpty(mechanismFollowInfos)) {
				returnMap.put("mechanismFollowInfo", mechanismFollowInfos.get(0));
			} else {
				returnMap.put("mechanismFollowInfo", null);
			}
		}

		return returnMap;
	}

}
