package com.zdsoft.finance.contract.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.contract.entity.ConFormatContractMaterial;
import com.zdsoft.framework.core.common.exception.BusinessException;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ConFormatContractMaterialService.java 
 * @ClassName: ConFormatContractMaterialService 
 * @Description: 格式化合同实物接口
 * @author zhongyong 
 * @date 2017年3月7日 下午5:07:41 
 * @version V1.0
 */
public interface ConFormatContractMaterialService extends BaseService<ConFormatContractMaterial> {
	
	/**
	 * @Title: createFormatContractMaterial 
	 * @Description: 生成格式化合同实物
	 * @author zhongyong 
	 * @param applyId 格式化合同申领id
	 * @throws BusinessException
	 */
	public void createFormatContractMaterial(String applyId) throws Exception;

}
