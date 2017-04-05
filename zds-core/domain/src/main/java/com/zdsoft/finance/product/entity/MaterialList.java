package com.zdsoft.finance.product.entity;

import com.zdsoft.framework.core.common.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: MaterialList.java 
 * @ClassName: MaterialList 
 * @Description: 资料清单域对象
 * @author gufeng 
 * @date 2017年3月2日 下午4:08:47 
 * @version V1.0
 */
@Entity
@Table(name = "prd_materialList")
public class MaterialList extends BaseEntity{

	private static final long serialVersionUID = 2498742413144500732L;

	/**
     * 资料大类编号
     */
    @Column(length = 32)
    private String materiaTypeCode;

    /**
     * 资料大类名称
     */
    @Column(length = 64)
    private String materiaTypeName;

    /**
     * 所属产品编号
     */
    @Column(length = 32)
    private String productId;
    /**
     * 所属产品名称
     */
    @Column(length = 64)
    private String productName;

    /**
     * 显示顺序
     */
    @Column
    private Integer showOrder;

    /**
     * 资料编号（小类编号）
     */
    @Column(length = 32)
    private String materiaCode;
    /**
     * 资料名称（小类名称）
     */
    @Column(length = 64)
    private String materiaName;

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
     * 资料证明
     */
    @Column(length = 128)
    private String materiaIdentify;

    /**
     * 资料证明名称
     */
    @Column(length = 128)
    private String materiaIdentifyName;

    public String getMateriaTypeCode() {
        return materiaTypeCode;
    }

    public void setMateriaTypeCode(String materiaTypeCode) {
        this.materiaTypeCode = materiaTypeCode;
    }

    public String getMateriaTypeName() {
        return materiaTypeName;
    }

    public void setMateriaTypeName(String materiaTypeName) {
        this.materiaTypeName = materiaTypeName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getMateriaCode() {
        return materiaCode;
    }

    public void setMateriaCode(String materiaCode) {
        this.materiaCode = materiaCode;
    }

    public String getMateriaName() {
        return materiaName;
    }

    public void setMateriaName(String materiaName) {
        this.materiaName = materiaName;
    }

    public String getRememberCode() {
        return rememberCode;
    }

    public void setRememberCode(String rememberCode) {
        this.rememberCode = rememberCode;
    }

    public String getMateriaIdentifyName() {
        return materiaIdentifyName;
    }

    public void setMateriaIdentifyName(String materiaIdentifyName) {
        this.materiaIdentifyName = materiaIdentifyName;
    }

    public Integer getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(Integer showOrder) {
        this.showOrder = showOrder;
    }



    public Long getRememberNo() {
        return rememberNo;
    }

    public void setRememberNo(Long rememberNo) {
        this.rememberNo = rememberNo;
    }

    public String getMateriaIdentify() {
        return materiaIdentify;
    }

    public void setMateriaIdentify(String materiaIdentify) {
        this.materiaIdentify = materiaIdentify;
    }

}
