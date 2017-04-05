package com.zdsoft.finance.contract.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.contract.entity.ConAgencyContractTpl;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ConAgencyContractTplService.java 
 * @ClassName: ConAgencyContractTplService 
 * @Description: 机构合同报备接口
 * @author zhongyong 
 * @date 2017年2月27日 上午11:20:21 
 * @version V1.0
 */
public interface ConAgencyContractTplService extends BaseService<ConAgencyContractTpl>{
	
	/**
	 * @Title: saveOrUpdateAgencyContract 
	 * @Description: 保存机构合同报备申请
	 * @author zhongyong 
	 * @param contract 合同报备信息
	 * @return
	 * @throws Exception 
	 */
	public ConAgencyContractTpl saveOrUpdateAgencyContract(ConAgencyContractTpl contract) throws Exception;
	
	/**
	 * @Title: findPageAgencyContract 
	 * @Description: 分页查询机构合同报备
	 * @author zhongyong 
	 * @param pageable 分页信息
	 * @param queryObjs 查询参数
	 * @return
	 * @throws Exception
	 */
	public Page<Map<String, Object>> findPageAgencyContract(PageRequest pageable, List<QueryObj> queryObjs)
			throws Exception;

}
