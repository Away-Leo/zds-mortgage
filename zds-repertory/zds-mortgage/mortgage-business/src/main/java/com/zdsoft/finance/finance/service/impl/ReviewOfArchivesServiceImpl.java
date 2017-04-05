
package com.zdsoft.finance.finance.service.impl;

import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.finance.entity.ReviewOfArchives;
import com.zdsoft.finance.finance.repository.ReviewOfArchivesRepostory;
import com.zdsoft.finance.finance.service.ReviewOfArchivesService;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: ReviewOfArchivesServiceImpl.java
 * @ClassName: ReviewOfArchivesServiceImpl
 * @Description:  财务复核-档案信息复核 接口实现
 * @author dengyy
 * @date 2017年2月20日 下午1:54:56
 * @version V1.0
 */
@Service("reviewOfArchivesService")
public class ReviewOfArchivesServiceImpl extends BaseServiceImpl<ReviewOfArchives, ReviewOfArchivesRepostory> implements ReviewOfArchivesService {

    @Override
    public ReviewOfArchives findByCaseApplyId(String caseApplyId) throws BusinessException {
        if(ObjectHelper.isEmpty(caseApplyId)){
            throw new BusinessException("10010004", "案件id不能为空！");
        }
        return this.customReposity.findByCaseApplyId(caseApplyId);
    }

}
