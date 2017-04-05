package com.zdsoft.finance.contract.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.contract.entity.ConFormatContractDetail;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ConFormatContractDetailService.java 
 * @ClassName: ConFormatContractDetailService 
 * @Description: 格式化合同明细清单接口
 * @author zhongyong 
 * @date 2017年3月7日 下午5:07:26 
 * @version V1.0
 */
public interface ConFormatContractDetailService extends BaseService<ConFormatContractDetail> {
	
	/**
	 * @Title: saveOrUpdateFormatContractDetail 
	 * @Description: 保存或更新格式化合同清单
	 * @author zhongyong 
	 * @param detail 格式化合同清单
	 * @return
	 * @throws Exception
	 */
	public ConFormatContractDetail saveOrUpdateFormatContractDetail(ConFormatContractDetail detail) throws Exception;

	/**
	 * @Title: findByFormatContractApplyId 
	 * @Description: 根据格式化申领id获取清单列表
	 * @author zhongyong 
	 * @param applyId 格式化合同申请id
	 * @return
	 */
	public List<ConFormatContractDetail> findByFormatContractApplyId(String applyId);

}
