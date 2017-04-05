package com.zdsoft.finance.afterloan.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.afterloan.entity.AfterMonitorRecord;
import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AfterMonitorRecordService.java 
 * @ClassName: AfterMonitorRecordService 
 * @Description: 贷中贷后监控service
 * @author xj 
 * @date 2017年2月14日 上午11:31:29 
 * @version V1.0
 */
public interface AfterMonitorRecordService extends BaseService<AfterMonitorRecord> {
	
	/**
	 * 
	 * @Title: findPageMonitorRecord 
	 * @Description: 查询监控记录
	 * @author xj 
	 * @param pageable
	 * @param queryObjs
	 * @param controlType  贷前还是贷后监控
	 * @return
	 * @throws Exception
	 */
	public Page<Map<String, Object>> findPageMonitorRecord(PageRequest pageable, List<QueryObj> queryObjs, String controlType) throws Exception;
	
	/**
	 * 
	 * @Title: saveOrsubmitConnectLand 
	 * @Description: 保存或者提交待发送清单
	 * @author xj 
	 * @param caseApplyIds 需要发送的案件id数组
	 * @throws Exception 
	 */
	public void saveOrsubmitConnectLand(String[] caseApplyIds) throws Exception;
	
	/**
	 * 
	 * @Title: findMonitorRecordByCaseApplyId 
	 * @Description: 通过案件id查询监控记录
	 * @author liuwei
	 * @param caseApplyId 案件id
	 * @param controlType 监控记录
	 * @return 监控记录信息
	 */
	public Map<String, Object> findMonitorRecordByCaseApplyId(String caseApplyId, String controlType);
}
