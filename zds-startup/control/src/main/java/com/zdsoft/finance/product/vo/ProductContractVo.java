package com.zdsoft.finance.product.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.product.entity.ProductContract;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ProductContractVo.java 
 * @ClassName: ProductContractVo 
 * @Description: 产品合同关联关系
 * @author gufeng 
 * @date 2017年3月13日 下午4:46:16 
 * @version V1.0
 */
public class ProductContractVo extends BaseVo<ProductContract> {


	private static final long serialVersionUID = -2470281713950092757L;

	/**
	 * 产品id
	 */
	private String productId;
	
	/**
	 * 合同id
	 */
	private String contractId;
	
	/**
	 * 合同id多个
	 */
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
