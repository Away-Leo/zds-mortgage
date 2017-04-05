package com.zdsoft.finance.finance.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.finance.entity.RequestCommissionDetail;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;

public interface RequestCommissionDetailService extends BaseService<RequestCommissionDetail> {

	/**
	 * 
	 * @Title: saveOrUpdateRequestCommissionDetail 
	 * @Description: 新增并或修改收费请款-费用明细
	 * @author xiangjx 
	 * @param detail
	 * @return
	 * @throws Exception
	 */
	RequestCommissionDetail saveOrUpdateRequestCommissionDetail(RequestCommissionDetail detail) throws Exception;

	/**
	 * 
	 * @Title: findRequestCommissionDetailByReqFundsId 
	 * @Description: 通过请款id，获取本次请款的所有费用项
	 * @author xiangjx 
	 * @param reqFundsId
	 * @return
	 */
	List<RequestCommissionDetail> findRequestCommissionDetailByReqFundsId(String reqFundsId);

	/**
	 * 
	 * @Title: findByReqFundsIdAndPayObjectName 
	 * @Description: 通过请款id、往来单位，获取本次请款的所有费用项
	 * @author xiangjx 
	 * @param reqFundsId
	 * @param payObjectName
	 * @return
	 */
	List<RequestCommissionDetail> findByReqFundsIdAndPayObjectName(String reqFundsId, String payObjectName);

	/**
	 * 
	 * @Title: deleteRequestCommissionDetailById 
	 * @Description: 通过费用项id,删除本次请款费用项
	 * @author xiangjx 
	 * @param id
	 */
	void deleteRequestCommissionDetailById(String id);

	/**
	 * 
	 * @Title: getRequestCommissionDetailGroupList 
	 * @Description: 请款-根据往来单位分组
	 * @author xiangjx 
	 * @param pageRequest
	 * @param queryObjs
	 * @return
	 */
	Page<Map<String, Object>> getRequestCommissionDetailGroupList(PageRequest pageRequest, List<QueryObj> queryObjs);

}
