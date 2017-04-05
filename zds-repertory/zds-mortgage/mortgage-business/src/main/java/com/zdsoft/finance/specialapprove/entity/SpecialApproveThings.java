package com.zdsoft.finance.specialapprove.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: SpecialApproveThings.java
 * @Package com.zdsoft.finance.risk.entity
 * @Description: 风险特批事项 
 * @author wangrongwei
 * @date 2017年1月15日 下午3:54:52
 * @version V1.0
 */
@Entity
@Table(name = "exp_special_approve_things")
public class SpecialApproveThings extends BaseEntity {
	
	/**   
	 * @Fields THINGS_TYPE_CODE : 风险事项code  
	 */ 
	public static String THINGS_TYPE_CODE="YWDM0077";

    private static final long serialVersionUID = 2268499051441977064L;

    /**
     * 事项编码
     */
    @Column(length=32)
    private String itemCode;
    
    /**
     * 事项类别
     */
    @Column(length=20)
    private String itemType;
    
    /**
     * 事项名称
     */
    @Column(length=64)
    private String itemName;
    
    /**
     * 特批管理
     */
    @ManyToOne(targetEntity=SpecialApproveManage.class)
    @JoinColumn(name="specialApproveId",referencedColumnName="id")
    private SpecialApproveManage specialApproveManage;
    
    /** 
     * @Fields otherInfo : 其它（当选择风险措施中的‘其它’时所保存对应的内容）
     */
    @Column(length=64)
    private String otherInfo;
    
    /**   
     * @Fields expRiskRulesId : 风险规则ID 
     */ 
    @Column(length=32)
    private String expRiskRulesId;
    
    public SpecialApproveThings() {
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

	public SpecialApproveManage getSpecialApproveManage() {
		return specialApproveManage;
	}

	public void setSpecialApproveManage(SpecialApproveManage specialApproveManage) {
		this.specialApproveManage = specialApproveManage;
	}
	public String getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}

	public String getExpRiskRulesId() {
		return expRiskRulesId;
	}

	public void setExpRiskRulesId(String expRiskRulesId) {
		this.expRiskRulesId = expRiskRulesId;
	}
	
}
