package com.zdsoft.finance.businesssetting.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.businesssetting.entity.InComeBody;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

import java.util.List;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @title InComeBodyService.java
 * @className InComeBodyService
 * @description 收款主体
 * @author LiaoGuoWei
 * @create 2017/3/3 15:02
 * @version V1.0
 **/
public interface InComeBodyService extends BaseService<InComeBody> {

	/**
	 * @Title: deleteInComeBody
	 * @Description: 删除收款主体
	 * @author liaoguowei
	 * @param id 收款主体ID
	 * @return void
	 * @throws
	 */
	public void deleteInComeBody(String id);

	/**
	 * @Title: findAllInComeBodyBySimpCode
	 * @Description: 查询所有收款主体 作为下拉列表数据
	 * @author liaoguowei
	 * @param
	 * @return java.util.List<com.zdsoft.finance.businesssetting.entity.InComeBody>
	 * @throws
	 */
	public List<InComeBody> findAllInComeBodyBySimpCode();

	/**
	 * 
	 * @Title: saveOrUpdateInComeBody 
	 * @Description: 新增修改收款主体
	 * @author liuwei
	 * @param inComeBody 收款主体
	 * @return 收款主体信息
	 * @throws Exception 
	 */
	public InComeBody saveOrUpdateInComeBody(InComeBody inComeBody) throws Exception;

	/**
	 * @Title: findIncomBodyByCondition
	 * @Description: 按照条件查找
	 * @author liaoguowei
	 * @param pageable 分页参数
	 * @param inComeBody 查询条件
	 * @return com.zdsoft.framework.core.common.page.Page<com.zdsoft.finance.businesssetting.entity.InComeBody>
	 * @throws BusinessException
	 */
	public Page<InComeBody> findIncomBodyByCondition(Pageable pageable,InComeBody inComeBody) throws BusinessException;

	/**
	 * @Title: findByOrgId 
	 * @Description: 查询当前机构收款主体
	 * @author jincheng 
	 * @param orgId
	 * @return
	 */
	public List<InComeBody> findByOrgId(String orgId);

}
