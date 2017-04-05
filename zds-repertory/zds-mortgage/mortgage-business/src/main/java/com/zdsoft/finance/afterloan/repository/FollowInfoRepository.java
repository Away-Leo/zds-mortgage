package com.zdsoft.finance.afterloan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.afterloan.entity.FollowInfo;
import com.zdsoft.finance.common.base.CustomRepository;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: FollowInfoRepository.java
 * @ClassName: FollowInfoRepository
 * @Description: 跟催实现类
 * @author zhoushichao
 * @date 2017年2月16日 下午3:05:01
 * @version V1.0
 */
public interface FollowInfoRepository extends CustomRepository<FollowInfo, String> {

	/**
	 * 
	 * @Title: findByCaseApplyIdAndFormStatus
	 * @Description: 根据案件id以及审批单状态查询跟催信息
	 * @author liuwei
	 * @param caseApplyId
	 *            案件id
	 * @return 跟催信息
	 */
	@Query(" from FollowInfo t where t.isDeleted = false and t.caseApplyId = :caseApplyId order by t.createTime desc ")
	public List<FollowInfo> findByCaseApplyIdAndFormStatus(@Param("caseApplyId") String caseApplyId);

	/**
	 * 
	 * @Title: findByCaseApplyIdAndCreateOrgCdOrderByCreateTimeDesc
	 * @Description: 通过案件id和部门Code查找跟催信息，并按照创建时间倒序
	 * @author liuwei
	 * @param caseApplyId
	 *            案件id
	 * @param careateOrgCd
	 *            部门Code
	 * @return 跟催集合
	 */
	public List<FollowInfo> findByCaseApplyIdAndCreateOrgCdOrderByCreateTimeDesc(String caseApplyId,
			String careateOrgCd);

	/**
	 * 
	 * @Title: findByCaseApplyIdAndCreateOrgCdOrderByCreateTimeDesc
	 * @Description: 通过案件id和公司Code查找跟催信息，并按照创建时间倒序
	 * @author liuwei
	 * @param caseApplyId
	 *            案件id
	 * @param companyCode
	 *            公司code
	 * @return 跟催集合
	 */
	public List<FollowInfo> findByCaseApplyIdAndCompanyCodeOrderByCreateTimeDesc(String caseApplyId,
			String companyCode);
}
