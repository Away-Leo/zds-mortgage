package com.zdsoft.finance.casemanage.record.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.casemanage.record.entity.CaseApplyRecord;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CaseApplyRecordService.java
 * @Package com.zdsoft.finance.casemanage.record.service
 * @Description: 案件录入信息
 * @author Liyb
 * @date 2017年1月16日 下午4:14:35
 * @version V1.0
 */
public interface CaseApplyRecordService extends BaseService<CaseApplyRecord> {

    /**
     * 案件录入信息
     * 
     * @param params
     * @return
     */
    List<CaseApplyRecord> queryList(Map<String, Object> params);

    /**
     * 分页查询
     * 
     * @param pageable
     * @param condition
     * @return
     */
    Page<CaseApplyRecord> queryList(Pageable pageable, Map<String, Object> condition);

}
