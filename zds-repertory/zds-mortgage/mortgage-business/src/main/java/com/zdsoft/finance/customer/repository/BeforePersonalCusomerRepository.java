package com.zdsoft.finance.customer.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.customer.entity.BeforePersonalCustomer;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: BeforePersonalCusomerRepository.java 
 * @ClassName: BeforePersonalCusomerRepository 
 * @Description: 贷前客户
 * @author xj 
 * @date 2017年3月9日 上午9:45:52 
 * @version V1.0
 */
public interface BeforePersonalCusomerRepository extends CustomRepository<BeforePersonalCustomer,String> {
	
	/**
	 * 
	 * @Title: findByCredentialTypeAndCredentialNoOrderByUpdateTimeDesc 
	 * @Description: 通过证件类型和证件号码查询
	 * @author xj 
	 * @param credentiaType 证件类型
	 * @param credentialNo 证件号码
	 * @return
	 */
	public List<BeforePersonalCustomer> findByCredentialTypeAndCredentialNoOrderByUpdateTimeDesc(String credentiaType,String credentialNo);
	
	
	
}
