package com.zdsoft.finance.contract.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.contract.entity.ConCaseContract;

import java.math.BigDecimal;

/**
 *
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ConCaseContractVo.java
 * @ClassName: ConCaseContractVo
 * @Description: 合同VO
 * @author denglw
 * @date 2017年3月21日 下午5:07:03
 * @version V1.0
 */
public class ConCaseContractVo extends BaseVo<ConCaseContract> {

    private static final long serialVersionUID = 1L;
    /**
     * 案件ID
     */
    private String caseApplyId;
    /**
     * 合同编号
     */
    private String contractNo;

    /**
     * 合同金额
     */
    private BigDecimal contractAmount = BigDecimal.ZERO;
    /**
     * 利率
     */
    private BigDecimal contractRate = BigDecimal.ZERO;

    /**
     * 2017/3/1 dengyy
     * 利率单位
     */
    private String contractRateUnit ;

    /**
     * 还款方式
     */
    private String repaymentMethod;
    /**
     * 合同开始日期
     */
    private Long contractStartDate;
    /**
     * 合同结束日期
     */
    private Long contractEndDate;
    /**
     * 备注
     */
    private String remark;

    public String getCaseApplyId() {
        return caseApplyId;
    }

    public void setCaseApplyId(String caseApplyId) {
        this.caseApplyId = caseApplyId;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public BigDecimal getContractAmount() {
        return contractAmount;
    }

    public void setContractAmount(BigDecimal contractAmount) {
        this.contractAmount = contractAmount;
    }

    public BigDecimal getContractRate() {
        return contractRate;
    }

    public void setContractRate(BigDecimal contractRate) {
        this.contractRate = contractRate;
    }

    public String getContractRateUnit() {
        return contractRateUnit;
    }

    public void setContractRateUnit(String contractRateUnit) {
        this.contractRateUnit = contractRateUnit;
    }

    public String getRepaymentMethod() {
        return repaymentMethod;
    }

    public void setRepaymentMethod(String repaymentMethod) {
        this.repaymentMethod = repaymentMethod;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
