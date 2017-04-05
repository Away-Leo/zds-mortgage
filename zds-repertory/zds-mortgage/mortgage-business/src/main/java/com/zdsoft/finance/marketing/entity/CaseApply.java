package com.zdsoft.finance.marketing.entity;

import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.finance.casemanage.booking.entity.Booking;
import com.zdsoft.finance.casemanage.datasurvey.entity.FeeInfomation;
import com.zdsoft.finance.casemanage.datasurvey.entity.RiskInfomation;
import com.zdsoft.finance.casemanage.interview.entity.Interview;
import com.zdsoft.finance.casemanage.receivablePlan.entity.BankAccount;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivableInfo;
import com.zdsoft.framework.core.common.domain.BaseEntity;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CaseApply.java 
 * @ClassName: CaseApply 
 * @Description: 案件实体类
 * @author zhoushichao 
 * @date 2017年3月14日 下午5:42:47 
 * @version V1.0
 */
@Entity
@Table(name = "mkt_case_apply")
public class CaseApply extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 项目申请流程类型代码
     */
    public static final String PROCESS_KEY_CODE = "YWDM0012601";
    /**
     * 正常
     */   
    public static final String NORMAL = "01";

    /**
     * 退单
     */   
    public static final String BACK = "02";
    
    /**
     * 否
     */
    public static final Integer NOTERMINALCASE = new Integer(0);
    
    /**
     * 是
     */
    public static final Integer YESTERMINALCASE = new Integer(1);
  
    /**
     * 一审(初审)
     */
    public static final String FIRST_INSTANCE = "100";
    /**
     * 一审通过
     */
    public static final String FIRST_INSTANCE_THROUGH = "101";
    /**
     * 二审
     */
    public static final String SECOND_INSTANCE = "102";
    
    /**
     * 案件状态：资调
     */
    public static final String CASE_STATUS_DATASURVEY = "YWDM009206";
    
    /**
     * simplecode 年
     */
    public static final String DATEUNIT_YEAR = "0931001";
    /**
     * simplecode 月
     */
    public static final String DATEUNIT_MONTH = "0931002";
    /**
     * simplecode 日
     */
    public static final String DATEUNIT_DAY = "0931003";
    
    
    /**
     * 案件号
     */
    @Column(length = 32)
    private String caseApplyCode;
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
    private BigDecimal applyAmount=BigDecimal.ZERO;
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
    private BigDecimal applyRate=BigDecimal.ZERO;
    /**
     * 贷款利率单位
     */
    @Column(length = 32)
    private String applyRateUnit;
    /**
     * 逾期利率
     */
    @Column(precision = 18, scale = 6)
    private BigDecimal overdueRate=BigDecimal.ZERO;
    /**
     * 逾期利率单位
     */
    @Column(length = 32)
    private String overdueRateUnit;
    /**
     * 综合利率
     */
    @Column(precision = 18, scale = 6)
    private BigDecimal synthesizeRate=BigDecimal.ZERO;
    /**
     * 综合利率单位
     */
    @Column(length = 32)
    private String synthesizeRateUnit;
    /**
     * 动态转换利率
     */
    @Column(precision = 18, scale = 6)
    private BigDecimal dynamicRate=BigDecimal.ZERO;
    /**
     * 动态转换利率单位
     */
    @Column(length = 32)
    private String dynamicRateUnit;
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
    @Column(length = 32)
    private String assessedPriceMortgage;
    /**
     * 贷款成数
     */
    @Column(length = 32)
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
     * 案件阶段
     * model by liuhuan 修改为String 用SimpleCode维护
     */
    @Column(length=20)
    private String stage;
    /**
     * 案件是否算尾
     */
    @Column(length = 32)
    private String interestType;
    /**
     * 案件期限内是否算尾
     */
    @Column(length = 32)
    private String isTailType;
    /**
     * 还款方式
     */
    @Column(length = 20)
    private String repayMethod;
    /**
     * 每期还款日
     */
    @Column(length = 2)
    private Integer repaymentDate;
    
    /**
     * 每期还款日方式
     */
    @Column(length = 32)
    private String selectFixRepaymentDate;
    /**
     * 案件状态(01.正常，02.退单)
     */
    @Column(length = 20)
    private String caseApplyStatus;
    /**
     * 案件审批状态(100.一审‘初审’，101.一审通过，102.二审)
     */
    @Column(length = 20)
    private String examinationStatus;
    /**
     * 风险信息
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "riskInfoId", nullable = true, unique = true)
    private RiskInfomation riskInfo;

    @OneToMany(mappedBy = "caseApply", fetch = FetchType.LAZY)
    private List<FeeInfomation> feeInfoList;

    /**
     * 案件额度实际申请金额(默认为申请金额applyAmount)
     * 
     * @author xiongpan
     * @date 2017-01-10
     */
    @Column(precision = 18, scale = 2)
    private BigDecimal actualApplyAmount=BigDecimal.ZERO;

    /**
     * 实际申请额度状态(0.未配额度(默认);1.已配额度未配资金;2.已配额度已配资金)
     * 
     * @author xiongpan
     * @date 2017-01-10
     */
    @Column(length = 20)
    private String actualLimitStatus = "YWDM0051056";
    
    /**
     * 案件放款金额
     * 
     * @author jingjy
     * @date 2017-02-18
     */
    @Column(precision = 18, scale = 6)
    private BigDecimal loanApplyAnount=BigDecimal.ZERO;
    
    /**
     * 案件余额
     * 
     * @author jingjy
     * @date 2017-02-18
     */
    @Column(precision = 18, scale = 6)
    private BigDecimal caseApplyBalance=BigDecimal.ZERO;
    

    @OneToOne
    @JoinColumn(name = "receivableInfoId")
    @LazyCollection(LazyCollectionOption.TRUE)
    private ReceivableInfo receivableInfo;

    /**
     * 案件预约信息
     */
    @OneToOne
    @JoinColumn(name = "bookingId")
    @LazyCollection(LazyCollectionOption.TRUE)
    private Booking booking;

    /**
     * 预约状态(0.未预约(默认);1.已预约)
     */
    @Column(length = 20)
    private String bookingType = "YWDM0051061";

    /**
     * 案件银行卡信息
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "caseApplyId")
    private List<BankAccount> caseBankAccount = new ArrayList<BankAccount>();
    /**
     * 业务表单id
     */
    @OneToOne
    @JoinColumn(name = "busiFromId")
    private BusiForm busiForm;
    
    /**
     * 对应的面签
     */
    @OneToOne
    @JoinColumn(name = "interviewId")
    @LazyCollection(LazyCollectionOption.TRUE)
    private Interview interview;
    
    /**
     * 面签状态(0.未面签(默认);1.已面签)
     */
    @Column(length = 20)
    private String interviewStatus = "YWDM0051077";
    
    /**
     * 是否是终端进件的案件(0.否(默认);1.是)
     */
    @Column
    private Integer isTerminalCase;
    
    /**
     * 案件结算状态 0已结算 null未操作
     */
    @Column(length = 20)
    private String settlementStatus;

    /**
     * 是否出险
     */
    @Column(length = 20)
    private String isLoss;


    public String getIsLoss() {
        return isLoss;
    }

    public void setIsLoss(String isLoss) {
        this.isLoss = isLoss;
    }

    public String getSettlementStatus() {
		return settlementStatus;
	}

	public void setSettlementStatus(String settlementStatus) {
		this.settlementStatus = settlementStatus;
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

    public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getInterestType() {
        return interestType;
    }

    public void setInterestType(String interestType) {
        this.interestType = interestType;
    }

    public String getIsTailType() {
        return isTailType;
    }

    public void setIsTailType(String isTailType) {
        this.isTailType = isTailType;
    }

    public String getRepayMethod() {
        return repayMethod;
    }

    public void setRepayMethod(String repayMethod) {
        this.repayMethod = repayMethod;
    }

    public Integer getRepaymentDate() {
        return repaymentDate;
    }

    public void setRepaymentDate(Integer repaymentDate) {
        this.repaymentDate = repaymentDate;
    }

    public String getSelectFixRepaymentDate() {
        return selectFixRepaymentDate;
    }

    public void setSelectFixRepaymentDate(String selectFixRepaymentDate) {
        this.selectFixRepaymentDate = selectFixRepaymentDate;
    }

    public BigDecimal getOverdueRate() {
        return overdueRate;
    }

    public void setOverdueRate(BigDecimal overdueRate) {
        this.overdueRate = overdueRate;
    }

    public String getCaseApplyStatus() {
        return caseApplyStatus;
    }

    public void setCaseApplyStatus(String caseApplyStatus) {
        this.caseApplyStatus = caseApplyStatus;
    }

    public RiskInfomation getRiskInfo() {
        return riskInfo;
    }

    public void setRiskInfo(RiskInfomation riskInfo) {
        this.riskInfo = riskInfo;
    }

    public ReceivableInfo getReceivableInfo() {
        return receivableInfo;
    }

    public void setReceivableInfo(ReceivableInfo receivableInfo) {
        this.receivableInfo = receivableInfo;
    }

    public List<BankAccount> getCaseBankAccount() {
        return caseBankAccount;
    }

    public void setCaseBankAccount(List<BankAccount> caseBankAccount) {
        this.caseBankAccount = caseBankAccount;
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

    public String getExaminationStatus() {
        return examinationStatus;
    }

    public void setExaminationStatus(String examinationStatus) {
        this.examinationStatus = examinationStatus;
    }

    public List<FeeInfomation> getFeeInfoList() {
        return feeInfoList;
    }

    public void setFeeInfoList(List<FeeInfomation> feeInfoList) {
        this.feeInfoList = feeInfoList;
    }

    public BigDecimal getActualApplyAmount() {
        return actualApplyAmount;
    }

    public void setActualApplyAmount(BigDecimal actualApplyAmount) {
        this.actualApplyAmount = actualApplyAmount;
    }

    public String getActualLimitStatus() {
        return actualLimitStatus;
    }

    public void setActualLimitStatus(String actualLimitStatus) {
        this.actualLimitStatus = actualLimitStatus;
    }

    public static String getProcessKeyCode() {
        return PROCESS_KEY_CODE;
    }


    public BusiForm getBusiForm() {
        return busiForm;
    }

    public void setBusiForm(BusiForm busiForm) {
        this.busiForm = busiForm;
    }

	public Interview getInterview() {
		return interview;
	}

	public void setInterview(Interview interview) {
		this.interview = interview;
	}

	public String getInterviewStatus() {
		return interviewStatus;
	}

	public void setInterviewStatus(String interviewStatus) {
		this.interviewStatus = interviewStatus;
	}

	public String getApplyRateUnit() {
		return applyRateUnit;
	}

	public void setApplyRateUnit(String applyRateUnit) {
		this.applyRateUnit = applyRateUnit;
	}

	public String getOverdueRateUnit() {
		return overdueRateUnit;
	}

	public void setOverdueRateUnit(String overdueRateUnit) {
		this.overdueRateUnit = overdueRateUnit;
	}

	public BigDecimal getSynthesizeRate() {
		return synthesizeRate;
	}

	public void setSynthesizeRate(BigDecimal synthesizeRate) {
		this.synthesizeRate = synthesizeRate;
	}

	public String getSynthesizeRateUnit() {
		return synthesizeRateUnit;
	}

	public void setSynthesizeRateUnit(String synthesizeRateUnit) {
		this.synthesizeRateUnit = synthesizeRateUnit;
	}

	public BigDecimal getDynamicRate() {
		return dynamicRate;
	}

	public void setDynamicRate(BigDecimal dynamicRate) {
		this.dynamicRate = dynamicRate;
	}

	public String getDynamicRateUnit() {
		return dynamicRateUnit;
	}

	public void setDynamicRateUnit(String dynamicRateUnit) {
		this.dynamicRateUnit = dynamicRateUnit;
	}

	public Integer getIsTerminalCase() {
		return isTerminalCase;
	}

	public void setIsTerminalCase(Integer isTerminalCase) {
		this.isTerminalCase = isTerminalCase;
	}

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public String getBookingType() {
        return bookingType;
    }

    public void setBookingType(String bookingType) {
        this.bookingType = bookingType;
    }

	public BigDecimal getLoanApplyAnount() {
		return loanApplyAnount;
	}

	public void setLoanApplyAnount(BigDecimal loanApplyAnount) {
		this.loanApplyAnount = loanApplyAnount;
	}

	public BigDecimal getCaseApplyBalance() {
		return caseApplyBalance;
	}

	public void setCaseApplyBalance(BigDecimal caseApplyBalance) {
		this.caseApplyBalance = caseApplyBalance;
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
