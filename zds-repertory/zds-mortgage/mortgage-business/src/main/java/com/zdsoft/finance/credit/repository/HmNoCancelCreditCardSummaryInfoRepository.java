package com.zdsoft.finance.credit.repository;


import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.credit.entity.HmNoCancelCreditCardSummaryInfo;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HmCompulsoryExecutionRecordRepository.java 
 * @ClassName: HmCompulsoryExecutionRecordRepository 
 * @Description: 未销户贷记卡信息汇总
 * @author gufeng 
 * @date 2017年2月23日 上午9:45:06 
 * @version V1.0
 */
public interface HmNoCancelCreditCardSummaryInfoRepository extends CustomRepository<HmNoCancelCreditCardSummaryInfo, String> {

	/**
	 * @Title: findByQueryId 
	 * @Description: 信用卡发卡总额
	 * @author gufeng 
	 * @param queryId 查询结果id
	 * @return 数据集
	 */
	public List<HmNoCancelCreditCardSummaryInfo> findByQueryId(String queryId);

	
}
