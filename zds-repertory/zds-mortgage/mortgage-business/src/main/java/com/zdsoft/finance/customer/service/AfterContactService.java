package com.zdsoft.finance.customer.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.customer.entity.AfterContact;

/**
 * 贷后联系方式接口
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AfterContactService.java 
 * @ClassName: AfterContactService 
 * @Description: 
 * @author zhangchao 
 * @date 2017年2月6日 上午10:22:48 
 * @version V1.0
 */
public interface AfterContactService extends BaseService<AfterContact>{
	/**
	 * 
	 * @Title: queryContactList 
	 * @Description: 通过客户id查询客户联系方式、创建时间升序排序
	 * @author xj 
	 * @param customerId 客户id
	 * @return
	 */
	public List<AfterContact> findContactListByCustomerId(String customerId);
	
	/**
	 * 
	 * @Title: queryContactList 
	 * @Description: 通过客户id查询客户联系方式、创建时间升序排序
	 * @author xj 
	 * @param customerId 客户id
	 * @return
	 */
	public List<AfterContact> queryContactList(String customerId);
}
