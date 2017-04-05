package com.zdsoft.finance.customer.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.customer.entity.AfterPersonaAssociation;

/**
 * 贷后客户关系表
 * 版权所有：重庆正大华日软件有限公司
 * @Title: PostLoanPersonaAssociationVo.java 
 * @ClassName: PostLoanPersonaAssociationVo 
 * @Description: 
 * @author zhangchao 
 * @date 2017年2月6日 上午11:12:47 
 * @version V1.0
 */
public class AfterPersonaAssociationVo extends BaseVo<AfterPersonaAssociation>{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 主表客户id
     */
	private String postLoanCustomerbeforeCustomerId;
    
    /**
     * 从表客户id
     */
	private String relationtCustomerId;
    
    /**
     * 与主借人关系(YWDM0014801：朋友；YWDM0014802：亲戚；YWDM0014803：同事；YWDM0014804：配偶)
     */
	private String relationship;

	public String getPostLoanCustomerbeforeCustomerId() {
		return postLoanCustomerbeforeCustomerId;
	}

	public void setPostLoanCustomerbeforeCustomerId(String postLoanCustomerbeforeCustomerId) {
		this.postLoanCustomerbeforeCustomerId = postLoanCustomerbeforeCustomerId;
	}

	public String getRelationtCustomerId() {
		return relationtCustomerId;
	}

	public void setRelationtCustomerId(String relationtCustomerId) {
		this.relationtCustomerId = relationtCustomerId;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	
	public AfterPersonaAssociationVo(){}
	
	public AfterPersonaAssociationVo(AfterPersonaAssociation po){
		super(po);
	}
	
	public AfterPersonaAssociation toPO(){
		AfterPersonaAssociation po = new AfterPersonaAssociation();
		return super.toPo(this, po);
	}

}
