package com.zdsoft.finance.specialapprove.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.specialapprove.entity.OrganizationRisk;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: OrganizationRiskService.java 
 * @ClassName: OrganizationRiskService 
 * @Description: 机构风险接口
 * @author jingjiyan 
 * @date 2017年3月6日 下午3:49:44 
 * @version V1.0
 */
public interface OrganizationRiskService {
	/**
	 * 机构风险，根据客户提供表结构查询数据
	 * @Title: getOrganizationRisk 
	 * @author jingjiyan 
	 * @param caseApplyId
	 * 			案件ID
	 * @return
	 */
	public List<OrganizationRisk> getOrganizationRisk(String caseApplyId);
	/**
	 * 根据案件ID查询客户偿债信息
	 * @Title: getSolvencyInfo 
	 * @author jingjy 
	 * @param caseApplyId
	 * 			案件ID
	 * @return
	 */
	public Map<String, Object> getSolvencyInfo(String caseApplyId);

}
