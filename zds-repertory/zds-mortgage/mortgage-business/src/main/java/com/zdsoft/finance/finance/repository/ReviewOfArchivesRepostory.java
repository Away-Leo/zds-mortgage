
package com.zdsoft.finance.finance.repository;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.finance.entity.ReviewOfArchives;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ReviewOfArchivesRepostory.java 
 * @ClassName: ReviewOfArchivesRepostory 
 * @Description: 财务复核-档案信息复核 数据库操作仓库
 * @author dengyy 
 * @date 2017年2月20日 下午1:52:22 
 * @version V1.0 
 */
public interface ReviewOfArchivesRepostory extends CustomRepository<ReviewOfArchives, String>{

    /** 
     * @Title: findByCaseApplyId 
     * @Description: 获取案件的档案复核信息
     * @author dengyy 
     * @param caseApplyId 案件id
     * @return  
     */ 
    public ReviewOfArchives findByCaseApplyId(String caseApplyId);

}
