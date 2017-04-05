package com.zdsoft.finance.casemanage.evaluated.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.casemanage.evaluated.entity.EvaluatedAppeal;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.marketing.entity.HouseProperty;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;

public interface EvaluatedAppealService extends BaseService<EvaluatedAppeal>{
	
	/**
	 * 
	 * @Title: queryAppealListByCondiction 
	 * @Description: 查找符合条件的评估价申诉
	 * @author caixiekang 
	 * @param pageable
	 * @param queryObjs 查询参数
	 * @return
	 */
	Page<Map<String, Object>> queryAppealListByCondiction(PageRequest pageable, List<QueryObj> queryObjs) throws Exception;

	/**
	 * 
	 * @Title: queryGroupHandleList 
	 * @Description: 查找集团所需要处理的评估价申诉列表
	 * @author caixiekang 
	 * @param queryObjs 查询参数
	 * @param pageRequest
	 * @return
	 * @throws Exception
	 */
	Page<Map<String, Object>> queryGroupHandleList(List<QueryObj> queryObjs, PageRequest pageRequest)throws Exception;
	
	/**
	 * 
	 * @Title: findDraftbyHousePropertyId 
	 * @Description: 查找审批中状态的申诉单
	 * @author caixiekang 
	 * @param housePropertyId 房产id
	 * @return
	 */
	List<EvaluatedAppeal> findInProcessByHousePropertyId(String housePropertyId) throws BusinessException;
	
	/**
	 * 
	 * @Title: findDraftbyHousePropertyId 
	 * @Description: 查找草稿状态的申诉单
	 * @author caixiekang 
	 * @param housePropertyId 房产id
	 * @return
	 */
	List<EvaluatedAppeal> findDraftByHousePropertyId(String housePropertyId) throws BusinessException;
	
	/**
	 * 
	 * @Title: findFinishByHousePropertyId 
	 * @Description: 按housePropertyId
	 * @author caixiekang 
	 * @param housePropertyId 房产id
	 * @param pageRequest
	 * @return
	 * @throws BusinessException
	 */
	Page<EvaluatedAppeal> findFinishByHousePropertyId(String housePropertyId, PageRequest pageRequest) throws BusinessException;

	/**
	 * 
	 * @Title: saveOrUpdateEntity 
	 * @Description: 重写saveOrUpdateEntity方法,增加更新HouseProperty的评估价
	 * @author caixiekang 
	 * @param evaluatedAppeal 评估价申诉实体
	 * @param houseProperty 房产实体
	 * @return
	 * @throws BusinessException 
	 */
	EvaluatedAppeal saveOrUpdateEntity(EvaluatedAppeal evaluatedAppeal, HouseProperty houseProperty) throws BusinessException;
	/**
	 * 
	 * @Title: findByHousePropertyIdAndStatus 
	 * @Description: 根据房产id和申请状态查找评估价申诉记录
	 * @author caixiekang 
	 * @param housePropertyId 房产id
	 * @param Status 申请状态(EvaluatedAppeal实体类里的常量)
	 * @return
	 * @throws BusinessException
	 */
	List<EvaluatedAppeal> findByHousePropertyIdAndStatus(String housePropertyId, String Status) throws Exception;
}
