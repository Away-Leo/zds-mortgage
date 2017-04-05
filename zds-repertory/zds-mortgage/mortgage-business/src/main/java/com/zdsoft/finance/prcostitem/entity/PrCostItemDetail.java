package com.zdsoft.finance.prcostitem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: PrCostItemDetail.java 
 * @ClassName: PrCostItemDetail 
 * @Description: 费用项明细
 * @author gufeng 
 * @date 2017年3月13日 下午4:49:38 
 * @version V1.0
 */
@Entity
@Table(name = "pr_cost_item_detail")
public class PrCostItemDetail extends BaseEntity {

	private static final long serialVersionUID = 2626066469634862266L;
	
	/**
	 * 费用项编码
	 */
	@Column(length = 32)
	private String code;
	
	/**
	 * 费用项名称
	 */
	@Column(length = 255)
	private String name;
	
	/**
	 * 收款方式
	 */
	@Column(length = 32)
	private String collectionType;
	
	/**
	 * 财务确认营收(标准) 笔数(%/笔)
	 */
	@Column(precision = 30, scale = 12)
	private Double stock;
	
	/**
	 * 财务确认营收(标准) 月数(%/月)
	 */
	@Column(precision = 30, scale = 12)
	private Double month;
	
	/**
	 * 支佣(标准) %/月
	 */
	@Column(precision = 30, scale = 12)
	private Double costMonth;
	
	/**
	 * 净收入(标准) %
	 */
	@Column(precision = 30, scale = 12)
	private Double receipt;

	@ManyToOne
    @JoinColumn(name="pr_cost_item_id")
    private PrCostItem prCostItem;
	
	/**
	 * 机构产品费用
	 */
    private transient String prCostItem_id;
	
	public String getPrCostItem_id() {
		return prCostItem_id;
	}

	public void setPrCostItem_id(String prCostItem_id) {
		this.prCostItem_id = prCostItem_id;
	}

	public PrCostItem getPrCostItem() {
		return prCostItem;
	}

	public void setPrCostItem(PrCostItem prCostItem) {
		this.prCostItem = prCostItem;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCollectionType() {
		return collectionType;
	}

	public void setCollectionType(String collectionType) {
		this.collectionType = collectionType;
	}

	public Double getStock() {
		return stock;
	}

	public void setStock(Double stock) {
		this.stock = stock;
	}

	public Double getMonth() {
		return month;
	}

	public void setMonth(Double month) {
		this.month = month;
	}

	public Double getCostMonth() {
		return costMonth;
	}

	public void setCostMonth(Double costMonth) {
		this.costMonth = costMonth;
	}

	public Double getReceipt() {
		return receipt;
	}

	public void setReceipt(Double receipt) {
		this.receipt = receipt;
	}
	
}
