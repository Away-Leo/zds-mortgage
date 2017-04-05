package com.zdsoft.finance.customer.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.customer.entity.BeforeWorkUnit;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: BeforeWorkUnitRepository.java 
 * @ClassName: BeforeWorkUnitRepository 
 * @Description: 贷前工作单位
 * @author xj 
 * @date 2017年3月9日 上午9:46:09 
 * @version V1.0
 */
public interface BeforeWorkUnitRepository extends CustomRepository<BeforeWorkUnit, String> {
	
	/**
	 * 
	 * @Title: findByCustomerId 
	 * @Description: 通过贷前客户id查询工作单位
	 * @author xj 
	 * @param customerId 客户id
	 * @return
	 */
	public List<BeforeWorkUnit> findByCustomerId(String customerId);
}
