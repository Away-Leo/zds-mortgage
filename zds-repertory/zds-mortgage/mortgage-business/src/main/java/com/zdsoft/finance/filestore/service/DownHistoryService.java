package com.zdsoft.finance.filestore.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.filestore.entity.DownHistory;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

import java.util.List;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title DownHistoryService.java
 * @className DownHistoryService
 * @description 下载历史service
 * @author LiaoGuoWei
 * @create 2017/2/27 11:05
 * @version V1.0
 **/
public interface DownHistoryService extends BaseService<DownHistory> {

    /**
      * @Title: saveDownHis
      * @Description: 保存下载历史
      * @author liaoguowei
      * @param downHistory
      * @return DownHistory
      * @throws Exception
    */
    public DownHistory saveDownHis(DownHistory downHistory) throws Exception;

    /**
      * @Title: saveDownHisByFileStoreIds
      * @Description:  批量或单个保存历史纪录
      * @author liaoguowei
      * @param ids
      * @return List<DownHistory>
      * @throws Exception
    */
    public List<DownHistory> saveDownHisByFileStoreIds(String ids) throws Exception;

    /**
      * @Title: findPageByCondition
      * @Description: 分页查询数据
      * @author liaoguowei
      * @param pageable
      * @param downHistory
      * @return Page<DownHistory>
      * @throws Exception
    */
    public Page<DownHistory> findPageByCondition(Pageable pageable,DownHistory downHistory) throws Exception;

}
