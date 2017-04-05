package com.zdsoft.finance.marketing.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: BeforehandApply.java 
 * @ClassName: BeforehandApply 
 * @Description: 案件预申请实体类
 * @author zhoushichao 
 * @date 2017年3月14日 下午5:41:34 
 * @version V1.0
 */
@Entity
@Table(name = "mkt_beforehand_apply")
public class BeforehandApply extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**
     * 正常
     */
    public static final String NORMAL = "01";
    /**
     * 退单
     */
    public static final String BACK = "02";

	/**
     * 案件Id
     */
    @Column(length = 32)
    private String caseApplyId;
    /**
	 * 产品父类Id
	 */
	@Column(length = 32)
	private String productTypeId;
	/**
	 * 产品父类名称
	 */
	@Column(length = 32)
	private String productTypeName;
	/**
	 * 产品子类Id
	 */
	@Column(length = 32)
	private String productSubtypeId;
	/**
	 * 产品子类名称
	 */
	@Column(length = 32)
	private String productSubtypeName;
	/**
     * 申请金额
     */
    @Column(precision = 18, scale = 2)
    private BigDecimal applyAmount = BigDecimal.ZERO;
    /**
     * 申请时间
     */
    @Column
    private Long applyDate;
    /**
     * 申请期限
     */
    @Column
    private Integer applyTerm;
    /**
     * 申请期限单位
     */
    @Column(length = 20)
    private String applyTermUnit;
    /**
     * 贷款利率
     */
    @Column(precision = 18, scale = 6)
    private BigDecimal applyRate = BigDecimal.ZERO;
    /**
     * 逾期利率
     */
    @Column(precision = 18, scale = 6)
    private BigDecimal overdueRate = BigDecimal.ZERO;
    /**
	 * 案件阶段
	 */
	@Column
	private Integer stage;
    /**
     * 终端ID
     */
    @Column(length = 32)
    private String terminalId;
    /**
	 * 拓展经理代码
	 */
	@Column(length = 32)
	private String developmentManagerCode;
	/**
	 * 拓展经理名称
	 */
	@Column(length = 128)
	private String developmentManagerName;
	/**
	 * 拓展部门代码
	 */
	@Column(length = 32)
	private String developmentDepartmentCode;
	/**
	 * 拓展部门名称
	 */
	@Column(length = 128)
	private String developmentDepartmentName;
	/**
	 * 公司代码
	 */
	@Column(length = 128)
	private String mechanismCode;
	/**
	 * 公司名称
	 */
	@Column(length = 128)
	private String mechanismName;
    /**
     * 风险经理
     */
    @Column(length = 128)
    private String riskManager;
    /**
     * 资信员
     */
    @Column(length = 128)
    private String creditMember;
    /**
     * 评估价抵押成数
     */
    @Column(length=32)
    private String assessedPriceMortgage;
    /**
     * 贷款成数
     */
    @Column(length=32)
    private String loanNumber;
    /**
     * 资金来源
     */
    @Column(length = 32)
    private String capitalSource;
    /**
     * 贷款用途
     */
    @Column(length = 20)
    private String capitalUseFor;
    /**
     * 案件来源
     */
    @Column(length = 32)
    private String caseApplySource;
    /**
     * 备注
     */
    @Column(length = 3000)
    private String mo;
    
    /**
     * 案件状态(01.正常，02.退单)
     */
    @Column(length = 20)
    private String caseApplyStatus;
    
    /**
     * 是否是终端进件的案件(0.否(默认);1.是)
     */
    @Column
    private Integer isTerminalCase;
    
	public String getCaseApplyId() {
		return caseApplyId;
	}
	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}
	public String getProductTypeId() {
		return productTypeId;
	}
	public void setProductTypeId(String productTypeId) {
		this.productTypeId = productTypeId;
	}
	public String getProductTypeName() {
		return productTypeName;
	}
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	public String getProductSubtypeId() {
		return productSubtypeId;
	}
	public void setProductSubtypeId(String productSubtypeId) {
		this.productSubtypeId = productSubtypeId;
	}
	public String getProductSubtypeName() {
		return productSubtypeName;
	}
	public void setProductSubtypeName(String productSubtypeName) {
		this.productSubtypeName = productSubtypeName;
	}
	public BigDecimal getApplyAmount() {
		return applyAmount;
	}
	public void setApplyAmount(BigDecimal applyAmount) {
		this.applyAmount = applyAmount;
	}
	public Long getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Long applyDate) {
		this.applyDate = applyDate;
	}
	
	public Integer getApplyTerm() {
        return applyTerm;
    }
    public void setApplyTerm(Integer applyTerm) {
        this.applyTerm = applyTerm;
    }
    public String getApplyTermUnit() {
        return applyTermUnit;
    }
    public void setApplyTermUnit(String applyTermUnit) {
        this.applyTermUnit = applyTermUnit;
    }
    public BigDecimal getApplyRate() {
		return applyRate;
	}
	public void setApplyRate(BigDecimal applyRate) {
		this.applyRate = applyRate;
	}
	public Integer getStage() {
		return stage;
	}
	public void setStage(Integer stage) {
		this.stage = stage;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public String getDevelopmentManagerCode() {
		return developmentManagerCode;
	}
	public void setDevelopmentManagerCode(String developmentManagerCode) {
		this.developmentManagerCode = developmentManagerCode;
	}
	public String getDevelopmentManagerName() {
		return developmentManagerName;
	}
	public void setDevelopmentManagerName(String developmentManagerName) {
		this.developmentManagerName = developmentManagerName;
	}
	public String getDevelopmentDepartmentCode() {
		return developmentDepartmentCode;
	}
	public void setDevelopmentDepartmentCode(String developmentDepartmentCode) {
		this.developmentDepartmentCode = developmentDepartmentCode;
	}
	public String getDevelopmentDepartmentName() {
		return developmentDepartmentName;
	}
	public void setDevelopmentDepartmentName(String developmentDepartmentName) {
		this.developmentDepartmentName = developmentDepartmentName;
	}
	public String getMechanismCode() {
		return mechanismCode;
	}
	public void setMechanismCode(String mechanismCode) {
		this.mechanismCode = mechanismCode;
	}
	public String getMechanismName() {
		return mechanismName;
	}
	public void setMechanismName(String mechanismName) {
		this.mechanismName = mechanismName;
	}
	public String getRiskManager() {
		return riskManager;
	}
	public void setRiskManager(String riskManager) {
		this.riskManager = riskManager;
	}
	public String getCreditMember() {
		return creditMember;
	}
	public void setCreditMember(String creditMember) {
		this.creditMember = creditMember;
	}
	public String getCapitalSource() {
		return capitalSource;
	}
	public void setCapitalSource(String capitalSource) {
		this.capitalSource = capitalSource;
	}
	public String getCapitalUseFor() {
		return capitalUseFor;
	}
	public void setCapitalUseFor(String capitalUseFor) {
		this.capitalUseFor = capitalUseFor;
	}
	public String getCaseApplySource() {
		return caseApplySource;
	}
	public void setCaseApplySource(String caseApplySource) {
		this.caseApplySource = caseApplySource;
	}
	public String getMo() {
		return mo;
	}
	public void setMo(String mo) {
		this.mo = mo;
	}
	public BigDecimal getOverdueRate() {
		return overdueRate;
	}
	public void setOverdueRate(BigDecimal overdueRate) {
		this.overdueRate = overdueRate;
	}
	public String getAssessedPriceMortgage() {
		return assessedPriceMortgage;
	}
	public void setAssessedPriceMortgage(String assessedPriceMortgage) {
		this.assessedPriceMortgage = assessedPriceMortgage;
	}
	public String getLoanNumber() {
		return loanNumber;
	}
	public void setLoanNumber(String loanNumber) {
		this.loanNumber = loanNumber;
	}
	public String getCaseApplyStatus() {
		return caseApplyStatus;
	}
	public void setCaseApplyStatus(String caseApplyStatus) {
		this.caseApplyStatus = caseApplyStatus;
	}
	public Integer getIsTerminalCase() {
		return isTerminalCase;
	}
	public void setIsTerminalCase(Integer isTerminalCase) {
		this.isTerminalCase = isTerminalCase;
	}
}
