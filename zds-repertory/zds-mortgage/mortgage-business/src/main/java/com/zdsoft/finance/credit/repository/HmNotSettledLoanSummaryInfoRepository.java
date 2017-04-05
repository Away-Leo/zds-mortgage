package com.zdsoft.finance.credit.repository;


import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.credit.entity.HmNotSettledLoanSummaryInfo;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HmCompulsoryExecutionRecordRepository.java 
 * @ClassName: HmCompulsoryExecutionRecordRepository 
 * @Description: 未结清贷款信息汇总
 * @author gufeng 
 * @date 2017年2月23日 上午9:45:06 
 * @version V1.0
 */
public interface HmNotSettledLoanSummaryInfoRepository extends CustomRepository<HmNotSettledLoanSummaryInfo, String> {

	/**
	 * @Title: findByQueryId 
	 * @Description: 未结清笔数
	 * @author gufeng 
	 * @param queryId 主表查询id
	 * @return 结果集
	 */
	public List<HmNotSettledLoanSummaryInfo> findByQueryId(String queryId);

}
