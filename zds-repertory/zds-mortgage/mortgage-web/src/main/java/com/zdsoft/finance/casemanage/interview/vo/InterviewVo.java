package com.zdsoft.finance.casemanage.interview.vo;

import com.zdsoft.finance.casemanage.interview.entity.Interview;
import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: InterviewVo.java 
 * @ClassName: InterviewVo 
 * @Description: 案件面签信息Vo
 * @author dengyy 
 * @date 2017年2月17日 下午3:09:25 
 * @version V1.0
 */
public class InterviewVo extends BaseVo<Interview>{

	private static final long serialVersionUID = 1345713770935842839L;
    /**
     * 案件id
     */
    private String caseApplyId ;
    
    /**
     * 预计强制执行公证办理时间 
     */
    private String compulsoryNotaryDate ;
    
    /**
     * 是否仲裁 
     */
    private Boolean isArbitrate ;
    
    /**
     * 第三方还款授权
     */
    private Boolean isThirdPartyRepayAccredit ;
    
    /**
     * 第三方还款授权人id 保前的客户id
     */
    private String thirdPartyRepayAccreditId ;
    
    /**
     * 第三方还款授权人姓名 
     */
    private String thirdPartyRepayAccreditName ;
    
    /**
     * 流程基础信息
     */
    private String busiFormId ;
    
    /**
     * 面签状态(0.未面签(默认);1.已面签)
     */
    private String interviewStatus;
    
    /**
     * 面签状态名称(0.未面签(默认);1.已面签)
     */
    private String interviewStatusName;
    
    /**
     * 面签时间
     */
    private Long actualInterviewDate ;
    
    /**
     * 案件房产信息 数据 json
     */
    private String houseData ;
    
    /**
     * 是否启动流程
     */
    private Boolean isSubmit ;
    
    /**
     * 下一处理人
     */
    private String currentDealEmpNm ;

    public String getCaseApplyId() {
        return caseApplyId;
    }

    public void setCaseApplyId(String caseApplyId) {
        this.caseApplyId = caseApplyId;
    }

    public String getCompulsoryNotaryDate() {
        return compulsoryNotaryDate;
    }

    public void setCompulsoryNotaryDate(String compulsoryNotaryDate) {
        this.compulsoryNotaryDate = compulsoryNotaryDate;
    }

    public Boolean getIsArbitrate() {
        return isArbitrate;
    }

    public void setIsArbitrate(Boolean isArbitrate) {
        this.isArbitrate = isArbitrate;
    }

    public Boolean getIsThirdPartyRepayAccredit() {
        return isThirdPartyRepayAccredit;
    }

    public void setIsThirdPartyRepayAccredit(Boolean isThirdPartyRepayAccredit) {
        this.isThirdPartyRepayAccredit = isThirdPartyRepayAccredit;
    }

    public String getThirdPartyRepayAccreditName() {
        return thirdPartyRepayAccreditName;
    }

    public void setThirdPartyRepayAccreditName(String thirdPartyRepayAccreditName) {
        this.thirdPartyRepayAccreditName = thirdPartyRepayAccreditName;
    }

    public String getBusiFormId() {
        return busiFormId;
    }

    public void setBusiFormId(String busiFormId) {
        this.busiFormId = busiFormId;
    }

    public String getInterviewStatus() {
        return interviewStatus;
    }

    public void setInterviewStatus(String interviewStatus) {
        this.interviewStatus = interviewStatus;
    }
    
    public String getInterviewStatusName() {
        return interviewStatusName;
    }

    public void setInterviewStatusName(String interviewStatusName) {
        this.interviewStatusName = interviewStatusName;
    }
    
    public String getHouseData() {
        return houseData;
    }

    public void setHouseData(String houseData) {
        this.houseData = houseData;
    }

    public Boolean getIsSubmit() {
        return isSubmit;
    }

    public void setIsSubmit(Boolean isSubmit) {
        this.isSubmit = isSubmit;
    }
    
    public InterviewVo(){
        super();
    }
    
    public InterviewVo(Interview entity){
        super(entity, null, new String[]{"interviewStatus"}) ;
        if(ObjectHelper.isNotEmpty(entity.getBusiForm())){
            this.setBusiFormId(entity.getBusiForm().getId());
        }
    }
    
    public Interview toPo(){
        Interview entity = new Interview();
        super.toPo(this, entity);
        return entity ;
    }

    public Long getActualInterviewDate() {
        return actualInterviewDate;
    }

    public void setActualInterviewDate(Long actualInterviewDate) {
        this.actualInterviewDate = actualInterviewDate;
    }

    public String getCurrentDealEmpNm() {
        return currentDealEmpNm;
    }

    public void setCurrentDealEmpNm(String currentDealEmpNm) {
        this.currentDealEmpNm = currentDealEmpNm;
    }

    public String getThirdPartyRepayAccreditId() {
        return thirdPartyRepayAccreditId;
    }

    public void setThirdPartyRepayAccreditId(String thirdPartyRepayAccreditId) {
        this.thirdPartyRepayAccreditId = thirdPartyRepayAccreditId;
    }

}
