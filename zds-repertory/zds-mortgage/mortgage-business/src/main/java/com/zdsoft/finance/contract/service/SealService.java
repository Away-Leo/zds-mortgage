package com.zdsoft.finance.contract.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.casemanage.material.entity.CaseMaterialListAttaLog;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.contract.entity.CoactCaseContract;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;

public interface SealService extends BaseService<CoactCaseContract> {
	/**
     * 合同盖章列表
     *
     * @author huangdk
     * @param pageRequest
     *            分页请求对象
     * @param li
     *            封装后的查询参数集合
     * @return
     * @throws Exception
     */
    Page<Map<String, Object>> findCoactCaseList(PageRequest pageRequest, List<QueryObj> li) throws Exception;

}
