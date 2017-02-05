package com.zdsoft.finance.common.base;

import java.math.BigDecimal;

/**
 * 项目申请一级页面VO
 * @author LiaoGuoWei
 * @create 2016-11-17 19:06
 **/
public class ProjectEvaluateVo {

      /**
       * 项目申请ID
       */
      private String commonApplyId;
      /**
       * 项目表ID
       */
      private String projectId;

      /**
       * 授余额
       */
      private BigDecimal clientCreditBalance;
      /**
       * 客户编号
       */
      private String clientCd;

      /**
       * 客户名称
       */
      private String clientNm;
      /**
       * 项目案号
       */
      private String projectCd;
      /**
       *项目名称
       */
      private String projectNm;


      /**
       * 申请金额
       */
      private BigDecimal amount;

      /**
       * 申请期限
       */
      private Integer applyDeadline;

      /**
       * 申请期限单位
       */
      private String applyDeadlineUnit;

      /**
       * 申请费率
       */
      private BigDecimal applyRate;

      /**
       * 申请费率单位
       */
      private String applyRateUnit;


      /**
       * 产品编号
       */
      private String productCd;
      /**
       * 产品名称
       */
      private String productNm;

      /**
       * 对应busiform表ID
       */
      private String busiFormId;

      /**
       * 资金用途描述
       */
      private String capitalUseFor;

      /**
       * 拟保证措施
       */
      private String quasiGuarMeasures;

      /**
       * 项目来源
       */
      private String projectSource;

      /**
       * 项目概述
       */
      private String projectDesc;

      /**
       * 备注
       */
      private String remark;


      /**
       * 具体申请类型(类名)
       */
      private String specificType;

      /**
       * 具体申请ID
       */
      private String specificId;

      /**
       *申请时间
       */
      private Long applyDateTime;

      /**
       * 受理人编号
       */
      private String acceptEmpCd;
      /**
       * 受理人姓名
       */
      private String acceptEmpNm;

      /**
       * 授信ID
       */
      private String creditId;

      /**
       * 中介机构编号
       */
      private String intemediaryOrgCd;

      /**
       * 调查报告ID（attachmentId）
       */
      private String surveyAttId;

      /**
       * 是否为提交
       */
      private  Boolean isSubmit;

      /**
       * 申请时间
       */
      private String applyDateTimeStr;

      public String getApplyDateTimeStr() {
            return applyDateTimeStr;
      }

      public void setApplyDateTimeStr(String applyDateTimeStr) {
            this.applyDateTimeStr = applyDateTimeStr;
      }

      public boolean isSubmit() {
            return isSubmit;
      }

      public void setIsSubmit(boolean isSubmit) {
            this.isSubmit = isSubmit;
      }

      public String getCommonApplyId() {
            return commonApplyId;
      }

      public void setCommonApplyId(String commonApplyId) {
            this.commonApplyId = commonApplyId;
      }

      public String getProjectId() {
            return projectId;
      }

      public void setProjectId(String projectId) {
            this.projectId = projectId;
      }

      public BigDecimal getClientCreditBalance() {
            return clientCreditBalance;
      }

      public void setClientCreditBalance(BigDecimal clientCreditBalance) {
            this.clientCreditBalance = clientCreditBalance;
      }

      public String getClientCd() {
            return clientCd;
      }

      public void setClientCd(String clientCd) {
            this.clientCd = clientCd;
      }

      public String getClientNm() {
            return clientNm;
      }

      public void setClientNm(String clientNm) {
            this.clientNm = clientNm;
      }

      public String getProjectCd() {
            return projectCd;
      }

      public void setProjectCd(String projectCd) {
            this.projectCd = projectCd;
      }

      public String getProjectNm() {
            return projectNm;
      }

      public void setProjectNm(String projectNm) {
            this.projectNm = projectNm;
      }

      public BigDecimal getAmount() {
            return amount;
      }

      public void setAmount(BigDecimal amount) {
            this.amount = amount;
      }

      public Integer getApplyDeadline() {
            return applyDeadline;
      }

      public void setApplyDeadline(Integer applyDeadline) {
            this.applyDeadline = applyDeadline;
      }

      public String getApplyDeadlineUnit() {
            return applyDeadlineUnit;
      }

      public void setApplyDeadlineUnit(String applyDeadlineUnit) {
            this.applyDeadlineUnit = applyDeadlineUnit;
      }

      public BigDecimal getApplyRate() {
            return applyRate;
      }

      public void setApplyRate(BigDecimal applyRate) {
            this.applyRate = applyRate;
      }

      public String getApplyRateUnit() {
            return applyRateUnit;
      }

      public void setApplyRateUnit(String applyRateUnit) {
            this.applyRateUnit = applyRateUnit;
      }

      public String getProductCd() {
            return productCd;
      }

      public void setProductCd(String productCd) {
            this.productCd = productCd;
      }

      public String getProductNm() {
            return productNm;
      }

      public void setProductNm(String productNm) {
            this.productNm = productNm;
      }

      public String getBusiFormId() {
            return busiFormId;
      }

      public void setBusiFormId(String busiFormId) {
            this.busiFormId = busiFormId;
      }

      public String getCapitalUseFor() {
            return capitalUseFor;
      }

      public void setCapitalUseFor(String capitalUseFor) {
            this.capitalUseFor = capitalUseFor;
      }

      public String getQuasiGuarMeasures() {
            return quasiGuarMeasures;
      }

      public void setQuasiGuarMeasures(String quasiGuarMeasures) {
            this.quasiGuarMeasures = quasiGuarMeasures;
      }

      public String getProjectSource() {
            return projectSource;
      }

      public void setProjectSource(String projectSource) {
            this.projectSource = projectSource;
      }

      public String getProjectDesc() {
            return projectDesc;
      }

      public void setProjectDesc(String projectDesc) {
            this.projectDesc = projectDesc;
      }

      public String getRemark() {
            return remark;
      }

      public void setRemark(String remark) {
            this.remark = remark;
      }

      public String getSpecificType() {
            return specificType;
      }

      public void setSpecificType(String specificType) {
            this.specificType = specificType;
      }

      public String getSpecificId() {
            return specificId;
      }

      public void setSpecificId(String specificId) {
            this.specificId = specificId;
      }

      public Long getApplyDateTime() {
            return applyDateTime;
      }

      public void setApplyDateTime(Long applyDateTime) {
            this.applyDateTime = applyDateTime;
      }

      public String getAcceptEmpCd() {
            return acceptEmpCd;
      }

      public void setAcceptEmpCd(String acceptEmpCd) {
            this.acceptEmpCd = acceptEmpCd;
      }

      public String getAcceptEmpNm() {
            return acceptEmpNm;
      }

      public void setAcceptEmpNm(String acceptEmpNm) {
            this.acceptEmpNm = acceptEmpNm;
      }

      public String getCreditId() {
            return creditId;
      }

      public void setCreditId(String creditId) {
            this.creditId = creditId;
      }

      public String getIntemediaryOrgCd() {
            return intemediaryOrgCd;
      }

      public void setIntemediaryOrgCd(String intemediaryOrgCd) {
            this.intemediaryOrgCd = intemediaryOrgCd;
      }

      public String getSurveyAttId() {
            return surveyAttId;
      }

      public void setSurveyAttId(String surveyAttId) {
            this.surveyAttId = surveyAttId;
      }

}
