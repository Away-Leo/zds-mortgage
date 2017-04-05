package com.zdsoft.finance.credit.repository;


import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.credit.entity.HmPerSummaryGuarantee;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HmPerGuaranteeInfoRepository.java 
 * @ClassName: HmPerGuaranteeInfoRepository 
 * @Description: 外担保信息汇总
 * @author gufeng 
 * @date 2017年2月23日 上午9:46:32 
 * @version V1.0
 */
public interface HmPerSummaryGuaranteeRepository extends CustomRepository<HmPerSummaryGuarantee, String> {

	/**
	 * @Title: findByQueryId 
	 * @Description: 对外担保
	 * @author gufeng 
	 * @param queryId 查询主表id
	 * @return 结果集
	 */
	public List<HmPerSummaryGuarantee> findByQueryId(String queryId);

	
}
