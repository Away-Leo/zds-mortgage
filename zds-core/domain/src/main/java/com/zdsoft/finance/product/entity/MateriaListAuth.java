package com.zdsoft.finance.product.entity;

import com.zdsoft.framework.core.common.domain.BaseEntity;

import javax.persistence.*;

/**
 * 资料清单权限
 * @author LiaoGuoWei
 * @create 2016-12-23 17:31
 **/
@Entity
@Table(name = "prct_materialistauth")
public class MateriaListAuth extends BaseEntity {

    /**
     * 所属产品
     */
    @Column(length = 36)
    private String productCode;

    /**
     * 流程编号
     */
    @Column(length = 36)
    private String processCode;

    /**
     * 流程名称
     */
    @Column(length = 255)
    private String processName;

    /**
     * 节点编号
     */
    @Column(length = 36)
    private String processNodeCode;

    /**
     * 节点名称
     */
    @Column(length = 255)
    private String processNodeName;

    /**
     * 所属资料
     */
    @ManyToOne
    @JoinColumn(name="materialList_Id")
    private MaterialList materialList;

    public MaterialList getMaterialList() {
        return materialList;
    }

    public void setMaterialList(MaterialList materialList) {
        this.materialList = materialList;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProcessCode() {
        return processCode;
    }

    public void setProcessCode(String processCode) {
        this.processCode = processCode;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getProcessNodeCode() {
        return processNodeCode;
    }

    public void setProcessNodeCode(String processNodeCode) {
        this.processNodeCode = processNodeCode;
    }

    public String getProcessNodeName() {
        return processNodeName;
    }

    public void setProcessNodeName(String processNodeName) {
        this.processNodeName = processNodeName;
    }
}
