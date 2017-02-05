package com.zdsoft.finance.authgrade.vo;

import com.zdsoft.finance.authgrade.entity.OrgAuthConn;
import com.zdsoft.finance.common.base.BaseVo;

/**
 * 关系表VO
 * @author LiaoGuoWei
 * @create 2017-01-22 21:41
 **/
public class OrgAuthConnVo extends BaseVo<OrgAuthConn> {

    /**
     * 授权等级code
     */
    private String authGradeCode;
    /**
     * 授权等级name
     */
    private String authGradeName;

    /**
     * 机构表ID
     */
    private String orgIntermediateId;
    /**
     * 机构名称
     */
    private String orgIntermediateName;

    /**
     * 操作人编号
     */
    private String handelerCode;

    /**
     * 操作人姓名
     */
    private String handelerName;

    /**
     * 操作时间
     */
    private String handelTime;

    /**
     * 原评级编号
     */
    private String originalGradeCode;

    /**
     * 原评级名称
     */
    private String originalGradeName;

    public String getAuthGradeCode() {
        return authGradeCode;
    }

    public void setAuthGradeCode(String authGradeCode) {
        this.authGradeCode = authGradeCode;
    }

    public String getAuthGradeName() {
        return authGradeName;
    }

    public void setAuthGradeName(String authGradeName) {
        this.authGradeName = authGradeName;
    }

    public String getOrgIntermediateId() {
        return orgIntermediateId;
    }

    public void setOrgIntermediateId(String orgIntermediateId) {
        this.orgIntermediateId = orgIntermediateId;
    }

    public String getOrgIntermediateName() {
        return orgIntermediateName;
    }

    public void setOrgIntermediateName(String orgIntermediateName) {
        this.orgIntermediateName = orgIntermediateName;
    }

    public String getHandelerCode() {
        return handelerCode;
    }

    public void setHandelerCode(String handelerCode) {
        this.handelerCode = handelerCode;
    }

    public String getHandelerName() {
        return handelerName;
    }

    public void setHandelerName(String handelerName) {
        this.handelerName = handelerName;
    }

    public String getHandelTime() {
        return handelTime;
    }

    public void setHandelTime(String handelTime) {
        this.handelTime = handelTime;
    }

    public String getOriginalGradeCode() {
        return originalGradeCode;
    }

    public void setOriginalGradeCode(String originalGradeCode) {
        this.originalGradeCode = originalGradeCode;
    }

    public String getOriginalGradeName() {
        return originalGradeName;
    }

    public void setOriginalGradeName(String originalGradeName) {
        this.originalGradeName = originalGradeName;
    }

    public OrgAuthConn toPo(){
        OrgAuthConn orgAuthConn=new OrgAuthConn();
        org.springframework.beans.BeanUtils.copyProperties(this,orgAuthConn);
        return orgAuthConn;
    }
}
