package com.zdsoft.finance.workbench.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: OverdueCaseService.java 
 * @ClassName: OverdueCaseService 
 * @Description: 逾期案件接口
 * @author longwei 
 * @date 2017年2月23日 上午10:23:18 
 * @version V1.0
 */
public interface OverdueCaseService {
	
	/**
	 * 
	 * @Title: findPageOverdueCase 
	 * @Description: 多条件分页查询逾期案件
	 * @author yangjia 
	 * @param map
	 * @param pageable
	 * @return
	 * @throws Exception
	 */
	public  Page<Map<String,Object>> findPageOverdueCase (Map<String,Object> map,Pageable pageable) throws Exception;
	
	/**
	 * 
	 * @Title: findSumOverdueCase 
	 * @Description: 多条件查询逾期案件总数
	 * @author yangjia 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public  List<Map<String,Object>> findSumOverdueCase (Map<String,Object> map) throws Exception;
}
