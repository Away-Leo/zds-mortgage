package com.zdsoft.finance.customer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.zdsoft.finance.filestore.entity.FileStore;
import com.zdsoft.framework.core.common.domain.BaseEntity;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CreditAttachment.java 
 * @ClassName: CreditAttachment 
 * @Description: 案件客户征信附件信息
 * @author liuhuan 
 * @date 2017年2月23日 上午10:52:29 
 * @version V1.0 
 */ 
@Entity
@Table(name="cust_credit_attachment")
public class CreditAttachment extends BaseEntity{
	
	private static final long serialVersionUID = -8810462397372782570L;
	/**
     *  非征信-附件
     */
    public static final String NOT_CREDIT_ATTACHMENT = "YWDM001";
    /**
     *  征信报告-附件
     */
    public static final String CREDIT_REPORT = "YWDM002";
    /**
     *  征信授权书-附件
     */
    public static final String CREDIT_AUTHORIZATION = "YWDM003";
    /**
     *  征信身份证-附件
     */
    public static final String CREDIT_CARD = "YWDM004";
	
	/**
	 * 案件客户征信id
	 */
	@Column(length=32)
	private String creditId;
	
	/**
	 * 案件附件
	 */
	@OneToOne
	@JoinColumn(name = "commonFilestoreId")
	private FileStore fileStore;
	
	@Transient
	private String commonFilestoreId;
	
	public String getCommonFilestoreId() {
		return commonFilestoreId;
	}

	public void setCommonFilestoreId(String commonFilestoreId) {
		this.commonFilestoreId = commonFilestoreId;
	}

	public String getCreditId() {
		return creditId;
	}

	public void setCreditId(String creditId) {
		this.creditId = creditId;
	}

	public FileStore getFileStore() {
		return fileStore;
	}

	public void setFileStore(FileStore fileStore) {
		this.fileStore = fileStore;
	}
	
}
