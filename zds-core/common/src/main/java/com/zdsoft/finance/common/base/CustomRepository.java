package com.zdsoft.finance.common.base;

import com.zdsoft.framework.core.common.domain.BaseEntity;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 自定义操作库
 *
 * @author LiaoGuoWei
 * @create 2016-10-27 10:00
 **/
@NoRepositoryBean
public interface CustomRepository<T extends BaseEntity, ID extends Serializable> extends JpaRepository<T, ID> {

	/**
	 * 通过HQL查找
	 *
	 * @param hql
	 *            HQL语句
	 * @param condition
	 *            查询条件
	 * @return
	 */
	public List<T> findByHql(String hql, Map<String, Object> condition);

	/**
	 * 多条件hql分页查询
	 *
	 * @param pageable
	 *            分页参数
	 * @param hql
	 *            查询HQL
	 * @param condition
	 *            查询条件
	 * @return
	 */
	public Page<T> findByHqlPage(Pageable pageable, String hql, Map<String, Object> condition);

	/**
	 * 多条件sql分页查询
	 * @param pageable 分页参数
	 * @param sql sql语句
	 * @param condition 查询条件
	 * @return
	 */
	public Page<Map<String,Object>> findBySqlMapPage(Pageable pageable, String sql, Map<String, Object> condition) throws Exception;

	/**
	 * 多条件sql分页查询
	 * @param pageable 分页参数
	 * @param sql sql语句
	 * @param condition 查询条件
	 * @return
	 */
	public Page<T> findBySqlEntityPage(Pageable pageable, String sql, Map<String, Object> condition, Class<T> tClass) throws Exception;

	/**
	 * 分页查询自动封装
	 * @param pageable
	 * @param li
	 * @return
	 */
	public Page<T> findByHqlConditions(Pageable pageable, List<QueryObj> li);

	/**
	 * 获取当前查询条件下总条数
	 *
	 * @param hql
	 *            查询HQL
	 * @param condition
	 *            查询条件
	 * @return
	 */
	public Long countAll(String hql, Map<String, Object> condition);

	/**
	 * 获取当前查询条件下总条数
	 *
	 * @param sql
	 *            查询SQL
	 * @param condition
	 *            查询条件
	 * @return
	 */
	public Long countAllSql(String sql, Map<String, Object> condition);

	/**
	 * 保存域对象
	 *
	 * @param t
	 *            域对象
	 * @return
	 */
	public T saveEntity(T t);

	/**
	 * 更新实体
	 *
	 * @param t
	 *            需更新的实体
	 * @return
	 */
	public T updateEntity(T t);

	/**
	 * 逻辑删除
	 *
	 * @param t
	 *            需要删除的域对象
	 * @return
	 */
	public T logicDelete(T t);

	/**
	 * 列表查询SQL公共实现
	 * @param pageRequest 分页参数
	 * @param param 参数集合
	 * @param _sql 查询基础SQL
	 * @param _extendSql 查询扩展SQL
	 * @return SQL查询的列表数据page对象
	 */
	public Page<Map<String,Object>> getListObjectBySql(Pageable pageable, List<QueryObj> param, StringBuffer _sql, StringBuffer _extendSql);

	/**
	 * 根据条件查询List<Map<>>
	 * @param sql
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> findListMapByCondition(String sql, Map<String, Object> condition) throws Exception;

	/**
	 * 批量保存
	 * @param list
	 * @return
	 */
	public List<T> batchSave(List<T> list);

	/**
	 * 批量更新
	 * @param list
	 * @return
	 */
	public List<T> batchUpdate(List<T> list);


	/**
	 * 批量逻辑删除
	 * @param list
	 * @return
	 */
	public List<T> batchLogicDelete(List<T> list);


}
