package com.zdsoft.finance.risk.huifa.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.risk.entity.DaasMordetail;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: DaasMordetailRepository.java 
 * @ClassName: DaasMordetailRepository 
 * @Description: 动产抵押信息仓储接口
 * @author panshm 
 * @date 2017年2月20日 下午8:31:08 
 * @version V1.0
 */
public interface DaasMordetailRepository extends CustomRepository<DaasMordetail, String>  {
	
	/**
	 * @Title: findByOrderId 
	 * @Description: 根据订单号取得动产抵押信息
	 * @author panshm 
	 * @param orderId 订单编号
	 * @return 动产抵押信息
	 */
	public List<DaasMordetail> findByOrderId(String orderId);

}
