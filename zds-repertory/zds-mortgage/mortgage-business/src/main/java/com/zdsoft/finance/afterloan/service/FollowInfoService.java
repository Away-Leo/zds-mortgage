package com.zdsoft.finance.afterloan.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.afterloan.entity.FollowInfo;
import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.busiform.entity.BusiFormStatus;
import com.zdsoft.finance.common.exception.BusinessException;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: FollowInfoServiceImpl.java
 * @ClassName: FollowInfoServiceImpl
 * @Description: 跟催服务类
 * @author zhoushichao
 * @date 2017年2月16日 下午3:08:15
 * @version V1.0
 */
public interface FollowInfoService extends BaseService<FollowInfo> {

	/**
	 * 
	 * @Title: findByCaseApplyIdAndFormStatus
	 * @Description: 根据案件id以及审批单状态查询跟催集合
	 * @author liuwei
	 * @param caseApplyId
	 *            案件id
	 * @param busiFormStatus
	 *            审批单状态
	 * @return 跟催集合
	 * @throws BusinessException
	 */
	public List<FollowInfo> findByCaseApplyIdAndFormStatus(String caseApplyId, BusiFormStatus busiFormStatus)
			throws BusinessException;

	/**
	 * 
	 * @Title: findByCaseApplyIdAndCreateOrgCdOrderByCreateTimeDesc
	 * @Description: 通过案件id和创建人部门Code查找跟催信息，并按照创建时间倒序
	 * @author liuwei
	 * @param caseApplyId
	 *            案件id
	 * @param createOrgcd
	 *            部门code
	 * @return 跟催信息集合
	 */
	public List<FollowInfo> findByCaseApplyIdAndCreateOrgCdOrderByCreateTimeDesc(String caseApplyId,
			String createOrgcd);

	/**
	 * 
	 * @Title: findByCaseApplyIdAndCreateOrgCdOrderByCreateTimeDesc
	 * @Description: 通过案件id和公司Code查找跟催信息，并按照创建时间倒序
	 * @author liuwei
	 * @param caseApplyId
	 *            案件id
	 * @param companyCode
	 *            公司code
	 * @return 跟催信息集合
	 */
	public List<FollowInfo> findByCaseApplyIdAndCompanyCodeOrderByCreateTimeDesc(String caseApplyId,
			String companyCode);

	/**
	 * 
	 * @Title: findByCaseApplyIdAndCreateOrgCds
	 * @Description: 查询监控部门、贷后部门、法务部门、机构最后一次的跟催信息
	 * @author liuwei
	 * @param monitorCd
	 *            监控部门cd
	 * @param afterLoanCd
	 *            贷后部门cd
	 * @param justiceCd
	 *            法务部门cd
	 * @param mechanismCd
	 *            机构cd
	 * @param caseApplyId
	 *            案件id
	 * @return 对应部门最后一次跟催信息(monitorFollowInfo,afterLoanFollowInfo,justiceFollowInfo,mechanismFollowInfo)
	 */
	public Map<String, FollowInfo> findByCaseApplyIdAndOrgCds(String monitorCd, String afterLoanCd, String justiceCd,
			String mechanismCd, String caseApplyId);

}
