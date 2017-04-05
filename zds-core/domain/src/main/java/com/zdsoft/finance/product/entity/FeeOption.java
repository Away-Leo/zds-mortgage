package com.zdsoft.finance.product.entity;

import com.zdsoft.framework.core.common.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import java.math.BigDecimal;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: FeeOption.java 
 * @ClassName: FeeOption 
 * @Description: 产品机构费用
 * @author gufeng 
 * @date 2017年3月6日 上午10:51:52 
 * @version V1.0
 */
@Entity
@Table(name = "prd_feeoption")
public class FeeOption extends BaseEntity {

	private static final long serialVersionUID = 4855564738503102583L;
	/**
     * 产品id
     */
    @Column(length = 36)
    private String productId;
    /**
     * 收费类型
     */
    @Column(length = 20)
    private String feeType;
    /**
     * 收费项目
     */
    @Column(length = 20)
    private String feeItem;
    /**
     * 收款计算方式
     */
    @Column(length = 20)
    private String chargeCalculateWay;
    /**
     * 收款金额
     */
    @Column(precision = 18,scale = 6)
    private BigDecimal chargeAmount = BigDecimal.ZERO;
    /**
     * 收款比例
     */
    @Column(precision = 18,scale = 6)
    private Double chargeRatio;
    /**
     * 付款计算方式
     */
    @Column(length = 20)
    private String payCalculateWay;
    /**
     * 付款金额
     */
    @Column(precision = 18,scale = 6)
    private BigDecimal payAmount = BigDecimal.ZERO;
    /**
     * 付款比例
     */
    @Column(precision = 18,scale = 6)
    private Double payRatio;
    /**
     * 是否营业收入
     */
    @Column
    @Type(type="true_false")
    private Boolean isOperateIncome = false;
    /**
     * 是否先请再付
     */
    @Column
    @Type(type="true_false")
    private Boolean isPayFirst = false;
    /**
     * 支付条件
     */
    @Column(length = 20)
    private String paymentTerms;
    /**
     * 是否支佣
     */
    @Column
    @Type(type="true_false")
    private Boolean isPayCommission = false;
    /**
     * 佣金支付条件
     */
    @Column(length = 20)
    private String payCondition;
    /**
     * 支佣节点
     */
    @Column(length = 64)
    private String point;
    /**
     * 是否停用
     */
    @Column
    @Type(type="true_false")
    private Boolean isEnable;
    /**
     * 备注
     */
    @Column(length = 1000)
    private String remark;
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	public String getFeeItem() {
		return feeItem;
	}
	public void setFeeItem(String feeItem) {
		this.feeItem = feeItem;
	}
	public String getChargeCalculateWay() {
		return chargeCalculateWay;
	}
	public void setChargeCalculateWay(String chargeCalculateWay) {
		this.chargeCalculateWay = chargeCalculateWay;
	}
	public BigDecimal getChargeAmount() {
		return chargeAmount;
	}
	public void setChargeAmount(BigDecimal chargeAmount) {
		this.chargeAmount = chargeAmount;
	}
	public Double getChargeRatio() {
		return chargeRatio;
	}
	public void setChargeRatio(Double chargeRatio) {
		this.chargeRatio = chargeRatio;
	}
	public String getPayCalculateWay() {
		return payCalculateWay;
	}
	public void setPayCalculateWay(String payCalculateWay) {
		this.payCalculateWay = payCalculateWay;
	}
	public BigDecimal getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}
	public Double getPayRatio() {
		return payRatio;
	}
	public void setPayRatio(Double payRatio) {
		this.payRatio = payRatio;
	}
	public Boolean getIsOperateIncome() {
		return isOperateIncome;
	}
	public void setIsOperateIncome(Boolean isOperateIncome) {
		this.isOperateIncome = isOperateIncome;
	}
	public Boolean getIsPayFirst() {
		return isPayFirst;
	}
	public void setIsPayFirst(Boolean isPayFirst) {
		this.isPayFirst = isPayFirst;
	}
	public String getPaymentTerms() {
		return paymentTerms;
	}
	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
	}
	public Boolean getIsPayCommission() {
		return isPayCommission;
	}
	public void setIsPayCommission(Boolean isPayCommission) {
		this.isPayCommission = isPayCommission;
	}
	public String getPayCondition() {
		return payCondition;
	}
	public void setPayCondition(String payCondition) {
		this.payCondition = payCondition;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public Boolean getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
    
   
}
