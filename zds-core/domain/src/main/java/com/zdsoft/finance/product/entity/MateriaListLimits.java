package com.zdsoft.finance.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: MateriaListLimits.java 
 * @ClassName: MateriaListLimits 
 * @Description: 资料清单权限
 * @author gufeng 
 * @date 2017年3月2日 下午4:05:20 
 * @version V1.0
 */
@Entity
@Table(name = "prd_materialList_limits")
public class MateriaListLimits extends BaseEntity{

	private static final long serialVersionUID = -2683465336173829386L;

	/**
	 * 产品id
	 */
	@Column(length = 32)
	private String productId;
	/**
     * 资料大类编号
     */
    @Column(length = 20)
    private String materiaTypeCode;
    /**
     * 拥有权限编号
     */
    @Column(length = 20)
    private String materiaLimit;
    
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getMateriaTypeCode() {
		return materiaTypeCode;
	}
	public void setMateriaTypeCode(String materiaTypeCode) {
		this.materiaTypeCode = materiaTypeCode;
	}
	public String getMateriaLimit() {
		return materiaLimit;
	}
	public void setMateriaLimit(String materiaLimit) {
		this.materiaLimit = materiaLimit;
	}
    
    
}
