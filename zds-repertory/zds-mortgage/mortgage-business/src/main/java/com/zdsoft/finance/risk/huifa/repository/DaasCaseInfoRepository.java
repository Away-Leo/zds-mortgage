package com.zdsoft.finance.risk.huifa.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.risk.entity.DaasCaseInfo;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: DaasCaseInfoRepository.java 
 * @ClassName: DaasCaseInfoRepository 
 * @Description: 行政处罚历史信息仓储接口
 * @author panshm 
 * @date 2017年2月20日 下午8:31:08 
 * @version V1.0
 */
public interface DaasCaseInfoRepository extends CustomRepository<DaasCaseInfo, String>  {
	
	/**
	 * @Title: findByOrderId 
	 * @Description: 根据订单号取得行政处罚历史信息
	 * @author panshm 
	 * @param orderId 订单编号
	 * @return 行政处罚历史信息
	 */
	public List<DaasCaseInfo> findByOrderId(String orderId);

}
