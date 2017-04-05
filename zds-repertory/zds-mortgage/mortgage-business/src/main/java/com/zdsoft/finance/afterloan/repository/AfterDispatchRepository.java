package com.zdsoft.finance.afterloan.repository;

import java.util.List;

import com.zdsoft.finance.afterloan.entity.AfterDispatch;
import com.zdsoft.finance.common.base.CustomRepository;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AfterDispatchRepository.java 
 * @ClassName: AfterDispatchRepository 
 * @Description: 督办派工
 * @author xj 
 * @date 2017年3月7日 下午1:02:45 
 * @version V1.0
 */
public interface AfterDispatchRepository extends CustomRepository<AfterDispatch, String> {
	
	/**
	 * 
	 * @Title: findByAfterSuperviseId 
	 * @Description: 根据督办id查询派工
	 * @author xj 
	 * @param afterSuperviseId 督办id
	 * @return
	 */
	public List<AfterDispatch> findByAfterSuperviseId(String afterSuperviseId);
	
	/**
	 * 
	 * @Title: findByAfterSuperviseIdAndDispatchCode 
	 * @Description: 通过督办id和派工人员查询
	 * @author xj 
	 * @param afterSuperviseId 督办id
	 * @param dispatchCode 派工人
	 * @return
	 */
	public AfterDispatch findByAfterSuperviseIdAndDispatchCode(String afterSuperviseId,String dispatchCode);
	
	/**
	 * 
	 * @Title: findByAfterSuperviseIdAndDispatchCode 
	 * @Description: 通过督办id和派工人员查询
	 * @author xj 
	 * @param afterSuperviseId 督办id
	 * @param dispatchCode 督办人code
	 * @return
	 */
	public AfterDispatch findByAfterSuperviseIdAndSuperviserCode(String afterSuperviseId,String superviserCode);
}
