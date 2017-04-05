package com.zdsoft.finance.finance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ReviewOfArchives.java 
 * @ClassName: ReviewOfArchives 
 * @Description: 财务复核-档案信息复核
 * @author dengyy 
 * @date 2017年2月20日 上午11:13:57 
 * @version V1.0 
 */
@Entity
@Table(name="case_review_of_archives")
public class ReviewOfArchives extends BaseEntity {
    
    private static final long serialVersionUID = 7706628776344749778L;

    /**
     * 案件id
     */
    @Column(length=32)
    private String   caseApplyId ;
   
    /**
     * 是否收齐资料
     */
    @Column(length=20)
    private String isAllCollectInformation ;
    
    /**
     * 备注 
     */
    @Column(length=512)
    private String remark;

    public String getCaseApplyId() {
        return caseApplyId;
    }

    public void setCaseApplyId(String caseApplyId) {
        this.caseApplyId = caseApplyId;
    }

    public String getIsAllCollectInformation() {
        return isAllCollectInformation;
    }

    public void setIsAllCollectInformation(String isAllCollectInformation) {
        this.isAllCollectInformation = isAllCollectInformation;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
