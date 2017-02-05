package com.zdsoft.finance.base.service;

import java.util.List;

import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.framework.core.common.domain.BaseEntity;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

public interface BaseService<T extends BaseEntity> {

	/**
	 * 公用分页查询
	 * 
	 * @param pageable
	 *            分页信息
	 * @param li
	 *            查询条件
	 * @return 分页信息
	 */
	Page<T> findByHqlConditions(Pageable pageable, List<QueryObj> li);
	
	/**
	 * 保存域对象
	 * 
	 * @param t
	 *            域对象
	 * @return
	 * @throws Exception
	 */
	public T saveEntity(T t) throws BusinessException;

	/**
	 * 更新实体
	 * 
	 * @param t
	 *            需更新的实体
	 * @return
	 * @throws BusinessException
	 * @throws Exception
	 */
	public T updateEntity(T t) throws BusinessException;

	/**
	 * 保存或修改实体
	 * 
	 * @param t
	 *            实体
	 * @return
	 * @throws BusinessException
	 * @throws Exception
	 */
	public T saveOrUpdateEntity(T t) throws BusinessException;

	/**
	 * 逻辑删除
	 * 
	 * @param t
	 *            需要删除的域对象
	 * @return
	 */
	public T logicDelete(T t);

	/**
	 * 逻辑删除
	 * 
	 * @param id
	 *            需要删除的域对象的id
	 * @return
	 * @throws BusinessException
	 */
	public T logicDelete(String id) throws BusinessException;

	/**
	 * 根据id查找对象
	 * 
	 * @param id
	 *            对象的id
	 * @return
	 * @throws BusinessException
	 */
	public T findOne(String id) throws BusinessException;

}
