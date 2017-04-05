package com.zdsoft.finance.credit.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.credit.entity.HmReportBasics;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HmPerGuaranteeInfoRepository.java 
 * @ClassName: HmPerGuaranteeInfoRepository 
 * @Description: 人行报告的基本情况
 * @author gufeng 
 * @date 2017年2月23日 上午9:46:32 
 * @version V1.0
 */
public interface HmReportBasicsRepository extends CustomRepository<HmReportBasics, String> {

	/**
	 * @Title: findByQueryId 
	 * @Description: 查询人行数据
	 * @author gufeng 
	 * @param id 查询id
	 * @return 人行数据
	 */
	public List<HmReportBasics> findByQueryId(String queryId);

}
