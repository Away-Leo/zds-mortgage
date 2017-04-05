package com.zdsoft.finance.risk.huifa.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.risk.entity.DaasBasic;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: DaasBasicRepository.java 
 * @ClassName: DaasBasicRepository 
 * @Description: 企业照面信息仓储接口
 * @author panshm 
 * @date 2017年2月20日 下午8:31:08 
 * @version V1.0
 */
public interface DaasBasicRepository extends CustomRepository<DaasBasic, String>  {
	
	/**
	 * @Title: findByOrderId 
	 * @Description: 根据订单号取得企业照面信息
	 * @author panshm 
	 * @param orderId 订单编号
	 * @return 企业照面信息
	 */
	public List<DaasBasic> findByOrderId(String orderId);

	/**
	 * @Title: findByRegno 
	 * @Description: 根据注册号获取企业照面
	 * @author jincheng 
	 * @param regno
	 * @return
	 */
	public DaasBasic findByRegno(String regno);

}
