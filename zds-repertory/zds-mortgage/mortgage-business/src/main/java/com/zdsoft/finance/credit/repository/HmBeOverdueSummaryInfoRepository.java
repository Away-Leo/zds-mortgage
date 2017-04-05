package com.zdsoft.finance.credit.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.credit.entity.HmBeOverdueSummaryInfo;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HmBeOverdueSummaryInfoRepository.java 
 * @ClassName: HmBeOverdueSummaryInfoRepository 
 * @Description: 逾期（透支）信息汇总
 * @author gufeng 
 * @date 2017年2月23日 上午9:45:01 
 * @version V1.0
 */
public interface HmBeOverdueSummaryInfoRepository extends CustomRepository<HmBeOverdueSummaryInfo, String>{

	/**
	 * @Title: findByQueryId 
	 * @Description: 查询
	 * @author gufeng 
	 * @param queryId 主表查询id
	 * @return 数据集
	 */
	public List<HmBeOverdueSummaryInfo> findByQueryId(String queryId);

}
