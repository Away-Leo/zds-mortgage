package com.zdsoft.finance.workbench.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.workbench.entity.RemindPool;

/**
 * 提醒池
 * @createTime 2017-01-17
 * @author <a href="mailto:gufeng@zdsoft.cn">gufeng</a>
 * @version 1.0
 */
public interface RemindPoolService extends BaseService<RemindPool>{

	/**
	 * 根据分类查询
	 * @param string
	 * @return
	 */
	public List<RemindPool> findByCategory(String category);

}
