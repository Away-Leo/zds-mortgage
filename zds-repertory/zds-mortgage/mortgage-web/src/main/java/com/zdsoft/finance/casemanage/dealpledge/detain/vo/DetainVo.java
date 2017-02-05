package com.zdsoft.finance.casemanage.dealpledge.detain.vo;

import javax.persistence.Column;

import com.zdsoft.finance.casemanage.dealpledge.detain.entity.Detain;
import com.zdsoft.finance.common.base.BaseVo;

public class DetainVo extends BaseVo<Detain>{
    
    /*
     * 房产Id
     */
    private String housePropertyId;
    /*
     * 抵押类型
     */
    private String pledgeType;
    /*
     * 抵押类型Name
     */
    private String pledgeTypeName;
    /*
     * 现抵押权人
     */
    private String presentName;
    /*
     * 现抵押时间
     */
    private Long presentPledgeDate;
    /*
     * 预计出他证时间
     */
    private Long predictCertifiedDate;
    /*
     * 备注
     */
    private String mo;
    
    
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
    
    
    public String getPledgeTypeName() {
        return pledgeTypeName;
    }
    public void setPledgeTypeName(String pledgeTypeName) {
        this.pledgeTypeName = pledgeTypeName;
    }
    //无参构造方法
    public DetainVo(){
        super();
    }
    /**
     * 
     * 构造函数 (simple转名字)
     *
     * @param detain
     */
    public DetainVo(Detain detain){
        super(detain, null, new String[]{"pledgeType"});
    }
    
    /**
     * 
     * 转换PO对象
     *
     * @author caixiekang
     * @param detainVo
     * @return
     */
    public Detain toPo(){
        Detain detain = new Detain();
        return (Detain)super.toPo(this, detain);
    }

}
