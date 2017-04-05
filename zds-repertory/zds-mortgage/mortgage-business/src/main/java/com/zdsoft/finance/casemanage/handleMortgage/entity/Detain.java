package com.zdsoft.finance.casemanage.handleMortgage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: Detain.java 
 * @ClassName: Detain 
 * @Description: 查册入押实体
 * @author zhoushichao 
 * @date 2017年2月16日 下午6:14:15 
 * @version V1.0 
 */ 
@Entity
@Table(name = "case_detain")
public class Detain extends BaseEntity {
    
    private static final long serialVersionUID = 1681008240488637071L;
    /**
     * 房产Id
     */
    @Column(length = 32)
    private String housePropertyId;
    /**
     * 抵押模式确认
     */
    @Column(length = 20)
    private String pledgeType;
    /**
     * 现抵押权人Id
     */
    @Column(length = 32)
    private String presentId;
    /**
     * 现抵押时间
     */
    @Column
    private Long presentPledgeDate;
    /**
     * 预计出他证时间
     */
    @Column
    private Long predictCertifiedDate;
    /**
     * 备注
     */
    @Column(length = 500)
    private String remark;
    public String getHousePropertyId() {
        return housePropertyId;
    }
    public void setHousePropertyId(String housePropertyId) {
        this.housePropertyId = housePropertyId;
    }
    public String getPledgeType() {
        return pledgeType;
    }
    public void setPledgeType(String pledgeType) {
        this.pledgeType = pledgeType;
    }
    public Long getPresentPledgeDate() {
        return presentPledgeDate;
    }
    public void setPresentPledgeDate(Long presentPledgeDate) {
        this.presentPledgeDate = presentPledgeDate;
    }
    public Long getPredictCertifiedDate() {
        return predictCertifiedDate;
    }
    public void setPredictCertifiedDate(Long predictCertifiedDate) {
        this.predictCertifiedDate = predictCertifiedDate;
    }
	public String getPresentId() {
		return presentId;
	}
	public void setPresentId(String presentId) {
		this.presentId = presentId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
