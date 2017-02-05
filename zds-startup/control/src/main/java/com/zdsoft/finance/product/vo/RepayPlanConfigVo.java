package com.zdsoft.finance.product.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.product.entity.Product;
import com.zdsoft.finance.product.entity.RepayPlanConfig;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 流程配置vo
 * @author longwei
 * @date 2016/12/29
 * @version 1.0
 */
public class RepayPlanConfigVo extends BaseVo<RepayPlanConfig> {

	// 费用项目
	private String feeNm;
	
	// 费用项目
	private String feeCd;
	
	// 收款方
	private String receiverCd;
	
	// 收款方
	private String receiverNm;
	
	// 是否启用
	private Boolean isEnable;
	
	// 所属产品
	private ProductVo productVo;

	public String getFeeNm() {
		return feeNm;
	}

	public void setFeeNm(String feeNm) {
		this.feeNm = feeNm;
	}

	public String getFeeCd() {
		return feeCd;
	}

	public void setFeeCd(String feeCd) {
		this.feeCd = feeCd;
	}

	public String getReceiverCd() {
		return receiverCd;
	}

	public void setReceiverCd(String receiverCd) {
		this.receiverCd = receiverCd;
	}

	public String getReceiverNm() {
		return receiverNm;
	}

	public void setReceiverNm(String receiverNm) {
		this.receiverNm = receiverNm;
	}

	public Boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	public ProductVo getProductVo() {
		return productVo;
	}

	public void setProductVo(ProductVo productVo) {
		this.productVo = productVo;
	}

	public RepayPlanConfigVo(){}
	
	public RepayPlanConfigVo(RepayPlanConfig repayPlanConfig){
		super(repayPlanConfig, new String[]{"productVo"});
		this.setProductVo(new ProductVo(repayPlanConfig.getProduct()));
	}
	
	public RepayPlanConfig toPo(){
		RepayPlanConfig repayPlanConfig=new RepayPlanConfig();
		super.toPo(this, repayPlanConfig,new String[]{"productVo"});
		if(ObjectHelper.isNotEmpty(this.getProductVo())){
			Product product=this.getProductVo().toPo();
			repayPlanConfig.setProduct(product);
		}
		
		return repayPlanConfig;
	}
	
}
