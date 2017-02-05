package com.zdsoft.finance.product.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.product.entity.ApprovalOpinion;
import com.zdsoft.finance.product.entity.Product;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 审批意见vo
 * @author longwei
 * @date 2016/12/28
 * @version 1.0
 */
public class ApprovalOpinionVo extends BaseVo<ApprovalOpinion>{

	// 审批类型
	private String approvalTypeCd;
	
	// 审批类型
	private String approvalTypeNm;
	
	// 抵押顺序
	private String mortgageOrderCd;
	
	// 抵押顺序
	private String mortgageOrderNm;
	
	// 是否启用
	private Boolean isEnable;
	
	// 审批用语
	private String remark;
	
	// 所属产品
	private ProductVo productVo;

	public String getApprovalTypeCd() {
		return approvalTypeCd;
	}

	public void setApprovalTypeCd(String approvalTypeCd) {
		this.approvalTypeCd = approvalTypeCd;
	}

	public String getApprovalTypeNm() {
		return approvalTypeNm;
	}

	public void setApprovalTypeNm(String approvalTypeNm) {
		this.approvalTypeNm = approvalTypeNm;
	}

	public String getMortgageOrderCd() {
		return mortgageOrderCd;
	}

	public void setMortgageOrderCd(String mortgageOrderCd) {
		this.mortgageOrderCd = mortgageOrderCd;
	}

	public String getMortgageOrderNm() {
		return mortgageOrderNm;
	}

	public void setMortgageOrderNm(String mortgageOrderNm) {
		this.mortgageOrderNm = mortgageOrderNm;
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

	public ProductVo getProductVo() {
		return productVo;
	}

	public void setProductVo(ProductVo productVo) {
		this.productVo = productVo;
	}
	
	public ApprovalOpinionVo(){}
	
	public ApprovalOpinionVo(ApprovalOpinion approvalOpinion){
		super(approvalOpinion, new String[]{"productVo"});
		this.setProductVo(new ProductVo(approvalOpinion.getProduct()));
	}
	
	public ApprovalOpinion toPo(){
		ApprovalOpinion approvalOpinion=new ApprovalOpinion();
		super.toPo(this, approvalOpinion,new String[]{"productVo"});
		if(ObjectHelper.isNotEmpty(this.getProductVo())){
			Product product=this.getProductVo().toPo();
			approvalOpinion.setProduct(product);
		}
		
		return approvalOpinion;
	}
}
