package com.zdsoft.finance.capital.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.capital.entity.CreditEntrustAttom;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CreditEntrustAttomService.java
 * @ClassName: CreditEntrustAttomService
 * @Description: 信托计划转让Service
 * @author liuwei
 * @date 2017年2月8日 上午10:33:59
 * @version V1.0
 */
public interface CreditEntrustAttomService extends BaseService<CreditEntrustAttom> {

	/**
	 * 
	 * @Title: saveOrUpdateEntrustAttom
	 * @Description: 新增或修改计划转让信息
	 * @author liuwei
	 * @param creditEntrustAttom
	 *            计划转让信息
	 * @return 计划转让信息
	 * @throws Exception
	 */
	public CreditEntrustAttom saveOrUpdateEntrustAttom(CreditEntrustAttom creditEntrustAttom) throws Exception;

	/**
	 * 
	 * @Title: findByTempUuid
	 * @Description: 根据tempId查询计划转让信息
	 * @author liuwei
	 * @param tempUuid
	 *            临时id
	 * @return 计划转让列表
	 */
	public List<CreditEntrustAttom> findByTempUuid(String tempUuid);

	/**
	 * 
	 * @Title: findByConditions
	 * @Description: 根据查询条件查询计划转让信息
	 * @author liuwei
	 * @param conditions
	 *            查询条件
	 * @return 计划转让信息
	 */
	public List<CreditEntrustAttom> findByConditions(Map<String, Object> conditions);

}
