package com.zdsoft.finance.businesssetting.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.businesssetting.entity.SysLabel;
import com.zdsoft.finance.common.exception.BusinessException;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title SysLabelService.java
 * @className SysLabelService
 * @description 标签设置service
 * @author LiaoGuoWei
 * @create 2017/2/27 20:54
 * @version V1.0
 **/
public interface SysLabelService extends BaseService<SysLabel>{
	
	/**
	  * @Title: deleteLabel
	  * @Description: 逻辑删除数据
	  * @author liaoguowei 
	  * @param id 标签ID
	  * @return 
	  * @throws  
	*/
	public void deleteLabel(String id) throws BusinessException;

	/**
	  * @Title: findSysLabelById
	  * @Description: 通过ID查找
	  * @author liaoguowei
	  * @param id 标签ID
	  * @return SysLabel
	  * @throws
	*/

	public SysLabel findSysLabelById(String id) throws BusinessException;

	/**
	  * @Title: saveSysLabel
	  * @Description: 保存标签设置
	  * @author liaoguowei
	  * @param sysLabel 标签
	  * @return SysLabel
	  * @throws Exception
	*/
	public SysLabel saveSysLabel(SysLabel sysLabel) throws Exception;

	/**
	 * @Title: updateSysLabel
	 * @Description: 更新标签设置
	 * @author liaoguowei
	 * @param sysLabel 标签
	 * @return SysLabel
	 * @throws Exception
	 */
	public SysLabel updateSysLabel(SysLabel sysLabel) throws Exception;

	/**
	 * @Title: saveOrUpdateSysLabel
	 * @Description: 更新或保存标签设置
	 * @author liaoguowei
	 * @param sysLabel 标签
	 * @return SysLabel
	 * @throws Exception
	 */
	public SysLabel saveOrUpdateSysLabel(SysLabel sysLabel) throws Exception;

	/**
	 * @Title: findBylabelType
	 * @Description: 按照标签类型查找
	 * @author liaoguowei
	 * @param labelType 标签类型
	 * @return java.util.List<com.zdsoft.finance.businesssetting.entity.SysLabel>
	 * @throws Exception
	 */
    public List<SysLabel> findBylabelType(String labelType) throws Exception;

    public Map<String, Object> getLabelValueSqlByKey(Map<String, Object> hsLabel);


}
