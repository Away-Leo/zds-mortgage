package com.zdsoft.finance.casemanage.material.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.casemanage.material.entity.CaseMaterialListAttaLog;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:CaseMaterialListAttaLogService.java
 * @Package:com.zdsoft.finance.casemanage.material.service
 * @Description:案件资料清单附件日志服务接口
 * @author: gonght
 * @date:2017年1月15日 下午2:52:05
 * @version:v1.0
 */
public interface CaseMaterialListAttaLogService extends BaseService<CaseMaterialListAttaLog> {

    /**
     * 案件资料清单（附件）下载日志数据分页查询
     *
     * @author gonght
     * @param pageRequest
     *            分页请求对象
     * @param li
     *            封装后的查询参数集合
     * @return
     * @throws Exception
     */
    Page<Map<String, Object>> findCaseMaterialAttaLogList(PageRequest pageRequest, List<QueryObj> li) throws Exception;
}
