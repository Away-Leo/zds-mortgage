package com.zdsoft.finance.product.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.Company;

/**
 * 机构操作接口
 * @author longwei
 * @date 2016/12/26
 * @version 1.0
 */
public interface CompanyService extends BaseService<Company>{

	/**
	 * 物理删除
	 * @throws BusinessException
	 */
	public void delete(String id) throws BusinessException;
}
