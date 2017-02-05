package com.zdsoft.finance.casemanage.casetracking.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.casemanage.casetracking.entity.ManualNodeRecord;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:CaseTrackingService.java
 * @Package:com.zdsoft.finance.casemanage.casetracking.service
 * @Description:案件跟踪表Service
 * @author: caixiekang
 * @date:2017年1月13日 下午10:34:02
 * @version:v1.0
 */
public interface CaseTrackingService extends BaseService<ManualNodeRecord>{
    
    /**
     * 
     * 查找案件跟踪列表
     *
     * @author caixiekang
     * @param pageable  
     * @param queryObjs 页面传回来的查询条件
     * @return
     */
    Page<Map<String, Object>> queryAllCaseTracking(Pageable pageable, List<QueryObj> queryObjs);

}
