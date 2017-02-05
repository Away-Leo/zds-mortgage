package com.zdsoft.finance.customer.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.customer.entity.WorkUnits;

/**
 * 工作单位
 * @author zhangchao
 * 2016/12/21
 */
public class WorkUnitsVo extends BaseVo<WorkUnits> {	

	/**
     * 客户id
     */
    private String clientId;
    
    /**
     * 姓名
     */
    private String unitsName;
    
    /**
     * 单位名称
     */
    private String companyName;
    
    /**
     * 单位电话
     */
    private String unitPhone;
    
    /**
     * 单位性质
     */
    private String unitNature;
    
    /**
     * 工作年限
     */
    private int workeYears;
    
    /**
     * 职务
     */
    private String position;
    
    /**
     * 行业类型
     */
    private String industryType;
    
    /**
     * 行业
     */
    private String industry;
    
    /**
     * 单位地址
     */
    private String unitAddress;
    
    /**
     * 关系类型
     */
    private String relationshipType;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getUnitsName() {
		return unitsName;
	}

	public void setUnitsName(String unitsName) {
		this.unitsName = unitsName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getUnitPhone() {
		return unitPhone;
	}

	public void setUnitPhone(String unitPhone) {
		this.unitPhone = unitPhone;
	}

	public String getUnitNature() {
		return unitNature;
	}

	public void setUnitNature(String unitNature) {
		this.unitNature = unitNature;
	}

	public int getWorkeYears() {
		return workeYears;
	}

	public void setWorkeYears(int workeYears) {
		this.workeYears = workeYears;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getIndustryType() {
		return industryType;
	}

	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}

	public String getUnitAddress() {
		return unitAddress;
	}

	public void setUnitAddress(String unitAddress) {
		this.unitAddress = unitAddress;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getRelationshipType() {
		return relationshipType;
	}

	public void setRelationshipType(String relationshipType) {
		this.relationshipType = relationshipType;
	}

	public WorkUnitsVo(){}
	
	public WorkUnitsVo(WorkUnits po){
		super(po);
	}
	
	public WorkUnits toPO(){
		WorkUnits po = new WorkUnits();
		return super.toPo(this, po);
	}
}
