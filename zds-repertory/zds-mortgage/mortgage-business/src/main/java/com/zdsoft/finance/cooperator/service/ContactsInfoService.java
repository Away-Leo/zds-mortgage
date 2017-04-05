package com.zdsoft.finance.cooperator.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.cooperator.entity.ContactsInfo;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: ContactsInfoService.java
 * @ClassName: ContactsInfoService
 * @Description: 联系人资料Service
 * @author liuwei
 * @date 2017年2月25日 下午4:21:14
 * @version V1.0
 */
public interface ContactsInfoService extends BaseService<ContactsInfo> {

	/**
	 * 
	 * @Title: saveOrUpdateContactsInfo
	 * @Description: 保存或修改联系人资料
	 * @author liuwei
	 * @param contactsInfo
	 *            联系人信息
	 * @return 联系人信息
	 * @throws Exception
	 */
	public ContactsInfo saveOrUpdateContactsInfo(ContactsInfo contactsInfo) throws Exception;

}
