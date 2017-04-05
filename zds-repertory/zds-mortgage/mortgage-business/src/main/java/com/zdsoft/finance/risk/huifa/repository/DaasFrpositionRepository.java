package com.zdsoft.finance.risk.huifa.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.risk.entity.DaasFrposition;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: DaasFrpositionRepository.java 
 * @ClassName: DaasFrpositionRepository 
 * @Description: 企业法定代表人其他公司任职信息仓储接口
 * @author panshm 
 * @date 2017年2月20日 下午8:31:08 
 * @version V1.0
 */
public interface DaasFrpositionRepository extends CustomRepository<DaasFrposition, String>  {
	
	/**
	 * @Title: findByOrderId 
	 * @Description: 根据订单号取得企业法定代表人其他公司任职信息
	 * @author panshm 
	 * @param orderId 订单编号
	 * @return 企业法定代表人其他公司任职信息
	 */
	public List<DaasFrposition> findByOrderId(String orderId);

}
