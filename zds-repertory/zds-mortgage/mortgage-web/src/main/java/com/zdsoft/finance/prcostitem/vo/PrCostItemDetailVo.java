package com.zdsoft.finance.prcostitem.vo;


import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.prcostitem.entity.PrCostItemDetail;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 费用项明细
 * @author <a href="mailto:gufeng@zdsoft.cn">gufeng</a>
 * @date 2016-12-30
 */
public class PrCostItemDetailVo extends BaseVo<PrCostItemDetail>{

	/**
	 * 费用项编码
	 */
	private String code;
	
	/**
	 * 费用项名称
	 */
	private String name;
	
	/**
	 * 收款方式
	 */
	private String collectionType;
	
	/**
	 * 财务确认营收(标准) 笔数(%/笔)
	 */
	private Double stock;
	
	/**
	 * 财务确认营收(标准) 月数(%/月)
	 */
	private Double month;
	
	/**
	 * 支佣(标准) %/月
	 */
	private Double costMonth;
	
	/**
	 * 净收入(标准) %
	 */
	private Double receipt;
	
	/**
	 * 机构产品费用
	 */
    private String prCostItem_id;
	
	public String getPrCostItem_id() {
		return prCostItem_id;
	}

	public void setPrCostItem_id(String prCostItem_id) {
		this.prCostItem_id = prCostItem_id;
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
	
	public PrCostItemDetailVo(){}
	
	public PrCostItemDetailVo(PrCostItemDetail po){
		super(po);
		if(ObjectHelper.isNotEmpty(po.getPrCostItem())){
			this.setPrCostItem_id(po.getPrCostItem().getId());
		}
	}
	
	public PrCostItemDetail toPO(){
		PrCostItemDetail po = new PrCostItemDetail();
		return super.toPo(this, po);
	}
}
