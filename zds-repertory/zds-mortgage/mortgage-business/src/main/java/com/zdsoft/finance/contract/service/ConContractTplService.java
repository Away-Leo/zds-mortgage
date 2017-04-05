package com.zdsoft.finance.contract.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.contract.entity.ConContractTpl;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ConContractTplService.java 
 * @ClassName: ConContractTplService 
 * @Description: 标准合同接口
 * @author zhongyong 
 * @date 2017年3月1日 上午9:20:15 
 * @version V1.0
 */
public interface ConContractTplService extends BaseService<ConContractTpl> {

	/**
	 * @Title: findPageContract 
	 * @Description: 分页查询标准合同
	 * @author zhongyong 
	 * @param pageable 分页信息
	 * @param queryObjs 查询参数
	 * @return
	 * @throws Exception
	 */
	public Page<Map<String, Object>> findPageContract(PageRequest pageable, List<QueryObj> queryObjs, int type)
			throws Exception;

	/**
	 * @Title: findByOrgCantractApplyId 
	 * @Description: 根据机构合同报备id获取标准合同模板
	 * @author zhongyong 
	 * @param orgCantractApplyId 机构合同报备id
	 * @return
	 */
	public List<ConContractTpl> findByOrgCantractApplyId(String orgCantractApplyId);

	/**
	 * @Title: findContractTplListForFormatContract 
	 * @Description: 格式化合同清单明细获取标准合同
	 * @author zhongyong 
	 * @param capitalId 资方id
	 * @param contractType 合同类型
	 * @return
	 */
	public List<ConContractTpl> findContractTplListForFormatContract(String capitalId, String contractType);
}
