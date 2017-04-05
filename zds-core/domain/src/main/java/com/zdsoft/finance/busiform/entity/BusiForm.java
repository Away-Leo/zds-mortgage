package com.zdsoft.finance.busiform.entity;

import com.zdsoft.framework.core.common.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: BusiForm.java
 * @ClassName: BusiForm
 * @Description: 业务信息基础表
 * @author dengyy
 * @date 2017年2月13日 下午5:41:23
 * @version V1.0
 */
@Entity
@Table(name = "busi_form")
public class BusiForm extends BaseEntity {

    private static final long serialVersionUID = -7926164958600739409L;
    
    /**
	 * 流程中存储别名
	 */
	public transient static final String PROCESS_STORE_KEY = "busiFormId";
    
    /**
     * 业务品种编号
     */
    @Column(length = 32)
    private String productCode;

    /**
     * 业务品种名称
     */
    @Column(length = 64)
    private String productName;

    /**
     * 发起人编号
     */
    @Column(length = 32)
    private String launchEmpCode;

    /**
     * 发起人姓名
     */
    @Column(length = 64)
    private String launchEmpName;

    /**
     * 关联业务表单数据id
     */
    @Column(length = 32)
    private String businessEntityId;

    /**
     * 关联表单域对象类名
     */
    @Column(length = 64)
    private String businessEntityName;

    /**
     * 关联组件数据ID
     */
    @Column(length = 32)
    private String componentsEntityId;

    /**
     * 关联组件域对象类名
     */
    @Column(length = 64)
    private String componentsEntityName;

    /**
     * 流程实例key
     */
    @Column(length = 32)
    private String processInstanceKey;

    /**
     * 流程开始时间
     */
    @Column(length = 16)
    private Long processStartDate;

    /**
     * 流程结束时间
     */
    @Column(length = 16)
    private Long processEndDate;

    /**
     * 申请时间
     */
    @Column(length = 16)
    private Long applyDate;

    /**
     * 申请表状态(非业务)
     */
    @Column(length = 2)
    private Integer formStatus;

    /**
     * 标题(各申请表单自行生成)
     */
    @Column(length = 236)
    private String applyTitle;

    /**
     * 模块类型(枚举类：ApplyModelTypeEnum中value)
     */
    @Column(length = 32)
    private String modelType;

    /**
     * 业务编号(各业务表单的编号)
     */
    @Column(length = 32)
    private String businessCode;
    
    /**
     * 是否被规则拒绝过
     */
    @Column
    @org.hibernate.annotations.Type(type="true_false")
    private Boolean hadRulesRefuse = false;
    
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
    
    /**
     * 产品Id
     */
    private transient String productId;
    
    /**
	 * 流程功能代码
	 */
	private transient String functionCode;

    public String getLaunchEmpCode() {
        return launchEmpCode;
    }

    public void setLaunchEmpCode(String launchEmpCode) {
        this.launchEmpCode = launchEmpCode;
    }

    public String getLaunchEmpName() {
        return launchEmpName;
    }

    public void setLaunchEmpName(String launchEmpName) {
        this.launchEmpName = launchEmpName;
    }

    public String getBusinessEntityId() {
        return businessEntityId;
    }

    public void setBusinessEntityId(String businessEntityId) {
        this.businessEntityId = businessEntityId;
    }

    public String getBusinessEntityName() {
        return businessEntityName;
    }

    public void setBusinessEntityName(String businessEntityName) {
        this.businessEntityName = businessEntityName;
    }

    public String getComponentsEntityId() {
        return componentsEntityId;
    }

    public void setComponentsEntityId(String componentsEntityId) {
        this.componentsEntityId = componentsEntityId;
    }

    public String getComponentsEntityName() {
        return componentsEntityName;
    }

    public void setComponentsEntityName(String componentsEntityName) {
        this.componentsEntityName = componentsEntityName;
    }

    public String getProcessInstanceKey() {
        return processInstanceKey;
    }

    public void setProcessInstanceKey(String processInstanceKey) {
        this.processInstanceKey = processInstanceKey;
    }

    public Long getProcessStartDate() {
        return processStartDate;
    }

    public void setProcessStartDate(Long processStartDate) {
        this.processStartDate = processStartDate;
    }

    public Long getProcessEndDate() {
        return processEndDate;
    }

    public void setProcessEndDate(Long processEndDate) {
        this.processEndDate = processEndDate;
    }

    public Long getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Long applyDate) {
        this.applyDate = applyDate;
    }

    public Integer getFormStatus() {
        return formStatus;
    }

    public void setFormStatus(Integer formStatus) {
        this.formStatus = formStatus;
    }

    public String getApplyTitle() {
        return applyTitle;
    }

    public void setApplyTitle(String applyTitle) {
        this.applyTitle = applyTitle;
    }

    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

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

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

	public Boolean getHadRulesRefuse() {
		return hadRulesRefuse;
	}

	public void setHadRulesRefuse(Boolean hadRulesRefuse) {
		this.hadRulesRefuse = hadRulesRefuse;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getFunctionCode() {
		return functionCode;
	}

	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}
	
}
