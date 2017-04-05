package com.zdsoft.finance.specialapprove.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.specialapprove.entity.SpecialApproveFee;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: SpecialApproveFeeRepository.java 
 * @ClassName: SpecialApproveFeeRepository 
 * @Description: 费用特批repository
 * @author wangrongwei
 * @date 2017年2月21日 下午4:01:06 
 * @version V1.0 
 */ 
public interface SpecialApproveFeeRepository extends CustomRepository<SpecialApproveFee, String> {
	
	/** 
	 * @Title: findByReceiveFeeId 
	 * @Description: 通过收费ID查询费用特批事项
	 * @author wangrongwei
	 * @param receiveFeeId 收费ID
	 * @return  
	 */ 
	public List<SpecialApproveFee> findByReceiveFeeId(String receiveFeeId);
}
