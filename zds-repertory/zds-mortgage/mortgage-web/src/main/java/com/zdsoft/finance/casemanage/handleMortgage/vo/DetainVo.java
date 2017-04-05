package com.zdsoft.finance.casemanage.handleMortgage.vo;

import com.zdsoft.finance.casemanage.handleMortgage.entity.Detain;
import com.zdsoft.finance.common.base.BaseVo;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: DetainVo.java 
 * @ClassName: DetainVo 
 * @Description: 查册入押Vo
 * @author zhoushichao 
 * @date 2017年2月16日 下午6:10:36 
 * @version V1.0 
 */ 
public class DetainVo extends BaseVo<Detain>{
    
	private static final long serialVersionUID = 1L;
	/**
     * 房产Id
     */
    private String housePropertyId;
    /**
     * 抵押模式确认code
     */
    private String pledgeType;
    /**
     * 抵押模式确认name
     */
    private String pledgeTypeName;
    /**
     * 现抵押权人Id
     */
    private String presentId;
    /**
     * 现抵押权人名称
     */
    private String presentName;
    /**
     * 现抵押时间
     */
    private Long presentPledgeDate;
    /**
     * 预计出他证时间
     */
    private Long predictCertifiedDate;
    /**
     * 备注
     */
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
    public String getPresentId() {
		return presentId;
	}
	public void setPresentId(String presentId) {
		this.presentId = presentId;
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
    public String getPledgeTypeName() {
        return pledgeTypeName;
    }
    public void setPledgeTypeName(String pledgeTypeName) {
        this.pledgeTypeName = pledgeTypeName;
    }
    public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
    public DetainVo(){
        super();
    }
    public DetainVo(Detain detain){
        super(detain, null, new String[]{"pledgeType"});
    }
    public Detain toPo(){
        Detain detain = new Detain();
        return super.toPo(this, detain);
    }
}
