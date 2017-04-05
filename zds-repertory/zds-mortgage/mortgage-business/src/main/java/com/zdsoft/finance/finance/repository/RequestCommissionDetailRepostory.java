package com.zdsoft.finance.finance.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.finance.entity.RequestCommissionDetail;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:RequestCommissionRepostory.java
 * @Package:com.zdsoft.finance.finance.repository
 * @Description:支佣请款详情repository
 * @author: xiangjx
 * @date:2017年3月2日 上午10:48:02
 * @version:v1.0
 */
public interface RequestCommissionDetailRepostory extends CustomRepository<RequestCommissionDetail, String>{

	/**
	 * 
	 * @Title: findRequestCommissionDetailByReqCommissionId 
	 * @Description: 通过请款id，获取本次请款的所有费用项
	 * @author xiangjx 
	 * @param reqFundsId
	 * @return
	 */
	List<RequestCommissionDetail> findRequestCommissionDetailByReqCommissionId(String reqFundsId);

	/**
	 * 
	 * @Title: findByReqCommissionIdAndPayObjectName 
	 * @Description: 通过请款id、往来单位，获取本次请款的所有费用项
	 * @author xiangjx 
	 * @param reqFundsId
	 * @param payObjectName
	 * @return
	 */
	List<RequestCommissionDetail> findByReqCommissionIdAndPayObjectName(String reqFundsId, String payObjectName);
}
