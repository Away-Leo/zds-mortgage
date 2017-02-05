package com.zdsoft.finance.casemanage.dealpledge.notarize.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.casemanage.dealpledge.notarize.entity.Notarize;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:NotarizeService.java
 * @Package:com.zdsoft.finance.casemanage.dealpledge.notarize.service
 * @Description:NotarizeService接口
 * @author: caixiekang
 * @date:2017年1月13日 上午11:46:24
 * @version:v1.0
 */

public interface NotarizeService extends BaseService<Notarize>{

//	/**
//	 * 保存
//	 * @param notarize
//	 * @return
//	 */
//	public Notarize saveOrUpdateNotarize(Notarize notarize) throws BusinessException;
//	

    /**
     * 
     * 根据cassApplyId查找对应的公证信息
     *
     * @author caixiekang
     * @param pageable
     * @param caseApplyId
     * @return
     * @throws BusinessException
     */
    public Page<Notarize> queryNotarizeBycaseApplyId(Pageable pageable, String caseApplyId) throws BusinessException;
}
