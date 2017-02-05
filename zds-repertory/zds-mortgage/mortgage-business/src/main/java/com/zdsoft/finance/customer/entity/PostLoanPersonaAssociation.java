package com.zdsoft.finance.customer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 贷后客户关系表
 * @author <a href="mailto:502549751@qq.com">zhangchao</a>
 * @createTime: 2017-01-10 14::41
 * @version: 1.0
 *
 */
@Entity
@Table(name = "cus_post_pers_association")
public class PostLoanPersonaAssociation extends BaseEntity{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * 主表客户id
     */
    @Column(length = 32)
	private String postLoanCustomerId;
    
    /**
     * 从表客户id
     */
    @Column(length = 32)
	private String relationtCustomerId;
    
    /**
     * 与主借人关系(r01431：朋友；r01432：亲戚；r01433：同事；r01434：配偶)
     */
    @Column(length = 20)
	private String relationship;

	public String getPostLoanCustomerId() {
		return postLoanCustomerId;
	}

	public void setPostLoanCustomerId(String postLoanCustomerId) {
		this.postLoanCustomerId = postLoanCustomerId;
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

}
