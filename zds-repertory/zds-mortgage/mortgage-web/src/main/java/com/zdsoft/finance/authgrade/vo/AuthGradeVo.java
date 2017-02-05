package com.zdsoft.finance.authgrade.vo;

import com.zdsoft.finance.authgrade.entity.AuthGrade;
import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

/**
 * 授权等级设定列表VO
 * @author LiaoGuoWei
 * @create 2017-01-04 17:04
 **/
public class AuthGradeVo extends BaseVo<AuthGrade> {

    /**
     * 授权等级编号
     */
    private String gradeCode;
    /**
     * 授权等级名称
     */
    private String gradeName;

    /**
     * 产品大类ID
     */
    private String productParentId;

    /**
     * 产品大类名称
     */
    private String productParentName;

    /**
     * 子产品ID
     */
    private String productChildId;

    /**
     * 子产品名称
     */
    private String productChildName;

    /**
     * 额度
     */
    private BigDecimal authAmount;

    /**
     * 创建人名称
     */
    private String createByName;

    /**
     * 更新人（操作人）名称
     */
    private String updateByName;

    /**
     * 机构评级ID
     */
    private transient String institutionGradeId;

    /**
     * 机构编号
     */
    private transient String institutionCode;
    /**
     * 机构名称
     */
    private transient String institutionName;

    /**
     * 终评编号
     */
    private transient String insGradeCode;
    /**
     * 终评名称
     */
    private transient String insGradeName;

    /**
     * 原评级编号
     */
    private transient String originalGradeCode;

    /**
     * 原平级名称
     */
    private transient String originalGradeName;

    /**
     * 操作人编号
     */
    private transient String handerCode;

    /**
     * 操作人姓名
     */
    private transient String handerName;

    /**
     * 终评编号
     */
    private transient String finalGradeCode;

    /**
     * 终评名称
     */
    private transient String finalGradeName;

    /**
     * 操作时间
     */
    private transient String handelTime;

    /**
     * 操作字符串类型时间
     */
    private String updateTimeStr;


    public String getUpdateTimeStr() {
        return updateTimeStr;
    }

    public void setUpdateTimeStr(String updateTimeStr) {
        this.updateTimeStr = updateTimeStr;
    }


    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getProductParentId() {
        return productParentId;
    }

    public void setProductParentId(String productParentId) {
        this.productParentId = productParentId;
    }

    public String getProductParentName() {
        return productParentName;
    }

    public void setProductParentName(String productParentName) {
        this.productParentName = productParentName;
    }

    public String getProductChildId() {
        return productChildId;
    }

    public void setProductChildId(String productChildId) {
        this.productChildId = productChildId;
    }

    public String getProductChildName() {
        return productChildName;
    }

    public void setProductChildName(String productChildName) {
        this.productChildName = productChildName;
    }

    public BigDecimal getAuthAmount() {
        return authAmount;
    }

    public void setAuthAmount(BigDecimal authAmount) {
        this.authAmount = authAmount;
    }

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    public String getUpdateByName() {
        return updateByName;
    }

    public void setUpdateByName(String updateByName) {
        this.updateByName = updateByName;
    }

    public String getInstitutionGradeId() {
        return institutionGradeId;
    }

    public void setInstitutionGradeId(String institutionGradeId) {
        this.institutionGradeId = institutionGradeId;
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

    public String getInsGradeCode() {
        return insGradeCode;
    }

    public void setInsGradeCode(String insGradeCode) {
        this.insGradeCode = insGradeCode;
    }

    public String getInsGradeName() {
        return insGradeName;
    }

    public void setInsGradeName(String insGradeName) {
        this.insGradeName = insGradeName;
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

    public String getHandelTime() {
        return handelTime;
    }

    public void setHandelTime(String handelTime) {
        this.handelTime = handelTime;
    }

    public AuthGradeVo(){};


    public AuthGradeVo(AuthGrade authGrade){
        BeanUtils.copyProperties(authGrade,this,new String[]{"updateTime"});
        if(ObjectHelper.isNotEmpty(authGrade.getUpdateTime())){
           this.setUpdateTimeStr(DateHelper.dateToString(authGrade.getUpdateTime(), DateHelper.DATE_WITHMINUTE_FORMAT));
        }
    };

    public AuthGrade toPo(){
        AuthGrade authGrade=new AuthGrade();
        BeanUtils.copyProperties(this,authGrade);
        return authGrade;
    }
}
