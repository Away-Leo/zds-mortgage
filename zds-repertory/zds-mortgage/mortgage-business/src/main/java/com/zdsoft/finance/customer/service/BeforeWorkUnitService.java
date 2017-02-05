package com.zdsoft.finance.customer.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.customer.entity.BeforeWorkUnit;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:BeforeWorkUnitService.java
 * @Package:com.zdsoft.finance.customer.service
 * @Description:工作单位
 * @author: xj
 * @date:2017年1月12日 上午10:10:08
 * @version:v1.0
 */
public interface BeforeWorkUnitService extends BaseService<BeforeWorkUnit> {

	/**
	 * 
	 *  单个保存工作单位
	 *
	 * @author xj
	 * @param beforeWorkUnit
	 * @return
	 * @throws Exception
	 */
	public BeforeWorkUnit saveOrUpdateWorkUnit(BeforeWorkUnit beforeWorkUnit,String customerId) throws Exception;
	/**
	 * 
	 * 多个保存工作单位
	 *
	 * @author xj
	 * @param beforeWorkUnits
	 * @return
	 * @throws Exception
	 */
	public List<BeforeWorkUnit> saveOrUpdateWorkUnit(List<BeforeWorkUnit> beforeWorkUnits,String customerId,String token) throws Exception;
	/**
	 * 
	 * 通过id删除
	 *
	 * @author xj
	 * @param id
	 */
	public void deleteById(String id) throws Exception;;
	/**
	 * 
	 * 通过案件id查询贷前工作单位
	 *
	 * @author xj
	 * @param customerId
	 * @return
	 */
	public List<BeforeWorkUnit> queryByCustomerId(String customerId);

}
