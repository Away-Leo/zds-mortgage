package com.zdsoft.finance.casemanage.dealpledge.detain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:Detain.java
 * @Package:com.zdsoft.finance.casemanage.dealpledge.detain.entity
 * @Description:入押表实体
 * @author: caixiekang
 * @date:2017年1月15日 下午2:00:12
 * @version:v1.0
 */

@Entity
@Table(name = "case_detain")
public class Detain extends BaseEntity {
    
    /**
     * 用一句话描述这个变量表示什么
     */
    private static final long serialVersionUID = 1681008240488637071L;
    /*
     * 房产Id
     */
    @Column(length = 32)
    private String housePropertyId;
    /*
     * 抵押类型
     */
    @Column(length = 20)
    private String pledgeType;
    /*
     * 现抵押权人
     */
    @Column(length = 32)
    private String presentName;
    /*
     * 现抵押时间
     */
    @Column
    private Long presentPledgeDate;
    /*
     * 预计出他证时间
     */
    @Column
    private Long predictCertifiedDate;
    /*
     * 备注
     */
    @Column(length = 500)
    private String mo;
   
    
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
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
    public String getPresentName() {
        return presentName;
    }
    public void setPresentName(String presentName) {
        this.presentName = presentName;
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
    public String getMo() {
        return mo;
    }
    public void setMo(String mo) {
        this.mo = mo;
    }
    
    
}
