package com.zdsoft.finance.marketing.vo;

import java.math.BigDecimal;
import java.util.List;

import com.zdsoft.finance.casemanage.datasurvey.vo.FeeInfomationVo;
import com.zdsoft.finance.casemanage.datasurvey.vo.RiskInformationVo;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivableInfo;
import com.zdsoft.finance.casemanage.receivablePlan.vo.ReceivableInfoVo;
import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.common.utils.AmountConversionUtil;
import com.zdsoft.finance.common.utils.RateUtil;
import com.zdsoft.finance.common.utils.VoUtil;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CaseApplyVo.java 
 * @ClassName: CaseApplyVo 
 * @Description: 案件Vo
 * @author zhoushichao 
 * @date 2017年3月14日 下午7:48:34 
 * @version V1.0
 */
public class CaseApplyVo extends BaseVo<CaseApply> {

    private static final long serialVersionUID = -2701119792964406508L;
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
     * 申请金额(格式化金额，千位分隔符)
     */
    private String applyAmountString;
    /**
     * 申请时间
     */
    private Long applyDate;

    /**
     * 申请时间 yyyy-MM-dd
     */
    private String applyDateStr;

    /**
     * 申请期限
     */
    private Integer applyTerm;
    /**
     * 申请期限单位
     */
    private String applyTermUnit;
    /**
     * 申请期限单位名称
     */
    private String applyTermUnitName;
    /**
     * 贷款利率
     */
    private BigDecimal applyRate;
    /**
     * 贷款利率单位
     */
    private String applyRateUnit;
    private String applyRateUnitName;
    /**
     * 逾期利率
     */
    private BigDecimal overdueRate;
    /**
     * 逾期利率单位
     */
    private String overdueRateUnit;
    private String overdueRateUnitName;
    /**
     * 综合利率
     */
    private BigDecimal synthesizeRate;
    /**
     * 综合利率单位
     */
    private String synthesizeRateUnit;
    private String synthesizeRateUnitName;
    /**
     * 动态转换利率
     */
    private BigDecimal dynamicRate;
    /**
     * 动态转换利率单位
     */
    private String dynamicRateUnit;
    private String dynamicRateUnitName;
    /**
     * 终端ID
     */
    private String terminalId;
    private String terminalIdName;
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
     * 资金来源name
     */
    private String capitalSourceName;
    /**
     * 贷款用途
     */
    private String capitalUseFor;
    private String capitalUseForName;
    /**
     * 案件来源
     */
    private String caseApplySource;
    /**
     * 备注
     */
    private String mo;

    /**
     * 案件阶段
     * model by liuhuan 修改为String 用SimpleCode维护
     */
    private String stage;
    private String stageName;
    /**
     * 案件是否算尾
     */
    private String interestType;
    /**
     * 案件期限内是否算尾
     */
    private String isTailType;
    /**
     * 还款方式
     */
    private String repayMethod;
    private String repayMethodName;
    /**
     * 每期还款日
     */
    private Integer repaymentDate;
    /**
     * 每期还款日方式
     */
    private String selectFixRepaymentDate;
    /**
     * 案件状态(1.正常（默认），2.退单)
     */
    private String caseApplyStatus;

    /**
     * 评估价抵押成数
     */

    private String assessedPriceMortgage;
    /**
     * 贷款成数
     */
    private String loanNumber;

    /**
     * 案件额度实际申请金额(默认为申请金额applyAmount)
     * 
     * @author xiongpan
     * @date 2017-01-10
     */
    private BigDecimal actualApplyAmount;

    /**
     * 实际申请额度状态(0.未配额度(默认);1.已配额度未配资金;2.已配额度已配资金)
     * 
     * @author xiongpan
     * @date 2017-01-10
     */
    private String actualLimitStatus;
    /**
     * 案件放款金额
     * @author xj
     * @date 2017-03-03
     */
    private BigDecimal loanApplyAnount;
    /**
     * 案件余额
     * 
     * @author xj
     * @date 2017-03-03
     */
    private BigDecimal caseApplyBalance;
    /**
     * 资金计划名称(规则而来)
     * 
     * @author xiongpan
     * @date 2017-01-10
     */
    private String fundPlanName;

