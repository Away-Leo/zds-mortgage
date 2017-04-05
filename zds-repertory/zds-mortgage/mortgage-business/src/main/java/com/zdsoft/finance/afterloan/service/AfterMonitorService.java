package com.zdsoft.finance.afterloan.service;

import com.zdsoft.finance.afterloan.entity.AfterMonitor;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;

import java.util.List;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title AfterMonitorService.java
 * @className AfterMonitorService
 * @description 贷后监控service
 * @author LiaoGuoWei
 * @create 2017/3/6 21:20
 * @version V1.0
 **/
public interface AfterMonitorService {

    /**
     * @Title: findMonitorByCondition
     * @Description: 按照条件查找
     * @author liaoguowei
     * @param page 分页参数
     * @param after 查询条件
     * @return com.zdsoft.framework.core.common.page.Page<com.zdsoft.finance.afterloan.entity.AfterMonitor>
     * @throws
     */
    public Page<AfterMonitor> findMonitorByCondition(PageRequest page,AfterMonitor after) throws Exception;

    /**
     * @Title: findMonitorInitiActiveList
     * @Description: 主动查询列表
     * @author liaoguowei
     * @param afterMonitor 查询条件
     * @return java.util.List<com.zdsoft.finance.afterloan.entity.AfterMonitor>
     * @throws BusinessException
     */
    public List<AfterMonitor> findMonitorInitiActiveList(AfterMonitor afterMonitor) throws Exception;

    /**
     * @Title: doInitiActive
     * @Description: 贷后监控主动查询
     * @author liaoguowei
     * @param ids 案件ID
     * @param impls 接口类型
     * @return boolean
     * @throws
     */
    public void doInitiActive(String ids,String impls) throws Exception;



}
