package com.zdsoft.finance.capital.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.capital.entity.CreditEntrustPrincipal;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CreditEntrustPrincipalService.java
 * @ClassName: CreditEntrustPrincipalService
 * @Description: 信托计划本金投入service
 * @author liuwei
 * @date 2017年2月8日 上午10:36:57
 * @version V1.0
 */
public interface CreditEntrustPrincipalService extends BaseService<CreditEntrustPrincipal> {

	/**
	 * 
	 * @Title: saveOrUpdateEntrustPrincipal
	 * @Description: 保存或修改计划本金投入
	 * @author liuwei
	 * @param creditEntrsutPrincipal
	 *            计划本金投入信息
	 * @return 计划本金投入信息
	 * @throws Exception
	 *             调用平台出现Exception
	 */
	public CreditEntrustPrincipal saveOrUpdateEntrustPrincipal(CreditEntrustPrincipal creditEntrsutPrincipal)
			throws Exception;

	/**
	 * 
	 * @Title: findByTempUuid
	 * @Description: 通过临时id查询计划本金投入
	 * @author liuwei
	 * @param tempUuid
	 *            临时id
	 * @return 计划本金投入列表
	 */
	public List<CreditEntrustPrincipal> findByTempUuid(String tempUuid);

	/**
	 * 
	 * @Title: findByConditions
	 * @Description: 通过查询条件查询本金投入信息
	 * @author liuwei
	 * @param conditions
	 *            查询条件
	 * @return 本金投入列表
	 */
	public List<CreditEntrustPrincipal> findByConditions(Map<String, Object> conditions);

}
