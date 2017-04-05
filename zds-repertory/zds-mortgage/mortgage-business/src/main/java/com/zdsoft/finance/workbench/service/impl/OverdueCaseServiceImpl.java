package com.zdsoft.finance.workbench.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.workbench.repository.OverdueCaseRepository;
import com.zdsoft.finance.workbench.service.OverdueCaseService;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: OverdueCaseServiceImpl.java 
 * @ClassName: OverdueCaseServiceImpl 
 * @Description: 逾期案件接口实现
 * @author longwei 
 * @date 2017年2月23日 上午10:23:58 
 * @version V1.0
 */
@Service("overdueCaseService")
public class OverdueCaseServiceImpl implements OverdueCaseService {
    
	@Autowired
	private OverdueCaseRepository overdueCaseRepository;
	
	/**
	 * 
	 * <p>Title: findPageOverdueCase</p>   
	 * <p>Description: 多条件分页查询逾期案件</p>   
	 * @param map
	 * @param pageable
	 * @return
	 * @throws Exception   
	 * @see com.zdsoft.finance.workbench.service.OverdueCaseService#findPageOverdueCase(java.util.Map, com.zdsoft.framework.core.common.page.Pageable)
	 */
	@Override
	public Page<Map<String, Object>> findPageOverdueCase(
			Map<String, Object> map, Pageable pageable) throws Exception {
		
		return overdueCaseRepository.findPageOverdueCase(map, pageable);
	}
    
	/**
	 * 
	 * <p>Title: findSumOverdueCase</p>   
	 * <p>Description: 多条件查询逾期案件总数</p>   
	 * @param map
	 * @return
	 * @throws Exception   
	 * @see com.zdsoft.finance.workbench.service.OverdueCaseService#findSumOverdueCase(java.util.Map)
	 */
	@Override
	public List<Map<String, Object>> findSumOverdueCase(Map<String, Object> map)
			throws Exception {
	
		return overdueCaseRepository.findSumOverdueCase(map);
	}

	

}
