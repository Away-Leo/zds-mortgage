
package com.zdsoft.finance.finance.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.finance.entity.ReviewOfArchives;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ReviewOfArchivesService.java 
 * @ClassName: ReviewOfArchivesService 
 * @Description: 财务复核-档案信息复核 接口定义
 * @author dengyy 
 * @date 2017年2月20日 下午1:54:07 
 * @version V1.0 
 */
public interface ReviewOfArchivesService extends BaseService<ReviewOfArchives> {

    /**
     * 
     * @Title: findByCaseApplyId 
     * @Description: 获取案件的档案复核信息
     * @author dengyy 
     * @param caseApplyId 案件id
     * @return
     * @throws BusinessException
     */
    public ReviewOfArchives findByCaseApplyId(String caseApplyId) throws BusinessException;
}
