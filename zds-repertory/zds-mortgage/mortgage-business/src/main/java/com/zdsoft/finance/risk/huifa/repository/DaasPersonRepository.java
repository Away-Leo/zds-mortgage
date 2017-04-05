package com.zdsoft.finance.risk.huifa.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.risk.entity.DaasPerson;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: DaasPersonRepository.java 
 * @ClassName: DaasPersonRepository 
 * @Description: 企业主要管理人员信息仓储接口
 * @author panshm 
 * @date 2017年2月20日 下午8:31:08 
 * @version V1.0
 */
public interface DaasPersonRepository extends CustomRepository<DaasPerson, String>  {
	
	/**
	 * @Title: findByOrderId 
	 * @Description: 根据订单号取得企业主要管理人员信息
	 * @author panshm 
	 * @param orderId 订单编号
	 * @return 企业主要管理人员信息
	 */
	public List<DaasPerson> findByOrderId(String orderId);

}
