package com.zdsoft.finance.credit.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.credit.entity.HmCreditCardInfo;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HmCompulsoryExecutionRecordRepository.java 
 * @ClassName: HmCompulsoryExecutionRecordRepository 
 * @Description: 贷记卡概况
 * @author gufeng 
 * @date 2017年2月23日 上午9:45:06 
 * @version V1.0
 */
public interface HmCreditCardInfoRepository extends CustomRepository<HmCreditCardInfo, String>{

	/**
	 * @Title: findByQueryId 
	 * @Description: 查询
	 * @author gufeng 
	 * @param queryId 主表查询id
	 * @return 数据集
	 */
	public List<HmCreditCardInfo> findByQueryId(String queryId);
	
	/**
	 * @Title: findByQueryIdAndAccountStatusIn 
	 * @Description: 状态数据
	 * @author gufeng 
	 * @param queryId 主表查询id
	 * @param List<String> status 状态集
	 * @return 数据集
	 */
	public List<HmCreditCardInfo> findByQueryIdAndAccountStatusIn(String queryId, List<String> accountStatus);

}
