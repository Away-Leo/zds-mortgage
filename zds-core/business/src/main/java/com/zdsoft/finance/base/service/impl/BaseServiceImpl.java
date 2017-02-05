package com.zdsoft.finance.base.service.impl;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.domain.BaseEntity;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class BaseServiceImpl<T extends BaseEntity, CT extends CustomRepository<T, String>>
		implements BaseService<T> {

	@Autowired
	public CT customReposity;

	@Log
	public Logger logger;

	@Override
	public Page<T> findByHqlConditions(Pageable pageable, List<QueryObj> li) {
		return customReposity.findByHqlConditions(pageable, li);
	}

	@Override
	@Transactional
	public T saveEntity(T t) throws BusinessException {
		return customReposity.saveEntity(t);
	}

	@Override
	@Transactional
	public T updateEntity(T t) throws BusinessException {

		if (ObjectHelper.isEmpty(t) || ObjectHelper.isEmpty(t.getId())) {
			throw new BusinessException("10010004", "传入参数为空");
		}
		T oldT = findOne(t.getId());
		if (ObjectHelper.isEmpty(oldT)) {
			throw new BusinessException("10010002", "根据相应参数未找到相应的数据");
		}
		BeanUtils.copyProperties(t, oldT);

		return customReposity.updateEntity(oldT);
	}

	@Override
	@Transactional
	public T saveOrUpdateEntity(T t) throws BusinessException {
		if (ObjectHelper.isNotEmpty(t.getId())) {
			return this.updateEntity(t);
		} else {
			return this.saveEntity(t);
		}
	}

	@Override
	@Transactional
	public T logicDelete(T t) {
		return customReposity.logicDelete(t);
	}

	@Override
	@Transactional
	public T logicDelete(String id) throws BusinessException {
		if (ObjectHelper.isEmpty(id)) {
			throw new BusinessException("10010004", "传入参数为空");
		}
		T oldT = findOne(id);
		if (ObjectHelper.isEmpty(oldT)) {
			throw new BusinessException("10010002", "根据相应参数未找到相应的数据");
		}
		return customReposity.logicDelete(oldT);
	}

	@Override
	public T findOne(String id) throws BusinessException {
		if (ObjectHelper.isEmpty(id)) {
			throw new BusinessException("10010004", "传入参数为空");
		}
		return customReposity.findOne(id);
	}

}
