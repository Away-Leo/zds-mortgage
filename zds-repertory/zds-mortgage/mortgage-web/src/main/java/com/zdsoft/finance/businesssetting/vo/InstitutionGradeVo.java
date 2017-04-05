package com.zdsoft.finance.businesssetting.vo;

import com.zdsoft.finance.businesssetting.entity.InstitutionGrade;
import com.zdsoft.finance.common.base.BaseVo;
import org.springframework.beans.BeanUtils;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @title InstitutionGradeVo.java
 * @className InstitutionGradeVo
 * @description 机构评级VO
 * @author LiaoGuoWei
 * @create 2017/3/3 15:23
 * @version V1.0
 **/
public class InstitutionGradeVo extends BaseVo<InstitutionGrade> {

    /**
     * 机构编号
     */
    private String institutionCode;

    /**
     * 机构名称
     */
    private String institutionName;

    /**
     * 终评编号
     */
    private String finalGradeCode;

    /**
     * 终评名称
     */
    private String finalGradeName;

    /**
     * 机构授权等级
     */
    private AuthGradeVo authGradeVo;

    /**
     * 操作人编号
     */
    private String handerCode;

    /**
     * 操作人姓名
     */
    private String handerName;

    /**
     * 操作时间
     */
    private String handelTime;

    /**
     * 原评级编号
     */
    private String originalGradeCode;

    /**
     * 原平级名称
     */
    private String originalGradeName;

    /**
     * 冗余字段-授权等级ID
     */
    private String authGradeId;

    public String getInstitutionCd() {
        return institutionCode;
    }

    public void setInstitutionCd(String institutionCode) {
        this.institutionCode = institutionCode;
    }

    public String getInstitutionCode() {
        return institutionCode;
    }

    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getFinalGradeCode() {
        return finalGradeCode;
    }

    public void setFinalGradeCode(String finalGradeCode) {
        this.finalGradeCode = finalGradeCode;
    }

    public String getFinalGradeName() {
        return finalGradeName;
    }

    public void setFinalGradeName(String finalGradeName) {
        this.finalGradeName = finalGradeName;
    }

    public String getHanderCode() {
        return handerCode;
    }

    public void setHanderCode(String handerCode) {
        this.handerCode = handerCode;
    }

    public String getHanderName() {
        return handerName;
    }

    public void setHanderName(String handerName) {
        this.handerName = handerName;
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

    public String getAuthGradeId() {
        return authGradeId;
    }

    public void setAuthGradeId(String authGradeId) {
        this.authGradeId = authGradeId;
    }

    public AuthGradeVo getAuthGradeVo() {
        return authGradeVo;
    }

    public void setAuthGradeVo(AuthGradeVo authGradeVo) {
        this.authGradeVo = authGradeVo;
    }

    public InstitutionGrade toPo(){
        InstitutionGrade institutionGrade=new InstitutionGrade();
        BeanUtils.copyProperties(this,institutionGrade);
        return institutionGrade;
    }
}
