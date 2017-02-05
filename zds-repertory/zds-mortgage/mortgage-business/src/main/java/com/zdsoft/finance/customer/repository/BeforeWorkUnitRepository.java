package com.zdsoft.finance.customer.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.customer.entity.BeforeWorkUnit;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:BeforeWorkUnitRepository.java
 * @Package:com.zdsoft.finance.customer.repository
 * @Description:贷前工作单位
 * @author: xj
 * @date:2017年1月11日 下午2:50:05
 * @version:v1.0
 */
public interface BeforeWorkUnitRepository extends CustomRepository<BeforeWorkUnit, String> {
	/**
	 * 
	 * 通过贷前客户id查询工作单位
	 *
	 * @author xj
	 * @param beforeCustomerId
	 * @return
	 */
	public List<BeforeWorkUnit> findByCustomerId(String customerId);
}