    /**
     * 是否已获得备付资金分配(0.否(默认);1.是)
     * 
     * @author xiongpan
     * @date 2017-01-10
     */
    private Boolean isGetLimit = false;

    /**
     * 货款发放账户机构名称
     */
    private String loanOutAccountBranchName;

    /**
     * 货款发放账户账户名
     */
    private String loanOutAccountName;
    /**
     * 货款发放账户
     */
    private String loanOutAccount;

    /**
     * 货款发放账户类型(0.专项账户;1.备付金账户)
     */
    private String isPrepareAccount;

    /**
     * 货款回款账户机构名称
     */
    private String loanBackAccountBranchName;

    /**
     * 货款回款账户账户名
     */
    private String loanBackAccountName;
    /**
     * 货款回款账户
     */
    private String loanBackAccount;

    /**
     * 主借人联系电话
     */
    private String mainBorrowerPhone;

    /**
     * 费用信息
     */
    private List<FeeInfomationVo> feeInfoListVo;

    private String riskInfoId;

    private RiskInformationVo riskInfoVo;


    /**
     * 主借人姓名
     */
    private String customerName;

    /**
     * 抵押类型
     */
    private String pledgeType;

    /**
     * 当前申请人id
     */
    private String currentApplierId;

    /**
     * 当前申请人姓名
     */
    private String currentApplierName;

    /**
     * 是否已获得备付资金分配(0.否,默认;1.是)
     */
    private String isGetPrepareLimit;
    private ReceivableInfoVo receivableInfoVo;
    
    /**
     * 是否是终端进件的案件(0.否(默认);1.是)
     */
    private Integer isTerminalCase;
    
    /**
	 * 合同开始日期
	 */
	private Long contractStartDate;
	/**
	 * 合同结束日期
	 */
	private Long contractEndDate;
    

    public CaseApplyVo() {
    }

    public CaseApplyVo(CaseApply po) {
    	super(po, null, new String[] {"capitalUseFor","applyTermUnit","repayMethod","applyRateUnit","stage","synthesizeRateUnit","overdueRateUnit" });
    	//获取贷款成数 与 评估价抵押成数 百分比 Modify by xj
    	String loanNumber = po.getLoanNumber();
    	String assessedPriceMortgage = po.getAssessedPriceMortgage();
    	if(ObjectHelper.isNotEmpty(loanNumber) && new BigDecimal(loanNumber).compareTo(BigDecimal.ZERO)>0){
    		this.setLoanNumber(new BigDecimal(RateUtil.percentRate(Double.valueOf(loanNumber))).setScale(2, BigDecimal.ROUND_HALF_DOWN).toString());
    	}else{
    		this.setLoanNumber(null);
    	}
    	if(ObjectHelper.isNotEmpty(assessedPriceMortgage) && new BigDecimal(assessedPriceMortgage).compareTo(BigDecimal.ZERO)>0){
    		this.setAssessedPriceMortgage(new BigDecimal(RateUtil.percentRate(Double.valueOf(assessedPriceMortgage))).setScale(2, BigDecimal.ROUND_HALF_DOWN).toString());
    	}else{
    		this.setAssessedPriceMortgage(null);
    	}
    	//贷款利率转换(日利率是‰号)
		if(ObjectHelper.isNotEmpty(this.applyRate)){
			if(ObjectHelper.isNotEmpty(this.applyRateUnit)&&ReceivableInfo.RECEIVABLEINFO_DAY.equals(this.applyRateUnit)){
				this.applyRate = new BigDecimal(RateUtil.percentRate2(applyRate.doubleValue(), true)).setScale(2,BigDecimal.ROUND_HALF_UP);
			}else{
				this.applyRate = new BigDecimal(RateUtil.percentRate(applyRate.doubleValue())).setScale(2,BigDecimal.ROUND_HALF_UP);
			}
		}
		//逾期利率转换(日利率是‰号)
		if(ObjectHelper.isNotEmpty(this.overdueRate)){
			if(ObjectHelper.isNotEmpty(this.overdueRateUnit)&&ReceivableInfo.RECEIVABLEINFO_DAY.equals(this.overdueRateUnit)){
				this.overdueRate = new BigDecimal(RateUtil.percentRate2(overdueRate.doubleValue(), true)).setScale(2,BigDecimal.ROUND_HALF_UP);
			}else{
				this.overdueRate = new BigDecimal(RateUtil.percentRate(overdueRate.doubleValue())).setScale(2,BigDecimal.ROUND_HALF_UP);
			}
		}
	    //综合利率转换(日利率是‰号)
		if(ObjectHelper.isNotEmpty(this.synthesizeRate)){
			if(ObjectHelper.isNotEmpty(this.synthesizeRateUnit)&&ReceivableInfo.RECEIVABLEINFO_DAY.equals(this.synthesizeRateUnit)){
				this.synthesizeRate = new BigDecimal(RateUtil.percentRate2(synthesizeRate.doubleValue(), true)).setScale(2,BigDecimal.ROUND_HALF_UP);
			}else{
				this.synthesizeRate = new BigDecimal(RateUtil.percentRate(synthesizeRate.doubleValue())).setScale(2,BigDecimal.ROUND_HALF_UP);
			}
		}
		//动态转换利率转换(日利率是‰号)
		if(ObjectHelper.isNotEmpty(this.dynamicRate)){
			if(ObjectHelper.isNotEmpty(this.dynamicRateUnit)&&ReceivableInfo.RECEIVABLEINFO_DAY.equals(this.dynamicRateUnit)){
				this.dynamicRate = new BigDecimal(RateUtil.percentRate2(dynamicRate.doubleValue(), true)).setScale(2,BigDecimal.ROUND_HALF_UP);
			}else{
				this.dynamicRate = new BigDecimal(RateUtil.percentRate(dynamicRate.doubleValue())).setScale(2,BigDecimal.ROUND_HALF_UP);
			}
		}
    }

