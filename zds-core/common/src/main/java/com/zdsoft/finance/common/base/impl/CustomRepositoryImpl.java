package com.zdsoft.finance.common.base.impl;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.beanutils.ConvertUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.dto.permission.DataOperPermDto;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.framework.core.common.domain.BaseEntity;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageImpl;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 自定义操作仓库实现类
 *
 * @author LiaoGuoWei
 * @create 2016-10-27 10:13
 **/
@SuppressWarnings("deprecation")
@NoRepositoryBean
public class CustomRepositoryImpl<T extends BaseEntity, ID extends Serializable> extends SimpleJpaRepository<T, ID>
		implements CustomRepository<T, ID> {
	static Logger logger = LoggerFactory.getLogger(CustomRepositoryImpl.class);
	private final EntityManager entityManager;

	public CustomRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityManager = entityManager;
	}

	public CustomRepositoryImpl(Class<T> domainClass, EntityManager em) {
		this(JpaEntityInformationSupport.getEntityInformation(domainClass, em), em);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByHql(String hql, Map<String, Object> condition) {
		// 获得query对象
		Query q = entityManager.createQuery(hql);
		// 将查询条件注入HQL语句中
		for (Serializable key : condition.keySet()) {
			q.setParameter(key.toString(), condition.get(key));
		}
		// 执行查询
		List<T> sourceData = (List<T>) q.getResultList();
		return sourceData;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<T> findByHqlPage(Pageable pageable, String hql, Map<String, Object> condition) {
		if (pageable == null) {
			throw new IllegalArgumentException("pageable 不能为空!");
		}
		// 申明分页数据
		Page<T> pager = new PageImpl<T>(pageable);
		// 创建query
		Query q = entityManager.createQuery(hql);
		// 设置查询条件
		for (Serializable key : condition.keySet()) {
			q.setParameter(key.toString(), condition.get(key));
		}
		q.setFirstResult((pageable.getPage() - 1) * pageable.getRows());
		q.setMaxResults(pageable.getRows());
		pager.setRecords(q.getResultList());
		pager.setTotalRows(this.countAll(hql, condition));
		return pager;
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page<Map<String, Object>> findBySqlMapPage(Pageable pageable, String sql, Map<String, Object> condition)
			throws Exception {
		if (pageable == null) {
			throw new IllegalArgumentException("pageable 不能为空!");
		}
		// 得到Session
		Session session = entityManager.unwrap(Session.class);
		// 创建查询对象
		SQLQuery query = session.createSQLQuery(sql);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		// 申明分页数据
		Page<Map<String, Object>> pager = new PageImpl<Map<String, Object>>(pageable);
		// 创建查询
		// Query query=entityManager.createNativeQuery(sql);
		// 设置查询条件
		for (Serializable key : condition.keySet()) {
			query.setParameter(key.toString(), condition.get(key));
		}
		// 获得查询结果
		query.setFirstResult((pageable.getPage() - 1) * pageable.getRows());
		query.setMaxResults(pageable.getRows());
		List<Map<String, Object>> sourceData = query.getResultList();
		pager.setRecords(sourceData);
		pager.setTotalRows(this.countAllSql(sql, condition));
		return pager;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
	public Page<T> findBySqlEntityPage(Pageable pageable, String sql, Map<String, Object> condition, Class<T> tClass)
			throws Exception {
		if (pageable == null) {
			throw new IllegalArgumentException("pageable 不能为空!");
		}
		Session session = entityManager.unwrap(Session.class);
		SQLQuery query = session.createSQLQuery(sql);
		// 申明分页数据
		Page<T> pager = new PageImpl<T>(pageable);
		// // 创建query
		// Query query = entityManager.createNativeQuery(sql,tClass);
		// 设置查询条件
		for (Serializable key : condition.keySet()) {
			query.setParameter(key.toString(), condition.get(key));
		}
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		// 设置分页条件
		query.setFirstResult((pageable.getPage() - 1) * pageable.getRows());
		query.setMaxResults(pageable.getRows());
		// 查询结果
		List<Map<String, Object>> result = query.list();
		List returnListData = new ArrayList();
		if (tClass != null) {
			List<Object> entityList = convert(tClass, result);
			returnListData = entityList;
		}
		pager.setRecords(returnListData);
		pager.setTotalRows(this.countAllSql(sql, condition));
		return pager;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
	public List<T> findBySql(String sql, Map<String, Object> condition, Class<T> tClass) throws Exception {
		Session session = entityManager.unwrap(Session.class);
		SQLQuery query = session.createSQLQuery(sql);
		// 设置查询条件
		for (Serializable key : condition.keySet()) {
			query.setParameter(key.toString(), condition.get(key));
		}
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		// 查询结果
		List<Map<String, Object>> result = query.list();
		List returnListData = new ArrayList();
		if (tClass != null) {
			List<Object> entityList = convert(tClass, result);
			returnListData = entityList;
		}
		return returnListData;
	}

	@Override
	public Page<T> findByHqlConditions(Pageable pageable, final List<QueryObj> li) {
		Specification<T> specification = new Specification<T>() {

			@SuppressWarnings("unchecked")
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();

				predicates.add(cb.equal((root.get("isDeleted")).as(Boolean.class), false));
				Boolean isDesc = false;
				if (ObjectHelper.isNotEmpty(li)) {

					for (QueryObj obj : li) {
						if ("R".equals(obj.getOperator())) {
							switch (obj.getElement()) {
							case "Integer":
								if (obj.getObj().indexOf(".") != -1) {
									String[] objArray = obj.getObj().split("\\.");
									if (3 == objArray.length) {
										predicates.add(cb.gt((root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
												.as(Integer.class), Integer.valueOf(obj.getValue().toString())));
									} else {
										predicates
												.add(cb.gt((root.join(objArray[0]).get(objArray[1])).as(Integer.class),
														Integer.valueOf(obj.getValue().toString())));
									}

								} else {
									predicates.add(cb.gt((root.get(obj.getObj())).as(Integer.class),
											Integer.valueOf(obj.getValue().toString())));
								}

								break;
							case "BigDecimal":
								if (obj.getObj().indexOf(".") != -1) {
									String[] objArray = obj.getObj().split("\\.");
									if (3 == objArray.length) {
										predicates.add(cb.gt((root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
												.as(BigDecimal.class), new BigDecimal(obj.getValue().toString())));
									} else {
										predicates.add(
												cb.gt((root.join(objArray[0]).get(objArray[1])).as(BigDecimal.class),
														new BigDecimal(obj.getValue().toString())));
									}

								} else {
									predicates.add(cb.gt((root.get(obj.getObj())).as(BigDecimal.class),
											new BigDecimal(obj.getValue().toString())));
								}

								break;
							case "Double":
								if (obj.getObj().indexOf(".") != -1) {
									String[] objArray = obj.getObj().split("\\.");
									if (3 == objArray.length) {
										predicates.add(cb.gt((root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
												.as(Double.class), Double.valueOf(obj.getValue().toString())));
									} else {
										predicates.add(cb.gt((root.join(objArray[0]).get(objArray[1])).as(Double.class),
												Double.valueOf(obj.getValue().toString())));
									}

								} else {
									predicates.add(cb.gt((root.get(obj.getObj())).as(Double.class),
											Double.valueOf(obj.getValue().toString())));
								}
								break;
							case "Float":
								if (obj.getObj().indexOf(".") != -1) {
									String[] objArray = obj.getObj().split("\\.");
									if (3 == objArray.length) {
										predicates.add(cb.gt((root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
												.as(Float.class), Float.valueOf(obj.getValue().toString())));
									} else {
										predicates.add(cb.gt((root.join(objArray[0]).get(objArray[1])).as(Float.class),
												Float.valueOf(obj.getValue().toString())));
									}

								} else {
									predicates.add(cb.gt((root.get(obj.getObj())).as(Float.class),
											Float.valueOf(obj.getValue().toString())));
								}
								break;
							case "Long":
								if (obj.getObj().indexOf(".") != -1) {
									String[] objArray = obj.getObj().split("\\.");
									if (3 == objArray.length) {
										predicates.add(cb.gt((root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
												.as(Long.class), Long.valueOf(obj.getValue().toString())));
									} else {
										predicates.add(cb.gt((root.join(objArray[0]).get(objArray[1])).as(Long.class),
												Long.valueOf(obj.getValue().toString())));
									}

								} else {
									predicates.add(cb.gt((root.get(obj.getObj())).as(Long.class),
											Long.valueOf(obj.getValue().toString())));
								}
								break;
							case "Date":
								if (obj.getObj().indexOf(".") != -1) {
									String[] objArray = obj.getObj().split("\\.");
									if (3 == objArray.length) {
										predicates.add(
												cb.greaterThan((root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
														.as(Date.class), (Date) obj.getValue()));
									} else {
										predicates.add(
												cb.greaterThan((root.join(objArray[0]).get(objArray[1])).as(Date.class),
														(Date) obj.getValue()));
									}
								} else {
									predicates.add(cb.greaterThan(root.get(obj.getObj()).as(Date.class),
											(Date) obj.getValue()));
								}
								break;
							default:
								break;
							}
						} else if ("RE".equals(obj.getOperator())) {
							switch (obj.getElement()) {
							case "Integer":
								if (obj.getObj().indexOf(".") != -1) {
									String[] objArray = obj.getObj().split("\\.");
									if (3 == objArray.length) {
										predicates
												.add(cb.greaterThanOrEqualTo(
														(root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
																.as(Integer.class),
														Integer.valueOf(obj.getValue().toString())));
									} else {
										predicates.add(cb.greaterThanOrEqualTo(
												(root.join(objArray[0]).get(objArray[1])).as(Integer.class),
												Integer.valueOf(obj.getValue().toString())));
									}

								} else {
									predicates.add(cb.greaterThanOrEqualTo((root.get(obj.getObj())).as(Integer.class),
											Integer.valueOf(obj.getValue().toString())));
								}
								break;
							case "BigDecimal":
								if (obj.getObj().indexOf(".") != -1) {
									String[] objArray = obj.getObj().split("\\.");
									if (3 == objArray.length) {
										predicates
												.add(cb.greaterThanOrEqualTo(
														(root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
																.as(BigDecimal.class),
														new BigDecimal(obj.getValue().toString())));
									} else {
										predicates.add(cb.greaterThanOrEqualTo(
												(root.join(objArray[0]).get(objArray[1])).as(BigDecimal.class),
												new BigDecimal(obj.getValue().toString())));
									}

								} else {
									predicates
											.add(cb.greaterThanOrEqualTo((root.get(obj.getObj())).as(BigDecimal.class),
													new BigDecimal(obj.getValue().toString())));
								}
								break;
							case "Double":
								if (obj.getObj().indexOf(".") != -1) {
									String[] objArray = obj.getObj().split("\\.");
									if (3 == objArray.length) {
										predicates
												.add(cb.greaterThanOrEqualTo(
														(root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
																.as(Double.class),
														Double.valueOf(obj.getValue().toString())));
									} else {
										predicates.add(cb.greaterThanOrEqualTo(
												(root.join(objArray[0]).get(objArray[1])).as(Double.class),
												Double.valueOf(obj.getValue().toString())));
									}

								} else {
									predicates.add(cb.greaterThanOrEqualTo((root.get(obj.getObj())).as(Double.class),
											Double.valueOf(obj.getValue().toString())));
								}
								break;
							case "Float":
								if (obj.getObj().indexOf(".") != -1) {
									String[] objArray = obj.getObj().split("\\.");
									if (3 == objArray.length) {
										predicates
												.add(cb.greaterThanOrEqualTo(
														(root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
																.as(Float.class),
														Float.valueOf(obj.getValue().toString())));
									} else {
										predicates.add(cb.greaterThanOrEqualTo(
												(root.join(objArray[0]).get(objArray[1])).as(Float.class),
												Float.valueOf(obj.getValue().toString())));
									}

								} else {
									predicates.add(cb.greaterThanOrEqualTo((root.get(obj.getObj())).as(Float.class),
											Float.valueOf(obj.getValue().toString())));
								}
								break;
							case "Long":
								if (obj.getObj().indexOf(".") != -1) {
									String[] objArray = obj.getObj().split("\\.");
									if (3 == objArray.length) {
										predicates.add(cb.greaterThanOrEqualTo(
												(root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
														.as(Long.class),
												Long.valueOf(obj.getValue().toString())));
									} else {
										predicates.add(cb.greaterThanOrEqualTo(
												(root.join(objArray[0]).get(objArray[1])).as(Long.class),
												Long.valueOf(obj.getValue().toString())));
									}

								} else {
									predicates.add(cb.greaterThanOrEqualTo((root.get(obj.getObj())).as(Long.class),
											Long.valueOf(obj.getValue().toString())));
								}
								break;
							case "Date":
								if (obj.getObj().indexOf(".") != -1) {
									String[] objArray = obj.getObj().split("\\.");
									if (3 == objArray.length) {
										predicates.add(cb.greaterThanOrEqualTo(
												(root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
														.as(Date.class),
												(Date) obj.getValue()));
									} else {
										predicates.add(cb.greaterThanOrEqualTo(
												(root.join(objArray[0]).get(objArray[1])).as(Date.class),
												(Date) obj.getValue()));
									}

								} else {
									predicates.add(cb.greaterThanOrEqualTo(root.get(obj.getObj()).as(Date.class),
											(Date) obj.getValue()));
								}
								break;
							default:
								break;
							}
						} else if ("L".equals(obj.getOperator())) {
							switch (obj.getElement()) {
							case "Integer":
								if (obj.getObj().indexOf(".") != -1) {
									String[] objArray = obj.getObj().split("\\.");
									if (3 == objArray.length) {
										predicates.add(cb.lt((root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
												.as(Integer.class), Integer.valueOf(obj.getValue().toString())));
									} else {
										predicates
												.add(cb.lt((root.join(objArray[0]).get(objArray[1])).as(Integer.class),
														Integer.valueOf(obj.getValue().toString())));
									}

								} else {
									predicates.add(cb.lt((root.get(obj.getObj())).as(Integer.class),
											Integer.valueOf(obj.getValue().toString())));
								}
								break;
							case "BigDecimal":
								if (obj.getObj().indexOf(".") != -1) {
									String[] objArray = obj.getObj().split("\\.");
									if (3 == objArray.length) {
										predicates.add(cb.lt((root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
												.as(BigDecimal.class), new BigDecimal(obj.getValue().toString())));
									} else {
										predicates.add(
												cb.lt((root.join(objArray[0]).get(objArray[1])).as(BigDecimal.class),
														new BigDecimal(obj.getValue().toString())));
									}
								} else {
									predicates.add(cb.lt((root.get(obj.getObj())).as(BigDecimal.class),
											new BigDecimal(obj.getValue().toString())));
								}
								break;
							case "Double":
								if (obj.getObj().indexOf(".") != -1) {
									String[] objArray = obj.getObj().split("\\.");
									if (3 == objArray.length) {
										predicates.add(cb.lt((root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
												.as(Double.class), Double.valueOf(obj.getValue().toString())));
									} else {
										predicates.add(cb.lt((root.join(objArray[0]).get(objArray[1])).as(Double.class),
												Double.valueOf(obj.getValue().toString())));
									}

								} else {
									predicates.add(cb.lt((root.get(obj.getObj())).as(Double.class),
											Double.valueOf(obj.getValue().toString())));
								}
								break;
							case "Float":
								if (obj.getObj().indexOf(".") != -1) {
									String[] objArray = obj.getObj().split("\\.");
									if (3 == objArray.length) {
										predicates.add(cb.lt((root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
												.as(Float.class), Float.valueOf(obj.getValue().toString())));
									} else {
										predicates.add(cb.lt((root.join(objArray[0]).get(objArray[1])).as(Float.class),
												Float.valueOf(obj.getValue().toString())));
									}
								} else {
									predicates.add(cb.lt((root.get(obj.getObj())).as(Float.class),
											Float.valueOf(obj.getValue().toString())));
								}
								break;
							case "Long":
								if (obj.getObj().indexOf(".") != -1) {
									String[] objArray = obj.getObj().split("\\.");
									if (3 == objArray.length) {
										predicates.add(cb.lt((root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
												.as(Long.class), Long.valueOf(obj.getValue().toString())));
									} else {
										predicates.add(cb.lt((root.join(objArray[0]).get(objArray[1])).as(Long.class),
												Long.valueOf(obj.getValue().toString())));
									}
								} else {
									predicates.add(cb.lt((root.get(obj.getObj())).as(Long.class),
											Long.valueOf(obj.getValue().toString())));
								}
								break;
							case "Date":
								if (obj.getObj().indexOf(".") != -1) {
									String[] objArray = obj.getObj().split("\\.");
									if (3 == objArray.length) {
										predicates.add(
												cb.lessThan((root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
														.as(Date.class), (Date) obj.getValue()));
									} else {
										predicates.add(
												cb.lessThan((root.join(objArray[0]).get(objArray[1])).as(Date.class),
														(Date) obj.getValue()));
									}
								} else {
									predicates.add(
											cb.lessThan(root.get(obj.getObj()).as(Date.class), (Date) obj.getValue()));
								}
								break;
							default:
								break;
							}
						} else if ("LE".equals(obj.getOperator())) {
							switch (obj.getElement()) {
							case "Integer":
								if (obj.getObj().indexOf(".") != -1) {
									String[] objArray = obj.getObj().split("\\.");
									if (3 == objArray.length) {
										predicates
												.add(cb.lessThanOrEqualTo(
														(root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
																.as(Integer.class),
														Integer.valueOf(obj.getValue().toString())));
									} else {
										predicates.add(cb.lessThanOrEqualTo(
												(root.join(objArray[0]).get(objArray[1])).as(Integer.class),
												Integer.valueOf(obj.getValue().toString())));
									}

								} else {
									predicates.add(cb.lessThanOrEqualTo((root.get(obj.getObj())).as(Integer.class),
											Integer.valueOf(obj.getValue().toString())));
								}
								break;
							case "BigDecimal":
								if (obj.getObj().indexOf(".") != -1) {
									String[] objArray = obj.getObj().split("\\.");
									if (3 == objArray.length) {
										predicates
												.add(cb.lessThanOrEqualTo(
														(root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
																.as(BigDecimal.class),
														new BigDecimal(obj.getValue().toString())));
									} else {
										predicates.add(cb.lessThanOrEqualTo(
												(root.join(objArray[0]).get(objArray[1])).as(BigDecimal.class),
												new BigDecimal(obj.getValue().toString())));
									}

								} else {
									predicates.add(cb.lessThanOrEqualTo((root.get(obj.getObj())).as(BigDecimal.class),
											new BigDecimal(obj.getValue().toString())));
								}
								break;
							case "Double":
								if (obj.getObj().indexOf(".") != -1) {
									String[] objArray = obj.getObj().split("\\.");
									if (3 == objArray.length) {
										predicates
												.add(cb.lessThanOrEqualTo(
														(root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
																.as(Double.class),
														Double.valueOf(obj.getValue().toString())));
									} else {
										predicates.add(cb.lessThanOrEqualTo(
												(root.join(objArray[0]).get(objArray[1])).as(Double.class),
												Double.valueOf(obj.getValue().toString())));
									}

								} else {
									predicates.add(cb.lessThanOrEqualTo((root.get(obj.getObj())).as(Double.class),
											Double.valueOf(obj.getValue().toString())));
								}
								break;
							case "Float":
								if (obj.getObj().indexOf(".") != -1) {
									String[] objArray = obj.getObj().split("\\.");
									if (3 == objArray.length) {
										predicates
												.add(cb.lessThanOrEqualTo(
														(root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
																.as(Float.class),
														Float.valueOf(obj.getValue().toString())));
									} else {
										predicates.add(cb.lessThanOrEqualTo(
												(root.join(objArray[0]).get(objArray[1])).as(Float.class),
												Float.valueOf(obj.getValue().toString())));
									}

								} else {
									predicates.add(cb.lessThanOrEqualTo((root.get(obj.getObj())).as(Float.class),
											Float.valueOf(obj.getValue().toString())));
								}
								break;
							case "Long":
								if (obj.getObj().indexOf(".") != -1) {
									String[] objArray = obj.getObj().split("\\.");
									if (3 == objArray.length) {
										predicates.add(cb.lessThanOrEqualTo(
												(root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
														.as(Long.class),
												Long.valueOf(obj.getValue().toString())));
									} else {
										predicates.add(cb.lessThanOrEqualTo(
												(root.join(objArray[0]).get(objArray[1])).as(Long.class),
												Long.valueOf(obj.getValue().toString())));
									}

								} else {
									predicates.add(cb.lessThanOrEqualTo((root.get(obj.getObj())).as(Long.class),
											Long.valueOf(obj.getValue().toString())));
								}
								break;
							case "Date":
								if (obj.getObj().indexOf(".") != -1) {
									String[] objArray = obj.getObj().split("\\.");
									if (3 == objArray.length) {
										predicates.add(cb.lessThanOrEqualTo(
												(root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
														.as(Date.class),
												(Date) obj.getValue()));
									} else {
										predicates.add(cb.lessThanOrEqualTo(
												(root.join(objArray[0]).get(objArray[1])).as(Date.class),
												(Date) obj.getValue()));
									}

								} else {
									predicates.add(cb.lessThanOrEqualTo(root.get(obj.getObj()).as(Date.class),
											(Date) obj.getValue()));
								}
								break;
							default:
								break;
							}
						} else if ("E".equals(obj.getOperator())) {
							switch (obj.getElement()) {
							case "Integer":
								if (obj.getObj().indexOf(".") != -1) {
									String[] objArray = obj.getObj().split("\\.");
									if (3 == objArray.length) {
										predicates
												.add(cb.equal(
														(root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
																.as(Integer.class),
														Integer.valueOf(obj.getValue().toString())));
									} else {
										predicates.add(
												cb.equal((root.join(objArray[0]).get(objArray[1])).as(Integer.class),
														Integer.valueOf(obj.getValue().toString())));
									}

								} else {
									predicates.add(cb.equal((root.get(obj.getObj())).as(Integer.class),
											Integer.valueOf(obj.getValue().toString())));
								}
								break;
							case "BigDecimal":
								if (obj.getObj().indexOf(".") != -1) {
									String[] objArray = obj.getObj().split("\\.");
									if (3 == objArray.length) {
										predicates
												.add(cb.equal(
														(root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
																.as(BigDecimal.class),
														new BigDecimal(obj.getValue().toString())));
									} else {
										predicates.add(
												cb.equal((root.join(objArray[0]).get(objArray[1])).as(BigDecimal.class),
														new BigDecimal(obj.getValue().toString())));
									}

								} else {
									predicates.add(cb.equal((root.get(obj.getObj())).as(BigDecimal.class),
											new BigDecimal(obj.getValue().toString())));
								}
								break;
							case "Double":
								if (obj.getObj().indexOf(".") != -1) {
									String[] objArray = obj.getObj().split("\\.");
									if (3 == objArray.length) {
										predicates
												.add(cb.equal(
														(root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
																.as(Double.class),
														Double.valueOf(obj.getValue().toString())));
									} else {
										predicates.add(
												cb.equal((root.join(objArray[0]).get(objArray[1])).as(Double.class),
														Double.valueOf(obj.getValue().toString())));
									}

								} else {
									predicates.add(cb.equal((root.get(obj.getObj())).as(Double.class),
											Double.valueOf(obj.getValue().toString())));
								}
								break;
							case "Float":
								if (obj.getObj().indexOf(".") != -1) {
									String[] objArray = obj.getObj().split("\\.");
									if (3 == objArray.length) {
										predicates
												.add(cb.equal(
														(root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
																.as(Float.class),
														Float.valueOf(obj.getValue().toString())));
									} else {
										predicates
												.add(cb.equal((root.join(objArray[0]).get(objArray[1])).as(Float.class),
														Float.valueOf(obj.getValue().toString())));
									}

								} else {
									predicates.add(cb.equal((root.get(obj.getObj())).as(Float.class),
											Float.valueOf(obj.getValue().toString())));
								}
								break;
							case "Long":
								if (obj.getObj().indexOf(".") != -1) {
									String[] objArray = obj.getObj().split("\\.");
									if (3 == objArray.length) {
										predicates
												.add(cb.equal((root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
														.as(Long.class), Long.valueOf(obj.getValue().toString())));
									} else {
										predicates
												.add(cb.equal((root.join(objArray[0]).get(objArray[1])).as(Long.class),
														Long.valueOf(obj.getValue().toString())));
									}

								} else {
									predicates.add(cb.equal((root.get(obj.getObj())).as(Long.class),
											Long.valueOf(obj.getValue().toString())));
								}
								break;
							case "String":
								if (obj.getObj().indexOf(".") != -1) {
									String[] objArray = obj.getObj().split("\\.");
									if (ObjectHelper.isNotEmpty(obj.getValue())) {
										if (3 == objArray.length) {
											if (obj.getValue().equals("isnull")) {
												predicates.add(cb.isNull(
														root.get(objArray[0]).get(objArray[1]).get(objArray[2])));
											} else if (obj.getValue().equals("isnotnull")) {
												predicates.add(cb.isNotNull(
														root.get(objArray[0]).get(objArray[1]).get(objArray[2])));
											} else {
												predicates.add(cb.equal(
														(root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
																.as(String.class),
														String.valueOf(obj.getValue().toString())));
											}
										} else {
											if (obj.getValue().equals("isnull")) {
												predicates.add(cb.isNull(root.get(objArray[0]).get(objArray[1])));
											} else if (obj.getValue().equals("isnotnull")) {
												predicates.add(cb.isNotNull(root.get(objArray[0]).get(objArray[1])));
											} else {
												predicates.add(cb.equal(
														(root.join(objArray[0]).get(objArray[1])).as(String.class),
														String.valueOf(obj.getValue().toString())));
											}
										}

									}
								} else {
									if (ObjectHelper.isNotEmpty(obj.getValue())) {
										if (obj.getValue().equals("isnull")) {
											predicates.add(cb.isNull(root.get(obj.getObj())));
										} else if (obj.getValue().equals("isnotnull")) {
											predicates.add(cb.isNotNull(root.get(obj.getObj())));
										} else {
											predicates.add(cb.equal((root.get(obj.getObj())).as(String.class),
													String.valueOf(obj.getValue().toString())));
										}
									}
								}
								break;
							case "Date":
								if (obj.getObj().indexOf(".") != -1) {
									String[] objArray = obj.getObj().split("\\.");
									if (3 == objArray.length) {
										predicates
												.add(cb.equal((root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
														.as(Date.class), (Date) (obj.getValue())));
									} else {
										predicates
												.add(cb.equal((root.join(objArray[0]).get(objArray[1])).as(Date.class),
														(Date) (obj.getValue())));
									}

								} else {
									predicates.add(
											cb.equal((root.get(obj.getObj())).as(Date.class), (Date) (obj.getValue())));
								}
								break;
							case "Boolean":
								if (obj.getObj().indexOf(".") != -1) {
									String[] objArray = obj.getObj().split("\\.");
									if (3 == objArray.length) {
										predicates
												.add(cb.equal((root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
														.as(Boolean.class), (Boolean) (obj.getValue())));
									} else {
										predicates.add(
												cb.equal((root.join(objArray[0]).get(objArray[1])).as(Boolean.class),
														(Boolean) (obj.getValue())));
									}

								} else {
									predicates.add(cb.equal((root.get(obj.getObj())).as(Boolean.class),
											(Boolean) (obj.getValue())));
								}
							default:
								break;
							}
						} else if ("NE".equals(obj.getOperator())) {
							switch (obj.getElement()) {
							case "Integer":
								if (obj.getObj().indexOf(".") != -1) {
									String[] objArray = obj.getObj().split("\\.");
									if (3 == objArray.length) {
										predicates
												.add(cb.notEqual(
														(root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
																.as(Integer.class),
														Integer.valueOf(obj.getValue().toString())));
									} else {
										predicates.add(
												cb.notEqual((root.join(objArray[0]).get(objArray[1])).as(Integer.class),
														Integer.valueOf(obj.getValue().toString())));
									}

								} else {
									predicates.add(cb.notEqual((root.get(obj.getObj())).as(Integer.class),
											Integer.valueOf(obj.getValue().toString())));
								}
								break;
							case "BigDecimal":
								if (obj.getObj().indexOf(".") != -1) {
									String[] objArray = obj.getObj().split("\\.");
									if (3 == objArray.length) {
										predicates
												.add(cb.notEqual(
														(root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
																.as(BigDecimal.class),
														new BigDecimal(obj.getValue().toString())));
									} else {
										predicates.add(cb.notEqual(
												(root.join(objArray[0]).get(objArray[1])).as(BigDecimal.class),
												new BigDecimal(obj.getValue().toString())));
									}

								} else {
									predicates.add(cb.notEqual((root.get(obj.getObj())).as(BigDecimal.class),
											new BigDecimal(obj.getValue().toString())));
								}
								break;
							case "Double":
								if (obj.getObj().indexOf(".") != -1) {
									String[] objArray = obj.getObj().split("\\.");
									if (3 == objArray.length) {
										predicates
												.add(cb.notEqual(
														(root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
																.as(Double.class),
														Double.valueOf(obj.getValue().toString())));
									} else {
										predicates.add(
												cb.notEqual((root.join(objArray[0]).get(objArray[1])).as(Double.class),
														Double.valueOf(obj.getValue().toString())));
									}

								} else {
									predicates.add(cb.notEqual((root.get(obj.getObj())).as(Double.class),
											Double.valueOf(obj.getValue().toString())));
								}
								break;
							case "Float":
								if (obj.getObj().indexOf(".") != -1) {
									String[] objArray = obj.getObj().split("\\.");
									if (3 == objArray.length) {
										predicates
												.add(cb.notEqual(
														(root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
																.as(Float.class),
														Float.valueOf(obj.getValue().toString())));
									} else {
										predicates.add(
												cb.notEqual((root.join(objArray[0]).get(objArray[1])).as(Float.class),
														Float.valueOf(obj.getValue().toString())));
									}

								} else {
									predicates.add(cb.notEqual((root.get(obj.getObj())).as(Float.class),
											Float.valueOf(obj.getValue().toString())));
								}
								break;
							case "Long":
								if (obj.getObj().indexOf(".") != -1) {
									String[] objArray = obj.getObj().split("\\.");
									if (3 == objArray.length) {
										predicates.add(
												cb.notEqual((root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
														.as(Long.class), Long.valueOf(obj.getValue().toString())));
									} else {
										predicates.add(
												cb.notEqual((root.join(objArray[0]).get(objArray[1])).as(Long.class),
														Long.valueOf(obj.getValue().toString())));
									}

								} else {
									predicates.add(cb.notEqual((root.get(obj.getObj())).as(Long.class),
											Long.valueOf(obj.getValue().toString())));
								}
								break;
							case "String":
								if (obj.getObj().indexOf(".") != -1) {
									String[] objArray = obj.getObj().split("\\.");
									if (3 == objArray.length) {
										predicates
												.add(cb.notEqual(
														(root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
																.as(String.class),
														String.valueOf(obj.getValue().toString())));
									} else {
										predicates.add(
												cb.notEqual((root.join(objArray[0]).get(objArray[1])).as(String.class),
														String.valueOf(obj.getValue().toString())));
									}

								} else {
									predicates.add(cb.notEqual((root.get(obj.getObj())).as(String.class),
											String.valueOf(obj.getValue().toString())));
								}
								break;
							case "Date":
								if (obj.getObj().indexOf(".") != -1) {
									String[] objArray = obj.getObj().split("\\.");
									if (3 == objArray.length) {
										predicates.add(
												cb.notEqual((root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
														.as(Date.class), (Date) (obj.getValue())));
									} else {
										predicates.add(
												cb.notEqual((root.join(objArray[0]).get(objArray[1])).as(Date.class),
														(Date) (obj.getValue())));
									}

								} else {
									predicates.add(cb.notEqual((root.get(obj.getObj())).as(Date.class),
											(Date) (obj.getValue())));
								}
								break;
							case "Boolean":
								if (obj.getObj().indexOf(".") != -1) {
									String[] objArray = obj.getObj().split("\\.");
									if (3 == objArray.length) {
										predicates.add(
												cb.notEqual((root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
														.as(Boolean.class), (Boolean) (obj.getValue())));
									} else {
										predicates.add(
												cb.notEqual((root.join(objArray[0]).get(objArray[1])).as(Boolean.class),
														(Boolean) (obj.getValue())));
									}

								} else {
									predicates.add(cb.notEqual((root.get(obj.getObj())).as(Boolean.class),
											(Boolean) (obj.getValue())));
								}
								break;
							default:
								break;
							}
						} else if ("LK".equals(obj.getOperator())) {
							switch (obj.getElement()) {
							case "String":
								if (obj.getObj().indexOf(".") != -1) {
									String[] objArray = obj.getObj().split("\\.");
									if (3 == objArray.length) {
										predicates.add(cb.like((root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
												.as(String.class), ("%" + obj.getValue().toString() + "%")));
									} else {
										Path<Object> testObj = root.join(objArray[0]).get(objArray[1]);
										predicates.add(cb.like(testObj.as(String.class),
												("%" + obj.getValue().toString() + "%")));
									}

								} else {
									predicates.add(cb.like((root.get(obj.getObj())).as(String.class),
											("%" + obj.getValue().toString() + "%")));
								}
								break;
							default:
								break;
							}
						} else if ("IN".equals(obj.getOperator())) {
							List<Object> queryList = (List<Object>) obj.getValue();
							if (obj.getObj().indexOf(".") != -1) {
								String[] objArray = obj.getObj().split("\\.");
								if (3 == objArray.length) {
									Expression<Object> exp = root.get(objArray[0]).get(objArray[1]).get(objArray[2]);
									predicates.add(exp.in(queryList));
								} else {
									Expression<Object> exp = root.get(objArray[0]).get(objArray[1]);
									predicates.add(exp.in(queryList));
								}
							} else {
								Expression<Object> exp = root.get(obj.getObj());
								predicates.add(exp.in(queryList));
							}
						} else if ("NN".equals(obj.getOperator())) {
							List<Object> queryList = (List<Object>) obj.getValue();
							if (obj.getObj().indexOf(".") != -1) {
								String[] objArray = obj.getObj().split("\\.");
								if (3 == objArray.length) {
									Expression<Object> exp = root.get(objArray[0]).get(objArray[1]).get(objArray[2]);
									predicates.add(exp.in(queryList).not());
								} else {
									Expression<Object> exp = root.get(objArray[0]).get(objArray[1]);
									predicates.add(exp.in(queryList).not());
								}
							} else {
								Expression<Object> exp = root.get(obj.getObj());
								predicates.add(exp.in(queryList).not());
							}

						} else if ("OL".equals(obj.getOperator())) {
							switch (obj.getElement()) {
							case "String":
								List<Predicate> newPredicates = new ArrayList<Predicate>();
								List<Object> conditions = (List<Object>) obj.getValue();
								for (Object objs : conditions) {
									if (obj.getObj().indexOf(".") != -1) {
										String[] objArray = obj.getObj().split("\\.");
										if (3 == objArray.length) {
											newPredicates.add(
													cb.like((root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
															.as(String.class), ("%" + objs.toString() + "%")));
										} else {
											newPredicates.add(
													cb.like((root.join(objArray[0]).get(objArray[1])).as(String.class),
															("%" + objs.toString() + "%")));
										}

									} else {
										newPredicates.add(cb.like((root.get(obj.getObj())).as(String.class),
												("%" + objs.toString() + "%")));
									}

								}
								Predicate predocate = cb.or(newPredicates.toArray(new Predicate[newPredicates.size()]));
								predicates.add(predocate);
								break;
							default:
								break;
							}
						} else if ("BT".equals(obj.getOperator())) {
							String[] times = obj.getValue().toString().split(",");
							if (ObjectHelper.isNotEmpty(times)) {
								if (ObjectHelper.isNotEmpty(times[0])) {
									if (obj.getObj().indexOf(".") != -1) {
										String[] objArray = obj.getObj().split("\\.");
										if (3 == objArray.length) {
											predicates.add(cb.greaterThanOrEqualTo(
													(root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
															.as(Long.class),
													Long.valueOf(times[0])));
										} else {
											predicates.add(cb.greaterThanOrEqualTo(
													(root.join(objArray[0]).get(objArray[1])).as(Long.class),
													Long.valueOf(times[0])));
										}

									} else {
										predicates.add(cb.greaterThanOrEqualTo((root.get(obj.getObj())).as(Long.class),
												Long.valueOf(times[0])));
									}

								}
								if (times.length > 1 && ObjectHelper.isNotEmpty(times[1])) {
									if (obj.getObj().indexOf(".") != -1) {
										String[] objArray = obj.getObj().split("\\.");
										if (3 == objArray.length) {
											predicates.add(cb.lessThanOrEqualTo(
													(root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
															.as(Long.class),
													Long.valueOf(times[1])));
										} else {
											predicates.add(cb.lessThanOrEqualTo(
													(root.join(objArray[0]).get(objArray[1])).as(Long.class),
													Long.valueOf(times[1])));
										}

									} else {
										predicates.add(cb.lessThanOrEqualTo((root.get(obj.getObj())).as(Long.class),
												Long.valueOf(times[1])));
									}

								}
							}
						} else if ("OB".equals(obj.getOperator())) {
							isDesc = true;
							if (obj.getObj().indexOf(".") != -1) {
								String[] objArray = obj.getObj().split("\\.");
								if (3 == objArray.length) {
									if (objArray[2].indexOf(",") != -1) {
										String[] objArrays = objArray[2].split(",");

										if (obj.getValue().equals("ASC")) {
											query.orderBy(
													cb.asc(root.get(objArray[0]).get(objArray[1]).get(objArrays[0])),
													cb.asc(root.get(objArray[0]).get(objArray[1]).get(objArrays[1])));
										} else {
											query.orderBy(
													cb.desc(root.get(objArray[0]).get(objArray[1]).get(objArrays[0])),
													cb.desc(root.get(objArray[0]).get(objArray[1]).get(objArrays[1])));
										}

									} else {
										if (obj.getValue().equals("DESC")) {
											query.orderBy(
													cb.desc(root.get(objArray[0]).get(objArray[1]).get(objArray[2])));
										} else {
											query.orderBy(
													cb.asc(root.get(objArray[0]).get(objArray[1]).get(objArray[2])));
										}

									}
								} else {
									if (objArray[1].indexOf(",") != -1) {
										String[] objArrays = objArray[1].split(",");

										if (obj.getValue().equals("ASC")) {
											query.orderBy(cb.asc(root.get(objArray[0]).get(objArrays[0])),
													cb.asc(root.get(objArray[0]).get(objArrays[1])));
										} else {
											query.orderBy(cb.desc(root.get(objArray[0]).get(objArrays[0])),
													cb.desc(root.get(objArray[0]).get(objArrays[1])));
										}

									} else {
										if (obj.getValue().equals("DESC")) {
											query.orderBy(cb.desc(root.get(objArray[0]).get(objArray[1])));
										} else {
											query.orderBy(cb.asc(root.get(objArray[0]).get(objArray[1])));
										}
									}
								}
							} else {
								if (obj.getObj().indexOf(",") != -1) {
									String[] objArrays = obj.getObj().split(",");
									if (obj.getValue().equals("DESC")) {
										query.orderBy(cb.desc(root.get(objArrays[0])), cb.desc(root.get(objArrays[1])));
									} else {
										query.orderBy(cb.asc(root.get(objArrays[0])), cb.desc(root.get(objArrays[1])));
									}

								} else {
									if (obj.getValue().equals("DESC")) {
										query.orderBy(cb.desc(root.get(obj.getObj())));
									} else {
										query.orderBy(cb.asc(root.get(obj.getObj())));
									}

								}
							}
						}
					}
				}
				if (!isDesc) {
					query.orderBy(cb.desc(root.get("createTime")));
				}
				query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));

				return query.getRestriction();
			}

		};
		org.springframework.data.domain.Pageable springPageable = new PageRequest(pageable.getPage() - 1,
				pageable.getRows());
		org.springframework.data.domain.Page<T> pages = super.findAll(specification, springPageable);
		Page<T> zdPage = new PageImpl<T>(pageable);
		zdPage.setRecords(pages.getContent());
		zdPage.setTotalRows(pages.getTotalElements());
		return zdPage;
	}

	@Override
	public Long countAll(String hql, Map<String, Object> condition) {
		if (hql == null) {
			return 0l;
		}
		String tmpHql = hql.toLowerCase();
		hql = "select count(*) " + hql.substring(tmpHql.indexOf("from"));
		logger.debug("count(*) hql ---->" + hql);
		// 创建query
		Query q = entityManager.createQuery(hql);
		// 设置查询条件
		for (Serializable key : condition.keySet()) {
			q.setParameter(key.toString(), condition.get(key));
		}
		Long result = (Long) q.getResultList().get(0);
		return result;
	}

	@Override
	public Long countAllSql(String sql, Map<String, Object> condition) {
		if (sql == null) {
			return 0l;
		}
		String tmpHql = sql.toLowerCase();
		sql = "select count(*) " + sql.substring(tmpHql.indexOf("from"));
		logger.debug("count(*) sql ---->" + sql);
		// 创建query
		Query q = entityManager.createNativeQuery(sql);
		// 设置查询条件
		for (Serializable key : condition.keySet()) {
			q.setParameter(key.toString(), condition.get(key));
		}
		String resultStr = q.getResultList().get(0).toString();
		return Long.valueOf(resultStr);
	}

	@Override
	@Transactional
	public T saveEntity(T t) {
		t.setCreateTime(new Date());
		t.setIsDeleted(false);
		return this.saveAndFlush(t);
	}

	@Override
	@Transactional
	public T updateEntity(T t) {
		t.setUpdateTime(new Date());
		t.setIsDeleted(false);
		return this.saveAndFlush(t);
	}

	@Override
	public T logicDelete(T t) {
		t.setDeleteTime(new Date());
		t.setIsDeleted(true);
		return this.entityManager.merge(t);
	}

	@Override
	public Page<Map<String, Object>> getListObjectBySql(Pageable pageable, List<QueryObj> param, StringBuffer _sql,
			StringBuffer _extendSql) {
		String searchSql = CustomCommon.getSearchSql(param, _sql, _extendSql);
		logger.info("=========查询sql：" + searchSql);
		String countSql = " select count(*) countNum from ( " + searchSql + " )   temp ";
		Object[] params = (Object[]) CustomCommon.generateSearchConditionSql(param).get(CustomCommon.QUERY_PARAM);
		Query query = (Query) entityManager.createNativeQuery(searchSql);
		Query queryCount = (Query) entityManager.createNativeQuery(countSql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i + 1, params[i]);
			queryCount.setParameter(i + 1, params[i]);
		}
		query.setFirstResult((pageable.getPage() - 1) * pageable.getRows());
		query.setMaxResults(pageable.getRows());
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> dataList = query.getResultList();
		Page<Map<String, Object>> page = new PageImpl<>(pageable);
		page.setRecords(dataList);
		page.setTotalRows(Long.parseLong(queryCount.getSingleResult().toString()));
		return page;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
	public List<Map<String, Object>> findListMapByCondition(String sql, Map<String, Object> condition)
			throws Exception {
		// 得到Session
		Session session = entityManager.unwrap(Session.class);
		// 创建查询对象
		SQLQuery query = session.createSQLQuery(sql);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		// 设置查询条件
		if (ObjectHelper.isNotEmpty(condition) && condition.size() > 0) {
			for (Serializable key : condition.keySet()) {
				query.setParameter(key.toString(), condition.get(key));
			}
		}
		List<Map<String, Object>> sourceData = query.getResultList();
		return sourceData;
	}

	@Override
	@Transactional
	public List<T> batchSave(List<T> list) {
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setCreateTime(new Date());
			entityManager.persist(list.get(i));
			entityManager.flush();
			entityManager.clear();
		}
		return list;
	}

	@Override
	@Transactional
	public List<T> batchUpdate(List<T> list) {
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setUpdateTime(new Date());
			entityManager.merge(list.get(i));
			entityManager.flush();
			entityManager.clear();
		}
		return list;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<T> batchLogicDelete(List<T> list) {
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setIsDeleted(true);
			list.get(i).setDeleteTime(new Date());
			entityManager.merge(list.get(i));
			entityManager.flush();
			entityManager.clear();
		}
		return list;
	}

	/**
	 * 将list<Map<>>转换为实体对象集合
	 * 
	 * @param clazz
	 * @param list
	 * @return
	 */
	private List<Object> convert(Class<?> clazz, List<Map<String, Object>> list) {
		List<Object> result;
		if (ObjectHelper.isEmpty(list)) {
			return new ArrayList<Object>();
		}
		result = new ArrayList<Object>();
		try {
			PropertyDescriptor[] props = Introspector.getBeanInfo(clazz).getPropertyDescriptors();
			for (Map<String, Object> map : list) {
				Object obj = clazz.newInstance();
				for (String key : map.keySet()) {
					String attrName = key;
					for (PropertyDescriptor prop : props) {
						attrName = removeUnderLine(attrName);
						if (!attrName.toUpperCase().equals(prop.getName().toUpperCase())) {
							continue;
						}
						Method method = prop.getWriteMethod();
						Object value = map.get(key);
						if (value != null) {
							value = ConvertUtils.convert(value, prop.getPropertyType());
						}
						method.invoke(obj, value);
					}
				}
				result.add(obj);
			}
		} catch (Exception e) {
			throw new RuntimeException("数据转换错误");
		}
		return result;
	}

	/**
	 * 去掉下划线
	 * 
	 * @param attrName
	 * @return
	 */
	private String removeUnderLine(String attrName) {
		// 去掉数据库字段的下划线
		if (attrName.contains("_")) {
			String[] names = attrName.split("_");
			String firstPart = names[0];
			String otherPart = "";
			for (int i = 1; i < names.length; i++) {
				String word = names[i].replaceFirst(names[i].substring(0, 1), names[i].substring(0, 1).toUpperCase());
				otherPart += word;
			}
			attrName = firstPart + otherPart;
		}
		return attrName;
	}

    @Override
    public Page<T> findByHqlConditions(Pageable pageable, List<QueryObj> li, DataOperPermDto dataOperPermDto) {
        Specification<T> specification = new Specification<T>() {

            @SuppressWarnings("unchecked")
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();

                predicates.add(cb.equal((root.get("isDeleted")).as(Boolean.class), false));
                Boolean isDesc = false;
                if (ObjectHelper.isNotEmpty(li)) {

                    for (QueryObj obj : li) {
                        if ("R".equals(obj.getOperator())) {
                            switch (obj.getElement()) {
                            case "Integer":
                                if (obj.getObj().indexOf(".") != -1) {
                                    String[] objArray = obj.getObj().split("\\.");
                                    if (3 == objArray.length) {
                                        predicates.add(cb.gt((root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
                                                .as(Integer.class), Integer.valueOf(obj.getValue().toString())));
                                    } else {
                                        predicates
                                                .add(cb.gt((root.join(objArray[0]).get(objArray[1])).as(Integer.class),
                                                        Integer.valueOf(obj.getValue().toString())));
                                    }

                                } else {
                                    predicates.add(cb.gt((root.get(obj.getObj())).as(Integer.class),
                                            Integer.valueOf(obj.getValue().toString())));
                                }

                                break;
                            case "BigDecimal":
                                if (obj.getObj().indexOf(".") != -1) {
                                    String[] objArray = obj.getObj().split("\\.");
                                    if (3 == objArray.length) {
                                        predicates.add(cb.gt((root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
                                                .as(BigDecimal.class), new BigDecimal(obj.getValue().toString())));
                                    } else {
                                        predicates.add(
                                                cb.gt((root.join(objArray[0]).get(objArray[1])).as(BigDecimal.class),
                                                        new BigDecimal(obj.getValue().toString())));
                                    }

                                } else {
                                    predicates.add(cb.gt((root.get(obj.getObj())).as(BigDecimal.class),
                                            new BigDecimal(obj.getValue().toString())));
                                }

                                break;
                            case "Double":
                                if (obj.getObj().indexOf(".") != -1) {
                                    String[] objArray = obj.getObj().split("\\.");
                                    if (3 == objArray.length) {
                                        predicates.add(cb.gt((root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
                                                .as(Double.class), Double.valueOf(obj.getValue().toString())));
                                    } else {
                                        predicates.add(cb.gt((root.join(objArray[0]).get(objArray[1])).as(Double.class),
                                                Double.valueOf(obj.getValue().toString())));
                                    }

                                } else {
                                    predicates.add(cb.gt((root.get(obj.getObj())).as(Double.class),
                                            Double.valueOf(obj.getValue().toString())));
                                }
                                break;
                            case "Float":
                                if (obj.getObj().indexOf(".") != -1) {
                                    String[] objArray = obj.getObj().split("\\.");
                                    if (3 == objArray.length) {
                                        predicates.add(cb.gt((root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
                                                .as(Float.class), Float.valueOf(obj.getValue().toString())));
                                    } else {
                                        predicates.add(cb.gt((root.join(objArray[0]).get(objArray[1])).as(Float.class),
                                                Float.valueOf(obj.getValue().toString())));
                                    }

                                } else {
                                    predicates.add(cb.gt((root.get(obj.getObj())).as(Float.class),
                                            Float.valueOf(obj.getValue().toString())));
                                }
                                break;
                            case "Long":
                                if (obj.getObj().indexOf(".") != -1) {
                                    String[] objArray = obj.getObj().split("\\.");
                                    if (3 == objArray.length) {
                                        predicates.add(cb.gt((root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
                                                .as(Long.class), Long.valueOf(obj.getValue().toString())));
                                    } else {
                                        predicates.add(cb.gt((root.join(objArray[0]).get(objArray[1])).as(Long.class),
                                                Long.valueOf(obj.getValue().toString())));
                                    }

                                } else {
                                    predicates.add(cb.gt((root.get(obj.getObj())).as(Long.class),
                                            Long.valueOf(obj.getValue().toString())));
                                }
                                break;
                            case "Date":
                                if (obj.getObj().indexOf(".") != -1) {
                                    String[] objArray = obj.getObj().split("\\.");
                                    if (3 == objArray.length) {
                                        predicates.add(
                                                cb.greaterThan((root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
                                                        .as(Date.class), (Date) obj.getValue()));
                                    } else {
                                        predicates.add(
                                                cb.greaterThan((root.join(objArray[0]).get(objArray[1])).as(Date.class),
                                                        (Date) obj.getValue()));
                                    }
                                } else {
                                    predicates.add(cb.greaterThan(root.get(obj.getObj()).as(Date.class),
                                            (Date) obj.getValue()));
                                }
                                break;
                            default:
                                break;
                            }
                        } else if ("RE".equals(obj.getOperator())) {
                            switch (obj.getElement()) {
                            case "Integer":
                                if (obj.getObj().indexOf(".") != -1) {
                                    String[] objArray = obj.getObj().split("\\.");
                                    if (3 == objArray.length) {
                                        predicates
                                                .add(cb.greaterThanOrEqualTo(
                                                        (root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
                                                                .as(Integer.class),
                                                        Integer.valueOf(obj.getValue().toString())));
                                    } else {
                                        predicates.add(cb.greaterThanOrEqualTo(
                                                (root.join(objArray[0]).get(objArray[1])).as(Integer.class),
                                                Integer.valueOf(obj.getValue().toString())));
                                    }

                                } else {
                                    predicates.add(cb.greaterThanOrEqualTo((root.get(obj.getObj())).as(Integer.class),
                                            Integer.valueOf(obj.getValue().toString())));
                                }
                                break;
                            case "BigDecimal":
                                if (obj.getObj().indexOf(".") != -1) {
                                    String[] objArray = obj.getObj().split("\\.");
                                    if (3 == objArray.length) {
                                        predicates
                                                .add(cb.greaterThanOrEqualTo(
                                                        (root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
                                                                .as(BigDecimal.class),
                                                        new BigDecimal(obj.getValue().toString())));
                                    } else {
                                        predicates.add(cb.greaterThanOrEqualTo(
                                                (root.join(objArray[0]).get(objArray[1])).as(BigDecimal.class),
                                                new BigDecimal(obj.getValue().toString())));
                                    }

                                } else {
                                    predicates
                                            .add(cb.greaterThanOrEqualTo((root.get(obj.getObj())).as(BigDecimal.class),
                                                    new BigDecimal(obj.getValue().toString())));
                                }
                                break;
                            case "Double":
                                if (obj.getObj().indexOf(".") != -1) {
                                    String[] objArray = obj.getObj().split("\\.");
                                    if (3 == objArray.length) {
                                        predicates
                                                .add(cb.greaterThanOrEqualTo(
                                                        (root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
                                                                .as(Double.class),
                                                        Double.valueOf(obj.getValue().toString())));
                                    } else {
                                        predicates.add(cb.greaterThanOrEqualTo(
                                                (root.join(objArray[0]).get(objArray[1])).as(Double.class),
                                                Double.valueOf(obj.getValue().toString())));
                                    }

                                } else {
                                    predicates.add(cb.greaterThanOrEqualTo((root.get(obj.getObj())).as(Double.class),
                                            Double.valueOf(obj.getValue().toString())));
                                }
                                break;
                            case "Float":
                                if (obj.getObj().indexOf(".") != -1) {
                                    String[] objArray = obj.getObj().split("\\.");
                                    if (3 == objArray.length) {
                                        predicates
                                                .add(cb.greaterThanOrEqualTo(
                                                        (root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
                                                                .as(Float.class),
                                                        Float.valueOf(obj.getValue().toString())));
                                    } else {
                                        predicates.add(cb.greaterThanOrEqualTo(
                                                (root.join(objArray[0]).get(objArray[1])).as(Float.class),
                                                Float.valueOf(obj.getValue().toString())));
                                    }

                                } else {
                                    predicates.add(cb.greaterThanOrEqualTo((root.get(obj.getObj())).as(Float.class),
                                            Float.valueOf(obj.getValue().toString())));
                                }
                                break;
                            case "Long":
                                if (obj.getObj().indexOf(".") != -1) {
                                    String[] objArray = obj.getObj().split("\\.");
                                    if (3 == objArray.length) {
                                        predicates.add(cb.greaterThanOrEqualTo(
                                                (root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
                                                        .as(Long.class),
                                                Long.valueOf(obj.getValue().toString())));
                                    } else {
                                        predicates.add(cb.greaterThanOrEqualTo(
                                                (root.join(objArray[0]).get(objArray[1])).as(Long.class),
                                                Long.valueOf(obj.getValue().toString())));
                                    }

                                } else {
                                    predicates.add(cb.greaterThanOrEqualTo((root.get(obj.getObj())).as(Long.class),
                                            Long.valueOf(obj.getValue().toString())));
                                }
                                break;
                            case "Date":
                                if (obj.getObj().indexOf(".") != -1) {
                                    String[] objArray = obj.getObj().split("\\.");
                                    if (3 == objArray.length) {
                                        predicates.add(cb.greaterThanOrEqualTo(
                                                (root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
                                                        .as(Date.class),
                                                (Date) obj.getValue()));
                                    } else {
                                        predicates.add(cb.greaterThanOrEqualTo(
                                                (root.join(objArray[0]).get(objArray[1])).as(Date.class),
                                                (Date) obj.getValue()));
                                    }

                                } else {
                                    predicates.add(cb.greaterThanOrEqualTo(root.get(obj.getObj()).as(Date.class),
                                            (Date) obj.getValue()));
                                }
                                break;
                            default:
                                break;
                            }
                        } else if ("L".equals(obj.getOperator())) {
                            switch (obj.getElement()) {
                            case "Integer":
                                if (obj.getObj().indexOf(".") != -1) {
                                    String[] objArray = obj.getObj().split("\\.");
                                    if (3 == objArray.length) {
                                        predicates.add(cb.lt((root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
                                                .as(Integer.class), Integer.valueOf(obj.getValue().toString())));
                                    } else {
                                        predicates
                                                .add(cb.lt((root.join(objArray[0]).get(objArray[1])).as(Integer.class),
                                                        Integer.valueOf(obj.getValue().toString())));
                                    }

                                } else {
                                    predicates.add(cb.lt((root.get(obj.getObj())).as(Integer.class),
                                            Integer.valueOf(obj.getValue().toString())));
                                }
                                break;
                            case "BigDecimal":
                                if (obj.getObj().indexOf(".") != -1) {
                                    String[] objArray = obj.getObj().split("\\.");
                                    if (3 == objArray.length) {
                                        predicates.add(cb.lt((root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
                                                .as(BigDecimal.class), new BigDecimal(obj.getValue().toString())));
                                    } else {
                                        predicates.add(
                                                cb.lt((root.join(objArray[0]).get(objArray[1])).as(BigDecimal.class),
                                                        new BigDecimal(obj.getValue().toString())));
                                    }
                                } else {
                                    predicates.add(cb.lt((root.get(obj.getObj())).as(BigDecimal.class),
                                            new BigDecimal(obj.getValue().toString())));
                                }
                                break;
                            case "Double":
                                if (obj.getObj().indexOf(".") != -1) {
                                    String[] objArray = obj.getObj().split("\\.");
                                    if (3 == objArray.length) {
                                        predicates.add(cb.lt((root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
                                                .as(Double.class), Double.valueOf(obj.getValue().toString())));
                                    } else {
                                        predicates.add(cb.lt((root.join(objArray[0]).get(objArray[1])).as(Double.class),
                                                Double.valueOf(obj.getValue().toString())));
                                    }

                                } else {
                                    predicates.add(cb.lt((root.get(obj.getObj())).as(Double.class),
                                            Double.valueOf(obj.getValue().toString())));
                                }
                                break;
                            case "Float":
                                if (obj.getObj().indexOf(".") != -1) {
                                    String[] objArray = obj.getObj().split("\\.");
                                    if (3 == objArray.length) {
                                        predicates.add(cb.lt((root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
                                                .as(Float.class), Float.valueOf(obj.getValue().toString())));
                                    } else {
                                        predicates.add(cb.lt((root.join(objArray[0]).get(objArray[1])).as(Float.class),
                                                Float.valueOf(obj.getValue().toString())));
                                    }
                                } else {
                                    predicates.add(cb.lt((root.get(obj.getObj())).as(Float.class),
                                            Float.valueOf(obj.getValue().toString())));
                                }
                                break;
                            case "Long":
                                if (obj.getObj().indexOf(".") != -1) {
                                    String[] objArray = obj.getObj().split("\\.");
                                    if (3 == objArray.length) {
                                        predicates.add(cb.lt((root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
                                                .as(Long.class), Long.valueOf(obj.getValue().toString())));
                                    } else {
                                        predicates.add(cb.lt((root.join(objArray[0]).get(objArray[1])).as(Long.class),
                                                Long.valueOf(obj.getValue().toString())));
                                    }
                                } else {
                                    predicates.add(cb.lt((root.get(obj.getObj())).as(Long.class),
                                            Long.valueOf(obj.getValue().toString())));
                                }
                                break;
                            case "Date":
                                if (obj.getObj().indexOf(".") != -1) {
                                    String[] objArray = obj.getObj().split("\\.");
                                    if (3 == objArray.length) {
                                        predicates.add(
                                                cb.lessThan((root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
                                                        .as(Date.class), (Date) obj.getValue()));
                                    } else {
                                        predicates.add(
                                                cb.lessThan((root.join(objArray[0]).get(objArray[1])).as(Date.class),
                                                        (Date) obj.getValue()));
                                    }
                                } else {
                                    predicates.add(
                                            cb.lessThan(root.get(obj.getObj()).as(Date.class), (Date) obj.getValue()));
                                }
                                break;
                            default:
                                break;
                            }
                        } else if ("LE".equals(obj.getOperator())) {
                            switch (obj.getElement()) {
                            case "Integer":
                                if (obj.getObj().indexOf(".") != -1) {
                                    String[] objArray = obj.getObj().split("\\.");
                                    if (3 == objArray.length) {
                                        predicates
                                                .add(cb.lessThanOrEqualTo(
                                                        (root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
                                                                .as(Integer.class),
                                                        Integer.valueOf(obj.getValue().toString())));
                                    } else {
                                        predicates.add(cb.lessThanOrEqualTo(
                                                (root.join(objArray[0]).get(objArray[1])).as(Integer.class),
                                                Integer.valueOf(obj.getValue().toString())));
                                    }

                                } else {
                                    predicates.add(cb.lessThanOrEqualTo((root.get(obj.getObj())).as(Integer.class),
                                            Integer.valueOf(obj.getValue().toString())));
                                }
                                break;
                            case "BigDecimal":
                                if (obj.getObj().indexOf(".") != -1) {
                                    String[] objArray = obj.getObj().split("\\.");
                                    if (3 == objArray.length) {
                                        predicates
                                                .add(cb.lessThanOrEqualTo(
                                                        (root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
                                                                .as(BigDecimal.class),
                                                        new BigDecimal(obj.getValue().toString())));
                                    } else {
                                        predicates.add(cb.lessThanOrEqualTo(
                                                (root.join(objArray[0]).get(objArray[1])).as(BigDecimal.class),
                                                new BigDecimal(obj.getValue().toString())));
                                    }

                                } else {
                                    predicates.add(cb.lessThanOrEqualTo((root.get(obj.getObj())).as(BigDecimal.class),
                                            new BigDecimal(obj.getValue().toString())));
                                }
                                break;
                            case "Double":
                                if (obj.getObj().indexOf(".") != -1) {
                                    String[] objArray = obj.getObj().split("\\.");
                                    if (3 == objArray.length) {
                                        predicates
                                                .add(cb.lessThanOrEqualTo(
                                                        (root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
                                                                .as(Double.class),
                                                        Double.valueOf(obj.getValue().toString())));
                                    } else {
                                        predicates.add(cb.lessThanOrEqualTo(
                                                (root.join(objArray[0]).get(objArray[1])).as(Double.class),
                                                Double.valueOf(obj.getValue().toString())));
                                    }

                                } else {
                                    predicates.add(cb.lessThanOrEqualTo((root.get(obj.getObj())).as(Double.class),
                                            Double.valueOf(obj.getValue().toString())));
                                }
                                break;
                            case "Float":
                                if (obj.getObj().indexOf(".") != -1) {
                                    String[] objArray = obj.getObj().split("\\.");
                                    if (3 == objArray.length) {
                                        predicates
                                                .add(cb.lessThanOrEqualTo(
                                                        (root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
                                                                .as(Float.class),
                                                        Float.valueOf(obj.getValue().toString())));
                                    } else {
                                        predicates.add(cb.lessThanOrEqualTo(
                                                (root.join(objArray[0]).get(objArray[1])).as(Float.class),
                                                Float.valueOf(obj.getValue().toString())));
                                    }

                                } else {
                                    predicates.add(cb.lessThanOrEqualTo((root.get(obj.getObj())).as(Float.class),
                                            Float.valueOf(obj.getValue().toString())));
                                }
                                break;
                            case "Long":
                                if (obj.getObj().indexOf(".") != -1) {
                                    String[] objArray = obj.getObj().split("\\.");
                                    if (3 == objArray.length) {
                                        predicates.add(cb.lessThanOrEqualTo(
                                                (root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
                                                        .as(Long.class),
                                                Long.valueOf(obj.getValue().toString())));
                                    } else {
                                        predicates.add(cb.lessThanOrEqualTo(
                                                (root.join(objArray[0]).get(objArray[1])).as(Long.class),
                                                Long.valueOf(obj.getValue().toString())));
                                    }

                                } else {
                                    predicates.add(cb.lessThanOrEqualTo((root.get(obj.getObj())).as(Long.class),
                                            Long.valueOf(obj.getValue().toString())));
                                }
                                break;
                            case "Date":
                                if (obj.getObj().indexOf(".") != -1) {
                                    String[] objArray = obj.getObj().split("\\.");
                                    if (3 == objArray.length) {
                                        predicates.add(cb.lessThanOrEqualTo(
                                                (root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
                                                        .as(Date.class),
                                                (Date) obj.getValue()));
                                    } else {
                                        predicates.add(cb.lessThanOrEqualTo(
                                                (root.join(objArray[0]).get(objArray[1])).as(Date.class),
                                                (Date) obj.getValue()));
                                    }

                                } else {
                                    predicates.add(cb.lessThanOrEqualTo(root.get(obj.getObj()).as(Date.class),
                                            (Date) obj.getValue()));
                                }
                                break;
                            default:
                                break;
                            }
                        } else if ("E".equals(obj.getOperator())) {
                            switch (obj.getElement()) {
                            case "Integer":
                                if (obj.getObj().indexOf(".") != -1) {
                                    String[] objArray = obj.getObj().split("\\.");
                                    if (3 == objArray.length) {
                                        predicates
                                                .add(cb.equal(
                                                        (root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
                                                                .as(Integer.class),
                                                        Integer.valueOf(obj.getValue().toString())));
                                    } else {
                                        predicates.add(
                                                cb.equal((root.join(objArray[0]).get(objArray[1])).as(Integer.class),
                                                        Integer.valueOf(obj.getValue().toString())));
                                    }

                                } else {
                                    predicates.add(cb.equal((root.get(obj.getObj())).as(Integer.class),
                                            Integer.valueOf(obj.getValue().toString())));
                                }
                                break;
                            case "BigDecimal":
                                if (obj.getObj().indexOf(".") != -1) {
                                    String[] objArray = obj.getObj().split("\\.");
                                    if (3 == objArray.length) {
                                        predicates
                                                .add(cb.equal(
                                                        (root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
                                                                .as(BigDecimal.class),
                                                        new BigDecimal(obj.getValue().toString())));
                                    } else {
                                        predicates.add(
                                                cb.equal((root.join(objArray[0]).get(objArray[1])).as(BigDecimal.class),
                                                        new BigDecimal(obj.getValue().toString())));
                                    }

                                } else {
                                    predicates.add(cb.equal((root.get(obj.getObj())).as(BigDecimal.class),
                                            new BigDecimal(obj.getValue().toString())));
                                }
                                break;
                            case "Double":
                                if (obj.getObj().indexOf(".") != -1) {
                                    String[] objArray = obj.getObj().split("\\.");
                                    if (3 == objArray.length) {
                                        predicates
                                                .add(cb.equal(
                                                        (root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
                                                                .as(Double.class),
                                                        Double.valueOf(obj.getValue().toString())));
                                    } else {
                                        predicates.add(
                                                cb.equal((root.join(objArray[0]).get(objArray[1])).as(Double.class),
                                                        Double.valueOf(obj.getValue().toString())));
                                    }

                                } else {
                                    predicates.add(cb.equal((root.get(obj.getObj())).as(Double.class),
                                            Double.valueOf(obj.getValue().toString())));
                                }
                                break;
                            case "Float":
                                if (obj.getObj().indexOf(".") != -1) {
                                    String[] objArray = obj.getObj().split("\\.");
                                    if (3 == objArray.length) {
                                        predicates
                                                .add(cb.equal(
                                                        (root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
                                                                .as(Float.class),
                                                        Float.valueOf(obj.getValue().toString())));
                                    } else {
                                        predicates
                                                .add(cb.equal((root.join(objArray[0]).get(objArray[1])).as(Float.class),
                                                        Float.valueOf(obj.getValue().toString())));
                                    }

                                } else {
                                    predicates.add(cb.equal((root.get(obj.getObj())).as(Float.class),
                                            Float.valueOf(obj.getValue().toString())));
                                }
                                break;
                            case "Long":
                                if (obj.getObj().indexOf(".") != -1) {
                                    String[] objArray = obj.getObj().split("\\.");
                                    if (3 == objArray.length) {
                                        predicates
                                                .add(cb.equal((root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
                                                        .as(Long.class), Long.valueOf(obj.getValue().toString())));
                                    } else {
                                        predicates
                                                .add(cb.equal((root.join(objArray[0]).get(objArray[1])).as(Long.class),
                                                        Long.valueOf(obj.getValue().toString())));
                                    }

                                } else {
                                    predicates.add(cb.equal((root.get(obj.getObj())).as(Long.class),
                                            Long.valueOf(obj.getValue().toString())));
                                }
                                break;
                            case "String":
                                if (obj.getObj().indexOf(".") != -1) {
                                    String[] objArray = obj.getObj().split("\\.");
                                    if (ObjectHelper.isNotEmpty(obj.getValue())) {
                                        if (3 == objArray.length) {
                                            if (obj.getValue().equals("isnull")) {
                                                predicates.add(cb.isNull(
                                                        root.get(objArray[0]).get(objArray[1]).get(objArray[2])));
                                            } else if (obj.getValue().equals("isnotnull")) {
                                                predicates.add(cb.isNotNull(
                                                        root.get(objArray[0]).get(objArray[1]).get(objArray[2])));
                                            } else {
                                                predicates.add(cb.equal(
                                                        (root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
                                                                .as(String.class),
                                                        String.valueOf(obj.getValue().toString())));
                                            }
                                        } else {
                                            if (obj.getValue().equals("isnull")) {
                                                predicates.add(cb.isNull(root.get(objArray[0]).get(objArray[1])));
                                            } else if (obj.getValue().equals("isnotnull")) {
                                                predicates.add(cb.isNotNull(root.get(objArray[0]).get(objArray[1])));
                                            } else {
                                                predicates.add(cb.equal(
                                                        (root.join(objArray[0]).get(objArray[1])).as(String.class),
                                                        String.valueOf(obj.getValue().toString())));
                                            }
                                        }

                                    }
                                } else {
                                    if (ObjectHelper.isNotEmpty(obj.getValue())) {
                                        if (obj.getValue().equals("isnull")) {
                                            predicates.add(cb.isNull(root.get(obj.getObj())));
                                        } else if (obj.getValue().equals("isnotnull")) {
                                            predicates.add(cb.isNotNull(root.get(obj.getObj())));
                                        } else {
                                            predicates.add(cb.equal((root.get(obj.getObj())).as(String.class),
                                                    String.valueOf(obj.getValue().toString())));
                                        }
                                    }
                                }
                                break;
                            case "Date":
                                if (obj.getObj().indexOf(".") != -1) {
                                    String[] objArray = obj.getObj().split("\\.");
                                    if (3 == objArray.length) {
                                        predicates
                                                .add(cb.equal((root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
                                                        .as(Date.class), (Date) (obj.getValue())));
                                    } else {
                                        predicates
                                                .add(cb.equal((root.join(objArray[0]).get(objArray[1])).as(Date.class),
                                                        (Date) (obj.getValue())));
                                    }

                                } else {
                                    predicates.add(
                                            cb.equal((root.get(obj.getObj())).as(Date.class), (Date) (obj.getValue())));
                                }
                                break;
                            case "Boolean":
                                if (obj.getObj().indexOf(".") != -1) {
                                    String[] objArray = obj.getObj().split("\\.");
                                    if (3 == objArray.length) {
                                        predicates
                                                .add(cb.equal((root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
                                                        .as(Boolean.class), (Boolean) (obj.getValue())));
                                    } else {
                                        predicates.add(
                                                cb.equal((root.join(objArray[0]).get(objArray[1])).as(Boolean.class),
                                                        (Boolean) (obj.getValue())));
                                    }

                                } else {
                                    predicates.add(cb.equal((root.get(obj.getObj())).as(Boolean.class),
                                            (Boolean) (obj.getValue())));
                                }
                            default:
                                break;
                            }
                        } else if ("NE".equals(obj.getOperator())) {
                            switch (obj.getElement()) {
                            case "Integer":
                                if (obj.getObj().indexOf(".") != -1) {
                                    String[] objArray = obj.getObj().split("\\.");
                                    if (3 == objArray.length) {
                                        predicates
                                                .add(cb.notEqual(
                                                        (root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
                                                                .as(Integer.class),
                                                        Integer.valueOf(obj.getValue().toString())));
                                    } else {
                                        predicates.add(
                                                cb.notEqual((root.join(objArray[0]).get(objArray[1])).as(Integer.class),
                                                        Integer.valueOf(obj.getValue().toString())));
                                    }

                                } else {
                                    predicates.add(cb.notEqual((root.get(obj.getObj())).as(Integer.class),
                                            Integer.valueOf(obj.getValue().toString())));
                                }
                                break;
                            case "BigDecimal":
                                if (obj.getObj().indexOf(".") != -1) {
                                    String[] objArray = obj.getObj().split("\\.");
                                    if (3 == objArray.length) {
                                        predicates
                                                .add(cb.notEqual(
                                                        (root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
                                                                .as(BigDecimal.class),
                                                        new BigDecimal(obj.getValue().toString())));
                                    } else {
                                        predicates.add(cb.notEqual(
                                                (root.join(objArray[0]).get(objArray[1])).as(BigDecimal.class),
                                                new BigDecimal(obj.getValue().toString())));
                                    }

                                } else {
                                    predicates.add(cb.notEqual((root.get(obj.getObj())).as(BigDecimal.class),
                                            new BigDecimal(obj.getValue().toString())));
                                }
                                break;
                            case "Double":
                                if (obj.getObj().indexOf(".") != -1) {
                                    String[] objArray = obj.getObj().split("\\.");
                                    if (3 == objArray.length) {
                                        predicates
                                                .add(cb.notEqual(
                                                        (root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
                                                                .as(Double.class),
                                                        Double.valueOf(obj.getValue().toString())));
                                    } else {
                                        predicates.add(
                                                cb.notEqual((root.join(objArray[0]).get(objArray[1])).as(Double.class),
                                                        Double.valueOf(obj.getValue().toString())));
                                    }

                                } else {
                                    predicates.add(cb.notEqual((root.get(obj.getObj())).as(Double.class),
                                            Double.valueOf(obj.getValue().toString())));
                                }
                                break;
                            case "Float":
                                if (obj.getObj().indexOf(".") != -1) {
                                    String[] objArray = obj.getObj().split("\\.");
                                    if (3 == objArray.length) {
                                        predicates
                                                .add(cb.notEqual(
                                                        (root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
                                                                .as(Float.class),
                                                        Float.valueOf(obj.getValue().toString())));
                                    } else {
                                        predicates.add(
                                                cb.notEqual((root.join(objArray[0]).get(objArray[1])).as(Float.class),
                                                        Float.valueOf(obj.getValue().toString())));
                                    }

                                } else {
                                    predicates.add(cb.notEqual((root.get(obj.getObj())).as(Float.class),
                                            Float.valueOf(obj.getValue().toString())));
                                }
                                break;
                            case "Long":
                                if (obj.getObj().indexOf(".") != -1) {
                                    String[] objArray = obj.getObj().split("\\.");
                                    if (3 == objArray.length) {
                                        predicates.add(
                                                cb.notEqual((root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
                                                        .as(Long.class), Long.valueOf(obj.getValue().toString())));
                                    } else {
                                        predicates.add(
                                                cb.notEqual((root.join(objArray[0]).get(objArray[1])).as(Long.class),
                                                        Long.valueOf(obj.getValue().toString())));
                                    }

                                } else {
                                    predicates.add(cb.notEqual((root.get(obj.getObj())).as(Long.class),
                                            Long.valueOf(obj.getValue().toString())));
                                }
                                break;
                            case "String":
                                if (obj.getObj().indexOf(".") != -1) {
                                    String[] objArray = obj.getObj().split("\\.");
                                    if (3 == objArray.length) {
                                        predicates
                                                .add(cb.notEqual(
                                                        (root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
                                                                .as(String.class),
                                                        String.valueOf(obj.getValue().toString())));
                                    } else {
                                        predicates.add(
                                                cb.notEqual((root.join(objArray[0]).get(objArray[1])).as(String.class),
                                                        String.valueOf(obj.getValue().toString())));
                                    }

                                } else {
                                    predicates.add(cb.notEqual((root.get(obj.getObj())).as(String.class),
                                            String.valueOf(obj.getValue().toString())));
                                }
                                break;
                            case "Date":
                                if (obj.getObj().indexOf(".") != -1) {
                                    String[] objArray = obj.getObj().split("\\.");
                                    if (3 == objArray.length) {
                                        predicates.add(
                                                cb.notEqual((root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
                                                        .as(Date.class), (Date) (obj.getValue())));
                                    } else {
                                        predicates.add(
                                                cb.notEqual((root.join(objArray[0]).get(objArray[1])).as(Date.class),
                                                        (Date) (obj.getValue())));
                                    }

                                } else {
                                    predicates.add(cb.notEqual((root.get(obj.getObj())).as(Date.class),
                                            (Date) (obj.getValue())));
                                }
                                break;
                            case "Boolean":
                                if (obj.getObj().indexOf(".") != -1) {
                                    String[] objArray = obj.getObj().split("\\.");
                                    if (3 == objArray.length) {
                                        predicates.add(
                                                cb.notEqual((root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
                                                        .as(Boolean.class), (Boolean) (obj.getValue())));
                                    } else {
                                        predicates.add(
                                                cb.notEqual((root.join(objArray[0]).get(objArray[1])).as(Boolean.class),
                                                        (Boolean) (obj.getValue())));
                                    }

                                } else {
                                    predicates.add(cb.notEqual((root.get(obj.getObj())).as(Boolean.class),
                                            (Boolean) (obj.getValue())));
                                }
                                break;
                            default:
                                break;
                            }
                        } else if ("LK".equals(obj.getOperator())) {
                            switch (obj.getElement()) {
                            case "String":
                                if (obj.getObj().indexOf(".") != -1) {
                                    String[] objArray = obj.getObj().split("\\.");
                                    if (3 == objArray.length) {
                                        predicates.add(cb.like((root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
                                                .as(String.class), ("%" + obj.getValue().toString() + "%")));
                                    } else {
                                        Path<Object> testObj = root.join(objArray[0]).get(objArray[1]);
                                        predicates.add(cb.like(testObj.as(String.class),
                                                ("%" + obj.getValue().toString() + "%")));
                                    }

                                } else {
                                    predicates.add(cb.like((root.get(obj.getObj())).as(String.class),
                                            ("%" + obj.getValue().toString() + "%")));
                                }
                                break;
                            default:
                                break;
                            }
                        } else if ("IN".equals(obj.getOperator())) {
                            List<Object> queryList = (List<Object>) obj.getValue();
                            if (obj.getObj().indexOf(".") != -1) {
                                String[] objArray = obj.getObj().split("\\.");
                                if (3 == objArray.length) {
                                    Expression<Object> exp = root.get(objArray[0]).get(objArray[1]).get(objArray[2]);
                                    predicates.add(exp.in(queryList));
                                } else {
                                    Expression<Object> exp = root.get(objArray[0]).get(objArray[1]);
                                    predicates.add(exp.in(queryList));
                                }
                            } else {
                                Expression<Object> exp = root.get(obj.getObj());
                                predicates.add(exp.in(queryList));
                            }
                        } else if ("NN".equals(obj.getOperator())) {
                            List<Object> queryList = (List<Object>) obj.getValue();
                            if (obj.getObj().indexOf(".") != -1) {
                                String[] objArray = obj.getObj().split("\\.");
                                if (3 == objArray.length) {
                                    Expression<Object> exp = root.get(objArray[0]).get(objArray[1]).get(objArray[2]);
                                    predicates.add(exp.in(queryList).not());
                                } else {
                                    Expression<Object> exp = root.get(objArray[0]).get(objArray[1]);
                                    predicates.add(exp.in(queryList).not());
                                }
                            } else {
                                Expression<Object> exp = root.get(obj.getObj());
                                predicates.add(exp.in(queryList).not());
                            }

                        } else if ("OL".equals(obj.getOperator())) {
                            switch (obj.getElement()) {
                            case "String":
                                List<Predicate> newPredicates = new ArrayList<Predicate>();
                                List<Object> conditions = (List<Object>) obj.getValue();
                                for (Object objs : conditions) {
                                    if (obj.getObj().indexOf(".") != -1) {
                                        String[] objArray = obj.getObj().split("\\.");
                                        if (3 == objArray.length) {
                                            newPredicates.add(
                                                    cb.like((root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
                                                            .as(String.class), ("%" + objs.toString() + "%")));
                                        } else {
                                            newPredicates.add(
                                                    cb.like((root.join(objArray[0]).get(objArray[1])).as(String.class),
                                                            ("%" + objs.toString() + "%")));
                                        }

                                    } else {
                                        newPredicates.add(cb.like((root.get(obj.getObj())).as(String.class),
                                                ("%" + objs.toString() + "%")));
                                    }

                                }
                                Predicate predocate = cb.or(newPredicates.toArray(new Predicate[newPredicates.size()]));
                                predicates.add(predocate);
                                break;
                            default:
                                break;
                            }
                        } else if ("BT".equals(obj.getOperator())) {
                            String[] times = obj.getValue().toString().split(",");
                            if (ObjectHelper.isNotEmpty(times)) {
                                if (ObjectHelper.isNotEmpty(times[0])) {
                                    if (obj.getObj().indexOf(".") != -1) {
                                        String[] objArray = obj.getObj().split("\\.");
                                        if (3 == objArray.length) {
                                            predicates.add(cb.greaterThanOrEqualTo(
                                                    (root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
                                                            .as(Long.class),
                                                    Long.valueOf(times[0])));
                                        } else {
                                            predicates.add(cb.greaterThanOrEqualTo(
                                                    (root.join(objArray[0]).get(objArray[1])).as(Long.class),
                                                    Long.valueOf(times[0])));
                                        }

                                    } else {
                                        predicates.add(cb.greaterThanOrEqualTo((root.get(obj.getObj())).as(Long.class),
                                                Long.valueOf(times[0])));
                                    }

                                }
                                if (times.length > 1 && ObjectHelper.isNotEmpty(times[1])) {
                                    if (obj.getObj().indexOf(".") != -1) {
                                        String[] objArray = obj.getObj().split("\\.");
                                        if (3 == objArray.length) {
                                            predicates.add(cb.lessThanOrEqualTo(
                                                    (root.get(objArray[0]).get(objArray[1]).get(objArray[2]))
                                                            .as(Long.class),
                                                    Long.valueOf(times[1])));
                                        } else {
                                            predicates.add(cb.lessThanOrEqualTo(
                                                    (root.join(objArray[0]).get(objArray[1])).as(Long.class),
                                                    Long.valueOf(times[1])));
                                        }

                                    } else {
                                        predicates.add(cb.lessThanOrEqualTo((root.get(obj.getObj())).as(Long.class),
                                                Long.valueOf(times[1])));
                                    }

                                }
                            }
                        } else if ("OB".equals(obj.getOperator())) {
                            isDesc = true;
                            if (obj.getObj().indexOf(".") != -1) {
                                String[] objArray = obj.getObj().split("\\.");
                                if (3 == objArray.length) {
                                    if (objArray[2].indexOf(",") != -1) {
                                        String[] objArrays = objArray[2].split(",");

                                        if (obj.getValue().equals("ASC")) {
                                            query.orderBy(
                                                    cb.asc(root.get(objArray[0]).get(objArray[1]).get(objArrays[0])),
                                                    cb.asc(root.get(objArray[0]).get(objArray[1]).get(objArrays[1])));
                                        } else {
                                            query.orderBy(
                                                    cb.desc(root.get(objArray[0]).get(objArray[1]).get(objArrays[0])),
                                                    cb.desc(root.get(objArray[0]).get(objArray[1]).get(objArrays[1])));
                                        }

                                    } else {
                                        if (obj.getValue().equals("DESC")) {
                                            query.orderBy(
                                                    cb.desc(root.get(objArray[0]).get(objArray[1]).get(objArray[2])));
                                        } else {
                                            query.orderBy(
                                                    cb.asc(root.get(objArray[0]).get(objArray[1]).get(objArray[2])));
                                        }

                                    }
                                } else {
                                    if (objArray[1].indexOf(",") != -1) {
                                        String[] objArrays = objArray[1].split(",");

                                        if (obj.getValue().equals("ASC")) {
                                            query.orderBy(cb.asc(root.get(objArray[0]).get(objArrays[0])),
                                                    cb.asc(root.get(objArray[0]).get(objArrays[1])));
                                        } else {
                                            query.orderBy(cb.desc(root.get(objArray[0]).get(objArrays[0])),
                                                    cb.desc(root.get(objArray[0]).get(objArrays[1])));
                                        }

                                    } else {
                                        if (obj.getValue().equals("DESC")) {
                                            query.orderBy(cb.desc(root.get(objArray[0]).get(objArray[1])));
                                        } else {
                                            query.orderBy(cb.asc(root.get(objArray[0]).get(objArray[1])));
                                        }
                                    }
                                }
                            } else {
                                if (obj.getObj().indexOf(",") != -1) {
                                    String[] objArrays = obj.getObj().split(",");
                                    if (obj.getValue().equals("DESC")) {
                                        query.orderBy(cb.desc(root.get(objArrays[0])), cb.desc(root.get(objArrays[1])));
                                    } else {
                                        query.orderBy(cb.asc(root.get(objArrays[0])), cb.desc(root.get(objArrays[1])));
                                    }

                                } else {
                                    if (obj.getValue().equals("DESC")) {
                                        query.orderBy(cb.desc(root.get(obj.getObj())));
                                    } else {
                                        query.orderBy(cb.asc(root.get(obj.getObj())));
                                    }

                                }
                            }
                        }
                    }
                }
                if (!isDesc) {
                    query.orderBy(cb.desc(root.get("createTime")));
                }
                //加上数据权限
                if(ObjectHelper.isNotEmpty(dataOperPermDto)){
                    //白名单
                    if(ObjectHelper.isNotEmpty(dataOperPermDto.getWhiteDataEmps() )  &&  ObjectHelper.isNotEmpty( dataOperPermDto.getWhiteDataOrgs()) ){
                        Expression<Object> exp = root.get("createBy");
                        Expression<Object> orgexp = root.get("createOrgCd");
                        predicates.add(cb.or(exp.in(dataOperPermDto.getWhiteDataEmps()),orgexp.in(dataOperPermDto.getWhiteDataOrgs())));

                    }else if ( ObjectHelper.isNotEmpty(dataOperPermDto.getWhiteDataEmps() )  &&  ObjectHelper.isEmpty( dataOperPermDto.getWhiteDataOrgs()) ){
                        Expression<Object> exp = root.get("createBy");
                        predicates.add(cb.and(exp.in(dataOperPermDto.getWhiteDataEmps())));

                    }else if ( ObjectHelper.isEmpty(dataOperPermDto.getWhiteDataEmps() ) && ObjectHelper.isNotEmpty( dataOperPermDto.getWhiteDataOrgs()) ){
                        Expression<Object> exp = root.get("createOrgCd");
                        predicates.add(cb.and(exp.in(dataOperPermDto.getWhiteDataOrgs())));
                    }
                    //黑名单
                    if(ObjectHelper.isNotEmpty(dataOperPermDto.getBlackDataEmps() )  &&  ObjectHelper.isNotEmpty(dataOperPermDto.getBlackDataOrgs()) ){
                        Expression<Object> exp = root.get("createBy");
                        Expression<Object> orgexp = root.get("createOrgCd");
                            predicates.add(cb.and(exp.in(dataOperPermDto.getBlackDataEmps()).not(), orgexp.in(dataOperPermDto.getBlackDataOrgs()).not()));
                    }else if ( ObjectHelper.isNotEmpty(dataOperPermDto.getBlackDataEmps() )  &&  ObjectHelper.isEmpty(dataOperPermDto.getBlackDataOrgs()) ){
                        Expression<Object> exp = root.get("createBy");
                        predicates.add(cb.and(exp.in(dataOperPermDto.getBlackDataEmps()).not()));
                    }else if ( ObjectHelper.isEmpty(dataOperPermDto.getBlackDataEmps() ) && ObjectHelper.isNotEmpty( dataOperPermDto.getBlackDataOrgs()) ){
                        Expression<Object> exp = root.get("createOrgCd");
                        predicates.add(cb.and(exp.in(dataOperPermDto.getWhiteDataOrgs()).not()));
                    }
                }
                query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
                return query.getRestriction();
            }

        };
        org.springframework.data.domain.Pageable springPageable = new PageRequest(pageable.getPage() - 1,
                pageable.getRows());
        org.springframework.data.domain.Page<T> pages = super.findAll(specification, springPageable);
        Page<T> zdPage = new PageImpl<T>(pageable);
        zdPage.setRecords(pages.getContent());
        zdPage.setTotalRows(pages.getTotalElements());
        return zdPage;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<T> findByHqlPage(Pageable pageable, String hql, Map<String, Object> condition, DataOperPermDto dataOperPermDto) {
        if (pageable == null) {
            throw new IllegalArgumentException("pageable 不能为空!");
        }
        int orderIndex=hql.toLowerCase().indexOf("order");
        int groupIndex=hql.toLowerCase().indexOf("group");
        StringBuffer sb=null;
        String lastStr="";
        if((orderIndex+groupIndex)<0){
            sb=new StringBuffer(hql);
        }else{
            //存在order
            int min=0;
            if(orderIndex>0){
                min=orderIndex;
            }
            if(groupIndex>0&&groupIndex<min){
                min=groupIndex;
            }
            sb=new StringBuffer(hql.substring(0,min));
            lastStr=" "+hql.substring(min);
        }
        //加上数据权限
        if(ObjectHelper.isNotEmpty(dataOperPermDto)){
            //白名单
            if(ObjectHelper.isNotEmpty(dataOperPermDto.getWhiteDataEmps() )  &&  ObjectHelper.isNotEmpty( dataOperPermDto.getWhiteDataOrgs()) ){
                sb.append(" AND  ( createBy in (:whiteDataEmps) or  createOrgCd in ( :whiteDataOrgs )  )");
                condition.put("whiteDataEmps", dataOperPermDto.getWhiteDataEmps());
                condition.put("whiteDataOrgs", dataOperPermDto.getWhiteDataOrgs());
            }else if ( ObjectHelper.isNotEmpty(dataOperPermDto.getWhiteDataEmps() )  &&  ObjectHelper.isEmpty( dataOperPermDto.getWhiteDataOrgs()) ){
                sb.append(" AND   createBy in (:whiteDataEmps)  ");
                condition.put("whiteDataEmps", dataOperPermDto.getWhiteDataEmps());
            }else if ( ObjectHelper.isEmpty(dataOperPermDto.getWhiteDataEmps() ) && ObjectHelper.isNotEmpty( dataOperPermDto.getWhiteDataOrgs()) ){
                sb.append(" AND   createOrgCd in (:whiteDataOrgs)  ");
                condition.put("whiteDataOrgs", dataOperPermDto.getWhiteDataOrgs() );
            }
            //黑名单
            if(ObjectHelper.isNotEmpty(dataOperPermDto.getBlackDataEmps() )  &&  ObjectHelper.isNotEmpty(dataOperPermDto.getBlackDataOrgs()) ){
                sb.append(" AND  ( createBy  not in (:blackDataEmps) AND  createOrgCd not in ( :blackDataOrgs )  )");
                condition.put("blackDataEmps", dataOperPermDto.getBlackDataEmps());
                condition.put("blackDataOrgs", dataOperPermDto.getBlackDataOrgs());
            }else if ( ObjectHelper.isNotEmpty(dataOperPermDto.getBlackDataEmps() )  &&  ObjectHelper.isEmpty(dataOperPermDto.getBlackDataOrgs()) ){
                sb.append(" AND   createBy not in (:blackDataEmps)  ");
                condition.put("blackDataEmps", dataOperPermDto.getBlackDataEmps());
            }else if ( ObjectHelper.isEmpty(dataOperPermDto.getBlackDataEmps() ) && ObjectHelper.isNotEmpty( dataOperPermDto.getBlackDataOrgs()) ){
                sb.append(" AND   createOrgCd not in (:blackDataOrgs)  ");
                condition.put("blackDataOrgs", dataOperPermDto.getBlackDataOrgs() );
            }
        }
        sb.append(lastStr);
        // 申明分页数据
        Page<T> pager = new PageImpl<T>(pageable);
        // 创建query
        Query q = entityManager.createQuery(sb.toString());
        // 设置查询条件
        for (Serializable key : condition.keySet()) {
            q.setParameter(key.toString(), condition.get(key));
        }
        q.setFirstResult((pageable.getPage() - 1) * pageable.getRows());
        q.setMaxResults(pageable.getRows());
        pager.setRecords(q.getResultList());
        pager.setTotalRows(this.countAll(hql, condition));
        return pager;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findByHql(String hql, Map<String, Object> condition, DataOperPermDto dataOperPermDto) {
        int orderIndex=hql.toLowerCase().indexOf("order");
        int groupIndex=hql.toLowerCase().indexOf("group");
        StringBuffer sb=null;
        String lastStr="";
        if((orderIndex+groupIndex)<0){
            sb=new StringBuffer(hql);
        }else{
            //存在order
            int min=0;
            if(orderIndex>0){
                min=orderIndex;
            }
            if(groupIndex>0&&groupIndex<min){
                min=groupIndex;
            }
            sb=new StringBuffer(hql.substring(0,min));
            lastStr=" "+hql.substring(min);
        }
        //加上数据权限
        if(ObjectHelper.isNotEmpty(dataOperPermDto))
        {
            //白名单
            if(ObjectHelper.isNotEmpty(dataOperPermDto.getWhiteDataEmps() )  &&  ObjectHelper.isNotEmpty( dataOperPermDto.getWhiteDataOrgs()) )
            {
                sb.append(" AND  ( createBy in (:whiteDataEmps) or  createOrgCd in ( :whiteDataOrgs )  )");
                condition.put("whiteDataEmps", dataOperPermDto.getWhiteDataEmps());
                condition.put("whiteDataOrgs", dataOperPermDto.getWhiteDataOrgs());
            }else if ( ObjectHelper.isNotEmpty(dataOperPermDto.getWhiteDataEmps() )  &&  ObjectHelper.isEmpty( dataOperPermDto.getWhiteDataOrgs()) )
            {
                sb.append(" AND   createBy in (:whiteDataEmps)  ");
                condition.put("whiteDataEmps", dataOperPermDto.getWhiteDataEmps());
            }else if ( ObjectHelper.isEmpty(dataOperPermDto.getWhiteDataEmps() ) && ObjectHelper.isNotEmpty( dataOperPermDto.getWhiteDataOrgs()) )
            {
                sb.append(" AND   createOrgCd in (:whiteDataOrgs)  ");
                condition.put("whiteDataOrgs", dataOperPermDto.getWhiteDataOrgs() );
            }
            //黑名单
            if(ObjectHelper.isNotEmpty(dataOperPermDto.getBlackDataEmps() )  &&  ObjectHelper.isNotEmpty(dataOperPermDto.getBlackDataOrgs()) )
            {
                sb.append(" AND  ( createBy  not in (:blackDataEmps) AND  createOrgCd not in ( :blackDataOrgs )  )");
                condition.put("blackDataEmps", dataOperPermDto.getBlackDataEmps());
                condition.put("blackDataOrgs", dataOperPermDto.getBlackDataOrgs());
            }else if ( ObjectHelper.isNotEmpty(dataOperPermDto.getBlackDataEmps() )  &&  ObjectHelper.isEmpty(dataOperPermDto.getBlackDataOrgs()) )
            {
                sb.append(" AND   createBy not in (:blackDataEmps)  ");
                condition.put("blackDataEmps", dataOperPermDto.getBlackDataEmps());
            }else if ( ObjectHelper.isEmpty(dataOperPermDto.getBlackDataEmps() ) && ObjectHelper.isNotEmpty( dataOperPermDto.getBlackDataOrgs()) )
            {
                sb.append(" AND   createOrgCd not in (:blackDataOrgs)  ");
                condition.put("blackDataOrgs", dataOperPermDto.getBlackDataOrgs() );
            }
        }
        sb.append(lastStr);
        // 获得query对象
        Query q = entityManager.createQuery(sb.toString());
        // 将查询条件注入HQL语句中
        for (Serializable key : condition.keySet()) {
            q.setParameter(key.toString(), condition.get(key));
        }
        // 执行查询
        List<T> sourceData = (List<T>) q.getResultList();
        return sourceData;
    }
}
