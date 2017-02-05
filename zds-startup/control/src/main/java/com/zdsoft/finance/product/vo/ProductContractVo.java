package com.zdsoft.finance.product.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.product.entity.ProductContract;

/**
 * 产品合同关联关系
 * @createTime 2017年1月10日 下午7:31:23
 * @author <a href="mailto:gufeng@zdsoft.cn">gufeng</a>
 * @version 1.0
 */
public class ProductContractVo extends BaseVo<ProductContract> {


	/**
	 * 产品id
	 */
	private String productId;
	
	/**
	 * 合同id
	 */
	private String contractId;
	
	private String[] contractIds;

	public String[] getContractIds() {
		return contractIds;
	}

	public void setContractIds(String[] contractIds) {
		this.contractIds = contractIds;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	
	public ProductContractVo(){}
	
	public ProductContractVo(ProductContract po){
		super(po);
	}
	
	public ProductContract toPO(){
		ProductContract po = new ProductContract();
		return super.toPo(this, po);
	}
	
}
