package com.zdsoft.finance.customer.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.customer.entity.BeforeContact;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: BeforeContactService.java 
 * @ClassName: BeforeContactService 
 * @Description: 联系方式
 * @author xj 
 * @date 2017年3月9日 上午9:58:14 
 * @version V1.0
 */
public interface BeforeContactService extends BaseService<BeforeContact> {
	
	/**
	 * 
	 * @Title: saveOrUpdateContact 
	 * @Description: 保存或者修改联系方式
	 * @author xj 
	 * @param beforeContact 联系方式
	 * @param customerId 客户id
	 * @param token app登录token
	 * @return
	 * @throws Exception
	 */
	public List<BeforeContact> saveOrUpdateContact(List<BeforeContact> beforeContact,String customerId,String token) throws Exception;
	
	/**
	 * 
	 * @Title: queryContact 
	 * @Description: 根据客户id查询联系方式
	 * @author xj 
	 * @param customerId 客户id
	 * @return
	 */
	public List<BeforeContact> queryContact(String customerId);
	
	/**
	 * 
	 * @Title: loadContact 
	 * @Description: 通过案件id和地址类型查询联系方式
	 * @author xj 
	 * @param customerId 客户id
	 * @param addressType 地址类型
	 * @return
	 */
	public List<BeforeContact> loadContact(String customerId,String addressType);
}
