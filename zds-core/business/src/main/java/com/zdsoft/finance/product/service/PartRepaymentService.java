package com.zdsoft.finance.product.service;

import java.util.List;

import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.PartRepayment;
import com.zdsoft.finance.product.entity.Product;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: PartRepaymentService.java 
 * @ClassName: PartRepaymentService 
 * @Description: 分段还款
 * @author gufeng 
 * @date 2017年3月13日 下午4:44:49 
 * @version V1.0
 */
public interface PartRepaymentService extends BaseService<PartRepayment>{

	/**
	 * @Title: saveOrUpdate 
	 * @Description: 保存或更新
	 * @author gufeng 
	 * @param po 需要保存的对象
	 * @return 保存后的对象
	 * @throws BusinessException 异常
	 */
	public PartRepayment saveOrUpdate(PartRepayment po)throws BusinessException;

	/**
	 * @Title: deleteByProductId 
	 * @Description: 删除
	 * @author gufeng 
	 * @param productId 产品id
	 * @throws BusinessException 异常
	 */
	public void deleteByProductId(String productId)throws BusinessException;
	
	/**
	 * @Title: findByProductId 
	 * @Description: 查询
	 * @author gufeng 
	 * @param productId 产品id
	 * @return 条件数据
	 * @throws BusinessException 异常
	 */
	public List<PartRepayment> findByProductId(String productId) throws BusinessException;
	
	/**
	 * @Title: copy 
	 * @Description: 复制
	 * @author gufeng 
	 * @param oldProduct 旧产品
	 * @param newProduct 新产品
	 * @param empDto 员工
	 * @throws BusinessException 异常
	 */
	public void copy(Product oldProduct,Product newProduct ,EmpDto empDto) throws BusinessException;
}
