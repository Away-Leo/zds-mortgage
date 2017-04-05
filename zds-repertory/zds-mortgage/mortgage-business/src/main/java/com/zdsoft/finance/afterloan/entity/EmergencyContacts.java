package com.zdsoft.finance.afterloan.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.zdsoft.framework.core.common.domain.BaseEntity;
/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: EmergencyContacts.java 
 * @ClassName: EmergencyContacts 
 * @Description: 紧急联系人实体类
 * @author huangwei 
 * @date 2017年3月2日 上午10:38:26 
 * @version V1.0
 */
@Entity
@Table(name = "aftloan_urgency_contact")
public class EmergencyContacts extends BaseEntity {
	/**   
	 * @Fields serialVersionUID : TODO   
	 */ 
	private static final long serialVersionUID = -5369419678106331850L;
	/**
	 * 案件id
	 */
	@Column(length=32)
	public String caseApplyId;
	/**
	 * 姓名
	 */
	@Column(length=128)
	public String contactName;
	/**
	 * 联系方式
	 */
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "customerId")
	@LazyCollection(LazyCollectionOption.TRUE)
	private List<EmergencyContact> postLoanContacts = new ArrayList<EmergencyContact>();
	/**
	 * 与主借人关系
	 */
	@Column(length=20)
	public String relationship;
	public String getCaseApplyId() {
		return caseApplyId;
	}
	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public List<EmergencyContact> getPostLoanContacts() {
		return postLoanContacts;
	}
	public void setPostLoanContacts(List<EmergencyContact> postLoanContacts) {
		this.postLoanContacts = postLoanContacts;
	}
	
}
