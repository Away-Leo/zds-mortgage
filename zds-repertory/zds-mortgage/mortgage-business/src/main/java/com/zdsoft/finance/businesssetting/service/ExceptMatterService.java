package com.zdsoft.finance.businesssetting.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.businesssetting.entity.ExceptMatter;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

import java.util.List;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title ExceptMatterService.java
 * @className ExceptMatterService
 * @description 特批事项管理service
 * @author LiaoGuoWei
 * @create 2017/2/16 16:39
 * @version V1.0
 **/
public interface ExceptMatterService extends BaseService<ExceptMatter>{

	/**
	 * @Title: deleteExceptMatter
	 * @Description: 删除特批事项
	 * @author liaoguowei
	 * @param id 特批事项ID
	 * @return void
	 * @throws  BusinessException
	 */
	public void deleteExceptMatter(String id) throws BusinessException;


	/**
	 * @Title: findById
	 * @Description: 通过ID查找
	 * @author liaoguowei
	 * @param id 特批事项ID
	 * @return com.zdsoft.finance.businesssetting.entity.ExceptMatter
	 * @throws BusinessException
	 */
	public ExceptMatter findById(String id) throws BusinessException;


	/**
	 * @Title: saveExceptMatter
	 * @Description: 保存
	 * @author liaoguowei
	 * @param exceptMatter 特批事项
	 * @return com.zdsoft.finance.businesssetting.entity.ExceptMatter
	 * @throws Exception
	 */
	public ExceptMatter saveExceptMatter(ExceptMatter exceptMatter) throws Exception;

	/**
	 * @Title: updateExceptMatter
	 * @Description: 更新
	 * @author liaoguowei
	 * @param exceptMatter 特批事项
	 * @return com.zdsoft.finance.businesssetting.entity.ExceptMatter
	 * @throws Exception
	 */
	public ExceptMatter updateExceptMatter(ExceptMatter exceptMatter) throws Exception;


	/**
	 * @Title: saveOrUpdateExceptMatter
	 * @Description: 保存或更新
	 * @author liaoguowei
	 * @param exceptMatter 特批事项
	 * @return com.zdsoft.finance.businesssetting.entity.ExceptMatter
	 * @throws Exception
	 */
	public ExceptMatter saveOrUpdateExceptMatter(ExceptMatter exceptMatter) throws Exception;
	
	/** 
	 * @Title: findByExceptMattercode 
	 * @Description: 通过事项编码查询风险特批事项
	 * @author wangrongwei
	 * @param exceptMattercode 特批事项编号
	 * @return  
	 */ 
	public ExceptMatter findByExceptMattercode(String exceptMattercode);
	
	/** 
	 * @Title: findByExceptMatterType 
	 * @Description: 通过特批事项类型查询风险特批事项
	 * @author wangrongwei
	 * @param exceptMatterType 特批事项类型
	 * @return  
	 */ 
	public List<ExceptMatter> findByExceptMatterType(String exceptMatterType);
	/**
	 * @Title: buildingCode
	 * @Description: 生成编号
	 * @author liaoguowei
	 * @param
	 * @return java.lang.String
	 * @throws
	 */
	public String buildingCode() throws Exception;

	/**
	 * @Title: findByAllTypeAndName
	 * @Description: 通过名称和类型查找
	 * @author liaoguowei
	 * @param exceptMatter 查询条件
	 * @return java.util.List<com.zdsoft.finance.businesssetting.entity.ExceptMatter>
	 * @throws BusinessException 业务异常
	 */
	public List<ExceptMatter> findByTypeAndName(ExceptMatter exceptMatter) throws BusinessException;

	/**
	 * @Title: findExceptMatterByCondition
	 * @Description: 按照条件查找
	 * @author liaoguowei
	 * @param page 分页参数
	 * @param exceptMatter 查询条件
	 * @return com.zdsoft.framework.core.common.page.Page<com.zdsoft.finance.businesssetting.entity.ExceptMatter>
	 * @throws BusinessException
	 */
	public Page<ExceptMatter> findExceptMatterByCondition(Pageable page,ExceptMatter exceptMatter) throws BusinessException;

	
}

