package com.zdsoft.finance.product.entity;

import com.zdsoft.framework.core.common.domain.BaseEntity;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * 资料清单域对象
 * @author LiaoGuoWei
 * @create 2016-12-23 17:20
 **/
@Entity
@Table(name = "prct_materialList")
public class MaterialList extends BaseEntity{

    /**
     * 资料大类编号
     */
    @Column(length = 36)
    private String materiaTypeCode;

    /**
     * 资料大类名称
     */
    @Column(length = 255)
    private String materiaTypeName;

    /**
     * 所属产品编号
     */
    @Column(length = 255)
    private String productCode;
    /**
     * 所属产品名称
     */
    @Column(length = 255)
    private String productName;

    /**
     * 显示顺序
     */
    @Column(length = 9)
    private Integer showOrder=0;

    /**
     * 资料编号（小类编号）
     */
    @Column(length = 36)
    private String materiaCode;
    /**
     * 资料名称（小类名称）
     */
    @Column(length = 255)
    private String materiaName;

    /**
     * 助记码
     */
    @Column(length = 255)
    private String rememberCode;

    /**
     * 数字助记码
     */
    @Column(length = 19)
    private Long rememberNo;

    /**
     * 资料证明
     */
    @Column(length = 255)
    private String materiaIdentify;

    /**
     * 资料证明名称
     */
    @Column(length = 255)
    private String materiaIdentifyName;

    /**
     * 拥有的流程权限节点
     */
    @OneToMany(mappedBy = "materialList")
    @LazyCollection(LazyCollectionOption.TRUE)
    @Cascade({ CascadeType.ALL })
    private List<MateriaListAuth> materiaListAuth;

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

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
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

    public List<MateriaListAuth> getMateriaListAuth() {
        return materiaListAuth;
    }

    public void setMateriaListAuth(List<MateriaListAuth> materiaListAuth) {
        this.materiaListAuth = materiaListAuth;
    }
}
