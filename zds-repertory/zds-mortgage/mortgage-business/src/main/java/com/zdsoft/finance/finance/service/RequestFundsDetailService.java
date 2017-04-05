package com.zdsoft.finance.finance.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.essential.dto.permission.DataOperPermDto;
import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.finance.entity.RequestFundsDetail;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: RequestFundsDetailService.java 
 * @ClassName: RequestFundsDetailService 
 * @Description: 收费请款-费用明细接口
 * @author jincheng 
 * @date 2017年2月16日 下午3:58:24 
 * @version V1.0
 */
public interface RequestFundsDetailService extends BaseService<RequestFundsDetail>{

	/**
	 * @Title: saveOrSubmitRequestFunds 
	 * @Description: 新增并或修改收费请款-费用明细
	 * @author jincheng 
	 * @param funds
	 * @return
	 */
	RequestFundsDetail saveOrUpdateRequestFundsDetail(RequestFundsDetail detail)throws Exception ;

	
	/**
	 * @Title: findRequestFundsDetailByReqFundsId 
	 * @Description: 通过请款id，获取本次请款的所有费用项
	 * @author jincheng 
	 * @param reqFundsId 请款id
	 * @return
	 */
	List<RequestFundsDetail> findRequestFundsDetailByReqFundsId(String reqFundsId);
	

	/**
	 * @Title: deleteRequestFundsDetailById 
	 * @Description: 通过费用项id,删除本次请款费用项
	 * @author jincheng 
	 * @param id
	 */
	void deleteRequestFundsDetailById(String id);


	/**
	 * @Title: getRequestFundsDetailGroupList 
	 * @Description: 请款-根据往来单位分组
	 * @author jincheng 
	 * @param pageRequest
	 * @param queryObjs
	 * @return
	 */
	Page<Map<String,Object>> getRequestFundsDetailGroupList(PageRequest pageRequest, List<QueryObj> queryObjs,DataOperPermDto dataOperPermDto)throws BusinessException ;
	
	
}
