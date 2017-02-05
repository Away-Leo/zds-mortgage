package com.zdsoft.finance.prcostitem.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 机构产品费用
 * @author <a href="mailto:gufeng@zdsoft.cn">gufeng</a>
 * @date 2016-12-30
 */
@Entity
@Table(name = "pr_cost_item")
public class PrCostItem extends BaseEntity {

	private static final long serialVersionUID = 1085102587372404085L;

	/**
	 * 产品id
	 */
	@Column(length = 32)
	private String productId;
	
	/**
	 * 产品父级id
	 */
	@Column(length = 32)
	private String productParentId;
	
	/**
	 * 期数开始
	 */
	@Column(precision = 30, scale = 12)
	private Double periodStart;
	
	/**
	 * 期数开始单位
	 */
	@Column(length = 32)
	private String periodStartUnit;
	
	/**
	 * 期数结束
	 */
	@Column(precision = 30, scale = 12)
	private Double periodEnd;
	
	/**
	 * 期数结束单位
	 */
	@Column(length = 32)
	private String periodEndUnit;
	
	/**
	 * 贷款金额 开始
	 */
	@Column(precision = 30, scale = 12)
	private BigDecimal amountStart;
	
	/**
	 * 贷款金额 结束
	 */
	@Column(precision = 30, scale = 12)
	private BigDecimal amountEnd;
	
	/**
	 * 抵押顺位 开始
	 */
	@Column(length = 32)
	private String pledgePickStart;
	
	/**
	 * 抵押顺位 结束
	 */
	@Column(length = 32)
	private String pledgePickEnd;
	
	/**
	 * 逾期收费 (%/天)
	 */
	@Column(precision = 30, scale = 12)
	private Double overdueFee;
	
	/**
	 * 展期收费
	 */
	@Column(precision = 30, scale = 12)
	private Double renewalFee;
	
	/**
	 * 终端
	 */
	@Column(length = 32)
	private String terminal;
	
	/**
	 * 还款方式
	 */
	@Column(length = 32)
	private String repaymentType;
	
	/**
	 * 提前还款违约金(所有已收费用，一律不退)
	 */
	@Column(length = 32)
	private String prepayment;
	
	@OneToMany(mappedBy = "prCostItem")
	private List<PrCostItemDetail> prCostItemDetails;
	
	@ManyToOne
	@JoinColumn(name="pr_costitem_apply_id")
	private PrCostitemApply prCostitemApply;

	private transient String[] prCostItemDetail_ids;
	
	private transient String prCostitemApply_id;
	
	private transient String[] detailCode;
	private transient String[] detailName;
	private transient String[] detailCollectionType;
	private transient Double[] detailStock;
	private transient Double[] detailMonth;
	private transient Double[] detailCostMonth;
	private transient Double[] detailReceipt;
	
	public String[] getDetailCode() {
		return detailCode;
	}

	public void setDetailCode(String[] detailCode) {
		this.detailCode = detailCode;
	}

	public String[] getDetailName() {
		return detailName;
	}

	public void setDetailName(String[] detailName) {
		this.detailName = detailName;
	}

	public String[] getDetailCollectionType() {
		return detailCollectionType;
	}

	public void setDetailCollectionType(String[] detailCollectionType) {
		this.detailCollectionType = detailCollectionType;
	}

	public Double[] getDetailStock() {
		return detailStock;
	}

	public void setDetailStock(Double[] detailStock) {
		this.detailStock = detailStock;
	}

	public Double[] getDetailMonth() {
		return detailMonth;
	}

	public void setDetailMonth(Double[] detailMonth) {
		this.detailMonth = detailMonth;
	}

	public Double[] getDetailCostMonth() {
		return detailCostMonth;
	}

	public void setDetailCostMonth(Double[] detailCostMonth) {
		this.detailCostMonth = detailCostMonth;
	}

	public Double[] getDetailReceipt() {
		return detailReceipt;
	}

	public void setDetailReceipt(Double[] detailReceipt) {
		this.detailReceipt = detailReceipt;
	}

	public String getPrCostitemApply_id() {
		return prCostitemApply_id;
	}

	public void setPrCostitemApply_id(String prCostitemApply_id) {
		this.prCostitemApply_id = prCostitemApply_id;
	}

	public PrCostitemApply getPrCostitemApply() {
		return prCostitemApply;
	}

	public void setPrCostitemApply(PrCostitemApply prCostitemApply) {
		this.prCostitemApply = prCostitemApply;
	}

	public String[] getPrCostItemDetail_ids() {
		return prCostItemDetail_ids;
	}

	public void setPrCostItemDetail_ids(String[] prCostItemDetail_ids) {
		this.prCostItemDetail_ids = prCostItemDetail_ids;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductParentId() {
		return productParentId;
	}

	public void setProductParentId(String productParentId) {
		this.productParentId = productParentId;
	}

	public Double getPeriodStart() {
		return periodStart;
	}

	public void setPeriodStart(Double periodStart) {
		this.periodStart = periodStart;
	}

	public String getPeriodStartUnit() {
		return periodStartUnit;
	}

	public void setPeriodStartUnit(String periodStartUnit) {
		this.periodStartUnit = periodStartUnit;
	}

	public Double getPeriodEnd() {
		return periodEnd;
	}

	public void setPeriodEnd(Double periodEnd) {
		this.periodEnd = periodEnd;
	}

	public String getPeriodEndUnit() {
		return periodEndUnit;
	}

	public void setPeriodEndUnit(String periodEndUnit) {
		this.periodEndUnit = periodEndUnit;
	}

	public BigDecimal getAmountStart() {
		return amountStart;
	}

	public void setAmountStart(BigDecimal amountStart) {
		this.amountStart = amountStart;
	}

	public BigDecimal getAmountEnd() {
		return amountEnd;
	}

	public void setAmountEnd(BigDecimal amountEnd) {
		this.amountEnd = amountEnd;
	}

	public String getPledgePickStart() {
		return pledgePickStart;
	}

	public void setPledgePickStart(String pledgePickStart) {
		this.pledgePickStart = pledgePickStart;
	}

	public String getPledgePickEnd() {
		return pledgePickEnd;
	}

	public void setPledgePickEnd(String pledgePickEnd) {
		this.pledgePickEnd = pledgePickEnd;
	}

	public Double getOverdueFee() {
		return overdueFee;
	}

	public void setOverdueFee(Double overdueFee) {
		this.overdueFee = overdueFee;
	}

	public Double getRenewalFee() {
		return renewalFee;
	}

	public void setRenewalFee(Double renewalFee) {
		this.renewalFee = renewalFee;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getRepaymentType() {
		return repaymentType;
	}

	public void setRepaymentType(String repaymentType) {
		this.repaymentType = repaymentType;
	}

	public String getPrepayment() {
		return prepayment;
	}

	public void setPrepayment(String prepayment) {
		this.prepayment = prepayment;
	}

	public List<PrCostItemDetail> getPrCostItemDetails() {
		return prCostItemDetails;
	}

	public void setPrCostItemDetails(List<PrCostItemDetail> prCostItemDetails) {
		this.prCostItemDetails = prCostItemDetails;
	}

}
