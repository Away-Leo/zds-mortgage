package com.zdsoft.finance.casemanage.datasurvey.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.casemanage.datasurvey.entity.MatterModuleValidate;
import com.zdsoft.finance.common.exception.BusinessException;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: MatterModuleValidateService.java 
 * @ClassName: MatterModuleValidateService 
 * @Description: 事项模块验证Service
 * @author zhoushichao 
 * @date 2017年3月3日 下午3:09:35 
 * @version V1.0 
 */ 
public interface MatterModuleValidateService extends BaseService<MatterModuleValidate>{

	/**
	 * 
	 * @Title: findByBusinessKeyAndMatterName 
	 * @Description: 根据主体Id、事项名查询事项模块验证
	 * @author zhoushichao 
	 * @param businessKey 主体Id
	 * @param matterName 事项名
	 * @return
	 * @throws BusinessException
	 */
	public List<MatterModuleValidate> findByBusinessKeyAndMatterName(String businessKey,String matterName) throws BusinessException;
	/**
	 * 
	 * @Title: findByMatterNameAndTabNameAndExecuteTag 
	 * @Description: 根据主体Id、事项名、页签名、是否执行查询事项模块验证
	 * @author zhoushichao 
	 * @param businessKey 主体Id
	 * @param matterName 事项名
	 * @param tabName 页签名
	 * @param executeTag 是否执行
	 * @return
	 */
	public List<MatterModuleValidate> findByMatterNameAndTabNameAndExecuteTag(String businessKey,String matterName,String tabName,Integer executeTag) throws BusinessException;
}
