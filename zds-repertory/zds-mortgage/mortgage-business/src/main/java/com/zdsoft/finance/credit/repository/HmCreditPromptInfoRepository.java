package com.zdsoft.finance.credit.repository;


import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.credit.entity.HmCreditPromptInfo;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HmCompulsoryExecutionRecordRepository.java 
 * @ClassName: HmCompulsoryExecutionRecordRepository 
 * @Description: 信用提示
 * @author gufeng 
 * @date 2017年2月23日 上午9:45:06 
 * @version V1.0
 */
public interface HmCreditPromptInfoRepository extends CustomRepository<HmCreditPromptInfo, String> {

	/**
	 * 
	 * @Title: findByQueryId 
	 * @Description: 查询
	 * @author gufeng 
	 * @param queryId 查询主表id
	 * @return 数据集
	 */
	public List<HmCreditPromptInfo> findByQueryId(String queryId);


}
