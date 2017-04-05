package com.zdsoft.finance.afterloan.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.afterloan.entity.AfterSupervise;
import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.busiform.entity.BusiFormStatus;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: AfterSuperviseService.java
 * @ClassName: AfterSuperviseService
 * @Description: 督办service
 * @author xj
 * @date 2017年3月2日 下午3:18:25
 * @version V1.0
 */
public interface AfterSuperviseService extends BaseService<AfterSupervise> {

	/**
	 * 
	 * @Title: queryAllJoinCustomer
	 * @Description: 通过案件id查询所有参与人
	 * @author xj
	 * @param caseApplyId
	 *            案件id
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> queryAllJoinCustomer(String caseApplyId) throws Exception;

	/**
	 * 
	 * @Title: saveOrUpdateAfterSupervise
	 * @Description: 保存或者修改督办
	 * @author xj
	 * @param afterSupervise
	 *            督办
	 * @param submitStatus
	 *            是否发起流程
	 * @return
	 * @throws Exception
	 */
	public AfterSupervise saveOrUpdateAfterSupervise(AfterSupervise afterSupervise, boolean submitStatus)
			throws Exception;

	/**
	 * 
	 * @Title: findByCaseApplyIdAndFormStatus
	 * @Description: 根据案件id和审批状态 创建时间降序查询督办
	 * @author xj
	 * @param caseApplyId
	 *            案件id
	 * @param busiFormStatus
	 *            审批状态
	 * @return
	 * @throws Exception
	 */
	public List<AfterSupervise> findByCaseApplyIdAndFormStatus(String caseApplyId, BusiFormStatus busiFormStatus)
			throws Exception;

	/**
	 * 
	 * @Title: findByCaseApplyIdAndCreateOrgCdOrderByCreateTimeDesc
	 * @Description: 通过案件id以及创建部门code查询督办集合并倒序排列
	 * @author liuwei
	 * @param caseApplyId
	 *            案件id
	 * @param createOrgcd
	 *            创建部门code
	 * @return 督办集合
	 */
	public List<AfterSupervise> findByCaseApplyIdAndCreateOrgCdOrderByCreateTimeDesc(String caseApplyId,
			String createOrgcd);

	/**
	 * 
	 * @Title: findByCaseApplyIdAndCompanyCodeOrderByCreateTimeDesc
	 * @Description: 通过案件id以及公司code查询督办集合并倒序排列
	 * @author liuwei
	 * @param caseApplyId
	 *            案件id
	 * @param companyCode
	 *            公司code
	 * @return 督办集合
	 */
	public List<AfterSupervise> findByCaseApplyIdAndCompanyCodeOrderByCreateTimeDesc(String caseApplyId,
			String companyCode);

	/**
	 * 
	 * @Title: findByCaseApplyIdAndCreateOrgCds
	 * @Description: 查询监控部门、贷后部门、法务部门、机构最后一次的督办信息
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
	 * @return 对应部门最后一次督办信息(monitorFollow,afterLoanFollow,justiceFollow,mechanismFollow)
	 */
	public Map<String, AfterSupervise> findByCaseApplyIdAndOrgCds(String monitorCd, String afterLoanCd,
			String justiceCd, String mechanismCd, String caseApplyId);
}
