package com.zdsoft.finance.marketing.vo;

import java.math.BigDecimal;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.marketing.entity.BeforehandApply;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: BeforehandApplyVo.java 
 * @ClassName: BeforehandApplyVo 
 * @Description: 案件预申请VO
 * @author zhoushichao 
 * @date 2017年3月14日 下午7:48:13 
 * @version V1.0
 */
public class BeforehandApplyVo extends BaseVo<BeforehandApply>{

	private static final long serialVersionUID = 1L;

	/**
     * 案件Id
     */
    private String caseApplyId;
	/**
     * 案件号
     */
    private String caseApplyCode;
    /**
	 * 产品父类Id
	 */
	private String productTypeId;
	/**
	 * 产品父类名称
	 */
	private String productTypeName;
	/**
	 * 产品子类Id
	 */
	private String productSubtypeId;
	/**
	 * 产品子类名称
	 */
	private String productSubtypeName;
	/**
     * 申请金额
     */
    private BigDecimal applyAmount;
    /**
     * 申请时间
     */
    private Long applyDate;
    /**
     * 申请期限
     */
    private Integer applyTerm;
    /**
     * 申请期限单位
     */
    private String applyTermUnit;
    /**
     * 申请费率
     */
    private BigDecimal applyRate;
    /**
	 * 案件阶段（CaseApplyStageEnum.value）
	 */
	private Integer stage;
    /**
     * 终端ID
     */
    private String terminalId;
    /**
     * 终端名称
     */
    private String terminalFullName;
    /**
	 * 拓展经理代码
	 */
	private String developmentManagerCode;
	/**
	 * 拓展经理名称
	 */
	private String developmentManagerName;
	/**
	 * 拓展部门代码
	 */
	private String developmentDepartmentCode;
	/**
	 * 拓展部门名称
	 */
	private String developmentDepartmentName;
	/**
	 * 公司代码
	 */
	private String mechanismCode;
	/**
	 * 公司名称
	 */
	private String mechanismName;
	/**
	 * 风险经理
	 */
	private String riskManager;
    /**
     * 资信员
     */
    private String creditMember;
    /**
     * 资金来源
     */
    private String capitalSource;
    /**
     * 贷款用途
     */
    private String capitalUseFor;
    /**
     * 贷款用途名称
     */
    private String capitalUseForName;
    /**
     * 案件来源
     */
    private String caseApplySource;
    /**
     * 评估价抵押成数
     */
    private String assessedPriceMortgage;
    /**
     * 贷款成数
     */
    private String loanNumber;
    /**
     * 备注
     */
    private String mo;
    
    /**
     * 是否是终端进件的案件(0.否(默认);1.是)
     */
    private Integer isTerminalCase;
    
	public String getCaseApplyId() {
		return caseApplyId;
	}
	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}
	public String getCaseApplyCode() {
		return caseApplyCode;
	}
	public void setCaseApplyCode(String caseApplyCode) {
		this.caseApplyCode = caseApplyCode;
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
	public BeforehandApplyVo() {
		super();
	}
	public BeforehandApplyVo(BeforehandApply po){
		super(po);
	}
	
	public String getCapitalUseForName() {
		return capitalUseForName;
	}
	public void setCapitalUseForName(String capitalUseForName) {
		this.capitalUseForName = capitalUseForName;
	}
	
	public String getTerminalFullName() {
		return terminalFullName;
	}
	public void setTerminalFullName(String terminalFullName) {
		this.terminalFullName = terminalFullName;
	}
	public BeforehandApply toPO(){
		BeforehandApply po = new BeforehandApply();
		return super.toPo(this, po);
	}
	public Integer getIsTerminalCase() {
		return isTerminalCase;
	}
	public void setIsTerminalCase(Integer isTerminalCase) {
		this.isTerminalCase = isTerminalCase;
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
	
	
}
