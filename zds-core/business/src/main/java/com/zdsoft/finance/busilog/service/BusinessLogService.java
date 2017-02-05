package com.zdsoft.finance.busilog.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.busilog.entity.BusinessLog;

/**
 * 业务日志记录
 * 
 * @author liuwei
 *
 */
public interface BusinessLogService extends BaseService<BusinessLog> {

	/**
	 * 记录业务日志
	 * 
	 * @param businessLog
	 *            业务日志
	 * @return 业务日志
	 */
	public BusinessLog recordBusinessLog(BusinessLog businessLog);

	/**
	 * 记录业务日志
	 * 
	 * @param operatorNm
	 *            操作人
	 * @param moduleNm
	 *            模块名(枚举类ModuleNm)
	 * @param actionNm
	 *            动作名(枚举类ActionNm)
	 * @param description
	 *            描述
	 * @return 业务日志
	 */
	public BusinessLog recordBusinessLog(String operatorNm, String moduleNm, String actionNm, String description);

}
