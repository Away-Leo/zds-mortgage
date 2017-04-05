package com.zdsoft.finance.product.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.Company;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CompanyService.java 
 * @ClassName: CompanyService 
 * @Description: 公司操作接口
 * @author gufeng 
 * @date 2017年3月14日 下午4:51:59 
 * @version V1.0
 */
public interface CompanyService extends BaseService<Company>{

	/**
	 * @Title: delete 
	 * @Description: 物理删除
	 * @author gufeng 
	 * @param id 公司id
	 * @throws BusinessException 删除异常
	 */
	public void delete(String id) throws BusinessException;
}
