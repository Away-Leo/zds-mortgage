package com.zdsoft.finance.risk.entity;

import javax.persistence.Entity;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: SpecialApproveThings.java
 * @Package com.zdsoft.finance.risk.entity
 * @Description: 特批事项 TODO:仅模拟，实际模型未定
 * @author Liyb
 * @date 2017年1月15日 下午3:54:52
 * @version V1.0
 */
@Entity
// @Table(name = "ext_special_approve_things")
public class SpecialApproveThings extends BaseEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 2268499051441977064L;

    private String id;
    /**
     * 事项编码
     */
    private String itemCode;
    /**
     * 事项类别
     */
    private String itemType;
    /**
     * 事项名称
     */
    private String itemName;
    /**
     * 风险特批id
     */
    private String riskId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getRiskId() {
        return riskId;
    }

    public void setRiskId(String riskId) {
        this.riskId = riskId;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
