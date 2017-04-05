package com.zdsoft.finance.capital.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.capital.entity.CreditEntrust;
import com.zdsoft.finance.capital.entity.CreditEntrustFeeItem;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CreditEntrustToolService.java
 * @ClassName: CreditEntrustToolService
 * @Description: 信托计划列表统计信息工具类
 * @author liuwei
 * @date 2017年2月16日 下午6:22:51
 * @version V1.0
 */
public interface CreditEntrustToolService extends BaseService<CreditEntrust> {
	/**
	 * 
	 * @Title: listFill
	 * @Description: 填充统计列表数据，将各个子表中的数据统计进信托计划表
	 * @author liuwei
	 * @param creditEntrust
	 *            信托计划信息
	 * @return 信托计划
	 */
	public CreditEntrust listFill(CreditEntrust creditEntrust);

	/**
	 * 
	 * @Title: accumulateFeeAmount
	 * @Description: 统计费用项
	 * @author liuwei
	 * @param creditEntrustFeeItems
	 *            费用项
	 * @return 各费用项累计和
	 */
	public Map<String, Object> accumulateFeeAmount(List<CreditEntrustFeeItem> creditEntrustFeeItems);

	/**
	 * 
	 * @Title: calculateSpecialFields
	 * @Description: 计算指定日期的信托计划6个跑批字段
	 * @author liuwei
	 * @param creditEntrust
	 *            信托计划
	 * @param countDate
	 *            计算日期
	 * @return 6个字段的值
	 */
	public Map<String, BigDecimal> calculateSpecialFields(CreditEntrust creditEntrust, Date countDate);
}
