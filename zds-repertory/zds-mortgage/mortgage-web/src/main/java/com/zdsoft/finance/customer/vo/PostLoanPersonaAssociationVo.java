package com.zdsoft.finance.customer.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.customer.entity.PostLoanPersonaAssociation;

/**
 * 贷后客户关系表
 * @author <a href="mailto:502549751@qq.com">zhangchao</a>
 * @createTime: 2017-01-10 14::41
 * @version: 1.0
 *
 */
public class PostLoanPersonaAssociationVo extends BaseVo<PostLoanPersonaAssociation>{

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
     * 与主借人关系(r01431：朋友；r01432：亲戚；r01433：同事；r01434：配偶)
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
	
	public PostLoanPersonaAssociationVo(){}
	
	public PostLoanPersonaAssociationVo(PostLoanPersonaAssociation po){
		super(po);
	}
	
	public PostLoanPersonaAssociation toPO(){
		PostLoanPersonaAssociation po = new PostLoanPersonaAssociation();
		return super.toPo(this, po);
	}

}
