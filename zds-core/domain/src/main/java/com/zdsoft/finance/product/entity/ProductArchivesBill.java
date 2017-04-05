package com.zdsoft.finance.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ProductArchivesBill.java 
 * @ClassName: ProductArchivesBill 
 * @Description: 档案清单
 * @author gufeng 
 * @date 2017年3月13日 下午4:46:37 
 * @version V1.0
 */
@Entity
@Table(name = "prd_archives_bill")
public class ProductArchivesBill extends BaseEntity{

	private static final long serialVersionUID = 987658391285820857L;
	
	/**
	 * 档案名称
	 */
	@Column(length = 64)
	private String archivesName;
	
	/**
	 * 档案等级
	 */
	@Column(length = 20)
	private String archivesLevel;
	
	/**
	 * 原件/复印件/照片件
	 */
	@Column(length = 128)
	private String archivesType;
	
	/**
	 * 产品id
	 */
	@Column(length = 32)
	private String productId;

	public String getArchivesName() {
		return archivesName;
	}

	public void setArchivesName(String archivesName) {
		this.archivesName = archivesName;
	}

	public String getArchivesLevel() {
		return archivesLevel;
	}

	public void setArchivesLevel(String archivesLevel) {
		this.archivesLevel = archivesLevel;
	}

	public String getArchivesType() {
		return archivesType;
	}

	public void setArchivesType(String archivesType) {
		this.archivesType = archivesType;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
}