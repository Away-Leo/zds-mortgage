package com.zdsoft.finance.casemanage.evaluated.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.casemanage.evaluated.entity.EvaluatedAppeal;
import com.zdsoft.finance.casemanage.evaluated.repository.EvaluatedAppealRepository;
import com.zdsoft.finance.casemanage.evaluated.service.EvaluatedAppealService;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.marketing.entity.HouseProperty;
import com.zdsoft.finance.marketing.service.HousePropertyService;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: EvaluatedAppealServiceImpl.java 
 * @ClassName: EvaluatedAppealServiceImpl 
 * @Description: EvaluatedAppealServiceImpl
 * @author caixiekang 
 * @date 2017年3月1日 下午1:59:05 
 * @version V1.0
 */
@Service
public class EvaluatedAppealServiceImpl extends BaseServiceImpl<EvaluatedAppeal, CustomRepository<EvaluatedAppeal,String>> 
		implements EvaluatedAppealService{

	@Autowired
	private EvaluatedAppealRepository appealRepository;
	@Autowired 
	private HousePropertyService housePropertyService;
	@Autowired
	private CED CED;
	@Override
	public Page<Map<String, Object>> queryAppealListByCondiction(PageRequest pageable, List<QueryObj> queryObjs) throws Exception {
		//增加机构权限控制
		QueryObj obj = new QueryObj();
		obj.setObj("ca");
		obj.setElement("mechanismCode");
		obj.setOperator("=");
		obj.setValue(CED.getLoginUser().getCompanyCd());
		queryObjs.add(obj);
		Page<Map<String, Object>> page = appealRepository.getListObjectBySql(pageable, queryObjs, EvaluatedAppealRepository.SQL, 
				EvaluatedAppealRepository.EXTEND_SQL);
		return page;
	}

	@Override
	public Page<Map<String, Object>> queryGroupHandleList(List<QueryObj> queryObjs, PageRequest pageRequest)
			throws Exception {
		Page<Map<String, Object>> page = appealRepository.getListObjectBySql(pageRequest, queryObjs, EvaluatedAppealRepository.GROUP_SQL, 
				EvaluatedAppealRepository.GROUP_EXTEND_SQL);
		return page;
	}


	@Override
	public List<EvaluatedAppeal> findInProcessByHousePropertyId(String housePropertyId) throws BusinessException {
		if(ObjectHelper.isEmpty(housePropertyId)){
			logger.error("参数为空");
			throw new BusinessException("参数为空");
		}
		return appealRepository.findInProcessByHousePropertyId(housePropertyId);
	}

	@Override
	public List<EvaluatedAppeal> findDraftByHousePropertyId(String housePropertyId) throws BusinessException {
		if(ObjectHelper.isEmpty(housePropertyId)){
			logger.error("参数为空");
			throw new BusinessException("参数为空");
		}
		return appealRepository.findDraftByHousePropertyId(housePropertyId);
	}

	@Override
	public Page<EvaluatedAppeal> findFinishByHousePropertyId(String housePropertyId, PageRequest pageRequest)
			throws BusinessException {
		String hql = "From EvaluatedAppeal where 1=1 And housePropertyId=:housePropertyId"
				+ " And appealStatus=:appealStatus And isDeleted=:isDeleted Order By handleDate DESC";
		Map<String, Object> condition = new LinkedHashMap<>();
		condition.put("housePropertyId", housePropertyId);
		condition.put("appealStatus", EvaluatedAppeal.FINISH);
		condition.put("isDeleted", Boolean.valueOf(false));
		return appealRepository.findByHqlPage(pageRequest, hql, condition);
	}

	@Transactional(rollbackFor = Exception.class)
	public EvaluatedAppeal saveOrUpdateEntity(EvaluatedAppeal evaluatedAppeal, HouseProperty houseProperty) throws BusinessException {
		if(ObjectHelper.isNotEmpty(houseProperty)){
			housePropertyService.saveOrUpdateEntity(houseProperty);
		}
		EvaluatedAppeal evaluatedAppeal2 = this.saveOrUpdateEntity(evaluatedAppeal);
		return evaluatedAppeal2;
	}

	@Override
	public List<EvaluatedAppeal> findByHousePropertyIdAndStatus(String housePropertyId, String Status)
			throws BusinessException {
		return appealRepository.findByHousePropertyIdAndStatus(housePropertyId,Status);
	}


	
}
