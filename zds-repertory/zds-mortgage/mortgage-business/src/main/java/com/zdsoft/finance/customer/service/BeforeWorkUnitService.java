package com.zdsoft.finance.customer.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.customer.entity.BeforeWorkUnit;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: BeforeWorkUnitService.java 
 * @ClassName: BeforeWorkUnitService 
 * @Description: 工作单位
 * @author xj 
 * @date 2017年3月9日 上午10:04:42 
 * @version V1.0
 */
public interface BeforeWorkUnitService extends BaseService<BeforeWorkUnit> {

	/**
	 * 
	 * @Title: saveOrUpdateWorkUnit 
	 * @Description: 单个保存工作单位
	 * @author xj 
	 * @param beforeWorkUnit 地址
	 * @param customerId 客户id
	 * @return
	 * @throws Exception
	 */
	public BeforeWorkUnit saveOrUpdateWorkUnit(BeforeWorkUnit beforeWorkUnit,String customerId) throws Exception;
	
	/**
	 * 
	 * @Title: saveOrUpdateWorkUnit 
	 * @Description: 多个保存工作单位
	 * @author xj 
	 * @param beforeWorkUnits  工作单位
	 * @param customerId 客户id
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public List<BeforeWorkUnit> saveOrUpdateWorkUnit(List<BeforeWorkUnit> beforeWorkUnits,String customerId,String token) throws Exception;
	
	/**
	 * 
	 * @Title: deleteById 
	 * @Description: 通过id删除
	 * @author xj 
	 * @param id id
	 * @throws Exception
	 */
	public void deleteById(String id) throws Exception;;
	
	/**
	 * 
	 * @Title: queryByCustomerId 
	 * @Description: 通过案件id查询贷前工作单位
	 * @author xj 
	 * @param customerId 客户id
	 * @return
	 */
	public List<BeforeWorkUnit> queryByCustomerId(String customerId);

}
