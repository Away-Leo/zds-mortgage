package com.zdsoft.finance.risk.huifa.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.risk.entity.DaasSharesFrost;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: DaasSharesFrostRepository.java 
 * @ClassName: DaasSharesFrostRepository 
 * @Description: 股权冻结历史信息仓储接口
 * @author panshm 
 * @date 2017年2月20日 下午8:31:08 
 * @version V1.0
 */
public interface DaasSharesFrostRepository extends CustomRepository<DaasSharesFrost, String>  {
	
	/**
	 * @Title: findByOrderId 
	 * @Description: 根据订单号取得股权冻结历史信息
	 * @author panshm 
	 * @param orderId 订单编号
	 * @return 股权冻结历史信息
	 */
	public List<DaasSharesFrost> findByOrderId(String orderId);

}
