package com.zdsoft.finance.prcostitem.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.prcostitem.entity.PrCostItem;
import com.zdsoft.finance.prcostitem.entity.PrCostItemDetail;
import com.zdsoft.framework.core.common.util.ObjectHelper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: PrCostItemVo.java 
 * @ClassName: PrCostItemVo 
 * @Description: 机构产品费用
 * @author gufeng 
 * @date 2017年3月13日 下午5:10:03 
 * @version V1.0
 */
public class PrCostItemVo extends BaseVo<PrCostItem> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2221250245616592384L;

	/**
	 * 产品id
	 */
	private String productId;
	
	/**
	 * 产品名字
	 */
	private String productName;
	
	/**
	 * 产品父级id
	 */
	private String productParentId;
	
	/**
	 * 父产品名字
	 */
	private String productParentName;
	
	/**
	 * 期数开始
	 */
	private Double periodStart;
	
	/**
	 * 期数开始单位
	 */
	private String periodStartUnit;
	
	private String periodStartUnitName;
	
	/**
	 * 期数结束
	 */
	private Double periodEnd;
	
	/**
	 * 期数结束单位
	 */
	private String periodEndUnit;
	
	private String periodEndUnitName;
	
	/**
	 * 贷款金额 开始
	 */
	private BigDecimal amountStart;
	
	/**
	 * 贷款金额 结束
	 */
	private BigDecimal amountEnd;
	
	/**
	 * 抵押顺位 开始
	 */
	private String pledgePickStart;
	
	/**
	 * 抵押顺位 结束
	 */
	private String pledgePickEnd;
	
	/**
	 * 逾期收费 (%/天)
	 */
	private Double overdueFee;
	/**
	 * 逾期收费单位编码
	 */
	private String overdueFeeUnit;
	/**
	 * 逾期收费单位编码名称
	 */
	private String overdueFeeUnitName;

	/**
	 * 展期收费
	 */
	private Double renewalFee;
	
	/**
	 * 终端
	 */
	private String terminal;
	
	/**
	 * 还款方式
	 */
	private String repaymentType;
	
	/**
	 * 提前还款违约金(所有已收费用，一律不退)
	 */
	private String prepayment;
	
	/**
	 * 费用明细
	 */
	private String[] prCostItemDetail_ids;
	
	private List<PrCostItemDetailVo> details;
	
	private String prCostitemApply_id;
	
	private String[] detailCode;
	private String[] detailName;
	private String[] detailCollectionType;
	private Double[] detailStock;
	private Double[] detailMonth;
	private Double[] detailCostMonth;
	private Double[] detailReceipt;

	public String getOverdueFeeUnit() {
		return overdueFeeUnit;
	}

	public void setOverdueFeeUnit(String overdueFeeUnit) {
		this.overdueFeeUnit = overdueFeeUnit;
	}

	public String getOverdueFeeUnitName() {
		return overdueFeeUnitName;
	}

	public void setOverdueFeeUnitName(String overdueFeeUnitName) {
		this.overdueFeeUnitName = overdueFeeUnitName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductParentName() {
		return productParentName;
	}

	public void setProductParentName(String productParentName) {
		this.productParentName = productParentName;
	}

	public String getPeriodStartUnitName() {
		return periodStartUnitName;
	}

	public void setPeriodStartUnitName(String periodStartUnitName) {
		this.periodStartUnitName = periodStartUnitName;
	}

	public String getPeriodEndUnitName() {
		return periodEndUnitName;
	}

	public void setPeriodEndUnitName(String periodEndUnitName) {
		this.periodEndUnitName = periodEndUnitName;
	}

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

	public List<PrCostItemDetailVo> getDetails() {
		return details;
	}

	public void setDetails(List<PrCostItemDetailVo> details) {
		this.details = details;
	}

	public String getPrCostitemApply_id() {
		return prCostitemApply_id;
	}

	public void setPrCostitemApply_id(String prCostitemApply_id) {
		this.prCostitemApply_id = prCostitemApply_id;
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

	public String[] getPrCostItemDetail_ids() {
		return prCostItemDetail_ids;
	}

	public void setPrCostItemDetail_ids(String[] prCostItemDetail_ids) {
		this.prCostItemDetail_ids = prCostItemDetail_ids;
	}

	public PrCostItemVo(){}
	
	public PrCostItemVo(PrCostItem po){
		super(po,null,new String[]{"periodStartUnit","periodEndUnit","overdueFeeUnit"});
		if(ObjectHelper.isNotEmpty(po.getPrCostItemDetails())){
			List<PrCostItemDetail> detailList = po.getPrCostItemDetails();
			String[] detail_ids = new String[detailList.size()];
			String[] detailCode = new String[detailList.size()];
			String[] detailName = new String[detailList.size()];
			String[] detailCollectionType = new String[detailList.size()];
			Double[] detailStock = new Double[detailList.size()];
			Double[] detailMonth = new Double[detailList.size()];
			Double[] detailCostMonth = new Double[detailList.size()];
			Double[] detailReceipt = new Double[detailList.size()];
			List<PrCostItemDetailVo> list = new ArrayList<>();
			for (int i = 0; i < detailList.size(); i++) {
				PrCostItemDetail detail = detailList.get(i);
				detail_ids[i] = detail.getId();
				PrCostItemDetailVo vo = new PrCostItemDetailVo(detail);
				list.add(vo);
				detailCode[i] = detail.getCode();
				detailName[i] = detail.getName();
				detailCollectionType[i] = detail.getCollectionType();
				detailStock[i] = detail.getStock();
				detailMonth[i] = detail.getMonth();
				detailCostMonth[i] = detail.getCostMonth();
				detailReceipt[i] =detail.getReceipt();
			}
			this.setPrCostItemDetail_ids(detail_ids);
			this.setDetails(list);
			//数组
			this.setDetailCode(detailCode);
			this.setDetailName(detailName);
			this.setDetailCollectionType(detailCollectionType);
			this.setDetailStock(detailStock);
			this.setDetailMonth(detailMonth);
			this.setDetailCostMonth(detailCostMonth);
			this.setDetailReceipt(detailReceipt);
		}
	}
	
	public PrCostItem toPO(){
		PrCostItem po = new PrCostItem();
		return super.toPo(this, po);
	}
	//页面列表展示
	//期数
	public String getPeriod(){
		return this.periodStart + this.periodStartUnitName + "-" + this.periodEnd + this.periodEndUnitName;
	}
	//金额
	public String getAmount(){
		return this.amountStart + "-" + this.amountEnd + "元";
	}
	//抵押顺位
	public String getPledgePick(){
		return this.pledgePickStart + "-" + this.pledgePickEnd;
	}
	//费用项
	public String getDetailNames(){
		return this.detailName[0] + "<br/>" + this.detailName[1] + "<br/>" + this.detailName[2];
	}
	//收款方式
	public String getDetailCollectionTypes(){
		return (ObjectHelper.isEmpty(this.detailCollectionType[0])?"&nbsp;":this.detailCollectionType[0]) + 
				"<br/>" + (ObjectHelper.isEmpty(this.detailCollectionType[1])?"&nbsp;":this.detailCollectionType[1]) + 
				"<br/>" + (ObjectHelper.isEmpty(this.detailCollectionType[2])?"&nbsp;":this.detailCollectionType[2]);
	}
	//财务确认营收(标准)
	public String getDetailStocks(){
		return (this.detailStock[0]==null?"&nbsp;":(this.detailStock[0]  + "%/笔")) + (this.detailMonth[0]==null?"&nbsp;":(this.detailMonth[0]  + "%/月")) + 
				"<br/>" + (this.detailStock[1]==null?"&nbsp;":(this.detailStock[1]  + "%/笔")) + (this.detailMonth[1]==null?"&nbsp;":(this.detailMonth[1]  + "%/月")) + 
				"<br/>" + (this.detailStock[2]==null?"&nbsp;":(this.detailStock[2]  + "%/笔")) + (this.detailMonth[2]==null?"&nbsp;":(this.detailMonth[2]  + "%/月"));
	}
	//支佣(标准)
	public String getDetailCostMonths(){
		return (this.detailCostMonth[0]==null?"&nbsp;":this.detailCostMonth[0]) + 
				"<br/>" + (this.detailCostMonth[1]==null?"&nbsp;":this.detailCostMonth[1]) + 
				"<br/>" + (this.detailCostMonth[2]==null?"&nbsp;":this.detailCostMonth[2]);
	}
	//净收入(标准)
	public String getDetailReceipts(){
		return (this.detailReceipt[0]==null?"&nbsp;":this.detailReceipt[0]) + 
				"<br/>" + (this.detailReceipt[1]==null?"&nbsp;":this.detailReceipt[1]) + 
				"<br/>" + (this.detailReceipt[2]==null?"&nbsp;":this.detailReceipt[2]);
	}
	
}
