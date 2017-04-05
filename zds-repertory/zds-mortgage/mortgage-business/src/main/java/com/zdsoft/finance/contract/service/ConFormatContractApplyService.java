package com.zdsoft.finance.contract.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.contract.entity.ConFormatContractApply;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ConFormatContractApplyService.java 
 * @ClassName: ConFormatContractApplyService 
 * @Description: 格式化合同申领接口
 * @author zhongyong 
 * @date 2017年3月7日 下午5:04:47 
 * @version V1.0
 */
public interface ConFormatContractApplyService extends BaseService<ConFormatContractApply> {
	
	/**
	 * @Title: saveOrUpdateFormatContractApply 
	 * @Description: 保存或更新格式化合同申领
	 * @author zhongyong 
	 * @param entity 格式化合同申领信息
	 * @return
	 * @throws Exception
	 */
	public ConFormatContractApply saveOrUpdateFormatContractApply(ConFormatContractApply entity) throws Exception;
	
	/**
	 * @Title: findPageFormatContractApply 
	 * @Description: 分页获取格式合同申领数据
	 * @author zhongyong 
	 * @param pageable 分页查询
	 * @param queryObjs 查询参数
	 * @return
	 * @throws Exception
	 */
	public Page<Map<String, Object>> findPageFormatContractApply(PageRequest pageable, List<QueryObj> queryObjs)
			throws Exception;
	
	/**
	 * @Title: saveStationSend 
	 * @Description: 格式化合同驻点寄出保存
	 * @author zhongyong 
	 * @param apply 格式化合同申领信息
	 * @return
	 * @throws Exception
	 */
	public ConFormatContractApply saveStationSend(ConFormatContractApply apply) throws Exception;

}
