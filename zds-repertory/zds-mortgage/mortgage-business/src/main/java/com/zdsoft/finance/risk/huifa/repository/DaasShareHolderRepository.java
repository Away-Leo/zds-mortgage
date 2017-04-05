package com.zdsoft.finance.risk.huifa.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.risk.entity.DaasShareHolder;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: DaasShareHolderRepository.java 
 * @ClassName: DaasShareHolderRepository 
 * @Description: 企业股东及出资信息仓储接口
 * @author panshm 
 * @date 2017年2月20日 下午8:31:08 
 * @version V1.0
 */
public interface DaasShareHolderRepository extends CustomRepository<DaasShareHolder, String>  {
	
	/**
	 * @Title: findByOrderId 
	 * @Description: 根据订单号取得企业股东及出资信息
	 * @author panshm 
	 * @param orderId 订单编号
	 * @return 企业股东及出资信息
	 */
	public List<DaasShareHolder> findByOrderId(String orderId);

}
