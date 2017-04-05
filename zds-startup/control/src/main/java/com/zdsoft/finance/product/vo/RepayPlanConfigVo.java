package com.zdsoft.finance.product.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.product.entity.Product;
import com.zdsoft.finance.product.entity.RepayPlanConfig;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: RepayPlanConfigVo.java 
 * @ClassName: RepayPlanConfigVo 
 * @Description: 还款计划配置
 * @author gufeng 
 * @date 2017年3月6日 下午5:02:44 
 * @version V1.0
 */
public class RepayPlanConfigVo extends BaseVo<RepayPlanConfig> {

	private static final long serialVersionUID = -85683954887695687L;

	/**
	 * 费用项目
	 */
	private String feeName;
	
	/**
	 * 费用项目
	 */
	private String feeCode;
	
	// 收款方
	private String receiverId;
	
	/**
	 * 收款方名字
	 */
	private String receiverName;
	
	/**
	 * 是否启用
	 */
	private Boolean isEnable;
	
	/**
	 * 所属产品
	 */
	private ProductVo productVo;

	public String getFeeName() {
		return feeName;
	}

	public void setFeeName(String feeName) {
		this.feeName = feeName;
	}

	public String getFeeCode() {
		return feeCode;
	}

	public void setFeeCode(String feeCode) {
		this.feeCode = feeCode;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
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
