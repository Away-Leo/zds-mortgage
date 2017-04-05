package com.zdsoft.finance.afterloan.service;

import java.util.List;

import com.zdsoft.finance.afterloan.entity.AfterDispatch;
import com.zdsoft.finance.base.service.BaseService;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AfterDispatchService.java 
 * @ClassName: AfterDispatchService 
 * @Description: 派工service
 * @author xj 
 * @date 2017年3月7日 下午1:09:21 
 * @version V1.0
 */
public interface AfterDispatchService extends BaseService<AfterDispatch> {
	
	
	/**
	 * 
	 * @Title: saveAfterDispatch 
	 * @Description: 保存派工(派工的时候调用) 用于机构派工初始化派工人员数据（保存督办人基本信息、派工人基本信息）
	 * @author xj 
	 * @param afterSuperviseId 督办人id
	 * @param dispatchCode 派工人code
	 * @return
	 * @throws Exception
	 */
	public AfterDispatch saveAfterDispatch(String afterSuperviseId,String dispatchCode) throws Exception;
	
	/**
	 * 
	 * @Title: updateAfterDispatch 
	 * @Description: 跟新派工 只跟新反馈人员反馈结果
	 * @author xj 
	 * @param afterSuperviseId 督办id
	 * @param feedbackRresults 反馈结果
	 * @return
	 * @throws Exception
	 */
	public AfterDispatch updateAfterDispatch(String afterSuperviseId,String feedbackRresults) throws Exception;
	
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
	 * @Title: findByAfterSuperviseIdAndSuperviserCode 
	 * @Description: 通过督办id和督办人code查询
	 * @author xj 
	 * @param afterSuperviseId 督办id
	 * @param sperviserCode 督办人code
	 * @return
	 */
	public AfterDispatch findByAfterSuperviseIdAndSuperviserCode(String afterSuperviseId,String superviserCode);
}
