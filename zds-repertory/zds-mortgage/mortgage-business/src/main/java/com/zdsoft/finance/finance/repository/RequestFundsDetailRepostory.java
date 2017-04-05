package com.zdsoft.finance.finance.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.finance.entity.RequestFundsDetail;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: RequestFundsDetailRepostory.java 
 * @ClassName: RequestFundsDetailRepostory 
 * @Description: 收费请款-费用明细 Repostory 
 * @author jincheng 
 * @date 2017年2月16日 下午3:57:38 
 * @version V1.0
 */
public interface RequestFundsDetailRepostory extends CustomRepository<RequestFundsDetail, String> {

	/**
	 * @Title: findRequestFundsDetailByReqFundsId 
	 * @Description: 通过请款id，获取本次请款的所有费用项
	 * @author jincheng 
	 * @param reqFundsId
	 * @return
	 */
	List<RequestFundsDetail> findRequestFundsDetailByReqFundsId(String reqFundsId);

}
