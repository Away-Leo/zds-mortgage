/** 
* 版权所有：重庆正大华日软件有限公司
* @Title: ReviewOfArchivesVo.java 
* @Package com.zdsoft.finance.finance.vo 
* @Description: TODO
* @author dengyy 
* @date 2017年2月20日 下午2:00:29 
* @version V1.0 
*/ 
package com.zdsoft.finance.finance.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.finance.entity.ReviewOfArchives;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ReviewOfArchivesVo.java 
 * @ClassName: ReviewOfArchivesVo 
 * @Description: 财务复核-档案信息复核 vo
 * @author dengyy 
 * @date 2017年2月20日 下午2:00:29 
 * @version V1.0 
 */
public class ReviewOfArchivesVo extends BaseVo<ReviewOfArchives> {

    private static final long serialVersionUID = 1L;

    /**
     * 案件id
     */
    private String   caseApplyId ;
   
    /**
     * 是否收齐资料
     */
    private String isAllCollectInformation ;
    
    /**
     * 备注 
     */
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
    
    public ReviewOfArchivesVo(){
        super();
    }
    
    public ReviewOfArchivesVo(ReviewOfArchives entity){
        super(entity);
    }
    
    public ReviewOfArchives toPo(){
        ReviewOfArchives entity = new ReviewOfArchives() ;
        return super.toPo(this, entity);
    }
}