    public CaseApplyVo(CaseApply po, String[] args, String[] simpleArgs) throws Exception {
    	VoUtil.copyPoperties(po, this, false, args, simpleArgs);
    	//获取贷款成数 与 评估价抵押成数 百分比 Modify by xj
    	String loanNumber = po.getLoanNumber();
    	String assessedPriceMortgage = po.getAssessedPriceMortgage();
    	if(ObjectHelper.isNotEmpty(loanNumber) && new BigDecimal(loanNumber).compareTo(BigDecimal.ZERO)>0){
    		this.setLoanNumber(new BigDecimal(RateUtil.percentRate(Double.valueOf(loanNumber))).setScale(2, BigDecimal.ROUND_HALF_DOWN).toString());
    	}
    	if(ObjectHelper.isNotEmpty(assessedPriceMortgage) && new BigDecimal(assessedPriceMortgage).compareTo(BigDecimal.ZERO)>0){
    		this.setAssessedPriceMortgage(new BigDecimal(RateUtil.percentRate(Double.valueOf(assessedPriceMortgage))).setScale(2, BigDecimal.ROUND_HALF_DOWN).toString());
    	}
    	//贷款利率转换(日利率是‰号)
		if(ObjectHelper.isNotEmpty(this.applyRate)){
			if(ObjectHelper.isNotEmpty(this.applyRateUnit)&&ReceivableInfo.RECEIVABLEINFO_DAY.equals(this.applyRateUnit)){
				this.applyRate = new BigDecimal(RateUtil.percentRate2(applyRate.doubleValue(), true)).setScale(2,BigDecimal.ROUND_HALF_UP);
			}else{
				this.applyRate = new BigDecimal(RateUtil.percentRate(applyRate.doubleValue())).setScale(2,BigDecimal.ROUND_HALF_UP);
			}
		}
		//逾期利率转换(日利率是‰号)
		if(ObjectHelper.isNotEmpty(this.overdueRate)){
			if(ObjectHelper.isNotEmpty(this.overdueRateUnit)&&ReceivableInfo.RECEIVABLEINFO_DAY.equals(this.overdueRateUnit)){
				this.overdueRate = new BigDecimal(RateUtil.percentRate2(overdueRate.doubleValue(), true)).setScale(2,BigDecimal.ROUND_HALF_UP);
			}else{
				this.overdueRate = new BigDecimal(RateUtil.percentRate(overdueRate.doubleValue())).setScale(2,BigDecimal.ROUND_HALF_UP);
			}
		}
	    //综合利率转换(日利率是‰号)
		if(ObjectHelper.isNotEmpty(this.synthesizeRate)){
			if(ObjectHelper.isNotEmpty(this.synthesizeRateUnit)&&ReceivableInfo.RECEIVABLEINFO_DAY.equals(this.synthesizeRateUnit)){
				this.synthesizeRate = new BigDecimal(RateUtil.percentRate2(synthesizeRate.doubleValue(), true)).setScale(2,BigDecimal.ROUND_HALF_UP);
			}else{
				this.synthesizeRate = new BigDecimal(RateUtil.percentRate(synthesizeRate.doubleValue())).setScale(2,BigDecimal.ROUND_HALF_UP);
			}
		}
		//动态转换利率转换(日利率是‰号)
		if(ObjectHelper.isNotEmpty(this.dynamicRate)){
			if(ObjectHelper.isNotEmpty(this.dynamicRateUnit)&&ReceivableInfo.RECEIVABLEINFO_DAY.equals(this.dynamicRateUnit)){
				this.dynamicRate = new BigDecimal(RateUtil.percentRate2(dynamicRate.doubleValue(), true)).setScale(2,BigDecimal.ROUND_HALF_UP);
			}else{
				this.dynamicRate = new BigDecimal(RateUtil.percentRate(dynamicRate.doubleValue())).setScale(2,BigDecimal.ROUND_HALF_UP);
			}
		}
    }

