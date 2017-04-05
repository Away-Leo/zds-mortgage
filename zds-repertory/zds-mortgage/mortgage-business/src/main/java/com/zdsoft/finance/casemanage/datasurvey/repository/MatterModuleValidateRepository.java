package com.zdsoft.finance.casemanage.datasurvey.repository;

import java.util.List;

import com.zdsoft.finance.casemanage.datasurvey.entity.MatterModuleValidate;
import com.zdsoft.finance.common.base.CustomRepository;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: MatterModuleValidateRepository.java 
 * @ClassName: MatterModuleValidateRepository 
 * @Description: 事项模块验证Repository 
 * @author zhoushichao 
 * @date 2017年3月3日 下午3:06:43 
 * @version V1.0 
 */ 
public interface MatterModuleValidateRepository extends CustomRepository<MatterModuleValidate, String> {

	/**
	 * 
	 * @Title: findByBusinessKeyAndMatterNameAndIsImplement 
	 * @Description: 根据主体Id、事项名、是否执行查询事项模块验证
	 * @author zhoushichao 
	 * @param businessKey 主体Id
	 * @param matterName 事项名
	 * @param isImplement 是否执行
	 * @return
	 */
	public List<MatterModuleValidate> findByBusinessKeyAndMatterNameAndExecuteTag(String businessKey,String matterName,Integer executeTag);
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
	public List<MatterModuleValidate> findByBusinessKeyAndMatterNameAndTabNameAndExecuteTag(String businessKey,String matterName,String tabName,Integer executeTag);
}
