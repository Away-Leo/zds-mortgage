package com.zdsoft.finance.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: MaterialListType.java 
 * @ClassName: MaterialListType 
 * @Description: 资料类型
 * @author gufeng 
 * @date 2017年3月16日 下午2:24:22 
 * @version V1.0
 */
@Entity
@Table(name = "prd_materiallist_type")
public class MaterialListType extends BaseEntity {

	private static final long serialVersionUID = -6773246144177701931L;
    /**
     * 资料名称
     */
    @Column(length = 64)
    private String name;
    /**
     * 助记码
     */
    @Column(length = 32)
    private String rememberCode;
    /**
     * 数字助记码
     */
    @Column
    private Long rememberNo;
    /**
     * 备注
     */
    @Column(length = 500)
    private String remark;
    /**
     * 父级
     */
    @Column(length = 32,name="parent_id")
    private String pId;
    
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRememberCode() {
		return rememberCode;
	}
	public void setRememberCode(String rememberCode) {
		this.rememberCode = rememberCode;
	}
	public Long getRememberNo() {
		return rememberNo;
	}
	public void setRememberNo(Long rememberNo) {
		this.rememberNo = rememberNo;
	}
	
}
