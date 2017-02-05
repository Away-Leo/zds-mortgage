package com.zdsoft.finance.busiform.entity;

import com.zdsoft.framework.core.common.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "zf_busiform")
public class BusiForm extends BaseEntity {

       /**
        *
        */
       private static final long serialVersionUID = -7926164958600739409L;


       /**
        * 业务品种编号
        */
       @Column(length = 32)
       private String productCd;

       /**
        * 业务品种名称
        */
       @Column(length = 255)
       private String productNm;

       /**
        * 状态
        */
       @Column
       private Integer status;

       /**
        * 申请人编号
        */
       @Column(length = 32)
       private String applyEmpCd;

       /**
        * 申请人姓名
        */
       @Column(length = 255)
       private String applyEmpNm;

       /**
        * 申请时间
        */
       @Column
       private Long applyTime;

       /**
        * 完成时间
        */
       @Column
       private Long completeTime;

       /**
        * 废弃时间
        */
       @Column
       private Long terminateTime;

       /**
        * 关联业务表单数据id
        */
       @Column(length = 32)
       private String businessEntityId;

       /**
        * 关联表单域对象类名 例如表单为Project时 此字段存入的值为Project
        */
       @Column(length = 255)
       private String businessEntityNm;

       /**
        * 关联组件数据ID 例如 项目表ID 合同ID
        */
       @Column(length = 32)
       private String componentsEntityId;

       /**
        * 关联组件域对象类名 例如表单为Project时 此字段存入的值为Project
        */
       @Column(length = 255)
       private String componentsEntityNm;

       /**
        * 流程实例key
        */
       @Column(length = 32)
       private String processInstanceKey;

       /**
        * 流程开始时间
        */
       @Column(length = 16)
       private Long processStartTime;

       /**
        * 流程结束时间
        */
       @Column(length = 16)
       private Long processEndTime;

       /**
        * 流程发起人编号
        */
       @Column(length = 36)
       private String processApplayEmpCd;

       /**
        * 流程发起人姓名
        */
       @Column(length = 255)
       private String processApplayEmpNm;
       
       /**
        * 申请单code
        */
       @Column(length = 255)
       private String applayFormCode;
       
       /**
        * 申请单Name
        */
       @Column(length = 255)
       private String applayFormName;

       /**
        * 当前处理人Cd
        */
       private transient String currentDealEmpCd;
       /**
        * 当前处理人Nm
        */
       private transient String currentDealEmpNm;
       /**
        * 流程名字
        */
       private transient String processKey;

       public String getCurrentDealEmpCd() {
              return currentDealEmpCd;
       }

       public void setCurrentDealEmpCd(String currentDealEmpCd) {
              this.currentDealEmpCd = currentDealEmpCd;
       }

       public String getCurrentDealEmpNm() {
              return currentDealEmpNm;
       }

       public void setCurrentDealEmpNm(String currentDealEmpNm) {
              this.currentDealEmpNm = currentDealEmpNm;
       }

       public String getProcessKey() {
              return processKey;
       }

       public void setProcessKey(String processKey) {
              this.processKey = processKey;
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

       public String getBusinessEntityId() {
              return businessEntityId;
       }

       public void setBusinessEntityId(String businessEntityId) {
              this.businessEntityId = businessEntityId;
       }

       public String getBusinessEntityNm() {
              return businessEntityNm;
       }

       public void setBusinessEntityNm(String businessEntityNm) {
              this.businessEntityNm = businessEntityNm;
       }

       public String getComponentsEntityId() {
              return componentsEntityId;
       }

       public void setComponentsEntityId(String componentsEntityId) {
              this.componentsEntityId = componentsEntityId;
       }

       public String getComponentsEntityNm() {
              return componentsEntityNm;
       }

       public void setComponentsEntityNm(String componentsEntityNm) {
              this.componentsEntityNm = componentsEntityNm;
       }

       public Long getProcessStartTime() {
              return processStartTime;
       }

       public void setProcessStartTime(Long processStartTime) {
              this.processStartTime = processStartTime;
       }

       public Long getProcessEndTime() {
              return processEndTime;
       }

       public void setProcessEndTime(Long processEndTime) {
              this.processEndTime = processEndTime;
       }

       public String getProcessApplayEmpCd() {
              return processApplayEmpCd;
       }

       public void setProcessApplayEmpCd(String processApplayEmpCd) {
              this.processApplayEmpCd = processApplayEmpCd;
       }

       public String getProcessApplayEmpNm() {
              return processApplayEmpNm;
       }

       public void setProcessApplayEmpNm(String processApplayEmpNm) {
              this.processApplayEmpNm = processApplayEmpNm;
       }

       public Integer getStatus() {
              return status;
       }

       public void setStatus(Integer status) {
              this.status = status;
       }

       public String getApplyEmpCd() {
              return applyEmpCd;
       }

       public void setApplyEmpCd(String applyEmpCd) {
              this.applyEmpCd = applyEmpCd;
       }

       public String getApplyEmpNm() {
              return applyEmpNm;
       }

       public void setApplyEmpNm(String applyEmpNm) {
              this.applyEmpNm = applyEmpNm;
       }

       public Long getApplyTime() {
              return applyTime;
       }

       public void setApplyTime(Long applyTime) {
              this.applyTime = applyTime;
       }

       public Long getCompleteTime() {
              return completeTime;
       }

       public void setCompleteTime(Long completeTime) {
              this.completeTime = completeTime;
       }

       public Long getTerminateTime() {
              return terminateTime;
       }

       public void setTerminateTime(Long terminateTime) {
              this.terminateTime = terminateTime;
       }

       public String getProcessInstanceKey() {
              return processInstanceKey;
       }

       public void setProcessInstanceKey(String processInstanceKey) {
              this.processInstanceKey = processInstanceKey;
       }

	public String getApplayFormCode() {
		return applayFormCode;
	}

	public void setApplayFormCode(String applayFormCode) {
		this.applayFormCode = applayFormCode;
	}

	public String getApplayFormName() {
		return applayFormName;
	}

	public void setApplayFormName(String applayFormName) {
		this.applayFormName = applayFormName;
	}

}
