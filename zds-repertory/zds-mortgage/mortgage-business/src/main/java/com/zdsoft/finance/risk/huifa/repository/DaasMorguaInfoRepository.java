package com.zdsoft.finance.risk.huifa.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.risk.entity.DaasMorguaInfo;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: DaasMorguaInfoRepository.java 
 * @ClassName: DaasMorguaInfoRepository 
 * @Description: 动产抵押物信息仓储接口
 * @author panshm 
 * @date 2017年2月20日 下午8:31:08 
 * @version V1.0
 */
public interface DaasMorguaInfoRepository extends CustomRepository<DaasMorguaInfo, String>  {
	
	/**
	 * @Title: findByOrderId 
	 * @Description: 根据订单号取得动产抵押物信息
	 * @author panshm 
	 * @param orderId 订单编号
	 * @return 动产抵押物信息
	 */
	public List<DaasMorguaInfo> findByOrderId(String orderId);

}
