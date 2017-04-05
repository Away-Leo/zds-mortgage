package com.zdsoft.finance.common.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.entity.CommonEntity;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;

public interface CommonService extends BaseService<CommonEntity>  {
	/**
     * 公共分页
     *
     * @author huangdk
     * @param pageRequest
     *            分页请求对象
     * @param li
     *            封装后的查询参数集合
     * @return
     * @throws Exception
     */
    Page<Map<String, Object>> GetCaseContractPages(PageRequest pageRequest, List<QueryObj> li) throws Exception;

}
