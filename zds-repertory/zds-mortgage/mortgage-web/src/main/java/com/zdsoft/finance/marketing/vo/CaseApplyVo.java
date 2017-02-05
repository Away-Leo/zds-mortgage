package com.zdsoft.finance.marketing.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.zdsoft.finance.casemanage.datasurvey.vo.FeeInfomationVo;
import com.zdsoft.finance.casemanage.datasurvey.vo.RiskInformationVo;
import com.zdsoft.finance.casemanage.receivablePlan.entity.BankAccount;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivableInfo;
import com.zdsoft.finance.casemanage.receivablePlan.vo.ReceivableInfoVo;
import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.common.utils.VoUtil;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.framework.core.common.util.DateHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:CaseApplyVo.java
 * @Package:com.zdsoft.finance.marketing.vo
 * @Description:案件Vo
 * @author: zhoushichao
 * @date:2017年1月13日 下午10:15:02
 * @version:v1.0
 */
public class CaseApplyVo extends BaseVo<CaseApply> {

    /**
     * 
     */
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
    private Integer applyDeadline;
    /**
     * 申请期限单位
     */
    private String applyDeadlineUnit;
    /**
     * 申请期限单位名称
     */
    private String applyDeadlineUnitName;
    /**
     * 贷款利率
     */
    private BigDecimal applyRate;
    /**
     * 逾期利率
     */
    private BigDecimal overdueRate;
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
     * 机构代码
     */
    private String mechanismCode;
    /**
     * 机构名称
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
     * 案件阶段（CaseApplyStageEnum.value）
     */
    private Integer stage;
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
     * 案件是否已预约(0.否(默认);1.是)
     * 
     * @author xiongpan
     * @date 2017-01-14
     */
    private ReceivableInfo receivableInfo;

    /**
     * 案件银行卡信息
     */
    private List<BankAccount> caseBankAccount = new ArrayList<BankAccount>();

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
     * 案件是否已预约(0.否(默认);1.是)
     */
    // private boolean isAppointment;

    /**
     * 主借人联系电话
     */
    private String mainBorrowerPhone;

    /* private List<FeeInfomationVo> feeInfoList; */

    /**
     * 费用信息
     */
    private List<FeeInfomationVo> feeInfoListVo;

    private String riskInfoId;

    private RiskInformationVo riskInfoVo;

    /**
     * 还款计划信息
     */

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
    private ReceivableInfoVo receivevableInfoVo;

    public CaseApplyVo() {
    }

    public CaseApplyVo(CaseApply po) {
        super(po, null, new String[] {"capitalUseFor" });

        // RiskInfomation riskInfomation=po.getRiskInfo();
        // if(ObjectHelper.isNotEmpty(riskInfomation)){
        // this.setRiskInfoId(riskInfomation.getId());
        // }

        // if( "1".equals(po.getActualLimitStatus())){
        // this.setActualLimitStatus("已分配额度未分配资金");
        // }else if("2".equals(po.getActualLimitStatus())){
        // this.setActualLimitStatus("已分配额度已分配资金");
        // }else{
        // this.setActualLimitStatus("未分配额度");
        // }

    }

    public CaseApplyVo(CaseApply po, String[] args, String[] simpleArgs) throws Exception {
        VoUtil.copyPoperties(po, this, false, args, simpleArgs);
    }

    public CaseApply toPO() {
        CaseApply po = new CaseApply();
        po = super.toPo(this, po);

        return po;
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

    public Integer getApplyDeadline() {
        return applyDeadline;
    }

    // public String getRiskInfoId() {
    // return riskInfoId;
    // }
    //
    // public void setRiskInfoId(String riskInfoId) {
    // this.riskInfoId = riskInfoId;
    // }

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

    public Integer getStage() {
        return stage;
    }

    public void setStage(Integer stage) {
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

    public ReceivableInfoVo getReceivevableInfoVo() {
        return receivevableInfoVo;
    }

    public void setReceivevableInfoVo(ReceivableInfoVo receivevableInfoVo) {
        this.receivevableInfoVo = receivevableInfoVo;
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

    public String getApplyDeadlineUnitName() {
        return applyDeadlineUnitName;
    }

    public void setApplyDeadlineUnitName(String applyDeadlineUnitName) {
        this.applyDeadlineUnitName = applyDeadlineUnitName;
    }

    public Boolean getIsGetLimit() {
        return isGetLimit;
    }

    public void setIsGetLimit(Boolean isGetLimit) {
        this.isGetLimit = isGetLimit;
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

	public String getCapitalSourceName() {
		return capitalSourceName;
	}

	public void setCapitalSourceName(String capitalSourceName) {
		this.capitalSourceName = capitalSourceName;
	}

}