    public CaseApply toPO() {
        CaseApply po = new CaseApply();
        po = super.toPo(this, po);

        return po;
    }

    public String getStageName() {
		return stageName;
	}

	public void setStageName(String stageName) {
		this.stageName = stageName;
	}

	public Long getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Long applyDate) {
        this.applyDate = applyDate;
    }

    public String getApplyDateStr() {
        applyDateStr = DateHelper.longToDate(this.applyDate);
        return applyDateStr;
    }

    public void setApplyDateStr(String applyDateStr) {
        this.applyDateStr = applyDateStr;
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

    public Long getContractStartDate() {
		return contractStartDate;
	}

	public void setContractStartDate(Long contractStartDate) {
		this.contractStartDate = contractStartDate;
	}

	public Long getContractEndDate() {
		return contractEndDate;
	}

	public void setContractEndDate(Long contractEndDate) {
		this.contractEndDate = contractEndDate;
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

    public String getFundPlanName() {
        return fundPlanName;
    }

    public void setFundPlanName(String fundPlanName) {
        this.fundPlanName = fundPlanName;
    }

    public String getLoanOutAccountBranchName() {
        return loanOutAccountBranchName;
    }

    public void setLoanOutAccountBranchName(String loanOutAccountBranchName) {
        this.loanOutAccountBranchName = loanOutAccountBranchName;
    }

    public String getLoanOutAccountName() {
        return loanOutAccountName;
    }

    public void setLoanOutAccountName(String loanOutAccountName) {
        this.loanOutAccountName = loanOutAccountName;
    }

    public String getLoanOutAccount() {
        return loanOutAccount;
    }

    public void setLoanOutAccount(String loanOutAccount) {
        this.loanOutAccount = loanOutAccount;
    }

    public String getLoanBackAccountBranchName() {
        return loanBackAccountBranchName;
    }

    public void setLoanBackAccountBranchName(String loanBackAccountBranchName) {
        this.loanBackAccountBranchName = loanBackAccountBranchName;
    }

    public String getLoanBackAccountName() {
        return loanBackAccountName;
    }

    public void setLoanBackAccountName(String loanBackAccountName) {
        this.loanBackAccountName = loanBackAccountName;
    }

    public String loanOutAccountAmount() {
        return loanBackAccount;
    }

    public void setLoanBackAccount(String loanBackAccount) {
        this.loanBackAccount = loanBackAccount;
    }

    public String getMainBorrowerPhone() {
        return mainBorrowerPhone;
    }

    public void setMainBorrowerPhone(String mainBorrowerPhone) {
        this.mainBorrowerPhone = mainBorrowerPhone;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPledgeType() {
        return pledgeType;
    }

    public void setPledgeType(String pledgeType) {
        this.pledgeType = pledgeType;
    }

    public String getIsPrepareAccount() {
        return isPrepareAccount;
    }

    public void setIsPrepareAccount(String isPrepareAccount) {
        this.isPrepareAccount = isPrepareAccount;
    }

    public String getCurrentApplierId() {
        return currentApplierId;
    }

    public void setCurrentApplierId(String currentApplierId) {
        this.currentApplierId = currentApplierId;
    }

    public String getCurrentApplierName() {
        return currentApplierName;
    }

    public void setCurrentApplierName(String currentApplierName) {
        this.currentApplierName = currentApplierName;
    }

    public String getRiskInfoId() {
        return riskInfoId;
    }

    public void setRiskInfoId(String riskInfoId) {
        this.riskInfoId = riskInfoId;
    }

    public String getIsGetPrepareLimit() {
        return isGetPrepareLimit;
    }

    public void setIsGetPrepareLimit(String isGetPrepareLimit) {
        this.isGetPrepareLimit = isGetPrepareLimit;
    }

    public String getLoanBackAccount() {
        return loanBackAccount;
    }

    public List<FeeInfomationVo> getFeeInfoListVo() {
        return feeInfoListVo;
    }

    public void setFeeInfoListVo(List<FeeInfomationVo> feeInfoListVo) {
        this.feeInfoListVo = feeInfoListVo;
    }

    public RiskInformationVo getRiskInfoVo() {
        return riskInfoVo;
    }

    public void setRiskInfoVo(RiskInformationVo riskInfoVo) {
        this.riskInfoVo = riskInfoVo;
    }

    public ReceivableInfoVo getReceivableInfoVo() {
		return receivableInfoVo;
	}

	public void setReceivableInfoVo(ReceivableInfoVo receivableInfoVo) {
		this.receivableInfoVo = receivableInfoVo;
	}

	public String getTerminalIdName() {
        return terminalIdName;
    }

    public void setTerminalIdName(String terminalIdName) {
        this.terminalIdName = terminalIdName;
    }

    public String getCapitalUseForName() {
        return capitalUseForName;
    }

    public void setCapitalUseForName(String capitalUseForName) {
        this.capitalUseForName = capitalUseForName;
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

    public String getApplyTermUnitName() {
        return applyTermUnitName;
    }

    public void setApplyTermUnitName(String applyTermUnitName) {
        this.applyTermUnitName = applyTermUnitName;
    }

    public Boolean getIsGetLimit() {
        return isGetLimit;
    }

    public void setIsGetLimit(Boolean isGetLimit) {
        this.isGetLimit = isGetLimit;
    }

	public String getCapitalSourceName() {
		return capitalSourceName;
	}

	public void setCapitalSourceName(String capitalSourceName) {
		this.capitalSourceName = capitalSourceName;
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

	public String getApplyRateUnitName() {
		return applyRateUnitName;
	}

	public void setApplyRateUnitName(String applyRateUnitName) {
		this.applyRateUnitName = applyRateUnitName;
	}

	public String getOverdueRateUnitName() {
		return overdueRateUnitName;
	}

	public void setOverdueRateUnitName(String overdueRateUnitName) {
		this.overdueRateUnitName = overdueRateUnitName;
	}

	public String getSynthesizeRateUnitName() {
		return synthesizeRateUnitName;
	}

	public void setSynthesizeRateUnitName(String synthesizeRateUnitName) {
		this.synthesizeRateUnitName = synthesizeRateUnitName;
	}

	public String getDynamicRateUnitName() {
		return dynamicRateUnitName;
	}

	public void setDynamicRateUnitName(String dynamicRateUnitName) {
		this.dynamicRateUnitName = dynamicRateUnitName;
	}

	public String getRepayMethodName() {
		return repayMethodName;
	}

	public void setRepayMethodName(String repayMethodName) {
		this.repayMethodName = repayMethodName;
	}

	public Integer getIsTerminalCase() {
		return isTerminalCase;
	}

	public void setIsTerminalCase(Integer isTerminalCase) {
		this.isTerminalCase = isTerminalCase;
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

	public String getApplyAmountString() {
		this.applyAmountString = AmountConversionUtil.convertFormatAmount(this.applyAmount);
		return applyAmountString;
	}

	public void setApplyAmountString(String applyAmountString) {
		this.applyAmountString = applyAmountString;
	}
}
