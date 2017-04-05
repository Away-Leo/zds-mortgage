package com.zdsoft.finance.contract.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.contract.entity.ConFormatContractDetail;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ConFormatContractDetailRepository.java 
 * @ClassName: ConFormatContractDetailRepository 
 * @Description: 格式化合同明细清单Repository
 * @author zhongyong 
 * @date 2017年3月7日 下午5:06:51 
 * @version V1.0
 */
public interface ConFormatContractDetailRepository extends CustomRepository<ConFormatContractDetail, String> {
	
	/**
	 * @Title: findByFormatContractApplyIdAndIsDeletedFalse 
	 * @Description: 根据格式化合同申领id获取清单列表
	 * @author zhongyong 
	 * @param applyId
	 * @return
	 */
	public List<ConFormatContractDetail> findByFormatContractApplyIdAndIsDeletedFalse(String applyId);

}
