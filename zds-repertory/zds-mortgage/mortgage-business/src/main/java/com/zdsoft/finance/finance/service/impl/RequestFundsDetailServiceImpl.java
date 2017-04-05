package com.zdsoft.finance.finance.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.essential.dto.permission.DataOperPermDto;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.finance.entity.RequestFundsDetail;
import com.zdsoft.finance.finance.repository.RequestFundsDetailRepostory;
import com.zdsoft.finance.finance.service.RequestFundsDetailService;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: RequestFundsServiceImpl.java 
 * @ClassName: RequestFundsServiceImpl 
 * @Description: 收费请款-费用明细接口实现
 * @author jincheng 
 * @date 2017年2月16日 下午5:06:29 
 * @version V1.0
 */
@Service("requestFundsDetailService")
public class RequestFundsDetailServiceImpl extends BaseServiceImpl<RequestFundsDetail, RequestFundsDetailRepostory>  implements RequestFundsDetailService{

	@Autowired
	private com.zdsoft.essential.client.service.CED CED;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public RequestFundsDetail saveOrUpdateRequestFundsDetail(RequestFundsDetail entity) throws Exception {
		EmpDto emp=CED.getLoginUser();
		if(ObjectHelper.isEmpty(entity.getId())){
			entity.setCreateTime(new Date());
			entity.setCreateBy(emp.getEmpCd());
			entity.setCreateOrgCd(emp.getOrgCd());
			this.saveEntity(entity);
		}
		return entity;
	}

	@Override
	public List<RequestFundsDetail> findRequestFundsDetailByReqFundsId(String reqFundsId) { 
		return this.customReposity.findRequestFundsDetailByReqFundsId(reqFundsId);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void deleteRequestFundsDetailById(String id) {
		this.customReposity.delete(id);
	}


	@Override
	public Page<Map<String,Object>> getRequestFundsDetailGroupList(PageRequest pageRequest, List<QueryObj> queryObjs,DataOperPermDto dataOperPermDto) throws BusinessException {
//				StringBuffer data_auth=this.createByDataAuth(dataOperPermDto, "rq");
			StringBuffer _sql=new StringBuffer();
			_sql.append(" SELECT rq.id,rq.billCode,rqd.payObjectId,rqd.payObjectName, rqd.reqFundsAmount,rq.summary,rqd.isPayment,rq.paymentDate FROM fin_request_funds rq ");
			_sql.append(" left  join ( SELECT reqFundsId,payObjectId,isPayment,payObjectName,SUM(reqFundsAmount) reqFundsAmount,COUNT (1) FROM fin_request_funds_detail where 1=1 GROUP BY reqFundsId,isPayment,payObjectId,payObjectName) rqd ON rqd.reqFundsId = rq.id ");
			_sql.append(" where 1=1 ");
//			_sql.append(data_auth);
			StringBuffer _extendSql=new StringBuffer();
			_extendSql.append(" order by rq.createTime desc ");
		return this.customReposity.getListObjectBySql(pageRequest,queryObjs, _sql, _extendSql);
	}
}
