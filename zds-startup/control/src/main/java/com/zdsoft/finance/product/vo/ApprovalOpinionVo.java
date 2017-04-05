package com.zdsoft.finance.product.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.product.entity.ApprovalOpinion;
import com.zdsoft.finance.product.entity.Product;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ApprovalOpinionVo.java 
 * @ClassName: ApprovalOpinionVo 
 * @Description: 审批意见vo
 * @author gufeng 
 * @date 2017年3月6日 下午7:22:40 
 * @version V1.0
 */
public class ApprovalOpinionVo extends BaseVo<ApprovalOpinion>{

	private static final long serialVersionUID = -246637072671722909L;

	/**
	 * 审批类型
	 */
	private String approvalType;
	
	/**
	 * 审批类型
	 */
	private String approvalTypeName;
	
	/**
	 * 抵押顺序
	 */
	private String mortgageOrder;
	
	/**
	 * 抵押顺序
	 */
	private String mortgageOrderName;
	
	/**
	 * 是否启用
	 */
	private Boolean isEnable;
	
	/**
	 * 审批用语
	 */
	private String remark;
	
	/**
	 * 所属产品
	 */
	private ProductVo productVo;

	public String getApprovalType() {
		return approvalType;
	}

	public void setApprovalType(String approvalType) {
		this.approvalType = approvalType;
	}

	public String getApprovalTypeName() {
		return approvalTypeName;
	}

	public void setApprovalTypeName(String approvalTypeName) {
		this.approvalTypeName = approvalTypeName;
	}

	public String getMortgageOrder() {
		return mortgageOrder;
	}

	public void setMortgageOrder(String mortgageOrder) {
		this.mortgageOrder = mortgageOrder;
	}

	public String getMortgageOrderName() {
		return mortgageOrderName;
	}

	public void setMortgageOrderName(String mortgageOrderName) {
		this.mortgageOrderName = mortgageOrderName;
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
